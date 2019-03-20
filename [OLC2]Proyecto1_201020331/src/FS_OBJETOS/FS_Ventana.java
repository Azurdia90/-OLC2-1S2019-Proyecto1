/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_AST.Nodo_AST_FS;
import FS_INSTRUCCIONES.Instruccion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_Ventana extends JFrame
{
    private String identificador;
    private boolean tipo;
    private int alto;
    private int ancho;
    
    private Color color;
    
    private Nodo_AST_FS nodo_alcargar;
    private Nodo_AST_FS nodo_alcerrar;
    
    private Instruccion alcargar;
    private Instruccion alcerrar;
    
    //PERMITIRA ALMACENAR TODOS LOS COMPONENTES QUE LE SON AGREGADOS PARA OBTENER
    //INFORMACION QUE LUEGO SE IMPRIMIRA EN GFACE
    private ArrayList<Object> lista_componentes;
    
    //CONSTRUCTOR PARA FS
    public FS_Ventana(String p_id, Integer p_alto, Integer p_ancho, Color p_color)
    {        
        this.identificador = p_id;
        this.tipo = false;
        this.alto = p_alto;
        this.ancho = p_ancho;
        this.color = p_color;
        
        this.nodo_alcargar = null;
        this.nodo_alcerrar = null;
        
        this.lista_componentes = new ArrayList<Object>();
        
        this.setTitle(identificador);
        this.setSize(alto, ancho);     
        this.setBackground(color); 
        this.setLayout(null);
        this.setVisible(false);  
    }
    
    //CONSTRUCTOR PARA GXML
    public FS_Ventana(String p_id, Boolean p_tipo)
    {
        this.identificador = p_id;
        this.tipo = p_tipo;
        this.alto = 700;
        this.ancho =700;
        this.color = Color.WHITE;
        
        this.nodo_alcargar = null;
        this.nodo_alcerrar = null;
        
        this.lista_componentes = new ArrayList<Object>();
        
        this.setTitle(identificador);
        this.setSize(alto, ancho);
        this.setBackground(color);
        this.setLayout(null);
        this.setVisible(false);   
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
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

    public Nodo_AST_FS getNodo_alcargar() {
        return nodo_alcargar;
    }

    public void setNodo_alcargar(Nodo_AST_FS nodo_alcargar) {
        this.nodo_alcargar = nodo_alcargar;
    }

    public Nodo_AST_FS getNodo_alcerrar() {
        return nodo_alcerrar;
    }

    public void setNodo_alcerrar(Nodo_AST_FS nodo_alcerrar) {
        this.nodo_alcerrar = nodo_alcerrar;
    }

    public Instruccion getAlcargar() {
        return alcargar;
    }

    public void setAlcargar(Instruccion alcargar) {
        this.alcargar = alcargar;
    }

    public Instruccion getAlcerrar() {
        return alcerrar;
    }

    public void setAlcerrar(Instruccion alcerrar) {
        this.alcerrar = alcerrar;
    }

    public ArrayList<Object> getLista_componentes() {
        return lista_componentes;
    }

    public void setLista_componentes(ArrayList<Object> lista_componentes) {
        this.lista_componentes = lista_componentes;
    }          
    
    public void actualizarVentana()
    {
        this.setTitle(identificador);
        this.setSize(alto, ancho);
        this.setBackground(color);
        this.repaint(); 
    }
    
    public String traducirVentana()
    {
        return  "var vent_" + identificador + " =  CrearVentana(\"" + traducirColor() + "\", " + ancho + ", " + alto + ", \"" + identificador + "\"); \n";        
    }
   
    public String traduciralCargar()
    {
        return traduccirArbol(nodo_alcargar);
    }
    
    public String traduciralCerrar()
    {
        return traduccirArbol(nodo_alcerrar);
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
    
    private String traduccirArbol(Nodo_AST_FS nodo_llamada)
    {
        String traduccion = "";
        
        if(nodo_llamada != null)
        {
            if(nodo_llamada.IsNodoOrNot("SENTENCIA_LLAMADA"))
            {
                traduccion += nodo_llamada.getValor();
                if(nodo_llamada.getHijos().size()> 0)
                {
                    traduccion += traduccirArbol(nodo_llamada.getHijos().get(0));
                }
                else
                {
                    traduccion += "()";
                }
            }
            else if(nodo_llamada.IsNodoOrNot("LISTA_EXPRESIONES"))
            {
                traduccion += "(";
                traduccion += traduccirArbol(nodo_llamada.getHijos().get(0));
                for(int i = 1; i < nodo_llamada.getHijos().size(); i++)
                {
                    traduccion += ", " + traduccirArbol(nodo_llamada.getHijos().get(i));
                }
                traduccion += ")";
            }
            else if(nodo_llamada.IsNodoOrNot("EXPRESION"))
            {
                return traduccirExpresion(nodo_llamada.getHijos().get(0));
            }
        }
        return traduccion;
    }
    
    private String traduccirExpresion(Nodo_AST_FS nodo_expresion)
    {
        String traduccion = "";
        
        if(nodo_expresion != null)
        {            
            if(nodo_expresion.getHijos().size() > 0)
            {
                traduccion += traduccirExpresion(nodo_expresion.getHijos().get(0));
            }           
            if(!nodo_expresion.getValor().equalsIgnoreCase(""))
            {
                traduccion += nodo_expresion.getValor();
            }
            else if (nodo_expresion.getEtiqueta().length() > 0 && nodo_expresion.getEtiqueta().length() < 3)
            {
                traduccion += nodo_expresion.getEtiqueta();
            }
            else
            {
                traduccion += "";
            }
            
            if(nodo_expresion.getHijos().size() > 1)
            {
                traduccion += traduccirExpresion(nodo_expresion.getHijos().get(1));
            }            
        }        
        return traduccion;
    }

}
