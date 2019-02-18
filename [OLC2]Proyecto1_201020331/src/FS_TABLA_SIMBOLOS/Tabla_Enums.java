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
public class Tabla_Enums 
{
     /*ENUM PARA TIPOS*/    
    public static enum tipo_primitivo_Simbolo
    {
        nulo,booleano,entero,decimal,caracter,cadena,identificador,error
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
        mayorque,menorque,mayorigualque,menorigualque,diferente,igual,
        not,and,or,
        error
    }
}
