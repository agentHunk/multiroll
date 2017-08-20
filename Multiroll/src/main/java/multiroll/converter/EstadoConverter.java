package multiroll.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import multiroll.modelo.Estado;
import multiroll.dao.EstadoDAO;

@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		if (valor != null) {
			EstadoDAO dao = new EstadoDAO();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Estado) valor).getId());
		}
		return null;
	}

}
