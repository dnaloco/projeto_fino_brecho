package br.arthur.entities;

public enum Especie {

	D("Dinheiro"), C("Cr�dito"), B("D�bito");

	private String descricao;

	private Especie(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
