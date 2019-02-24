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
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo condicion_base = condicion.ejecutar(entorno_padre, salida);
            Simbolo condicion_caso = null;
            Simbolo display;
            
            double valor_base_double;
            boolean valor_base_booleano;
            
            
            double valor_caso_double;
            boolean valor_caso_booleano;
            
            Entorno entorno_local = new Entorno();            
            
            if(condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal || condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {                          
                FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_local);
                boolean continuar_selecciona = false;
                
                for(int i = 0; i < lista_casos.size(); i++)
                {
                    condicion_caso = lista_casos.get(i).getCondicion().ejecutar(entorno_padre, salida);
                    
                    if( (condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano && condicion_caso.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano) || ( (condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal) && (condicion_caso.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || condicion_caso.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)))
                    {
                        if(condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
                        {
                           valor_base_booleano = condicion_base.getValor().toString().equals("verdadero") ? true : false;
                           valor_caso_booleano = condicion_caso.getValor().toString().equals("verdadero") ? true : false;
                           
                           if( (continuar_selecciona == true) || (valor_base_booleano == valor_caso_booleano && continuar_selecciona == false))
                           {
                               continuar_selecciona = true;
                               
                               display = lista_casos.get(i).ejecutar(entorno_local, salida);
                               if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .detener || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .retornar)
                               {
                                  return display;
                               }
                           }                           
                        }
                        else if(condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || condicion_base.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
                        {
                           valor_base_double = Double.parseDouble(condicion_base.getValor().toString());
                           valor_caso_double = Double.parseDouble(condicion_caso.getValor().toString());
                           
                           if( (continuar_selecciona == true) || (valor_base_double == valor_caso_double && continuar_selecciona == false) )
                           {
                               continuar_selecciona = true;
                               display = lista_casos.get(i).ejecutar(entorno_local, salida);
                               if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .detener || display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .retornar)
                               {
                                  return display;
                               }
                           } 
                        }                                                                        
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador("33-12");
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("Los valores a comparar no son del mismo tipo");

                        return nuevo_simbolo; 
                    }
                }  
                
                for(int i = 0; i < lista_sentencias_defecto.size(); i++)
                {
                    lista_sentencias_defecto.get(i).ejecutar(entorno_local, salida);
                }  
                FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("33-12");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La expresion de control no puede ser del siguiente tipo: " + condicion_base.getTipo());

                return nuevo_simbolo;                
            }
            
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
