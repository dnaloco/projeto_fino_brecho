package br.arthur.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="entradas")
public class Entrada {
	@Id
	@GeneratedValue
	@Column(name="entrada_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="consignatario_fk", nullable=false)
	private Consignatario consignatario;

	@Column(name="titulo")
	private String titulo;
	
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
	
	@Column(name="quantidate")
	private int quantidate;
	@Column(name="custo")
	private double custo;
	@Column(name="total")
	private double total;	
	@Column(name="data_entrada")
	private Date dataEntrada;
	@Column(name="validade")
	private Date validade;
	
	@ManyToOne
	@JoinColumn(name="situacao_fk", nullable=false)
	private Situacao situacao;
	

	@ManyToOne
	@JoinColumn(name="condicao_fk", nullable=false)
	private Condicao condicao;
	
	@Column(name="observacao", columnDefinition="TEXT")
	private String observacao;
	
	@Column(name="margem")
	private String margem;

	@ManyToMany(  
	        targetEntity=br.arthur.entities.Imagem.class,  
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}  
	    )  
	    @JoinTable(  
	        name="imagem_produto",  
	        joinColumns=@JoinColumn(name="PRODUTO_ID"),  
	        inverseJoinColumns=@JoinColumn(name="IMAGEM_ID")  
	    )
	private Set<Imagem> imagens = new HashSet<Imagem>();
	
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public int getQuantidate() {
		return quantidate;
	}

	public void setQuantidate(int quantidate) {
		this.quantidate = quantidate;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Condicao getCondicao() {
		return condicao;
	}

	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getMargem() {
		return margem;
	}

	public void setMargem(String margem) {
		this.margem = margem;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Imagem> imagens) {
		this.imagens = imagens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((condicao == null) ? 0 : condicao.hashCode());
		result = prime * result
				+ ((consignatario == null) ? 0 : consignatario.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(custo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imagens == null) ? 0 : imagens.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((margem == null) ? 0 : margem.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + quantidate;
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		if (condicao == null) {
			if (other.condicao != null)
				return false;
		} else if (!condicao.equals(other.condicao))
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
		if (margem == null) {
			if (other.margem != null)
				return false;
		} else if (!margem.equals(other.margem))
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
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
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
}
