package br.arthur.entities;

import java.sql.Date;

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
@Table(name="contas_receber")
public class ContaReceber {
	@Id
	@Column(name="conta_receber_id")
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="header_saida_fk", nullable=false)
	private HeaderSaida headerSaida;
	
	@Column(name="data_vencimento")
	private Date dataVencimento;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="data_pagto")
	private Date dataPagto;
	
	@Column(name="parcela")
	private byte parcela;

	@Column(name="pagto")
	private boolean pagto;
	
	@ManyToOne
	@JoinColumn(name="formaPagto", nullable=false)
	private FormaPagto formaPagto;
	
	public ContaReceber() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HeaderSaida getHeaderSaida() {
		return headerSaida;
	}

	public void setHeaderSaida(HeaderSaida headerSaida) {
		this.headerSaida = headerSaida;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}

	public byte getParcela() {
		return parcela;
	}

	public void setParcela(byte parcela) {
		this.parcela = parcela;
	}

	public boolean isPagto() {
		return pagto;
	}

	public void setPagto(boolean pagto) {
		this.pagto = pagto;
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
		result = prime * result
				+ ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result
				+ ((formaPagto == null) ? 0 : formaPagto.hashCode());
		result = prime * result
				+ ((headerSaida == null) ? 0 : headerSaida.hashCode());
		result = prime * result + id;
		result = prime * result + (pagto ? 1231 : 1237);
		result = prime * result + parcela;
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ContaReceber other = (ContaReceber) obj;
		if (dataPagto == null) {
			if (other.dataPagto != null)
				return false;
		} else if (!dataPagto.equals(other.dataPagto))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (formaPagto == null) {
			if (other.formaPagto != null)
				return false;
		} else if (!formaPagto.equals(other.formaPagto))
			return false;
		if (headerSaida == null) {
			if (other.headerSaida != null)
				return false;
		} else if (!headerSaida.equals(other.headerSaida))
			return false;
		if (id != other.id)
			return false;
		if (pagto != other.pagto)
			return false;
		if (parcela != other.parcela)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
