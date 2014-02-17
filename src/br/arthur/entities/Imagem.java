package br.arthur.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="imagens")
public class Imagem {
	@Id
	@GeneratedValue
	@Column(name="imagem_id")
	private long id;
	@Column(name="imagem_blob")
	private Blob imagemBlob;
	
	public Imagem() {
		
	}
	
	public Imagem(Blob imagemBlog) {
		this.imagemBlob = imagemBlob;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Imagem other = (Imagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Blob getImagemBlob() {
		return imagemBlob;
	}
	public void setImagemBlob(Blob imagemBlob) {
		this.imagemBlob = imagemBlob;
	}
}
