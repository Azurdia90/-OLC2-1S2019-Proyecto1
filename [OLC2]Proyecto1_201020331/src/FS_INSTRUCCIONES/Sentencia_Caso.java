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
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Caso implements Instruccion
{
    private int fila;
    private int columna;
    
    private Entorno entorno_local;
    private Expresion condicion;
    private ArrayList<Instruccion> lista_sentencias;    
    
    public Sentencia_Caso(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
        this.lista_sentencias = new ArrayList<Instruccion>();
        this.llenarListaSentencias(nodo_sentencia.getHijos().get(1), lista_sentencias);
    }
   
    @Override
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo display = null;
            Entorno entorno_local = new Entorno();   
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_local);
            
            for(int i = 0; i < lista_sentencias.size(); i++)
            {
                display = lista_sentencias.get(i).ejecutar(entorno_local, salida);
                if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.detener || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.retornar || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
                    return display;
                }
            }
            
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
            nuevo_simbolo.setValor("Sentencia Caso realizada correctamente");  

            return nuevo_simbolo;
            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Selecciona no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;            
        }
    }
    
    private void llenarListaSentencias(Nodo_AST_FS nodos_sentencias, ArrayList<Instruccion> lista_sentencias)
    {
        Fabrica_Sentencias fabrica;
        Instruccion instruccion_aux;
        for(int i = 0; i < nodos_sentencias.getHijos().size(); i++)
        {
            fabrica = new Fabrica_Sentencias(nodos_sentencias.getHijos().get(i));
            instruccion_aux = fabrica.ejecutar();
            if(instruccion_aux != null)
            {
                lista_sentencias.add(instruccion_aux);
            }
        }
    }  

    public Expresion getCondicion() {
        return condicion;
    }

    public void setCondicion(Expresion condicion) {
        this.condicion = condicion;
    }
            
}
