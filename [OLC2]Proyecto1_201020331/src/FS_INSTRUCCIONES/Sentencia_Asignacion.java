/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Objeto;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Sentencia_Asignacion implements Instruccion
{
    private int fila;
    private int columna;
    
    //OBJETOS A LOS QUE SE LE ASIGNARA UN VALOR
    private ArrayList<String> lista_identificadores;   
    private Sentencia_Acceso acceso_simbolo;
    
    //VARIABLE QUE NOS PERMITE SABER QUE TIPO DE OBJETO MODIFICAREMOS
    private int tipo_acceso;  //0 sin valor  1 Expresion, 2 acceso
    
    //OBJETOS DE LOS QUE SE OBTENDRA UN VALOR
    private Expresion expresion;
    private FS_Arreglo arreglo;
    private FS_Objeto objeto;
    
    //VARIABLE QUE NOS PERMITE SABER QUE TIPO DE VALOR OBTENDREMOS
    private int tipo_valor;  //0 sin valor  1 Expresion, 2 Arreglo, 3 Objeto
    
    public Sentencia_Asignacion(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.lista_identificadores = new ArrayList<>();
        
        if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("LISTA_IDENTIFICADORES"))
        {
            this.crearLista_identificadores(nodo_sentencia.getHijos().get(0), lista_identificadores);
            this.tipo_acceso = 1;
        }
        else if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("SENTENCIA_ACCESO"))
        {
            acceso_simbolo = new Sentencia_Acceso(nodo_sentencia.getHijos().get(0));
            this.tipo_acceso = 2;
        }
        
        if(nodo_sentencia.getHijos().get(1).IsNodoOrNot("EXPRESION"))
        {
            this.expresion = new Expresion(nodo_sentencia.getHijos().get(1).getHijos().get(0));
            this.tipo_valor = 1;
        }
        else if(nodo_sentencia.getHijos().get(1).IsNodoOrNot("ARREGLO"))
        {
            this.arreglo = new FS_Arreglo(nodo_sentencia.getHijos().get(1));
            this.tipo_valor = 2;
        }
        else if(nodo_sentencia.getHijos().get(1).IsNodoOrNot("OBJETO"))
        {
            this.objeto = new FS_Objeto(nodo_sentencia.getHijos().get(1));
            this.tipo_valor = 3;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo variable_encontrada = null;
            Simbolo valor_asignar = null;
            Simbolo arreglo_asignar = null;
            Simbolo objeto_asignar = null;
            
            if(tipo_valor == 1)
            {
                valor_asignar = expresion.ejecutar(entorno_local, salida);
                if(valor_asignar.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return valor_asignar;
                }                
            }
            else if(tipo_valor == 2)
            {
                arreglo_asignar = arreglo.ejecutar(entorno_local, salida);
                if(arreglo_asignar.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return arreglo_asignar;
                }                                                                    
            }
            else if(tipo_valor == 3)
            {
                objeto_asignar = objeto.ejecutar(entorno_local, salida);
                if(objeto_asignar.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return objeto_asignar;
                }                
            }
                        
            if(tipo_acceso == 1)
            {
                //REALIZAR LA DECLARACION EN EL ENTORNO LOCAL
                //for(int i = 0; i < lista_identificadores.size(); i++)
                //{ 
                    //variable_encontrada = entorno_local.Obtener(lista_identificadores.get(i));

                    variable_encontrada = entorno_local.Obtener(lista_identificadores.get(lista_identificadores.size()-1));

                    if(variable_encontrada.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        if(tipo_valor == 1)
                        {
                            variable_encontrada.setRol(valor_asignar.getRol());
                            variable_encontrada.setTipo(valor_asignar.getTipo());
                            variable_encontrada.setValor(valor_asignar.getValor());                    
                        }
                        else if (tipo_valor == 2)
                        {
                            variable_encontrada.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                            variable_encontrada.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                            variable_encontrada.setValor(arreglo);
                        }    
                        else if(tipo_valor == 3)
                        {
                            variable_encontrada.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                            variable_encontrada.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                            variable_encontrada.setValor(objeto);
                        }                                                        
                    }
                    else
                    {
                        return variable_encontrada;
                    }  
                //}                     
            }
            else if(tipo_acceso == 2)
            {
                variable_encontrada = acceso_simbolo.ejecutar(entorno_local, salida);

                if(variable_encontrada.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    if(tipo_valor == 1)
                    {
                        variable_encontrada.setRol(valor_asignar.getRol());
                        variable_encontrada.setTipo(valor_asignar.getTipo());
                        variable_encontrada.setValor(valor_asignar.getValor());                    
                    }
                    else 
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("Sentencia Asignacion no fue realizada, error: Un Objeto o Arreglo no puede almacenar otro Objeto o Arreglo.");

                        return nuevo_simbolo;                   
                    }                    
                }
                else
                {
                    return variable_encontrada;
                }                    
            } 
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
            nuevo_simbolo.setValor("Sentencia Asignacion realizada correctamente");  

            return nuevo_simbolo;
            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Asignacion no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }   
    }
    
    private void crearLista_identificadores(Nodo_AST_FS lista_identificadores, ArrayList<String> identificadores)
    {            
       
        for(int i = 0; i < lista_identificadores.getHijos().size(); i++)
        {
            identificadores.add(lista_identificadores.getHijos().get(i).getValor());
        }
    }
    
}
