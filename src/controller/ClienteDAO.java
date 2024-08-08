/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author clebe
 */
public class ClienteDAO {

    Connection conexao = null;
    PreparedStatement ps;
    ResultSet rs;

    String cadastrarCliente = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
    String consultarClientesNome = "select idcli as ID, nomecli as Nome, endcli as Endereço, fonecli as Fone, emailcli as email from tbclientes where nomecli like ?";
    String listarClientes = "SELECT * FROM tbclientes";
    String editarCliente = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
    String excluirCliente = "delete from tbclientes where idcli=?";

    /**
     * Método responsável por adicionar um novo cliente recebendo como parâmetro
     * um obj do tipo Cliente
     */
    public void cadastrarCliente(Cliente obj) {
        try {
            //String cadastrarCliente = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(cadastrarCliente);

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEndereco());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getEmail());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com Sucesso!");
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Email já existente.\nEscolha outro email.");

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
     * Método responsável pela pesquisa de clientes pelo nome com filtro
     *
     * @param nome
     * @return lista
     */
    public List<Cliente> consultarClientes(String nome) {
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(consultarClientesNome);
            ps.setString(1, nome);

            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString(2));
                obj.setEndereco(rs.getString(3));
                obj.setTelefone(rs.getString(4));
                obj.setEmail(rs.getString(5));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!!!");
            return null;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public List<Cliente> listarClientes() {
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(listarClientes);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("idcli"));
                obj.setNome(rs.getString("nomecli"));
                obj.setEndereco(rs.getString(3));
                obj.setTelefone(rs.getString(4));
                obj.setEmail(rs.getString(5));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!!!");
            return null;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public void editarCliente(Cliente obj) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(editarCliente);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEndereco());
            ps.setString(3, obj.getTelefone());
            if (obj.getEmail()== null) {
                ps.setString(4, null);
            } else {
                ps.setString(4, obj.getEmail());
            }
            ps.setInt(5, obj.getId());
            if ((obj.getNome().isEmpty()) || (obj.getTelefone().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = ps.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
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

    public void excluirCliente(Cliente obj) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(excluirCliente);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui OS pendente.");
        } catch (HeadlessException | SQLException e2) {
            JOptionPane.showMessageDialog(null, e2);

        }
    }
}
