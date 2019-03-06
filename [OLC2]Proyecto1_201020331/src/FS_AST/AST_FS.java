package FS_AST;

import UI.ObjetoEntrada;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_INSTRUCCIONES.*;
import FS_OBJETOS.Funcion;
import FS_TABLA_SIMBOLOS.Entorno;
import FS_TABLA_SIMBOLOS.Simbolo;
import javax.swing.JPanel;

/**
 *
 * @author crist
 */
public class AST_FS 
{
    private ObjetoEntrada entrada;
    private Nodo_AST_FS raiz;
    private boolean importar;
    private Entorno entorno_global;    
        
    //CONSTRUCTOR QUE SE UTILIZA DESDE LA GRAMATICA ORIGINAL
    public AST_FS(Nodo_AST_FS p_raiz, ObjetoEntrada p_entrada, boolean p_importar)
    {
        this.entrada = p_entrada;
        this.raiz = p_raiz;
        this.importar = p_importar;
        
        if(p_importar)
        {
            entorno_global = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().peek();
        }
        else
        {
            entorno_global = new Entorno();     
        }        
    }    
    
    public void ejecutar_AST()
    {
        if(raiz.IsNodoOrNot("AST") && importar == false)
        {
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_global);
            this.primer_recorrido_AST(raiz.getHijos().get(0));
            this.segundo_recorrido_AST(raiz.getHijos().get(0));
            this.tercer_recorrido_AST(raiz.getHijos().get(0));
            this.ejecutar_FS();
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
        }
        else if(raiz.IsNodoOrNot("AST") && importar == true)
        {
            this.primer_recorrido_AST(raiz.getHijos().get(0));
            this.segundo_recorrido_AST(raiz.getHijos().get(0)); 
            this.tercer_recorrido_AST(raiz.getHijos().get(0));            
        }
        else
        {
            //ocurrrio error por algun motivo no se guardo la raiz
        }
    }
    
    //PRIMER RECORRECORRIDO ES PARA GUARDAR FUNCIONES Y VARIABLES DEL ARCHIVO BASE
    public void primer_recorrido_AST(Nodo_AST_FS nodo)
    {
        if(nodo.getHijos().size() > 0)
        {  
            Simbolo resultado;
            for(int i=0; i < nodo.getHijos().size(); i++)
            {
                if(nodo.getHijos().get(i).IsNodoOrNot("CUERPO_FS"))
                {
                    primer_recorrido_AST(nodo.getHijos().get(0));
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_DECLARACION"))
                {
                    Nodo_AST_FS nodo_declaracion = nodo.getHijos().get(i);
                    Sentencia_Declaracion sentencia_declaracion = new Sentencia_Declaracion(nodo_declaracion);
                    resultado = sentencia_declaracion.ejecutar(entorno_global,entrada);
                    manejoErrorEjecucion(resultado);
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("FUNCION"))
                {
                    Nodo_AST_FS nodo_funcion = nodo.getHijos().get(i);
                    Funcion funcion= new Funcion(nodo_funcion);
                    if(!FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().existe_metodo(funcion.getIdentificador()))
                    {
                        FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().agregar_metodo(nodo_funcion.getValor(),funcion);
                    }
                    else
                    {
                        resultado = new Simbolo();
                        resultado.setRol(Tabla_Enums.tipo_Simbolo.error);
                        resultado.setAcceso(Tabla_Enums.tipo_Acceso.publico);
                        resultado.setIdentificador(nodo.getHijos().get(i).getFila() + " - " + nodo.getHijos().get(i).getColumna());
                        resultado.setTipo(Tabla_Enums.tipo_primitivo_Simbolo.error);
                        resultado.setValor("La funcion \"" + nodo.getHijos().get(i).getValor() + "\" ya existe."); 
                        manejoErrorEjecucion(resultado);
                    }
                }
            }            
        }
    }
    
    //TERCER RECORRECORRIDO ES PARA GUARDAR SENTENCIAS
    public void segundo_recorrido_AST(Nodo_AST_FS nodo)
    {
        if(nodo.getHijos().size() > 0)
        {
            for(int i=0; i < nodo.getHijos().size(); i++)
            {
                if(nodo.getHijos().get(i).IsNodoOrNot("CUERPO_FS"))
                {
                    segundo_recorrido_AST(nodo.getHijos().get(0));
                }               
                else if(!(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_IMPORTAR") || nodo.getHijos().get(i).IsNodoOrNot("FUNCION") || nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_DECLARACION")))
                {
                    Fabrica_Sentencias fabrica;
                    Instruccion instruccion_aux;
                    fabrica = new Fabrica_Sentencias(nodo.getHijos().get(i));
                    instruccion_aux = fabrica.ejecutar();
                    if(instruccion_aux != null)
                    {
                       FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getLista_instrucciones().add(instruccion_aux);
                    }                    
                }
            }
        }
    }    
    
    //SEGUNDO RECORRECORRIDO ES PARA GUARDAR FUNCIONES, VARIABLES, SENTENCIAS DE ARCHIVOS IMPORTADOS
    public void tercer_recorrido_AST(Nodo_AST_FS nodo)
    {
        Simbolo resultado;
        if(nodo.getHijos().size() > 0)
        {                       
            for(int i=0; i < nodo.getHijos().size(); i++)
            {
                if(nodo.getHijos().get(i).IsNodoOrNot("CUERPO_FS"))
                {
                    tercer_recorrido_AST(nodo.getHijos().get(0));
                }               
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_IMPORTAR"))
                {                    
                    Nodo_AST_FS nodo_importar = nodo.getHijos().get(i);
                    Sentencia_Importar asignacion = new Sentencia_Importar(nodo_importar);
                    resultado = asignacion.ejecutar(entorno_global, entrada);
                    manejoErrorEjecucion(resultado);
                }
            }            
        }        
    }
        
    //TERCER RECORRECORRIDO ES PARA EJECUTAR SENTENCIAS DE ARCHIVO BASE
    public void ejecutar_FS()
    {
        Simbolo resultado;
        if(FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getLista_instrucciones().size() > 0)
        {                       
            for(int i=0; i < FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getLista_instrucciones().size(); i++)
            {                
                resultado = FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getLista_instrucciones().get(i).ejecutar(entorno_global, entrada);                    
                manejoErrorEjecucion(resultado);
            }            
        }        
    }
        
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
