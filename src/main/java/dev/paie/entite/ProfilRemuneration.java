package dev.paie.entite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class ProfilRemuneration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profilRemuneration_cotisationNonImposable", joinColumns = @JoinColumn(name = "ProfilRemuneration_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "cotisationsNonImposables_id", referencedColumnName = "ID"))
	private List<Cotisation> cotisationsNonImposables;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "profilRemuneration_cotisation", joinColumns = @JoinColumn(name = "ProfilRemuneration_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "cotisationsImposables_id", referencedColumnName = "ID"))
	private List<Cotisation> cotisationsImposables;
	@Transient
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

	@Override
	public String toString() {
		return code;
	}

}
