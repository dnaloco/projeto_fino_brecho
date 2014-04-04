package br.arthur.models;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.ContaReceber;
import br.arthur.entities.Entrada;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.Saida;
import br.arthur.utils.HibernateUtil;

public class SaidaModel {
	private Saida entity;
	private static Session session;
	private int qtdeSaida = 0;
	
	public void createSaida(Map<String, Object> data) {
		entity = new Saida();
		
		entity.setEntrada((Entrada) data.get("entrada"));
		entity.setQuantidate((int) data.get("qtde"));
		entity.setDataSaida((Timestamp) data.get("dataSaida"));
		entity.setHeaderSaida((HeaderSaida) data.get("headerSaida"));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		
		close();
	}

	public static Saida findOneWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Saida where " + prop + " = " + val;
		Saida e = (Saida) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Saida) e;
	}
	
	public boolean hasSaida(long theId) {
		List saidas = SaidaModel.findWhere("entrada_fk", String.valueOf(theId));
		
		if(saidas.size() > 0) {
			int totalQtde = 0;
			for(Object o : saidas) {
				Saida s = (Saida) o;
				if (!s.isDisponivel()) {
					int qtde = s.getQuantidate();
					totalQtde += qtde;
				}
			}
			setQtdeSaida(totalQtde);
		} else {
			setQtdeSaida(0);
			return false;
		}
		
		return true;
	}
	
	public void setQtdeSaida(int qtdeSaida) {
		this.qtdeSaida = qtdeSaida;
	}
	
	public int getQtdeSaida() {
		return qtdeSaida;
	}
	
	public static List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Saida where " + prop + " = " + val;
		List saidas = session.createQuery(hql).list();
		
		close();
		
		return saidas;
	}

	

	private static void close() {
		session.close();
	}

	public void deleteById(long entradaId) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
	public List findWhereFks(long hSaidaId, long entradaId) {
		session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM Saida where header_saida_fk = " + String.valueOf(hSaidaId) + " and entrada_fk = " + String.valueOf(entradaId);
		
		List saidas = session.createQuery(hql).list();
		
		close();
		
		return saidas;
	}

	public void deleteByFks(long hSaidaId, long entradaId) {
		List saidas = findWhereFks(hSaidaId, entradaId);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Object o : saidas) {
			Saida s = (Saida) o;
			session.delete(s);
		}
		session.getTransaction().commit();
		
		close();
	}
	
	public void deleteWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		Iterator saidas = session.createQuery("FROM Saida where " + prop + " = " + val).list().iterator();
		
		session.beginTransaction();
		
		while (saidas.hasNext()) {
			Saida entity = (Saida) saidas.next();
			session.delete(entity);
		}		
		
		session.getTransaction().commit();
		
		close();
	}
}
