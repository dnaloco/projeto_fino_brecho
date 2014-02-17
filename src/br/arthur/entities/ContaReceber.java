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
@Table(name="contas_receber")
public class ContaReceber {
	@Id
	@Column(name="consignatario_id")
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="saida_fk", nullable=false)
	private Saida saida;
	
	@Column(name="parcela")
	private byte parcela;
	@Column(name="total_parcela")
	private byte total_parcela;
	@Column(name="data_vencimento")
	private Date dataVencimento;
	@Column(name="valor")
	private double valor;
	@Column(name="data_pagto")
	private Date dataPagto;
	@Column(name="pagto")
	private boolean pagto;
	
	public ContaReceber() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + id;
		result = prime * result + (pagto ? 1231 : 1237);
		result = prime * result + parcela;
		result = prime * result + ((saida == null) ? 0 : saida.hashCode());
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
		if (id != other.id)
			return false;
		if (pagto != other.pagto)
			return false;
		if (parcela != other.parcela)
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		if (total_parcela != other.total_parcela)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
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

}
