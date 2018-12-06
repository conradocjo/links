package br.com.links.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class SubCategoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdsubcategoria")
	private Integer cdSubcategoria;

	@Column(name = "descricao", length = 100)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "cdcategoria",referencedColumnName = "cdcategoria", nullable = false)
	private Categoria cdCategoria;

	// Getters e Setters

	public Integer getCdSubcategoria() {
		return cdSubcategoria;
	}

	public void setCdSubcategoria(Integer cdSubcategoria) {
		this.cdSubcategoria = cdSubcategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCdCategoria() {
		return cdCategoria;
	}

	public void setCdCategoria(Categoria cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	@Override
	public String toString() {
		return String.format("%s[cdSubcategoria=%d]", getClass().getSimpleName(), getCdSubcategoria());
	}

}
