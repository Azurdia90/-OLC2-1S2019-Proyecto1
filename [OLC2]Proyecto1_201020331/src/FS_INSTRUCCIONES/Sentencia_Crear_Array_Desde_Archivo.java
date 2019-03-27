/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Crear_Array_Desde_Archivo implements Instruccion
{
    private int fila;
    private int columna;
    
    private String ventana;
    private String path;
    private boolean lectura;
    
    private FileWriter archivo_gdato;
    private PrintWriter impresion_gdato;  
    
    public Sentencia_Crear_Array_Desde_Archivo(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());    
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        archivo_gdato = null;
        impresion_gdato = null;  
        
        if(lectura)
        {
            this.ventana =  "";
            this.path = nodo_sentencia.getHijos().get(0).getValor().substring(0, nodo_sentencia.getHijos().get(0).getValor().length() - 1);
        }
        else
        {
            this.ventana = nodo_sentencia.getValor().toString();
            this.path = "";
        }        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            if(lectura)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo fue realizada exitosamente.");

                return nuevo_simbolo;
            }
            else
            {
                Simbolo ventana_obtenida;
                ventana_obtenida = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().obtener_Simbolo(entorno_local,ventana);
                
                if(! (ventana_obtenida.getValor() instanceof FS_Ventana))
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setValor("Este evento es compatible únicamente con Ventanas.");    
                    return nuevo_simbolo;
                }
                
                FS_Ventana ventana_imprimir = (FS_Ventana) ventana_obtenida.getValor();
                      
                String[] ventana_desglosada = ventana.split("_");
                String ventana_real = ventana_desglosada[1];
                
                boolean primera_vez = false;
                        
                File buscar_archivo = new File((salida.getPath_archivo() + ventana_real + ".gdato"));
                
                if(!buscar_archivo.exists())
                {
                    primera_vez = true;
                }
                
                archivo_gdato = new FileWriter((salida.getPath_archivo() + ventana_real + ".gdato"), true);
                impresion_gdato = new PrintWriter(archivo_gdato);
                
                ArrayList<Object> lista_componentes = ventana_imprimir.getLista_componentes();
                
                if(primera_vez)
                {
                    impresion_gdato.println("<lista>");
                    impresion_gdato.println("");
                    impresion_gdato.println("");
                }
                                                   
                impresion_gdato.println("    <" + (ventana_imprimir.getTipo() == true ? "principal" : "secundaria") + ">");
                for(int x = 0; x < lista_componentes.size(); x++)
                {                                        
                    
                    if(lista_componentes.get(x) instanceof FS_Contenedor)
                    {
                        FS_Contenedor contenedor_imprimir = (FS_Contenedor) lista_componentes.get(x);
                        ArrayList<Object> lista_componentes_contenedor = contenedor_imprimir.getLista_componentes();
                        
                        for(int y = 0; y < lista_componentes_contenedor.size(); y++)
                        {
                            if(lista_componentes_contenedor.get(y) instanceof FS_Area_Texto)
                            {
                               FS_Area_Texto area_texto_imprimir = (FS_Area_Texto)lista_componentes_contenedor.get(y);
                               impresion_gdato.println("        <" + area_texto_imprimir.getId() + ">\"" + area_texto_imprimir.getText() +  "\"</" + area_texto_imprimir.getId() + ">" );                        
                            }
                            else if(lista_componentes_contenedor.get(y) instanceof FS_Caja_Texto)
                            {
                               FS_Caja_Texto caja_texto_imprimir = (FS_Caja_Texto)lista_componentes_contenedor.get(y);
                               impresion_gdato.println("        <" + caja_texto_imprimir.getId() + ">\"" + caja_texto_imprimir.getText() +  "\"</" + caja_texto_imprimir.getId() + ">" );                        
                            }
                            else if(lista_componentes_contenedor.get(y) instanceof FS_Spinner)
                            {
                               FS_Spinner spinner_imprimir = (FS_Spinner)lista_componentes_contenedor.get(y);
                               impresion_gdato.println("        <" + spinner_imprimir.getId() + ">" + spinner_imprimir.getValue().toString() +  "</" + spinner_imprimir.getId() + ">" );                        
                            }
                            else if(lista_componentes_contenedor.get(y) instanceof FS_ComboBox)
                            {
                               FS_ComboBox combobox_imprimir = (FS_ComboBox)lista_componentes_contenedor.get(y);
                               impresion_gdato.println("        <" + combobox_imprimir.getId() + ">\"" + combobox_imprimir.getSelectedItem().toString() +  "\"</" + combobox_imprimir.getId() + ">" );                        
                            }   
                        }
                    }
                    else if(lista_componentes.get(x) instanceof FS_Area_Texto)
                    {
                        FS_Area_Texto area_texto_imprimir = (FS_Area_Texto)lista_componentes.get(x);
                        impresion_gdato.println("        <" + area_texto_imprimir.getId() + ">\"" + area_texto_imprimir.getText() +  "\"</" + area_texto_imprimir.getId() + ">" );                        
                    }
                    else if(lista_componentes.get(x) instanceof FS_Caja_Texto)
                    {
                        FS_Caja_Texto caja_texto_imprimir = (FS_Caja_Texto)lista_componentes.get(x);
                        impresion_gdato.println("        <" + caja_texto_imprimir.getId() + ">\"" + caja_texto_imprimir.getText() +  "\"</" + caja_texto_imprimir.getId() + ">" );                        
                    }
                    else if(lista_componentes.get(x) instanceof FS_Spinner)
                    {
                        FS_Spinner spinner_imprimir = (FS_Spinner)lista_componentes.get(x);
                        impresion_gdato.println("        <" + spinner_imprimir.getId() + ">" + spinner_imprimir.getValue().toString() +  "</" + spinner_imprimir.getId() + ">" );                        
                    }
                    else if(lista_componentes.get(x) instanceof FS_ComboBox)
                    {
                        FS_ComboBox combobox_imprimir = (FS_ComboBox)lista_componentes.get(x);
                        impresion_gdato.println("        <" + combobox_imprimir.getId() + ">\"" + combobox_imprimir.getSelectedItem().toString() +  "\"</" + combobox_imprimir.getId() + ">" );                        
                    }                    
                }                
                impresion_gdato.println("    </" + (ventana_imprimir.getTipo() == true ? "principal" : "secundaria") + ">");                
                impresion_gdato.println("");
                impresion_gdato.println("");
                impresion_gdato.println("</lista>");  
                impresion_gdato.close();
                
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo fue realizada exitosamente.");

                return nuevo_simbolo;
            }         
        }
        catch (Exception e)
        {
            impresion_gdato.close();
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Crear Array Desde Archivo no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;                      
        }
    }    
}
