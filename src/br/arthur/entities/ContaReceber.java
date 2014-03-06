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
	@Column(name="consignatario_id")
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="header_saida_fk", nullable=false)
	private HeaderSaida header_saida;
	
	@Column(name="data_vencimento")
	private Date dataVencimento;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="data_pagto")
	private Date dataPagto;
	
	@Column(name="parcela")
	private byte parcela;
	
	@Column(name="total_parcela")
	private byte total_parcela;	

	@Column(name="pagto")
	private Pagto pagto;
	
	public ContaReceber() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HeaderSaida getHeader_saida() {
		return header_saida;
	}

	public void setHeader_saida(HeaderSaida header_saida) {
		this.header_saida = header_saida;
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

	public byte getTotal_parcela() {
		return total_parcela;
	}

	public void setTotal_parcela(byte total_parcela) {
		this.total_parcela = total_parcela;
	}

	@Enumerated(EnumType.STRING)
	public Pagto getPagto() {
		return pagto;
	}

	public void setPagto(Pagto pagto) {
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
		result = prime * result
				+ ((header_saida == null) ? 0 : header_saida.hashCode());
		result = prime * result + id;
		result = prime * result + ((pagto == null) ? 0 : pagto.hashCode());
		result = prime * result + parcela;
		result = prime * result + total_parcela;
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
		if (header_saida == null) {
			if (other.header_saida != null)
				return false;
		} else if (!header_saida.equals(other.header_saida))
			return false;
		if (id != other.id)
			return false;
		if (pagto != other.pagto)
			return false;
		if (parcela != other.parcela)
			return false;
		if (total_parcela != other.total_parcela)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	

}
