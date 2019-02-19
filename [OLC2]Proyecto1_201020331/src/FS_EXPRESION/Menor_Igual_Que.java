/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_EXPRESION;

import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Menor_Igual_Que implements Instruccion
{
    private Expresion p_izq;
    private Expresion p_der;
    
    private Simbolo valor1;
    private Simbolo valor2;
    
    private Tabla_Enums.tipo_operacion resultado;
    
    /*
     *   0.nulo
     *   1.booleano 
     *   2.entero
     *   3.decimal
     *   4.caracter
     *   5.cadena
     *   6.identificador
     *   7.error
    */
    
    private Tabla_Enums.tipo_operacion [][] tabla_menor_igual_que = {                                                 
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.menorigualque_booleano,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.menorigualque_numerico,Tabla_Enums.tipo_operacion.menorigualque_numerico,Tabla_Enums.tipo_operacion.menorigualque_numerico_caracter,Tabla_Enums.tipo_operacion.menorigualque_numerico_caracter,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.menorigualque_numerico,Tabla_Enums.tipo_operacion.menorigualque_numerico,Tabla_Enums.tipo_operacion.menorigualque_numerico_caracter,Tabla_Enums.tipo_operacion.menorigualque_numerico_caracter,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.menorigualque_caracter_numerico,Tabla_Enums.tipo_operacion.menorigualque_caracter_numerico,Tabla_Enums.tipo_operacion.menorigualque_caracter,Tabla_Enums.tipo_operacion.menorigualque_caracter,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.menorigualque_caracter_numerico,Tabla_Enums.tipo_operacion.menorigualque_caracter_numerico,Tabla_Enums.tipo_operacion.menorigualque_caracter,Tabla_Enums.tipo_operacion.menorigualque_caracter,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                                {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error}
                                                              };
    public Menor_Igual_Que(Expresion p_izq, Expresion p_der) 
    {
        this.p_izq = p_izq;
        this.p_der = p_der;
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            valor1 = (p_izq == null) ? null : p_izq.ejecutar(entorno_local, salida);
            valor2 = (p_izq == null) ? null : p_der.ejecutar(entorno_local, salida);
            
            if(valor1 == null || valor2 == null)
            {
                return null;
            }
            else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor1;
            }
            else if(valor2.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor2;
            }    
            
            resultado = tabla_menor_igual_que[valor1.getTipo().ordinal()] [valor2.getTipo().ordinal()];
            boolean resultado_final = false;
            Simbolo nuevo_simbolo = new Simbolo();
            
            switch(resultado)
            {
                case menorigualque_numerico:
                    double val1_double = Double.parseDouble(valor1.getValor().toString());
                    double val2_double = Double.parseDouble(valor2.getValor().toString());
                    resultado_final = val1_double <= val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                    nuevo_simbolo.setValor(resultado_final == true ? "verdadero" :"falso");
                    
                    return nuevo_simbolo;
                case menorigualque_caracter:
                    int val1_entero = equivalenteCadena(valor1.getValor().toString());
                    int val2_entero = equivalenteCadena(valor2.getValor().toString());
                    resultado_final = val1_entero <= val2_entero;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                    nuevo_simbolo.setValor(resultado_final == true ? "verdadero" :"falso");
                    
                    return nuevo_simbolo;      
                case menorigualque_booleano:                    
                    val1_entero = valor1.getValor().toString().equals("verdadero") ? 1 : 0;
                    val2_entero = valor2.getValor().toString().equals("verdadero") ? 1 : 0;                            
                    resultado_final = val1_entero <= val2_entero;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                    nuevo_simbolo.setValor(resultado_final == true ? "verdadero" :"falso");
                    
                    return nuevo_simbolo;
                case menorigualque_numerico_caracter:
                    val1_double = Double.parseDouble(valor1.getValor().toString());                            
                    val2_double = equivalenteCadena(valor2.getValor().toString());
                    resultado_final = val1_double <= val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                    nuevo_simbolo.setValor(resultado_final == true ? "verdadero" :"falso");
                    
                    return nuevo_simbolo;
                case menorigualque_caracter_numerico:
                    val1_double = equivalenteCadena(valor1.getValor().toString());
                    val2_double = Double.parseDouble(valor2.getValor().toString());
                    resultado_final = val1_double <= val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                    nuevo_simbolo.setValor(resultado_final == true ? "verdadero" :"falso");
                    
                    return nuevo_simbolo;                 
                case error:                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible relacionar un valor del tipo " + valor1.getTipo() + " con un valor tipo " + valor2.getTipo() +".");
                    
                    return nuevo_simbolo;
                default:                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible realizar menor igual que, verifique los valores.");
                    
                    return nuevo_simbolo;
            }                        
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar menor igual que, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }
    
    public Integer equivalenteCadena(String p_cadena) 
    {
        Integer valor_final = 0;
        
        for(int i = 0; i < p_cadena.length()-1; i++)
        {
            valor_final = valor_final + (int) p_cadena.charAt(i);
        }
        
        return valor_final;
    }    
}
