package br.arthur.entities;

import java.sql.Blob;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name = "categorias")  
public class Categoria implements Comparator<Categoria>{
	@Id
	@Column(name="categoria_id")  
	@GeneratedValue
	private int id;

	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@Column(name="icon")
	private Blob icon;
	
	public Categoria() {
		
	}
	
	public Categoria(String name) {
		this.name = name;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getIcon() {
		return icon;
	}

	public void setIcon(Blob icon) {
		this.icon = icon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compare(Categoria cat1, Categoria cat2) {
		return cat1.getName().compareTo(cat2.getName());
	}
}
