/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Ventana extends JFrame
{
    private String identificador;
    private boolean tipo;
    private int alto;
    private int ancho;
    
    private Color color;
    
    
    //CONSTRUCTOR PARA FS
    public FS_Ventana(String p_id, Integer p_alto, Integer p_ancho, Color p_color)
    {        
        this.identificador = p_id;
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.color = p_color;
        
        this.setTitle(identificador);
        this.setSize(alto, ancho);     
        this.setBackground(color);       
        this.setVisible(false);  
    }
    //CONSTRUCTOR PARA FS
    public FS_Ventana(String p_id, Boolean p_tipo)
    {
        this.identificador = p_id;
        this.tipo = p_tipo;
        this.alto = 700;
        this.ancho =700;
        
        this.setTitle(identificador);
        this.setSize(alto, ancho);
        this.setVisible(false);   
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }        
    
}
