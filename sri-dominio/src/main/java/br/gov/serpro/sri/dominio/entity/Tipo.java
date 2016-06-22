package br.gov.serpro.sri.dominio.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Tipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8378598480126864008L;
	
	@Id
	@SequenceGenerator( name = "TIPO_ID", sequenceName = "TIPO_SEQ")
	@GeneratedValue(strategy = SEQUENCE, generator="TIPO_ID")
	@Column(name="tipo_id")
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
