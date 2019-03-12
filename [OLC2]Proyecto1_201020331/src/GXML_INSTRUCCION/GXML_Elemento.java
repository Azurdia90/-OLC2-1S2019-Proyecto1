/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
/**
 *
 * @author Cristian Azurdia
 */
public class GXML_Elemento 
{
    private int fila;
    private int columna;
    
    private String identificador;
    private Tabla_Enums.tipo_primitivo_Simbolo tipo_elemento;
    private Object valor;
    
    public GXML_Elemento(String p_identificador, Tabla_Enums.tipo_primitivo_Simbolo tipo_elemento, Object p_valor, int p_fila, int p_columna)
    {
        this.fila = p_fila;
        this.columna = p_columna;
        
        this.identificador = p_identificador;
        this.tipo_elemento = tipo_elemento;
        this.valor = p_valor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Tabla_Enums.tipo_primitivo_Simbolo getTipo_elemento() {
        return tipo_elemento;
    }

    public void setTipo_elemento(Tabla_Enums.tipo_primitivo_Simbolo simbolo) {
        this.tipo_elemento = simbolo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    //METODO QUE PERMITE VERIFICAR SI EL ELEMENTO CORRESPONDE O NO 
    public boolean IsElementOrNot(String p_nombre)
    {
        return this.identificador.equals(p_nombre);
    }//private boolean IsNodoOrNot(String p_nombre)      
}
