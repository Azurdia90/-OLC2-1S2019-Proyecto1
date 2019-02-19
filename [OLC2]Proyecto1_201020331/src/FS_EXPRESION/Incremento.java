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
public class Incremento implements Instruccion
{
    boolean variable_o_no;
    
    private Nodo_AST_FS p_izq_aux;
    private Expresion p_izq;
        
    private Simbolo valor1;
    
    public Incremento(Nodo_AST_FS p_izq) 
    {
        String cadena_comp = p_izq.getEtiqueta();
        
        if(cadena_comp.equals("identificador"))
        {
            this.variable_o_no = true;
            this.p_izq_aux = p_izq;
        }
        else
        {
            this.variable_o_no = false;
            this.p_izq = new Expresion(p_izq);
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo nuevo_simbolo = new Simbolo();
            
            if(variable_o_no)
            {
                valor1 = entorno_local.Obtener(p_izq_aux.getValor());
                
                if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return valor1;
                }
                
                if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero)
                {
                    int val1_entero = Integer.parseInt(valor1.getValor().toString());
                    
                    valor1.setValor(val1_entero + 1);
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(val1_entero + 1);

                }    
                else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.caracter)
                {
                    int val1_entero = (int) valor1.getValor().toString().charAt(0);
                    
                    valor1.setValor((char) (val1_entero + 1));

                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(val1_entero + 1);
                }
                else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
                {
                    double val1_double = Double.parseDouble(valor1.getValor().toString());
                    
                    valor1.setValor(val1_double + 1);
                    
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                    nuevo_simbolo.setValor(val1_double + 1);                
                }
                else
                {
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible aumentar un valor del tipo " + valor1.getTipo() + ".");                
                }

                return nuevo_simbolo;
                
            }
            else
            {
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
                    nuevo_simbolo.setValor(val1_entero + 1);

                }    
                else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.caracter)
                {
                    int val1_entero = (int) valor1.getValor().toString().charAt(0);

                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                    nuevo_simbolo.setValor(val1_entero + 1);
                }
                else if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
                {
                    double val1_double = Double.parseDouble(valor1.getValor().toString());

                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.decimal);
                    nuevo_simbolo.setValor(val1_double + 1);                
                }
                else
                {
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("33-12");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("No es posible aumentar un valor del tipo " + valor1.getTipo() + ".");                
                }

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
            nuevo_simbolo.setValor("No es posible realizar el aumento, error: " + e.getMessage());
            
            return nuevo_simbolo;            
        }
    }
}
