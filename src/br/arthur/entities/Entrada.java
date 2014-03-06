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
public class Entrada {
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

	public Entrada() {
		
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public HeaderEntrada getHeaderEntrada() {
		return headerEntrada;
	}



	public void setHeaderEntrada(HeaderEntrada headerEntrada) {
		this.headerEntrada = headerEntrada;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public Marca getMarca() {
		return marca;
	}



	public void setMarca(Marca marca) {
		this.marca = marca;
	}



	public String getTamanho() {
		return tamanho;
	}



	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}



	public String getCor() {
		return cor;
	}



	public void setCor(String cor) {
		this.cor = cor;
	}



	public double getVenda() {
		return venda;
	}



	public void setVenda(double venda) {
		this.venda = venda;
	}



	public int getQuantidate() {
		return quantidate;
	}



	public void setQuantidate(int quantidate) {
		this.quantidate = quantidate;
	}



	public double getMargemCusto() {
		return margemCusto;
	}



	public void setMargemCusto(double margemCusto) {
		this.margemCusto = margemCusto;
	}



	public double getMargemComissao() {
		return margemComissao;
	}



	public void setMargemComissao(double margemComissao) {
		this.margemComissao = margemComissao;
	}



	public double getCusto() {
		return custo;
	}



	public void setCusto(double custo) {
		this.custo = custo;
	}



	public double getComissao() {
		return comissao;
	}



	public void setComissao(double comissao) {
		this.comissao = comissao;
	}



	public Timestamp getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(Timestamp dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public Date getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}



	public Date getDataVencimento() {
		return dataVencimento;
	}



	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}



	public Situacao getSituacao() {
		return situacao;
	}



	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}



	public Tipo getTipo() {
		return tipo;
	}



	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}



	public String getObservacao() {
		return observacao;
	}



	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



	public List<Imagem> getImagens() {
		return imagens;
	}



	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		long temp;
		temp = Double.doubleToLongBits(comissao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		temp = Double.doubleToLongBits(custo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((headerEntrada == null) ? 0 : headerEntrada.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagens == null) ? 0 : imagens.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		temp = Double.doubleToLongBits(margemComissao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(margemCusto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + quantidate;
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		temp = Double.doubleToLongBits(venda);
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
		Entrada other = (Entrada) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (Double.doubleToLongBits(comissao) != Double
				.doubleToLongBits(other.comissao))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (Double.doubleToLongBits(custo) != Double
				.doubleToLongBits(other.custo))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (headerEntrada == null) {
			if (other.headerEntrada != null)
				return false;
		} else if (!headerEntrada.equals(other.headerEntrada))
			return false;
		if (id != other.id)
			return false;
		if (imagens == null) {
			if (other.imagens != null)
				return false;
		} else if (!imagens.equals(other.imagens))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (Double.doubleToLongBits(margemComissao) != Double
				.doubleToLongBits(other.margemComissao))
			return false;
		if (Double.doubleToLongBits(margemCusto) != Double
				.doubleToLongBits(other.margemCusto))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (quantidate != other.quantidate)
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (Double.doubleToLongBits(venda) != Double
				.doubleToLongBits(other.venda))
			return false;
		return true;
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
