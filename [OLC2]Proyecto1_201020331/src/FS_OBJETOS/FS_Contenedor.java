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
    private String id;
    private int alto;
    private int ancho;
    private Color color;
    private int pos_x;
    private int pos_y;
    
    private boolean borde;
    
    //CONSTRUCTOR PARA FS
    public FS_Contenedor(int p_alto, int p_ancho, Color p_color, boolean p_borde, int p_pos_x, int p_pos_y)
    {
        this.id = ""; 
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.ancho = p_ancho;
        this.alto = p_alto;
                                
        this.color = p_color;
                
        this.setBounds(pos_x,pos_y,ancho,alto);
        this.setBackground(color);        
        
        if(borde)
        {
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }        
        
        this.setVisible(true);
        this.repaint();
    }
    
    //CONSTRUCTOR PARA GXML
    public FS_Contenedor(String p_id, int p_pos_x, int p_pos_y)
    {
        this.id = p_id;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.alto = 300;
        this.ancho = 300;
        
        this.color = Color.WHITE;
         
        this.setBounds(pos_x,pos_y,ancho,alto);
        this.setBackground(color);
        this.setVisible(true);
        this.repaint();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isBorde() {
        return borde;
    }

    public void setBorde(boolean borde) {
        this.borde = borde;
    }
    
    public void actualizarContenedor()
    {
        this.setBounds(pos_x,pos_y,ancho,alto);
        this.setBackground(color);        
        
        if(borde)
        {
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }        
        
        this.setVisible(true);
        this.repaint();
    }
    
    public String traducirContenedor(String padre)
    {        
        return "var " + padre + "_cont_" + id + " = " + padre + ".CrearContenedor(" + alto + ", " + ancho + ", \"" +  traducirColor() + "\", " + (borde == true ? "verdadero" : "falso") + ", " + pos_x + ", " + pos_y + "); \n";
    }
    
    private String traducirColor()
    {
        return getHexadecimal(color.getRed(),color.getGreen(),color.getBlue());
    }
    
    private String getHexadecimal(int red, int green, int blue)
    {
        return "#" + toHexadecimal(red) + toHexadecimal(green) + toHexadecimal(blue);
    }
    
    private String toHexadecimal(int color)
    {
        StringBuilder constructor = new StringBuilder(Integer.toHexString(color & 0xff));
        while(constructor.length() < 2)
        {
            constructor.append("0");
        }
        return constructor.toString().toUpperCase();
    }    
}
