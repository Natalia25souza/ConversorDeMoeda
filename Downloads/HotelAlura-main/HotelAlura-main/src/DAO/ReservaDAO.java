package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Reserva;

public class ReservaDAO {
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public long cadastrarReserva(Reserva reserva) {
		try {
			String sql = "INSERT INTO Reservas (DataEntrada, DataSaida, Valor, FormaPagamento) VALUES (?, ?, ?, ?)";
			
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, reserva.getDataInicio());
				pstm.setString(2, reserva.getDataFim());
				pstm.setDouble(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());
				
				pstm.execute();
				
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getLong(1));
					}
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
			
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return 0;
	}
}
