/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_OBJETOS;

import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;

/**
 *
 * @author Cristian Azurdia
 */
public class FS_ComboBox extends JComboBox
{
    private String id;
    private int pos_x;
    private int pos_y;
    private int alto;
    private int ancho;
    
    private FS_Arreglo listadatos;
    private String defecto;
    
    private Simbolo defecto_final;
        
    //CONSTRUCTOR PARA FS
    public FS_ComboBox(int p_alto, int p_ancho, FS_Arreglo p_listadatos, int p_posx, int p_posy, String p_defecto, String p_id)
    {
        this.id = p_id;
        this.pos_x = p_posx;
        this.pos_y = p_posy;
        this.alto = p_alto;
        this.ancho = p_ancho;
        
        this.listadatos = p_listadatos;
        this.defecto = p_defecto;
        
        Simbolo defecto_final = new Simbolo();
        defecto_final.setAcceso(Tabla_Enums.tipo_Acceso.publico);
        defecto_final.setRol(Tabla_Enums.tipo_Simbolo.variable);
        defecto_final.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
        defecto_final.setValor(defecto);
        
        this.cargarDatos();
        this.setSelectedItem(defecto_final);
                
        this.setBounds(pos_x, pos_y, ancho, alto);                       
        this.setVisible(true); 
        this.repaint();
                       
    }
    
    //CONSTRUCTOR PARA GXML
    public FS_ComboBox(String p_id, int p_posx, int p_posy)
    {
        this.id = p_id;
        this.pos_x = p_posx;
        this.pos_y = p_posy;
        this.alto = 100;
        this.ancho = 500;
        
        this.listadatos = null;
        this.defecto = "";
        
        Simbolo defecto_final = new Simbolo();
        defecto_final.setAcceso(Tabla_Enums.tipo_Acceso.publico);
        defecto_final.setRol(Tabla_Enums.tipo_Simbolo.variable);
        defecto_final.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
        defecto_final.setValor(defecto);
        this.setSelectedItem(defecto_final);
        
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

    public FS_Arreglo getListadatos() {
        return listadatos;
    }

    public void setListadatos(FS_Arreglo listadatos) {
        this.listadatos = listadatos;
    }

    public String getDefecto() {
        return defecto;
    }

    public void setDefecto(String defecto) {
        this.defecto = defecto;
    }
    
    public void cargarDatos()
    {
        for(int i = 0; i < listadatos.size(); i++)
        {
            this.addItem(listadatos.get(i));
        }
    }
    
    public void actualizarComboBox()
    {
        Simbolo defecto_final = new Simbolo();
        defecto_final.setAcceso(Tabla_Enums.tipo_Acceso.publico);
        defecto_final.setRol(Tabla_Enums.tipo_Simbolo.variable);
        defecto_final.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
        defecto_final.setValor(defecto);
        
        this.setSelectedItem(defecto_final);        
        this.setBounds(pos_x, pos_y, ancho, alto);
        this.repaint();
    }
    
    public String traducirComboBox(String padre)
    {
        String traduccion = "";
        traduccion += traducirLista(padre);
        traduccion += padre + ".CrearDesplegable(" + alto + ", " + ancho + ", lista_" + id.trim() + "_" + padre  + " , " + pos_x + ", " + pos_y + ", \"" + defecto.trim() +  "\", \"" + id.trim() + "\"); \n";
        return traduccion;
    }
    
    private String traducirLista(String padre)
    {
        String traduccion = "";
        traduccion += "var lista_" + id.trim() + "_" + padre + " = [";
        
        for(int i = 0; i < listadatos.size(); i++)
        {
            if(i ==0)
            {
               traduccion += "\"" + listadatos.get(i).getValor() + "\"";
            }
            else
            {
                traduccion += ", \"" + listadatos.get(i).getValor() + "\"";
            }                           
        }
        return traduccion + "]; \n";
    }
}
