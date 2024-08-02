package com.gestaoPdt.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class AgendamentoDAO {

	private static Connection conn;

	public AgendamentoDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean inserir(String tipoEvento, String horarioInicio, String horarioFim, String eventoRecorrente,
			Date dataReserva) {
		try {
			String adicionar = "INSERT INTO reserva (tipoEvento, Data_Reserva, horario_Inicio_Reserva,horario_Termino_Reserva,eventoRecorrente ) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(adicionar);
			stmt.setString(1, tipoEvento);
			stmt.setDate(2, dataReserva);
			stmt.setString(3, horarioInicio);
			stmt.setString(4, horarioFim);
			stmt.setString(5, eventoRecorrente);
			stmt.executeUpdate();
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {

		}
		return true;
	}

	public DefaultTableModel leitura(String tipoEvento, String horarioInicio, String horarioFim,
			String eventoRecorrente, Date dataReserva) {
		DefaultTableModel model = null;
		try {
			String buscar = "SELECT * FROM reserva WHERE tipoEvento = ? AND Data_Reserva = ? AND horario_Inicio_Reserva = ? AND horario_Termino_Reserva = ? AND eventoRecorrente =?";
			PreparedStatement st = conn.prepareStatement(buscar, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, tipoEvento);
			st.setDate(2, dataReserva);
			st.setString(3, horarioInicio);
			st.setString(4, horarioFim);
			st.setString(5, eventoRecorrente);
			ResultSet rs = st.executeQuery("SELECT * FROM reserva");

			String[] columnNames = { "ID do evento", "Tipo do evento", "Data reservada", "Horário de início",
					"Horário de término", "Evento recorrente" };

			// Cria o modelo da tabela
			model = new DefaultTableModel(columnNames, 0);

			// Adiciona os dados ao modelo da tabela
			while (rs.next()) {

				String tpEvento = rs.getString("tipoEvento");
				String dtReserva = rs.getString("Data_Reserva");
				String hrInicio = rs.getString("horario_Inicio_Reserva");
				String hrFim = rs.getString("horario_Termino_Reserva");
				String evRecorrente = rs.getString("eventoRecorrente");
				int id = rs.getInt("ID_Reserva");
				Object[] row = { id, tpEvento, dtReserva, hrInicio, hrFim, evRecorrente };
				model.addRow(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	public static boolean cancelarEvento(int eventoID, String tipoEvento, Date dataReserva, String horarioInicio,
			String horarioFim, String eventoRecorrente) {
		try {
			// Instrução SQL corrigida
			String cancelar = "DELETE FROM reserva WHERE ID_Reserva = ? AND tipoEvento = ? AND Data_Reserva = ? AND horario_Inicio_Reserva = ? AND horario_Termino_Reserva = ? AND eventoRecorrente = ?";

			PreparedStatement st = conn.prepareStatement(cancelar);
			st.setInt(1, eventoID); // Corrigir a ordem dos parâmetros para incluir o ID primeiro
			st.setString(2, tipoEvento);
			st.setDate(3, dataReserva);
			st.setString(4, horarioInicio);
			st.setString(5, horarioFim);
			st.setString(6, eventoRecorrente);

			int rowsAffected = st.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
