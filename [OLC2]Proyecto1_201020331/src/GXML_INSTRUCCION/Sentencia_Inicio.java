/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GXML_INSTRUCCION;

import FS_OBJETOS.FS_Arreglo;
import FS_OBJETOS.FS_Ventana;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import FS_TABLA_SIMBOLOS.Tabla_Enums;
import UI.ObjetoEntrada;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian Azurdia
 */
public class Sentencia_Inicio implements Instruccion
{
    private ArrayList<Instruccion> lista_instrucciones;
    private ObjetoEntrada entrada;
    private Entorno entorno_global;
    private boolean importar;
       
    //atributos que seran de utilidad para la traduccion
    private ArrayList<FS_Ventana> lista_ventana;
    
    //CONSTRUCTOR PARA LA GENERACION DE LA TRADCUCCION
    public Sentencia_Inicio(ArrayList<Instruccion> p_lista_instrucciones, ObjetoEntrada p_entrada, boolean p_importar)
    {        
        this.entorno_global = new Entorno(); //Este entorno solo sirve para guardar las ventanas
        this.entrada = p_entrada;
        this.lista_instrucciones = p_lista_instrucciones;
        this.importar = p_importar;
    }
    
    //CONSTRUCTOR PARA LA GENERACION DEL LEER GXML
    public Sentencia_Inicio(ArrayList<Instruccion> p_lista_instrucciones, Entorno entorno_local, ObjetoEntrada p_entrada, boolean p_importar)
    {        
        this.entorno_global = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().peek();
        this.entrada = p_entrada;
        this.lista_instrucciones = p_lista_instrucciones;
        this.importar = p_importar;
    }
     
