package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Cor;
import br.arthur.utils.HibernateUtil;

public class CorModel {
	private static Session session;
	private Cor entity;
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List cores = session.createQuery("FROM Cor ORDER BY name").list();
		close();
		
		return cores;
	}
	
	public static Cor findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Cor where " + prop + " = " + val;
		Cor c = (Cor) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Cor) c;
	}
	
	public static void close() {
		session.close();
	}

	public int createCor(String cor) {
		Cor c = new Cor(cor);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		close();
		
		return c.getId();
	}
	
	public void saveCor(int id, String name) {
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
