package br.com.caelum.leilao.dominio;

import java.util.Calendar;

public class Pagamento {

	private double valor;
	private Calendar data;

	public Pagamento(double valor, Calendar data) {
		this.valor = valor;
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public Calendar getData() {
		return data;
	}
}
