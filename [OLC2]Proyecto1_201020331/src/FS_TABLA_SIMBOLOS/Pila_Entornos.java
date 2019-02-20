/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;


/**
 *
 * @author crist
 */
public class Pila_Entornos 
{
    private Entorno entorno_inicio;
    private Integer tamaño; 
    
    public Pila_Entornos()
    {
        this.entorno_inicio = null;
        this.tamaño = 0;
    }
    
    public boolean estaVacia()
    {
        return entorno_inicio == null ? true : false;
    }
    
    public int tamaño()
    {
        return this.tamaño;
    }
    
    public void Agregar(Entorno nuevo_entorno)
    {
        if(estaVacia())
        {
            entorno_inicio = nuevo_entorno;
            entorno_inicio.setIdentificador(tamaño);
            this.tamaño++;
        }
        else
        {
            nuevo_entorno.setSiguiente_entorno(entorno_inicio);            
            entorno_inicio = nuevo_entorno;
            entorno_inicio.setIdentificador(tamaño);
            this.tamaño++;
        }
        
    }
    
    public Entorno Desapilar()
    {
        Entorno retorno = null;
        
        if(!estaVacia())
        {
            retorno = entorno_inicio;
            this.entorno_inicio =  entorno_inicio.getSiguiente_entorno();
            this.tamaño--;
            
            return retorno;            
        }
        else
        {
            return retorno;
        }        
    }
    
    public Entorno peek()
    {
        if(!estaVacia())
        {
             return entorno_inicio;
        }
        else
        {
            return null;
        }
    }   
    
    public Simbolo Buscar_Simbolo(Entorno entorno_actual, String key)
    {
        Entorno entorno_aux = entorno_actual;
        Simbolo simbolo_aux = null;
        
        while(entorno_aux != null)
        {
            simbolo_aux = entorno_aux.Obtener(key);
            if(simbolo_aux.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)            
            {    
                return simbolo_aux;
            }
            entorno_aux = entorno_aux.getSiguiente_entorno();
        }        
        
        return simbolo_aux;
    }  
    
    public void imprimir()
    {
        try
        {
            Entorno entorno_aux = null;
            if(!estaVacia())
            {
                entorno_inicio.Imprimir();
                if(tamaño > 1)
                {                    
                    entorno_aux = entorno_inicio;
                    for(int i = 1 ; i < tamaño; i++)
                    {
                        entorno_aux = entorno_aux.getSiguiente_entorno();
                        entorno_aux.Imprimir();
                    }   
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Esta vacio el Stack");
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Existe un error al imprimir el Stack: \n" + e.getMessage());
        }
    }
    
    public void vaciar()
    {
        this.entorno_inicio = null;
        this.tamaño = 0;
    }
    
}
