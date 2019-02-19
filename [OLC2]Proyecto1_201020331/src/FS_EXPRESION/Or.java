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
public class Or implements Instruccion
{
    private Expresion p_izq;
    private Expresion p_der;
    
    private Simbolo valor1;
    private Simbolo valor2;
    
    public Or(Expresion p_izq, Expresion p_der) 
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
            
            Simbolo nuevo_simbolo = new Simbolo();
            
            if(valor1.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano && valor2.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano)
            {
                boolean val1_boolean = valor1.getValor().toString().equals("verdadero") ? true : false;
                boolean val2_boolean = valor2.getValor().toString().equals("verdadero") ? true : false;
                boolean resultado = val1_boolean || val2_boolean;
                
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                nuevo_simbolo.setValor(resultado == true ? "verdadero" : "falso");                
            } 
            else
            {
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("33-12");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("No es posible realizar or con valores no booleanos.");    
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
            nuevo_simbolo.setValor("No es posible realizar Or, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }    
    }    
}
