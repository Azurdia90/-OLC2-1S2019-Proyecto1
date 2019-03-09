/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_AST.Nodo_AST_FS;
import FS_INSTRUCCIONES.Fabrica_Sentencias;
import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Funcion implements Instruccion
{
    private int fila; 
    private int columna; 
      
    private FS_TABLA_SIMBOLOS.Tabla_Simbolos.tipo_primitivo_Simbolo tipo_retorno;
    private Entorno entorno_local;
    private String identificador;
    //private ArrayList<FS_TABLA_SIMBOLOS.Tabla_Simbolos.tipo_primitivo_Simbolo> lista_tipo_parametros;
    private ArrayList<String> lista_parametros;
    private ArrayList<Instruccion> lista_instrucciones;
    
    private ArrayList<Simbolo> lista_parametros_enviados;
    
    public FS_Funcion(Nodo_AST_FS nodo_funcion)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.identificador = nodo_funcion.getValor();
        this.lista_instrucciones = new ArrayList<Instruccion>();
        this.llenarListaSentencias(nodo_funcion.getHijos().get(0), lista_instrucciones);
        this.lista_parametros = new ArrayList<String>();        
        if(nodo_funcion.getHijos().size() == 2)
        {
            this.crearLista_identificadores(nodo_funcion.getHijos().get(1), lista_parametros);
        }        
        this.lista_parametros_enviados = new ArrayList<Simbolo>();
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        try
        {            
            entorno_local = new Entorno();
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_local);
            
            Simbolo display;
            if(!cargarParametros())
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador(fila + "-" + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La funcion no fue ejecutada, verifique la cantidad de parametros enviados");

                return nuevo_simbolo; 
            }
            
            for(int i = 0; i < lista_instrucciones.size(); i++)
            {
                display = lista_instrucciones.get(i).ejecutar(entorno_local, salida);
                if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo .retornar )
                {
                    FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
                    
                    //if(display.getTipo().equals(tipo_retorno))
                    //{
                        return display;
                    //}
                    //else
                    /*{
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("El tipo del valor retornado no es el mismo del tipo."); 
                        return nuevo_simbolo;
                    }*/                                        
                }
                else if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.detener)
                {
                    FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(display.getIdentificador());
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Una funcion no debe tener sentencias Detener.");
                    
                    return nuevo_simbolo;                            
                }
                else if(display.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
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
            nuevo_simbolo.setValor("Funcion realizada correctamente");  

            return nuevo_simbolo;                        
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("La funcion no fue ejecutada, error: " + e.getMessage());

            return nuevo_simbolo;     
        }
    }
                      
    private void crearLista_identificadores(Nodo_AST_FS lista_identificadores, ArrayList<String> identificadores)
    {            
        for(int i = 0; i < lista_identificadores.getHijos().size(); i++)
        {
            identificadores.add(lista_identificadores.getHijos().get(i).getValor());
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
    
    public boolean cargarParametros()
    {
        if(lista_parametros.size() == lista_parametros_enviados.size())
        {
            Simbolo simbolo_nuevo;
            Simbolo simbolo_enviado;
            
            /*for(int i = 0; i< lista_parametros_enviados.size(); i++)
            {
                if(!lista_parametros_enviados.get(i).getTipo().equals(lista_tipo_parametros.get(i)))
                {
                    return false;
                }
            }*/
            
            for(int i = 0; i < lista_parametros.size(); i++)
            {
                try
                {
                    simbolo_enviado = lista_parametros_enviados.get(i);
                    
                    simbolo_nuevo = new Simbolo();
                    simbolo_nuevo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                    simbolo_nuevo.setAcceso(Tabla_Enums.tipo_Acceso.publico);                    
                    simbolo_nuevo.setTipo(simbolo_enviado.getTipo());
                    simbolo_nuevo.setIdentificador(lista_parametros.get(i));
                    simbolo_nuevo.setValor(simbolo_enviado.getValor());
                    entorno_local.Crear(lista_parametros.get(i), simbolo_nuevo);                
                }
                catch(Exception e)
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Simbolo> getLista_parametros_enviados() {
        return lista_parametros_enviados;
    }

    public void setLista_parametros_enviados(ArrayList<Simbolo> lista_parametros_enviados) {
        this.lista_parametros_enviados = lista_parametros_enviados;
    }

    public ArrayList<String> getLista_parametros() {
        return lista_parametros;
    }

    public void setLista_parametros(ArrayList<String> lista_parametros) {
        this.lista_parametros = lista_parametros;
    }
        
}
