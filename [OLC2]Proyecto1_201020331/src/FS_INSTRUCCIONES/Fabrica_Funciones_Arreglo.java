/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Arreglo;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Fabrica_Funciones_Arreglo 
{
    private int fila;
    private int columna;
    
    private Nodo_AST_FS funcion_ejecutar;
    
    public Fabrica_Funciones_Arreglo(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.funcion_ejecutar = nodo_sentencia;
    }
    
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida, FS_Arreglo p_arreglo) 
    {
        Simbolo resultado;
        if(funcion_ejecutar.IsNodoOrNot("FUNCION_DESCENDENTE"))
        {
            Funcion_Descendente nueva_funcion = new Funcion_Descendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_ASCENDENTE"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_CREAR_DESDE_ARCHIVO"))
        {
            Funcion_Crear_Desde_Archivo nueva_funcion = new Funcion_Crear_Desde_Archivo(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_INVERTIR"))
        {
            Funcion_Invertir nueva_funcion = new Funcion_Invertir(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_MAXIMO"))
        {
            Funcion_Maximo nueva_funcion = new Funcion_Maximo(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_MINIMO"))
        {
            Funcion_Minimo nueva_funcion = new Funcion_Minimo(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_FILTRAR"))
        {
            Funcion_Filtrar nueva_funcion = new Funcion_Filtrar(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_BUSCAR"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_MAP"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_REDUCE"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_TODOS"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else if(funcion_ejecutar.IsNodoOrNot("FUNCION_ALGUNO"))
        {
            Funcion_Ascendente nueva_funcion = new Funcion_Ascendente(funcion_ejecutar,p_arreglo);
            resultado = nueva_funcion.ejecutar(entorno_local, salida);
            return resultado;
        }
        else
        {
            return null;
        } 
    }    
}
