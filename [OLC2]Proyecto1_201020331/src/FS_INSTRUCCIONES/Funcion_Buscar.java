/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Funcion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_TABLA_SIMBOLOS.Tabla_Simbolos;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Funcion_Buscar implements Instruccion
{
    private int fila;
    private int columna;
    
    private String identificador;
    private FS_Arreglo arreglo;
    
    public Funcion_Buscar(Nodo_AST_FS nodo_funcion, FS_Arreglo p_arreglo)
    {
        this.fila = Integer.parseInt(nodo_funcion.getFila());
        this.columna = Integer.parseInt(nodo_funcion.getColumna());
        
        this.identificador = nodo_funcion.getValor();
        this.arreglo = p_arreglo;        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            FS_Funcion funcion_llamar;
            Simbolo simbolo_aux;            
            
            if(arreglo.size() > 0)
            {
                if(Tabla_Simbolos.getInstance().existe_metodo(identificador))
                {
                    funcion_llamar = Tabla_Simbolos.getInstance().obtener_Metodo(identificador);
                    
                    if(funcion_llamar.getLista_parametros().size() == 1)
                    {
                        for(int i = 0; i < arreglo.size(); i++)
                        {
                            funcion_llamar.getLista_parametros_enviados().clear();
                            funcion_llamar.getLista_parametros_enviados().add(arreglo.get(i));
                            funcion_llamar.cargarParametros();
                            simbolo_aux = funcion_llamar.ejecutar(entorno_local, salida);
                            if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                            {
                                return simbolo_aux;
                            }
                            else if(simbolo_aux.getTipo()== Tabla_Enums.tipo_primitivo_Simbolo.retornar)
                            {
                                simbolo_aux = (Simbolo) simbolo_aux.getValor();
                                if(simbolo_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.booleano && simbolo_aux.getValor().equals("verdadero"))
                                {
                                    return arreglo.get(i);
                                }                                
                            }
                            else
                            {
                                Simbolo nuevo_simbolo = new Simbolo();
                                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                                nuevo_simbolo.setValor("La llamada a Funcion Buscar de Arreglo no puede recibir como retorno objetos o arreglos, así como metodos sin retorno.");

                                return nuevo_simbolo;  
                            }
                            
                        }    
                        
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);                                                
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.nulo);
                        nuevo_simbolo.setIdentificador("10-4");                        
                        nuevo_simbolo.setValor("nulo");
                        
                        return nuevo_simbolo;
                        
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setValor("La llamada a Funcion Buscar de Arreglo no puede ejecutarse la funcion a ejecutar tiene mas de un parametro o no tiene.");

                        return nuevo_simbolo;  
                    }
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("La llamada a Funcion Buscar de Arreglo no puede ejecutarse la funcion a ejecutar no existe.");

                    return nuevo_simbolo;  
                }    
                                    
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("La llamada a Funcion Buscar de Arreglo no puede ejecutarse en un arreglo vacio.");

                return nuevo_simbolo;                
            }
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("La llamada a funciones de un Arreglo no fue realizada, error: " + e.getMessage());
            
            return nuevo_simbolo;
        }
    }  
}
