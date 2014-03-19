package br.arthur.models;

import java.sql.Date;
import java.util.HashMap;

import org.hibernate.Session;

import br.arthur.entities.Consignatario;
import br.arthur.entities.ContaPagar;
import br.arthur.entities.ContaReceber;
import br.arthur.utils.HibernateUtil;

public class ContaPagarModel {
	private static ContaPagar entity;
	private static Session session;
	
	public long criarContaPagar (HashMap<String, Object> data) {
		entity = new ContaPagar();
		
		entity.setConsignatario((Consignatario) data.get("consignatario"));
		entity.setContaRecebimento((ContaReceber) data.get("contaReceber"));
		entity.setDataVencimento((Date) data.get("dataVencimento"));
		entity.setPagto((boolean) data.get("pagto"));
		entity.setValor((double) data.get("valorPago"));		
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		
		close();
		
		return entity.getId();
	}
	
	public static void close() {
		session.close();
	}
}
