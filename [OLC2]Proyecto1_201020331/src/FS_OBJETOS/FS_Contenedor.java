/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Contenedor extends JPanel
{
    private int alto;
    private int ancho;
    private Color color;
    private int pos_x;
    private int pos_y;
    
    private boolean borde;
    
    public FS_Contenedor(int p_alto, int p_ancho, Color p_color, boolean p_borde, int p_pos_x, int p_pos_y)
    {
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.color = p_color;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        
        this.setSize(alto,ancho);
        this.setBackground(color);
        this.setLocation(pos_x,pos_y);
        if(borde)
        {
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }        
    }
}
