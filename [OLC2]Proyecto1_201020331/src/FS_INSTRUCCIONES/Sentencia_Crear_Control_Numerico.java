/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Spinner;
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
public class Sentencia_Crear_Control_Numerico implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    
    private Expresion alto;
    private Expresion ancho;
    private Expresion maximo;
    private Expresion minimo;
    private Expresion pos_x;
    private Expresion pos_y;
    private Expresion defecto;
    private Expresion nombre;
    
    public Sentencia_Crear_Control_Numerico(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna =Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
             
        this.alto =  new Expresion(nodo_sentencia.getHijos().get(0));
        this.ancho = new Expresion(nodo_sentencia.getHijos().get(1));        
        this.maximo =  new Expresion(nodo_sentencia.getHijos().get(2));
        this.minimo = new Expresion(nodo_sentencia.getHijos().get(3));
        this.pos_x = new Expresion(nodo_sentencia.getHijos().get(4));
        this.pos_y = new Expresion(nodo_sentencia.getHijos().get(5));        
        this.defecto = new Expresion(nodo_sentencia.getHijos().get(6)); 
        this.nombre = new Expresion(nodo_sentencia.getHijos().get(7)); 
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo ventana;
                        
            Simbolo alto_r = alto.ejecutar(entorno_local, salida);
            Simbolo ancho_r = ancho.ejecutar(entorno_local, salida);
            Simbolo maximo_r = maximo.ejecutar(entorno_local, salida);
            Simbolo minimo_r = minimo.ejecutar(entorno_local, salida);
            Simbolo pos_x_r = pos_x.ejecutar(entorno_local, salida);
            Simbolo pos_y_r = pos_y.ejecutar(entorno_local, salida);
            Simbolo defecto_r = defecto.ejecutar(entorno_local, salida);
            Simbolo nombre_r = nombre.ejecutar(entorno_local, salida);
            
            if(!(alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, la altura debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el ancho debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(maximo_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || maximo_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el maximo debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(minimo_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || minimo_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el minimo debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_x_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || pos_y_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }
                        
            if(!(defecto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || defecto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el valor por defecto debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(defecto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || defecto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el valor por defecto debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(nombre_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.cadena))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizada, el nombre debe ser una cadena.");

                return nuevo_simbolo;
            }
            
            FS_Spinner nuevo_spinner = new FS_Spinner(Integer.parseInt(alto_r.getValor().toString()), Integer.parseInt(ancho_r.getValor().toString()), Integer.parseInt(minimo_r.getValor().toString()), Integer.parseInt(maximo_r.getValor().toString()), Integer.parseInt(pos_x_r.getValor().toString()), Integer.parseInt(pos_y_r.getValor().toString()), Integer.parseInt(defecto_r.getValor().toString()), nombre_r.getValor().toString());
                        
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
                        FS_Ventana ventana_modificar = (FS_Ventana) ventana.getValor();
                        ventana_modificar.getLista_componentes().add(nuevo_spinner);
                        ventana_modificar.add(nuevo_spinner);
                        ventana_modificar.repaint();
                    }
                    else if(ventana.getValor() instanceof FS_Contenedor)
                    {
                        FS_Contenedor contenedor_modificar = (FS_Contenedor) ventana.getValor();
                        contenedor_modificar.getLista_componentes().add(nuevo_spinner);
                        contenedor_modificar.add(nuevo_spinner);
                        contenedor_modificar.repaint();
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
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setValor(nuevo_spinner);  

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
                nuevo_simbolo.setValor("La Creación del Control Numerico no fue realizado, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }
}
