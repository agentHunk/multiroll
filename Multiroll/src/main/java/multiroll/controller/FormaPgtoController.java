package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.FormaPgtoDAO;
import multiroll.modelo.FormaPgto;

@ManagedBean
@SessionScoped
public class FormaPgtoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormaPgto formaPgto = new FormaPgto();
	private FormaPgtoDAO dao = new FormaPgtoDAO();
	

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public void limparCampos() {
		formaPgto = new FormaPgto();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (formaPgto.getId() == null) {
			try {
				dao.incluir(formaPgto);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		} else {
			try {
				dao.alterar(formaPgto);
				limparCampos();
				exibirMensagem("Alteração realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		}
		return "cadastroFormaPgto.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(formaPgto);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "listaFormaPgto.xthml";
	}

	public String prepararParaEditar() {
		return "cadastroFormaPgto.xhtml";
	}

	public List<FormaPgto> getLista() {
		List<FormaPgto> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}
}
