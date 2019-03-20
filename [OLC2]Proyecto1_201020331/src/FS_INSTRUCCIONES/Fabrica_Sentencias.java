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
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_ARRAY_DESDE_ARCHIVO"))
        {
            Sentencia_Crear_Array_Desde_Archivo nuevo_crear_array_desde_archivo = new Sentencia_Crear_Array_Desde_Archivo(sentencia_ejecutar);
            return nuevo_crear_array_desde_archivo;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_LLAMADA_FUNCIONES_ARREGLO"))
        {
            Sentencia_LLamada_Funciones_Arreglo nueva_llamada = new Sentencia_LLamada_Funciones_Arreglo(sentencia_ejecutar);
            return nueva_llamada;
        }    
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_LEER_GXML"))
        {
            Sentencia_Leer_GXML nuevo_leer_GXML = new Sentencia_Leer_GXML(sentencia_ejecutar);
            return nuevo_leer_GXML;
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
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_TEXTO"))
        {
            Sentencia_Crear_Texto nuevo_texto = new Sentencia_Crear_Texto(sentencia_ejecutar);
            return nuevo_texto;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_CAJA_TEXTO"))
        {
            Sentencia_Crear_Caja_Texto nuevo_caja_texto = new Sentencia_Crear_Caja_Texto(sentencia_ejecutar);
            return nuevo_caja_texto;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_AREA_TEXTO"))
        {
            Sentencia_Crear_Area_Texto nuevo_area_texto = new Sentencia_Crear_Area_Texto(sentencia_ejecutar);
            return nuevo_area_texto;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_CONTROL_NUMERICO"))
        {
            Sentencia_Crear_Control_Numerico nuevo_control_numerico = new Sentencia_Crear_Control_Numerico(sentencia_ejecutar);
            return nuevo_control_numerico;
        }        
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_DESPLEGABLE"))
        {
            Sentencia_Crear_Desplegable nuevo_desplegable = new Sentencia_Crear_Desplegable(sentencia_ejecutar);
            return nuevo_desplegable;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_BOTON"))
        {
            Sentencia_Crear_Boton nuevo_boton = new Sentencia_Crear_Boton(sentencia_ejecutar);
            return nuevo_boton;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_IMAGEN"))
        {            
            Sentencia_Crear_Imagen nueva_imagen = new Sentencia_Crear_Imagen(sentencia_ejecutar);
            return nueva_imagen;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_REPRODUCTOR"))
        {           
            Sentencia_Crear_Musica nueva_musica = new Sentencia_Crear_Musica(sentencia_ejecutar);
            return nueva_musica;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_CREAR_VIDEO"))
        {
            return null;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_EVENTO_ALCLIC"))
        {
            Sentencia_Evento_AlClic nuevo_clic = new Sentencia_Evento_AlClic(sentencia_ejecutar);
            return nuevo_clic;
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
