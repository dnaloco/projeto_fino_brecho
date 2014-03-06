package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Situacao;
import br.arthur.utils.HibernateUtil;

public class SituacaoModel {
	private static Session session;	

	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List situacoes = session.createQuery("FROM Situacao ORDER BY name").list();
		close();
		return situacoes;
	}
	
	
	public static Situacao findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Situacao where " + prop + " = " + val;
		System.out.println(hql);
		Situacao e = (Situacao) session.createQuery(hql).uniqueResult();
		
		return (Situacao) e;
	}
	
	public static void close() {
		session.close();
	}
}
