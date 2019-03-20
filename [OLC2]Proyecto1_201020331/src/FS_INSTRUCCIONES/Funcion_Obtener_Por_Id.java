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
public class Funcion_Obtener_Por_Id implements Instruccion
{
    private int fila;
    private int columna;
    
    private String id;
    
    private FS_Arreglo arreglo;
    
    public Funcion_Obtener_Por_Id(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.id = nodo_funcion.getValor().substring(1,nodo_funcion.getValor().length()-1);;
        this.arreglo = p_arreglo; 
    }
            
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {                             
            if(arreglo.size() > 0)
            {                               
                for(int i = 0; i < arreglo.size(); i++)
                {
                    if( (arreglo.get(i).getIdentificador().equalsIgnoreCase(id) && arreglo.get(i).getValor() instanceof FS_Ventana) || (arreglo.get(i).getIdentificador().equalsIgnoreCase(id) && arreglo.get(i).getValor() instanceof FS_Contenedor) )
                    {
                        return arreglo.get(i);
                    }                    
                }
                
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                nuevo_simbolo.setIdentificador("10-4");                        
                nuevo_simbolo.setValor("nulo");

                return nuevo_simbolo;                                             
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La llamada a Funcion Obtener Por Id de Arreglo no puede ejecutarse en un arreglo vacio.");

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
