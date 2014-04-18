package br.arthur.entities;

import java.io.Serializable;
import java.sql.Date;

import br.arthur.temp.tests.DateChooserPanel;

public class FormaDataPK  implements Serializable {
	protected Integer formaPagto;
	protected String dataCaixa;
	
	public FormaDataPK() {
		
	}
	
	public FormaDataPK(Integer formaPagto, String dataCaixa) {
		this.formaPagto = formaPagto;
		this.dataCaixa = dataCaixa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCaixa == null) ? 0 : dataCaixa.hashCode());
		result = prime * result
				+ ((formaPagto == null) ? 0 : formaPagto.hashCode());
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
		FormaDataPK other = (FormaDataPK) obj;
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
		return true;
	}

	
}
