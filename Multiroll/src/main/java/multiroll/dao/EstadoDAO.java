package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Estado;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class EstadoDAO {

    public void incluir(Estado estado) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Estados (EST_NOME, EST_UF) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, estado.getNome());
        ps.setString(2, estado.getUf());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Estado estado) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Estados SET EST_NOME = ?, EST_UF = ? WHERE EST_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, estado.getNome());
        ps.setString(2, estado.getUf());
        ps.setLong(3, estado.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Estado estado) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "DELETE FROM Estados WHERE EST_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, estado.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Estado> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT EST_ID, EST_NOME, EST_UF FROM Estados";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Estado> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Estado estado = new Estado();
            estado.setId(rs.getLong("EST_ID"));
            estado.setNome(rs.getString("EST_NOME").trim());
            estado.setUf(rs.getString("EST_UF").trim());
            listaRetorno.add(estado);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Estado pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT EST_ID, EST_NOME, EST_UF  FROM Estados WHERE EST_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Estado retorno = new Estado();
        if (rs.next()) {
            retorno.setId(rs.getLong("EST_ID"));
            retorno.setNome(rs.getString("EST_NOME").trim());
            retorno.setUf(rs.getString("EST_UF").trim());
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }

}
