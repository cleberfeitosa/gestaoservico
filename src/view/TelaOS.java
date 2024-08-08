package view;

import controller.ClienteDAO;
import controller.ModuloConexao;
import controller.OsDAO;
import java.sql.*;

import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Os;

/**
 * Tela de gestão do sistema
 *
 * @author Professor Cleber Feitosa
 */
public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;

    private String tipo;

    /**
     * Creates new form TelaOS
     */
    public TelaOS() {
        initComponents();
    }
    
    /**
     * Método usado para recuperar o número da OS
     */
    private void recuperarOs() {
        String sql = "select max(os) from tbos";
        try {
            conexao = ModuloConexao.conectar();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                jTxtOs.setText(rs.getString(1));
            }
        } catch (HeadlessException | SQLException e) {
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
     * método para carregar/atualizar os dados na tabela quando abrir o
     * formulário
     */
    public void listarClientes() {
        ClienteDAO dao = new ClienteDAO();

        List<Cliente> lista = dao.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) jTblClientes.getModel();
        dados.setNumRows(0);

        for (Cliente c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getTelefone()
            });

        }

    }
    
    
    
    

    /**
     * Método responsável pela edição de uma Ordem de Seviço
     */
    private void editarOs() {
        String sql = "update tbos set tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";
        try {
            conexao = ModuloConexao.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, jCBXOsSit.getSelectedItem().toString());
            pst.setString(3, jTxtOsEquip.getText());
            pst.setString(4, jTxtOsDef.getText());
            pst.setString(5, jTxtOsServ.getText());
            pst.setString(6, jTxtOsTec.getText());
            pst.setString(7, jTxtOsValor.getText().replace(",", "."));
            pst.setString(8, jTxtOs.getText());
            if ((jTxtIdCliente.getText().isEmpty()) || (jTxtOsEquip.getText().isEmpty()) || (jTxtOsDef.getText().isEmpty()) || jCBXOsSit.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
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
     * Método responsável pela exclusão de uma Ordem de Serviço
     */
    private void excluirOs() {
        conexao = ModuloConexao.conectar();
        String verifica = jCBXOsSit.getSelectedItem().toString();
        if (verifica.equals("Retirado")) {
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String sql = "delete from tbos where os=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, jTxtOs.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        limpar();
                        JOptionPane.showMessageDialog(null, "OS excluída com sucesso");
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    try {
                        conexao.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Para excluir esta OS é necessário modificar\no status para \"Retirado\"");
        }
    }

    /**
     * Método responsável pela impressão da Ordem de Serviço com JasperReports
     */
//    private void imprimirOs() {
//        conexao = ModuloConexao.conectar();
//        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
//        if (confirma == JOptionPane.YES_OPTION) {
//            try {
//                HashMap filtro = new HashMap();
//                filtro.put("os", Integer.parseInt(txtOs.getText()));
//                JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/os.jasper"), filtro, conexao);
//                JasperViewer.viewReport(print, false);
//                conexao.close();
//            } catch (NumberFormatException | SQLException | JRException e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//    }
    

    /**
     * Método responsável por limpar os campos e gerenciar os componentes
     */
    private void limpar() {
        jTxtOs.setText(null);
        txtData.setText(null);
        jTxtPesquisar.setText(null);
        ((DefaultTableModel) jTblClientes.getModel()).setRowCount(0);
        jCBXOsSit.setSelectedItem(" ");
        jTxtIdCliente.setText(null);
        jTxtOsEquip.setText(null);
        jTxtOsDef.setText(null);
        jTxtOsServ.setText(null);
        jTxtOsTec.setText(null);
        jTxtOsValor.setText("0");
        jBtnOsAdicionar.setEnabled(true);
        jBtnOsPesquisar.setEnabled(true);
        jTxtPesquisar.setEnabled(true);
        jTblClientes.setVisible(true);
        jBtnOsAlterar.setEnabled(false);
        jBtnOsExcluir.setEnabled(false);
        jBtnOsImprimir.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtOs = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOrc = new javax.swing.JRadioButton();
        rbtOs = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jCBXOsSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jTxtPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtIdCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTxtOsEquip = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jBtnOsAdicionar = new javax.swing.JButton();
        jTxtOsTec = new javax.swing.JTextField();
        jTxtOsDef = new javax.swing.JTextField();
        jTxtOsServ = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTxtOsValor = new javax.swing.JTextField();
        jBtnOsPesquisar = new javax.swing.JButton();
        jBtnOsAlterar = new javax.swing.JButton();
        jBtnOsExcluir = new javax.swing.JButton();
        jBtnOsImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("OS");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data");

        jTxtOs.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOs);
        rbtOs.setText("Ordem de serviço");
        rbtOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTxtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtOrc)
                        .addGap(18, 18, 18)
                        .addComponent(rbtOs)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrc)
                    .addComponent(rbtOs))
                .addGap(22, 22, 22))
        );

        jLabel3.setText("* Status");

        jCBXOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Aguardando aprovação", "Aguardando peças", "Aguardando retirada", "Na bancada", "Retirado", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jTxtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPesquisarActionPerformed(evt);
            }
        });
        jTxtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pesquisar.png"))); // NOI18N

        jLabel5.setText("* Id");

        jTxtIdCliente.setEditable(false);

        jTblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblClientes.setGridColor(new java.awt.Color(51, 51, 51));
        jTblClientes.getTableHeader().setReorderingAllowed(false);
        jTblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblClientes);
        if (jTblClientes.getColumnModel().getColumnCount() > 0) {
            jTblClientes.getColumnModel().getColumn(0).setMinWidth(25);
            jTblClientes.getColumnModel().getColumn(0).setMaxWidth(35);
            jTblClientes.getColumnModel().getColumn(1).setResizable(false);
            jTblClientes.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jTxtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTxtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("* Equipamento");

        jLabel7.setText("* Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Técnico");

        jBtnOsAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/create.png"))); // NOI18N
        jBtnOsAdicionar.setToolTipText("Emitir OS");
        jBtnOsAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnOsAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnOsAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOsAdicionarActionPerformed(evt);
            }
        });

        jLabel10.setText("Valor Total");

        jTxtOsValor.setText("0,00");

        jBtnOsPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/read.png"))); // NOI18N
        jBtnOsPesquisar.setToolTipText("Pesquisar OS");
        jBtnOsPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnOsPesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnOsPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOsPesquisarActionPerformed(evt);
            }
        });

        jBtnOsAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        jBtnOsAlterar.setToolTipText("Editar OS");
        jBtnOsAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnOsAlterar.setEnabled(false);
        jBtnOsAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnOsAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOsAlterarActionPerformed(evt);
            }
        });

        jBtnOsExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jBtnOsExcluir.setToolTipText("Excluir OS");
        jBtnOsExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnOsExcluir.setEnabled(false);
        jBtnOsExcluir.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnOsExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOsExcluirActionPerformed(evt);
            }
        });

        jBtnOsImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.png"))); // NOI18N
        jBtnOsImprimir.setToolTipText("Imprimir OS");
        jBtnOsImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnOsImprimir.setEnabled(false);
        jBtnOsImprimir.setPreferredSize(new java.awt.Dimension(80, 80));
        jBtnOsImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOsImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jBtnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOsExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(jCBXOsSit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel7)))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTxtOsDef, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                    .addComponent(jTxtOsEquip)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTxtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTxtOsServ))))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jCBXOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTxtOsEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtOsDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtOsServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtOsTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTxtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnOsAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOsAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOsExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOsImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(484, 484, 484))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtPesquisarKeyReleased
        String nome = jTxtPesquisar.getText() + "%";
        ClienteDAO dao = new ClienteDAO();

        List<Cliente> lista = dao.consultarClientes(nome);
        DefaultTableModel dados = (DefaultTableModel) jTblClientes.getModel();
        dados.setNumRows(0);

        for (Cliente c : lista) {
            dados.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getTelefone(),});

        }
    }//GEN-LAST:event_jTxtPesquisarKeyReleased

    private void jTblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblClientesMouseClicked
