package br.arthur.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class originalImagem {
	@Id
	@GeneratedValue
	@Column(name="imagem_id")
	private int id;
	@Column(name="imagem_blob")
	private Blob imagemBlob;

	@ManyToOne(fetch = FetchType.EAGER)
	private Entrada entrada;
}
