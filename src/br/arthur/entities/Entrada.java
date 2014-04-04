package br.arthur.entities;

import java.sql.Blob;
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
	private long id;
	
	@ManyToOne
	@JoinColumn(name="consignatario_fk", nullable=false)
	private Consignatario consignatario;

	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_fk")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name="marca_fk")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name="tamanho_fk")
	private Tamanho tamanho;
	
	@ManyToOne
	@JoinColumn(name="cor_fk")
	private Cor cor;

	@Column(name="venda")
	private double venda;
	@Column(name="quantidate")
	private int quantidate;
	@Column(name="margem_venda")
	private double margeVenda;
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
	@JoinColumn(name="situacao_fk")
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name="tipo_fk")
	private Tipo tipo;
	
	@Column(name="observacao", columnDefinition="TEXT")
	private String observacao;
	
	@Column(name="imagem_blob")
	private Blob imagemBlob;
	
	@Column(name="localizacao")
	private String localizacao;


	@Column(name="nEncontrado")
	private int nEncontrado;
	
	@Column(name="devolvido")
	private int devolvido;

	public Entrada() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Consignatario getConsignatario() {
		return consignatario;
	}

	public void setConsignatario(Consignatario consignatario) {
		this.consignatario = consignatario;
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

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
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

	public double getMargeVenda() {
		return margeVenda;
	}

	public void setMargeVenda(double margeVenda) {
		this.margeVenda = margeVenda;
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

	public Blob getImagemBlob() {
		return imagemBlob;
	}

	public void setImagemBlob(Blob imagemBlob) {
		this.imagemBlob = imagemBlob;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getnEncontrado() {
		return nEncontrado;
	}

	public void setnEncontrado(int nEncontrado) {
		this.nEncontrado = nEncontrado;
	}

	public int getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(int devolvido) {
		this.devolvido = devolvido;
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
		result = prime * result
				+ ((consignatario == null) ? 0 : consignatario.hashCode());
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
		result = prime * result + devolvido;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		temp = Double.doubleToLongBits(margeVenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(margemComissao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nEncontrado;
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
		if (consignatario == null) {
			if (other.consignatario != null)
				return false;
		} else if (!consignatario.equals(other.consignatario))
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
		if (devolvido != other.devolvido)
			return false;
		if (id != other.id)
			return false;
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (Double.doubleToLongBits(margeVenda) != Double
				.doubleToLongBits(other.margeVenda))
			return false;
		if (Double.doubleToLongBits(margemComissao) != Double
				.doubleToLongBits(other.margemComissao))
			return false;
		if (nEncontrado != other.nEncontrado)
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
	
	

}
