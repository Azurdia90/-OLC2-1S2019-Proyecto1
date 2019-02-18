/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JTextPane;

/**
 *
 * @author Cristian Azurdia
 */
public class ObjetoEntrada 
{    
    private JTextPane JTEntrada;
    private JTextPane consola;        
    
    private String path_archivo;
    private String nombre_archivo;
    private String extesion_archivo;
    
    public ObjetoEntrada()
    {
        this.JTEntrada = new JTextPane();        
        this.consola = null;
        
        this.path_archivo = "";
        this.nombre_archivo = "";
        this.extesion_archivo = "";        
    }

    public JTextPane getJTEntrada() {
        return JTEntrada;
    }

    public void setJTEntrada(JTextPane JTEntrada) {
        this.JTEntrada = JTEntrada;
    }

    public JTextPane getConsola() {
        return consola;
    }

    public void setConsola(JTextPane consola) {
        this.consola = consola;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getExtesion_archivo() {
        return extesion_archivo;
    }

    public void setExtesion_archivo(String extesion_archivo) {
        this.extesion_archivo = extesion_archivo;
    }            
}
