package multiroll.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.FuncionarioDAO;
import multiroll.modelo.Funcionario;

@ManagedBean
@SessionScoped
public class FuncionarioController {

	private Funcionario funcionario = new Funcionario();
	private FuncionarioDAO dao = new FuncionarioDAO();
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void limparCampos() {
		funcionario = new Funcionario();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (funcionario.getId() == null) {
			try {
				dao.incluir(funcionario);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		} else {
			try {
				dao.alterar(funcionario);
				limparCampos();
				exibirMensagem("Alteração realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
			}
		}
		return "cadastroFuncionario.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(funcionario);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return "listaFuncionario.xthml";
	}

	public String prepararParaEditar() {
		return "cadastroFuncionario.xhtml";
	}

	public List<Funcionario> getLista() {
		List<Funcionario> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}
}
