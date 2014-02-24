package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import br.arthur.entities.Consignatario;
import br.arthur.entities.Estado;
import br.arthur.utils.HibernateUtil;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ConsignatarioModel {
	private Consignatario entity;
	
	public int createConsignatario(Map<String, Object> data) {
		entity = new Consignatario();
		
		setData(data);
		entity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		return entity.getId();
	}

	private void setData(Map<String, Object> data) {
		entity.setNome((String)data.get("nome"));
		entity.setTelefone((String)data.get("telefone"));
		entity.setCelular((String)data.get("celular"));
		entity.setEmail((String) data.get("email"));
		entity.setSite((String) data.get("site"));
		entity.setLogradouro((String) data.get("logra"));
		entity.setNumero((String) data.get("num"));
		entity.setComplemento((String) data.get("complem"));
		entity.setBairro((String) data.get("bairro"));
		entity.setCep((String) data.get("cep"));
		entity.setCidade((String) data.get("cidade"));
		entity.setEstado((Estado) data.get("estado"));
		entity.setCpf((String) data.get("cpf"));
		entity.setRg((String) data.get("rg"));
	}
	
	public static List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List categorias = session.createQuery("FROM Consignatario").list();
		return categorias;
	}
	
	
	public static Consignatario findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Consignatario where " + prop + " = " + val;
		Consignatario c = (Consignatario) session.createQuery(hql).uniqueResult();
		
		return (Consignatario) c;
	}
	
	public void deleteById(int id) throws MySQLIntegrityConstraintViolationException, ConstraintViolationException {
		entity = findOneWhere("id", String.valueOf(id));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
	}

	public void saveConsignatario(int theId, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(theId));
		setData(data);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
	}
}
