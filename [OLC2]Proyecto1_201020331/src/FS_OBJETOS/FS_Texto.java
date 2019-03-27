/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_INSTRUCCIONES.Instruccion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Texto extends JLabel
{
    private String id;
    private String fuente;
    private int tamaño;
    private Color color;
    private int pos_x;
    private int pos_y;
    private boolean bold;
    private boolean italic;
    private String texto;
    
    private Font nueva_fuente;
    
    //CONSTRUCTOR PARA FS
    public FS_Texto(String p_fuente, int p_tamaño, Color p_color, int p_pos_x, int p_pos_y, boolean p_bold, boolean p_italic, String p_texto)
    {
        this.fuente = p_fuente;
        this.tamaño = p_tamaño;
        this.color = p_color;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.bold = p_bold;
        this.italic = p_italic;
        this.texto = p_texto;
        
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
        
        this.setForeground(color);
        this.setFont(nueva_fuente);
        this.setText(texto);              
        this.setLocation(pos_x, pos_y);
        this.setVisible(true);
        this.repaint();
    }
    
    //CONSTRUCTOR PARA GXML
    public FS_Texto(String p_id, String p_texto,int p_pos_x, int p_pos_y)
    {
        this.id =  p_id;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.texto = p_texto;
        this.tamaño = 10;
        this.fuente = "Arial";
        this.color = Color.BLACK;
        this.bold = false;
        this.italic = false;
        
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
                        
        this.setForeground(color);
        this.setFont(nueva_fuente);
        this.setText(texto);              
        this.setLocation(pos_x, pos_y);
        this.setVisible(true);
        this.repaint();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
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

    public boolean getBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean getItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public void actualizarTexto()
    {
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
                        
        this.setForeground(color);
        this.setFont(nueva_fuente);
        this.setText(texto);              
        this.setLocation(pos_x, pos_y);
        this.setVisible(true);        
        this.repaint();
    }   
    
    public String traducirTexto(String padre)
    {        
        return  padre + ".CrearTexto(\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() + "\", " + pos_x + ", " + pos_y + ", " + (bold == true ? "verdadero" : "falso") + ", " + (italic == true ? "verdadero" : "falso") + ", \"" +  texto.trim()  + "\"); \n";
    }
    
    public String traducirColor()
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
