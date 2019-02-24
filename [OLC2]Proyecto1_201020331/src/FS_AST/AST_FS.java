package FS_AST;

import UI.ObjetoEntrada;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import FS_TABLA_SIMBOLOS.Tabla_Enums;
import FS_INSTRUCCIONES.*;
import FS_OBJETOS.Funcion;
import FS_TABLA_SIMBOLOS.Entorno;
import javax.swing.JPanel;

/**
 *
 * @author crist
 */
public class AST_FS 
{
    private ObjetoEntrada entrada;
    private Nodo_AST_FS raiz;
   
    private Entorno entorno_global;    
    
    private ArrayList<Nodo_AST_FS> lista_sentencias;
    
    public AST_FS(Nodo_AST_FS p_raiz, ObjetoEntrada p_entrada)
    {
        this.entrada = p_entrada;
        this.raiz = p_raiz;
        entorno_global = new Entorno(); //esto es temporal        
    }    
    
    public void ejecutar_AST()
    {
        if(raiz.IsNodoOrNot("AST"))
        {
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Agregar(entorno_global);
            this.primer_recorrido_AST(raiz.getHijos().get(0));
            this.segundo_recorrido_AST(raiz.getHijos().get(0));
            FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().getMi_Stack().Desapilar();
        }
        else
        {
            //ocurrrio error por algun motivo no se guardo la raiz
        }
    }
    
    public void primer_recorrido_AST(Nodo_AST_FS nodo)
    {
        if(nodo.getHijos().size() > 0)
        {  
            for(int i=0; i < nodo.getHijos().size(); i++)
            {
                if(nodo.getHijos().get(i).IsNodoOrNot("CUERPO_FS"))
                {
                    primer_recorrido_AST(nodo.getHijos().get(0));
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("IMPORTAR"))
                {
                
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_DECLARACION"))
                {
                    Nodo_AST_FS nodo_declaracion = nodo.getHijos().get(i);
                    Sentencia_Declaracion sentencia_declaracion = new Sentencia_Declaracion(nodo_declaracion);
                    sentencia_declaracion.ejecutar(entorno_global,entrada);
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("FUNCION"))
                {
                    Nodo_AST_FS nodo_funcion = nodo.getHijos().get(i);
                    Funcion funcion= new Funcion(nodo_funcion);
                    if(!FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().existe_metodo(funcion.getIdentificador(), funcion.getLista_parametros().size()))
                    {
                        FS_TABLA_SIMBOLOS.Tabla_Simbolos.getInstance().agregar_metodo(funcion);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"El metodo ya existe.");
                    }
                }
            }            
        }
    }
    
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
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_ASIGNACION"))
                {
                    Nodo_AST_FS nodo_asignacion = nodo.getHijos().get(i);
                    Sentencia_Asignacion asignacion = new Sentencia_Asignacion(nodo_asignacion);
                    asignacion.ejecutar(entorno_global, entrada);
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_IMPRIMIR"))
                {
                    Nodo_AST_FS nodo_imprimir = nodo.getHijos().get(i);
                    Sentencia_Imprimir imprimir = new Sentencia_Imprimir(nodo_imprimir);
                    imprimir.ejecutar(entorno_global, entrada);
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_SI"))
                {
                    Nodo_AST_FS nodo_si = nodo.getHijos().get(i);
                    Sentencia_Si sentencia_si = new Sentencia_Si(nodo_si);
                    sentencia_si.ejecutar(entorno_global, entrada);
                }
                else if(nodo.getHijos().get(i).IsNodoOrNot("SENTENCIA_SELECCIONA"))
                {
                    Nodo_AST_FS nodo_selecciona = nodo.getHijos().get(i);
                    Sentencia_Selecciona sentencia_selecciona = new Sentencia_Selecciona(nodo_selecciona);
                    sentencia_selecciona.ejecutar(entorno_global, entrada);
                }
            }            
        }        
    }    
}
