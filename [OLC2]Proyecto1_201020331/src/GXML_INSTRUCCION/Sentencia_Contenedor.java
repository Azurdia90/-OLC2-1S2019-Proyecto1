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
import javax.swing.BorderFactory;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Contenedor implements Instruccion
{
    private int fila;
    private int columna;
    
    //principales
    private String id;
    private int posx;
    private int posy;

    //opcionales
    private Color color;
    private int ancho;
    private int alto;
    private boolean borde;
    
    private ArrayList<GXML_Elemento> lista_elementos;
    private ArrayList<Instruccion> lista_contenido;
    
    
    //CONSTRUCTOR PARA VENTANA SIN NADA DE CONTENIDO
    public Sentencia_Contenedor(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
                
        this.lista_elementos = p_lista_elementos;
        this.lista_contenido = new ArrayList<Instruccion>();
    }
    
    //CONSTRUCTOR PARA VENTANA CON CONTENIDO
    public Sentencia_Contenedor(ArrayList<GXML_Elemento> p_lista_elementos, ArrayList<Instruccion> p_lista_contenido, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
                
        this.lista_elementos = p_lista_elementos;
        this.lista_contenido = p_lista_contenido;        
    }

    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 3;
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
    
    private boolean cargar_opcionales(FS_Contenedor contenedor_nuevo)
    {
        boolean completo = true;
        int principal = 4;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("color") && principal > 0)
            {
                try
                {
                    Color color = Color.decode(lista_elementos.get(i).getValor().toString());
                    contenedor_nuevo.setBackground(color);
                    contenedor_nuevo.repaint();                    
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
            else if(lista_elementos.get(i).IsElementOrNot("alto") && principal > 0)
            {
                contenedor_nuevo.setAlto((Integer) lista_elementos.get(i).getValor());
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("ancho") && principal > 0)
            {
                contenedor_nuevo.setAncho((Integer) lista_elementos.get(i).getValor());            
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("borde") && principal > 0)
            {
                if( (Boolean)(lista_elementos.get(i).getValor()))
                {
                    contenedor_nuevo.setBorder(BorderFactory.createRaisedBevelBorder());           
                }                
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(!(lista_elementos.get(i).IsElementOrNot("id") || lista_elementos.get(i).IsElementOrNot("x") || lista_elementos.get(i).IsElementOrNot("y")))
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
                FS_Contenedor contenedor_nuevo;
                contenedor_nuevo = new FS_Contenedor(id,posx,posy);
                String traduccion = "";
                Simbolo simbolo_contenedor = new Simbolo();
                
                if(cargar_opcionales(contenedor_nuevo))
                {
                    traduccion = contenedor_nuevo.traducirContenedor(padre); 
                    
                    simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_contenedor.setIdentificador(id);            
                    simbolo_contenedor.setValor(contenedor_nuevo);
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: la ventana tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                }                
                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_contenedor);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        Simbolo contenido_aux = lista_contenido.get(i).ejecutar(entorno_local, padre + "_cont_" + id);
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
                    nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: el contenedor " + id + " ya fue creado.");

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
                nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: " + e.getMessage());

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
                FS_Contenedor contenedor_nuevo;
                contenedor_nuevo = new FS_Contenedor(id,posx,posy);
                
                Simbolo simbolo_contenedor = new Simbolo();
                if(cargar_opcionales(contenedor_nuevo))
                {
                    contenedor_nuevo.actualizarContenedor();
                    
                    simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    simbolo_contenedor.setIdentificador(id);            
                    simbolo_contenedor.setValor(contenedor_nuevo);
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: la ventana tiene elementos adicionales o los ya existentes son incorrectos.");

                    return nuevo_simbolo;
                }                
                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_contenedor);
                    
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
                            contenedor_nuevo.add((FS_Contenedor)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Caja_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_Caja_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Area_Texto)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_Area_Texto)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Spinner)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_Spinner)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_ComboBox)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_ComboBox)contenido_aux.getValor());
                        }
                        else if(contenido_aux.getValor() instanceof FS_Boton)
                        {
                            lista_componentes.add(contenido_aux);
                            contenedor_nuevo.add((FS_Boton)contenido_aux.getValor());
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: Al Contenedor se le intentan agregar componentes adicionales o incorrectos.");

                            return nuevo_simbolo;
                        }
                    }                            
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                    nuevo_simbolo.setIdentificador(id);            
                    nuevo_simbolo.setValor(contenedor_nuevo);

                    return nuevo_simbolo;
                }   
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: el contenedor " + id + " ya fue creado.");

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
                nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación de Contenedor no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }   
    }
    
}
