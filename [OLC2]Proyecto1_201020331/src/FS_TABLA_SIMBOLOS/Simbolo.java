/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

/**
 *
 * @author crist
 */
public class Simbolo 
{
        
    private String identificador;
    private Tabla_Enums.tipo_primitivo_Simbolo tipo;
    private Object valor;
    private Integer Pos_Stack;
    private Tabla_Enums.tipo_Acceso acceso;
    private Tabla_Enums.tipo_Simbolo rol;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Tabla_Enums.tipo_primitivo_Simbolo getTipo() {
        return tipo;
    }

    public void setTipo(Tabla_Enums.tipo_primitivo_Simbolo tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Integer getPos_Stack() {
        return Pos_Stack;
    }

    public void setPos_Stack(Integer Pos_Stack) {
        this.Pos_Stack = Pos_Stack;
    }

    public Tabla_Enums.tipo_Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Tabla_Enums.tipo_Acceso acceso) {
        this.acceso = acceso;
    }

    public Tabla_Enums.tipo_Simbolo getRol() {
        return rol;
    }

    public void setRol(Tabla_Enums.tipo_Simbolo rol) {
        this.rol = rol;
    }   
    
}
