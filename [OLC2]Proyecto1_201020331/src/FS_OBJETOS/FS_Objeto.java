/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Objeto extends HashMap<String,Simbolo> implements Instruccion
{
    private int fila;
    private int columna;
    
    private ArrayList<String>    lista_identificadores;
    private ArrayList<Expresion> lista_expresiones;
    
    public FS_Objeto(Nodo_AST_FS nodo_objeto)
    {
        this.fila = Integer.parseInt(nodo_objeto.getFila());
        this.columna = Integer.parseInt(nodo_objeto.getColumna());
        this.clear();
        this.lista_identificadores = new ArrayList<String>();
        this.lista_expresiones = new ArrayList<Expresion>();
        llenar_lista_atributos(nodo_objeto.getHijos().get(0),lista_identificadores,lista_expresiones);        
    }
    
    private void llenar_lista_atributos(Nodo_AST_FS lista_nodos_atributos, ArrayList<String> lista_identificadores, ArrayList<Expresion> lista_expresion)
    {
        Nodo_AST_FS nodo_atributo;
        
        for(int i = 0; i < lista_nodos_atributos.getHijos().size(); i++)
        {
            nodo_atributo = lista_nodos_atributos.getHijos().get(i);
            lista_identificadores.add(nodo_atributo.getHijos().get(0).getValor());
            lista_expresion.add(new Expresion(nodo_atributo.getHijos().get(1)));
        }
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            this.clear();

            Simbolo simbolo_aux;            
            
            for(int i = 0; i < lista_expresiones.size(); i++)
            {
                simbolo_aux = lista_expresiones.get(i).ejecutar(entorno_local, salida);
                                
                if(simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.identificador || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.detener || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.retornar)
                {
                    this.put(lista_identificadores.get(i),simbolo_aux);
                }
                else if(simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.identificador)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Objeto no puede ser Asignado, error: No se permite Almacenar Objeto dentro de otro Objeto");

                    return nuevo_simbolo;
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.detener)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Objeto no puede ser Asignado, error: No se permite Almacenar Sentencia Detener en un Objeto");
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.retornar)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Objeto no puede ser Asignado, error: No se permite Almacenar Sentencia Retornar en un Objeto");
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return simbolo_aux;
                }               
            }
                        
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setValor("El Objeto si puede ser asignado.");

            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Objeto no puede ser Asignado, error: " + e.getMessage());

            return nuevo_simbolo;
        }
       
    }                    
}
