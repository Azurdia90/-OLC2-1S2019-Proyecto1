/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Fabrica_Sentencias
{
    Nodo_AST_FS sentencia_ejecutar;
    
    public Fabrica_Sentencias(Nodo_AST_FS nodo_sentencia)
    {
        sentencia_ejecutar = nodo_sentencia;
    }

    public Instruccion ejecutar() 
    {
        if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_DECLARACION"))
        {
            Sentencia_Declaracion nueva_declaracion = new Sentencia_Declaracion(sentencia_ejecutar);
            return nueva_declaracion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_ASIGNACION"))
        {
            Sentencia_Asignacion nueva_asignacion = new Sentencia_Asignacion();
            return nueva_asignacion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_IMPRIMIR"))
        {
            Sentencia_Imprimir nueva_impresion = new Sentencia_Imprimir(sentencia_ejecutar);
            return nueva_impresion;
        }
        else if(sentencia_ejecutar.IsNodoOrNot("SENTENCIA_SI")||sentencia_ejecutar.IsNodoOrNot("SENTENCIA_SINO_SI"))
        {
            Sentencia_Si nuevo_si = new Sentencia_Si(sentencia_ejecutar);
            return nuevo_si;
        }
        else
        {
            return null;
        }        
    }
}
