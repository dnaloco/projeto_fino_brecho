package br.arthur.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="generator_to_entrada_id")
public class CatLastId {
	@Id
	@Column(name="cat")
	private int cat;

	@Column(name="id")
	private long lastId;
	
	public CatLastId() {
		
	}
	
	public CatLastId(int cat, int lastId) {
		this.cat = cat;
		this.lastId = lastId;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public long getLastId() {
		return lastId;
	}

	public void setLastId(long lastId) {
		this.lastId = lastId;
	}
	
	
}
