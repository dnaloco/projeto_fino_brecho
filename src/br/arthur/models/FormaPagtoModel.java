package br.arthur.models;

import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.FormaPagto;
import br.arthur.utils.HibernateUtil;

public class FormaPagtoModel {
	private static Session session;

	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();

		List formasPgto = session.createQuery("FROM  FormaPagto ORDER BY name")
				.list();
		close();
		return formasPgto;
	}

	public static void close() {
		session.close();
	}

	public static FormaPagto findOneWhere(String prop, String val) {

		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "FROM FormaPagto where " + prop + " = " + val;
		FormaPagto fp = (FormaPagto) session.createQuery(hql).uniqueResult();

		close();
		return (FormaPagto) fp;

	}
}
