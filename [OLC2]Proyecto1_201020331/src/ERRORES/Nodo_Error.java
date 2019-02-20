/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ERRORES;

/**
 *
 * @author Cristian Azurdia
 */
public class Nodo_Error 
{
    private String archivo;
    private String identificador;
    private String columna;
    private String linea;
    private String tipo;
    private String descripcion;
    
    public Nodo_Error()
    {
        this.archivo = "";
        this.identificador = "";
        this.linea = "0";
        this.columna = "0";            
        this.tipo = "";
        this.descripcion = "";
    }
    
    public Nodo_Error(String p_archivo, String p_identificador, String p_linea, String p_columna, String p_tipo, String p_descripcion)
    {
        this.archivo = p_archivo;
        this.identificador = p_identificador;
        this.linea = p_linea;
        this.columna = p_columna;            
        this.tipo = p_tipo;
        this.descripcion = p_descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String clase) {
        this.identificador = clase;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
