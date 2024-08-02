package com.gestaoPdt.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class RecursosDAO {
	private Connection connection;

	public RecursosDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean adicionarRecursoParaReserva(int reservaID, String nomeRecurso, int quantidade) throws SQLException {
		String sql = "INSERT INTO recurso (ID_Reserva,Nome, Quantidade) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, reservaID);
			stmt.setString(2, nomeRecurso);
			stmt.setInt(3, quantidade);
			int rowsInserted = stmt.executeUpdate();

			return rowsInserted > 0;
		}

	}

	public DefaultTableModel leitura(String nome, int quantidade, int idReserv) {
		DefaultTableModel model = null;
		try {
			String query = "SELECT * FROM recursos WHERE Nome = ? AND Quantidade = ? AND ID_Reserva = ?";
			System.out.println("Executing query: " + query);
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, nome);
			st.setInt(2, quantidade);
			st.setInt(3, idReserv);
			ResultSet rs = st.executeQuery("SELECT * FROM recurso");
			
			String[] columnNames = { "ID do evento", "Nome do Recurso", "Quantidade" };

			// Create table model
			model = new DefaultTableModel(columnNames, 0);

			// Add data to the table model
			while (rs.next()) {
				int id = rs.getInt("ID_Reserva");
				String nome1 = rs.getString("Nome");
				int quantidade1 = rs.getInt("Quantidade");
				Object[] row = { nome1, id, quantidade1 };
				model.addRow(row);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

}
