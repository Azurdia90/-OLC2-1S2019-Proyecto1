/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.awt.Color;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Crear_Contenedor implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    private String cadena_color;
    
    private Expresion alto;
    private Expresion ancho;
    private Expresion borde;
    private Expresion pos_x;
    private Expresion pos_y;
    
    public Sentencia_Crear_Contenedor(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna =Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        this.cadena_color = nodo_sentencia.getHijos().get(1).getValor();                
        this.alto =  new Expresion(nodo_sentencia.getHijos().get(1));
        this.ancho = new Expresion(nodo_sentencia.getHijos().get(2));
        this.borde = new Expresion(nodo_sentencia.getHijos().get(3));
        this.pos_x = new Expresion(nodo_sentencia.getHijos().get(4));
        this.pos_y = new Expresion(nodo_sentencia.getHijos().get(5));
        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo ventana;
            
            Color color = Color.decode(cadena_color);
                        
            Simbolo alto_r = alto.ejecutar(entorno_local, salida);
            Simbolo ancho_r = ancho.ejecutar(entorno_local, salida);
            Simbolo borde_r = borde.ejecutar(entorno_local, salida);
            Simbolo pos_x_r = pos_x.ejecutar(entorno_local, salida);
            Simbolo pos_y_r = pos_y.ejecutar(entorno_local, salida);
            
            if(alto_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero || alto_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizada, la altura debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(ancho_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero || ancho_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizada, el ancho debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(borde_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizada, el borde debe ser un valor booleano.");

                return nuevo_simbolo;
            }
            
            if(pos_x_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_x_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(pos_y_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_y_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            
            FS_Contenedor nuevo_contenedor = new FS_Contenedor(Integer.parseInt(alto_r.getValor().toString()), Integer.parseInt(ancho_r.getValor().toString()), color, borde_r.getValor().toString().equals("verdadero") ? true : false, Integer.parseInt(pos_x_r.getValor().toString()), Integer.parseInt(pos_y_r.getValor().toString()));
            
            
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
                    FS_Ventana ventana_modificar = (FS_Ventana) ventana.getValor();
                    ventana_modificar.add(nuevo_contenedor);
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setValor(nuevo_contenedor);  

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
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizado, debe ingresar un color en hexadecimal.");
            }
            else
            {
                nuevo_simbolo.setValor("La Creación del Contenedor no fue realizado, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }
    
    
}
