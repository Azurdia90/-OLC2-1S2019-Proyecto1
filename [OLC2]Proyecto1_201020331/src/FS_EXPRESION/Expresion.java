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
public class Expresion implements Instruccion
{
    private Nodo_AST_FS op_izq;
    private Nodo_AST_FS op_der;
    private Nodo_AST_FS operador;
    
    private String tipo_expresion;
    
    public Expresion(Nodo_AST_FS p_expresion)
    {        
        if(p_expresion.getEtiqueta().equals("booleano") || p_expresion.getEtiqueta().equals("entero") || p_expresion.getEtiqueta().equals("decimal") || p_expresion.getEtiqueta().equals("caracter") || p_expresion.getEtiqueta().equals("cadena") || p_expresion.getEtiqueta().equals("identificador"))
        {//si es un dato primitivo
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();  
        }
        else if(p_expresion.getEtiqueta().equals("EXPRESION_ARITMETICA")||p_expresion.getEtiqueta().equals("EXPRESION_RELACIONAL")||p_expresion.getEtiqueta().equals("EXPRESION_LOGICA"))
        {//Si es algun tipo de expresion

            if(p_expresion.getHijos().get(0).getHijos().size() == 1)
            {
                op_izq = p_expresion.getHijos().get(0).getHijos().get(0);
                operador = p_expresion.getHijos().get(0).getHijos().get(1);  
                op_der = null;  
            }
            else if(p_expresion.getHijos().get(0).getHijos().size() == 2)
            {
                op_izq = p_expresion.getHijos().get(0).getHijos().get(0);
                operador = p_expresion.getHijos().get(0); 
                op_der = p_expresion.getHijos().get(0).getHijos().get(1);
            }
            tipo_expresion =  p_expresion.getEtiqueta();
        }                                                                                                  
    }
        
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        if(tipo_expresion.equals("EXPRESION_ARITMETICA"))
        {
            Expresion_Aritmetica expresion_aritmetica = new Expresion_Aritmetica(op_izq, operador, op_der);
            return expresion_aritmetica.ejecutar(entorno_local, salida);
        }
        else if(tipo_expresion.equals("EXPRESION_RELACIONAL"))
        {
            return null;
        }
        else if(tipo_expresion.equals("EXPRESION_LOGICA"))
        {
            return null;
        }
        else
        {
            if(tipo_expresion.equals("identificador"))
            {
                Simbolo nuevo_simbolo = entorno_local.Obtener(op_izq.getValor());
                return nuevo_simbolo;    
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.valueOf(tipo_expresion));
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.caracter || nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.cadena)
                {
                    nuevo_simbolo.setValor(op_izq.getValor().substring(1, op_izq.getValor().length()-1));   
                }
                else
                {
                    nuevo_simbolo.setValor(op_izq.getValor());
                }                
                return nuevo_simbolo;    
            }
        }
    }    
}
