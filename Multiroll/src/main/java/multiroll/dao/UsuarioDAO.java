package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Funcionario;
import multiroll.modelo.Usuario;

/**
 * 19/04/2017
 *
 * @author leleti
 */
public class UsuarioDAO {

    public void incluir(Usuario usuario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Usuarios (USU_LOGIN, USU_SENHA, USU_IDFUN) VALUES (?, md5(?), ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario.getLogin());
        ps.setString(2, usuario.getSenha());
        ps.setLong(3, usuario.getFuncionario().getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Usuario usuario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Usuarios SET USU_LOGIN = ?, USU_SENHA = md5(?), USU_IDFUN = ? WHERE USU_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario.getLogin());
        ps.setString(2, usuario.getSenha());
        ps.setLong(3, usuario.getFuncionario().getId());
        ps.setLong(4, usuario.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Usuario usuario) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "DELETE FROM Usuarios WHERE USU_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, usuario.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Usuario> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT u.USU_ID AS uid, u.USU_LOGIN AS ulogin, f.FUN_ID AS fid, f.FUN_NOME AS fnome, f.FUN_CARGO AS fcargo "
                + "FROM Usuarios u "
                + "INNER JOIN Funcionarios f ON u.USU_IDFUN = f.FUN_ID";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Usuario> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Funcionario func = new Funcionario();
            func.setId(rs.getLong("fid"));
            func.setNome(rs.getString("fnome").trim());
            func.setCargo(rs.getString("fcargo").trim());
            
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("uid"));
            usuario.setLogin(rs.getString("ulogin").trim());
            usuario.setFuncionario(func);
            listaRetorno.add(usuario);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Usuario pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT u.USU_ID AS uid, u.USU_LOGIN AS ulogin, u.USU_SENHA AS usenha, f.FUN_ID AS fid, f.FUN_NOME AS fnome, f.FUN_CARGO AS fcargo "
                + "FROM Usuarios u "
                + "INNER JOIN Funcionarios f ON u.USU_IDFUN = f.FUN_ID";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Usuario retorno = new Usuario();
        if (rs.next()) {
            Funcionario func = new Funcionario();
            func.setId(rs.getLong("fid"));
            func.setNome(rs.getString("fnome").trim());
            func.setCargo(rs.getString("fcargo").trim());
            
            retorno.setId(rs.getLong("uid"));
            retorno.setLogin(rs.getString("ulogin").trim());
            retorno.setSenha(rs.getString("usenha").trim());
            retorno.setFuncionario(func);
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
