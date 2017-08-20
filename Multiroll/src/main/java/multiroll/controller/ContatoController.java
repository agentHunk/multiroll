package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.ContatoDAO;
import multiroll.modelo.Contato;

@ManagedBean
@SessionScoped
public class ContatoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contato contato = new Contato();
	private ContatoDAO dao = new ContatoDAO();
	//private Estado estadoSelecionado = new Estado();
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

//	public Estado getEstadoSelecionado() {
//		return estadoSelecionado;
//	}
//
//	public void setEstadoSelecionado(Estado estadoSelecionado) {
//		this.estadoSelecionado = estadoSelecionado;
//	}

	public void limparCampos() {
		contato = new Contato();
		//estadoSelecionado = new Estado();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (contato.getId() == null) {
			try {
//				cidade.setEstado(estadoSelecionado);
				dao.incluir(contato);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
//				cidade.setEstado(estadoSelecionado);
				dao.alterar(contato);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		}
//		return "cadastroCidade.xhtml";
			return "Sucesso";
	}

	public List<Contato> getLista() {
		List<Contato> listaRetorno = new ArrayList<>();
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
			dao.excluir(contato);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso !");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		//return "listaCidade.xhtml";
		return "Sucesso";
	}

	public String prepararParaEditar() {
//		estadoSelecionado = cidade.getEstado();
		return "Sucesso";
//		return "cadastroCidade.xhtml";
	}
}
