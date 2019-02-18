/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FS_AST;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author crist
 */
public class Grafica_AST_FS 
{
    private Nodo_AST_FS raiz;
    
    private int contador;
    private String grafo;
    
    public Grafica_AST_FS(Nodo_AST_FS raiz)
    {
        this.raiz = raiz;
    }
    
    public void graficar_AST()
    {
        try{
            generaDot_AST((Nodo_AST_FS) raiz);         
        }
        catch(Exception e)
        {
            System.out.println("Error de Ejecución: " + e.toString());
        }
    }
    
/******************************************************************************/
    
    public void generaDot_AST(Nodo_AST_FS raiz)
    {
        grafo = "Digraph G{\n";
        grafo += "node[shape=\"box\"]";
        grafo += "nodo0[label=\"" + escapar(raiz.getEtiqueta()) + "\"];\n";
        contador = 1;
        recorrerAST("nodo0",raiz);
        grafo += "}";        
        graficar_AST(grafo,"AST_FS");
    }
    
    private void recorrerAST(String padre, Nodo_AST_FS hijos)
    {
        for(int x=0;x<hijos.getHijos().size();x++)
        {
            Nodo_AST_FS hijo = hijos.getHijos().get(x);
            String nombreHijo = "nodo" + contador;
            grafo += nombreHijo + "[label=\"" + escapar(hijo.getEtiqueta()) + "\"];\n";
            grafo += padre + "->" + nombreHijo + ";\n";
            contador++;
            if(!"".equals(hijo.getValor()) && !padre.equalsIgnoreCase("dato"))
            {
                String nombreNieto = "nodo" + contador;
                grafo += nombreNieto + "[label=\"" + escapar(hijo.getValor()) + "\"];\n";
                grafo += nombreHijo + "->" + nombreNieto + ";\n";
                contador++;
            }
            recorrerAST(nombreHijo,hijo);
        }        
    }

    
    public void graficar_AST(String cadena, String nombre){

        FileWriter fichero = null;            
        PrintWriter pw = null;        
        String archivo = nombre + ".dot";
        
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println(cadena);
            fichero.close();
        } catch (Exception e) {
            System.out.println("Error de Ejecución: " + e.toString());
        }
        try {
            String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe -Tjpg " + nombre + ".dot -o " + nombre + ".jpg";
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            System.out.println("Error de Ejecución: " + e.toString());
        }
    }//FIN METODO GRAFICAR

/******************************************************************************/    
    
    private static String escapar(String cadena)
    {
        cadena = cadena.replace("\\", "\\\\");
        cadena = cadena.replace("\"", "\\\"");
        return cadena;
    }

}
