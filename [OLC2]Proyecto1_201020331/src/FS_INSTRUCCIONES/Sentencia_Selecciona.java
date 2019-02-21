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
public class Sentencia_Selecciona implements Instruccion
{
    private int fila;
    private int columna;
    
    private Expresion condicion;
    private ArrayList<Sentencia_Caso> lista_casos; 
    private ArrayList<Instruccion> lista_sentencias_defecto;
    
    public Sentencia_Selecciona(Nodo_AST_FS nodo_sentencia)
    {
        this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
        
        this.lista_casos = new ArrayList<Sentencia_Caso>();
        this.llenarListaCasos(nodo_sentencia.getHijos().get(1), lista_casos);
        
        this.lista_sentencias_defecto = new ArrayList<Instruccion>();
        if(nodo_sentencia.getHijos().size() == 3)
        {
            this.llenarListaSentencias(nodo_sentencia.getHijos().get(2), lista_sentencias_defecto);
        }
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Impresion no fue realizada");  
            
            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Selecciona no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }
    
    private void llenarListaCasos(Nodo_AST_FS nodos_sentencias, ArrayList<Sentencia_Caso> lista_casos)
    {
        Fabrica_Sentencias fabrica;
        Sentencia_Caso instruccion_aux;
        for(int i = 0; i < nodos_sentencias.getHijos().size(); i++)
        {
            fabrica = new Fabrica_Sentencias(nodos_sentencias.getHijos().get(i));
            instruccion_aux = (Sentencia_Caso)fabrica.ejecutar();
            if(instruccion_aux != null)
            {
                lista_casos.add(instruccion_aux);
            }
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
    
}
