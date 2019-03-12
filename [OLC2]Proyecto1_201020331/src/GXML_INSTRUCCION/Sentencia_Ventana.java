/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Ventana implements Instruccion
{
    private int fila;
    private int columna;
    //principales
    private String id;
    private Boolean tipo;
    //opcionales
    private Color color;
    private Instruccion accionInicial;
    private Instruccion accionFinal;
    
    private ArrayList<GXML_Elemento> lista_elementos;
    
    public Sentencia_Ventana(ArrayList<GXML_Elemento> p_lista_elementos, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.lista_elementos = p_lista_elementos;                
    }
    
    private boolean cargar_principales()
    {
        boolean completo = false;
        int principal = 2;
        for(int i = 0; i < lista_elementos.size(); i++)
        {
            if(lista_elementos.get(i).IsElementOrNot("id") && principal > 0)
            {
                this.id = lista_elementos.get(i).getValor().toString();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
            else if(lista_elementos.get(i).IsElementOrNot("tipo") && principal > 0)
            {
                this.tipo = (Boolean) lista_elementos.get(i).getValor();
                principal--;
                if(principal == 0)
                {
                    return true;
                }
            }
        }
        
        return completo;
    }
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            boolean continuar = cargar_principales();
            if(continuar)
            {
                FS_Ventana ventana_nueva;
                ventana_nueva = new FS_Ventana(id,tipo);

                Simbolo simbolo_ventana = new Simbolo();
                simbolo_ventana.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                simbolo_ventana.setRol(Tabla_Enums.tipo_Simbolo.objeto);
                simbolo_ventana.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.identificador);
                simbolo_ventana.setIdentificador(id);            
                simbolo_ventana.setValor(ventana_nueva);
                
                if(!entorno_local.containsKey(id))
                {
                    entorno_local.Crear(id, simbolo_ventana);
                    
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                    nuevo_simbolo.setIdentificador("10-4");            
                    nuevo_simbolo.setValor("Creación de Ventana fue realizada exitosamente.");

                    return nuevo_simbolo;
                }   
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                    nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: la ventana " + id + " ya fue creada.");

                    return nuevo_simbolo;
                }                    
            }
            else
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( fila + " - " + columna);            
                nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: no cuenta con los elementos principales para su creación.");

                return nuevo_simbolo;
            }            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setIdentificador( fila + " - " + columna);            
            nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }            
    }
    
}
