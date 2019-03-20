/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_INSTRUCCIONES.Sentencia_LLamada;
import FS_TABLA_SIMBOLOS.Entorno;
import UI.ObjetoEntrada;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Boton extends JButton 
{
    private String id;
    private int pos_x;
    private int pos_y;
    private int alto;
    private int ancho;
    private String fuente;    
    private int tamaño;
    private Color color;        
    private String texto;
    private String referencia;
    
    private boolean enviar;
    private Sentencia_LLamada referencia_final;    
    
    private Font nueva_fuente;    
    
    //CONSTRUCTOR PARA FS
    public FS_Boton(String p_fuente, int p_tamaño, Color p_color, int p_pos_x, int p_pos_y, Sentencia_LLamada p_referencia, String p_texto, int p_alto, int p_ancho, Entorno entorno_local, ObjetoEntrada salida)
    {
        this.id = "";
        this.fuente = p_fuente;
        this.tamaño = p_tamaño;
        this.color = p_color;
        this.pos_x = p_pos_x;
        this.pos_y = p_pos_y;        
        this.texto = p_texto;
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.referencia = "";
        this.referencia_final = p_referencia;
               
        this.enviar = false;
        this.nueva_fuente = new Font(fuente, Font.PLAIN, tamaño);
        
        this.setFont(nueva_fuente);
        this.setText(texto);  
        this.setBackground(color);
        this.setBounds(pos_x, pos_y, ancho, alto);
        this.setVisible(true);
        
        this.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) 
                                {                                    
                                    // lista_fncions
                                    // lista_ventanas ()
                                    // lista_simbolos.obetner(id);
                                    // .show()
                                    //nodo_sentencia_llamada
                                    //llamar_reccorrer_AST(nodo_sentencia_llamada);
                                    referencia_final.ejecutar(entorno_local, salida);  		
                                }	
                                });                                         
        this.repaint();
    }
    
    //CONSTRUCTOR PARA GXML
    public FS_Boton(String p_id, String p_texto, int p_posx, int p_posy , boolean p_enviar)
    {
        this.id = p_id;
        this.pos_x = p_posx;
        this.pos_y = p_posy;
        
        this.alto = 100;
        this.ancho = 100;
        
        this.color = Color.WHITE;
        this.fuente = "Arial";
        this.tamaño = 10;
        this.texto = p_texto;
        
        this.enviar = p_enviar;
        this.referencia = "";
        this.referencia_final = null;
        
        this.setText(texto);     
        this.setBackground(color);
        this.setBounds(pos_x, pos_y, ancho, alto);
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Font getNueva_fuente() {
        return nueva_fuente;
    }

    public void setNueva_fuente(Font nueva_fuente) {
        this.nueva_fuente = nueva_fuente;
    }    
        
    public void actualizarBoton()
    {          
        this.setBackground(color);
        this.setBounds(pos_x, pos_y, ancho, alto);
        this.repaint();
    }
    
    public String traducirBoton(String padre)
    {
        String traduccion = "";
        if(enviar)
        {
            if(!referencia.equalsIgnoreCase(""))
            {
                traduccion += "Funcion Cargar_Ventana_" + referencia+"()\n{\n   vent_" + referencia + ".AlCargar(); \n}\n";
                traduccion += "var " + padre + "_btn_" + id + " = " + padre + ".CrearBoton(\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() +"\", " + pos_x + ", " + pos_y  + ", Cargar_Ventana_" + referencia + "() , \"" + texto.trim() + "\", " +  alto + ", " + ancho + "); \n";
                traduccion += "Funcion Guardar_" + padre +"()\n{\n  " + padre + ".CrearArrayDesdeArchivo();\n}\n";
                traduccion +=  padre + "_btn_" + id + ".AlClic( Guardar_" + padre + "() );\n";
                return traduccion;
            }
            else
            {
                traduccion += "var " + padre + "_btn_" + id + " = " + padre + ".CrearBoton(\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() +"\", " + pos_x + ", " + pos_y  + ", \""  + texto.trim() + "\", " +  alto + ", " + ancho + "); \n";
                traduccion += "Funcion Guardar_" + padre +"()\n{\n  " + padre + ".CrearArrayDesdeArchivo();\n}\n";
                traduccion +=  padre + "_btn_" + id + ".AlClic( Guardar_" + padre + "() );\n";
                return traduccion;
            }
        }    
        else
        {
            if(!referencia.equalsIgnoreCase(""))
            {
                traduccion += "Funcion Cargar_Ventana_" + referencia+"()\n{\n   vent_" + referencia + ".AlCargar(); \n}\n";
                traduccion += padre + ".CrearBoton(\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() +"\", " + pos_x + ", " + pos_y  + ", Cargar_Ventana_" + referencia + "() , \"" + texto.trim() + "\", " +  alto + ", " + ancho + "); \n";
                return traduccion;
            }
            else
            {
                traduccion += padre + ".CrearBoton(\"" + fuente + "\", " + tamaño + ", \"" +  traducirColor() +"\", " + pos_x + ", " + pos_y  + ", \""  + texto.trim() + "\", " +  alto + ", " + ancho + "); \n";
                return traduccion;
            }
        }
        
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
