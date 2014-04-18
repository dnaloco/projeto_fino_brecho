package br.arthur.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Consignatario;
import br.arthur.entities.Cor;
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tamanho;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class EntradaModel {
	private static Entrada entity;
	private static Session session;
	
	public long criarEntradaComPic(HashMap<String, Object> data) {
		entity = new Entrada();

		comporDataEntidate(data);
		
		Date tstamp = new Date();
		long id = Long.parseLong(String.valueOf(tstamp.getTime()).concat((String)data.get("id")));
		
		entity.setId(id);
		entity.setDataEntrada(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.save(entity);
		
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}

	private void comporDataEntidate(HashMap<String, Object> data) {
		entity.setConsignatario((Consignatario) data.get("consig"));
		entity.setDescricao((String) data.get("descricao"));
		entity.setCategoria((Categoria) data.get("categoria"));
		entity.setMarca((Marca) data.get("marca"));
		entity.setCor((Cor) data.get("cor"));
		entity.setTamanho((Tamanho) data.get("tamanho"));
		entity.setQuantidate((int) data.get("qtde"));
		entity.setVenda((double) data.get("venda"));
		entity.setMargeVenda((double) data.get("margemv"));
		entity.setMargemComissao((double) data.get("margemc"));
		entity.setCusto((double) data.get("custo"));
		entity.setComissao((double) data.get("comis"));
		entity.setDataInicio((Date) data.get("inicio"));
		entity.setDataVencimento((Date) data.get("venc"));
		entity.setSituacao((Situacao) data.get("situacao"));
		entity.setTipo((Tipo) data.get("tipo"));
		entity.setObservacao((String) data.get("observ"));
		if (data.containsKey("image")) {
			entity.setImagemBlob((Blob) data.get("image"));
		}
/*		if (data.containsKey("qtdeDevolvido")) {
			entity.setDevolvido((int) data.get("qtdeDevolvido"));
		}
		if (data.containsKey("qtdeNEncontrado")) {
			entity.setnEncontrado((int) data.get("qtdeNEncontrado"));
		}*/
		entity.setLocalizacao((String) data.get("local"));
	}
	
	public static Entrada findOneWhere(String prop, String val){
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where " + prop + " = " + val;
		Entrada e = (Entrada) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Entrada) e;
	}
	
	public Entrada getEntity() {
		return entity;
	}

	public static void close() {
		session.close();
	}

	public void deleteById(long entradaId) {
		entity = findOneWhere("id", String.valueOf(entradaId));
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.delete(entity);
		
		session.getTransaction().commit();
		
		close();
	}

	public Entrada salvarEntrada(long id, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(id));
		long oldId = entity.getId();
		
		if (String.valueOf(entity.getId()).length() >= 13 ) {
			int cat = ((Categoria) data.get("categoria")).getId();
			long clid = CatLastIdModel.getLastId(cat);
			entity.setId((cat * 1000000) + clid);
			CatLastIdModel.updateLastId(cat);
		} else {
			if (!entity.getCategoria().getName().contains(((Categoria) data.get("categoria")).getName())) {
				int cat = ((Categoria) data.get("categoria")).getId();
				long clid = CatLastIdModel.getLastId(cat);
				entity.setId((cat * 1000000) + clid);
				CatLastIdModel.updateLastId(cat);
			}
		}
		
		long newId = entity.getId();
		
		comporDataEntidate(data);

		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		if (oldId != newId) {
			System.out.println("ID NOVO");
			session.save(entity);
			String hql =  "FROM Entrada where id = " + oldId;
			Entrada e = (Entrada) session.createQuery(hql).uniqueResult();
			session.delete(e);
		} else {
			System.out.println("ID VELHO");
			session.update(entity);
		}

		session.getTransaction().commit();
		
		close();
		
		return entity;
	}
	
	private Entrada findOneWhereDisponivel(String id) {
		session = HibernateUtil.getSessionFactory().openSession();
		
		String hql =  "FROM Entrada where id  = " + id + " and situacao_fk = 2";
		Entrada e = (Entrada) session.createQuery(hql).uniqueResult();
		
		close();
		
		return (Entrada) e;
	}

	public boolean hasEntrada(long prodId) {
		Entrada entrada =  findOneWhereDisponivel(String.valueOf(prodId));
		long entradaId = 0;

		
		try {

			entradaId = entrada.getId();
			
		} catch (NullPointerException ex) {
			System.out.println("WHY???????????????????????");
			ex.printStackTrace();
		}
		
		if (entradaId ==  0) {
			return false;
		}
		entity = entrada;
		return true;
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM Entrada ORDER BY descricao").list();
		
		close();
		
		return estados;
	}

	public void changePicture(Long id, Blob imgBlob) {
		entity = findOneWhere("id", String.valueOf(id));
		
		entity.setImagemBlob(imgBlob);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		session.update(entity);
		
		session.getTransaction().commit();
		
		close();
		
	}
}
