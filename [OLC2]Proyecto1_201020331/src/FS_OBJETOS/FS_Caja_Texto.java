/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Caja_Texto extends JTextField
{
    private int alto;
    private int ancho;
    private String fuente;
    private int tamaño;
    private Color color;
    private int pos_x;
    private int pos_y;
    private boolean bold;
    private boolean italic;
    private String texto;
    private String identificador;
    
    public FS_Caja_Texto(int p_alto, int p_ancho, String p_fuente, int p_tamaño, Color p_color, int p_pos_x, int p_pos_y, boolean p_bold, boolean p_italic, String p_texto, String p_identificador)
    {
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.fuente = p_fuente;        
        this.tamaño = p_tamaño;
        this.color = p_color;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.bold = p_bold;
        this.italic = p_italic;
        this.texto = p_texto;
        this.identificador = p_identificador;
        
        this.identificador = p_identificador;
        
        this.setBounds(pos_x, pos_y, ancho, alto);
        Font nueva_fuente;
         if(bold && italic)
        {
            nueva_fuente = new Font(fuente, Font.BOLD | Font.ITALIC, tamaño);
        }
        else if( bold & italic == false)
        {
            nueva_fuente = new Font(fuente, Font.BOLD, tamaño);
        }
        else if( italic & bold == false)
        {
            nueva_fuente = new Font(fuente, Font.ITALIC, tamaño);
        }
        else
        {
            nueva_fuente = new Font(fuente, Font.PLAIN, tamaño);
        }
        
        this.setFont(nueva_fuente);
        this.setText(texto);
        this.setVisible(true);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
   
}
