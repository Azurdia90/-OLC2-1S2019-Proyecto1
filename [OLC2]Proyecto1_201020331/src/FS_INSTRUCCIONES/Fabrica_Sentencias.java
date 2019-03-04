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
        if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_DECLARACION"))
        {
            Sentencia_Declaracion nueva_declaracion = new Sentencia_Declaracion(sentencia_ejecutar);
            return nueva_declaracion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_ASIGNACION"))
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
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_ACCESO"))
        {
            Sentencia_LLamada nueva_llamada = new Sentencia_LLamada(sentencia_ejecutar);
            return nueva_llamada;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_VENTANA"))
        {
            Sentencia_Crear_Ventana nuevo_crear_ventana = new Sentencia_Crear_Ventana(sentencia_ejecutar);
            return nuevo_crear_ventana;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_CONTENEDOR"))
        {
            Sentencia_Crear_Contenedor nuevo_contenedor = new Sentencia_Crear_Contenedor(sentencia_ejecutar);
            return nuevo_contenedor;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_EVENTO_ALCARGAR"))
        {
            Sentencia_Evento_AlCargar nuevo_cargar = new Sentencia_Evento_AlCargar(sentencia_ejecutar);
            return nuevo_cargar;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_EVENTO_ALCERRAR"))
        {
            Sentencia_Evento_AlCerrar nuevo_cerrar = new Sentencia_Evento_AlCerrar(sentencia_ejecutar);
            return nuevo_cerrar;
        }
        else
        {
            return null;
        }        
    }
}