    /*************************METODOS PARA TRADUCIR EL GMXL*************************************/
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local, String padre) 
    {        
        try
        {
            if(importar)
            {
                padre += primer_recorrido_traduccion(entorno_local);
                padre += segundo_recorrido_traduccion(entorno_local);
                
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.aceptado);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.cadena);
                nuevo_simbolo.setIdentificador("10-4");            
                nuevo_simbolo.setValor(padre);
                return nuevo_simbolo;
            }
            else
            {
                padre += primer_recorrido_traduccion(entorno_global);
                padre += segundo_recorrido_traduccion(entorno_global);   
                padre += imprimir_secundarias();
                padre += imprimir_principal();
                
                this.traduccion_gxml(padre);
                return null;
            }            
        }
        catch(Exception e)
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setIdentificador( 0 + " - " + 0);            
            nuevo_simbolo.setValor("Analisis Semantica de GXML no fue realizado, error: " + e.getMessage());
            return nuevo_simbolo;         
        }
    }
    
    //SE EJECUTARAN UNICAMENTE LAS VENTANAS Y SE ALMACENARAN
    private String primer_recorrido_traduccion(Entorno entorno_local)
    {
        Simbolo simbolo_ventana;
        String traduccion = "";
        for(int i = 0; i < lista_instrucciones.size(); i++)
        {
            if(lista_instrucciones.get(i) instanceof Sentencia_Ventana)
            {   //el entorno que se envia solo almacena las ventnas para saber si hay repetidas o cual es la principal
                simbolo_ventana = lista_instrucciones.get(i).ejecutar(entorno_local,"");
                if(simbolo_ventana.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    traduccion += simbolo_ventana.getValor().toString();
                }
                else
                {
                    manejoErrorEjecucion(simbolo_ventana);
                }                                
            }
        }
        return traduccion;
    }
    
    //SE EJECUTARAN UNICAMENTE LOS IMPORT DE LOS OTROS ARCHIVOS
    private String segundo_recorrido_traduccion(Entorno entorno_local)
    {
        Simbolo simbolo_importar;
        String traduccion = "";
        for(int i = 0; i < lista_instrucciones.size(); i++)
        {
            if(lista_instrucciones.get(i) instanceof Sentencia_Importar)
            {
                simbolo_importar = lista_instrucciones.get(i).ejecutar(entorno_local,"");
                if(simbolo_importar.getTipo() != Tabla_Enums.tipo_primitivo_Simbolo.error)
                {
                    traduccion += simbolo_importar.getValor();
                }
                else
                {
                    manejoErrorEjecucion(simbolo_importar);
                }
            }
        }
        return traduccion;
    }
    
    private String imprimir_secundarias()
    {
        String traduccion = "";
        Iterator simbolo_aux = entorno_global.entrySet().iterator();
        while (simbolo_aux.hasNext()) 
        {
            Map.Entry e = (Map.Entry)simbolo_aux.next();            
            Simbolo simbolo_analizar = (Simbolo) e.getValue();
            if(simbolo_analizar.getValor() instanceof  FS_Ventana)
            {
                FS_Ventana secundaria = (FS_Ventana) simbolo_analizar.getValor();
                if(!secundaria.getTipo())
                {
                    traduccion += "vent_" + secundaria.getIdentificador() + ".AlCargar("+ secundaria.traduciralCargar() +");\n";
                    traduccion += "vent_" + secundaria.getIdentificador() + ".AlCerrar("+ secundaria.traduciralCerrar() +");\n";
                }
            }            
        }
        return traduccion;
    }
    
    private String imprimir_principal()
    {
        Iterator simbolo_aux = entorno_global.entrySet().iterator();
        while (simbolo_aux.hasNext()) 
        {
            Map.Entry e = (Map.Entry)simbolo_aux.next();            
            Simbolo simbolo_analizar = (Simbolo) e.getValue();
            if(simbolo_analizar.getValor() instanceof  FS_Ventana)
            {
                FS_Ventana principal = (FS_Ventana) simbolo_analizar.getValor();
                if(principal.getTipo())
                {
                    return "vent_" + principal.getIdentificador() + ".AlCargar("+ principal.traduciralCargar() +");";
                }
            }            
        }
        return "";
    }
    
    //SE EJECUTARA LA LISTA PARA LA CREACION DEL ARREGLO GXML
    private void traduccion_gxml(String traduccion)
    {
        if(!traduccion.equalsIgnoreCase("")){
            try
            {          
                System.out.flush();
                System.out.println(traduccion);
                FS_ANALIZADORES.Lexico_FS lexico_fs = new FS_ANALIZADORES.Lexico_FS(new BufferedReader(new StringReader(traduccion)));
                FS_ANALIZADORES.Sintactico_FS sintactico_fs = new FS_ANALIZADORES.Sintactico_FS(lexico_fs);
                sintactico_fs.setObjetoEntrada(entrada);
                sintactico_fs.setImportar(false);
                sintactico_fs.parse();                
               
            }
            catch(Exception e)
            {
                Simbolo nuevo_simbolo = new Simbolo();
                nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
                nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                nuevo_simbolo.setIdentificador( 0 + " - " + 0);            
                nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: " + e.getMessage());
                manejoErrorEjecucion(nuevo_simbolo);
            }
        }
        else
        {
            Simbolo nuevo_simbolo = new Simbolo();
            nuevo_simbolo.setAcceso(Tabla_Enums.tipo_Acceso.publico);
            nuevo_simbolo.setRol(Tabla_Enums.tipo_Simbolo.error);
            nuevo_simbolo.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
            nuevo_simbolo.setIdentificador( 0 + " - " + 0);            
            nuevo_simbolo.setValor("Creación de Ventana no fue realizada, error: no fue generado una salida a traducir.");
            manejoErrorEjecucion(nuevo_simbolo);
        }
    }
    
    /*************************METODOS PARA LEER EL GMXL****************************************/
    
    @Override
    public Simbolo ejecutar(Entorno entorno_local,ObjetoEntrada salida) 
    {
        try
        {
            if(importar)
            {
                this.primer_recorrido_ejecucion(salida);
                this.segundo_recorrido_ejecucion(salida);
                return null;
            }
            else
            {
                this.primer_recorrido_ejecucion(salida);
                this.segundo_recorrido_ejecucion(salida);
                this.ejecutar_gxml();
                return null;
            }                        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }                          
    }
    
    //SE EJECUTARAN UNICAMENTE LAS VENTANAS Y SE ALMACENARAN
    private void primer_recorrido_ejecucion(ObjetoEntrada salida)
    {
        Simbolo simbolo_ventana;
        for(int i = 0; i < lista_instrucciones.size(); i++)
        {
            if(lista_instrucciones.get(i) instanceof Sentencia_Ventana)
            {
                simbolo_ventana = lista_instrucciones.get(i).ejecutar(entorno_global, salida);
                manejoErrorEjecucion(simbolo_ventana);
            }
        }
    }
    
    //SE EJECUTARAN UNICAMENTE LOS IMPORT DE LOS OTROS ARCHIVOS
    private void segundo_recorrido_ejecucion(ObjetoEntrada salida)
    {
        Simbolo simbolo_importar;
        for(int i = 0; i < lista_instrucciones.size(); i++)
        {
            if(lista_instrucciones.get(i) instanceof Sentencia_Importar)
            {
                simbolo_importar = lista_instrucciones.get(i).ejecutar(entorno_global, salida);
                manejoErrorEjecucion(simbolo_importar);
            }
        }        
    }
    
    //SE EJECUTARA LA LISTA PARA LA CREACION DEL ARREGLO GXML
    private void ejecutar_gxml()
    {
        Iterator simbolo_aux = entorno_global.entrySet().iterator();
        while (simbolo_aux.hasNext()) 
        {
            Map.Entry e = (Map.Entry)simbolo_aux.next();            
            Simbolo simbolo_analizar = (Simbolo) e.getValue();
            if(simbolo_analizar.getValor() instanceof  FS_Ventana)
            {
                FS_Ventana principal = (FS_Ventana) simbolo_analizar.getValor();
                if(principal.getTipo())
                {
                    principal.show();
                }
            }            
        }
    }

    /*********************IMPRESION DE ERRORES***********************************/
    private void manejoErrorEjecucion(Simbolo simbolo_resultado)
    {
        if(simbolo_resultado.getTipo() == Tabla_Enums.tipo_primitivo_Simbolo.error)
        {
            String[] pos = simbolo_resultado.getIdentificador().split("-");
            ERRORES.Nodo_Error error_encontrado = new ERRORES.Nodo_Error();
            error_encontrado.setArchivo(entrada.getNombre_archivo());
            error_encontrado.setIdentificador("Análisis Semantico FuncionScript");
            error_encontrado.setDescripcion(simbolo_resultado.getValor().toString());
            error_encontrado.setLinea(pos[0]);
            error_encontrado.setColumna(pos[1]);
            error_encontrado.setTipo("Semantico");
            ERRORES.Tabla_Errores.getInstance().add(error_encontrado); 
        }
    }
}
