/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Funcion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_LLamada implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    private ArrayList<Expresion> lista_expresiones;
    private ArrayList<Simbolo> lista_parametros;
    
    public Sentencia_LLamada(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        this.lista_expresiones = new ArrayList<Expresion>();
        this.lista_parametros = new ArrayList<Simbolo>();
        
        if(nodo_sentencia.getHijos().size() == 1)
        {
            this.crearLista_identificadores(nodo_sentencia.getHijos().get(0), lista_expresiones);
        }        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_padre, ObjetoEntrada salida) 
    {
        try
        {
            if(FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().existe_metodo(identificador))
            {
                
                FS_Funcion funcion_invocada = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Metodo(identificador);
                crear_Valores_Enviar(entorno_padre, salida, lista_parametros);
                funcion_invocada.setLista_parametros_enviados(lista_parametros);
                funcion_invocada.cargarParametros();
                Simbolo simbolo_retorno = funcion_invocada.ejecutar(entorno_padre, salida);
                return simbolo_retorno;
                
                /*Simbolo nuevo_simbolo = new Simbolo();            
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                nuevo_simbolo.setValor("Sentencia Llamada realizada correctamente");  

                return nuevo_simbolo;*/
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La funcion " + identificador + " no existe.");
                    
                return nuevo_simbolo; 
            }            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("La funcion no fue ejecutada, error: " + e.getMessage());

            return nuevo_simbolo; 
        }
    }
    
    private void crearLista_identificadores(Nodo_AST_FS lista_nodos_expresion, ArrayList<Expresion> lista_expresiones)
    {           
        Expresion expresion_aux = null;
        
        for(int i = 0; i < lista_nodos_expresion.getHijos().size(); i++)
        {
            expresion_aux = new Expresion(lista_nodos_expresion.getHijos().get(i).getHijos().get(0));
            this.lista_expresiones.add(expresion_aux);
        }
    }    
    
    private void crear_Valores_Enviar(Entorno entorno_local, ObjetoEntrada salida, ArrayList<Simbolo> lista_parametros)
    {
        for(int i = 0; i < lista_expresiones.size(); i++)
        {
            lista_parametros.add(lista_expresiones.get(i).ejecutar(entorno_local, salida));
        }
        
    }
}
