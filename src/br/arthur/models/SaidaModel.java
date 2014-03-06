package br.arthur.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Entrada;
import br.arthur.entities.Saida;
import br.arthur.entities.HeaderSaida;
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
	
	public boolean hasSaida(int entradaId) {
		List saidas = SaidaModel.findWhere("entrada_fk", String.valueOf(entradaId));
		
		if(saidas.size() > 0) {
			int totalQtde = 0;
			for(Object o : saidas) {
				int qtde = ((Saida) o).getQuantidate();
				totalQtde += qtde;
			}
			qtdeSaida = totalQtde;
		} else {
			return false;
		}
		
		return true;
	}
	
	public static List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Saida where " + prop + " = " + val;
		List saidas = session.createQuery(hql).list();
		
		close();
		
		return saidas;
	}

	public int getQtdeSaida() {
		return qtdeSaida;
	}

	private static void close() {
		session.close();
	}
}
