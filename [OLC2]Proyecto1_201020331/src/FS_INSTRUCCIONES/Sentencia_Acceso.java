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

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Acceso implements Instruccion
{
    private int fila;
    private int columna;    
    private String identificador;
    
    private String atributo;
    private Expresion posicion;
    
    private int tipo_acceso; //0 error 1 objeto 2 arreglo
    
    public Sentencia_Acceso(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("IDENTIFICADOR"))
        {
            this.atributo = nodo_sentencia.getHijos().get(0).getValor();
            this.tipo_acceso = 1;
        }
        else if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("EXPRESION"))
        {
            this.posicion = new Expresion(nodo_sentencia.getHijos().get(0).getHijos().get(0));
            this.tipo_acceso = 2;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo simbolo_obtenido = entorno_local.Obtener(identificador);
            
            if(simbolo_obtenido.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return simbolo_obtenido;
            }
            
            if(tipo_acceso == 1)
            {                
                FS_Objeto objeto_aux = (FS_Objeto) simbolo_obtenido.getValor();
                
                if(objeto_aux.containsKey(atributo))
                {
                    return objeto_aux.get(atributo);
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                    nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                    return nuevo_simbolo; 
                }                
            }
            else if(tipo_acceso == 2)
            {   
                FS_Arreglo arreglo_aux = (FS_Arreglo) simbolo_obtenido.getValor();
                Simbolo posicion_obtenida = posicion.ejecutar(entorno_local, salida);
                
                if(posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return posicion_obtenida;
                }
                else if(posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
                {
                    int posicion_final = Integer.parseInt(posicion_obtenida.getValor().toString());
                    if(posicion_final > -1)
                    {
                        if(posicion_final < (arreglo_aux.size()))
                        {
                            return arreglo_aux.get(posicion_final);
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                            nuevo_simbolo.setValor("El valor de la posicion de acceso es mayor al tamaño del arreglo.");

                            return nuevo_simbolo; 
                        }
                    }
                    else 
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El valor de la posicion de acceso a un arreglo debe ser un numero mayor o igual a 0.");

                        return nuevo_simbolo;    
                    }                        
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                    nuevo_simbolo.setValor("El valor de la posicion de acceso a un arreglo debe ser un valor numerico.");

                    return nuevo_simbolo; 
                }                
            }
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setValor("Sentencia Acceso realizada exitosamente.");

            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Acceso no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;            
        }
    }
}
