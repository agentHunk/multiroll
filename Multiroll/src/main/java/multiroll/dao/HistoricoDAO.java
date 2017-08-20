package multiroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import multiroll.conexao.Conexao;
import multiroll.modelo.Categoria;
import multiroll.modelo.Cidade;
import multiroll.modelo.Cliente;
import multiroll.modelo.Contato;
import multiroll.modelo.Endereco;
import multiroll.modelo.Estado;
import multiroll.modelo.FormaPgto;
import multiroll.modelo.Funcionario;
import multiroll.modelo.HistoricoOrdemServico;
import multiroll.modelo.OrdemServico;
import multiroll.modelo.OrdemServicoItem;
import multiroll.modelo.Servico;
import multiroll.modelo.Usuario;

/**
 * 19/04/2017
 *
 * @author leleti
 */
public class HistoricoDAO {

    public void incluir(HistoricoOrdemServico historicoOrd) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "INSERT INTO Historicos (HIS_OBSERVACAO, HIS_IDORD) VALUES (?, ?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, historicoOrd.getObservacao());
        ps.setLong(2, historicoOrd.getOrdemServico().getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void alterar(HistoricoOrdemServico historicoOrd) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "UPDATE Historicos SET HIS_OBSERVACAO = ?, HIS_IDORD = ? WHERE HIS_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, historicoOrd.getObservacao());
        ps.setLong(2, historicoOrd.getOrdemServico().getId());
        ps.setLong(3, historicoOrd.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void excluir(HistoricoOrdemServico historicoOrd) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "DELETE FROM Historicos WHERE HIS_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, historicoOrd.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public List<HistoricoOrdemServico> listar() throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarNoBancoDados();
        String sql = "SELECT \n"
                + "	h.HIS_ID AS hid, h.HIS_OBSERVACAO AS hobservacao, \n"
                + "	o.ORD_ID AS oid, o.ORD_VALOR_TOTAL AS ovalor, o.ORD_DATA_SERVICO AS dataSer, o.ORD_DATA_ENTREGA AS odataEnt, o.ORD_DATA_PREVISAO AS dataPrev, o.ORD_OBSERVACAO AS oObs,\n"
                + "	u.USU_ID AS uid, u.USU_LOGIN AS ulogin, u.USU_SENHA AS usenha, \n"
                + "	fu.FUN_ID AS fuid, fu.FUN_NOME AS funome, fu.FUN_CARGO AS fucargo, \n"
                + "	fo.PGTO_ID AS foid, fo.PGTO_NOME AS fonome, fo.PGTO_SIGLA AS fosigla, \n"
                + "	os.OSI_QUANTIDADE AS osqtde, os.OSI_VALOR_UNIT AS osvalunit, os.OSI_DIAMETRO_F AS diamf, os.OSI_DIAMETRO_B AS diamb, os.OSI_CUMPRIMENTO AS oscump, os.OSI_DESCRICAO AS osdesc, \n"
                + "	se.SER_ID AS seid, se.SER_TIPO AS setipo, \n"
                + "	ca.CAT_ID AS caid, ca.CAT_NOME AS canome, \n"
                + "	cl.CLI_ID AS clid, cl.CLI_RAZAO AS clrazao, cl.CLI_NOME_FANTASIA AS clfantasia, cl.CLI_NOME_FUNC AS clfunc, cl.CLI_CPF AS cpf, cl.CLI_CNPJ AS cnpj, \n"
                + "	co.CON_ID AS coid, co.CON_FIXO AS fixo, co.CON_FIXO2 AS fixo2, co.CON_CELULAR AS celular, co.CON_CELULAR2 AS celular2, co.CON_WHATSAPP AS what, co.CON_EMAIL AS email, co.CON_EMAIL2 AS email2, co.CON_OBSERVACAO AS coobs, \n"
                + "	en.END_ID AS enid, en.END_ENDERECO AS endereco, en.END_COMPLEMENTO AS comp, en.END_NUMERO AS numero, en.END_CEP AS cep, en.END_OBSERVACAO AS endobs, \n"
                + "	ci.CID_ID AS cid, ci.CID_NOME AS cidade,\n"
                + "	es.EST_ID AS eid, es.EST_NOME AS estado, es.EST_UF AS uf\n"
                + "	FROM Historico h \n"
                + "	INNER JOIN Ordem_Servicos o ON h.HIS_IDORD = o.ORD_ID\n"
                + "	INNER JOIN Usuarios u ON u.USU_ID = o.ORD_IDUSU\n"
                + "	INNER JOIN Funcionarios fu ON FUN_ID = u.USU_IDFUN\n"
                + "	INNER JOIN Ordem_Servicos_itens os ON o.ORD_ID = os.OSI_IDORD\n"
                + "	INNER JOIN Servicos se ON se.SER_ID = os.OSI_IDSER\n"
                + "	INNER JOIN Categorias ca ON ca.CAT_ID = se.SER_IDCAR\n"
                + "	INNER JOIN Forma_pgtos fo ON fo.PGTO_ID = o.ORD_IDPGTO\n"
                + "	INNER JOIN Clientes cl ON cl.CLI_ID = o.ORD_IDCLI\n"
                + "	INNER JOIN Contatos co ON co.CON_IDCLI = cl.CLI_ID\n"
                + "	INNER JOIN Enderecos en ON en.END_IDCLI = cl.CLI_ID\n"
                + "	INNER JOIN Cidades ci ON ci.CID_ID = en.END_IDCID\n"
                + "	INNER JOIN Estados es ON es.EST_ID = ci.CID_IDEST;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<HistoricoOrdemServico> listaRetorno = new ArrayList<>();
        while (rs.next()) {
            
            Estado est = new Estado();
            est.setId(rs.getLong("eid"));
            est.setNome(rs.getString("estado").trim());
            est.setUf(rs.getString("uf").trim());
            
            Cidade cid = new Cidade();
            cid.setId(rs.getLong("cid"));
            cid.setNome(rs.getString("cidade").trim());
            cid.setEstado(est);
            
            Cliente cli = new Cliente();
            cli.setId(rs.getLong("clid"));
            cli.setRazaoSocial(rs.getString("clrazao").trim());
            cli.setRazaoSocial(rs.getString("clfantasia").trim());
            cli.setRazaoSocial(rs.getString("clfunc").trim());
            cli.setRazaoSocial(rs.getString("cpf").trim());
            cli.setRazaoSocial(rs.getString("cnpj").trim());
            
            Endereco end = new Endereco();
            end.setId(rs.getLong("enid"));
            end.setEndereco(rs.getString("endereco").trim());
            end.setComplemento(rs.getString("comp").trim());
            end.setNumero(rs.getString("numero").trim());
            end.setCep(rs.getString("cep").trim());
            end.setObservacao(rs.getString("endobs").trim());
            end.setCidade(cid);
            end.setCliente(cli);
            
            Contato cont = new Contato();
            cont.setId(rs.getLong("coid"));
            cont.setTelFixo(rs.getString("fixo").trim());
            cont.setTelFixo2(rs.getString("fixo2").trim());
            cont.setTelCel(rs.getString("celular").trim());
            cont.setTelCel2(rs.getString("celular2").trim());
            cont.setWhatsApp(rs.getString("what").trim());
            cont.setEmail(rs.getString("email").trim());
            cont.setEmail2(rs.getString("email2").trim());
            cont.setObservacao(rs.getString("coobs").trim());
            cont.setCliente(cli);
            
            FormaPgto pgto = new FormaPgto();
            pgto.setId(rs.getLong("foid"));
            pgto.setNome(rs.getString("fonome").trim());
            pgto.setSigla(rs.getString("fosigla").trim());
            
            Funcionario func = new Funcionario();
            func.setId(rs.getLong("fid"));
            func.setNome(rs.getString("fnome").trim());
            func.setCargo(rs.getString("fcargo").trim());

            Usuario usu = new Usuario();
            usu.setId(rs.getLong("uid"));
            usu.setLogin(rs.getString("ulogin").trim());
            usu.setSenha(rs.getString("usenha").trim());
            usu.setFuncionario(func);
            
            Categoria cat = new Categoria();
            cat.setId(rs.getLong("caid"));
            cat.setNome(rs.getString("canome").trim());

            Servico ser = new Servico();
            ser.setId(rs.getLong("seid"));
            ser.setNome(rs.getString("setipo").trim());
            ser.setCategoria(cat);
            
            OrdemServico os = new OrdemServico();
            os.setId(rs.getLong("oid"));
            os.setValorTotal(rs.getDouble("ovalor"));
            os.setDataServico(rs.getDate("dataSer"));
            os.setDataEntrega(rs.getDate("odataEnt"));
            os.setDataPrevisao(rs.getDate("dataPrev"));
            os.setObservacao(rs.getString("oObs").trim());
            os.setCliente(cli);
            os.setFormaPgto(pgto);
            os.setUsuario(usu);
            
            OrdemServicoItem osit = new OrdemServicoItem();
            osit.setOrdemServico(os);
            osit.setServico(ser);
            osit.setQuantidade(rs.getInt("osqtde"));
            osit.setValorUnit(rs.getDouble("osvalunit"));
            osit.setDiametroB(rs.getString("diamb").trim());
            osit.setDiametroF(rs.getString("diamf").trim());
            osit.setCumprimento(rs.getString("oscump").trim());
            osit.setDescricao(rs.getString("osdesc").trim());
            
            HistoricoOrdemServico historicoOrd = new HistoricoOrdemServico();
            historicoOrd.setObservacao(rs.getString("hobservacao").trim());
            historicoOrd.setOrdemServico(os);

            listaRetorno.add(historicoOrd);
        }
        rs.close();
        ps.close();
        conn.close();
        return listaRetorno;
    }
    
}
