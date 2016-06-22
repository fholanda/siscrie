package br.gov.serpro.sri.dominio.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class DARF implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378598480126864008L;
	
	@Id
	@SequenceGenerator( name = "DARF_ID", sequenceName = "DARF_SEQ")
	@GeneratedValue(strategy = SEQUENCE, generator = "DARF_ID" )
	private Long id;
	
	@NotEmpty
	private String nrDARF;
	
	@NotNull
	private Long cnpj;
	
	@NotEmpty
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "retencao_id")
	private Retencao retencao;
	
	@ManyToOne
	@JoinColumn(name = "uf_id")
	private UF uf;
	
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name = "risco_id")
	private Risco risco;
	
	private BigDecimal valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtVencimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtApuracao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNrDARF() {
		return nrDARF;
	}
	public void setNrDARF(String nrDARF) {
		this.nrDARF = nrDARF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public Date getDtApuracao() {
		return dtApuracao;
	}
	public void setDtApuracao(Date dtApuracao) {
		this.dtApuracao = dtApuracao;
	}
	public Retencao getRetencao() {
		return retencao;
	}
	public void setRetencao(Retencao retencao) {
		this.retencao = retencao;
	}
	public Long getCnpj() {
		return cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Risco getRisco() {
		return risco;
	}
	public void setRisco(Risco risco) {
		this.risco = risco;
	}
	

}
