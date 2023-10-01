package Modelo;

import java.util.Date;

public class Reserva {
	private Long id;
	private String dataInicio;
	private String dataFim;
	private Double valor;
	private String formaPagamento;

	public Reserva(String dataInicio, String dataFim, Double valor, String formaPagamento) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valor = valor;
		this.formaPagamento = formaPagamento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public Double getValor() {
		return valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

}
