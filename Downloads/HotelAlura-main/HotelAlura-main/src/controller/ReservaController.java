package controller;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAO.ReservaDAO;
import Factory.ConnectionFactory;
import Modelo.Reserva;
import views.RegistroHospede;
import views.ReservasView;

public class ReservaController {
	Double diaria = 20.00;
	
	public void diasChange(JDateChooser txtDataE, JDateChooser txtDataS, JTextField txtValor) {
		if(ReservasView.txtDataE.getDate() != null && ReservasView.txtDataS.getDate() != null) {
			String value = new ReservaController().taxaDiaria(ReservasView.txtDataE.getDate(), ReservasView.txtDataS.getDate());
			txtValor.setText(value);
			
			//System.err.println("Aqui: "+ ReservasView.txtValor.getText().isEmpty());
			
		}
	}
	
	public String taxaDiaria(Date dataInicio, Date dataFim) {
		System.out.println("Inicio: " + dataInicio);				
		System.out.println("Fim: " + dataFim);
		Long inicio = dataInicio.getTime();
		Long fim = dataFim.getTime();
		
//		System.out.println(TimeUnit.DAYS.convert((fim - inicio), TimeUnit.MILLISECONDS) + 1);	
		
		if(fim >= inicio) {
			Long dias = TimeUnit.DAYS.convert((fim - inicio), TimeUnit.MILLISECONDS) + 1;
			Double taxa = dias * this.diaria; 
			return "$" + String.valueOf(taxa).replace(".", ",");
		} else {
			JOptionPane.showMessageDialog(null, "A data de inicio da reserva é maior do que a data de fim", "Alerta",JOptionPane.ERROR_MESSAGE);
			return "";
		}
		
	}

	public void cadastrar () {
			String valueString = ReservasView.txtValor.getText()
					.replace("$", "").replace(",", ".").trim();
			
			Double valorDouble = Double.parseDouble(valueString);
			System.out.println(valorDouble);
			
			String dataInicio = new SimpleDateFormat("yyyy-MM-dd").format(ReservasView.txtDataE.getDate());
			String dataFim = new SimpleDateFormat("yyyy-MM-dd").format(ReservasView.txtDataS.getDate());
//			System.out.println(dataInicio);
//			Date dataInicio = ReservasView.txtDataE.getDate();
//			Date dataFim = ReservasView.txtDataS.getDate();
		
			Reserva reserva = new Reserva(dataInicio, dataFim, valorDouble, valueString);
			
			Connection connection = new ConnectionFactory().recuperarConexao();
			ReservaDAO reservaDAO = new ReservaDAO(connection);
			long id = reservaDAO.cadastrarReserva(reserva);
			RegistroHospede.txtNreserva = new JTextField();
			RegistroHospede.txtNreserva.setText(String.valueOf(id));
			
			
	}
}
