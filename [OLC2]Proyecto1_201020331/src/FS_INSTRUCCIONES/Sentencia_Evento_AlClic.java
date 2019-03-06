/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Boton;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Evento_AlClic implements Instruccion
{
    private int fila;
    private int columna;
    private String identificador;
    private Sentencia_LLamada sentencia_llamada;
    
    public Sentencia_Evento_AlClic(Nodo_AST_FS nodo_sentencia)            
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        this.identificador = nodo_sentencia.getValor();
        this.sentencia_llamada = new Sentencia_LLamada(nodo_sentencia.getHijos().get(0));
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo boton;
            boton = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local,identificador);
            
            if(boton.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return boton;
            }
            else
            {
                if(boton.getRol() != Tabla_Enums.tipo_Simbolo.objeto)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Botón.");    
                    return nuevo_simbolo;
                }
                else
                {
                    FS_Boton boton_cargar = (FS_Boton) boton.getValor();
                    boton_cargar.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) 
                                {
                                    sentencia_llamada.ejecutar(entorno_local, salida);  		
                                }	
                                });
                                      
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor("Carga del evento fue realizada correctamente");  

                    return nuevo_simbolo;
                }                        
            }                                    
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            if(e.getMessage().substring(0,17).equals("For input string:"))
            {
                nuevo_simbolo.setValor("El Evento del Botón no fue realizado, debe ingresar un color en hexadecimal.");
            }
            else
            {
                nuevo_simbolo.setValor("El Evento del Botón no fue realizado, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }
}
