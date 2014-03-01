package br.arthur.models;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Group;
import br.arthur.utils.HibernateUtil;

public class CategoriaModel {
	static Session session;
	
	public int createCategoria(String categoria) {
		Categoria c = new Categoria(categoria);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(c);
		session.getTransaction().commit();
		close();
		
		return c.getId();
	}

	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List categorias = session.createQuery("FROM Categoria ORDER BY name").list();
		
		close();
		
		return categorias;
	}
	
	public static Categoria findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Categoria where " + prop + " = " + val;
		Categoria c = (Categoria) session.createQuery(hql).uniqueResult();
		
		close();
		return (Categoria) c;
	}
	
	public static void close() {
		session.close();
	}
}
