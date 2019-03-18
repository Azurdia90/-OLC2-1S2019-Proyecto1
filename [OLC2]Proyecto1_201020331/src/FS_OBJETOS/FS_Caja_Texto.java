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
    private String id;
    private int pos_x;
    private int pos_y;
    
    private int alto;
    private int ancho;
    private String fuente;
    private int tamaño;
    private Color color;    
    private boolean bold;
    private boolean italic;
    private String texto;
    
    private Font nueva_fuente;
    public FS_Caja_Texto(int p_alto, int p_ancho, String p_fuente, int p_tamaño, Color p_color, int p_pos_x, int p_pos_y, boolean p_bold, boolean p_italic, String p_texto, String p_identificador)
    {
        this.id = p_identificador;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.alto = p_alto;
        this.ancho = p_ancho;
        
        this.color = p_color;
        this.fuente = p_fuente;        
        this.tamaño = p_tamaño;                
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
        
        this.setBounds(pos_x, pos_y, ancho, alto); 
        this.setFont(nueva_fuente);
        this.setForeground(color);
        this.setText(texto);
        this.setVisible(true);
        this.repaint();
        
    }

    public FS_Caja_Texto(String p_identificador, int p_pos_x, int p_pos_y)
    {
        this.id = p_identificador;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;
        this.ancho = 300;
        this.alto = 100;
        
        this.color = Color.BLACK;
        this.fuente = "Arial";
        this.tamaño = 10;
        this.bold = false;
        this.italic = false;
        this.texto = "";                        
        
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
                
        this.setBounds(pos_x, pos_y, ancho, alto); 
        this.setFont(nueva_fuente);
        this.setForeground(color);
        this.setText(texto);
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

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
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
    
    public void actualizarCajaTexto()
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
        
        this.setBounds(pos_x, pos_y, ancho, alto); 
        this.setFont(nueva_fuente);
        this.setForeground(color);
        this.setText(texto);
        this.repaint();
    }
    
    public String traducirCajaTexto(String padre)
    {        
        return padre + ".CrearCajaTexto(" + alto + "," + ancho + ",\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() + "\", " + pos_x + ", " + pos_y + ", " + (bold == true ? "verdadero" : "falso") + ", " + (italic == true ? "verdadero" : "falso") + ", \"" +  texto.trim()  + "\", \"" + id + "\"); \n";
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
