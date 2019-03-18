/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Boton;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Texto;
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
public class Sentencia_Crear_Boton implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    private String cadena_color;
    
    private Expresion fuente;
    private Expresion tamaño;   
    private Expresion pos_x;
    private Expresion pos_y;
    private Sentencia_LLamada referencia;
    private Expresion texto;
    private Expresion alto;
    private Expresion ancho;
    
    private Nodo_AST_FS nodo_referencia;
    
    public Sentencia_Crear_Boton(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        if(nodo_sentencia.getHijos().size() == 9)
        {
            this.cadena_color = nodo_sentencia.getHijos().get(0).getValor().substring(1,nodo_sentencia.getHijos().get(0).getValor().length()-1);
            this.fuente =  new Expresion(nodo_sentencia.getHijos().get(1));
            this.tamaño = new Expresion(nodo_sentencia.getHijos().get(2));
            this.pos_x = new Expresion(nodo_sentencia.getHijos().get(3));
            this.pos_y = new Expresion(nodo_sentencia.getHijos().get(4));
            this.nodo_referencia = nodo_sentencia.getHijos().get(5);
            this.texto = new Expresion(nodo_sentencia.getHijos().get(6));
            this.alto = new Expresion(nodo_sentencia.getHijos().get(7));
            this.ancho = new Expresion(nodo_sentencia.getHijos().get(8));        
        }
        else if(nodo_sentencia.getHijos().size() == 8)
        {
            this.cadena_color = nodo_sentencia.getHijos().get(0).getValor().substring(1,nodo_sentencia.getHijos().get(0).getValor().length()-1);
            this.fuente =  new Expresion(nodo_sentencia.getHijos().get(1));
            this.tamaño = new Expresion(nodo_sentencia.getHijos().get(2));
            this.pos_x = new Expresion(nodo_sentencia.getHijos().get(3));
            this.pos_y = new Expresion(nodo_sentencia.getHijos().get(4));
            this.nodo_referencia = null;
            this.texto = new Expresion(nodo_sentencia.getHijos().get(5));
            this.alto = new Expresion(nodo_sentencia.getHijos().get(6));
            this.ancho = new Expresion(nodo_sentencia.getHijos().get(7));        
        }
        
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo contenedor;
            
            Color color = Color.decode(cadena_color);
                        
            Simbolo fuente_r = fuente.ejecutar(entorno_local, salida);
            Simbolo tamaño_r = tamaño.ejecutar(entorno_local, salida);
            Simbolo pos_x_r = pos_x.ejecutar(entorno_local, salida);
            Simbolo pos_y_r = pos_y.ejecutar(entorno_local, salida);
            
            if(nodo_referencia != null )
            {
                if(nodo_referencia.IsNodoOrNot("SENTENCIA_LLAMADA"))
                {
                    this.referencia = new Sentencia_LLamada(nodo_referencia);
                }
                else
                {   
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("La Creación del Botón no fue realizada, la referencia debe ser una llamada a un metodo.");

                    return nuevo_simbolo;
                }
            }
            else
            {
                this.referencia = null;
            }
            Simbolo texto_r = texto.ejecutar(entorno_local, salida);
            Simbolo alto_r = alto.ejecutar(entorno_local, salida);
            Simbolo ancho_r = ancho.ejecutar(entorno_local, salida);
            
            if(fuente_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, el tipo de fuente debe ser una cadena.");

                return nuevo_simbolo;
            }
            
            if(!(tamaño_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || tamaño_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, el tamaño de la fuente debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }            
            
            if(texto_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, el texto debe ser tipo cadena.");

                return nuevo_simbolo;
            }
            
           if(!(alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            FS_Boton nuevo_boton = new FS_Boton(fuente_r.getValor().toString(), Integer.parseInt(tamaño_r.getValor().toString()), color, Integer.parseInt(pos_x_r.getValor().toString()), Integer.parseInt(pos_y_r.getValor().toString()), referencia, texto_r.getValor().toString(), Integer.parseInt(alto_r.getValor().toString()), Integer.parseInt(ancho_r.getValor().toString()),entorno_local,salida);
                        
            contenedor = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local,identificador);
            
            if(contenedor.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return contenedor;
            }
            else
            {
                if(contenedor.getRol() != Tabla_Enums.tipo_Simbolo.objeto)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Contenedor.");    
                    return nuevo_simbolo;
                }
                else
                {
                    if(contenedor.getValor() instanceof FS_Ventana)
                    {
                        FS_Ventana ventana_modificar = (FS_Ventana) contenedor.getValor();
                        ventana_modificar.add(nuevo_boton);
                        ventana_modificar.repaint();
                    }
                    else if(contenedor.getValor() instanceof FS_Contenedor)
                    {
                        FS_Contenedor contenedor_modificar = (FS_Contenedor) contenedor.getValor();
                        contenedor_modificar.add(nuevo_boton);
                        contenedor_modificar.repaint();
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Contenedor.");    
                        return nuevo_simbolo;
                    }
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setValor(nuevo_boton);  

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
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, debe ingresar un color en hexadecimal.");
            }
            else
            {
                nuevo_simbolo.setValor("La Creación del Botón no fue realizada, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }
}
