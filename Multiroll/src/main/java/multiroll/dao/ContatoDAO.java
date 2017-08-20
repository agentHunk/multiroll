package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Contato;

/**
 * 05/08/2017
 *
 * @author leleti
 */
public class ContatoDAO {

	public Long incluir(Contato contato) throws ClassNotFoundException, SQLException {
		
		Long idContato = null;
		
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Contatos (CON_FIXO, CON_FIXO2, CON_CELULAR, CON_CELULAR2, CON_WHATSAPP, CON_EMAIL, CON_EMAIL2, CON_OBSERVACAO, CON_IDCLI) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, contato.getTelFixo());
        ps.setString(2, contato.getTelFixo2());
        ps.setString(3, contato.getTelCel());
        ps.setString(4, contato.getTelCel2());
        ps.setString(5, contato.getWhatsApp());
        ps.setString(6, contato.getEmail());
        ps.setString(7, contato.getEmail2());
        ps.setString(8, contato.getObservacao());
        ps.setLong(9, contato.getCliente().getId());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();

		while (rs.next()) {
			idContato = rs.getLong(1);
		}
		
        ps.close();
        conn.close();
        
        return idContato;
    }

    public void alterar(Contato contato) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Contatos SET CON_FIXO = ?, CON_FIXO2 = ?, CON_CELULAR = ?, CON_CELULAR2 = ?, CON_WHATSAPP = ?, CON_EMAIL = ?, CON_EMAIL2 = ?, CON_OBSERVACAO = ?, CON_IDCLI = ? WHERE CON_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, contato.getTelFixo());
        ps.setString(2, contato.getTelFixo2());
        ps.setString(3, contato.getTelCel());
        ps.setString(4, contato.getTelCel2());
        ps.setString(5, contato.getWhatsApp());
        ps.setString(6, contato.getEmail());
        ps.setString(7, contato.getEmail2());
        ps.setString(8, contato.getObservacao());
        ps.setLong(9, contato.getCliente().getId());
        ps.setLong(10, contato.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Contato contato) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Contatos SET CON_EXCLUIDO = now() WHERE CON_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, contato.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Contato> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT CON_FIXO, CON_FIXO2, CON_CELULAR, CON_CELULAR2, CON_WHATSAPP, CON_EMAIL, CON_EMAIL2, CON_OBSERVACAO FROM Contatos";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Contato> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            Contato contato = new Contato();
            contato.setId(rs.getLong("CLI_ID"));
            contato.setTelFixo(rs.getString("CON_FIXO"));
            contato.setTelFixo2(rs.getString("CON_FIXO2"));
            contato.setTelFixo(rs.getString("CON_CELULAR"));
            contato.setTelFixo(rs.getString("CON_CELULAR2"));
            contato.setTelFixo(rs.getString("CON_WHATSAPP"));
            contato.setTelFixo(rs.getString("CON_EMAIL"));
            contato.setTelFixo(rs.getString("CON_EMAIL2"));
            contato.setTelFixo(rs.getString("CON_OBSERVACAO"));
            
            listaRetorno.add(contato);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Contato pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT CON_FIXO, CON_FIXO2, CON_CELULAR, CON_CELULAR2, CON_WHATSAPP, CON_EMAIL, CON_EMAIL2, CON_OBSERVACAO FROM Contatos";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Contato retorno = new Contato();
        if (rs.next()) {
        	retorno.setId(rs.getLong("CLI_ID"));
        	retorno.setTelFixo(rs.getString("CON_FIXO"));
        	retorno.setTelFixo2(rs.getString("CON_FIXO2"));
        	retorno.setTelFixo(rs.getString("CON_CELULAR"));
        	retorno.setTelFixo(rs.getString("CON_CELULAR2"));
        	retorno.setTelFixo(rs.getString("CON_WHATSAPP"));
        	retorno.setTelFixo(rs.getString("CON_EMAIL"));
        	retorno.setTelFixo(rs.getString("CON_EMAIL2"));
        	retorno.setTelFixo(rs.getString("CON_OBSERVACAO"));
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
