package br.arthur.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="entradas")
public class originalEntrada {
	@Id
	@Column(name="entrada_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="header_entrada_fk", nullable=false)
	private HeaderEntrada headerEntrada;

	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_fk", nullable=false)
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name="marca_fk", nullable=false)
	private Marca marca;
	
	@Column(length=30)
	private String tamanho;
	
	@Column(length=30)
	private String cor;


	@Column(name="venda")
	private double venda;
	@Column(name="quantidate")
	private int quantidate;
	@Column(name="margem_custo")
	private double margemCusto;
	@Column(name="margem_comissao")
	private double margemComissao;
	
	@Column(name="custo")
	private double custo;
	@Column(name="comissao")
	private double comissao;
	
	@Column(name="data_entrada")
	private Timestamp dataEntrada;
	
	@Column(name="data_inicio")
	private Date dataInicio;

	@Column(name="data_vencimento")
	private Date dataVencimento;
	
	@ManyToOne
	@JoinColumn(name="situacao_fk", nullable=false)
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name="tipo_fk")
	private Tipo tipo;
	
	@Column(name="observacao", columnDefinition="TEXT")
	private String observacao;
	
	@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="entrada_id")
	private List<Imagem> imagens = new ArrayList<Imagem>();

	public originalEntrada() {
		
	}

	public void addImage(Imagem ie) {
		// TODO Auto-generated method stub
		this.imagens.add(ie);
	}

	public void updateImage(Imagem ie) {
		
	}
	
	public void removeImage(Imagem ie) {
		
	}
}
