package br.arthur.models;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.ContaPagar;
import br.arthur.entities.ContaReceber;
import br.arthur.entities.HeaderSaida;
import br.arthur.utils.HibernateUtil;

public class ContaPagarModel {
	private static ContaPagar entity;
	private static Session session;
	
	public long criarContaPagar (HashMap<String, Object> data) {
		entity = new ContaPagar();
		
		entity.setConsignatario((Consignatario) data.get("consignatario"));
		entity.setHeaderVenda((HeaderSaida) data.get("headerSaida"));
		entity.setDataVencimento((Date) data.get("dataVencimento"));
		entity.setPagto((boolean) data.get("pagto"));
		entity.setValor((double) data.get("valorPago"));		
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static void close() {
		session.close();
	}

	public void salvarDuplicataPagar(long idDuplicata,
			HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(idDuplicata));

		entity.setDataPagto(new Date(Calendar.getInstance().getTimeInMillis()));
		entity.setPagto((boolean) data.get("pagto"));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		
		close();
		
	}
	
	public static ContaPagar findOneWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "FROM ContaPagar where " + prop + " = " + val;
		ContaPagar cp = (ContaPagar) session.createQuery(hql).uniqueResult();

		close();

		return (ContaPagar) cp;
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM ContaPagar").list();
		
		close();
		
		return estados;
	}

	public void deleteWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		Iterator contasPagar = session.createQuery("FROM ContaPagar where " + prop + " = " + val).list().iterator();
		
		session.beginTransaction();
		
		while (contasPagar.hasNext()) {
			ContaPagar entity = (ContaPagar) contasPagar.next();
			session.delete(entity);
		}		
		
		session.getTransaction().commit();
		
		close();
	}
}
