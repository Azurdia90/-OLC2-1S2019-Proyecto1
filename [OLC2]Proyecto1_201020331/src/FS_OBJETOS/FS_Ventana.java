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
    
    private int pos_x;
    private int pos_y;
    
    private Color color;
    
    public FS_Ventana(String p_id, Integer p_x, Integer p_y, Color p_color)
    {        
        this.identificador = p_id;
        this.pos_x = p_x;
        this.pos_y = p_y;
        this.color = p_color;
        
        this.setTitle(identificador);
        this.setSize(pos_x, pos_y);
        this.setLocationRelativeTo(null);       
        this.setBackground(color);       
        this.setVisible(false);  
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
            
}
