package br.arthur.models;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import br.arthur.entities.ContaReceber;
import br.arthur.entities.HeaderSaida;
import br.arthur.utils.HibernateUtil;

public class ContaReceberModel {
	private static ContaReceber entity;
	private static Session session;
	
	public long criarContaReceber (HashMap<String, Object> data) {
		entity = new ContaReceber();
		
		entity.setHeaderSaida((HeaderSaida) data.get("headerSaida"));
		entity.setDataVencimento((Date) data.get("dataVencimento"));
		entity.setValor((double) data.get("valor"));
		entity.setDesconto((double) data.get("desconto"));
		entity.setDataPagto((Date) data.get("dataPagto"));
		entity.setParcela((byte) data.get("parcela"));
		entity.setPagto((boolean) data.get("pagto"));
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static List findAll() {
		session = HibernateUtil.getSessionFactory().openSession();
		
		List estados = session.createQuery("FROM ContaReceber").list();
		
		close();
		
		return estados;
	}
	
	public static ContaReceber getEntity () {
		return entity;
	}
	
	public static void close() {
		session.close();
	}
}
