package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.entities.Status;
import br.arthur.utils.HibernateUtil;

public class StatusModel {
	Session session;

	public List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List status = session.createQuery("FROM Status").list();
		
		return status;
	}
	
	public Status findOneWhere(String prop, String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Status where " + prop + " = " + val;
		Status s = (Status) session.createQuery(hql).uniqueResult();
		
		return (Status) s;
	}
	
}
