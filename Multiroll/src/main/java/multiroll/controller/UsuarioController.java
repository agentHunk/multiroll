package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.UsuarioDAO;
import multiroll.modelo.Funcionario;
import multiroll.modelo.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private UsuarioDAO dao = new UsuarioDAO();
	private Funcionario FuncionarioSelecionado = new Funcionario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcionario getFuncionarioSelecionado() {
		return FuncionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario FuncionarioSelecionado) {
		this.FuncionarioSelecionado = FuncionarioSelecionado;
	}

	public void limparCampos() {
		usuario = new Usuario();
		FuncionarioSelecionado = new Funcionario();
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		if (usuario.getId() == null) {
			try {
				usuario.setSenha(usuario.getSenha());
				usuario.setFuncionario(FuncionarioSelecionado);
				dao.incluir(usuario);
				limparCampos();
				exibirMensagem("Inclusão realizada  com sucesso !");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			try {
				usuario.setSenha(usuario.getSenha());
				usuario.setFuncionario(FuncionarioSelecionado);
				dao.alterar(usuario);
				limparCampos();
				exibirMensagem("Alteração realizada com sucesso!");
			} catch (ClassNotFoundException | SQLException e) {
				exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return "cadastroUsuario.xhtml";
	}

	public List<Usuario> getLista() {
		List<Usuario> listaRetorno = new ArrayList<>();
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
			dao.excluir(usuario);
			limparCampos();
			exibirMensagem("Exclusão realizada com sucesso !");
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		return "listaUsuario.xhtml";
	}

	public String prepararParaEditar() {
		FuncionarioSelecionado = usuario.getFuncionario();
		return "cadastroUsuario.xhtml";
	}
}
