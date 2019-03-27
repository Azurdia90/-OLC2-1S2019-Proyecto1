/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Imagen;
import FS_OBJETOS.FS_Musica;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Video;
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
public class Sentencia_Multimedia implements Instruccion
{
    private int fila;
    private int columna;
    
    //principales
    private String nombre;
    private String tipo;
    private String path;
    private int posx;
    private int posy;
    
    //opcionales    
    private int ancho;
    private int alto;
    private boolean autoreproduccion;
    private boolean cursiva; 
    
    private ArrayList<GXML_Elemento> lista_elementos;
    
    private ObjetoEntrada salida;
    
    public Sentencia_Multimedia(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna, ObjetoEntrada p_salida)
    {
        this.fila = p_fila;
        this.columna = p_columna;
       
        this.lista_elementos = p_lista_elementos;
        this.salida = p_salida;
    }
    
    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 5;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("nombre") && principal > 0)
            {
                this.nombre = lista_elementos.get(i).getValor().toString();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("tipo") && principal > 0)
            {                
                if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("imagen"))
                {
                    this.tipo = "imagen";
                }
                else if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("musica"))
                {
                    this.tipo = "musica";
                }
                else if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("video"))
                {
                    this.tipo = "video";
                }
                else
                {
                    this.tipo = "imagen";
                }
                
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("path") && principal > 0)
            {
                this.path = lista_elementos.get(i).getValor().toString();
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
    
        private boolean cargar_opcionales(Object controlador_nuevo)
    {
        boolean completo = true;
        int principal = 0;
        
        if(controlador_nuevo instanceof FS_Imagen)
        {
            principal = 2;
        }
        else if(controlador_nuevo instanceof FS_Musica)
        {
            principal = 3;            
        }
        else if(controlador_nuevo instanceof FS_Video)
        {
            principal = 3;
        }        
        
        for(int i = 0; i < lista_elementos.size(); i++)
        {            
            if(lista_elementos.get(i).IsElementOrNot("alto") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Imagen)
                {
                   ((FS_Imagen) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Musica)
                {
                   ((FS_Musica) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Video)
                {
                   ((FS_Video) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("ancho") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Imagen)
                {
                   ((FS_Imagen) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Musica)
                {
                   ((FS_Musica) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Video)
                {
                   ((FS_Video) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }            
            else if(lista_elementos.get(i).IsElementOrNot("auto-reproduccion") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Imagen)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_Musica)
                {
                   ((FS_Musica) controlador_nuevo).setAutoreproduccion((Boolean) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Video)
                {
                   ((FS_Video) controlador_nuevo).setAutoreproduccion((Boolean) lista_elementos.get(i).getValor());
                }                 
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(!(lista_elementos.get(i).IsElementOrNot("nombre") || lista_elementos.get(i).IsElementOrNot("tipo") || lista_elementos.get(i).IsElementOrNot("x") || lista_elementos.get(i).IsElementOrNot("y") || lista_elementos.get(i).IsElementOrNot("path")))
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
                FS_Imagen imagen_nueva;
                FS_Musica musica_nueva;
                FS_Video  video_nuevo;
                
                String traduccion = "";
                
                Simbolo simbolo_contenedor = new Simbolo();
                simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                simbolo_contenedor.setIdentificador(nombre);   
                
                if(tipo.equalsIgnoreCase("imagen"))
                {
                    imagen_nueva = new FS_Imagen(nombre, path, posx,posy,salida);
                                        
                    if(cargar_opcionales(imagen_nueva))
                    {
                        traduccion += imagen_nueva.traducirImagen(padre);
                        simbolo_contenedor.setValor(imagen_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación de Multimedia no fue realizada, error: La imagen tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                    
                    if(!entorno_local.containsKey(nombre))
                    {                                                    
                        entorno_local.Crear(nombre, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la etiqueta imagen " + nombre + " ya fue creada.");

                        return nuevo_simbolo;
                    } 
                }
                else if( tipo.equalsIgnoreCase("musica"))
                {
                    musica_nueva = new FS_Musica(nombre,path,posx,posy,salida);                    
                    
                    if(cargar_opcionales(musica_nueva))
                    {
                        traduccion += musica_nueva.traducirMusica(padre);
                        simbolo_contenedor.setValor(musica_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: La etiqueta musica tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }                                        
                    
                    if(!entorno_local.containsKey(nombre))
                    {
                        entorno_local.Crear(nombre, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la etiqueta musica " + nombre + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("video"))
                {
                    video_nuevo = new FS_Video(nombre, path,posx,posy);                    
                    
                    if(cargar_opcionales(video_nuevo))
                    {
                        traduccion += video_nuevo.traducirVideo(padre);
                        simbolo_contenedor.setValor(video_nuevo);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: El video tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(!entorno_local.containsKey(nombre))
                    {
                        entorno_local.Crear(nombre, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la etiqueta video " + nombre + " ya fue creada.");

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
                    nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: el tipo ingresado no existe para este componente.");

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
                nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: " + e.getMessage());

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
                FS_Imagen imagen_nueva;
                FS_Musica musica_nueva;
                FS_Video  video_nuevo;
                
                Simbolo simbolo_contenedor = new Simbolo();
                simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                simbolo_contenedor.setIdentificador(nombre);   
                
                if(tipo.equalsIgnoreCase("imagen"))
                {
                    imagen_nueva = new FS_Imagen(nombre,path,posx,posy,salida);
                                        
                    if(cargar_opcionales(imagen_nueva))
                    {
                        simbolo_contenedor.setValor(imagen_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Multimedia no fue realizada, error: La imagen tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                    
                    if(!entorno_local.containsKey(nombre))
                    {                                                    
                        entorno_local.Crear(nombre, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");            
                        nuevo_simbolo.setValor(imagen_nueva);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la imagen " + nombre + " ya fue creada.");

                        return nuevo_simbolo;
                    } 
                }
                else if( tipo.equalsIgnoreCase("musica"))
                {
                    musica_nueva = new FS_Musica(nombre,path,posx,posy,salida);
                                        
                    if(cargar_opcionales(musica_nueva))
                    {
                        simbolo_contenedor.setValor(musica_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: Música tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }                                        
                    
                    if(!entorno_local.containsKey(nombre))
                    {
                        entorno_local.Crear(nombre, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador(nombre);            
                        nuevo_simbolo.setValor(musica_nueva);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la etiqueta musica  " + nombre + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("video"))
                {
                    video_nuevo = new FS_Video(nombre,path,posx,posy);
                                        
                    if(cargar_opcionales(video_nuevo))
                    {
                        simbolo_contenedor.setValor(video_nuevo);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Video no fue realizado, error: El Controlador Video tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(!entorno_local.containsKey(nombre))
                    {
                        entorno_local.Crear(nombre, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador(nombre);            
                        nuevo_simbolo.setValor(video_nuevo);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: la etiqueta video " + nombre + " ya fue creada.");

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
                    nuevo_simbolo.setValor("Creación Multimedia no fue realizada, error: el tipo ingresado no existe para este componente.");

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
                nuevo_simbolo.setValor("Creación del Controlador no fue realizada, error: no cuenta con los elementos principales para su creación.");

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
            nuevo_simbolo.setValor("Creación del Controlador no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }
    
}
