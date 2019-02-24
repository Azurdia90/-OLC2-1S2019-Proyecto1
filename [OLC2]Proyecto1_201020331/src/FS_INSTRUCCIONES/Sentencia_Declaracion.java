/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import UI.ObjetoEntrada;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Sentencia_Declaracion implements Instruccion
{
    private int fila;
    private int columna;
    
    private ArrayList<String> identificadores;
    private Expresion expresion;
    
    private Nodo_AST_FS lista_identificadores;
    private Nodo_AST_FS nodo_expresion;
    
    public Sentencia_Declaracion(Nodo_AST_FS nodo_sentencia)            
    {
        if(nodo_sentencia.getHijos().size() == 1)
        {
            this.lista_identificadores = nodo_sentencia.getHijos().get(0);
            this.crearLista_identificadores();
            this.expresion = null;
        }
        else if(nodo_sentencia.getHijos().size() == 2)
        {
            this.lista_identificadores = nodo_sentencia.getHijos().get(0);
            this.crearLista_identificadores();
            this.expresion = new Expresion(nodo_sentencia.getHijos().get(1).getHijos().get(0));
        }        
    }   
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        if(identificadores.size() > 0)
        {
            for(int i = 0; i < identificadores.size(); i++)
            {
                try
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                    nuevo_simbolo.setIdentificador(identificadores.get(i));    
                    //SI LA DECLARACION INCLUYE ASIGNACION DE UN VALOR
                    if(expresion != null)
                    {
                        Simbolo simbolo_expresion = expresion.ejecutar(entorno_local, salida);
                        nuevo_simbolo.setTipo(simbolo_expresion.getTipo());
                        nuevo_simbolo.setValor(simbolo_expresion.getValor());
                    }           
                    //REALIZAR LA DECLARACION EN EL ENTORNO LOCAL
                    if(entorno_local.Obtener(identificadores.get(i)).getIdentificador().equals("33-12"))
                    {
                        return entorno_local.Crear(identificadores.get(i), nuevo_simbolo);
                    }
                    else
                    {
                        return entorno_local.Crear(identificadores.get(i), nuevo_simbolo);
                    }                                   
                }
                catch(Exception e)
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Sentencia Declaracion no fue realizada, error: " + e.getMessage());

                    return nuevo_simbolo;
                } 
            }
        }
        else
        {
            return null;
        }
        return null;
    }

    private void crearLista_identificadores()
    {            
        identificadores = new ArrayList<String>();
        for(int i = 0; i < lista_identificadores.getHijos().size(); i++)
        {
            identificadores.add(lista_identificadores.getHijos().get(i).getValor());
        }
    }
            
}
