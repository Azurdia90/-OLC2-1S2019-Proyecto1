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
    private ArrayList<Instruccion> lista_sentencias_else;
    
    private Nodo_AST_FS lista_nodos_sentencias;
    private Nodo_AST_FS lista_nodos_sentencias_else;
    
    private Entorno entorno_local;
    
    public Sentencia_Si(Nodo_AST_FS nodo_sentencia)
    {   
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        if(nodo_sentencia.getHijos().size() == 2)
        {
            this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
            
            this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
            this.lista_sentencias = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
            this.lista_sentencias_else = new ArrayList<Instruccion>();            
        }
        else if(nodo_sentencia.getHijos().size() == 3)
        {
            this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));

            this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
            this.lista_sentencias = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);                
            this.lista_nodos_sentencias_else = nodo_sentencia.getHijos().get(2);
            this.lista_sentencias_else = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias_else,lista_sentencias_else);                
        }
        else if(nodo_sentencia.getHijos().size() == 4)
        {
            this.condicion = new Expresion(nodo_sentencia.getHijos().get(0));
            
            this.lista_nodos_sentencias = nodo_sentencia.getHijos().get(1);
            this.lista_sentencias = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias,lista_sentencias);
            this.lista_nodos_sentencias_else = nodo_sentencia.getHijos().get(2);
            this.lista_sentencias_else = new ArrayList<Instruccion>();
            this.llenarListaSentencias(lista_nodos_sentencias_else,lista_sentencias_else);                        
            this.lista_nodos_sentencias_else = nodo_sentencia.getHijos().get(3);
            this.agregarElseListaSentencias(lista_nodos_sentencias_else,lista_sentencias_else);;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo resultado_condicion = condicion.ejecutar(entorno_padre, salida);
            Simbolo display;

            if(resultado_condicion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {       
                entorno_local = new Entorno();
                FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_local);

                if(resultado_condicion.getValor().equals("verdadero") ? true : false)
                {                                
                    for(int i= 0; i < lista_sentencias.size(); i++)
                    {
                        display = lista_sentencias.get(i).ejecutar(entorno_local, salida);
                        if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .detener || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .retornar)
                        {
                           FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
                           return display;
                        }
                    }                
                }
                else
                {                                                   
                    for(int i= 0; i < lista_sentencias_else.size(); i++)
                    {
                        display = lista_sentencias_else.get(i).ejecutar(entorno_local, salida);
                        if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .detener || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .retornar)
                        {
                           FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
                           return display;
                        }
                    }                  
                }

                FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();

                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                nuevo_simbolo.setValor("Sentencia Si realizada correctamente");  

                return nuevo_simbolo;
            }
            else if(resultado_condicion.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return resultado_condicion;
            }
            else    
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador(fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La condicion de la sentencia Si debe dar como resultado un valor booleano.");           

                return nuevo_simbolo;
            }            
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
    
    private void agregarElseListaSentencias(Nodo_AST_FS nodos_sentencias, ArrayList<Instruccion> lista_sentencias)
    {
        Fabrica_Sentencias fabrica;
        Instruccion instruccion_aux;
        
        Sentencia_Si sentencia_if_final = (Sentencia_Si)lista_sentencias.get(lista_sentencias.size() - 1);
        
        for(int i = 0; i < nodos_sentencias.getHijos().size(); i++)
        {
            fabrica = new Fabrica_Sentencias(nodos_sentencias.getHijos().get(i));
            instruccion_aux = fabrica.ejecutar();
            if(instruccion_aux != null)
            {
               sentencia_if_final.getLista_sentencias_else().add(instruccion_aux);
            }
        }
    } 

    public ArrayList<Instruccion> getLista_sentencias_else() {
        return lista_sentencias_else;
    }

    public void setLista_sentencias_else(ArrayList<Instruccion> lista_sentencias_else) {
        this.lista_sentencias_else = lista_sentencias_else;
    }
        
}
