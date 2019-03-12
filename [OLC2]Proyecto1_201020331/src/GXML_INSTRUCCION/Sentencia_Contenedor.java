/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Contenedor implements Instruccion
{
    //principales
    private String id;
    private int posx;
    private int posy;

    //opcionales
    private Color color;
    private int ancho;
    private int alto;
    private boolean borde;
    
    public Sentencia_Contenedor(ArrayList<GXML_Elemento> lista_elmentos, int p_fila, int p_columna)
    {
        
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
