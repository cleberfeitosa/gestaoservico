/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author clebe
 */
public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement ps;
    ResultSet rs;

    String consultarUsuario = "select * from tbusuarios where iduser=?";
    String cadastrarUsuario = "insert into tbusuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,md5(?),?)";
    String altararSenha = "update tbusuarios set usuario=?,fone=?,login=?,senha=md5(?),perfil=? where iduser=?";
    String alterarUsuario = "update tbusuarios set usuario=?,fone=?,login=?,perfil=? where iduser=?";
    String deletarUsuario = "delete from tbusuarios where iduser=?";

    public Usuario pesquisarUsuario(int id) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareCall(consultarUsuario);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Usuario obj = new Usuario();
            if (rs.next()) {
                obj.setId(rs.getInt("iduser"));
                obj.setNome(rs.getString("usuario"));
                obj.setFone(rs.getString("fone"));
                obj.setLogin(rs.getString("login"));
                obj.setSenha(rs.getString("senha"));
                obj.setPerfil(rs.getString("perfil"));

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            return null;
        }

    }

    public void cadastrarUsuario(Usuario obj) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(cadastrarUsuario);
           // String cadastrarUsuario = "insert into tbusuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,md5(?),?)";
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getFone());
            ps.setString(4, obj.getLogin());
            ps.setString(5, obj.getSenha());
            ps.setString(6, obj.getPerfil());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public void alterarSenha(Usuario obj) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(altararSenha);

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getFone());
            ps.setString(3, obj.getLogin());
            ps.setString(4, obj.getSenha());
            ps.setString(5, obj.getPerfil());
            ps.setInt(6, obj.getId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário alterado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public void alterarUsuario(Usuario obj) {
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(alterarUsuario);

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getFone());
            ps.setString(3, obj.getLogin());
            ps.setString(4, obj.getPerfil());
            ps.setInt(5, obj.getId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário alterado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    public void deletarUsuario (Usuario obj){
        try {
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(deletarUsuario);
            
            ps.setInt(1, obj.getId());
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Usuário Excluído com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    }   

}
