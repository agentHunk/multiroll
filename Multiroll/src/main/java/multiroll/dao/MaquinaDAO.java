package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Maquina;

/**
 * 16/10/2017
 *
 * @author leleti
 */
public class MaquinaDAO {

    public void incluir(Maquina maquina) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Maquinas (MAQ_MARCA, MAQ_MODELO) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maquina.getMarca());
        ps.setString(2, maquina.getModelo());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(Maquina maquina) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Maquinas SET MAQ_MARCA = ?, MAQ_MODELO = ? WHERE MAQ_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maquina.getMarca());
        ps.setString(2, maquina.getModelo());
        ps.setLong(3, maquina.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Maquina maquina) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Maquinas SET MAQ_EXCLUIDO = now() WHERE MAQ_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, maquina.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Maquina> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT MAQ_ID, MAQ_MARCA, MAQ_MODELO FROM Maquinas";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Maquina> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Maquina maquina = new Maquina();
            maquina.setId(rs.getLong("MAQ_ID"));
            maquina.setMarca(rs.getString("MAQ_MARCA").trim());
            maquina.setModelo(rs.getString("MAQ_MODELO").trim());
            listaRetorno.add(maquina);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Maquina pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT MAQ_ID, EST_NOME, EST_UF  FROM Maquinas WHERE MAQ_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Maquina retorno = new Maquina();
        if (rs.next()) {
        	retorno.setId(rs.getLong("MAQ_ID"));
        	retorno.setMarca(rs.getString("MAQ_MARCA").trim());
        	retorno.setModelo(rs.getString("MAQ_MODELO").trim());
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }

}
