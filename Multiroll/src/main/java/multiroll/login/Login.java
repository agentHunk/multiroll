package multiroll.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import multiroll.modelo.Usuario;

@Named
@SessionScoped
public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String senha;
	private Usuario usuario;
	
	public String logar() {
		
		if(nome.equals("leleti") && senha.equals("123")) {
			usuario = new Usuario();
			return "/restricted/iniciosistema.xhtml?faces-redirect=true";
		}
		
		exibirMensagem("Usuário e/ou senha inválidos !");
		return null;
		
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		usuario = null;
		return "/login?faces-redirect=true";
		
	}
	
	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
