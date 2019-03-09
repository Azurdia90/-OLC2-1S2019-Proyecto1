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

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Arreglo extends ArrayList<Simbolo> implements Instruccion
{
    private int fila;
    private int columna;
    
    private ArrayList<Expresion> lista_expresiones;
       
    private boolean homogeneo;
    private Tabla_Enums.tipo_primitivo_Simbolo tipo;
    
    public FS_Arreglo(Nodo_AST_FS nodo_arreglo)
    {
        this.fila = Integer.parseInt(nodo_arreglo.getFila());
        this.columna = Integer.parseInt(nodo_arreglo.getColumna());
        this.clear();
        this.homogeneo = true;
        this.lista_expresiones = new ArrayList<Expresion>();
        llenar_lista_expresiones(nodo_arreglo.getHijos().get(0),lista_expresiones);
    }
    
    public FS_Arreglo()
    {
        this.fila = 33;
        this.columna = 12;
        this.clear();
        this.homogeneo = true;
        this.lista_expresiones = new ArrayList<Expresion>();
    }
    
    private void llenar_lista_expresiones(Nodo_AST_FS lista_nodos_expresiones, ArrayList<Expresion> lista_expresiones)
    {
        Expresion expresion_aux;
        for(int i  = 0; i < lista_nodos_expresiones.getHijos().size(); i++)
        {
            expresion_aux = new Expresion(lista_nodos_expresiones.getHijos().get(i).getHijos().get(0));
            lista_expresiones.add(expresion_aux);
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            this.clear();
            this.homogeneo = true;
            
            Simbolo simbolo_aux;            
            Tabla_Enums.tipo_primitivo_Simbolo ultimo_simbolo = Tabla_Enums.tipo_primitivo_Simbolo.nulo;
            
            for(int i = 0; i < lista_expresiones.size(); i++)
            {
                simbolo_aux = lista_expresiones.get(i).ejecutar(entorno_local, salida);
                                
                if(simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.identificador || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.detener || simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.retornar)
                {
                    if((ultimo_simbolo != simbolo_aux.getTipo()) && i > 0)
                    {
                        this.homogeneo = false;
                        this.tipo = Tabla_Enums.tipo_primitivo_Simbolo.identificador;
                    }
                    this.add(simbolo_aux);
                    this.tipo = simbolo_aux.getTipo();
                }
                else if(simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.identificador)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                    nuevo_simbolo.setValor("Arreglo no puede ser Asignado, error: No se permite Almacenar Objetos en un Arreglo");

                    return nuevo_simbolo;
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.detener)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Arreglo no puede ser Asignado, error: No se permite Almacenar Sentencia Detener en un Arreglo");
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.retornar)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Arreglo no puede ser Asignado, error: No se permite Almacenar Sentencia Retornar en un Arreglo");
                }
                else if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return simbolo_aux;
                }
                
                ultimo_simbolo = simbolo_aux.getTipo();
            }
                        
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setValor("El arreglo si puede ser asignado.");

            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Arreglo no puede ser Asignado, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }  

    public boolean getHomogeneo() {
        return homogeneo;
    }

    public void setHomogeneo(boolean homogeneo) {
        this.homogeneo = homogeneo;
    }

    public Tabla_Enums.tipo_primitivo_Simbolo getTipo() {
        return tipo;
    }

    public void setTipo(Tabla_Enums.tipo_primitivo_Simbolo tipo) {
        this.tipo = tipo;
    }        
}
