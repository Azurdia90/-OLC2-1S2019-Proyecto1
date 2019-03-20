/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Boton;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Texto;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Funcion_Obtener_Por_Etiqueta implements Instruccion
{
    private int fila;
    private int columna;
    
    private String etiqueta;
    
    private FS_Arreglo arreglo;

    public Funcion_Obtener_Por_Etiqueta(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.etiqueta = nodo_funcion.getValor().substring(1,nodo_funcion.getValor().length()-1);;
        this.arreglo = p_arreglo; 
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {        
            FS_Arreglo arreglo_final = new FS_Arreglo();
            Simbolo simbolo_aux;            
            
            if(arreglo.size() > 0)
            {
                if(!(etiqueta.equalsIgnoreCase("ventana") || etiqueta.equalsIgnoreCase("contenedor") || etiqueta.equalsIgnoreCase("texto") || etiqueta.equalsIgnoreCase("control") || etiqueta.equalsIgnoreCase("boton")||etiqueta.equalsIgnoreCase("multimedia")))
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("La llamada a Funcion Obtener Por Etiqueta de Arreglo no puede ejecutar etiqueta \"" + etiqueta + "\" no valida.");
                    
                    return nuevo_simbolo;
                }
                
                for(int i = 0; i < arreglo.size(); i++)
                {
                    if(etiqueta.equalsIgnoreCase("ventana"))
                    {
                        if(arreglo.get(i).getValor() instanceof FS_Ventana)
                        {
                            arreglo_final.add(arreglo.get(i));
                        }
                    }
                    else if(etiqueta.equalsIgnoreCase("contenedor"))
                    {
                        if(arreglo.get(i).getValor() instanceof FS_Contenedor)
                        {
                            arreglo_final.add(arreglo.get(i));
                        }
                    }
                    else if(etiqueta.equalsIgnoreCase("texto"))
                    {
                        if(arreglo.get(i).getValor() instanceof FS_Texto)
                        {
                            arreglo_final.add(arreglo.get(i));
                        }                        
                    }
                    else if(etiqueta.equalsIgnoreCase("control"))
                    {
                        if(arreglo.get(i).getValor() instanceof FS_Caja_Texto || arreglo.get(i).getValor() instanceof FS_Area_Texto || arreglo.get(i).getValor() instanceof FS_Spinner || arreglo.get(i).getValor() instanceof FS_ComboBox )
                        {
                            arreglo_final.add(arreglo.get(i));
                        }
                    }
                    else if(etiqueta.equalsIgnoreCase("boton"))
                    {
                        if(arreglo.get(i).getValor() instanceof FS_Boton)
                        {
                            arreglo_final.add(arreglo.get(i));
                        }                        
                    }
                    else if(etiqueta.equalsIgnoreCase("multimedia"))
                    {
                        //nada por el momento
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("La llamada a Funcion Obtener Por Etiqueta de Arreglo no puede ejecutar etiqueta \"" + etiqueta + "\" no valida.");

                        return nuevo_simbolo;
                    }
                }
                             
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                nuevo_simbolo.setIdentificador("10-4");                        
                nuevo_simbolo.setValor(arreglo_final);

                return nuevo_simbolo;
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La llamada a Funcion Obtener Por Etiqueta de Arreglo no puede ejecutarse en un arreglo vacio.");

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
