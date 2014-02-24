package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Estado;
import br.arthur.utils.HibernateUtil;

public class EstadoModel {
	public static List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM Estado ORDER BY name").list();
		
		return estados;
	}
	
	
	public static Estado findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Estado where " + prop + " = " + val;
		Estado e = (Estado) session.createQuery(hql).uniqueResult();
		
		return (Estado) e;
	}
}
