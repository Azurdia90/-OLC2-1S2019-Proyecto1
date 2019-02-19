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
public class Expresion_Unaria implements Instruccion
{
    private Expresion p_izq;
    
    private String operador;
    
    private Simbolo valor1;
    
    public Expresion_Unaria(Nodo_AST_FS p_izq, Nodo_AST_FS p_operador) 
    {
        this.p_izq = new Expresion(p_izq);
        this.operador = p_operador.getEtiqueta();
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo nuevo_simbolo = new Simbolo();
                       
            valor1 = (p_izq == null) ? null : p_izq.ejecutar(entorno_local, salida);

            if(valor1 == null)
            {
                return null;
            }
            else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return valor1;
            }                

            if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero)
            {
                int val1_entero = Integer.parseInt(valor1.getValor().toString());

                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                if(operador.equals("+"))
                {
                    nuevo_simbolo.setValor(val1_entero * 1);
                }
                else if(operador.equals("-"))
                {
                    nuevo_simbolo.setValor(val1_entero * (-1));
                }
            }
            else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
            {
                double val1_double = Double.parseDouble(valor1.getValor().toString());

                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                if(operador.equals("+"))
                {
                    nuevo_simbolo.setValor(val1_double * 1);
                }
                else if(operador.equals("-"))
                {
                    nuevo_simbolo.setValor(val1_double * (-1));
                }               
            }            
            else
            {
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("33-12");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("No es posible usar operadores unarios en un valor del tipo " + valor1.getTipo() + ".");                
            }

            return nuevo_simbolo;                            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("No es posible realizar una operacion unaria, error: " + e.getMessage());
            
            return nuevo_simbolo;            
        }
    }            
}
