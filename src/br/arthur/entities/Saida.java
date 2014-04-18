package br.arthur.entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="saidas")
public class Saida {
	@Id
	@GeneratedValue
	@Column(name="saida_id")
	private int id;

	@ManyToOne
	@JoinColumn(name="entrada_fk", nullable=false)
	private Entrada entrada;
	
	@Column(name="quantidate")
	private int quantidate;

	@Column(name="data_saida")
	private Timestamp dataSaida;
	
	@ManyToOne
	@JoinColumn(name="header_saida_fk", nullable=false)
	private HeaderSaida headerSaida;
	
	@Column(name="isDisponivel")
	private boolean disponivel;
	
	@Column(name="isDevolvido")
	private boolean devolvido;
	
	@Column(name="isNencontrado")
	private boolean nencontrado;
	
	public Saida() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public int getQuantidate() {
		return quantidate;
	}

	public void setQuantidate(int quantidate) {
		this.quantidate = quantidate;
	}

	public Timestamp getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Timestamp dataSaida) {
		this.dataSaida = dataSaida;
	}

	public HeaderSaida getHeaderSaida() {
		return headerSaida;
	}

	public void setHeaderSaida(HeaderSaida headerSaida) {
		this.headerSaida = headerSaida;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	public boolean isNencontrado() {
		return nencontrado;
	}

	public void setNencontrado(boolean nencontrado) {
		this.nencontrado = nencontrado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + (devolvido ? 1231 : 1237);
		result = prime * result + (disponivel ? 1231 : 1237);
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result
				+ ((headerSaida == null) ? 0 : headerSaida.hashCode());
		result = prime * result + id;
		result = prime * result + (nencontrado ? 1231 : 1237);
		result = prime * result + quantidate;
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
		Saida other = (Saida) obj;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (devolvido != other.devolvido)
			return false;
		if (disponivel != other.disponivel)
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (headerSaida == null) {
			if (other.headerSaida != null)
				return false;
		} else if (!headerSaida.equals(other.headerSaida))
			return false;
		if (id != other.id)
			return false;
		if (nencontrado != other.nencontrado)
			return false;
		if (quantidate != other.quantidate)
			return false;
		return true;
	}
	
}
