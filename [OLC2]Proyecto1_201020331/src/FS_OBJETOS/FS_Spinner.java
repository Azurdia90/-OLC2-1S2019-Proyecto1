/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Spinner extends JSpinner
{
    private String id;
    private int pos_x;
    private int pos_y;
    private int ancho;
    private int alto;
    private int defecto;

    private int minimo;
    private int maximo;
    
    private SpinnerNumberModel nueva_fuente;
    
    //CONTRUCTOR DE FS
    public FS_Spinner(int p_alto, int p_ancho, int p_minimo, int p_maximo, int p_posx, int p_posy, int p_defecto, String p_id)
    {
        this.id = p_id;
        this.pos_x = p_posx;
        this.pos_y = p_posy;
        
        this.ancho = p_ancho;
        this.alto = p_alto;
        
        this.defecto = p_defecto;        
        this.minimo = p_minimo;
        this.maximo = p_maximo;
                
        this.nueva_fuente = new SpinnerNumberModel(defecto,minimo,maximo,1);
        this.setModel(nueva_fuente);
        this.setBounds(pos_x, pos_y, ancho, alto);                
        this.setVisible(true);
        this.repaint();
    }
        
    //CONTRUCTOR DE GXML
    public FS_Spinner(String p_id, int p_posx, int p_posy)
    {
        this.id = p_id;
        this.pos_x = p_posx;
        this.pos_y = p_posy;
        
        this.ancho = 300;
        this.alto = 100;        
        
        this.defecto = 0;
        this.minimo = 0;
        this.maximo = 1000;
        
        this.nueva_fuente = new SpinnerNumberModel(defecto,minimo,maximo,1);
        this.setModel(nueva_fuente);
        this.setBounds(pos_x, pos_y, ancho, alto);       
        this.setVisible(true);
        this.repaint();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getDefecto() {
        return defecto;
    }

    public void setDefecto(int defecto) {
        this.defecto = defecto;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public SpinnerNumberModel getNueva_fuente() {
        return nueva_fuente;
    }

    public void setNueva_fuente(SpinnerNumberModel nueva_fuente) {
        this.nueva_fuente = nueva_fuente;
    }
        
    public void actualizarSpinner()
    {                        
        this.nueva_fuente = new SpinnerNumberModel(defecto,minimo,maximo,1);
        this.setModel(nueva_fuente);
        this.setBounds(pos_x, pos_y, ancho, alto);
        this.repaint();        
    }
    
    public String traducirSpinner(String padre)
    {
        return  padre + ".CrearControlNumerico(" + alto + ", " + ancho + ", " + maximo + ", " + minimo + ", " + pos_x + ", " + pos_y + ", " + defecto +  ", \"" + id  + "\"); \n";
    }    
}
