package br.arthur.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="headers_saida")
public class HeaderSaida {
	@Id
	@Column(name="header_saida_id")
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="vendedor_fk", nullable=false)
	private User vendedor;
	
	@ManyToOne
	@JoinColumn(name="cliente_fk", nullable=false)
	private Cliente cliente;

	@Column(name="data_venda", nullable=false)
	private Timestamp dataVenda;
	
	@Column(name="total_venda")
	private double totalVenda;
	
	@ManyToOne
	@JoinColumn(name="forma_pagto_fk")
	private FormaPagto formaPagto;
	
	public HeaderSaida() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getVendedor() {
		return vendedor;
	}

	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Timestamp getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Timestamp dataVenda) {
		this.dataVenda = dataVenda;
	}

	public double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}

	public FormaPagto getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(FormaPagto formaPagto) {
		this.formaPagto = formaPagto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result
				+ ((formaPagto == null) ? 0 : formaPagto.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(totalVenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((vendedor == null) ? 0 : vendedor.hashCode());
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
		HeaderSaida other = (HeaderSaida) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (formaPagto == null) {
			if (other.formaPagto != null)
				return false;
		} else if (!formaPagto.equals(other.formaPagto))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(totalVenda) != Double
				.doubleToLongBits(other.totalVenda))
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}
	
	
}
