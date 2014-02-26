package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class TipoModel {
	private static Session session;
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List tipos = session.createQuery("FROM Tipo ORDER BY name").list();
		
		close();
		
		return tipos;
	}
	
	
	public static Tipo findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Tipo where " + prop + " = " + val;
		Tipo e = (Tipo) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Tipo) e;
	}
	
	public static void close() {
		session.close();
	}
}
