package dev.paie.entite;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RemunerationEmploye {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String matricule;
	@ManyToOne
	@JoinColumn(name = "id_entreprise", nullable = false)
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name = "id_profilRemuneration", nullable = false)
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	@JoinColumn(name = "id_grade", nullable = false)
	private Grade grade;

	private ZonedDateTime dateCreation;

	@Override
	public String toString() {
		return "RemunerationEmploye [matricule=" + matricule + ", entreprise=" + entreprise + ", profilRemuneration="
				+ profilRemuneration + ", grade=" + grade + "]";
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}

	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(ZonedDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

}
