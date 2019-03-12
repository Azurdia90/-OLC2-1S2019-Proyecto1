/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Importar implements Instruccion
{

    private int fila;
    private int columna;       
    
    private String path;
    
    public Sentencia_Importar(String p_path, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.path = p_path;
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setIdentificador("10-4");            
            nuevo_simbolo.setValor("Creación de Ventana fue realizada exitosamente.");

            return nuevo_simbolo;
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
