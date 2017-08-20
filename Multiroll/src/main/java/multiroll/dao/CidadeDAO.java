package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Cidade;
import multiroll.modelo.Estado;

/**
 * 24/04/2017
 *
 * @author leleti
 */
public class CidadeDAO {

    public void incluir(Cidade cidade) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Cidades (CID_NOME, CID_IDEST) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cidade.getNome());
        ps.setLong(2, cidade.getEstado().getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Cidade cidade) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Cidades SET CID_NOME = ?, CID_IDEST = ? WHERE CID_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cidade.getNome());
        ps.setLong(2, cidade.getEstado().getId());
        ps.setLong(3, cidade.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Cidade cidade) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Cidades SET CID_EXCLUIDO = now() WHERE CID_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, cidade.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Cidade> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT c.CID_ID AS cid, c.CID_NOME AS cidade, "
                + "es.EST_ID AS esid, es.EST_NOME AS estado, es.EST_UF AS uf "
                + "FROM Cidades c "
                + "INNER JOIN Estados es ON es.EST_ID = c.CID_IDEST "
                + "WHERE CID_EXCLUIDO IS NULL;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Cidade> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            
            Estado est = new Estado();
            est.setId(rs.getLong("esid"));
            est.setNome(rs.getString("estado").trim());
            est.setUf(rs.getString("uf").trim());
            
            Cidade cidade = new Cidade();
            cidade.setId(rs.getLong("cid"));
            cidade.setNome(rs.getString("cidade").trim());
            cidade.setEstado(est);
            
            listaRetorno.add(cidade);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Cidade pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT c.CID_ID AS cid, c.CID_NOME AS cidade, "
                + "es.EST_ID AS esid, es.EST_NOME AS estado, es.EST_UF AS uf "
                + "FROM Cidades c "
                + "INNER JOIN Estados es ON es.EST_ID = c.CID_IDEST "
                + "WHERE CID_ID = ? AND c.CID_EXCLUIDO IS NULL;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Cidade retorno = new Cidade();
        if (rs.next()) {
             Estado est = new Estado();
            est.setId(rs.getLong("esid"));
            est.setNome(rs.getString("estado").trim());
            est.setUf(rs.getString("uf").trim());
            
            retorno.setId(rs.getLong("cid"));
            retorno.setNome(rs.getString("cidade").trim());
            retorno.setEstado(est);
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
