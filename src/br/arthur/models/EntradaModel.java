package br.arthur.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Consignatario;
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tipo;
import br.arthur.utils.HibernateUtil;

public class EntradaModel {
	private static Entrada entity;
	private static Session session;
	
	public long criarEntradaComPic(HashMap<String, Object> data) {
		entity = new Entrada();
		
		Date tstamp = new Date();
		long id = Long.parseLong(String.valueOf(tstamp.getTime()).concat((String)data.get("id")));
		
		entity.setId(id);
		entity.setConsignatario((Consignatario) data.get("consig"));
		entity.setDescricao((String) data.get("descricao"));
		entity.setCategoria((Categoria) data.get("categoria"));
		entity.setMarca((Marca) data.get("marca"));
		entity.setCor("");
		entity.setTamanho("");
		entity.setObservacao("");		
		entity.setQuantidate((int) data.get("qtde"));
		entity.setVenda((double) data.get("venda"));
		entity.setMargeVenda((double) data.get("margemv"));
		entity.setMargemComissao((double) data.get("margemc"));
		entity.setCusto((double) data.get("custo"));
		entity.setComissao((double) data.get("comis"));
		entity.setDataEntrada(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entity.setDataInicio((Date) data.get("inicio"));
		entity.setDataVencimento((Date) data.get("venc"));
		entity.setSituacao((Situacao) data.get("situacao"));
		entity.setTipo((Tipo) data.get("tipo"));
		entity.setImagemBlob((Blob) data.get("image"));
		
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
	
	public Entrada getEntity() {
		return entity;
	}

	public static void close() {
		session.close();
	}
	
}
