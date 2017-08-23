package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.CidadeDAO;
import multiroll.modelo.Cidade;
import multiroll.modelo.Estado;

@ManagedBean
@SessionScoped
public class CidadeController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cidade cidade = new Cidade();
	private CidadeDAO dao = new CidadeDAO();
	private Estado estadoSelecionado = new Estado();
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public void limparCampos() {
		cidade = new Cidade();
		estadoSelecionado = new Estado();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (cidade.getId() == null) {
			try {
				cidade.setEstado(estadoSelecionado);
				dao.incluir(cidade);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				cidade.setEstado(estadoSelecionado);
				dao.alterar(cidade);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return "cadastroCidade.xhtml";
	}

	public List<Cidade> getLista() {
		List<Cidade> listaRetorno = new ArrayList<>();
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
			dao.excluir(cidade);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso !");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		return "cadastroCidade.xhtml";
	}

	public String prepararParaEditar() {
		estadoSelecionado = cidade.getEstado();
		return "cadastroCidade.xhtml";
	}
}
