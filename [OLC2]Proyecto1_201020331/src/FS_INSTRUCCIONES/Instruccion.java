/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import UI.ObjetoEntrada;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;

/**
 *
 * @author crist
 */
public interface Instruccion 
{
    //metodo que se implementaria en las declaraciones, asignaciones, impresiones
    public Simbolo ejecutar(Entorno entorno_local,ObjetoEntrada salida);
}
