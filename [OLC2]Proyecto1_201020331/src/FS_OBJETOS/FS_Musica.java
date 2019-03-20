/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_INSTRUCCIONES.Instruccion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Musica extends JButton //implements ActionListener
{

    private String nombre;
    private String path;
    
    private int posx;
    private int posy;
    
    private int alto;
    private int ancho;
    private boolean autoreproduccion;
    
    ObjetoEntrada salida;
    
    public FS_Musica(String p_path, int p_posx, int p_posy, boolean p_autoreproduccion, int p_alto, int p_ancho, ObjetoEntrada p_salida)
    {
        this.salida = p_salida;
        this.nombre = "";
        this.path =  p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.autoreproduccion = p_autoreproduccion;    
        
        this.setBounds(posx, posy,ancho,alto);
        this.setText("reproducir");
        
        this.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) 
                                {                                    
                                    try {
                                        // lista_fncions
                                        // lista_ventanas ()
                                        // lista_simbolos.obetner(id);
                                        // .show()
                                        //nodo_sentencia_llamada
                                        //llamar_reccorrer_AST(nodo_sentencia_llamada);
                                        ReproducirSonido();
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(FS_Musica.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (JavaLayerException ex) {
                                        Logger.getLogger(FS_Musica.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }	
                                }); 

        this.setVisible(true);
        this.repaint(); 
        
    }
    
    public FS_Musica(String p_nombre, String p_path, int p_posx, int p_posy, ObjetoEntrada p_salida)
    {
        this.salida = p_salida;
        this.nombre = p_nombre;
        this.path =  p_path;
        this.posx = p_posx;
        this.posy = p_posy;
        this.alto = 300;
        this.ancho = 300;
        this.autoreproduccion = false;
        
        this.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) 
                                {                                    
                                    try {
                                        // lista_fncions
                                        // lista_ventanas ()
                                        // lista_simbolos.obetner(id);
                                        // .show()
                                        //nodo_sentencia_llamada
                                        //llamar_reccorrer_AST(nodo_sentencia_llamada);
                                        ReproducirSonido();
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(FS_Musica.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (JavaLayerException ex) {
                                        Logger.getLogger(FS_Musica.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }	
                                });    
        
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
        this.setBounds(posx, posy, ancho, alto);
        this.repaint();        
    }
    
    public String traducirMusica(String padre)
    {
        return  padre + ".CrearReproductor(\"" + path + "\", " + posx + ", " + posy + ", " + (autoreproduccion == true ? "verdadero" : "falso")+ "," + alto + ", " + ancho + "); \n";
    } 
    
    private void ReproducirSonido() throws FileNotFoundException, JavaLayerException
    {

            /*
            AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioinputstream);
            clip.start();
            while(clip.isRunning())
            {
                Thread.sleep(1000);
            }
            clip.close();
            */
        boolean pausa = false;

        Player reproductor = new Player(new FileInputStream(salida.getPath_archivo() + path));

        new Thread()
        {
            public void run()
            {
                try
                {
                    while(true)
                    {
                        if(!pausa)
                        {
                            if(!reproductor.play(1))
                            {
                                break;
                            }
                        }
                    }
                }
                catch(JavaLayerException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error: " + ex + "\n",
                       "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
                }                    
            }

        }.start();
                
    }
}
