package br.com.caelum.pm73.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.com.caelum.pm73.dominio.Lance;
import br.com.caelum.pm73.dominio.Leilao;
import br.com.caelum.pm73.dominio.Usuario;

public class LeilaoDao {

	private final Session session;

	public LeilaoDao(Session session) {
		this.session = session;
	}
	
	public void salvar(Leilao leilao) {
		session.save(leilao);
		
		for(Lance lance : leilao.getLances()) {
			session.save(lance);
		}
	}
	
	public Leilao porId(int id) {
		return (Leilao) session.get(Leilao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Leilao> novos() {
		return session.createQuery("from Leilao l where usado = false")
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Leilao> antigos() {
		Calendar seteDiasAtras = Calendar.getInstance();
		seteDiasAtras.add(Calendar.DAY_OF_MONTH, -7);
		
		return session.createQuery("from Leilao l where dataAbertura < :data")
				.setParameter("data", seteDiasAtras)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Leilao> porPeriodo(Calendar inicio, Calendar fim) {
		return session.createQuery("from Leilao l where l.dataAbertura " +
				"between :inicio and :fim and l.encerrado = false")
				.setParameter("inicio", inicio)
				.setParameter("fim", fim)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Leilao> disputadosEntre(double inicio, double fim) {
		return session.createQuery("from Leilao l where l.valorInicial " +
				"between :inicio and :fim and l.encerrado = false " +
				"and size(l.lances) > 3")
				.setParameter("inicio", inicio)
				.setParameter("fim", fim)
				.list();
	}
	
	public Long total() {
		return (Long) session.createQuery("select count(l) from Leilao l where l.encerrado = false")
				.uniqueResult();
	}
	
	public void atualiza(Leilao leilao) {
		session.merge(leilao);
	}
	
	public void deleta(Leilao leilao) {
		session.delete(leilao);
	}
	
	public void deletaEncerrados() {
		session
			.createQuery("delete from Leilao l where l.encerrado = true")
			.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Leilao> listaLeiloesDoUsuario(Usuario usuario) {
		return session.createQuery("select lance.leilao " +
								   "from Lance lance " +
								   "where lance.usuario = :usuario")
				.setParameter("usuario", usuario).list();
	}
	
	public double getValorInicialMedioDoUsuario(Usuario usuario) {
		return (Double) session.createQuery("select avg(lance.leilao.valorInicial) " +
											"from Lance lance " +
											"where lance.usuario = :usuario")
					.setParameter("usuario", usuario)
					.uniqueResult();
	}
}
