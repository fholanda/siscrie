package br.gov.serpro.sri.dominio.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class UF implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378598480126864008L;
	
	@Id
	@SequenceGenerator( name = "UF_ID", sequenceName = "UF_SEQ")
	@GeneratedValue(strategy = SEQUENCE, generator="UF_ID")
	@Column(name="uf_id")
	private Long id;
	
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
