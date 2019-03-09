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
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.Comparator;

/**
 *
 * @author Cristian Azurdia
 */
public class Funcion_Minimo implements Instruccion
{
    private int fila;
    private int columna;
    
    private FS_Arreglo arreglo;
    
    public Funcion_Minimo(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.arreglo = p_arreglo;        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo minValue;
            
            if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero)
            {
                minValue = arreglo.stream().min(Comparator.comparing(v -> Integer.parseInt(v.getValor().toString()))).get();
            }
            else if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                minValue = arreglo.stream().min(Comparator.comparing(v -> Double.parseDouble(v.getValor().toString()))).get();
            }
            else if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                minValue = arreglo.stream().min(Comparator.comparing(v -> v.getValor().toString())).get();
            }
            else
            {
                minValue = new Simbolo();
                minValue.setRol(Tabla_Enums.tipo_Simbolo.error);
                minValue.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                minValue.setIdentificador( fila + " - " + columna);
                minValue.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                minValue.setValor("La llamada a funciones Minimo solo puede realizarse en arreglos homogeneos y de tipo numerico o cadena");                
            }
            return minValue;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("La llamada a funciones de un Arreglo no fue realizada, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }
}
