package br.com.links.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity(name = "cargo")
public class Cargo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdcargo")
	private Integer cdCargo;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "descricao", length = 50)
	private String descricao;

	@Column(name = "cbo", nullable = false)
	private Character cbo;

	@Column(name = "codigo_folha", unique = true)
	private Integer codigoFolha;

	@Column(name = "valor_hora")
	private Integer valorHora;

	@Column(name = "hora_semana")
	private Integer horaSemana;

	@Column(name = "ativo", length = 10, nullable = false)
	private String ativo;

	@ManyToOne
	@JoinColumn(name = "cdsubcategoria", referencedColumnName = "cdSubcategoria", nullable = false)
	private SubCategoria cdSubcategoria;


	// atributos não persistidos ou pojo
	@Transient
	private String statusFormatado;

	// Getters e Setters
	
	public Integer getCdCargo() {
		return cdCargo;
	}

	public void setCdCargo(Integer cdCargo) {
		this.cdCargo = cdCargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getCbo() {
		return cbo;
	}

	public void setCbo(Character cbo) {
		this.cbo = cbo;
	}

	public Integer getCodigoFolha() {
		return codigoFolha;
	}

	public void setCodigoFolha(Integer codigoFolha) {
		this.codigoFolha = codigoFolha;
	}

	public Integer getValorHora() {
		return valorHora;
	}

	public void setValorHora(Integer valorHora) {
		this.valorHora = valorHora;
	}

	public Integer getHoraSemana() {
		return horaSemana;
	}

	public void setHoraSemana(Integer horaSemana) {
		this.horaSemana = horaSemana;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return String.format("%s[cdCargo=%d]", getClass().getSimpleName(), getCdCargo());
	}

	public String getStatusFormatado() {
		if (ativo.equals("1")) {
			statusFormatado = "Ativo";
		} else if (ativo.equals("0")) {
			statusFormatado = "inativo";
		}
		return statusFormatado;
	}

	public void setStatusFormatado(String statusFormatado) {
		this.statusFormatado = statusFormatado;
	}

	public SubCategoria getCdSubcategoria() {
		return cdSubcategoria;
	}

	public void setCdSubcategoria(SubCategoria cdSubcategoria) {
		this.cdSubcategoria = cdSubcategoria;
	}

}
