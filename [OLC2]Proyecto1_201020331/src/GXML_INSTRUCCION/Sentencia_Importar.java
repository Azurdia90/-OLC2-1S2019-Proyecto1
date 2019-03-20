/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Arreglo;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Importar implements Instruccion
{

    private int fila;
    private int columna;       
    
    private String path;
    
    private ObjetoEntrada entrada;
    
    private File archivo_entrada;
    private BufferedReader bufer_lectura;
    private StringBuffer bufer_cadena;
        
    public Sentencia_Importar(String p_path, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;        
        this.path = p_path.trim();
        this.entrada = null;
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, String padre) 
    {
      try
        {
                        
            if(!(path.substring(path.length() - 2, path.length()).equalsIgnoreCase("fs")|| path.substring(path.length() - 4, path.length()).equalsIgnoreCase("gxml")))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Sentencia importar no fue realizada, error: extesión de archivo no reconocida.");

                return nuevo_simbolo;                
            }

            archivo_entrada = new File(entrada.getPath_archivo() + path);
                        
            if(archivo_entrada.exists())
            {
                bufer_lectura = new BufferedReader(new FileReader(archivo_entrada));
                bufer_cadena = new StringBuffer();
                String texto= "";
                
                while((texto = bufer_lectura.readLine()) != null)
                {                    
                    bufer_cadena.append(texto + "\n");
                }
                String cadena_analizar;
                            
                if (bufer_cadena != null)
                {
                   cadena_analizar = bufer_cadena.toString();
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("La Importación no fue realizada, hubo un problema en la lectura del archivo.");

                    return nuevo_simbolo; 
                }
                
                if(!(cadena_analizar.equals("")))
                {
                    
                    if(path.substring(path.length() - 2, path.length()).equalsIgnoreCase("fs"))
                    {                                                                      
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setIdentificador("10-4");
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setValor("importar(\"" + path + "\");\n");

                        return nuevo_simbolo;
                    }
                    else if(path.substring(path.length() - 4, path.length()).equalsIgnoreCase("gxml"))
                    {
                        ObjetoEntrada salida_importar = new ObjetoEntrada();
                        salida_importar.setJTEntrada(entrada.getJTEntrada());
                        salida_importar.setConsola(entrada.getConsola());
                        salida_importar.setPath_archivo(entrada.getPath_archivo());
                        salida_importar.setNombre_archivo(path.substring(0, path.length()-5));
                        salida_importar.setExtesion_archivo("gxml");
                        
                        GXML_ANALIZADORES.Lexico_GXML lexico_gxml = new GXML_ANALIZADORES.Lexico_GXML(new BufferedReader( new StringReader(cadena_analizar)));
                        GXML_ANALIZADORES.Sintactico_GXML sintactico_gxml = new GXML_ANALIZADORES.Sintactico_GXML(lexico_gxml);
                        sintactico_gxml.setObjetoEntrada(salida_importar);                        
                        sintactico_gxml.setEntornoGlobal(entorno_local);
                        sintactico_gxml.setImportar(true);
                        sintactico_gxml.setTraducir(true);
                        sintactico_gxml.parse();   
                        
                        padre += sintactico_gxml.getSalida();
                        
                        return sintactico_gxml.getSalida();
                    } 
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Sentencia importar no fue realizada, error: extesión de archivo no reconocida.");

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
                    nuevo_simbolo.setValor("La Importación no fue realizada, el archivo no tiene contenido.");

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
                nuevo_simbolo.setValor("La Importación no fue realizada, el archivo \"" + path + "\"  no existe");

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
            if(!(path.substring(path.length() - 2, path.length()).equalsIgnoreCase("fs")|| path.substring(path.length() - 4, path.length()).equalsIgnoreCase("gxml")))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Sentencia importar no fue realizada, error: extesión de archivo no reconocida.");

                return nuevo_simbolo;                
            }
            
            archivo_entrada = new File(salida.getPath_archivo() + path);
            if(archivo_entrada.exists())
            {
                bufer_lectura = new BufferedReader(new FileReader(archivo_entrada));
                bufer_cadena = new StringBuffer();
                String texto= "";
                
                while((texto = bufer_lectura.readLine()) != null)
                {                    
                    bufer_cadena.append(texto + "\n");
                }
                String cadena_analizar;
                            
                if (bufer_cadena != null)
                {
                   cadena_analizar = bufer_cadena.toString();
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("La Importación no fue realizada, hubo un problema en la lectura del archivo.");

                    return nuevo_simbolo; 
                }
                
                if(!(cadena_analizar.equals("")))
                {
                    
                    if(path.substring(path.length() - 2, path.length()).equalsIgnoreCase("fs"))
                    {
                        ObjetoEntrada salida_importar = new ObjetoEntrada();
                        salida_importar.setJTEntrada(salida.getJTEntrada());
                        salida_importar.setConsola(salida.getConsola());
                        salida_importar.setPath_archivo(salida.getPath_archivo());
                        salida_importar.setNombre_archivo(path.substring(path.length() - 2, path.length()));
                        salida_importar.setExtesion_archivo("fs");
                        /*
                        FS_ANALIZADORES.Lexico_FS lexico_fs = new FS_ANALIZADORES.Lexico_FS(new BufferedReader( new StringReader(cadena_analizar)));
                        FS_ANALIZADORES.Sintactico_FS sintactico_fs = new FS_ANALIZADORES.Sintactico_FS(lexico_fs);
                        sintactico_fs.setObjetoEntrada(salida_importar);
                        sintactico_fs.setImportar(true);
                        sintactico_fs.parse();
                        */
                        
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador("10-4");                        
                        nuevo_simbolo.setValor(new FS_Arreglo());
                    
                        return nuevo_simbolo;
                    }
                    else if(path.substring(path.length() - 4, path.length()).equalsIgnoreCase("gxml"))
                    {
                        ObjetoEntrada salida_importar = new ObjetoEntrada();
                        salida_importar.setJTEntrada(salida.getJTEntrada());
                        salida_importar.setConsola(salida.getConsola());
                        salida_importar.setPath_archivo(salida.getPath_archivo());
                        salida_importar.setNombre_archivo(path.substring(0, path.length()- 5));
                        salida_importar.setExtesion_archivo("gxml");
                        
                        GXML_ANALIZADORES.Lexico_GXML lexico_gxml = new GXML_ANALIZADORES.Lexico_GXML(new BufferedReader( new StringReader(cadena_analizar)));
                        GXML_ANALIZADORES.Sintactico_GXML sintactico_gxml = new GXML_ANALIZADORES.Sintactico_GXML(lexico_gxml);
                        sintactico_gxml.setObjetoEntrada(salida_importar);
                        sintactico_gxml.setImportar(true);
                        sintactico_gxml.setTraducir(false);
                        sintactico_gxml.parse();   
                        
                        if(sintactico_gxml.getSalida().getValor() instanceof FS_Arreglo)
                        {
                            lista_componentes.addAll( (FS_Arreglo) sintactico_gxml.getSalida().getValor());
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                            nuevo_simbolo.setValor("Sentencia importar no fue realizada, error: existio un error durante la lectura.");
                                
                            return nuevo_simbolo;
                        }
                        
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.arreglo);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        nuevo_simbolo.setIdentificador("10-4");                        
                        nuevo_simbolo.setValor(lista_componentes);
                    
                        return nuevo_simbolo;
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                        nuevo_simbolo.setValor("Sentencia importar no fue realizada, error: extesión de archivo no reconocida.");

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
                    nuevo_simbolo.setValor("La Importación no fue realizada, el archivo no tiene contenido.");

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
                nuevo_simbolo.setValor("La Importación no fue realizada, el archivo \"" + path + "\"  no existe");

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
    
    public ObjetoEntrada getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjetoEntrada entrada) {
        this.entrada = entrada;
    }    
}

    
