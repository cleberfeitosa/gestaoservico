/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ModuloConexao;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author clebe
 */
public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement ps;
    ResultSet rs;

    public TelaLogin() {
        initComponents();
         
    }

    public void status() {
        try {
            conexao = ModuloConexao.conectar();
            if (conexao != null) {
                jLblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dbok.png")));
            } else {
                jLblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dberror.png")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void logar(){
        String sql = "select * from tbusuarios where login = ? and senha = md5(?)";
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, jTxtUsuario.getText());
            String captura = new String(jTxtSenha.getPassword());
            ps.setString(2, captura);
            rs = ps.executeQuery();
            if(rs.next()){
              String perfil = rs.getString(6);
                if (perfil.equals("Administrador")) {
                    TelaPrincipal telaprincipal = new TelaPrincipal();
                    telaprincipal.setVisible(true);
                    TelaPrincipal.itmMnUsuario.setEnabled(true);
                    TelaPrincipal.mnRelatorio.setEnabled(true);
                    TelaPrincipal.jLblUsuario.setText(rs.getString(2));
                    TelaPrincipal.jLblUsuario.setForeground(Color.blue);
                    dispose();
                } else {
                  TelaPrincipal telaprincipal = new TelaPrincipal();
                  telaprincipal.setVisible(true);
                  TelaPrincipal.jLblUsuario.setText(rs.getString(2));
                  TelaPrincipal.jLblUsuario.setText(rs.getString(2));
                  dispose();
                 }
        } else{
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido!!!");
                jTxtUsuario.setText(null);
                jTxtSenha.setText(null);
                jTxtUsuario.requestFocus();
                jBtnEntrar.setVisible(false);
            }
        }catch (Exception e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtUsuario = new javax.swing.JTextField();
        jTxtSenha = new javax.swing.JPasswordField();
        jBtnEntrar = new javax.swing.JButton();
        jLblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de OS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        jTxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtUsuarioKeyPressed(evt);
            }
        });

        jTxtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtSenhaKeyPressed(evt);
            }
        });

        jBtnEntrar.setText("Entrar");
        jBtnEntrar.setEnabled(false);
        jBtnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEntrarActionPerformed(evt);
            }
        });
        jBtnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnEntrarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnEntrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtUsuario)
                            .addComponent(jTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEntrar)
                    .addComponent(jLblStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        status();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jBtnEntrar.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void jBtnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEntrarActionPerformed
       if(jTxtUsuario.getText().isEmpty() || jTxtSenha.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Entre com usário e senha");
           jTxtUsuario.requestFocus();
       }else{
           logar();
       }
    }//GEN-LAST:event_jBtnEntrarActionPerformed

    private void jBtnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnEntrarKeyPressed
       if(jTxtUsuario.getText().isEmpty() || jTxtSenha.getText().isEmpty()) {
           JOptionPane.showMessageDialog(null, "Entre com usário e senha");
           jTxtUsuario.requestFocus();
       }else{
           logar();
       }
    }//GEN-LAST:event_jBtnEntrarKeyPressed

    private void jTxtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtSenhaKeyPressed
        if(jTxtSenha.getText().length()>=4){
            jBtnEntrar.setEnabled(true);
            //jBtnEntrar.requestFocus();
        }
    }//GEN-LAST:event_jTxtSenhaKeyPressed

    private void jTxtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtUsuarioKeyPressed
           if(jTxtUsuario.getText().length()>3){
           jBtnEntrar.setVisible(true);
       }
    }//GEN-LAST:event_jTxtUsuarioKeyPressed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblStatus;
    private javax.swing.JPasswordField jTxtSenha;
    private javax.swing.JTextField jTxtUsuario;
    // End of variables declaration//GEN-END:variables
}
