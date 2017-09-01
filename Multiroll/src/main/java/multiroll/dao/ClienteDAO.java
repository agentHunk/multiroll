package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Cidade;
import multiroll.modelo.Cliente;
import multiroll.modelo.Contato;
import multiroll.modelo.Endereco;
import multiroll.modelo.Estado;

/**
 * 24/04/2017
 *
 * @author leleti
 */
public class ClienteDAO {

	public Long incluir(Cliente cliente) throws ClassNotFoundException, SQLException {

		Long idCliente = null;

		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "INSERT INTO Clientes (CLI_RAZAO, CLI_NOME_FANTASIA, CLI_NOME_FUNC, CLI_CPF, CLI_CNPJ) VALUES (?,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cliente.getRazaoSocial());
		ps.setString(2, cliente.getNomeFantasia());
		ps.setString(3, cliente.getNomeFuncionario());
		ps.setString(4, cliente.getCpf());
		ps.setString(5, cliente.getCnpj());
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		while (rs.next()) {
			idCliente = rs.getLong(1);
		}

		ps.close();
		conn.close();

		return idCliente;
	}

	public void alterar(Cliente cliente) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "UPDATE Clientes SET CLI_RAZAO = ?, CLI_NOME_FANTASIA = ?, CLI_NOME_FUNC = ?, CLI_CPF = ?, CLI_CNPJ = ? WHERE CLI_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cliente.getRazaoSocial());
		ps.setString(2, cliente.getNomeFantasia());
		ps.setString(3, cliente.getNomeFuncionario());
		ps.setString(4, cliente.getCpf());
		ps.setString(5, cliente.getCnpj());
		ps.setLong(6, cliente.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void excluir(Cliente cliente) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "UPDATE Clientes SET CLI_EXCLUIDO = now() WHERE CLI_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, cliente.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<Cliente> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "SELECT \n"
				+ "	cli.CLI_ID AS idcli, cli.CLI_RAZAO AS razao, cli.CLI_NOME_FANTASIA AS fantasia, \n"
				+ "    cli.CLI_NOME_FUNC AS funcionario, cli.CLI_CPF AS cpf, cli.CLI_CNPJ AS cnpj,\n"
				+ "    con.CON_ID AS idcon, con.CON_FIXO AS fixo, con.CON_FIXO2 AS fixo2, con.CON_CELULAR AS cel, \n"
				+ "    con.CON_CELULAR2 AS cel2, con.CON_WHATSAPP AS whats, con.CON_EMAIL AS email, con.CON_EMAIL2 AS email2, con.CON_OBSERVACAO AS conobs,\n"
				+ "    e.END_ID AS idend, e.END_ENDERECO AS endereco, e.END_COMPLEMENTO AS comp, e.END_NUMERO AS num, e.END_CEP AS cep, e.END_OBSERVACAO AS endobs,\n"
				+ "    ci.CID_ID AS idcid, ci.CID_NOME AS cidnome, est.EST_ID AS idest, est.EST_NOME AS nomest, est.EST_UF AS uf\n"
				+ "FROM Enderecos e \n" + "    INNER JOIN Clientes cli ON cli.CLI_ID = e.END_IDCLI\n"
				+ "    INNER JOIN Contatos con ON cli.CLI_ID = con.CON_IDCLI\n"
				+ "    INNER JOIN Cidades ci ON e.END_IDCID = ci.CID_ID\n"
				+ "    INNER JOIN Estados est ON est.EST_ID = ci.CID_IDEST\n" + "WHERE"
				+ "	   cli.CLI_EXCLUIDO IS NULL ORDER BY cli.CLI_RAZAO";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Cliente> listaRetorno = new ArrayList<>();
		while (rs.next()) {

			Estado estado = new Estado();
			estado.setId(rs.getLong("idest"));
			estado.setNome(rs.getString("nomest").trim());
			estado.setUf(rs.getString("uf").trim());

			Cidade cidade = new Cidade();
			cidade.setId(rs.getLong("idcid"));
			cidade.setNome(rs.getString("cidnome").trim());
			cidade.setEstado(estado);

			Endereco endereco = new Endereco();
			endereco.setId(rs.getLong("idend"));
			endereco.setComplemento(rs.getString("comp").trim());
			endereco.setEndereco(rs.getString("endereco").trim());
			endereco.setNumero(rs.getString("num").trim());
			endereco.setCep(rs.getString("cep").trim());
			endereco.setObservacao(rs.getString("endobs").trim());
			endereco.setCidade(cidade);

			Contato contato = new Contato();
			contato.setId(rs.getLong("idcon"));
			contato.setTelFixo(rs.getString("fixo").trim());
			contato.setTelFixo2(rs.getString("fixo2").trim());
			contato.setTelCel(rs.getString("cel").trim());
			contato.setTelCel2(rs.getString("cel2").trim());
			contato.setEmail(rs.getString("email").trim());
			contato.setEmail2(rs.getString("email2").trim());
			contato.setWhatsApp(rs.getString("whats").trim());
			contato.setObservacao(rs.getString("conobs").trim());
			
			Cliente cliente = new Cliente();
			cliente.setId(rs.getLong("idcli"));
			cliente.setRazaoSocial(rs.getString("razao").trim());
			cliente.setNomeFantasia(rs.getString("fantasia").trim());
			cliente.setNomeFuncionario(rs.getString("funcionario").trim());
			cliente.setCpf(rs.getString("cpf"));
			cliente.setCnpj(rs.getString("cnpj"));
			cliente.setEndereco(endereco);
			cliente.setContato(contato);
			
			listaRetorno.add(cliente);
		}
		rs.close();
		ps.close();
		conn.close();
		return listaRetorno;
	}

	public Cliente pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBancoDados();
		String sql = "SELECT CLI_ID, CLI_RAZAO FROM Clientes WHERE CLI_ID = ? AND CLI_EXCLUIDO IS NULL";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		Cliente retorno = new Cliente();
		if (rs.next()) {
			retorno.setId(rs.getLong("CLI_ID"));
			retorno.setRazaoSocial(rs.getString("CLI_RAZAO").trim());
			retorno.setNomeFantasia(rs.getString("CLI_NOME_FANTASIA").trim());
			retorno.setNomeFuncionario(rs.getString("CLI_NOME_FUNC").trim());
			retorno.setCpf(rs.getString("CLI_CPF").trim());
			retorno.setCnpj(rs.getString("CLI_CNPJ").trim());
		}
		rs.close();
		ps.close();
		conn.close();
		return retorno;
	}
}
