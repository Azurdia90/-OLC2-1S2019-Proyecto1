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
public class Sentencia_Si implements Instruccion
{
    private int fila;
    private int columna;
    
    private Expresion condicion;
    private ArrayList<Instruccion> lista_sentencias;
    private ArrayList<Instruccion> lista_sentencias_else_if;
    private ArrayList<Instruccion> lista_sentencias_else;
    
    private Nodo_AST_FS lista_nodos_sentencias;
    private Nodo_AST_FS lista_nodos_sentencias_else_if;
    private Nodo_AST_FS lista_nodos_sentencias_else;
    private Entorno entorno_local;
    
    public Sentencia_Si(Nodo_AST_FS nodo_sentencia)
    {
        entorno_local = new Entorno();
        FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_local);
        
        if(nodo_sentencia.getHijos().size() == 2)
        {
            this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
            
            this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
            this.lista_sentencias = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
            this.lista_sentencias_else_if = new ArrayList<Instruccion>();
            this.lista_sentencias_else = new ArrayList<Instruccion>();            
        }
        else if(nodo_sentencia.getHijos().size() == 3)
        {
            if(nodo_sentencia.getHijos().get(2).IsNodoOrNot("LISTA_SENTENCIAS"))
            {
                this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
                
                this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
                this.lista_sentencias = new ArrayList<Instruccion>();
                this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
                this.lista_sentencias_else_if = new ArrayList<Instruccion>();
                this.lista_nodos_sentencias_else = nodo_sentencia.getHijos().get(2);
                this.lista_sentencias_else = new ArrayList<Instruccion>();
                this.llenarListaSentencias(lista_nodos_sentencias_else,lista_sentencias_else);
                
            }
            else
            {
                this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
                
                this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
                this.lista_sentencias = new ArrayList<Instruccion>();
                this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
                this.lista_nodos_sentencias_else_if = nodo_sentencia.getHijos().get(2);
                this.lista_sentencias_else_if = new ArrayList<Instruccion>();
                this.llenarListaSentencias(lista_nodos_sentencias_else_if,lista_sentencias_else_if);
                this.lista_sentencias_else = new ArrayList<Instruccion>();
            }
        }
        else if(nodo_sentencia.getHijos().size() == 4)
        {
            this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
            
            this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
            this.lista_sentencias = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
            this.lista_nodos_sentencias_else_if = nodo_sentencia.getHijos().get(2);
            this.lista_sentencias_else_if = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias_else_if,lista_sentencias_else_if);
            this.lista_nodos_sentencias_else = nodo_sentencia.getHijos().get(3);
            this.lista_sentencias_else = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias_else,lista_sentencias_else);;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        Simbolo resultado_condicion = new Simbolo();
        resultado_condicion = condicion.ejecutar(entorno_padre, salida);
        
        if(resultado_condicion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
        {            
            if(resultado_condicion.getValor().equals("verdadero") ? true : false)
            {
                for(int i= 0; i < lista_sentencias.size(); i++)
                {
                    lista_sentencias.get(i).ejecutar(entorno_local, salida);
                }                                                                  
            }
            else
            {
                for(int i= 0; i < lista_sentencias_else_if.size(); i++)
                {
                    lista_sentencias_else_if.get(i).ejecutar(entorno_local, salida);
                } 
                
                for(int i= 0; i < lista_sentencias_else.size(); i++)
                {
                    lista_sentencias_else.get(i).ejecutar(entorno_local, salida);
                } 
                
            }
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
            nuevo_simbolo.setValor("Sentencia Si realizada correctamente");  
            
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
            
            return nuevo_simbolo;
        }
        else
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("La condicion de la sentencia Si debe dar como resultado un valor booleano.");
            
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
            
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
}
