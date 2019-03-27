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
public class Sentencia_Controlador implements Instruccion
{
    private int fila;
    private int columna;
    
    //principales
    private String id;
    private String tipo;
    private int posx;
    private int posy;
    private String defecto;
    
    //opcionales    
    private int ancho;
    private int alto;
    private Color color;    
    private String fuente;
    private int tamaño;
    private boolean negrita;
    private boolean cursiva;    
    private int maximo;     //solo para numero
    private int minimo;     //solo para numero
    
    private ArrayList<GXML_Elemento> lista_elementos;
    private ArrayList<GXML_Elemento> lista_contenido;
    
    //CONSTRUCTOR PARA VENTANA SIN NADA DE CONTENIDO
    public Sentencia_Controlador(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.defecto = "";
        this.lista_elementos = p_lista_elementos;
        this.lista_contenido = new ArrayList<GXML_Elemento>();   
    }
    
    //CONSTRUCTOR PARA VENTANA CON CONTENIDO
    public Sentencia_Controlador(ArrayList<GXML_Elemento> p_lista_elementos, ArrayList<GXML_Elemento> p_lista_contenido, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
               
        this.lista_elementos = p_lista_elementos;     
        this.lista_contenido = p_lista_contenido;     
    }

    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 4;
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
            else if(lista_elementos.get(i).IsElementOrNot("tipo") && principal > 0)
            {                
                if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("texto"))
                {
                    this.tipo = "texto";
                }
                else if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("numerico"))
                {
                    this.tipo = "numerico";
                }
                else if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("textoarea"))
                {
                    this.tipo = "textoarea";
                }
                else if(lista_elementos.get(i).getValor().toString().equalsIgnoreCase("desplegable"))
                {
                    this.tipo = "desplegable";
                }
                else
                {
                    this.tipo = "texto";
                }
                
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
        
        if(controlador_nuevo instanceof FS_Caja_Texto)
        {
            principal = 7;
        }
        else if(controlador_nuevo instanceof FS_Area_Texto)
        {
            principal = 7;            
        }
        else if(controlador_nuevo instanceof FS_Spinner)
        {
            principal = 4;
        }
        else if(controlador_nuevo instanceof FS_ComboBox)
        {
            principal = 4;
        }
        
        for(int i = 0; i < lista_elementos.size(); i++)
        {            
            if(lista_elementos.get(i).IsElementOrNot("alto") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   ((FS_Spinner) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   ((FS_ComboBox) controlador_nuevo).setAlto((Integer) lista_elementos.get(i).getValor());
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
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   ((FS_Spinner) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   ((FS_ComboBox) controlador_nuevo).setAncho((Integer) lista_elementos.get(i).getValor());
                }           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("minimo") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   ((FS_Spinner) controlador_nuevo).setMinimo((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            } 
            else if(lista_elementos.get(i).IsElementOrNot("maximo") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                    return false;
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   ((FS_Spinner) controlador_nuevo).setMaximo((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }           
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
                    if(controlador_nuevo instanceof FS_Caja_Texto)
                    {
                       ((FS_Caja_Texto) controlador_nuevo).setForeground(color);
                    }
                    else if(controlador_nuevo instanceof FS_Area_Texto)
                    {
                       ((FS_Area_Texto) controlador_nuevo).setForeground(color);
                    }
                    else if(controlador_nuevo instanceof FS_Spinner)
                    {
                       return false;
                    }
                    else if(controlador_nuevo instanceof FS_ComboBox)
                    {
                       return false;
                    }   
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
            else if(lista_elementos.get(i).IsElementOrNot("fuente") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setFuente(lista_elementos.get(i).getValor().toString());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setFuente(lista_elementos.get(i).getValor().toString());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("tam") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setTamaño((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setTamaño((Integer) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }           
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            } 
            else if(lista_elementos.get(i).IsElementOrNot("negrita") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setBold((Boolean) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setBold((Boolean) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }                 
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("cursiva") && principal > 0)
            {
                if(controlador_nuevo instanceof FS_Caja_Texto)
                {
                   ((FS_Caja_Texto) controlador_nuevo).setItalic((Boolean) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Area_Texto)
                {
                   ((FS_Area_Texto) controlador_nuevo).setItalic((Boolean) lista_elementos.get(i).getValor());
                }
                else if(controlador_nuevo instanceof FS_Spinner)
                {
                   return false;
                }
                else if(controlador_nuevo instanceof FS_ComboBox)
                {
                   return false;
                }               
                completo = true;
                principal--;                
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(!(lista_elementos.get(i).IsElementOrNot("nombre") || lista_elementos.get(i).IsElementOrNot("tipo") || lista_elementos.get(i).IsElementOrNot("x") || lista_elementos.get(i).IsElementOrNot("y")))
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
                FS_Caja_Texto textocaja_nueva;
                FS_Area_Texto textoarea_nueva;
                FS_Spinner    spinner_nuevo;
                FS_ComboBox   combo_nuevo;
                
                String traduccion = "";
                
                Simbolo simbolo_contenedor = new Simbolo();
                simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                simbolo_contenedor.setIdentificador(id);   
                
                if(tipo.equalsIgnoreCase("texto"))
                {
                    textocaja_nueva = new FS_Caja_Texto(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            textocaja_nueva.setText(lista_contenido.get(i).getValor().toString());
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: la etiqueta control de Textos no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(textocaja_nueva))
                    {
                        traduccion += textocaja_nueva.traducirCajaTexto(padre);
                        simbolo_contenedor.setValor(textocaja_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: El Controlador Texto tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                    
                    if(!entorno_local.containsKey(id))
                    {                                                    
                        entorno_local.Crear(id, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    } 
                }
                else if( tipo.equalsIgnoreCase("textoarea"))
                {
                    textoarea_nueva = new FS_Area_Texto(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            textoarea_nueva.setText(lista_contenido.get(i).getValor().toString());
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador TExto Area no fue realizada, error: la etiqueta control de Textos no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(textoarea_nueva))
                    {
                        traduccion += textoarea_nueva.traducirAreaTexto(padre);
                        simbolo_contenedor.setValor(textoarea_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: El Controlador Texto tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }                                        
                    
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación del Controlador TExto Area no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("numerico"))
                {
                    spinner_nuevo = new FS_Spinner(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            spinner_nuevo.setDefecto(Integer.parseInt(lista_contenido.get(i).getValor().toString()));
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: la etiqueta controlador numerico no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(spinner_nuevo))
                    {
                        traduccion += spinner_nuevo.traducirSpinner(padre);
                        simbolo_contenedor.setValor(spinner_nuevo);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: El Controlador Numerico tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación del Controlador Númerico no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("desplegable"))
                {
                    combo_nuevo = new FS_ComboBox(id,posx,posy);
                    FS_Arreglo arreglo_aux = new FS_Arreglo();
                    Simbolo simbolo_defecto = null;
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            simbolo_defecto = new Simbolo();
                            simbolo_defecto.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            simbolo_defecto.setRol(Tabla_Enums.tipo_Simbolo.variable);
                            simbolo_defecto.setTipo(lista_contenido.get(i).getTipo_elemento());
                            simbolo_defecto.setIdentificador("10-4");            
                            simbolo_defecto.setValor(lista_contenido.get(i).getValor());                            
                        }
                        else
                        {
                            Simbolo simbolo_lista = new Simbolo();
                            simbolo_lista.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            simbolo_lista.setRol(Tabla_Enums.tipo_Simbolo.variable);
                            simbolo_lista.setTipo(lista_contenido.get(i).getTipo_elemento());
                            simbolo_lista.setIdentificador("10-4");            
                            simbolo_lista.setValor(lista_contenido.get(i).getValor());
                            arreglo_aux.add(simbolo_lista);
                        }                        
                    }
                    
                    if(cargar_opcionales(combo_nuevo))
                    {
                        combo_nuevo.actualizarComboBox();
                        combo_nuevo.setListadatos(arreglo_aux);                        
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: El Controlador Numerico tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(simbolo_defecto != null)
                    {
                        combo_nuevo.setDefecto(simbolo_defecto.getValor().toString());
                    }                    
                    
                    combo_nuevo.cargarDatos(); 
                    traduccion += combo_nuevo.traducirComboBox(padre);
                    simbolo_contenedor.setValor(combo_nuevo);
                    
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

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
                        nuevo_simbolo.setValor("Creación del Controlador Desplegable no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

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
                    nuevo_simbolo.setValor("Creación del controlador no fue realizada, error: el tipo ingresado no existe para este componente.");

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
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, FS_Arreglo lista_componentes, ObjetoEntrada salida) 
    {
        try
        {
            boolean continuar = cargar_principales();
            if(continuar)
            {
                FS_Caja_Texto textocaja_nueva;
                FS_Area_Texto textoarea_nueva;
                FS_Spinner    spinner_nuevo;
                FS_ComboBox   combo_nuevo;
                
                Simbolo simbolo_contenedor = new Simbolo();
                simbolo_contenedor.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                simbolo_contenedor.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                simbolo_contenedor.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                simbolo_contenedor.setIdentificador(id);   
                
                if(tipo.equalsIgnoreCase("texto"))
                {
                    textocaja_nueva = new FS_Caja_Texto(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            textocaja_nueva.setText(lista_contenido.get(i).getValor().toString());
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: la etiqueta control de Textos no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(textocaja_nueva))
                    {
                        textocaja_nueva.actualizarCajaTexto();
                        simbolo_contenedor.setValor(textocaja_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: El Controlador Texto tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                    
                    if(!entorno_local.containsKey(id))
                    {                                                    
                        entorno_local.Crear(id, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");            
                        nuevo_simbolo.setValor(textocaja_nueva);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    } 
                }
                else if( tipo.equalsIgnoreCase("textoarea"))
                {
                    textoarea_nueva = new FS_Area_Texto(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            textoarea_nueva.setText(lista_contenido.get(i).getValor().toString());
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador TExto Area no fue realizada, error: la etiqueta control de Textos no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(textoarea_nueva))
                    {
                        textoarea_nueva.actualizarAreaTexto();
                        simbolo_contenedor.setValor(textoarea_nueva);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Texto no fue realizada, error: El Controlador Texto tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }                                        
                    
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador(id);            
                        nuevo_simbolo.setValor(textoarea_nueva);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador TExto Area no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("numerico"))
                {
                    spinner_nuevo = new FS_Spinner(id,posx,posy);
                    
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            spinner_nuevo.setDefecto(Integer.parseInt(lista_contenido.get(i).getValor().toString()));
                            break;
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: la etiqueta controlador numerico no puede tener lista datos como contenido.");

                            return nuevo_simbolo;
                        }
                    }
                    
                    if(cargar_opcionales(spinner_nuevo))
                    {
                        spinner_nuevo.actualizarSpinner();
                        simbolo_contenedor.setValor(spinner_nuevo);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: El Controlador Numerico tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador(id);            
                        nuevo_simbolo.setValor(spinner_nuevo);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Númerico no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

                        return nuevo_simbolo;
                    }  
                }
                else if( tipo.equalsIgnoreCase("desplegable"))
                {
                    combo_nuevo = new FS_ComboBox(id,posx,posy);
                    FS_Arreglo arreglo_aux = new FS_Arreglo();
                    Simbolo simbolo_defecto = null;
                    for(int i = 0; i < lista_contenido.size(); i++)
                    {
                        if(lista_contenido.get(i).IsElementOrNot("defecto"))
                        {
                            simbolo_defecto = new Simbolo();
                            simbolo_defecto.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            simbolo_defecto.setRol(Tabla_Enums.tipo_Simbolo.variable);
                            simbolo_defecto.setTipo(lista_contenido.get(i).getTipo_elemento());
                            simbolo_defecto.setIdentificador("10-4");            
                            simbolo_defecto.setValor(lista_contenido.get(i).getValor());                            
                        }
                        else
                        {
                            Simbolo simbolo_lista = new Simbolo();
                            simbolo_lista.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            simbolo_lista.setRol(Tabla_Enums.tipo_Simbolo.variable);
                            simbolo_lista.setTipo(lista_contenido.get(i).getTipo_elemento());
                            simbolo_lista.setIdentificador("10-4");            
                            simbolo_lista.setValor(lista_contenido.get(i).getValor());
                            arreglo_aux.add(simbolo_lista);
                        }                        
                    }
                    
                    if(cargar_opcionales(combo_nuevo))
                    {
                        combo_nuevo.actualizarComboBox();
                        combo_nuevo.setListadatos(arreglo_aux);                        
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Numerico no fue realizada, error: El Controlador Numerico tiene elementos adicionales o los ya existentes son incorrectos.");

                        return nuevo_simbolo;  
                    }
                                        
                    if(simbolo_defecto != null)
                    {
                        combo_nuevo.setSelectedItem(defecto);
                    }                    
                    
                    combo_nuevo.cargarDatos();                    
                    simbolo_contenedor.setValor(combo_nuevo);
                    
                    if(!entorno_local.containsKey(id))
                    {
                        entorno_local.Crear(id, simbolo_contenedor);

                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador(id);            
                        nuevo_simbolo.setValor(combo_nuevo);

                        return nuevo_simbolo;
                    }   
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Creación del Controlador Desplegable no fue realizada, error: la etiqueta control " + id + " ya fue creada.");

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
                    nuevo_simbolo.setValor("Creación del controlador no fue realizada, error: el tipo ingresado no existe para este componente.");

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
