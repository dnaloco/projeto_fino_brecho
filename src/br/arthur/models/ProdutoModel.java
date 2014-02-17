package br.arthur.models;

import java.util.Map;

import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Marca;
import br.arthur.entities.Produto;
import br.arthur.utils.HibernateUtil;

public class ProdutoModel {
	Session session;
	
	public long createProduto(Map<String, Object> data) {
		Produto p = new Produto();
		
		p.setTitulo((String) data.get("titulo"));
		p.setCategoria((Categoria) data.get("categoria"));
		p.setMarca((Marca) data.get("marca"));
		p.setCor((String) data.get("cor"));
		p.setTamanho((String) data.get("tamanho"));
		p.setSaldo((double) 0);
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		
		return p.getId();
	}
}
