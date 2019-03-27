/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_INSTRUCCIONES;

import FS_AST.Nodo_AST_FS;
import FS_EXPRESION.Expresion;
import FS_OBJETOS.FS_Area_Texto;
import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Caja_Texto;
import FS_OBJETOS.FS_ComboBox;
import FS_OBJETOS.FS_Contenedor;
import FS_OBJETOS.FS_Imagen;
import FS_OBJETOS.FS_Musica;
import FS_OBJETOS.FS_Objeto;
import FS_OBJETOS.FS_Spinner;
import FS_OBJETOS.FS_Texto;
import FS_OBJETOS.FS_Ventana;
import FS_OBJETOS.FS_Video;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Acceso implements Instruccion
{
    private int fila;
    private int columna;    
    private String identificador;
    
    private String atributo;
    private Expresion posicion;
    
    private int tipo_acceso; //0 error 1 objeto 2 arreglo
    
    public Sentencia_Acceso(Nodo_AST_FS nodo_sentencia)
    {
        this.fila = Integer.parseInt(nodo_sentencia.getFila());
        this.columna = Integer.parseInt(nodo_sentencia.getColumna());
        
        this.identificador = nodo_sentencia.getValor();
        
        if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("IDENTIFICADOR"))
        {
            this.atributo = nodo_sentencia.getHijos().get(0).getValor();
            this.tipo_acceso = 1;
        }
        else if(nodo_sentencia.getHijos().get(0).IsNodoOrNot("EXPRESION"))
        {
            this.posicion = new Expresion(nodo_sentencia.getHijos().get(0).getHijos().get(0));
            this.tipo_acceso = 2;
        }
    }

    @Override
    public Simbolo ejecutar(Entorno entorno_local, ObjetoEntrada salida) 
    {
        try
        {
            Simbolo simbolo_obtenido = entorno_local.Obtener(identificador);
            
            if(simbolo_obtenido.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
            {
                return simbolo_obtenido;
            }
            
            if(tipo_acceso == 1)
            {                
                if(simbolo_obtenido.getValor() instanceof FS_Objeto)
                {
                    FS_Objeto objeto_aux = (FS_Objeto) simbolo_obtenido.getValor();

                    if(objeto_aux.containsKey(atributo))
                    {
                        return objeto_aux.get(atributo);
                    }
                    else
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Ventana)
                {
                    FS_Ventana ventana_obtenida = (FS_Ventana) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("id"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(ventana_obtenida.getIdentificador());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("tipo"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(ventana_obtenida.getTipo() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("color"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(ventana_obtenida.traducirColor());

                        return nuevo_simbolo;
                        
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(ventana_obtenida.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(ventana_obtenida.getAncho());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Contenedor)
                {
                    FS_Contenedor contenedor_obtenido = (FS_Contenedor) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("id"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getId());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("borde"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getBorde()== true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("color"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.traducirColor());

                        return nuevo_simbolo;
                        
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getAncho());

                        return nuevo_simbolo;
                    }    
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(contenedor_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Texto)
                {
                    FS_Texto texto_obtenido = (FS_Texto) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getId());

                        return nuevo_simbolo;
                    }
                    if(atributo.equalsIgnoreCase("fuente"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getFuente());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("negrita"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getBold() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("cursiva"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getItalic() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("color"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.traducirColor());

                        return nuevo_simbolo;
                        
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(texto_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }                    
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Area_Texto)
                {
                    FS_Area_Texto area_texto_obtenido = (FS_Area_Texto) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getId());

                        return nuevo_simbolo;
                    }
                    if(atributo.equalsIgnoreCase("fuente"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getFuente());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("negrita"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getBold() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("cursiva"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getItalic() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("color"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.traducirColor());

                        return nuevo_simbolo;
                        
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(area_texto_obtenido.getAncho());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }                      
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Caja_Texto)
                {
                    FS_Caja_Texto caja_texto_obtenido = (FS_Caja_Texto) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getId());

                        return nuevo_simbolo;
                    }
                    if(atributo.equalsIgnoreCase("fuente"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getFuente());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("negrita"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getBold() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("cursiva"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getItalic() == true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("color"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.traducirColor());

                        return nuevo_simbolo;
                        
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(caja_texto_obtenido.getAncho());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Spinner)
                {
                    FS_Spinner spinner_obtenido = (FS_Spinner) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getId());

                        return nuevo_simbolo;
                    }                    
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getAncho());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("maximo"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getMaximo());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("minimo"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(spinner_obtenido.getMinimo());

                        return nuevo_simbolo;
                    }
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_ComboBox)
                {
                    FS_ComboBox combobox_obtenido = (FS_ComboBox) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(combobox_obtenido.getId());

                        return nuevo_simbolo;
                    }                    
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(combobox_obtenido.getPos_x());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(combobox_obtenido.getPos_y());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(combobox_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(combobox_obtenido.getAncho());

                        return nuevo_simbolo;
                    }                    
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Imagen)                    
                {
                    FS_Imagen imagen_obtenido = (FS_Imagen) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getNombre());

                        return nuevo_simbolo;
                    }                    
                    else if(atributo.equalsIgnoreCase("path"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getPath());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getPosx());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getPosy());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(imagen_obtenido.getAncho());

                        return nuevo_simbolo;
                    }                    
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }                    
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Musica)
                {
                    FS_Musica musica_obtenido = (FS_Musica) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getNombre());

                        return nuevo_simbolo;
                    }                    
                    else if(atributo.equalsIgnoreCase("path"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getPath());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getPosx());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getPosy());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getAncho());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("auto-reproduccion"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(musica_obtenido.getAutoreproduccion()== true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }                    
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    } 
                }
                else if(simbolo_obtenido.getValor() instanceof FS_Video)
                {
                    FS_Video video_obtenido = (FS_Video) simbolo_obtenido.getValor();
                    
                    if(atributo.equalsIgnoreCase("nombre"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getNombre());

                        return nuevo_simbolo;
                    }                    
                    else if(atributo.equalsIgnoreCase("path"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getPath());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("x"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getPosx());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("y"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getPosy());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("alto"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getAlto());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("ancho"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.entero);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getAncho());

                        return nuevo_simbolo;
                    }
                    else if(atributo.equalsIgnoreCase("auto-reproduccion"))
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.variable);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.booleano);
                        nuevo_simbolo.setIdentificador("10-4");                    
                        nuevo_simbolo.setValor(video_obtenido.getAutoreproduccion()== true ? "verdadero" : "falso");

                        return nuevo_simbolo;
                    }                    
                    else
                    {                       
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El objeto: " + identificador + " no tiene un atributo llamado " + atributo);

                        return nuevo_simbolo; 
                    }                     
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                    nuevo_simbolo.setValor("La variable \"" + identificador + "\" no es de tipo objeto.");

                    return nuevo_simbolo; 
                }
            }
            else if(tipo_acceso == 2)
            {   
                FS_Arreglo arreglo_aux = (FS_Arreglo) simbolo_obtenido.getValor();
                Simbolo posicion_obtenida = posicion.ejecutar(entorno_local, salida);
                
                if(posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    return posicion_obtenida;
                }
                else if(posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.entero || posicion_obtenida.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.decimal)
                {
                    int posicion_final = Integer.parseInt(posicion_obtenida.getValor().toString());
                    if(posicion_final > -1)
                    {
                        if(posicion_final < (arreglo_aux.size()))
                        {
                            return arreglo_aux.get(posicion_final);
                        }
                        else
                        {
                            Simbolo nuevo_simbolo = new Simbolo();
                            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                            nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                            nuevo_simbolo.setValor("El valor de la posicion de acceso es mayor al tamaño del arreglo.");

                            return nuevo_simbolo; 
                        }
                    }
                    else 
                    {
                        Simbolo nuevo_simbolo = new Simbolo();
                        nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                        nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                        nuevo_simbolo.setValor("El valor de la posicion de acceso a un arreglo debe ser un numero mayor o igual a 0.");

                        return nuevo_simbolo;    
                    }                        
                }
                else
                {
                    Simbolo nuevo_simbolo = new Simbolo();
                    nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                    nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                    nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                    nuevo_simbolo.setIdentificador(fila + " - " + columna);                    
                    nuevo_simbolo.setValor("El valor de la posicion de acceso a un arreglo debe ser un valor numerico.");

                    return nuevo_simbolo; 
                }                
            }
            
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador("10-4");
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
            nuevo_simbolo.setValor("Sentencia Acceso realizada exitosamente.");

            return nuevo_simbolo;
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setIdentificador(fila + " - " + columna);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setValor("Sentencia Acceso no fue realizada, error: " + e.getMessage());

            return nuevo_simbolo;            
        }
    }
}
