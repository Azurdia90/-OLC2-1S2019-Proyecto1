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
import UI.ObjetoEntrada;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Sentencia_Declaracion implements Instruccion
{
    private int fila;
    private int columna;
    
    private ArrayList<String> identificadores;
    private Expresion expresion;
    private FS_Arreglo arreglo;
    private FS_Objeto objeto;
    
    private Nodo_AST_FS lista_identificadores;
    private int tipo_valor;  //0 sin valor  1 Expresion, 2 Arreglo, 3 Objeto
    
    public Sentencia_Declaracion(Nodo_AST_FS nodo_sentencia)            
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        if(nodo_sentencia.getHijos().size() == 1)
        {
            this.lista_identificadores = nodo_sentencia.getHijos().get(0);
            this.crearLista_identificadores();
            this.tipo_valor = 0;
        }
        else if(nodo_sentencia.getHijos().size() == 2)
        {
            this.lista_identificadores = nodo_sentencia.getHijos().get(0);
            this.crearLista_identificadores();
            
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
    }   
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        
        try
        {
            if( identificadores.size()> 1)
            {   
                for(int i = 0; i < identificadores.size()-1; i++)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                    nuevo_simbolo.setValor(null);
                    nuevo_simbolo.setIdentificador(identificadores.get(i));    

                    if(entorno_local.Obtener(identificadores.get(i)).getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        return entorno_local.Crear(identificadores.get(i), nuevo_simbolo);
                    }
                    else
                    {   
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);                        
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);
                        nuevo_simbolo.setValor("La variable \"" + identificadores.get(0) + "\" ya existe en el entorno local");
                        return nuevo_simbolo;
                    }                                                               
                } 
     
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                nuevo_simbolo.setIdentificador(identificadores.get(identificadores.size()-1));   


                if(tipo_valor == 1)
                {
                    Simbolo simbolo_expresion = expresion.ejecutar(entorno_local, salida);
                    if(simbolo_expresion.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(simbolo_expresion.getRol());
                        nuevo_simbolo.setTipo(simbolo_expresion.getTipo());
                        nuevo_simbolo.setValor(simbolo_expresion.getValor()); 
                    }
                    else
                    {
                        return simbolo_expresion;
                    }                           
                }    
                else if(tipo_valor == 2)
                {
                    Simbolo simbolo_arreglo = arreglo.ejecutar(entorno_local, salida);
                    if(simbolo_arreglo.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setValor(arreglo); 
                    }
                    else
                    {
                        return simbolo_arreglo;
                    }
                }
                else if(tipo_valor ==3)
                {
                    Simbolo simbolo_objeto = objeto.ejecutar(entorno_local, salida);
                    if(simbolo_objeto.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setValor(objeto); 
                    }
                    else
                    {
                        return simbolo_objeto;
                    }
                }
                
                //REALIZAR LA DECLARACION EN EL ENTORNO LOCAL
                if(entorno_local.Obtener(identificadores.get(identificadores.size()-1)).getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return entorno_local.Crear(identificadores.get(identificadores.size()-1), nuevo_simbolo);
                }
                else
                {                           
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);                        
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setValor("La variable \"" + identificadores.get(0) + "\" ya existe en el entorno local");
                    return nuevo_simbolo;
                }
            }        
            else if(identificadores.size() == 1)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                nuevo_simbolo.setIdentificador(identificadores.get(0));   
                nuevo_simbolo.setValor(null);
                    
                if(tipo_valor == 1)
                {
                    Simbolo simbolo_expresion = expresion.ejecutar(entorno_local, salida);
                    if(simbolo_expresion.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(simbolo_expresion.getRol());
                        nuevo_simbolo.setTipo(simbolo_expresion.getTipo());
                        nuevo_simbolo.setValor(simbolo_expresion.getValor());                        
                    }
                    else
                    {
                        return simbolo_expresion;
                    }                    
                }
                else if(tipo_valor == 2)
                {
                    Simbolo simbolo_arreglo = arreglo.ejecutar(entorno_local, salida);
                    if(simbolo_arreglo.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setValor(arreglo);                        
                    }
                    else
                    {
                        return simbolo_arreglo;
                    }
                }
                else if(tipo_valor == 3)
                {
                    Simbolo simbolo_objeto = objeto.ejecutar(entorno_local, salida);
                    if(simbolo_objeto.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setValor(objeto);                        
                    }
                    else
                    {
                        return simbolo_objeto;
                    }
                }
                
                //REALIZAR LA DECLARACION EN EL ENTORNO LOCAL
                if(entorno_local.Obtener(identificadores.get(0)).getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return entorno_local.Crear(identificadores.get(0), nuevo_simbolo);
                }
                else
                {                          
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setValor("La variable \"" + identificadores.get(0) + "\" ya existe en el entorno local");
                    return nuevo_simbolo;
                }  
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador(fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("Sentencia Declaracion no fue realizada, no existian valariables a declarar.");

                return nuevo_simbolo;                
            }
            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Declaracion no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        } 
    }

    private void crearLista_identificadores()
    {            
        identificadores = new ArrayList<String>();
        for(int i = 0; i < lista_identificadores.getHijos().size(); i++)
        {
            identificadores.add(lista_identificadores.getHijos().get(i).getValor());
        }
    }
            
}
