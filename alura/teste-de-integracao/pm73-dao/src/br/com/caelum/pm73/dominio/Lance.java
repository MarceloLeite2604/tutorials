package br.com.caelum.pm73.dominio;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lance {

	@Id @GeneratedValue
	private int id;
	private double valor;
	private Calendar data;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Leilao leilao;
	
	protected Lance() {}
	public Lance(Calendar data, Usuario usuario, double valor, Leilao leilao) {
		this.usuario = usuario;
		this.data = data;
		this.valor = valor;
		this.leilao = leilao;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Leilao getLeilao() {
		return leilao;
	}
	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	
	
}
