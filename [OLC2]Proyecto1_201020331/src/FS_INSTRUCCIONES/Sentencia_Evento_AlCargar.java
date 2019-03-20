/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Evento_AlCargar implements Instruccion
{
    private int fila;
    private int columna;
    private String identificador;
    
    private Sentencia_LLamada llamada_alCargar;
    
    public Sentencia_Evento_AlCargar(Nodo_AST_FS nodo_sentencia)            
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        this.identificador = nodo_sentencia.getValor();
        
        if(nodo_sentencia.getHijos().size() > 0)
        {
            this.llamada_alCargar = new Sentencia_LLamada(nodo_sentencia.getHijos().get(0));
        }
        else
        {
            this.llamada_alCargar = null;
        }        
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo ventana;
            ventana = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local,identificador);
            
            if(ventana.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return ventana;
            }
            else
            {
                if(ventana.getRol() != Tabla_Enums.tipo_Simbolo.objeto)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Ventana.");    
                    return nuevo_simbolo;
                }
                else
                {
                    if(ventana.getValor() instanceof FS_Ventana)
                    {
                        FS_Ventana ventana_cargar = (FS_Ventana) ventana.getValor();
                        
                        if(llamada_alCargar != null)
                        {
                            llamada_alCargar.ejecutar(entorno_local, salida);
                        }
                        
                        ventana_cargar.show();
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Ventana.");    
                        return nuevo_simbolo;                        
                    }
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor("Carga de ventana realizada correctamente");  

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
                nuevo_simbolo.setValor("La Creación de Ventana no fue realizada, debe ingresar un color en hexadecimal.");
            }
            else
            {
                nuevo_simbolo.setValor("La Carga de la Ventana no fue realizada, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }    
}
