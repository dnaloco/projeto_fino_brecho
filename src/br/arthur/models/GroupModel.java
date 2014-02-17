package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.utils.HibernateUtil;

public class GroupModel {
	Session session;
	public List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List groups = session.createQuery("FROM Group").list();
		
		return groups;
	}
	
	public Group findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Group where " + prop + " = " + val;
		Group g = (Group) session.createQuery(hql).uniqueResult();
		
		return (Group) g;
	}
}
