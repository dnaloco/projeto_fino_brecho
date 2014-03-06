package br.arthur.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Marca;
import br.arthur.utils.HibernateUtil;

public class MarcaModel {
	private static Session session;
	private Marca entity;
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List marcas = session.createQuery("FROM Marca ORDER BY name").list();
		close();
		
		return marcas;
	}
	
	public static Marca findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Marca where " + prop + " = " + val;
		Marca m = (Marca) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Marca) m;
	}
	
	public static void close() {
		session.close();
	}

	public int createMarca(String marca) {
		Marca c = new Marca(marca);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		close();
		
		return c.getId();
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
