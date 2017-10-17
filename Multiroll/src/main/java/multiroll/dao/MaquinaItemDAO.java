package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.MaquinaItem;
import multiroll.modelo.Maquina;

/**
 * 24/04/2017
 *
 * @author leleti
 */
public class MaquinaItemDAO {

	public void incluir(MaquinaItem maquinaItem) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "INSERT INTO MaquinaItens (MIT_QUANTIDADE, MIT_DESCRICAO, MIT_DIAMETRO, MIT_COMPRIMENTO, MIT_REVESTIMENTO, MIT_EIXO, MIT_IDMAQ) VALUES (?, ?, ?, ?, ?, ?, ?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, maquinaItem.getQuantidade());
		ps.setString(2, maquinaItem.getDescricao());
		ps.setString(3, maquinaItem.getDiametro());
		ps.setString(4, maquinaItem.getComprimento());
		ps.setDouble(5, maquinaItem.getRevestimento());
		ps.setDouble(6, maquinaItem.getEixo());
		ps.setLong(7, maquinaItem.getMaquina().getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void alterar(MaquinaItem maquinaItem) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "UPDATE MaquinaItens SET MIT_QUANTIDADE = ?, MIT_DESCRICAO = ?, MIT_DIAMETRO = ?, MIT_COMPRIMENTO = ?, MIT_REVESTIMENTO = ?, MIT_EIXO = ?, MIT_IDMAQ = ? WHERE MIT_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, maquinaItem.getQuantidade());
		ps.setString(2, maquinaItem.getDescricao());
		ps.setString(3, maquinaItem.getDiametro());
		ps.setString(4, maquinaItem.getComprimento());
		ps.setDouble(5, maquinaItem.getRevestimento());
		ps.setDouble(6, maquinaItem.getEixo());
		ps.setLong(7, maquinaItem.getMaquina().getId());
		ps.setLong(8, maquinaItem.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void excluir(MaquinaItem maquinaItem) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "UPDATE MaquinaItens SET MIT_EXCLUIDO = now() WHERE MIT_IDMAQ = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, maquinaItem.getMaquina().getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<MaquinaItem> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "SELECT ma.MAQ_ID AS idMaq, ma.MAQ_MODELO AS modelo, MAQ_MARCA AS marca, \n"
				+ "mi.MIT_ID AS idMit, mi.MIT_QUANTIDADE AS quantidade, \n"
				+ "mi.MIT_DESCRICAO AS descricao, mi.MIT_DIAMETRO AS diametro, \n"
				+ "mi.MIT_COMPRIMENTO AS comprimento, mi.MIT_REVESTIMENTO AS revestimento, mi.MIT_EIXO AS eixo \n"
				+ "FROM MaquinaItens mi \n" + "INNER JOIN Maquinas ma ON ma.MAQ_ID = mi.MIT_IDMAQ \n"
				+ "WHERE mi.MIT_EXCLUIDO IS NULL ORDER BY ma.MAQ_MODELO;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<MaquinaItem> listaRetorno = new ArrayList<>();
		while (rs.next()) {

			Maquina maq = new Maquina();
			maq.setId(rs.getLong("idMaq"));
			maq.setModelo(rs.getString("modelo").trim());
			maq.setMarca(rs.getString("marca").trim());

			MaquinaItem maquinaItem = new MaquinaItem();
			maquinaItem.setId(rs.getLong("idMit"));
			maquinaItem.setQuantidade(rs.getInt("quantidade"));
			maquinaItem.setDescricao(rs.getString("descricao").trim());
			maquinaItem.setDiametro(rs.getString("diametro").trim());
			maquinaItem.setComprimento(rs.getString("comprimento").trim());
			maquinaItem.setRevestimento(rs.getDouble("revestimento"));
			maquinaItem.setEixo(rs.getDouble("eixo"));
			maquinaItem.setMaquina(maq);

			listaRetorno.add(maquinaItem);
		}
		rs.close();
		ps.close();
		conn.close();
		return listaRetorno;
	}

	public MaquinaItem pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "SELECT ma.MAQ_ID AS idMaq, ma.MAQ_MODELO AS modelo, MAQ_MARCA AS marca, \n"
				+ "mi.MIT_ID AS idMit, mi.MIT_QUANTIDADE AS quantidade, \n"
				+ "mi.MIT_DESCRICAO AS descricao, mi.MIT_DIAMETRO AS diametro, \n"
				+ "mi.MIT_COMPRIMENTO AS comprimento, mi.MIT_REVESTIMENTO AS revestimento, mi.MIT_EIXO AS eixo \n"
				+ "FROM MaquinaItens mi \n" + "INNER JOIN Maquinas ma ON ma.MAQ_ID = mi.MIT_IDMAQ \n"
				+ "WHERE mi.MIT_EXCLUIDO IS NULL ORDER BY ma.MAQ_MODELO;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		MaquinaItem retorno = new MaquinaItem();
		if (rs.next()) {
			Maquina maq = new Maquina();
			maq.setId(rs.getLong("idMaq"));
			maq.setModelo(rs.getString("modelo").trim());
			maq.setMarca(rs.getString("marca").trim());

			retorno.setId(rs.getLong("idMit"));
			retorno.setQuantidade(rs.getInt("quantidade"));
			retorno.setDescricao(rs.getString("descricao").trim());
			retorno.setDiametro(rs.getString("diametro").trim());
			retorno.setComprimento(rs.getString("comprimento").trim());
			retorno.setRevestimento(rs.getDouble("revestimento"));
			retorno.setEixo(rs.getDouble("eixo"));
			retorno.setMaquina(maq);
		}
		rs.close();
		ps.close();
		conn.close();
		return retorno;
	}
}
