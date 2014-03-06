package br.arthur.entities;

public enum Pagto {
	P("Pago"), N("N�o pago"), D("Desdobrado");

	private String descricao;

	private Pagto(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}

}
