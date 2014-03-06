package br.arthur.entities;

public enum Especie {

	D("Dinheiro"), C("Crédito"), B("Débito");

	private String descricao;

	private Especie(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
