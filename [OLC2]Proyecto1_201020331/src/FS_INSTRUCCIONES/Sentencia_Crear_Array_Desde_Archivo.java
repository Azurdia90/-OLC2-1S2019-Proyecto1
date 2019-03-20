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
public class Sentencia_Crear_Array_Desde_Archivo implements Instruccion
{
    private int fila;
    private int columna;
    
    private String ventana;
    private String path;
    private boolean lectura;
    
    public Sentencia_Crear_Array_Desde_Archivo(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());    
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        if(lectura)
        {
            this.ventana =  "";
            this.path = nodo_sentencia.getHijos().get(0).getValor().substring(0, nodo_sentencia.getHijos().get(0).getValor().length() - 1);
        }
        else
        {
            this.ventana = nodo_sentencia.getValor().substring(0, nodo_sentencia.getValor().length() - 1);
            this.path = "";
        }        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            if(lectura)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo fue realizada exitosamente.");

                return nuevo_simbolo;
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo fue realizada exitosamente.");

                return nuevo_simbolo;
            }         
        }
        catch (Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;                      
        }
    }    
}
