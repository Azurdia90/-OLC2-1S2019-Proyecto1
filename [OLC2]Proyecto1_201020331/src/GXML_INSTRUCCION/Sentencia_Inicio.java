/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Inicio implements Instruccion
{
    private ArrayList<Instruccion> lista_instrucciones;
    private Entorno entorno_global;
    
    public Sentencia_Inicio(ArrayList<Instruccion> p_lista_instrucciones)
    {
        //this.entorno_global = new Entorno();
        entorno_global = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().peek();
        this.lista_instrucciones = p_lista_instrucciones;
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            this.primer_recorrido(salida);
            this.segundo_recorrido();
            return null;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }                          
    }
     
    //SE EJECUTARAN UNICAMENTE LAS VENTANAS Y SE ALMACENARAN
    private void primer_recorrido(ObjetoEntrada salida)
    {
        Simbolo simbolo_ventana;
        for(int i = 0; i < lista_instrucciones.size(); i++)
        {
            if(lista_instrucciones.get(i) instanceof Sentencia_Ventana)
            {
                simbolo_ventana = lista_instrucciones.get(i).ejecutar(entorno_global, salida);
               
            }
        }
    }
    
    //SE EJECUTARAN UNICAMENTE LOS IMPORT DE LOS OTROS ARCHIVOS
    private void segundo_recorrido()
    {
        
    }
    
}
