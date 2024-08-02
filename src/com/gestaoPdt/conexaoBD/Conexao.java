package com.gestaoPdt.conexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/gestaopdt";
		String username = "root";
		String password = "123456";
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Conectou");
		} catch (SQLException e) {
			System.out.println("Erro");
		}
		return conn;
	}
}
