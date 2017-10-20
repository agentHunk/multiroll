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
import multiroll.modelo.MaquinaItem;

@ManagedBean
@SessionScoped
public class MaquinaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Maquina maquina = new Maquina();
	private MaquinaItem maquinaItem = new MaquinaItem();
	private  List<MaquinaItem> listaItem = new ArrayList<MaquinaItem>();
	private MaquinaDAO daoMaquina = new MaquinaDAO();

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}
	
	public MaquinaItem getMaquinaItem() {
		return maquinaItem;
	}

	public void setMaquinaItem(MaquinaItem maquinaItem) {
		this.maquinaItem = maquinaItem;
	}

	public List<MaquinaItem> getListaItem() {
		return listaItem;
	}

	public void setListaItem(List<MaquinaItem> listaItem) {
		this.listaItem = listaItem;
	}

	public void limparCampos() {
		maquinaItem = new MaquinaItem();
		
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
	
	public List<MaquinaItem> adicionarItem (){
		
		maquinaItem.setMaquina(maquina);
		listaItem.add(maquinaItem);
		
		return listaItem;
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
	
	public List<MaquinaItem> getListarItem() {
		return listaItem;
	}

	public String prepararParaEditar() {
		return null;
	}
	
	public String cadastrarNovaMaquina() {
		return "cadastroMaquina.xhtml";
	}
}
