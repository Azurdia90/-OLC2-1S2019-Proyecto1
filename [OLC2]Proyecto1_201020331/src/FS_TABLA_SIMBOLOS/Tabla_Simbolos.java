/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import FS_OBJETOS.Funcion;
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
    private ArrayList<Funcion> lista_funciones;
    
    public Tabla_Simbolos()
    {
        this.lista_funciones = new ArrayList<Funcion>();
        this.mi_Stack = new Pila_Entornos();
    }
    
    public boolean existe_metodo(String p_id, int p_parametros)
    {
        for(int i = 0; i < lista_funciones.size(); i++)
        {
            if(lista_funciones.get(i).getIdentificador().equals(p_id) && lista_funciones.get(i).getLista_parametros().size() == p_parametros)
            {
                return true;
            }
        }        
        return false;
    }
    
    public void agregar_metodo(Funcion p_funcion)
    {
        lista_funciones.add(p_funcion);
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
            JOptionPane.showMessageDialog(null,"Existio un error y fue necesario instanciar nuevamente la Tabla de Simbolos.");
            return _Instance;
        }
    }

    public static void setInstance(Tabla_Simbolos _Instance) 
    {
        Tabla_Simbolos._Instance = _Instance;
    }
}
