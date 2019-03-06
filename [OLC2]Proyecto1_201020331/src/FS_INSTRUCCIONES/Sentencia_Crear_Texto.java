/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
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
public class Sentencia_Crear_Texto implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    private String cadena_color;
    
    private Expresion fuente;
    private Expresion tamaño;   
    private Expresion pos_x;
    private Expresion pos_y;
    private Expresion bold;
    private Expresion italic;
    private Expresion valor_pre;
    
    public Sentencia_Crear_Texto(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        this.cadena_color = nodo_sentencia.getHijos().get(0).getValor().substring(1,nodo_sentencia.getHijos().get(0).getValor().length()-1);
        this.fuente =  new Expresion(nodo_sentencia.getHijos().get(1));
        this.tamaño = new Expresion(nodo_sentencia.getHijos().get(2));
        this.pos_x = new Expresion(nodo_sentencia.getHijos().get(3));
        this.pos_y = new Expresion(nodo_sentencia.getHijos().get(4));
        this.bold = new Expresion(nodo_sentencia.getHijos().get(5));
        this.italic = new Expresion(nodo_sentencia.getHijos().get(6));
        this.valor_pre = new Expresion(nodo_sentencia.getHijos().get(7));        
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
            Simbolo bold_r = bold.ejecutar(entorno_local, salida);
            Simbolo italic_r = italic.ejecutar(entorno_local, salida);
            Simbolo valor_pre_r = valor_pre.ejecutar(entorno_local, salida);
            
            if(fuente_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, el tipo de fuente debe ser una cadena.");

                return nuevo_simbolo;
            }
            
            if(!(tamaño_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || tamaño_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, el tamaño de la fuente debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(bold_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, el parametro negrita debe ser tipo booleano.");

                return nuevo_simbolo;
            }
            
            if(italic_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, el parametro cursiva debe ser tipo booleano.");

                return nuevo_simbolo;
            }
            
            if(valor_pre_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, el valor predeterminado debe ser tipo cadena.");

                return nuevo_simbolo;
            }
            
            FS_Texto nuevo_texto = new FS_Texto(fuente_r.getValor().toString(), Integer.parseInt(tamaño_r.getValor().toString()), color, Integer.parseInt(pos_x_r.getValor().toString()), Integer.parseInt(pos_y_r.getValor().toString()), bold_r.getValor().toString().equals("verdadero") ? true : false, italic_r.getValor().toString().equals("verdadero") ? true : false, valor_pre_r.getValor().toString());
            
            
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
                    FS_Contenedor contenedor_modificar = (FS_Contenedor) contenedor.getValor();
                    contenedor_modificar.add(nuevo_texto);
                    contenedor_modificar.repaint();
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor("El texto fue agregado correctamente");  

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
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, debe ingresar un color en hexadecimal.");
            }
            else
            {
                nuevo_simbolo.setValor("La Creación del Texto no fue realizada, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }   
}
