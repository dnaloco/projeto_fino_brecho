package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.utils.HibernateUtil;

public class GroupModel {
	private static Session session;
	public List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List groups = session.createQuery("FROM Group").list();
		
		close();
		
		return groups;
	}
	
	public Group findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Group where " + prop + " = " + val;
		Group g = (Group) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Group) g;
	}
	
	public static void close() {
		session.close();
	}
}
