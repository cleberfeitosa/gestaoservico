/*
 * The MIT License
 *
 * Copyright 2021 Professor Cleber Feitosa.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package view;

import Atxy2k.CustomTextField.RestrictedTextField;
import controller.ClienteDAO;
import java.sql.*;

import controller.ModuloConexao;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import model.Cliente;


/**
 * Tela de gestão de clientes
 *
 * @author Professor Cleber Feitosa
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Creates new form TelaCliente
     */
    
    public TelaCliente() {
        initComponents();
        RestrictedTextField validarCliente;
        validarCliente = new RestrictedTextField(jTxtNome);
        validarCliente.setLimit(50);
        RestrictedTextField validarEndereco;
        validarEndereco = new RestrictedTextField(jTxtEndereco);
        validarEndereco.setLimit(100);
        RestrictedTextField validarFone;
        validarFone = new RestrictedTextField(jTxtTelefone);
        validarFone.setLimit(15);
        RestrictedTextField validarEmail;
        validarEmail = new RestrictedTextField(jTxtEmail);
        validarEmail.setLimit(50);
    }

    
    public void listarClientes(){
        ClienteDAO dao = new ClienteDAO();
        
        List<Cliente> lista = dao.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) jTblClientes.getModel();
        dados.setNumRows(0);
        
        for(Cliente c: lista){
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getEndereco(),
                c.getTelefone(),
                c.getEmail()
        });
        
        }
        
    }
     /**
     * Método responsável pela pesquisa de clientes pelo nome com filtro
     */
    private void pesquisarCliente() {
        String sql = "select idcli as id, nomecli as nome, endcli as endereço, fonecli as fone, emailcli as email from tbclientes where nomecli like ?";
        try {
            conexao = ModuloConexao.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, jTxtPesquisar.getText() + "%");
            rs = pst.executeQuery();
       //     jTblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * método usado para setar os campos de texto com o conteúdo da tabela
     */
    private void setarCampos() {
        int setar = jTblClientes.getSelectedRow();
        jTxtId.setText(jTblClientes.getModel().getValueAt(setar, 0).toString());
        jTxtNome.setText(jTblClientes.getModel().getValueAt(setar, 1).toString());
        jTxtEndereco.setText(jTblClientes.getModel().getValueAt(setar, 2).toString());
        jTxtTelefone.setText(jTblClientes.getModel().getValueAt(setar, 3).toString());
        if (jTblClientes.getModel().getValueAt(setar, 4) == null){
            jTxtEmail.setText(null);
        } else {
            jTxtEmail.setText(jTblClientes.getModel().getValueAt(setar, 4).toString());
        }        
        jBtnAdicionar.setEnabled(false);
        jBtnAlterar.setEnabled(true);
        jBtnRemover.setEnabled(true);
    }

   
    /**
     * Método responsável por limpar os campos e gerenciar os componentes
     */
    private void limpar() {
        jTxtPesquisar.setText(null);
        jTxtId.setText(null);
        jTxtNome.setText(null);
        jTxtEndereco.setText(null);
        jTxtTelefone.setText(null);
        jTxtEmail.setText(null);
        ((DefaultTableModel) jTblClientes.getModel()).setRowCount(0);
        jBtnAdicionar.setEnabled(true);
        jBtnAlterar.setEnabled(false);
        jBtnRemover.setEnabled(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBtnRemover = new javax.swing.JButton();
        jTxtNome = new javax.swing.JTextField();
        jTxtTelefone = new javax.swing.JTextField();
        jTxtEndereco = new javax.swing.JTextField();
        jTxtEmail = new javax.swing.JTextField();
        jBtnAlterar = new javax.swing.JButton();
        jBtnAdicionar = new javax.swing.JButton();
        jTxtPesquisar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTxtId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");
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

        jLabel1.setText("* Campos obrigatórios");

        jLabel2.setText("* Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("* Telefone");

        jLabel5.setText("E-mail");

        jBtnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jBtnRemover.setToolTipText("Excluir cliente");
        jBtnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnRemover.setEnabled(false);
        jBtnRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRemoverActionPerformed(evt);
            }
        });

        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        jBtnAlterar.setToolTipText("Editar cliente");
        jBtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAlterar.setEnabled(false);
        jBtnAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });

        jBtnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create.png"))); // NOI18N
        jBtnAdicionar.setToolTipText("Adicionar cliente");
        jBtnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAdicionarActionPerformed(evt);
            }
        });

        jTxtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPesquisarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pesquisar.png"))); // NOI18N
        jLabel6.setToolTipText("");

        jTblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Endereço", "Fone", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblClientes.setFocusable(false);
        jTblClientes.setGridColor(new java.awt.Color(102, 102, 102));
        jTblClientes.getTableHeader().setReorderingAllowed(false);
        jTblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblClientes);
        if (jTblClientes.getColumnModel().getColumnCount() > 0) {
            jTblClientes.getColumnModel().getColumn(0).setMinWidth(20);
            jTblClientes.getColumnModel().getColumn(0).setMaxWidth(25);
            jTblClientes.getColumnModel().getColumn(1).setResizable(false);
            jTblClientes.getColumnModel().getColumn(2).setResizable(false);
            jTblClientes.getColumnModel().getColumn(3).setMinWidth(100);
            jTblClientes.getColumnModel().getColumn(3).setMaxWidth(100);
            jTblClientes.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel7.setText("Id Cliente");

        jTxtId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jTxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTxtNome, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTxtTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtEndereco, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jBtnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jLabel1.getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAdicionarActionPerformed
        try {
            if ((jTxtNome.getText().isEmpty()) || (jTxtTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
           Cliente obj = new Cliente();
           obj.setNome(jTxtNome.getText());
           obj.setEndereco(jTxtEndereco.getText());
           obj.setTelefone(jTxtTelefone.getText());
           obj.setEmail(jTxtEmail.getText());
           
           ClienteDAO dao = new ClienteDAO();
           dao.cadastrarCliente(obj);
           limpar();
           listarClientes();
           }
        }catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
    }//GEN-LAST:event_jBtnAdicionarActionPerformed

    private void jTblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblClientesMouseClicked
        setarCampos();
    }//GEN-LAST:event_jTblClientesMouseClicked

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confima as alterações nos dados deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            
            try {
                Cliente obj = new Cliente();
                obj.setNome(jTxtNome.getText());
                obj.setEndereco(jTxtEndereco.getText());
                obj.setTelefone(jTxtTelefone.getText());
                if (jTxtEmail.getText().equals("")) {
                obj.setEmail(null);
            } else {
                obj.setEmail(jTxtEmail.getText());
            }
                obj.setId(Integer.parseInt(jTxtId.getText()));
                if ((jTxtNome.getText().isEmpty()) || (jTxtTelefone.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                } else {
                    ClienteDAO dao = new ClienteDAO();
                    dao.editarCliente(obj);
                    limpar();
                    listarClientes();
                }
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            } 
        }
    
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRemoverActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confima a exclusão deste cliente?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            Cliente obj = new Cliente();
            obj.setId(Integer.parseInt(jTxtId.getText()));
            ClienteDAO dao = new ClienteDAO();
            dao.excluirCliente(obj);
            limpar();
            listarClientes();
        }
    }//GEN-LAST:event_jBtnRemoverActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
         listarClientes(); 
       
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTxtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPesquisarKeyReleased
        String nome = jTxtPesquisar.getText()+"%";
        ClienteDAO dao = new ClienteDAO();
        
        List<Cliente> lista = dao.consultarClientes(nome);
        DefaultTableModel dados = (DefaultTableModel) jTblClientes.getModel();
        dados.setNumRows(0);
        
        for(Cliente c: lista){
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getEndereco(),
                c.getTelefone(),
                c.getEmail()
        });
        
        }
    }//GEN-LAST:event_jTxtPesquisarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdicionar;
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblClientes;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEndereco;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtPesquisar;
    private javax.swing.JTextField jTxtTelefone;
    // End of variables declaration//GEN-END:variables
}
