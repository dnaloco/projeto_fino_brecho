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
@Table(name="contas_pagar")
public class ContaPagar {
	@Id
	@GeneratedValue
	@Column(name="contas_pagar_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="consignatario_fk", nullable=false)
	private Consignatario consignatario;
	
	@ManyToOne
	@JoinColumn(name="headerVenda", nullable=false)
	private HeaderSaida headerVenda;
	
	@Column(name="data_vencimento")
	private Date dataVencimento;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="data_pagto")
	private Date dataPagto;
	
	@Column(name="pagto")
	private boolean pagto;
	
	public ContaPagar() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Consignatario getConsignatario() {
		return consignatario;
	}

	public void setConsignatario(Consignatario consignatario) {
		this.consignatario = consignatario;
	}

	public HeaderSaida getHeaderVenda() {
		return headerVenda;
	}

	public void setHeaderVenda(HeaderSaida headerVenda) {
		this.headerVenda = headerVenda;
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
				+ ((consignatario == null) ? 0 : consignatario.hashCode());
		result = prime * result
				+ ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result
				+ ((headerVenda == null) ? 0 : headerVenda.hashCode());
		result = prime * result + id;
		result = prime * result + (pagto ? 1231 : 1237);
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
		ContaPagar other = (ContaPagar) obj;
		if (consignatario == null) {
			if (other.consignatario != null)
				return false;
		} else if (!consignatario.equals(other.consignatario))
			return false;
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
		if (headerVenda == null) {
			if (other.headerVenda != null)
				return false;
		} else if (!headerVenda.equals(other.headerVenda))
			return false;
		if (id != other.id)
			return false;
		if (pagto != other.pagto)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
