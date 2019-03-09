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
public class Funcion_Crear_Desde_Archivo implements Instruccion
{
    private int fila;
    private int columna;
    
    private FS_Arreglo arreglo;
    
    public Funcion_Crear_Desde_Archivo(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.arreglo = p_arreglo;        
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
