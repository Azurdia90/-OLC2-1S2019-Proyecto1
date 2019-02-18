package FS_AST;

import java.util.ArrayList;

/**
 *
 * @author crist
 */
public class Nodo_AST_FS 
{
    String etiqueta;
    String valor;
    String columna;
    String fila;
    ArrayList<Nodo_AST_FS> hijos;
    
    public Nodo_AST_FS(){
        this.etiqueta = "";
        this.valor = "";
        this.columna = "";
        this.fila = "";
        this.hijos = new ArrayList<Nodo_AST_FS>();
    }
    
    public Nodo_AST_FS(String p_etiqueta, String p_valor, String p_columna, String p_fila, String p_tamaño){
        this.etiqueta = p_etiqueta;
        this.valor = p_valor;
        this.columna = p_columna;
        this.fila = p_fila;
        this.hijos = new ArrayList<Nodo_AST_FS>();
    }    

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public ArrayList<Nodo_AST_FS> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo_AST_FS> hijos) {
        this.hijos = hijos;
    }        
    
    //METODO QUE PERMITE VERIFICAR SI EL NO TERMINAL CORRESPONDE O NO 
    public boolean IsNodoOrNot(String p_nombre)
    {
        return this.etiqueta.equals(p_nombre);
    }//private boolean IsNodoOrNot(String p_nombre)  
}
