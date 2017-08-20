package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.FormaPgto;

/**
 * 19/04/2017
 *
 * @author leleti
 */
public class FormaPgtoDAO {

    public void incluir(FormaPgto forma) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Forma_pgtos (PGTO_NOME, PGTO_SIGLA) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, forma.getNome());
        ps.setString(2, forma.getSigla());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(FormaPgto forma) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Forma_pgtos SET PGTO_NOME = ?, PGTO_SIGLA = ? WHERE PGTO_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, forma.getNome());
        ps.setString(2, forma.getSigla());
        ps.setLong(3, forma.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(FormaPgto forma) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Forma_pgtos SET PGTO_EXCLUIDO = now() WHERE PGTO_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, forma.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<FormaPgto> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT PGTO_ID, PGTO_NOME, PGTO_SIGLA FROM Forma_pgtos WHERE PGTO_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<FormaPgto> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            FormaPgto forma = new FormaPgto();
            forma.setId(rs.getLong("PGTO_ID"));
            forma.setNome(rs.getString("PGTO_NOME").trim());
            forma.setSigla(rs.getString("PGTO_SIGLA").trim());
            listaRetorno.add(forma);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public FormaPgto pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT PGTO_ID, PGTO_NOME FROM Forma_pgtos WHERE PGTO_ID = ? AND PGTO_EXCLUIDO IS NULL";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        FormaPgto retorno = new FormaPgto();
        if (rs.next()) {
            retorno.setId(rs.getLong("PGTO_ID"));
            retorno.setNome(rs.getString("PGTO_NOME").trim());
            retorno.setSigla(rs.getString("PGTO_SIGLA").trim());
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
