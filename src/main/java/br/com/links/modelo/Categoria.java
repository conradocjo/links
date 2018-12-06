package br.com.links.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdcategoria")
	private Integer cdCategoria;

	@Column(name = "descricao", length = 100)
	private String descricao;

	// Getters e Setters

	public Integer getCdCategoria() {
		return cdCategoria;
	}

	public void setCdCategoria(Integer cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return String.format("%s[cdCategoria=%d]", getClass().getSimpleName(), getCdCategoria());
	}
}
