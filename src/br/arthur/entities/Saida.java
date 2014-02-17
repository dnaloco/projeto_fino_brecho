package br.arthur.entities;

import java.sql.Date;

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
	private long id;
	
	@ManyToOne
	@JoinColumn(name="cliente_fk", nullable=false)
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="estado_fk", nullable=false)
	private Entrada entrada;
	
	@Column(name="quantidate")
	private int quantidate;
	@Column(name="preco")
	private double preco;
	@Column(name="data_saida")
	private Date dataSaida;
	@ManyToOne
	@JoinColumn(name="forma_pagto_fk", nullable=false)
	private FormaPagto formaPagto;
	@Column(name="pagto")
	private boolean pagto;
	
	public Saida() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result
				+ ((formaPagto == null) ? 0 : formaPagto.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (pagto ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (formaPagto == null) {
			if (other.formaPagto != null)
				return false;
		} else if (!formaPagto.equals(other.formaPagto))
			return false;
		if (id != other.id)
			return false;
		if (pagto != other.pagto)
			return false;
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.preco))
			return false;
		if (quantidate != other.quantidate)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public FormaPagto getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(FormaPagto formaPagto) {
		this.formaPagto = formaPagto;
	}

	public boolean isPagto() {
		return pagto;
	}

	public void setPagto(boolean pagto) {
		this.pagto = pagto;
	}

}
