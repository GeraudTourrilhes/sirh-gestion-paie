package dev.paie.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.UtilisateurRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	private ClassPathXmlApplicationContext context;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private ProfilRemunerationRepository profilRemunerationsRepository;

	@Autowired
	private CotisationRepository cotisationRepository;

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	private List<Entreprise> entreprises;

	private List<Grade> grades;

	private List<ProfilRemuneration> profilRemunerations;

	private List<Cotisation> cotisations;

	@Transactional
	@Override
	public void initialiser() {

		context = new ClassPathXmlApplicationContext("init-config.xml");

		grades = new ArrayList<Grade>(context.getBeansOfType(Grade.class).values());

		profilRemunerations = new ArrayList<ProfilRemuneration>(
				context.getBeansOfType(ProfilRemuneration.class).values());

		cotisations = new ArrayList<Cotisation>(context.getBeansOfType(Cotisation.class).values());

		entreprises = new ArrayList<Entreprise>(context.getBeansOfType(Entreprise.class).values());

		context.close();

		utilisateurRepository
				.save(new Utilisateur("admin", this.passwordEncoder.encode("admin"), true, ROLES.ROLE_ADMINISTRATEUR));
		utilisateurRepository
				.save(new Utilisateur("user", this.passwordEncoder.encode("user"), true, ROLES.ROLE_UTILISATEUR));

		// Stream.of(Entreprise.class, Cotisation.class, Grade.class,
		// ProfilRemuneration.class)
		// .flatMap(c ->context.getBeansOfType(c).values().stream())
		// .forEach(em::persist);

		for (Entreprise entreprise : entreprises) {
			entrepriseRepository.save(entreprise);
		}

		for (Cotisation cotisation : cotisations) {
			cotisationRepository.save(cotisation);
		}

		for (ProfilRemuneration profilRemuneration : profilRemunerations) {
			profilRemunerationsRepository.save(profilRemuneration);
		}

		for (Grade grade : grades) {
			gradeRepository.save(grade);
		}

		int anneeCourante = Calendar.getInstance().get(Calendar.YEAR);
		/*
		 * IntStream.rangeClosed(0, 12) .mapToObj(i -> { Month mois =
		 * Month.of(i); LocalDate dateDebut = LocalDate.of(anneeCourante, mois,
		 * 01); LocalDate dateFin = LocalDate.of(anneeCourante, mois,
		 * dateDebut.lengthOfMonth()); Periode p = new Periode(dateDebut,
		 * dateFin); return p; }) .forEach(em::persist);
		 */

		for (int i = 1; i < 13; i++) {
			Month mois = Month.of(i);
			LocalDate dateDebut = LocalDate.of(anneeCourante, mois, 01);
			LocalDate dateFin = LocalDate.of(anneeCourante, mois, dateDebut.lengthOfMonth());
			periodeRepository.save(new Periode(dateDebut, dateFin));
		}

	}

}
