/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_EXPRESION;

import FS_AST.Nodo_AST_FS;
import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Expresion_Logica implements Instruccion
{
    private int fila;
    private int columna;
    
    private Expresion p_izq;
    private Expresion p_der;
    private String operador;
    
    public Expresion_Logica(Nodo_AST_FS p_izq, Nodo_AST_FS p_operador, Nodo_AST_FS p_der)
    {
        this.fila = Integer.parseInt(p_izq.getFila());
        this.columna = Integer.parseInt(p_izq.getColumna());
        
        this.operador = p_operador.getEtiqueta();
        
        if(!operador.equals("!"))
        {
            this.p_izq = new Expresion(p_izq);
            this.p_der = new Expresion(p_der);
        }
        else
        {
            this.p_izq = new Expresion(p_izq);
            this.p_der = null;
        }        
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        if(operador.equals("||"))
        {
            Or nuevo_or = new Or(p_izq, p_der);
            return nuevo_or.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("&&"))
        {
            And nuevo_and = new And(p_izq, p_der);
            return nuevo_and.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("!"))
        {
            Not nuevo_not = new Not(p_izq);
            return nuevo_not.ejecutar(entorno_local,salida);
        }
        else
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar la expresion logica, verifique los valores.");
            return nuevo_simbolo;
        }            
    }
    
}
