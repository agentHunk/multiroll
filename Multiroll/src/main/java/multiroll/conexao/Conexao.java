package multiroll.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 18/04/2017
 *
 * @author leleti
 */
public class Conexao {

    public Connection conectarNoBancoDados() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/Multiroll";
        String usuario = "root";
        String senha = "root";
        conn = DriverManager.getConnection(url, usuario, senha);
        return conn;
    }

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        try {
            conexao.conectarNoBancoDados();
            System.out.println("Conectou no banco de dados Multiroll.");
        } catch (ClassNotFoundException e) {
        	System.out.println("Classe Não encontrada");
        	e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Banco nao encontrado");
			e.printStackTrace();
		}
    }

}

