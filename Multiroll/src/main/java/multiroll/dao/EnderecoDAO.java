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
import multiroll.modelo.Endereco;
import multiroll.modelo.Estado;

/**
 * 24/04/2017
 *
 * @author leleti
 */
public class EnderecoDAO {

    public Long incluir(Endereco endereco) throws ClassNotFoundException, SQLException {
    	
    	Long idEndereco = null;
    	
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Enderecos (END_ENDERECO, END_COMPLEMENTO, END_NUMERO, END_CEP, END_IDCID, END_OBSERVACAO, END_IDCLI) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, endereco.getEndereco().trim());
        ps.setString(2, endereco.getComplemento().trim());
        ps.setString(3, endereco.getNumero().trim());
        ps.setString(4, endereco.getCep().trim());
        ps.setLong(5, endereco.getCidade().getId());
        ps.setString(6, endereco.getObservacao().trim());
        ps.setLong(7, endereco.getCliente().getId());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();

		while (rs.next()) {
			idEndereco = rs.getLong(1);
		}
		
        ps.close();
        conn.close();
        
        return idEndereco;
    }

    public void alterar(Endereco endereco) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Enderecos SET END_ENDERECO = ?, END_COMPLEMENTO = ?, END_NUMERO = ?, END_CEP = ?, END_IDCID = ?, END_OBSERVACAO = ?, END_IDCLI = ? WHERE END_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, endereco.getEndereco().trim());
        ps.setString(2, endereco.getComplemento().trim());
        ps.setString(3, endereco.getNumero().trim());
        ps.setString(4, endereco.getCep().trim());
        ps.setLong(5, endereco.getCidade().getId());
        ps.setString(6, endereco.getObservacao().trim());
        ps.setLong(7, endereco.getCliente().getId());
        ps.setLong(8, endereco.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(Endereco endereco) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Enderecos SET END_EXCLUIDO = now() WHERE END_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, endereco.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<Endereco> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT \n"
                + "	en.END_ID AS enid, en.END_ENDERECO AS endereco, en.END_COMPLEMENTO AS comp, en.END_NUMERO AS numero, en.END_CEP AS cep, en.END_OBSERVACAO AS endobs,\n"
                + "	cl.CLI_ID AS clid, cl.CLI_RAZAO AS razao, cl.CLI_NOME_FANTASIA AS fantasia, cl.CLI_NOME_FUNC AS funcionario, cl.CLI_CPF AS cpf, cl.CLI_CNPJ AS cnpj, \n"
                + "	ci.CID_ID AS cid, ci.CID_NOME AS cidade,\n"
                + "	es.EST_ID AS estid, es.EST_NOME as estado, es.EST_UF AS uf\n"
                + "FROM \n"
                + "	Enderecos en \n"
                + "	INNER JOIN Clientes cl ON cl.CLI_ID = en.END_IDCLI\n"
                + "	INNER JOIN Cidades ci ON ci.CID_ID = en.END_IDCID\n"
                + "	INNER JOIN Estados es ON ci.CID_IDEST = es.EST_ID;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Endereco> listaRetorno = new ArrayList<>();
        while (rs.next()) {

            Estado est = new Estado();
            est.setId(rs.getLong("estid"));
            est.setNome(rs.getString("estado").trim());
            est.setUf(rs.getString("uf").trim());

            Cidade cid = new Cidade();
            cid.setId(rs.getLong("cid"));
            cid.setNome(rs.getString("cidade").trim());
            cid.setEstado(est);
            
            Cliente cli = new Cliente();
            cli.setId(rs.getLong("clid"));
            cli.setRazaoSocial(rs.getString("razao").trim());
            cli.setNomeFantasia(rs.getString("fantasia").trim());
            cli.setNomeFuncionario(rs.getString("funcionario").trim());
            cli.setCpf(rs.getString("cpf").trim());
            cli.setCnpj(rs.getString("cnpj").trim());
            
            Endereco end = new Endereco();
            end.setId(rs.getLong("enid"));
            end.setEndereco(rs.getString("endereco").trim());
            end.setEndereco(rs.getString("comp").trim());
            end.setEndereco(rs.getString("numero").trim());
            end.setEndereco(rs.getString("cep").trim());
            end.setEndereco(rs.getString("endobs").trim());
            end.setCidade(cid);
            end.setCliente(cli);
            
            listaRetorno.add(end);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }

    public Endereco pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT \n"
                + "	en.END_ID AS enid, en.END_ENDERECO AS endereco, en.END_COMPLEMENTO AS comp, en.END_NUMERO AS numero, en.END_CEP AS cep, en.END_OBSERVACAO AS endobs,\n"
                + "	cl.CLI_ID AS clid, cl.CLI_RAZAO AS razao, cl.CLI_NOME_FANTASIA AS fantasia, cl.CLI_NOME_FUNC AS funcionario, cl.CLI_CPF AS cpf, cl.CLI_CNPJ AS cnpj, \n"
                + "	ci.CID_ID AS cid, ci.CID_NOME AS cidade,\n"
                + "	es.EST_ID AS estid, es.EST_NOME as estado, es.EST_UF AS uf\n"
                + "FROM \n"
                + "	Enderecos en \n"
                + "	INNER JOIN Clientes cl ON cl.CLI_ID = en.END_IDCLI\n"
                + "	INNER JOIN Cidades ci ON ci.CID_ID = en.END_IDCID\n"
                + "	INNER JOIN Estados es ON ci.CID_IDEST = es.EST_ID;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Endereco retorno = new Endereco();
        if (rs.next()) {
            Estado est = new Estado();
            est.setId(rs.getLong("estid"));
            est.setNome(rs.getString("estado").trim());
            est.setUf(rs.getString("uf").trim());

            Cidade cid = new Cidade();
            cid.setId(rs.getLong("cid"));
            cid.setNome(rs.getString("cidade").trim());
            cid.setEstado(est);
            
            Cliente cli = new Cliente();
            cli.setId(rs.getLong("clid"));
            cli.setRazaoSocial(rs.getString("razao").trim());
            cli.setNomeFantasia(rs.getString("fantasia").trim());
            cli.setNomeFuncionario(rs.getString("funcionario").trim());
            cli.setCpf(rs.getString("cpf").trim());
            cli.setCnpj(rs.getString("cnpj").trim());
            
            retorno.setId(rs.getLong("enid"));
            retorno.setEndereco(rs.getString("endereco").trim());
            retorno.setEndereco(rs.getString("comp").trim());
            retorno.setEndereco(rs.getString("numero").trim());
            retorno.setEndereco(rs.getString("cep").trim());
            retorno.setEndereco(rs.getString("endobs").trim());
            retorno.setCidade(cid);
            retorno.setCliente(cli);
        }
        rs.close();
        ps.close();
        conn.close();
        return retorno;
    }
}
