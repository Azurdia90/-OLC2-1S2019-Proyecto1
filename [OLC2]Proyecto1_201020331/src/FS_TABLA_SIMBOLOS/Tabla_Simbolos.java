/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_TABLA_SIMBOLOS;

import FS_INSTRUCCIONES.Instruccion;
import FS_OBJETOS.Funcion;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Azurdia
 */
public class Tabla_Simbolos 
{
    private static Tabla_Simbolos _Instance = new Tabla_Simbolos();
    
    private Pila_Entornos mi_Stack;
    private HashMap<String,Funcion> tabla_funciones;    
    private ArrayList<Instruccion> lista_instrucciones;
    
    public Tabla_Simbolos()
    {
        this.tabla_funciones = new HashMap<String,Funcion>();
        this.lista_instrucciones = new ArrayList<Instruccion>();
        this.mi_Stack = new Pila_Entornos();
    }
    
    /***************************METODOS PARA FUNCIONES******************************/
    
    public boolean existe_metodo(String p_id)
    {
        return tabla_funciones.containsKey(p_id);
    }
    
    public Funcion obtener_Metodo(String p_id)
    {
        return tabla_funciones.get(p_id);
    }
    
    public void agregar_metodo(String p_id, Funcion p_funcion)
    {
        this.tabla_funciones.put(p_id, p_funcion);
    }
    /*************************FIN METODOS PARA FUNCIONES******************************/
    
    /*************************METODOS PARA INSTRUCCIONES*********************************/
    public void setLista_instrucciones(ArrayList<Instruccion> lista_instrucciones) 
    {
        this.lista_instrucciones = lista_instrucciones;
    }

    public ArrayList<Instruccion> getLista_instrucciones()
    {
        return lista_instrucciones;
    }
    /*************************FIN METODOS PARA INSTRUCCIONES****************************/
    
    /*************************METODOS PARA VARIABLES*********************************/
    public Simbolo obtener_Simbolo(Entorno entorno_local, String p_key) {
        return mi_Stack.Buscar_Simbolo(entorno_local, p_key);
    }

    /*************************FIN METODOS PARA VARIABLES****************************/
    
    /*************************METODOS PARA PILA VARIABLES*********************************/    
    public Pila_Entornos getMi_Stack() 
    {
        return mi_Stack;
    }
    
    public void setMi_Stack(Pila_Entornos mi_Stack) 
    {
        this.mi_Stack = mi_Stack;
    }
    
    /*************************FIN METODOS PARA PILA VARIABLES****************************/
    
    /*************************METODOS PARA MANEJO TABLA SIMBOLOS************************/
    public void Limpiar()
    {
        this.mi_Stack.vaciar();
        this.tabla_funciones.clear();
    }            
    
    /**********************FIN METODOS PARA MANEJO TABLA SIMBOLOS***********************/
    
    /****************************METODOS PARA SINGLETON********************************/
    public static Tabla_Simbolos getInstance() 
    {
        if(_Instance != null)
        {
            return _Instance;
        }
        else
        {
            _Instance = new Tabla_Simbolos();
            JOptionPane.showMessageDialog(null,"Existio un error y fue necesario instanciar nuevamente la Tabla de Simbolos.");
            return _Instance;
        }
    }

    public static void setInstance(Tabla_Simbolos _Instance) 
    {
        Tabla_Simbolos._Instance = _Instance;
    }
    
    /****************************FIN METODOS PARA SINGLETON*****************************/
    
         /*ENUM PARA TIPOS*/    
    public static enum tipo_primitivo_Simbolo
    {
        nulo,booleano,entero,decimal,caracter,cadena,identificador,error,detener,retornar
    }
    
    /*ENUM PARA NIVEL ACCESO*/
    public static enum tipo_Acceso
    {
        publico,privado,protegico
    }
    
    /*ENUM PARA ROL DEL SIMBOLO*/
    public static enum tipo_Simbolo
    {
        variable,arreglo,objeto,aceptado,error
    }
    
    public static enum tipo_operacion
    {
        suma_entero,suma_decimal,suma_entero_caracter,suma_caracter_entero,
        suma_decimal_caracter,suma_caracter_decimal,       
        resta_entero,resta_decimal,resta_entero_caracter,resta_caracter_entero,
        resta_decimal_caracter,resta_caracter_decimal,
        multiplicacion_entero,multiplicacion_decimal,
        multiplicacion_entero_caracter,multiplicacion_caracter_entero,
        multiplicacion_decimal_caracter,multiplicacion_caracter_decimal,
        division_decimal,division_decimal_caracter,division_caracter_decimal,        
        modulo_entero,modulo_decimal,modulo_entero_caracter,modulo_caracter_entero,
        modulo_decimal_caracter,modulo_caracter_decimal,
        potencia_entero,potencia_decimal,potencia_entero_caracter,
        potencia_caracter_entero,potencia_entero_booleano,potencia_booleano_entero,
        potencia_decimal_caracter,potencia_caracter_decimal,
        potencia_decimal_booleano,potencia_booleano_decimal,
        concatenacion,
        mayorque_numerico,mayorque_caracter,mayorque_booleano,
        mayorque_numerico_caracter,mayorque_caracter_numerico,
        menorque_numerico,menorque_caracter,menorque_booleano,
        menorque_numerico_caracter,menorque_caracter_numerico,
        mayorigualque_numerico,mayorigualque_caracter,mayorigualque_booleano,
        mayorigualque_numerico_caracter,mayorigualque_caracter_numerico,
        menorigualque_numerico,menorigualque_caracter,menorigualque_booleano,
        menorigualque_numerico_caracter,menorigualque_caracter_numerico,
        diferente_numerico,diferente_caracter,diferente_booleano,
        diferente_numerico_caracter,diferente_caracter_numerico,
        igual_numerico,igual_caracter,igual_booleano,
        igual_numerico_caracter,igual_caracter_numerico,
        not,and,or,
        error
    }
    
    
}
