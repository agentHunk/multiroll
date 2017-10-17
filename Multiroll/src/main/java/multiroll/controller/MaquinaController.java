package multiroll.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import multiroll.dao.MaquinaDAO;
import multiroll.modelo.Maquina;

@ManagedBean
@SessionScoped
public class MaquinaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Maquina maquina = new Maquina();
	private MaquinaDAO daoMaquina = new MaquinaDAO();

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public void limparCampos() {

	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		return null;
	}

	public String excluir() {
		return null;
	}

	public List<Maquina> getLista() {
		List<Maquina> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = daoMaquina.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}

	public String prepararParaEditar() {
		return null;
	}
}
