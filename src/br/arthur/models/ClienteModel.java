package br.arthur.models;

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
		// TODO Auto-generated method stub
		return null;
	}
	
	public int createCliente(Map<String, Object> data) {
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

	private void close() {
		session.close();
	}

	private void setData(Map<String, Object> data) {
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
		entity.setNome((String) data.get("nome"));
	}

	public void saveCliente(int theId, HashMap<String, Object> data) {
		// TODO Auto-generated method stub
		
	}

}
