/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_INSTRUCCIONES.Fabrica_Funciones_Arreglo;
import FS_INSTRUCCIONES.Instruccion;
import FS_OBJETOS.FS_Arreglo;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_TABLA_SIMBOLOS.Tabla_Simbolos;
import UI.ObjetoEntrada;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_LLamada_Funciones_Arreglo implements Instruccion 
{
    private int fila;
    private int columna;
    private String identificador;
    private ArrayList<Fabrica_Funciones_Arreglo> lista_llamadas;
    
    public Sentencia_LLamada_Funciones_Arreglo(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        this.lista_llamadas = new ArrayList<Fabrica_Funciones_Arreglo>();
        this.llenar_lista_llamadas(nodo_sentencia.getHijos().get(0),lista_llamadas);
    }
    
    private void llenar_lista_llamadas(Nodo_AST_FS lista_nodo_instrucciones, ArrayList<Fabrica_Funciones_Arreglo> lista_llamadas)
    {
        Instruccion instruccion_aux;
        Fabrica_Funciones_Arreglo funcion_resultado;
        for(int i = 0; i < lista_nodo_instrucciones.getHijos().size(); i++)
        {
            lista_llamadas.add(new Fabrica_Funciones_Arreglo(lista_nodo_instrucciones.getHijos().get(i)));
        }
    }
        
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo simbolo_aux;
            FS_Arreglo arreglo_aux;
            Simbolo simbolo_resultado = null;
            
            
            simbolo_aux = Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local, identificador);
            
            if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return simbolo_aux;
            }
            else if(simbolo_aux.getRol() == Tabla_Enums.tipo_Simbolo.arreglo)
            {
                arreglo_aux = (FS_Arreglo) simbolo_aux.getValor();
               
                for(int i = 0; i < lista_llamadas.size(); i++)
                {
                    simbolo_resultado = this.lista_llamadas.get(i).ejecutar(entorno_local, salida, arreglo_aux);
                    if(simbolo_resultado.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        return simbolo_resultado;
                    }                    
                }

                return simbolo_resultado;
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La funcion a llamar es exclusiva de un arreglo, verifique el tipo del parametro a llamar.");

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
            nuevo_simbolo.setValor("La llamada a funciones de un Arreglo no fue realizada, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }    
}
