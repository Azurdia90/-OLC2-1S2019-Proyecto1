/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ERRORES;

import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Tabla_Errores extends ArrayList<Nodo_Error>
{
    private static Tabla_Errores _Instance = new Tabla_Errores();
        
    public static Tabla_Errores getInstance() 
    {
        if (_Instance != null){
            return _Instance;    
        }else{
          _Instance = new Tabla_Errores();
          return _Instance;
        }         
    }
    
    public static void setInstance(Tabla_Errores _Instance) 
    {
        Tabla_Errores._Instance = _Instance;
    } 
}
