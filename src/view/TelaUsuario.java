/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import Atxy2k.CustomTextField.RestrictedTextField;
import controller.UsuarioDAO;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author clebe
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    public TelaUsuario() {
        initComponents();
        RestrictedTextField validarId;
        validarId = new RestrictedTextField(jTxtid);
        validarId.setOnlyNums(true);
        validarId.setLimit(4);
        RestrictedTextField validarUsuario;
        validarUsuario = new RestrictedTextField(jTxtNome);
        validarUsuario.setLimit(30);
        RestrictedTextField validarFone;
        validarFone = new RestrictedTextField(jTxtFone);
        validarFone.setLimit(15);
        RestrictedTextField validarLogin;
        validarLogin = new RestrictedTextField(jTxtLogin);
        validarLogin.setLimit(15);
        RestrictedTextField validarSenha;
        validarSenha = new RestrictedTextField(jTxtSenha);
        validarSenha.setLimit(250);
        

    }

    public void limpar() {
        jTxtid.setText(null);
        jTxtid.setEnabled(true);
        jTxtNome.setText(null);
        jTxtFone.setText(null);
        jTxtLogin.setText(null);
        jTxtSenha.setText(null);
        jTxtSenha.setEnabled(true);
       // jCbxPerfil.setSelectedItem(" ");
        jBtnPesquisar.setEnabled(true);
        jBtnCreate.setEnabled(false);
        jBtnUpadte.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jChxSenha.setSelected(false);
        jChxSenha.setVisible(false);
        jChxSenha.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jChxSenha = new javax.swing.JCheckBox();
        jTxtid = new javax.swing.JTextField();
        jTxtNome = new javax.swing.JTextField();
        jTxtFone = new javax.swing.JTextField();
        jTxtLogin = new javax.swing.JTextField();
        jCbxPerfil = new javax.swing.JComboBox<>();
        jBtnCreate = new javax.swing.JButton();
        jBtnUpadte = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
        jTxtSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Usuários");
        setEnabled(false);
        setPreferredSize(new java.awt.Dimension(640, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("* Id");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("* Campos Obrigatórios");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("* Nome");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Fone");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("* Senha");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("* Perfil");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("* Login");

        jChxSenha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jChxSenha.setText("Alterar a Senha");
        jChxSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChxSenhaActionPerformed(evt);
            }
        });

        jCbxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário", "Administrador" }));

        jBtnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create.png"))); // NOI18N
        jBtnCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCreateActionPerformed(evt);
            }
        });

        jBtnUpadte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        jBtnUpadte.setEnabled(false);
        jBtnUpadte.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnUpadte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpadteActionPerformed(evt);
            }
        });

        jBtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jBtnDelete.setEnabled(false);
        jBtnDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jBtnPesquisar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });
        jBtnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBtnPesquisarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtFone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTxtSenha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addGap(14, 14, 14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jChxSenha))
                                        .addGap(47, 47, 47)
                                        .addComponent(jBtnUpadte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCbxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jBtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtNome))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtid, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnPesquisar)
                        .addGap(171, 171, 171)
                        .addComponent(jLabel2)))
                .addGap(58, 58, 58))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBtnCreate, jBtnDelete, jBtnUpadte});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jTxtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnPesquisar))
                        .addGap(55, 55, 55))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jTxtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jCbxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jChxSenha)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jBtnUpadte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        if (jTxtid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o Id do usuário");
            jTxtid.requestFocus();
        } else {

            Usuario obj = new Usuario();
            UsuarioDAO dao = new UsuarioDAO();

            obj = dao.pesquisarUsuario(Integer.parseInt(jTxtid.getText()));

            try {
              //  if(Objects.nonNull(obj.getId())){
                if (obj.getId() > 0) {
                    jTxtNome.setText(obj.getNome());
                    jTxtFone.setText(obj.getFone());
                    jTxtLogin.setText(obj.getLogin());
                    jTxtSenha.setText(obj.getSenha());
                    jCbxPerfil.setSelectedItem(obj.getPerfil());
                    jTxtid.setEnabled(false);
                    jBtnPesquisar.setEnabled(false);
                    jBtnUpadte.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jTxtSenha.setEnabled(false);
                    jChxSenha.setVisible(true);
                    jBtnCreate.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                    jTxtid.setEnabled(false);
                    jBtnPesquisar.setEnabled(false);
                    jBtnCreate.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
        }

    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jBtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCreateActionPerformed
        try {
            if ((jTxtid.getText().isEmpty()) || (jTxtNome.getText().isEmpty()) || (jTxtLogin.getText().isEmpty()) || (jTxtSenha.getPassword().length == 0) || (jCbxPerfil.getSelectedItem().equals(" "))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatórios (*)!");
            } else {
                Usuario obj = new Usuario();

                obj.setId(Integer.parseInt(jTxtid.getText()));
                obj.setNome(jTxtNome.getText());
                obj.setFone(jTxtFone.getText());
                String captura = new String(jTxtSenha.getPassword());
                obj.setSenha(captura);
                obj.setLogin(jTxtLogin.getText());
                obj.setPerfil(jCbxPerfil.getSelectedItem().toString());

                UsuarioDAO dao = new UsuarioDAO();
                dao.cadastrarUsuario(obj);
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jBtnCreateActionPerformed

    private void jBtnUpadteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpadteActionPerformed
        try {
            if ((jTxtid.getText().isEmpty()) || (jTxtNome.getText().isEmpty()) || (jTxtLogin.getText().isEmpty()) || (jTxtSenha.getPassword().length == 0) || (jCbxPerfil.getSelectedItem().equals(" "))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos Obrigatórios (*)!");
            } else {
                Usuario obj = new Usuario();

                obj.setId(Integer.parseInt(jTxtid.getText()));
                obj.setNome(jTxtNome.getText());
                obj.setFone(jTxtFone.getText());
                String captura = new String(jTxtSenha.getPassword());
                obj.setSenha(captura);
                obj.setLogin(jTxtLogin.getText());
                obj.setPerfil(jCbxPerfil.getSelectedItem().toString());

                UsuarioDAO dao = new UsuarioDAO();
                if (jChxSenha.isSelected()) {
                    dao.alterarSenha(obj);
                    limpar();
                } else {
                    dao.alterarUsuario(obj);
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jBtnUpadteActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        jChxSenha.setSelected(false);
        jChxSenha.setVisible(false);
    }//GEN-LAST:event_formInternalFrameOpened

    private void jChxSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChxSenhaActionPerformed
        jTxtSenha.setText(null);
        jTxtSenha.requestFocus();
        jTxtSenha.setEnabled(true);
        jChxSenha.setEnabled(false);

    }//GEN-LAST:event_jChxSenhaActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        Usuario obj = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                obj.setId(Integer.parseInt(jTxtid.getText()));
                dao.deletarUsuario(obj);
                limpar();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jBtnPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBtnPesquisarKeyPressed
        if (jTxtid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o Id do usuário");
            jTxtid.requestFocus();
        } else {

            Usuario obj = new Usuario();
            UsuarioDAO dao = new UsuarioDAO();

            obj = dao.pesquisarUsuario(Integer.parseInt(jTxtid.getText()));

            try {
                if (obj.getId() > 0) {
                    jTxtNome.setText(obj.getNome());
                    jTxtFone.setText(obj.getFone());
                    jTxtLogin.setText(obj.getLogin());
                    jTxtSenha.setText(obj.getSenha());
                    jTxtSenha.setText(obj.getSenha());
                    jCbxPerfil.setSelectedItem(obj.getPerfil());
                    jTxtid.setEnabled(false);
                    jBtnPesquisar.setEnabled(false);
                    jBtnUpadte.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jTxtSenha.setEnabled(false);
                    jChxSenha.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                    jTxtid.setEnabled(false);
                    jBtnPesquisar.setEnabled(false);
                    jBtnCreate.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e);
            }
        }

    }//GEN-LAST:event_jBtnPesquisarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCreate;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JButton jBtnUpadte;
    private javax.swing.JComboBox<String> jCbxPerfil;
    private javax.swing.JCheckBox jChxSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTxtFone;
    private javax.swing.JTextField jTxtLogin;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JPasswordField jTxtSenha;
    private javax.swing.JTextField jTxtid;
    // End of variables declaration//GEN-END:variables
}
