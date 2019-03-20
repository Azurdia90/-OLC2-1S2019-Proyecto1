/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Seleccion implements Instruccion
{
    private int fila;
    private int columna;
    
    private Expresion expresion_condicion;
    private Expresion expresion1;
    private Expresion expresion2;
    
    private Simbolo expresion;
    private Simbolo valor1;
    private Simbolo valor2;
    
    public Sentencia_Seleccion(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.expresion_condicion = new Expresion(nodo_sentencia.getHijos().get(0));
        this.expresion1 = new Expresion(nodo_sentencia.getHijos().get(1));
        this.expresion2 = new Expresion(nodo_sentencia.getHijos().get(2));
    }
        
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            expresion = (expresion_condicion == null) ? null : expresion_condicion.ejecutar(entorno_local, salida);
            
            if(expresion != null && expresion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                if(expresion.getValor().toString().equalsIgnoreCase("verdadero"))
                {                    
                    return (expresion1 == null) ? null : expresion1.ejecutar(entorno_local, salida);
                }
                else
                {
                    return (expresion2 == null) ? null : expresion2.ejecutar(entorno_local, salida);
                }
            }
            else
            {                
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador(fila + " - " + columna);                
                nuevo_simbolo.setValor("No es posible que la condicion de la sentencia seleccion de como resultado un valor tipo  " + expresion.getTipo() + ".");
                return nuevo_simbolo;
            }
            
            /*            
            valor1 = (expresion1 == null) ? null : expresion1.ejecutar(entorno_local, salida);
            valor2 = (expresion2 == null) ? null : expresion2.ejecutar(entorno_local, salida);
            
            if(expresion == null ||valor1 == null || valor2 == null)
            {
                return null;
            }
            else if(expresion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return expresion;
            }
            else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor1;
            }
            else if(valor2.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor2;
            }           
            
            Simbolo nuevo_simbolo = new Simbolo();
            
            if(expresion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                if(expresion.getValor().equals("verdadero") ? true : false)
                {
                    return valor1;
                }
                else
                {
                    return valor2;
                }
            }
            else
            {
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador(fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("No es posible que la condicion de la sentencia seleccion de como resultado un valor tipo  " + expresion.getTipo() + ".");
            } */                              
                       
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar la sentencia seleccion, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }
    
}
