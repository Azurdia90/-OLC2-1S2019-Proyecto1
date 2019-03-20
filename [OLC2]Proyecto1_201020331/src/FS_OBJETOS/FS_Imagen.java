/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import UI.ObjetoEntrada;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Imagen extends JLabel
{
    private String nombre;
    private String path;
    
    private int posx;
    private int posy;
    
    private int alto;
    private int ancho;
        
    public FS_Imagen(String p_path, int p_posx, int p_posy, int p_alto, int p_ancho, ObjetoEntrada salida)
    {
        this.nombre = "";
        this.path = p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = p_alto;
        this.ancho = p_ancho;
        
        this.setBounds(posx, posy,ancho,alto);
        try
        {
            ImageIcon imagen_a = new ImageIcon(salida.getPath_archivo() + path);
            Image imagen_aj = imagen_a.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            Icon imagen_final = new ImageIcon(imagen_aj);
            this.setIcon(imagen_final);
        }
        catch(Exception e)
        {
            this.setBackground(Color.BLACK);
        }
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setVisible(true);
        this.repaint();        
    }
    
    public FS_Imagen(String p_nombre, String p_path, int p_posx, int p_posy, ObjetoEntrada salida)
    {
        this.nombre = "";
        this.path = p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = 300;
        this.ancho = 300;
        
        this.setBounds(posx, posy,ancho,alto);
        try
        {
            ImageIcon imagen_a = new ImageIcon(salida.getPath_archivo() + path);
            Image imagen_aj = imagen_a.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            Icon imagen_final = new ImageIcon(imagen_aj);
            this.setIcon(imagen_final);
        }
        catch(Exception e)
        {
            this.setBackground(Color.BLACK);
        }
        
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setVisible(true);
        this.repaint();        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String Path) {
        this.path = Path;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
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
    
    public void actualizarImagen()
    {                                        
        this.setBounds(posx, posy, ancho, alto);
        this.repaint();        
    }
    
    public String traducirImagen(String padre)
    {
        return  padre + ".CrearImagen(\"" + path + "\", " + posx + ", " + posy + ", " + alto + ", " + ancho + "); \n";
    } 
        
}
