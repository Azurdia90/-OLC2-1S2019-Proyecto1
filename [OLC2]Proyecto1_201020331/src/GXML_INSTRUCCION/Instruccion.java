/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Arreglo;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public interface Instruccion 
{
    //metodo que se implementaria en las traducciones
    public Simbolo ejecutar(Entorno entorno_local,String padre);
    //metodo que se implementaria en las declaraciones, asignaciones, impresiones
    public Simbolo ejecutar(Entorno entorno_local, FS_Arreglo lista_componentes, ObjetoEntrada salida);
}