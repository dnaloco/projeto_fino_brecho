package br.arthur.models;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Cliente;
import br.arthur.entities.FormaPagto;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.User;
import br.arthur.utils.HibernateUtil;

public class HeaderSaidaModel {
	private HeaderSaida entity;
	private static Session session;

	public long createVenda(Map<String, Object> data) {
		entity = new HeaderSaida();

		entity.setVendedor((User) data.get("vendedor"));
		entity.setCliente((Cliente) data.get("cliente"));

		entity.setDataVenda(new Timestamp(System.currentTimeMillis()));

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(entity);

		session.getTransaction().commit();

		close();

		return entity.getId();
	}

	public void updateVenda(long id, HashMap<String, Object> data) {
		entity = findOneWhere("id", String.valueOf(id));

		entity.setTotalVenda((double) data.get("totalVenda"));
		entity.setFormaPagto((FormaPagto) data.get("formaPagto"));
		entity.setTotalParcela((byte) data.get("totalParcela"));

		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();

		close();
	}

	public static void close() {
		session.close();
	}

	public HeaderSaida findOneWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "FROM HeaderSaida where " + prop + " = " + val;
		HeaderSaida v = (HeaderSaida) session.createQuery(hql).uniqueResult();

		close();

		return (HeaderSaida) v;
	}

	public static List findWhere(String prop, String val) {
		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "FROM HeaderSaida where " + prop + " = " + val;
		List pedidos = session.createQuery(hql).list();

		close();

		return pedidos;
	}

	public void deleteById(long hSaidaId) {
		entity = findOneWhere("id", String.valueOf(hSaidaId));

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(entity);

		session.getTransaction().commit();

		close();
	}
}
