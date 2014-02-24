package br.arthur.models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;

import br.arthur.entities.Marca;
import br.arthur.utils.HibernateUtil;

public class MarcaModel {
	static Session session;

	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List marcas = session.createQuery("FROM Marca ORDER BY name").list();
		return marcas;
	}
	
	public static Marca findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Marca where " + prop + " = " + val;
		Marca m = (Marca) session.createQuery(hql).uniqueResult();
		
		return (Marca) m;
	}
}
