package br.arthur.models;

import java.sql.Blob;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.Imagem;
import br.arthur.utils.HibernateUtil;

public class ImagemModel {
	private Imagem entity;
	private static Session session;
	
	public int createImagem(Blob imgBlog) {
		entity = new Imagem();
		entity.setImagemBlob(imgBlog);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}

	public Imagem findOneWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Imagem where " + prop + " = " + val;
		Imagem i = (Imagem) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Imagem) i;
	}
	
	public static void close() {
		session.close();
	}
}
