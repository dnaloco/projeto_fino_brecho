package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Estado;
import br.arthur.utils.HibernateUtil;

public class EstadoModel {
	private static Session session;
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM Estado ORDER BY name").list();
		
		session.close();
		
		return estados;
	}
	
	
	public static Estado findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Estado where " + prop + " = " + val;
		Estado e = (Estado) session.createQuery(hql).uniqueResult();
		
		return (Estado) e;
	}
	
	public static void close() {
		session.close();
	}
}
