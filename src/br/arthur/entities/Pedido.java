package br.arthur.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {
	@Id
	@GeneratedValue
	@Column(name="pedido_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="consignatario_fk", nullable=false)
	private Consignatario consignatario;

	public Pedido() {
		
	}
	
	public Pedido(Consignatario consignatario) {
		this.consignatario = consignatario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Consignatario getConsignaratio() {
		return consignatario;
	}

	public void setConsignaratio(Consignatario consignaratio) {
		this.consignatario = consignaratio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consignatario == null) ? 0 : consignatario.hashCode());
		result = prime * result + id;
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
		Pedido other = (Pedido) obj;
		if (consignatario == null) {
			if (other.consignatario != null)
				return false;
		} else if (!consignatario.equals(other.consignatario))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
