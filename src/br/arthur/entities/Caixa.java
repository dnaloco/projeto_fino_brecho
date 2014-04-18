package br.arthur.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="caixa")
@IdClass(FormaDataPK.class)
public class Caixa {
	@Id
	private Integer formaPagto;
	@Id
	private String dataCaixa;
	
	@ManyToOne
	@JoinColumn(name="responsavel", nullable=false)
	private User responsavel;
	
	@Column(name="conferido")
	private double conferido;
	
	@Column(name="observacao")
	private String observacao;
	
	public Caixa() {}

	public Integer getFormaPagto() {
		return formaPagto;
	}

	public void setFormaPagto(Integer formaPagto) {
		this.formaPagto = formaPagto;
	}

	public String getDataCaixa() {
		return dataCaixa;
	}

	public void setDataCaixa(String dataCaixa) {
		this.dataCaixa = dataCaixa;
	}

	public User getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(User responsavel) {
		this.responsavel = responsavel;
	}

	public double getConferido() {
		return conferido;
	}

	public void setConferido(double conferido) {
		this.conferido = conferido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(conferido);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((dataCaixa == null) ? 0 : dataCaixa.hashCode());
		result = prime * result
				+ ((formaPagto == null) ? 0 : formaPagto.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
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
		Caixa other = (Caixa) obj;
		if (Double.doubleToLongBits(conferido) != Double
				.doubleToLongBits(other.conferido))
			return false;
		if (dataCaixa == null) {
			if (other.dataCaixa != null)
				return false;
		} else if (!dataCaixa.equals(other.dataCaixa))
			return false;
		if (formaPagto == null) {
			if (other.formaPagto != null)
				return false;
		} else if (!formaPagto.equals(other.formaPagto))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}

	
}
