package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Categoria;
import multiroll.modelo.Servico;

/**
 ** 19/04/2017
 *
 * @author leleti
 */
public class ServicoDAO {

    public void incluir(Servico servico) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Servicos (SER_TIPO, SER_IDCAR) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, servico.getNome());
        ps.setLong(2, servico.getCategoria().getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Servico servico) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Servicos SET SER_TIPO = ?, SER_IDCAR = ? WHERE SER_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, servico.getNome());
        ps.setLong(2, servico.getCategoria().getId());
        ps.setLong(3, servico.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Servico servico) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Servicos SET SER_EXCLUIDO = now() WHERE SER_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, servico.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Servico> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT s.SER_ID AS sid, s.SER_TIPO AS stipo, c.CAT_ID AS cid, c.CAT_NOME AS cnome "
                + "FROM Servicos s "
                + "INNER JOIN Categorias c ON s.SER_IDCAR = c.CAT_ID "
                + "WHERE SER_EXCLUIDO IS NULL;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Servico> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Categoria cat = new Categoria();
            cat.setId(rs.getLong("cid"));
            cat.setNome(rs.getString("cnome").trim());
            
            Servico servico = new Servico();
            servico.setId(rs.getLong("sid"));
            servico.setNome(rs.getString("stipo").trim());
            servico.setCategoria(cat);
            listaRetorno.add(servico);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Servico pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT s.SER_ID AS sid, s.SER_TIPO AS stipo, c.CAT_ID AS cid, c.CAT_NOME AS cnome "
                + "FROM Servicos s "
                + "INNER JOIN Categorias c ON s.SER_IDCAR = c.CAT_ID "
                + "WHERE SER_EXCLUIDO IS NULL;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Servico retorno = new Servico();
        if (rs.next()) {
            Categoria cat = new Categoria();
            cat.setId(rs.getLong("cid"));
            cat.setNome(rs.getString("cnome").trim());
            
            retorno.setId(rs.getLong("sid"));
            retorno.setNome(rs.getString("stipo").trim());
            retorno.setCategoria(cat);
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
