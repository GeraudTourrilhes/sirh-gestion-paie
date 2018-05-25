package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cotisation")
public class Cotisation {

	@Id
	private Integer id;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "libelle", nullable = false)
	private String libelle;

	@Column(name = "tauxSalarial", nullable = false)
	private BigDecimal tauxSalarial;

	@Column(name = "tauxPatronal", nullable = false)
	private BigDecimal tauxPatronal;

	public Cotisation(Integer id, String code, String libelle, BigDecimal tauxSalarial, BigDecimal tauxPatronal) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.tauxSalarial = tauxSalarial;
		this.tauxPatronal = tauxPatronal;
	}

	public Cotisation() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public BigDecimal getTauxSalarial() {
		return tauxSalarial;
	}

	public void setTauxSalarial(BigDecimal tauxSalarial) {
		this.tauxSalarial = tauxSalarial;
	}

	public BigDecimal getTauxPatronal() {
		return tauxPatronal;
	}

	public void setTauxPatronal(BigDecimal tauxPatronal) {
		this.tauxPatronal = tauxPatronal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
