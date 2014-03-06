package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Marca;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class TipoModel {
	private static Session session;
	private Tipo entity;
	
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


	public int createTipo(String tipo) {
		Tipo t = new Tipo(tipo);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		close();
		
		return t.getId();
	}


	public void deleteById(int theId) {
		entity = findOneWhere("id", String.valueOf(theId));
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
		
		close();
		
	}
}
