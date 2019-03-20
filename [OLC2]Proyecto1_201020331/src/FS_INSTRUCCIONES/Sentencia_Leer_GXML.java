/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
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
public class Sentencia_Leer_GXML implements Instruccion
{
    
    private int fila;
    private int columna;
    
    private String path;
    
    private File archivo_entrada;
    private BufferedReader bufer_lectura;
    private StringBuffer bufer_cadena;
    
    public Sentencia_Leer_GXML(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.path = nodo_sentencia.getValor().substring(1,nodo_sentencia.getValor().length()-1);
    }
    

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            
            if(!(path.substring(path.length() - 4, path.length()).equalsIgnoreCase("gxml")))
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Sentencia Crear GXML no fue realizada, error: extesión de archivo no reconocida.");

                return nuevo_simbolo;                
            }
            
            archivo_entrada = new File(path);
                        
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
                    ObjetoEntrada salida_importar = new ObjetoEntrada();
                    salida_importar.setJTEntrada(salida.getJTEntrada());
                    salida_importar.setPath_archivo(archivo_entrada.getAbsolutePath().substring(0, archivo_entrada.getAbsolutePath().length() - archivo_entrada.getName().length())); 
                    salida_importar.setNombre_archivo(archivo_entrada.getName().substring(0, archivo_entrada.getName().length() - 5));                
                    salida_importar.setExtesion_archivo(archivo_entrada.getName().substring(archivo_entrada.getName().length() - 4, archivo_entrada.getName().length()));   

                    GXML_ANALIZADORES.Lexico_GXML lexico_gxml = new GXML_ANALIZADORES.Lexico_GXML(new BufferedReader( new StringReader(cadena_analizar)));
                    GXML_ANALIZADORES.Sintactico_GXML sintactico_gxml = new GXML_ANALIZADORES.Sintactico_GXML(lexico_gxml);
                    sintactico_gxml.setObjetoEntrada(salida_importar);                        
                    sintactico_gxml.setEntornoGlobal(entorno_local);
                    sintactico_gxml.setImportar(false);
                    sintactico_gxml.setTraducir(false);
                    sintactico_gxml.parse();   

                    if(sintactico_gxml.getSalida() != null && sintactico_gxml.getSalida().getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                    {
                        FS_Arreglo arreglo_final  = (FS_Arreglo) sintactico_gxml.getSalida().getValor();
                        arreglo_final.setHomogeneo(true);
                        arreglo_final.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                        return sintactico_gxml.getSalida();                        
                    }
                    else 
                    {
                        return sintactico_gxml.getSalida();
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
            nuevo_simbolo.setValor("Sentencia Crear GXML no realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }
    }
    
}
