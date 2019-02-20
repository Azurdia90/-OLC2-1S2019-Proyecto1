/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import UI.ObjetoEntrada;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Imprimir implements Instruccion
{
    private int fila;
    private int columna;
    
    private Expresion expresion;
    
    public Sentencia_Imprimir(Nodo_AST_FS nodo_imprimir)
    {
        this.fila = Integer.parseInt(nodo_imprimir.getFila());
        this.columna = Integer.parseInt(nodo_imprimir.getColumna());
        this.expresion = new Expresion(nodo_imprimir.getHijos().get(0).getHijos().get(0));        
    }
    

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo resultado;
            resultado = expresion.ejecutar(entorno_local, salida);
            
            Simbolo nuevo_simbolo = new Simbolo();
            if(resultado.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                try
                {  
                    Document consolazo = salida.getConsola().getDocument();
                    consolazo.insertString(consolazo.getLength(), resultado.getValor().toString() + "\n", null);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Existio un error al escribir en la consola: " + e.getMessage());
                }
                                
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("10-4");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                nuevo_simbolo.setValor("Impresion realizada correctamente");                
            }
            else
            {
                ERRORES.Nodo_Error error_encontrado = new ERRORES.Nodo_Error();
                error_encontrado.setArchivo(salida.getNombre_archivo());
                error_encontrado.setIdentificador("Análisis Semantico FuncionScript");
                error_encontrado.setDescripcion(resultado.getValor().toString());
                error_encontrado.setLinea(Integer.toString(fila));
                error_encontrado.setColumna(Integer.toString(columna));
                error_encontrado.setTipo("Semantico");
                ERRORES.Tabla_Errores.getInstance().add(error_encontrado); 
                
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setIdentificador("33-12");
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setValor("Impresion no fue realizada");                
            }                                

            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("33-12");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Impresion no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;
        }        
    }
    
}
