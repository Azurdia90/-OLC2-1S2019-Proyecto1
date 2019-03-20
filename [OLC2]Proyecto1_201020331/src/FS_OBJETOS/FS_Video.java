/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Video 
{
    private String nombre;
    private String path;
    
    private int posx;
    private int posy;
    
    private int alto;
    private int ancho;
    private boolean autoreproduccion;
    
    public FS_Video(String p_path, int p_posx, int p_posy, boolean p_autoreproduccion, int p_alto, int p_ancho)
    {
        this.nombre = "";
        this.path = p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.autoreproduccion = p_autoreproduccion;         
    }
    
    public FS_Video(String p_nombre, String p_path, int p_posx, int p_posy)
    {
        this.nombre = "";
        this.path = p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = 300;
        this.ancho = 300;
        this.autoreproduccion = false;
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

    public void setPath(String path) {
        this.path = path;
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

    public boolean isAutoreproduccion() {
        return autoreproduccion;
    }

    public void setAutoreproduccion(boolean autoreproduccion) {
        this.autoreproduccion = autoreproduccion;
    }
    
    public void actualizarMusica()
    {                                        
        //this.setBounds(posx, posy, ancho, alto);
        //this.repaint();        
    }
    
    public String traducirVideo(String padre)
    {
        return  padre + ".CrearVideo(\"" + path + "\", " + posx + ", " + posy + ", " + (autoreproduccion == true ? "verdadero" : "falso")+ "," + alto + ", " + ancho + "); \n";
    } 
}
