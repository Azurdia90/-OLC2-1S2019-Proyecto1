/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import FS_OBJETOS.Funcion;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Azurdia
 */
public class Tabla_Simbolos 
{
    private static Tabla_Simbolos _Instance = new Tabla_Simbolos();
    
    private Pila_Entornos mi_Stack;
    private HashMap<String,Funcion> tabla_funciones;
    
    public Tabla_Simbolos()
    {
        this.tabla_funciones = new HashMap<String,Funcion>();
        this.mi_Stack = new Pila_Entornos();
    }
    
    /***************************METODOS PARA FUNCIONES******************************/
    
    public boolean existe_metodo(String p_id)
    {
        return tabla_funciones.containsKey(p_id);
    }
    
    public Funcion obtener_Metodo(String p_id)
    {
        return tabla_funciones.get(p_id);
    }
    
    public void agregar_metodo(String p_id, Funcion p_funcion)
    {
        this.tabla_funciones.put(p_id, p_funcion);
    }
    
    /*************************FIN METODOS PARA FUNCIONES******************************/
    
    /*************************METODOS PARA VARIABLES*********************************/
    
    public Simbolo obtener_Simbolo(Entorno entorno_local, String p_key)
    {
        return mi_Stack.Buscar_Simbolo(entorno_local, p_key);
    }

    /*************************FIN METODOS PARA VARIABLES****************************/
    
    public Pila_Entornos getMi_Stack() {
        return mi_Stack;
    }

    /*************************METODOS PARA PILA VARIABLES*********************************/
    public void setMi_Stack(Pila_Entornos mi_Stack) {
        this.mi_Stack = mi_Stack;
    }
    
    /*************************FIN METODOS PARA PILA VARIABLES****************************/
    
    /*************************METODOS PARA MANEJO TABLA SIMBOLOS************************/
    public void Limpiar()
    {
        this.mi_Stack.vaciar();
        this.tabla_funciones.clear();
    }            
    
    /**********************FIN METODOS PARA MANEJO TABLA SIMBOLOS***********************/
    
    /****************************METODOS PARA SINGLETON********************************/
    public static Tabla_Simbolos getInstance() 
    {
        if(_Instance != null)
        {
            return _Instance;
        }
        else
        {
            _Instance = new Tabla_Simbolos();
            JOptionPane.showMessageDialog(null,"Existio un error y fue necesario instanciar nuevamente la Tabla de Simbolos.");
            return _Instance;
        }
    }

    public static void setInstance(Tabla_Simbolos _Instance) 
    {
        Tabla_Simbolos._Instance = _Instance;
    }
    
    /****************************FIN METODOS PARA SINGLETON*****************************/
}
