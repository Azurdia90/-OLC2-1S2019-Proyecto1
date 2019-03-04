/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
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
public class Sentencia_Crear_Ventana implements Instruccion
{    
    private int fila;
    private int columna;
            
    private String cadena_color;
    
    private Expresion identificador;
    private Expresion valorx;
    private Expresion valory;
    
    public Sentencia_Crear_Ventana(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());                        
        this.cadena_color = nodo_sentencia.getValor().substring(1, nodo_sentencia.getValor().length()-1);
        this.identificador = new Expresion(nodo_sentencia.getHijos().get(0));
        this.valorx = new Expresion(nodo_sentencia.getHijos().get(1)); 
        this.valory = new Expresion(nodo_sentencia.getHijos().get(2));
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Color color = Color.decode(cadena_color);
                        
            Simbolo identificador_f = identificador.ejecutar(entorno_local, salida);
            Simbolo valor_x = valorx.ejecutar(entorno_local, salida);
            Simbolo valor_y = valory.ejecutar(entorno_local, salida);
            
            if(identificador_f.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.cadena)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Ventana no fue realizada, el identificador debe ser una cadena.");

                return nuevo_simbolo;
            }
            
            if(valor_x.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Ventana no fue realizada, el parametro de alto debe ser numerico.");

                return nuevo_simbolo;
            }
            
            if(valor_y.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.entero)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La Creación de Ventana no fue realizada, el parametro de ancho debe ser numerico");

                return nuevo_simbolo;
            }
            
            FS_Ventana nueva_ventana = new FS_Ventana(identificador_f.getValor().toString(), Integer.parseInt(valor_x.getValor().toString()), Integer.parseInt(valor_y.getValor().toString()), color);
               
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
            nuevo_simbolo.setValor(nueva_ventana);  

            return nuevo_simbolo;
            
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
                nuevo_simbolo.setValor("La Creación de Ventana no fue realizada, error: " + e.getMessage());
            }

            return nuevo_simbolo;
        }
    }
    
}
