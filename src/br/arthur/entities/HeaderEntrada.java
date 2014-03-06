package br.arthur.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="header_entrada")
public class HeaderEntrada {
	@Id
	@GeneratedValue
	@Column(name="header_entrada_id")
	private int id;
	
	@Column(name="data_entrada")
	private Timestamp dataEntrada;
	
	@ManyToOne
	@JoinColumn(name="consignatario_fk", nullable=false)
	private Consignatario consignatario;

	public HeaderEntrada() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Timestamp dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Consignatario getConsignatario() {
		return consignatario;
	}

	public void setConsignatario(Consignatario consignatario) {
		this.consignatario = consignatario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consignatario == null) ? 0 : consignatario.hashCode());
		result = prime * result
				+ ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
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
		HeaderEntrada other = (HeaderEntrada) obj;
		if (consignatario == null) {
			if (other.consignatario != null)
				return false;
		} else if (!consignatario.equals(other.consignatario))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
