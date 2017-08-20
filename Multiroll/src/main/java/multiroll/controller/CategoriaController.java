package multiroll.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.CategoriaDAO;
import multiroll.modelo.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaController {

	private Categoria categoria = new Categoria();
	private CategoriaDAO dao = new CategoriaDAO();
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void limparCampos() {
		categoria = new Categoria();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (categoria.getId() == null) {
			try {
				dao.incluir(categoria);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		} else {
			try {
				dao.alterar(categoria);
				limparCampos();
				exibirMensagem("Alteração realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		}
		return "cadastroCategoria.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(categoria);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!!!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "listaCategoria.xthml";
	}

	public String prepararParaEditar() {
		return "cadastroCategoria.xhtml";
	}

	public List<Categoria> getLista() {
		List<Categoria> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a Operação, tente de novo: " + e.getMessage());
		}
		return listaRetorno;
	}
}
