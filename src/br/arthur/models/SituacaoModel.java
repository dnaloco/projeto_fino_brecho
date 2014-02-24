package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Situacao;
import br.arthur.utils.HibernateUtil;

public class SituacaoModel {

	public static List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List situacoes = session.createQuery("FROM Situacao ORDER BY name").list();
		
		return situacoes;
	}
	
	
	public static Situacao findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Situacao where " + prop + " = " + val;
		System.out.println(hql);
		Situacao e = (Situacao) session.createQuery(hql).uniqueResult();
		
		return (Situacao) e;
	}
}
