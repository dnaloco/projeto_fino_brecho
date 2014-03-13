package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.CatLastId;
import br.arthur.entities.Categoria;
import br.arthur.utils.HibernateUtil;

public class CategoriaModel {
	static Session session;
	private Categoria entity;
	public int createCategoria(String categoria, boolean isVestimenta) {
		Categoria c = new Categoria(categoria, isVestimenta);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(c);
		CatLastId clid = new CatLastId(c.getId(), 1);
		session.save(clid);
		session.getTransaction().commit();
		close();
		
		return c.getId();
	}
	
	public void saveCategoria(int id, String name, boolean isVest) {
		entity = findOneWhere("id", String.valueOf(id));
		
		entity.setName(name);
		entity.setVestimenta(isVest);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
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

	public void deleteById(int theId) {
		entity = findOneWhere("id", String.valueOf(theId));
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
		
		close();
	}
}
