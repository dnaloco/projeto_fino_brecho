package br.arthur.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Entrada;
import br.arthur.entities.Imagem;
import br.arthur.entities.Marca;
import br.arthur.entities.Pedido;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class EntradaModel {
	private Entrada entity;
	private static Session session;

	public void saveImagens(int entradaId, List<Imagem> imgs) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		entity.setImagens(imgs);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}

	public int createEntrada(HashMap<String, Object> data) {
		entity = new Entrada();
		
		Date myDate = new Date();
		Timestamp hoje = new Timestamp(myDate.getTime());
		data.put("entrada", hoje);
		
		entity.setPedido((Pedido) data.get("pedido"));
		entity.setDataInicio((Date) data.get("inicio"));
		entity.setDataVencimento((Date) data.get("vencimento"));
		entity.setSituacao((Situacao) data.get("situacao"));
		entity.setVenda((Double) data.get("venda"));
		entity.setMargemCusto((Double) data.get("margem"));
		entity.setMargemComissao((Double) data.get("comissao"));
		entity.setTipo((Tipo) data.get("tipo"));
		
		setProduto(data);		
		
		entity.setDataEntrada(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	
	public static Entrada findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where " + prop + " = " + val;
		Entrada e = (Entrada) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Entrada) e;
	}
	
	public void saveProduto(int entradaId, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		setProduto(data);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
	public void saveAvaliacao(int entradaId, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		setAvaliacao(data);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
	public List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM Entrada ORDER BY produto").list();
		
		close();
		
		return estados;
	}

	public List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where " + prop + " = " + val;
		List entradas = session.createQuery(hql).list();
		
		close();
		
		return entradas;
	}
	
	private void setProduto(HashMap<String, Object> data) {
		entity.setDescricao((String) data.get("descricao"));
		entity.setCategoria((Categoria) data.get("categoria"));
		entity.setMarca((Marca) data.get("marca"));
		entity.setTamanho((String) data.get("tamanho"));
		entity.setCor((String) data.get("cor"));
		entity.setQuantidate((Integer) data.get("qtde"));
	}
	
	private void setAvaliacao(HashMap<String, Object> data) {
		entity.setVenda((Double) data.get("venda"));
		entity.setMargemCusto((Double) data.get("margemCusto"));
		entity.setMargemComissao((Double) data.get("margemComissao"));
		entity.setDataInicio((Date) data.get("inicio"));
		entity.setSituacao((Situacao) data.get("situacao"));
		entity.setDataVencimento((Date) data.get("vencimento"));
		entity.setTipo((Tipo) data.get("tipo"));
		entity.setObservacao((String) data.get("observacoes"));
		entity.setCusto((Double) data.get("custo"));
		entity.setComissao((Double) data.get("comissao"));
	}

	public void deleteById(int entradaId) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
	public static void close() {
		session.close();
	}

	public void saveOneImage(int entradaProdutoId, Imagem ie) {
		entity = findOneWhere("id", String.valueOf(entradaProdutoId));
		
		// entity.setImagens(imgs);
		entity.addImage(ie);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
	}
	
}
