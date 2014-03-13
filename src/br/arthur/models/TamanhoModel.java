package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Tamanho;
import br.arthur.utils.HibernateUtil;

public class TamanhoModel {
	private static Session session;
	private Tamanho entity;
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List tamanhos = session.createQuery("FROM Tamanho ORDER BY name").list();
		close();
		
		return tamanhos;
	}
	
	public static Tamanho findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Tamanho where " + prop + " = " + val;
		Tamanho t = (Tamanho) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Tamanho) t;
	}
	
	public static void close() {
		session.close();
	}

	public int createTamanho(String marca) {
		Tamanho c = new Tamanho(marca);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		close();
		
		return c.getId();
	}
	
	public void saveTamanho(int id, String name) {
		entity = findOneWhere("id", String.valueOf(id));
		
		entity.setName(name);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
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
