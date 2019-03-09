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
import java.util.Collections;

/**
 *
 * @author Cristian Azurdia
 */
public class Funcion_Ascendente implements Instruccion
{
    private int fila;
    private int columna;
    
    private FS_Arreglo arreglo;
    
    public Funcion_Ascendente(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
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
            if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero)
            {
                Collections.sort(arreglo, (Simbolo p1, Simbolo p2) -> new Integer(p2.getValor().toString()).compareTo(new Integer(p1.getValor().toString())));
            }
            else if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                Collections.sort(arreglo, (Simbolo p1, Simbolo p2) -> new Double(p2.getValor().toString()).compareTo(new Double(p1.getValor().toString())));
            }
            else if(arreglo.getHomogeneo() && arreglo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Collections.sort(arreglo, (Simbolo p1, Simbolo p2) -> p2.getValor().toString().compareTo(p1.getValor().toString()));
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La llamada a funciones Ascendente solo puede realizarse en arreglos homogeneos y de tipo numerico o cadena");

                return nuevo_simbolo;
            }                                

            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setValor("La llamada a funciones de un Arreglo no fue realizada");
            
            return nuevo_simbolo;
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
