package br.com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String conection = "jdbc:mysql://localhost:3306/watchweb";
		String usuario = "root";
		String senha = "root";
		return DriverManager.getConnection(conection, usuario, senha);
	}
}
