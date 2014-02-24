package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Entrada;
import br.arthur.entities.Imagem;
import br.arthur.entities.Marca;
import br.arthur.entities.Pedido;
import br.arthur.entities.Situacao;
import br.arthur.utils.HibernateUtil;

public class EntradaModel {
	private Entrada entity;
	private Session session;

	public void saveEntrada(int entradaId, HashMap<String, Object> data) {
		
	}
	
	public void addImagem(int entradaId, HashSet<Imagem> img) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		entity.setImagens(img);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		session.close();
	}

	public int createEntrada(HashMap<String, Object> data) {
		entity = new Entrada();
		
		setProduto(data);
		entity.setDataEntrada(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		session.close();
		
		return entity.getId();
	}
	
	
	public static Entrada findOneWhere(String prop, String val){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where " + prop + " = " + val;
		Entrada e = (Entrada) session.createQuery(hql).uniqueResult();
		
		session.close();
		
		return (Entrada) e;
	}
	
	public void saveProduto(int entradaId, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		setProduto(data);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	public List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM Entrada ORDER BY produto").list();
		
		session.close();
		
		return estados;
	}

	public List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where " + prop + " = " + val;
		List entradas = session.createQuery(hql).list();
		
		session.close();
		
		return entradas;
	}
	
	private void setProduto(HashMap<String, Object> data) {
		entity.setPedido((Pedido) data.get("pedido"));
		entity.setDescricao((String) data.get("descricao"));
		entity.setCategoria((Categoria) data.get("categoria"));
		entity.setMarca((Marca) data.get("marca"));
		entity.setTamanho((String) data.get("tamanho"));
		entity.setCor((String) data.get("cor"));
		entity.setQuantidate((Integer) data.get("qtde"));
		entity.setSituacao((Situacao) data.get("situacao"));
	}

	public void deleteById(int entradaId) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
	}

	
}
