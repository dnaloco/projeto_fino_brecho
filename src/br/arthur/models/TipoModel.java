package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class TipoModel {

	public static List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List tipos = session.createQuery("FROM Tipo ORDER BY name").list();
		
		return tipos;
	}
	
	
	public static Tipo findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Tipo where " + prop + " = " + val;
		Tipo e = (Tipo) session.createQuery(hql).uniqueResult();
		
		return (Tipo) e;
	}
}
