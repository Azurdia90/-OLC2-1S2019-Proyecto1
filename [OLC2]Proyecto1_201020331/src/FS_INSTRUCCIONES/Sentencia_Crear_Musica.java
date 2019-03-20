/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Imagen;
import FS_OBJETOS.FS_Musica;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Crear_Musica implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    
    private String nombre;
    
    private Expresion path;
    private Expresion posx;   
    private Expresion posy;
    private Expresion autoreproduccion;
    private Expresion alto;
    private Expresion ancho;
    
    public Sentencia_Crear_Musica(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        this.path =  new Expresion(nodo_sentencia.getHijos().get(0));
        this.posx = new Expresion(nodo_sentencia.getHijos().get(1));
        this.posy = new Expresion(nodo_sentencia.getHijos().get(2));
        this.autoreproduccion = new Expresion(nodo_sentencia.getHijos().get(3));
        this.alto = new Expresion(nodo_sentencia.getHijos().get(4));
        this.ancho = new Expresion(nodo_sentencia.getHijos().get(5));        
    }
    
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo contenedor;
                        
            Simbolo path_r  =  path.ejecutar(entorno_local, salida);
            Simbolo posx_r  =  posx.ejecutar(entorno_local, salida);
            Simbolo posy_r  =  posy.ejecutar(entorno_local, salida);            
            Simbolo alto_r  =  alto.ejecutar(entorno_local, salida);
            Simbolo ancho_r = ancho.ejecutar(entorno_local, salida);
            Simbolo autoreproduccion_r  =  autoreproduccion.ejecutar(entorno_local, salida);
            
            if(path_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Musica no fue realizada, el path debe ser una cadena.");

                return nuevo_simbolo;
            }
            
            if(!(posx_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || posx_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Musica no fue realizada, la posicion en x debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(posy_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || posy_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación Musica no fue realizada, la posicion en y debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || alto_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación Musica no fue realizada, el alto debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(!(ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || ancho_r.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Musica no fue realizada, el ancho debe ser un valor numerico.");

                return nuevo_simbolo;
            }
            
            if(autoreproduccion_r.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Musica no fue realizada, el parametro autoreproduccion debe ser tipo booleano.");

                return nuevo_simbolo;
            }
            
            FS_Musica nueva_musica = new FS_Musica(path_r.getValor().toString(), Integer.parseInt(posx_r.getValor().toString()), Integer.parseInt(posy_r.getValor().toString()), autoreproduccion_r.getValor().toString().equals("verdadero") ? true : false, Integer.parseInt(alto_r.getValor().toString()), Integer.parseInt(ancho_r.getValor().toString()), salida);
                        
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
                        ventana_modificar.getLista_componentes().add(nueva_musica);
                        ventana_modificar.add(nueva_musica);
                        ventana_modificar.repaint();
                    }
                    else if(contenedor.getValor() instanceof FS_Contenedor)
                    {
                        FS_Contenedor contenedor_modificar = (FS_Contenedor) contenedor.getValor();
                        contenedor_modificar.getLista_componentes().add(nueva_musica);
                        contenedor_modificar.add(nueva_musica);
                        contenedor_modificar.repaint();                    
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("Este evento es compatible únicamente con Objetos Ventana o Contenedor.");    
                        return nuevo_simbolo;
                    }
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor("La musica fue agregada correctamente");  

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
            nuevo_simbolo.setValor("La Creación de la Musica no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }
}
