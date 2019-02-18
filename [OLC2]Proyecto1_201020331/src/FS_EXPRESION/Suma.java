 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_EXPRESION;

import UI.ObjetoEntrada;
import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;

/**
 *
 * @author crist
 */
public class Suma implements Instruccion
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
    
    private Tabla_Enums.tipo_operacion [][] tabla_suma = {                                                 
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.suma_entero,Tabla_Enums.tipo_operacion.suma_decimal,Tabla_Enums.tipo_operacion.suma_entero_caracter,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.suma_decimal,Tabla_Enums.tipo_operacion.suma_decimal,Tabla_Enums.tipo_operacion.suma_decimal_caracter,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.suma_caracter_entero,Tabla_Enums.tipo_operacion.suma_caracter_decimal,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.concatenacion,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error},
                                                            {Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error,Tabla_Enums.tipo_operacion.error}
                                                        };
    
    public Suma(Expresion p_izq, Expresion p_der) 
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
            
            resultado = tabla_suma[valor1.getTipo().ordinal()] [valor2.getTipo().ordinal()];
            Simbolo nuevo_simbolo = new Simbolo();
            
            switch(resultado)
            {                
                case suma_entero:
                    int val1_entero = Integer.parseInt(valor1.getValor().toString());
                    int val2_entero = Integer.parseInt(valor2.getValor().toString());
                    int resultado_final_entero = val1_entero + val2_entero;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(resultado_final_entero);
                    
                    return nuevo_simbolo;
                case suma_decimal:
                    double val1_double = Double.parseDouble(valor1.getValor().toString());
                    double val2_double = Double.parseDouble(valor2.getValor().toString());
                    double resultado_final_double = val1_double + val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                    nuevo_simbolo.setValor(formatearDecimales(resultado_final_double,4));
                    
                    return nuevo_simbolo;      
                case suma_entero_caracter:
                    val1_entero = Integer.parseInt(valor1.getValor().toString());                            
                    val2_entero = (int) valor2.getValor().toString().charAt(0);                            
                    resultado_final_entero = val1_entero + val2_entero;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(resultado_final_entero);
                    
                    return nuevo_simbolo;
                case suma_caracter_entero:
                    val1_entero = (int) valor1.getValor().toString().charAt(0);
                    val2_entero = Integer.parseInt(valor2.getValor().toString());
                    resultado_final_entero = val1_entero + val2_entero;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(resultado_final_entero);
                    
                    return nuevo_simbolo;
                case suma_decimal_caracter:
                    val1_double = Double.parseDouble(valor1.getValor().toString());                             
                    val2_double = (int) valor2.getValor().toString().charAt(0);
                    resultado_final_double = val1_double + val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                    nuevo_simbolo.setValor(formatearDecimales(resultado_final_double,4));
                    
                    return nuevo_simbolo;    
                case suma_caracter_decimal:
                    val1_double = (int) valor1.getValor().toString().charAt(0);
                    val2_double = Double.parseDouble(valor2.getValor().toString());
                    resultado_final_double = val1_double + val2_double;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                    nuevo_simbolo.setValor(formatearDecimales(resultado_final_double,4));
                    
                    return nuevo_simbolo;                    
                case concatenacion:
                    String val1_cadena = valor1.getValor().toString();
                    String val2_cadena = valor2.getValor().toString();
                    String resultado_final_cadena = val1_cadena + val2_cadena;
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor(resultado_final_cadena);
                    
                    return nuevo_simbolo;    
                case error:                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible sumar un valor del tipo " + valor1.getTipo() + " con un valor tipo " + valor2.getTipo() +".");
                    
                    return nuevo_simbolo;
                default:                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible realizar la suma, verifique los valores.");
                    
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
            nuevo_simbolo.setValor("No es posible realizar la suma, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }   
    
    public Double formatearDecimales(Double numero, Integer numeroDecimales) 
    {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }
}
