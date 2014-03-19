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
	
	@Column(name="desconto")
	private double desconto; 
	
	@Column(name="data_pagto")
	private Date dataPagto;
	
	@Column(name="parcela")
	private byte parcela;
	
	@Column(name="total_parcela")
	private byte totalParcela;	

	@Column(name="pagto")
	private boolean pagto;
	
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

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
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

	public byte getTotalParcela() {
		return totalParcela;
	}

	public void setTotalParcela(byte totalParcela) {
		this.totalParcela = totalParcela;
	}

	public boolean isPagto() {
		return pagto;
	}

	public void setPagto(boolean pagto) {
		this.pagto = pagto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		long temp;
		temp = Double.doubleToLongBits(desconto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((headerSaida == null) ? 0 : headerSaida.hashCode());
		result = prime * result + id;
		result = prime * result + (pagto ? 1231 : 1237);
		result = prime * result + parcela;
		result = prime * result + totalParcela;
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
		if (Double.doubleToLongBits(desconto) != Double
				.doubleToLongBits(other.desconto))
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
		if (totalParcela != other.totalParcela)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
