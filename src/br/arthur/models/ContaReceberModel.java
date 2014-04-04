package br.arthur.models;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.ContaReceber;
import br.arthur.entities.HeaderSaida;
import br.arthur.utils.HibernateUtil;

public class ContaReceberModel {
	private static ContaReceber entity;
	private static Session session;
	
	public long criarContaReceber (HashMap<String, Object> data) {
		entity = new ContaReceber();
		
		entity.setHeaderSaida((HeaderSaida) data.get("headerSaida"));
		entity.setDataVencimento((Date) data.get("dataVencimento"));
		entity.setValor((double) data.get("valor"));
		entity.setParcela((byte) data.get("parcela"));
		entity.setPagto((boolean) data.get("pagto"));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static void salvarDuplicataReceber(long id, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(id));
			
		entity.setValor((double) data.get("valor"));
		entity.setDataPagto(new Date(Calendar.getInstance().getTimeInMillis()));
		entity.setPagto((boolean) data.get("pagto"));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		
		close();
	}
	
	public static ContaReceber findOneWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "FROM ContaReceber where " + prop + " = " + val;
		ContaReceber cr = (ContaReceber) session.createQuery(hql).uniqueResult();

		close();

		return (ContaReceber) cr;
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM ContaReceber").list();
		
		close();
		
		return estados;
	}
	
	public static ContaReceber getEntity () {
		return entity;
	}
	
	public static void close() {
		session.close();
	}

	public void deleteWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		Iterator contasReceber = session.createQuery("FROM ContaReceber where " + prop + " = " + val).list().iterator();
		
		session.beginTransaction();
		
		while (contasReceber.hasNext()) {
			ContaReceber entity = (ContaReceber) contasReceber.next();
			session.delete(entity);
		}		
		
		session.getTransaction().commit();
		
		close();
	}
}
