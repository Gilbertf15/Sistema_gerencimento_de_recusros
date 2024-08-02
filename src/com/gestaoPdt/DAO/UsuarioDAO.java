package com.gestaoPdt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean inserir(String usuario, String senha) {
		try {
			String adicionar = "INSERT INTO usuario (Nome_Usuario, Senha) VALUES (?, ?)";

			PreparedStatement stm = conn.prepareStatement(adicionar);
			stm.setString(1, usuario);
			stm.setString(2, senha);
			stm.executeUpdate();
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				System.out.print(" - " + rs.getString("Nome_Usuario"));

				return true;
			} else {
				System.out.println("erro");
			}

		} catch (SQLException e) {

		}
		return false;
	}

	public Boolean searchUserRegistration(String usuario, String senha) {
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM usuario WHERE Nome_Usuario = ? AND Senha = ?");
			st.setString(1, usuario);
			st.setString(2, senha);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				System.out.print(" - " + rs.getString("Nome_Usuario"));

				return true;
			} else {
				System.out.println("nao achou");
			}

			// String nameBD = rs.getString("nome");
			// String passwordBD = rs.getString("senha");

			// Usuario user = new Usuario(nameBD, passwordBD);

			// rs.close();
			// st.close();

			// return user;
		} catch (SQLException e) {

		}
		return false;
	}

}