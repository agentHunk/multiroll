package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.ServicoDAO;
import multiroll.modelo.Categoria;
import multiroll.modelo.Servico;

@ManagedBean
@SessionScoped
public class ServicoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Servico servico = new Servico();
	private ServicoDAO dao = new ServicoDAO();
	private Categoria categoriaSelecionada = new Categoria();
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public void limparCampos() {
		servico = new Servico();
		categoriaSelecionada = new Categoria();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (servico.getId() == null) {
			try {
				servico.setCategoria(categoriaSelecionada);
				dao.incluir(servico);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				servico.setCategoria(categoriaSelecionada);
				dao.alterar(servico);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return "cadastroServico.xhtml";
	}

	public List<Servico> getLista() {
		List<Servico> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public String excluir() {
		try {
			dao.excluir(servico);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso !");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		return "cadastroServico.xhtml";
	}

	public String prepararParaEditar() {
		categoriaSelecionada = servico.getCategoria();
		return "cadastroServico.xhtml";
	}
}
