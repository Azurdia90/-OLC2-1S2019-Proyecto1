/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Sentencia_Asignacion implements Instruccion
{
    private int fila;
    private int columna;
    
    private ArrayList<String> lista_identificadores;    
    private Expresion expresion;
    
    public Sentencia_Asignacion(Nodo_AST_FS nodo_sentencia)
    {
        this.crearLista_identificadores(nodo_sentencia.getHijos().get(0), lista_identificadores);
        this.expresion = new Expresion(nodo_sentencia.getHijos().get(1).getHijos().get(0));
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo valor_asignar = expresion.ejecutar(entorno_local, salida);
            Simbolo variable_encontrada = null;
            
            if(valor_asignar.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor_asignar;
            }
            
            for(int i = 0; i < lista_identificadores.size(); i++)
            {
                //REALIZAR LA DECLARACION EN EL ENTORNO LOCAL
                if(entorno_local.Obtener(lista_identificadores.get(i)).getIdentificador().equals("33-12") )
                {
                    variable_encontrada = entorno_local.Obtener(lista_identificadores.get(i));
                    variable_encontrada.setTipo(valor_asignar.getTipo());
                    variable_encontrada.setValor(valor_asignar.getValor());                    
                }
                else
                {
                    return entorno_local.Obtener(lista_identificadores.get(i));
                }  
            }
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
            nuevo_simbolo.setValor("Sentencia Asignacion realizada correctamente");  

            return nuevo_simbolo;
            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Asignacion no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }   
    }
    
    private void crearLista_identificadores(Nodo_AST_FS lista_identificadores, ArrayList<String> identificadores)
    {            
        identificadores = new ArrayList<String>();
        for(int i = 0; i < lista_identificadores.getHijos().size(); i++)
        {
            identificadores.add(lista_identificadores.getHijos().get(i).getValor());
        }
    }
    
}
