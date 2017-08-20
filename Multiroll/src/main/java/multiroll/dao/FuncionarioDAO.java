package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Funcionario;

/**
 * 19/04/2017
 *
 * @author leleti
 */
public class FuncionarioDAO {

    public void incluir(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Funcionarios (FUN_NOME, FUN_CARGO) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, funcionario.getNome());
        ps.setString(2, funcionario.getCargo());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Funcionarios SET FUN_NOME = ?, FUN_CARGO = ? WHERE FUN_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, funcionario.getNome());
        ps.setString(2, funcionario.getCargo());
        ps.setLong(3, funcionario.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Funcionarios SET FUN_EXCLUIDO = now() WHERE FUN_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, funcionario.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Funcionario> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT FUN_ID, FUN_NOME, FUN_CARGO FROM Funcionarios WHERE FUN_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Funcionario> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(rs.getLong("FUN_ID"));
            funcionario.setNome(rs.getString("FUN_NOME").trim());
            funcionario.setCargo(rs.getString("FUN_CARGO").trim());
            listaRetorno.add(funcionario);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Funcionario pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT FUN_ID, FUN_NOME, FUN_CARGO FROM Funcionarios WHERE FUN_ID = ? AND FUN_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Funcionario retorno = new Funcionario();
        if (rs.next()) {
            retorno.setId(rs.getLong("FUN_ID"));
            retorno.setNome(rs.getString("FUN_NOME").trim());
            retorno.setCargo(rs.getString("FUN_CARGO").trim());
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
