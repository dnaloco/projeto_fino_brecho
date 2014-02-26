package br.arthur.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	@GeneratedValue
	@Column(name="entrada_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="pedido_fk", nullable=false)
	private Pedido pedido;

	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_fk", nullable=false)
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name="marca_fk", nullable=false)
	private Marca marca;
	@Column(length=30)
	private String cor;
	@Column(length=30)
	private String tamanho;

	@Column(name="custo")
	private double custo;
	@Column(name="quantidate")
	private int quantidate;
	@Column(name="margem_venda")
	private String margemVenda;
	@Column(name="margem_comissao")
	private String margemComissao;
	
	@Column(name="revenda")
	private double revenda;
	@Column(name="comissao")
	private double comissao;
	
	
	@Column(name="total_custo_qtde")
	private double total;	
	@Column(name="data_entrada")
	private Timestamp dataEntrada;
	@Column(name="data_validade")
	private Timestamp validade;
	
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



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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



	public String getCor() {
		return cor;
	}



	public void setCor(String cor) {
		this.cor = cor;
	}



	public String getTamanho() {
		return tamanho;
	}



	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}



	public double getCusto() {
		return custo;
	}



	public void setCusto(double custo) {
		this.custo = custo;
	}



	public int getQuantidate() {
		return quantidate;
	}



	public void setQuantidate(int quantidate) {
		this.quantidate = quantidate;
	}



	public String getMargemVenda() {
		return margemVenda;
	}



	public void setMargemVenda(String margemVenda) {
		this.margemVenda = margemVenda;
	}



	public String getMargemComissao() {
		return margemComissao;
	}



	public void setMargemComissao(String margemComissao) {
		this.margemComissao = margemComissao;
	}



	public double getRevenda() {
		return revenda;
	}



	public void setRevenda(double revenda) {
		this.revenda = revenda;
	}



	public double getComissao() {
		return comissao;
	}



	public void setComissao(double comissao) {
		this.comissao = comissao;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Timestamp getDataEntrada() {
		return dataEntrada;
	}



	public void setDataEntrada(Timestamp dataEntrada) {
		this.dataEntrada = dataEntrada;
	}



	public Timestamp getValidade() {
		return validade;
	}



	public void setValidade(Timestamp validade) {
		this.validade = validade;
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



	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	
	public List<Imagem> getImagens() {
		return imagens;
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
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagens == null) ? 0 : imagens.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result
				+ ((margemComissao == null) ? 0 : margemComissao.hashCode());
		result = prime * result
				+ ((margemVenda == null) ? 0 : margemVenda.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + quantidate;
		temp = Double.doubleToLongBits(revenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((validade == null) ? 0 : validade.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (margemComissao == null) {
			if (other.margemComissao != null)
				return false;
		} else if (!margemComissao.equals(other.margemComissao))
			return false;
		if (margemVenda == null) {
			if (other.margemVenda != null)
				return false;
		} else if (!margemVenda.equals(other.margemVenda))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (quantidate != other.quantidate)
			return false;
		if (Double.doubleToLongBits(revenda) != Double
				.doubleToLongBits(other.revenda))
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
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		return true;
	}

	public void addImage(Imagem ie) {
		// TODO Auto-generated method stub
		this.imagens.add(ie);
	}
	
	
}
