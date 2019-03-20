 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Boton;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Texto;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Boton implements Instruccion
{
    private int fila;
    private int columna;
    
    //principales
    private String id;
    private int posx;
    private int posy;
    private String texto;
    
    //opcionales    
    private int ancho;
    private int alto;
    private String referencia;
        
    private boolean enviar;
    private ArrayList<GXML_Elemento> lista_elementos;
    
    public Sentencia_Boton(ArrayList<GXML_Elemento> p_lista_elementos, String p_texto, int p_fila, int p_columna, boolean p_enviar)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        this.lista_elementos = p_lista_elementos;
        this.texto = p_texto;
        this.enviar = p_enviar;       
    }            
    
    public Sentencia_Boton(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna, boolean p_enviar)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        this.lista_elementos = p_lista_elementos;
        this.texto = "";
        this.enviar = p_enviar;
    }   
    
    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 3;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("nombre") && principal > 0)
            {
                this.id = lista_elementos.get(i).getValor().toString();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("x") && principal > 0)
            {
                this.posx = (Integer) lista_elementos.get(i).getValor();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("y") && principal > 0)
            {
                this.posy = (Integer) lista_elementos.get(i).getValor();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
        }
        
        return completo;
    }
    
    private boolean cargar_opcionales(FS_Boton boton_nuevo)
    {
        boolean completo = true;
        int principal = 4;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            
            if(lista_elementos.get(i).IsElementOrNot("alto") && principal > 0)
            {
                boton_nuevo.setAlto((Integer) lista_elementos.get(i).getValor());           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("ancho") && principal > 0)
            {
                boton_nuevo.setAncho((Integer) lista_elementos.get(i).getValor());           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("referencia") && principal > 0)
            {
                boton_nuevo.setReferencia(lista_elementos.get(i).getValor().toString());
                completo = true;
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }            
            else if(lista_elementos.get(i).IsElementOrNot("accion") && principal > 0)
            {
                //texto_nuevo.setItalic((Boolean) lista_elementos.get(i).getValor());
                completo = true;
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(!(lista_elementos.get(i).IsElementOrNot("nombre") || lista_elementos.get(i).IsElementOrNot("x") || lista_elementos.get(i).IsElementOrNot("y")))
            {
               return false;
            }
        }        
        return completo;
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, String padre) 
    {
        try
        {
            boolean continuar = cargar_principales();
            if(continuar)
            {
                FS_Boton boton_nuevo;
                boton_nuevo = new FS_Boton(id,texto,posx,posy,enviar);
                String traduccion = "";
                Simbolo simbolo_texto = new Simbolo();
                
                if(cargar_opcionales(boton_nuevo))
                {
                    traduccion = boton_nuevo.traducirBoton(padre);
                    simbolo_texto.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_texto.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_texto.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_texto.setIdentificador(id);            
                    simbolo_texto.setValor(boton_nuevo);;
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: El boton tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                } 
                                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_texto);
                                                                   
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setIdentificador("10-4");            
                    nuevo_simbolo.setValor(traduccion);

                    return nuevo_simbolo;
                }   
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: la etiqueta texto " + id + " ya fue creada.");

                    return nuevo_simbolo;
                }                    
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: no cuenta con los elementos principales para su creación.");

                return nuevo_simbolo;
            }            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
            nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, FS_Arreglo lista_componentes, ObjetoEntrada salida) 
    {
        try
        {
            boolean continuar = cargar_principales();
            if(continuar)
            {
                FS_Boton boton_nuevo;
                boton_nuevo = new FS_Boton(id,texto,posx,posy,enviar);

                Simbolo simbolo_texto = new Simbolo();
                if(cargar_opcionales(boton_nuevo))
                {
                    boton_nuevo.actualizarBoton();
                    simbolo_texto.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_texto.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_texto.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_texto.setIdentificador(id);            
                    simbolo_texto.setValor(boton_nuevo);;
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: El boton tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                } 
                                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_texto);
                                                                   
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setIdentificador(id);            
                    nuevo_simbolo.setValor(boton_nuevo);

                    return nuevo_simbolo;
                }   
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: la etiqueta texto " + id + " ya fue creada.");

                    return nuevo_simbolo;
                }                    
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: no cuenta con los elementos principales para su creación.");

                return nuevo_simbolo;
            }            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
            nuevo_simbolo.setValor("Creación del Boton no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }    
    }
    
}
