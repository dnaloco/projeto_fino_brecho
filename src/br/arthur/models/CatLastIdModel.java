package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;

import org.hibernate.Session;

import br.arthur.entities.CatLastId;
import br.arthur.entities.Consignatario;
import br.arthur.utils.HibernateUtil;

public class CatLastIdModel {
	private static Session session;

	public static void updateLastId(long cat) {
		CatLastId entity = findOneWhere(cat);
		
		entity.setLastId(entity.getLastId() + 1);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
	public static CatLastId findOneWhere(long cat){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM CatLastId where cat = " + String.valueOf(cat);
		CatLastId c = (CatLastId) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (CatLastId) c;
	}
	
	public static long getLastId(long cat) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM CatLastId where cat = " + String.valueOf(cat);
		CatLastId c = (CatLastId) session.createQuery(hql).uniqueResult();
		
		close();
		
		return c.getLastId();
	}
	
	public static void close() {
		session.close();
	}
}
