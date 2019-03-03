/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ERRORES;

import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian Azurdia
 */
public class Ventana_Errores extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Errores
     */
    
    public static DefaultTableModel modelo;
    
    public Ventana_Errores() {
        initComponents();
        mostrar_errores();
    }
    
    public void mostrar_errores()
    {
        modelo=(DefaultTableModel) jTErrores.getModel();
        String[] obj;
        int i=0;
        if(modelo.getRowCount()!=0){
            while(i!=-1){
                try{
                    modelo.removeRow(modelo.getRowCount()-1);
                }catch(Exception e){
                    i=-1;   
                }
            }
        }
        try{            
            Iterator<ERRORES.Nodo_Error> it = ERRORES.Tabla_Errores.getInstance().iterator();
            ERRORES.Nodo_Error error_aux;
            while(it.hasNext()){
                error_aux = it.next();
                obj = new String[6];
                obj[0] = error_aux.getArchivo();
                obj[1] = error_aux.getIdentificador();
                obj[2] = error_aux.getDescripcion();
                obj[3] = error_aux.getLinea();
                obj[4] = error_aux.getColumna();
                obj[5] = error_aux.getTipo();
                modelo.addRow(obj);
            }
           
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error Detectado", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTErrores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Archivo", "Id", "Descripcion", "Fila", "Columna", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTErrores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTErrores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTErrores;
    // End of variables declaration//GEN-END:variables
}