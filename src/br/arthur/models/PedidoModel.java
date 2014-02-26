package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.Pedido;
import br.arthur.utils.HibernateUtil;

public class PedidoModel {
	private Pedido entity;
	private static Session session;

	public int createPedido(Consignatario consignatario) {
		entity = new Pedido();
		
		entity.setConsignaratio(consignatario);
		
		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List pedidos = session.createQuery("FROM Pedido").list();
		
		close();
		
		return pedidos;
	}
	
	public static Pedido findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Pedido where " + prop + " = " + val;
		Pedido p = (Pedido) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Pedido) p;
	}
	

	public static List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Pedido where " + prop + " = " + val;
		List pedidos = session.createQuery(hql).list();
		
		close();
		
		return pedidos;
	}
	
	public static void close() {
		session.close();
	}
}
