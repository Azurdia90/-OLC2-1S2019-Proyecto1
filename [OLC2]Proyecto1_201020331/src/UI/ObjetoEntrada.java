/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JTextPane;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author Cristian Azurdia
 */
public class ObjetoEntrada 
{    
    private boolean guardar_como;
    private RSyntaxTextArea JTEntrada;
    private JTextPane consola;        
    
    private String path_archivo;
    private String nombre_archivo;
    private String extesion_archivo;
    
    public ObjetoEntrada()
    {
        guardar_como = true;
        this.JTEntrada = new RSyntaxTextArea();  
        this.JTEntrada.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        this.JTEntrada.setCodeFoldingEnabled(true);
        this.consola = null;
        
        this.path_archivo = "";
        this.nombre_archivo = "";
        this.extesion_archivo = "";        
    }

    public boolean getGuardar_como() {
        return guardar_como;
    }

    public void setGuardar_como(boolean guardar_como) {
        this.guardar_como = guardar_como;
    }

    public RSyntaxTextArea getJTEntrada() {
        return JTEntrada;
    }

    public void setJTEntrada(RSyntaxTextArea JTEntrada) {
        this.JTEntrada = JTEntrada;
    }

    public JTextPane getConsola() {
        return consola;
    }

    public void setConsola(JTextPane consola) {
        this.consola = consola;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getExtesion_archivo() {
        return extesion_archivo;
    }

    public void setExtesion_archivo(String extesion_archivo) {
        this.extesion_archivo = extesion_archivo;
        if(this.extesion_archivo.equalsIgnoreCase("gxml"))
        {
            this.JTEntrada.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
            this.JTEntrada.setCodeFoldingEnabled(true);
            this.JTEntrada.repaint();
        }
        else
        {
            this.JTEntrada.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
            this.JTEntrada.setCodeFoldingEnabled(true);
            this.JTEntrada.repaint();
        }
    }

             
}
