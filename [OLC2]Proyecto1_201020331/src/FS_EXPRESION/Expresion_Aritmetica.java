/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_EXPRESION;

import FS_AST.Nodo_AST_FS;
import UI.ObjetoEntrada;
import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;

/**
 *
 * @author crist
 */
public class Expresion_Aritmetica implements Instruccion
{
    private Nodo_AST_FS p_izq_unario;
    
    private Expresion p_izq;
    private Expresion p_der;
    private String operador;
    
    public Expresion_Aritmetica(Nodo_AST_FS p_izq, Nodo_AST_FS p_operador, Nodo_AST_FS p_der)
    {
        this.operador = p_operador.getEtiqueta();        
        if(operador.equals("++") || operador.equals("--"))
        {
            this.p_izq_unario = p_izq;
            this.p_izq = null;
            this.p_der = null;             
        }
        else
        {
            this.p_izq = new Expresion(p_izq);
            this.p_der = new Expresion(p_der); 
            this.p_izq_unario = null;
        }               
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        if(operador.equals("+"))
        {
            Suma nueva_suma = new Suma(p_izq, p_der);
            return nueva_suma.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("-"))
        {
            Resta nueva_resta = new Resta(p_izq, p_der);
            return nueva_resta.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("*"))
        {
            Multiplicacion nueva_multiplicacion = new Multiplicacion(p_izq, p_der);
            return nueva_multiplicacion.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("/"))
        {
            Division nueva_division = new Division(p_izq, p_der);
            return nueva_division.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("%"))
        {
            //Modulo nuevo_modulo = new Modulo(p_izq, p_der);
            //return nuevo_modulo.ejecutar(entorno_local,salida);
            return null;
        }
        else if(operador.equals("^"))
        {
            Potencia nueva_potencia = new Potencia(p_izq, p_der);
            return nueva_potencia.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("++"))
        {
            Incremento nuevo_incremento = new Incremento(p_izq_unario);
            return nuevo_incremento.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("--"))
        {
            Decremento nuevo_decremento = new Decremento(p_izq_unario);
            return nuevo_decremento.ejecutar(entorno_local,salida);
        }
        else
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar la expresion aritmetica, verifique los valores.");
            return nuevo_simbolo;
        }            
    }              
}
