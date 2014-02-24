package br.arthur.models;

import java.sql.Blob;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.Imagem;
import br.arthur.utils.HibernateUtil;

public class ImagemModel {
	Imagem entity;
	Session session;
	
	public int createImagem(Blob imgBlog) {
		entity = new Imagem();
		entity.setImagemBlob(imgBlog);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		session.close();
		
		return entity.getId();
	}

	public Imagem findOneWhere(String prop, String val) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Imagem where " + prop + " = " + val;
		Imagem i = (Imagem) session.createQuery(hql).uniqueResult();
		
		return (Imagem) i;
	}
}
