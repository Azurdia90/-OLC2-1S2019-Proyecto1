/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Azurdia
 */
public class Tabla_Simbolos 
{
    private static Tabla_Simbolos _Instance = new Tabla_Simbolos();
    
    private Pila_Entornos mi_Stack;
    //private ArrayList<Funciones> lista_funciones;
    
    public Tabla_Simbolos()
    {
        mi_Stack = new Pila_Entornos();
    }
    
    public Simbolo obtener_Simbolo(Entorno entorno_local, String p_key)
    {
        return mi_Stack.Buscar_Simbolo(entorno_local, p_key);
    }

    public Pila_Entornos getMi_Stack() {
        return mi_Stack;
    }

    public void setMi_Stack(Pila_Entornos mi_Stack) {
        this.mi_Stack = mi_Stack;
    }
    
    public void Limpiar()
    {
        mi_Stack = new Pila_Entornos();
    }            
    
    /*METODOS PARA SINGLETON*/
    public static Tabla_Simbolos getInstance() 
    {
        if(_Instance != null)
        {
            return _Instance;
        }
        else
        {
            _Instance = new Tabla_Simbolos();
            JOptionPane.showMessageDialog(null,"Existio un error y fue necesario instanciar nuevamente la Fucking Tabla de Simbolos.");
            return _Instance;
        }
    }

    public static void setInstance(Tabla_Simbolos _Instance) 
    {
        Tabla_Simbolos._Instance = _Instance;
    }
}
