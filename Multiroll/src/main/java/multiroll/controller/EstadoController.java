package multiroll.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.EstadoDAO;
import multiroll.modelo.Estado;

@ManagedBean
@SessionScoped
public class EstadoController {

	private Estado estado = new Estado();
	private EstadoDAO dao = new EstadoDAO();
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void limparCampos() {
		estado = new Estado();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (estado.getId() == null) {
			try {
				dao.incluir(estado);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		} else {
			try {
				dao.alterar(estado);
				limparCampos();
				exibirMensagem("Alteração realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		}
		return "cadastroEstado.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(estado);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "listaEstado.xthml";
	}

	public String prepararParaEditar() {
		return "cadastroEstado.xhtml";
	}

	public List<Estado> getLista() {
		List<Estado> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}
}
