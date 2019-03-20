/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Boton;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Texto;
import FS_OBJETOS.FS_Ventana;
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
public class Sentencia_Ventana implements Instruccion
{
    private int fila;
    private int columna;
    
//principales
    private String id;
    private Boolean tipo;
    
    //opcionales
    private Color color;
    private Instruccion accionInicial;
    private Instruccion accionFinal;
    
    private ArrayList<GXML_Elemento> lista_elementos;
    private ArrayList<Instruccion> lista_contenido;
    
    //Este entorno solo es para guardar los componentes de la ventana y
    //saber si no vienen repetidos por ventana;
    private Entorno entorno_local; 
            
    //CONSTRUCTOR PARA VENTANA SIN NADA DE CONTENIDO
    public Sentencia_Ventana(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.lista_elementos = p_lista_elementos;
        this.lista_contenido = new ArrayList<Instruccion>();
        this.entorno_local = new Entorno();
        
    }
    
    //CONSTRUCTOR PARA VENTANA CON CONTENIDO
    public Sentencia_Ventana(ArrayList<GXML_Elemento> p_lista_elementos, ArrayList<Instruccion> p_lista_contenido, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.lista_elementos = p_lista_elementos;
        this.lista_contenido = p_lista_contenido;
        this.entorno_local = new Entorno();
    }
    
    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 2;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("id") && principal > 0)
            {
                this.id = lista_elementos.get(i).getValor().toString();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("tipo") && principal > 0)
            {
                if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("principal"))
                {
                    this.tipo = true;
                }
                else 
                {
                    this.tipo = false;
                }
                
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
        }
        
        return completo;
    }
    
    private boolean cargar_opcionales(FS_Ventana ventana_nueva)
    {
        boolean completo = true;
        int principal = 5;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("alto") && principal > 0)
            {
                ventana_nueva.setAlto((Integer) lista_elementos.get(i).getValor());           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("ancho") && principal > 0)
            {
                ventana_nueva.setAncho((Integer) lista_elementos.get(i).getValor());           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("color") && principal > 0)
            {
                try
                {
                    Color color = Color.decode(lista_elementos.get(i).getValor().toString());
                    ventana_nueva.setBackground(color);
                    completo = true;
                    principal--;
                }
                catch(Exception e)
                {
                    return false;
                }
                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("accioninicial") && principal > 0)
            {
                ventana_nueva.setNodo_alcargar(((Nodo_AST_FS) lista_elementos.get(i).getValor()));
                completo = true;
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("accionfinal") && principal > 0)
            {
                ventana_nueva.setNodo_alcerrar(((Nodo_AST_FS) lista_elementos.get(i).getValor()));
                completo = true;
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(!(lista_elementos.get(i).IsElementOrNot("id") || lista_elementos.get(i).IsElementOrNot("tipo")))
            {
               return false;
            }
        }
        
        return completo;
    }
        
    @Override
    public Simbolo ejecutar(Entorno entorno_global, String padre) 
    {
        try
        {
            boolean continuar = cargar_principales();
            if(continuar)
            {
                FS_Ventana ventana_nueva;
                Simbolo simbolo_ventana = new Simbolo();
                ventana_nueva = new FS_Ventana(id,tipo); 
                String traduccion;
                                
                if(cargar_opcionales(ventana_nueva))
                {
                    traduccion = ventana_nueva.traducirVentana();
                    
                    simbolo_ventana.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_ventana.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_ventana.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_ventana.setIdentificador(id);            
                    simbolo_ventana.setValor(ventana_nueva);                                                                                                  
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: la ventana tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                }           
                
                if(!entorno_global.containsKey(id))
                {
                    entorno_global.Crear(id, simbolo_ventana);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {                        
                        Simbolo contenido_aux = lista_contenido.get(i).ejecutar(entorno_local, "vent_" + id);
                        if(contenido_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                        {
                            return contenido_aux;                            
                        }
                        else
                        {
                            traduccion += contenido_aux.getValor().toString();
                        }                        
                    }
                    
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
                    nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: la ventana " + id + " ya fue creada.");

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
                nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: " + e.getMessage());

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
                FS_Ventana ventana_nueva;
                ventana_nueva = new FS_Ventana(id,tipo);                
                Simbolo simbolo_ventana = new Simbolo();
                
                if(cargar_opcionales(ventana_nueva))
                {
                    ventana_nueva.repaint();
                    
                    simbolo_ventana.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_ventana.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_ventana.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_ventana.setIdentificador(id);            
                    simbolo_ventana.setValor(ventana_nueva);
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: la ventana tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                }
                                                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_ventana);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        Simbolo contenido_aux = lista_contenido.get(i).ejecutar(entorno_local, lista_componentes, salida);
                        if(contenido_aux.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                        {
                            return contenido_aux;                            
                        }
                        else if(contenido_aux.getValor() instanceof FS_Contenedor)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.getLista_componentes().addAll(((FS_Contenedor) contenido_aux.getValor()).getLista_componentes());
                            ventana_nueva.add((FS_Contenedor)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Caja_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_Caja_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Area_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_Area_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Spinner)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_Spinner)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_ComboBox)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_ComboBox)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Boton)
                        {
                            lista_componentes.add(contenido_aux);
                            ventana_nueva.getLista_componentes().add(contenido_aux.getValor());
                            ventana_nueva.add((FS_Boton)contenido_aux.getValor());
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: Al la Ventana se le intentan agregar componentes adicionales o incorrectos.");

                            return nuevo_simbolo;
                        }
                    }                            
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setIdentificador(id);            
                    nuevo_simbolo.setValor(ventana_nueva);

                    return nuevo_simbolo;
                }   
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: la ventana " + id + " ya fue creada.");

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
                nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }            
    }
    
}
