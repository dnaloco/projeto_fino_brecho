package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.HeaderEntrada;
import br.arthur.utils.HibernateUtil;

public class HeaderEntradaModel {
	private HeaderEntrada entity;
	private static Session session;

	public int createPedido(Consignatario consignatario) {
		entity = new HeaderEntrada();
		
		
		entity.setConsignatario(consignatario);
		
		Date myDate = new Date();
		Timestamp hoje = new Timestamp(myDate.getTime());
		entity.setDataEntrada(hoje);		
		
		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		List pedidos = session.createQuery("FROM HeaderEntrada").list();
		
		close();
		
		return pedidos;
	}
	
	public static HeaderEntrada findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM HeaderEntrada where " + prop + " = " + val;
		HeaderEntrada p = (HeaderEntrada) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (HeaderEntrada) p;
	}
	

	public static List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM HeaderEntrada where " + prop + " = " + val;
		List pedidos = session.createQuery(hql).list();
		
		close();
		
		return pedidos;
	}
	
	public static void close() {
		session.close();
	}
}
