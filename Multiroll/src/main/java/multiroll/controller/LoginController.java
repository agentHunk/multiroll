package multiroll.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginController{

	private Usuario usuario = new Usuario();

	public String doEfetuarLogin() {
		try {
			
//			Implementar o DAO aqui...
			
			if ("leleti".equals(usuario.getLogin()) && "123".equals(usuario.getSenha())) {
				usuario = new Usuario();
				
				return "/restricted/inicio.xhtml?faces-redirect=true";
			} else {
				exibirMensagem("Usuário ou Senha Incorretos");
			}
		} catch (Exception e) {
			exibirMensagem("Erro ao realizar a  operação : " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		usuario = null;
		return "/login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
