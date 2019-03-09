/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Boton extends JButton 
{
    private String fuente;
    private int tamaño;
    private Color color;
    private int pos_x;
    private int pos_y;
    private String identificador;
    private String texto;
    private int alto;
    private int ancho;
    
    public FS_Boton(String p_fuente, int p_tamaño, Color p_color, int p_pos_x, int p_pos_y, String p_id, String p_texto, int p_alto, int p_ancho)
    {
        this.fuente = p_fuente;
        this.tamaño = p_tamaño;
        this.color = p_color;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.identificador = p_id;
        this.texto = p_texto;
        this.alto = p_alto;
        this.ancho = p_ancho;
        
        Font nueva_fuente;
        nueva_fuente = new Font(fuente, Font.PLAIN, tamaño);
        
        this.setFont(nueva_fuente);
        this.setText(texto);              
        this.setBounds(pos_x, pos_y, ancho, alto);
        this.setVisible(true);
    }
    
}
