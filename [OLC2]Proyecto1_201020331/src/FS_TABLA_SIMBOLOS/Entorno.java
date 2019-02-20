/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import java.util.HashMap;

/**
 *
 * @author crist
 */
public class Entorno extends HashMap<String,Simbolo>
{
    private Integer identificador;
    private Entorno siguiente_entorno; 
    
    public void Imprimir()
    {
        for(Simbolo sim : this.values())
        {
            //System.out.println("Entorno " + identificador + " " + sim.getTipo() + " " + sim.getIdentificador());
        }
    }
    
    public Simbolo Obtener(String key)
    {
        if(this.containsKey(key))
        {
            return this.get(key);
        }
        else
        {
            Simbolo sim_aux = new Simbolo();
            sim_aux.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            sim_aux.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            sim_aux.setIdentificador("33-12");            
            sim_aux.setValor("La variable \""+ key +"\" no ha sido definida.");           
            sim_aux.setRol(Tabla_Enums.tipo_Simbolo.error);
            return sim_aux;
        }                
    }
    
    public Simbolo Crear(String key,Simbolo nuevo_sim)
    {
        Simbolo sim_aux = null;
        if(!this.containsKey(key))
        {
            this.put(key, nuevo_sim);
            
            sim_aux = new Simbolo();
            sim_aux.setIdentificador("10-4");         
            //sim_aux.setTipo(sim_aux.getTipo());
            sim_aux.setValor("10-4");
            sim_aux.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            sim_aux.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            return sim_aux;            
        }
        else
        {
            sim_aux = new Simbolo();
            sim_aux.setIdentificador("33-12");         
            sim_aux.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            sim_aux.setValor("Variable ya fue definida");
            sim_aux.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            sim_aux.setRol(Tabla_Enums.tipo_Simbolo.error);
            return sim_aux;
        }                
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }        
    
    public Entorno getSiguiente_entorno() {
        return siguiente_entorno;
    }

    public void setSiguiente_entorno(Entorno siguiente_entorno) {
        this.siguiente_entorno = siguiente_entorno;
    }
            
}
