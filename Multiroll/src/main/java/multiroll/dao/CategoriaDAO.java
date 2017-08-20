package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Categoria;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class CategoriaDAO {

    public void incluir(Categoria categoria) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Categorias (CAT_NOME) VALUES (?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, categoria.getNome());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Categoria categoria) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Categorias SET CAT_NOME = ? WHERE CAT_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, categoria.getNome());
        ps.setLong(2, categoria.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Categoria categoria) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Categorias SET CAT_EXCLUIDO = now() WHERE CAT_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, categoria.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Categoria> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT c.CAT_ID AS id, c.CAT_NOME AS nome FROM Categorias c WHERE c.CAT_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Categoria> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("id"));
            categoria.setNome(rs.getString("nome").trim());
            listaRetorno.add(categoria);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Categoria pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT CAT_ID, CAT_NOME FROM Categorias WHERE CAT_ID = ? AND CAT_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Categoria retorno = new Categoria();
        if (rs.next()) {
            retorno.setId(rs.getLong("CAT_ID"));
            retorno.setNome(rs.getString("CAT_NOME").trim());
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }

}
