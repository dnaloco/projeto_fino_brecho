package br.arthur.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Cliente;
import br.arthur.utils.HibernateUtil;

public class ClienteModel {
	private Cliente entity;
	private static Session session;
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List clientes = session.createQuery("FROM Cliente").list();
		
		close();
		
		return clientes;
	}
	
	public long createCliente(Map<String, Object> data) {
		entity = new Cliente();
		
		setData(data);
		
		entity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();;
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}

	private static void close() {
		session.close();
	}

	private void setData(Map<String, Object> data) {
		entity.setNome((String) data.get("nome"));
		entity.setTelefone((String) data.get("telefone"));
		entity.setCelular((String) data.get("celular"));
		entity.setEmail((String) data.get("email"));
		entity.setSite((String) data.get("site"));
		entity.setAniversario((Date) data.get("aniver"));
		entity.setObservacao((String) data.get("observ"));
	}
	
	public static Cliente findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Cliente where " + prop + " = " + val;
		Cliente c = (Cliente) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Cliente) c;
	}
	

	public void saveCliente(long theId, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(theId));
		
		setData(data);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}

	public Cliente getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
