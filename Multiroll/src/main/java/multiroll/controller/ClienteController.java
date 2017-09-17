package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.ClienteDAO;
import multiroll.dao.ContatoDAO;
import multiroll.dao.EnderecoDAO;
import multiroll.modelo.Cidade;
import multiroll.modelo.Cliente;
import multiroll.modelo.Contato;
import multiroll.modelo.Endereco;

@ManagedBean
@SessionScoped
public class ClienteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private Contato contato = new Contato();
	private Endereco endereco = new Endereco();

	private ClienteDAO daoCliente = new ClienteDAO();
	private ContatoDAO daoContato = new ContatoDAO();
	private EnderecoDAO daoEndereco = new EnderecoDAO();

	private Cidade cidadeSelecionada = new Cidade();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public void limparCampos() {
		cliente = new Cliente();
		contato = new Contato();
		endereco = new Endereco();
		cidadeSelecionada = new Cidade();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			String cpf = validaCpf(cliente.getCpf());
			String cnpj = validaCnpj(cliente.getCnpj());
			
			String fixo = validaTelefone(contato.getTelFixo());
			String cel = validaCel(contato.getTelCel());

			if (cpf == null && cnpj == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe Cpf ou CNPJ!", ""));
			} else if (fixo == null && cel == null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe Telefone Fixo ou Celular!", ""));
			}else {
				if (cliente.getId() == null) {
					cliente.setCpf(cpf);
					cliente.setCnpj(cnpj);

					Long lidCliente = daoCliente.incluir(cliente);
					cliente.setId(lidCliente);

					contato.setCliente(cliente);
					Long lidContato = daoContato.incluir(contato);
					contato.setId(lidContato);

					endereco.setCidade(getCidadeSelecionada());
					endereco.setCliente(cliente);
					Long lidEndereco = daoEndereco.incluir(endereco);
					endereco.setId(lidEndereco);

					limparCampos();
					exibirMensagem("Inclusão realizada  com sucesso !");

				} else {
					cliente.setCpf(cpf);
					cliente.setCnpj(cnpj);

					daoCliente.alterar(cliente);

					endereco.setCliente(cliente);
					endereco.setCidade(getCidadeSelecionada());
					daoEndereco.alterar(endereco);

					contato.setCliente(cliente);
					daoContato.alterar(contato);

					limparCampos();
					exibirMensagem("Alteração realizada  com sucesso !");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "cadastroCliente.xhtml";
	}

	public String excluir() {
		try {
			daoCliente.excluir(cliente);
			daoContato.excluir(cliente.getContato());
			daoEndereco.excluir(cliente.getEndereco());
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "cadastroCliente.xthml";
	}

	public List<Cliente> getLista() {
		List<Cliente> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = daoCliente.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}

	public String prepararParaEditar() {
		contato = cliente.getContato();
		endereco = cliente.getEndereco();
		cidadeSelecionada = cliente.getEndereco().getCidade();

		return "cadastroCliente.xhtml";
	}

	public String validaCpf(String cpf) {
		if (cpf.equalsIgnoreCase("")) {
			cpf = null;
		}
		return cpf;
	}

	public String validaCnpj(String cnpj) {
		if (cnpj.equalsIgnoreCase("")) {
			cnpj = null;
		}
		return cnpj;
	}
	
	public String validaTelefone(String fixo) {
		if (fixo.equalsIgnoreCase("")) {
			fixo = null;
		}
		return fixo;
	}

	public String validaCel(String cel) {
		if (cel.equalsIgnoreCase("")) {
			cel = null;
		}
		return cel;
	}
}
