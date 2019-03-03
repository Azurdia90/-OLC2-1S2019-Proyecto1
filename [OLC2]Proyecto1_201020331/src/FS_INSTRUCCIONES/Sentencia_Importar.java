/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
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
    
    File archivo_entrada;
    BufferedReader bufer_lectura;
    StringBuffer bufer_cadena;
    
    private String path;
    
    public Sentencia_Importar(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
                
        this.path = nodo_sentencia.getValor().substring(1, nodo_sentencia.getValor().length() - 1);
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
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
                    FS_ANALIZADORES.Lexico_FS lexico_fs = new FS_ANALIZADORES.Lexico_FS(new BufferedReader( new StringReader(cadena_analizar)));
                    FS_ANALIZADORES.Sintactico_FS sintactico_fs = new FS_ANALIZADORES.Sintactico_FS(lexico_fs);
                    sintactico_fs.setObjetoEntrada(salida);
                    sintactico_fs.setImportar(true);
                    sintactico_fs.parse();   
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador("10-4");
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setValor("La Importación fue realizada.");
                    
                    return nuevo_simbolo; 
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
                nuevo_simbolo.setValor("La Importación no fue realizada, el archivo no existe");

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
            nuevo_simbolo.setValor("La Importación no fue realizada, error: " + e.getMessage());
            
            return nuevo_simbolo; 
        }
    }
    
}
