package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Group;
import br.arthur.entities.Status;
import br.arthur.entities.User;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.PasswordUtil;

public class UserModel {
	public int createUser(Map<String, Object> data) {
		User u = new User();
		
		u.setUser((String)data.get("user"));
		u.setName((String)data.get("name"));
		u.setEmail((String)data.get("email"));
		
		String pass = (String) data.get("pass");
		String salt = PasswordUtil.generateSalt();
		String hash = PasswordUtil.generateHash(pass, salt);
		
		u.setSalt(salt);
		u.setHash(hash);
		
		u.setStatus((Status) data.get("status"));
		u.setGroup((Group) data.get("group"));
		
		u.setMobile((String) data.get("mobile"));
		u.setPhone((String) data.get("phone"));
		
		u.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(u);
		
		session.getTransaction().commit();
		
		return u.getId();
	}
	
	public List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		return session.createQuery("FROM User").list();
	}
	
	public User findOneWhere(String prop, String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM User where " + prop + " = " + val;
		User u = (User) session.createQuery(hql).uniqueResult();
		
		return (User) u;
	}
}
