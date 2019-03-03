/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Fabrica_Sentencias
{
    private int fila;
    private int columna;
    
    private Nodo_AST_FS sentencia_ejecutar;
    
    public Fabrica_Sentencias(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.sentencia_ejecutar = nodo_sentencia;
    }

    public Instruccion ejecutar() 
    {
        if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_ASIGNACION"))
        {
            Sentencia_Asignacion nueva_asignacion = new Sentencia_Asignacion(sentencia_ejecutar);
            return nueva_asignacion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_IMPRIMIR"))
        {
            Sentencia_Imprimir nueva_impresion = new Sentencia_Imprimir(sentencia_ejecutar);
            return nueva_impresion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_SI")||sentencia_ejecutar.IsNodoOrNot("SENTENCIA_SINO_SI"))
        {
            Sentencia_Si nuevo_si = new Sentencia_Si(sentencia_ejecutar);
            return nuevo_si;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_SELECCIONA"))
        {
            Sentencia_Selecciona nuevo_selecciona = new Sentencia_Selecciona(sentencia_ejecutar);
            return nuevo_selecciona;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CASOS"))
        {
            Sentencia_Caso nuevo_caso = new Sentencia_Caso(sentencia_ejecutar);
            return nuevo_caso;
        }else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_DETENER"))
        {
            Sentencia_Detener nuevo_detener = new Sentencia_Detener(sentencia_ejecutar);
            return nuevo_detener;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_LLAMADA"))
        {
            Sentencia_LLamada nueva_llamada = new Sentencia_LLamada(sentencia_ejecutar);
            return nueva_llamada;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_RETORNAR"))
        {
            Sentencia_Retornar nuevo_retornar = new Sentencia_Retornar(sentencia_ejecutar);
            return nuevo_retornar;
        }
        else
        {
            return null;
        }        
    }
}