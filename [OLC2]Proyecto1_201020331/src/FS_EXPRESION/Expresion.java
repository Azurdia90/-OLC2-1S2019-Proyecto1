/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_EXPRESION;

import FS_AST.Nodo_AST_FS;
import UI.ObjetoEntrada;
import FS_INSTRUCCIONES.Instruccion;
import FS_INSTRUCCIONES.Sentencia_Crear_Ventana;
import FS_INSTRUCCIONES.Sentencia_LLamada;
import FS_INSTRUCCIONES.Sentencia_Seleccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;

/**
 *
 * @author crist
 */
public class Expresion implements Instruccion
{
    private int fila;
    private int columna;
    
    private Nodo_AST_FS op_izq;
    private Nodo_AST_FS op_der;
    private Nodo_AST_FS operador;
    
    private String tipo_expresion;
    
    public Expresion(Nodo_AST_FS p_expresion)
    {        
        this.fila = Integer.parseInt(p_expresion.getFila());
        this.columna = Integer.parseInt(p_expresion.getColumna());
        
        if(p_expresion.IsNodoOrNot("booleano") || p_expresion.IsNodoOrNot("entero") || p_expresion.IsNodoOrNot("decimal") || p_expresion.IsNodoOrNot("caracter") || p_expresion.IsNodoOrNot("cadena") || p_expresion.IsNodoOrNot("identificador"))
        {//si es un dato primitivo
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();  
        }
        else if(p_expresion.IsNodoOrNot("EXPRESION_ARITMETICA")||p_expresion.IsNodoOrNot("EXPRESION_UNARIA")||p_expresion.IsNodoOrNot("EXPRESION_RELACIONAL")||p_expresion.IsNodoOrNot("EXPRESION_LOGICA"))
        {//Si es algun tipo de expresion

            if(p_expresion.getHijos().get(0).getHijos().size() == 1)
            {
                op_izq = p_expresion.getHijos().get(0).getHijos().get(0);
                operador = p_expresion.getHijos().get(0);  
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
        else if(p_expresion.IsNodoOrNot("SENTENCIA_SELECCION"))
        {
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();
        }
        else if(p_expresion.IsNodoOrNot("SENTENCIA_LLAMADA"))
        {
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();
        }
        else if(p_expresion.IsNodoOrNot("SENTENCIA_ACCESO"))
        {
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();
        }
        else if(p_expresion.IsNodoOrNot("SENTENCIA_CREAR_VENTANA"))
        {
            op_izq = p_expresion;
            operador = null;
            op_der = null;  
            tipo_expresion = p_expresion.getEtiqueta();
        }
    }
        
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
             Simbolo nuevo_simbolo = new Simbolo();
            if(tipo_expresion.equals("EXPRESION_ARITMETICA"))
            {
                Expresion_Aritmetica expresion_aritmetica = new Expresion_Aritmetica(op_izq, operador, op_der);
                nuevo_simbolo = expresion_aritmetica.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    nuevo_simbolo.setIdentificador(fila + "-" + columna);
                }
                return nuevo_simbolo;          
            }
            if(tipo_expresion.equals("EXPRESION_UNARIA"))
            {
                Expresion_Unaria expresion_unaria = new Expresion_Unaria(op_izq, operador);
                nuevo_simbolo = expresion_unaria.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    nuevo_simbolo.setIdentificador(fila + "-" + columna);
                }
                return nuevo_simbolo;
            }
            else if(tipo_expresion.equals("EXPRESION_RELACIONAL"))
            {
                Expresion_Relacional expresion_relacional = new Expresion_Relacional(op_izq, operador, op_der);
                nuevo_simbolo = expresion_relacional.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    nuevo_simbolo.setIdentificador(fila + "-" + columna);
                }
                return nuevo_simbolo;
            }
            else if(tipo_expresion.equals("EXPRESION_LOGICA"))
            {
                Expresion_Logica expresion_logica = new Expresion_Logica(op_izq, operador, op_der);
                nuevo_simbolo = expresion_logica.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    nuevo_simbolo.setIdentificador(fila + "-" + columna);
                }
                return nuevo_simbolo;
            }
            else if(tipo_expresion.equals("SENTENCIA_SELECCION"))
            {
                Sentencia_Seleccion sentencia_seleccion = new Sentencia_Seleccion(op_izq);
                nuevo_simbolo = sentencia_seleccion.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    nuevo_simbolo.setIdentificador(fila + "-" + columna);
                }
                return nuevo_simbolo;
            }
            else if(tipo_expresion.equals("SENTENCIA_LLAMADA"))
            {
                Sentencia_LLamada sentencia_llamada = new Sentencia_LLamada(op_izq);
                nuevo_simbolo = sentencia_llamada.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo()  != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return (Simbolo) nuevo_simbolo.getValor();
                }
                else
                {
                    return nuevo_simbolo;
                }                
            }
            else if(tipo_expresion.equals("SENTENCIA_ACCESO"))
            {
                Sentencia_LLamada sentencia_llamada = new Sentencia_LLamada(op_izq);
                nuevo_simbolo = sentencia_llamada.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo()  != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return (Simbolo) nuevo_simbolo.getValor();
                }
                else
                {
                    return nuevo_simbolo;
                }                
            }
            else if(tipo_expresion.equals("SENTENCIA_CREAR_VENTANA"))
            {
                Sentencia_Crear_Ventana sentencia_crear_ventana = new Sentencia_Crear_Ventana(op_izq);
                nuevo_simbolo = sentencia_crear_ventana.ejecutar(entorno_local, salida);
                if(nuevo_simbolo.getTipo()  != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return nuevo_simbolo;
                }
                else
                {
                    return nuevo_simbolo;
                }                
            }
            else
            {
                if(tipo_expresion.equals("identificador"))
                {
                    nuevo_simbolo = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local,op_izq.getValor());
                    if(nuevo_simbolo.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        nuevo_simbolo.setIdentificador(fila + "-" + columna);
                    }
                    return nuevo_simbolo;    
                }
                else
                {
                   
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
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Expresion no fue realizada, error: " + e.getMessage());
            
            return nuevo_simbolo; 
        }
    }    
}
