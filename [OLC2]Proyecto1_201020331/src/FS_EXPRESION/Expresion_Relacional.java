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
 * @author crist
 */
public class Expresion_Relacional implements Instruccion
{
    private Expresion p_izq;
    private Expresion p_der;
    private String operador;
    
    public Expresion_Relacional(Nodo_AST_FS p_izq, Nodo_AST_FS p_operador, Nodo_AST_FS p_der)
    {
        this.p_izq = new Expresion(p_izq);
        this.p_der = new Expresion(p_der);
        this.operador = p_operador.getEtiqueta();        
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        if(operador.equals(">"))
        {
            Mayor_Que nuevo_mayorque = new Mayor_Que(p_izq, p_der);
            return nuevo_mayorque.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("<"))
        {
            Menor_Que nuevo_menorque = new Menor_Que(p_izq, p_der);
            return nuevo_menorque.ejecutar(entorno_local,salida);
        }
        else if(operador.equals(">="))
        {
            Mayor_Igual_Que nuevo_mayorigualque = new Mayor_Igual_Que(p_izq, p_der);
            return nuevo_mayorigualque.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("<="))
        {
            Menor_Igual_Que nuevo_menorigualque = new Menor_Igual_Que(p_izq, p_der);
            return nuevo_menorigualque.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("=="))
        {
            Igual_Que nuevo_igualque = new Igual_Que(p_izq, p_der);
            return nuevo_igualque.ejecutar(entorno_local,salida);
        }
        else if(operador.equals("!="))
        {
            Diferente_Que nuevo_diferenteque = new Diferente_Que(p_izq, p_der);
            return nuevo_diferenteque.ejecutar(entorno_local,salida);
        }
        else
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar la expresion relacional, verifique los valores.");
            return nuevo_simbolo;
        }            
    }                  
}
