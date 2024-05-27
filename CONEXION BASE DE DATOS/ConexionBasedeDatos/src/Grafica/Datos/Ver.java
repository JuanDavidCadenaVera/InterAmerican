/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Grafica.Datos;

import Grafica.Administrativo.EstudianteA;
import Grafica.Administrativo.ProfesorA;
import java.util.List;
import ucompensar.codigo.clases.Estudiante;
import ucompensar.codigo.clases.Profesor;

/**
 *
 * @author fabia
 */
public class Ver extends javax.swing.JFrame {
    private EstudianteA estudianteA;
    private ProfesorA profesorA;
    
    /**
     * Creates new form VerProfesor
     */
    public Ver(String email, String contraseña, String tipoUsuario) {
        initComponents();
        setLocationRelativeTo(null);
        if (tipoUsuario.equals("estudiante")) {
            estudianteA = new EstudianteA(email, contraseña);
        } else if (tipoUsuario.equals("profesor")) {
            profesorA = new ProfesorA(email, contraseña);
        }
        
        if (estudianteA != null) {
        listadotexto.setFont(new java.awt.Font("Segoe UI", 1, 36));
        listadotexto.setText("LISTA DE ESTUDIANTES");
        } else if (profesorA != null) {
        listadotexto.setFont(new java.awt.Font("Segoe UI", 1, 36));
        listadotexto.setText("LISTA DE PROFESORES");
        }
        
        
        listar();
    }
    
    private void listar() {
        if (estudianteA != null) {
            listarEstudiantes();
        } else if (profesorA != null) {
            listarProfesores();
        }
    }
    
    private void listarEstudiantes() {
        List<String> estudiantes = Estudiante.listarTodosLosEstudiantes();
        StringBuilder infoEstudiantes = new StringBuilder();
        for (String estudiante : estudiantes) {
            infoEstudiantes.append(estudiante).append("\n\n");
        }
        Listado.setText(infoEstudiantes.toString());
        Listado.setCaretPosition(0);
    }

    private void listarProfesores() {
        List<String> profesores = Profesor.listarTodosLosProfesores();
        StringBuilder infoProfesores = new StringBuilder();
        for (String profesor : profesores) {
            infoProfesores.append(profesor).append("\n");
        }
        Listado.setText(infoProfesores.toString());
        Listado.setCaretPosition(0);
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listado = new javax.swing.JTextArea();
        listadotexto = new javax.swing.JLabel();
        atras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Listado.setEditable(false);
        Listado.setBackground(new java.awt.Color(255, 255, 255));
        Listado.setColumns(20);
        Listado.setRows(5);
        jScrollPane1.setViewportView(Listado);

        listadotexto.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        listadotexto.setText("LISTA DE PERSONAS");

        atras.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        atras.setText("←");
        atras.setBorder(null);
        atras.setBorderPainted(false);
        atras.setContentAreaFilled(false);
        atras.setDefaultCapable(false);
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(atras)
                        .addGap(93, 93, 93)
                        .addComponent(listadotexto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listadotexto)
                    .addComponent(atras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        // TODO add your handling code here:
        this.dispose();
        if(estudianteA != null){
          estudianteA.setVisible(true);
        } else {
            profesorA.setVisible(true);
        }
        
    }//GEN-LAST:event_atrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ver("","","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Listado;
    private javax.swing.JButton atras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listadotexto;
    // End of variables declaration//GEN-END:variables
}