//        int setar = jTblClientes.getSelectedRow();
//        jTxtIdCliente.setText(jTblClientes.getModel().getValueAt(setar, 0).toString());

        jTxtIdCliente.setText(jTblClientes.getValueAt(jTblClientes.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jTblClientesMouseClicked

    private void jBtnOsAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOsAdicionarActionPerformed

        if ((jTxtIdCliente.getText().isEmpty()) || (jTxtOsEquip.getText().isEmpty()) || (jTxtOsDef.getText().isEmpty()) || jCBXOsSit.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
        } else {
            Os obj = new Os();
            Cliente cliente = new Cliente();

            if (rbtOrc.isSelected()) {
                obj.setTipo(rbtOrc.getText());
            } else if (rbtOs.isSelected()) {
                obj.setTipo(rbtOs.getText());
            }
            obj.setSituacao((String) jCBXOsSit.getSelectedItem());
            obj.setEquipamento(jTxtOsEquip.getText());
            obj.setDefeito(jTxtOsDef.getText());
            obj.setServico(jTxtOsServ.getText());
            obj.setTecnico(jTxtOsTec.getText());
            obj.setValor(Double.valueOf(jTxtOsValor.getText().replace(",", ".")));
            //  obj.setValor(Double.parseDouble(jTxtOsValor.getText()));

            cliente.setId(Integer.valueOf(jTxtIdCliente.getText()));
            obj.setCliente(cliente);

            OsDAO dao = new OsDAO();
            dao.emitirOS(obj);
            jTxtOs.setText(String.valueOf(dao.recuperarOs().getOs()));
            //JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
            jBtnOsAdicionar.setEnabled(false);
            jBtnOsPesquisar.setEnabled(false);
            jBtnOsImprimir.setEnabled(true);

        }


    }//GEN-LAST:event_jBtnOsAdicionarActionPerformed

    private void jBtnOsPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOsPesquisarActionPerformed
        int num_os = Integer.parseInt(JOptionPane.showInputDialog("Número da OS"));
        OsDAO dao = new OsDAO();
        Os obj = new Os();
        obj = dao.consultarOS(num_os);
        
        if(obj.getTipo().equalsIgnoreCase("Orçamento")){
            rbtOrc.setSelected(true);
        }else{
            rbtOs.setSelected(true);
        }
        jTxtOs.setText(String.valueOf(obj.getOs()));
        txtData.setText(String.valueOf(obj.getDataOs()));
        jTxtOsEquip.setText(obj.getEquipamento());
        jCBXOsSit.setSelectedItem(obj.getSituacao());
        jTxtOsDef.setText(obj.getDefeito());
        jTxtOsServ.setText(obj.getServico());
        jTxtOsTec.setText(obj.getTecnico());
        jTxtOsValor.setText(String.valueOf(obj.getValor()));
        jTxtIdCliente.setText(String.valueOf(obj.getCliente().getId()));
        jBtnOsAdicionar.setEnabled(false);
        jBtnOsPesquisar.setEnabled(false);
        jTxtPesquisar.setEnabled(false);
        jTblClientes.setVisible(false);
        jBtnOsAlterar.setEnabled(true);
        jBtnOsExcluir.setEnabled(true);
        jBtnOsImprimir.setEnabled(true);
        
        

    }//GEN-LAST:event_jBtnOsPesquisarActionPerformed

    private void jBtnOsAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOsAlterarActionPerformed
        editarOs();
    }//GEN-LAST:event_jBtnOsAlterarActionPerformed

    private void jBtnOsExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOsExcluirActionPerformed
        excluirOs();
    }//GEN-LAST:event_jBtnOsExcluirActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
        listarClientes();
    }//GEN-LAST:event_formInternalFrameOpened

    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void rbtOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOsActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_rbtOsActionPerformed

    private void jTxtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPesquisarActionPerformed

    }//GEN-LAST:event_jTxtPesquisarActionPerformed

    private void jBtnOsImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOsImprimirActionPerformed
//        imprimirOs();
    }//GEN-LAST:event_jBtnOsImprimirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnOsAdicionar;
    private javax.swing.JButton jBtnOsAlterar;
    private javax.swing.JButton jBtnOsExcluir;
    private javax.swing.JButton jBtnOsImprimir;
    private javax.swing.JButton jBtnOsPesquisar;
    private javax.swing.JComboBox<String> jCBXOsSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblClientes;
    private javax.swing.JTextField jTxtIdCliente;
    private javax.swing.JTextField jTxtOs;
    private javax.swing.JTextField jTxtOsDef;
    private javax.swing.JTextField jTxtOsEquip;
    private javax.swing.JTextField jTxtOsServ;
    private javax.swing.JTextField jTxtOsTec;
    private javax.swing.JTextField jTxtOsValor;
    private javax.swing.JTextField jTxtPesquisar;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JRadioButton rbtOs;
    private javax.swing.JTextField txtData;
    // End of variables declaration//GEN-END:variables
}