package dev.paie.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;

@Service
@Transactional
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	public Map<BulletinSalaire, ResultatCalculRemuneration> bulletinCalcul() {

		Map<BulletinSalaire, ResultatCalculRemuneration> result = new HashMap<BulletinSalaire, ResultatCalculRemuneration>();

		List<BulletinSalaire> bulletinsSalaires = bulletinSalaireRepository.findAll();
		for (BulletinSalaire bulletinSalaire : bulletinsSalaires) {
			result.put(bulletinSalaire, calculer(bulletinSalaire));
		}

		return result;

	}

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		String salaireDeBase = paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade()
				.getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase()));
		String salaireBrut = paieUtils
				.formaterBigDecimal(new BigDecimal(salaireDeBase).add(bulletin.getPrimeExceptionnelle()));

		BigDecimal retenueSalarial = new BigDecimal(0);
		BigDecimal cotisationPatronal = new BigDecimal(0);

		for (Cotisation cotisation : bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables()) {
			if (cotisation.getTauxSalarial() != null) {
				retenueSalarial = retenueSalarial
						.add(cotisation.getTauxSalarial().multiply(new BigDecimal(salaireBrut)));
			}
			if (cotisation.getTauxPatronal() != null) {
				cotisationPatronal = cotisationPatronal
						.add(cotisation.getTauxPatronal().multiply(new BigDecimal(salaireBrut)));

			}
		}
		BigDecimal totalImposable = new BigDecimal(0);
		for (Cotisation cotisation : bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables()) {
			if (cotisation.getTauxSalarial() != null) {
				totalImposable = totalImposable.add(cotisation.getTauxSalarial().multiply(new BigDecimal(salaireBrut)));
			}
		}
		String totalRetenueSalarial = paieUtils.formaterBigDecimal(retenueSalarial);
		String totalCotisationsPatronales = paieUtils.formaterBigDecimal(cotisationPatronal);
		String netImposable = paieUtils
				.formaterBigDecimal(new BigDecimal(salaireBrut).subtract(new BigDecimal(totalRetenueSalarial)));
		String netAPayer = paieUtils.formaterBigDecimal(new BigDecimal(netImposable).subtract(totalImposable));

		resultat.setSalaireDeBase(salaireDeBase);
		resultat.setSalaireBrut(salaireBrut);
		resultat.setTotalRetenueSalarial(totalRetenueSalarial);
		resultat.setTotalCotisationsPatronales(totalCotisationsPatronales);
		resultat.setNetImposable(netImposable);
		resultat.setNetAPayer(netAPayer);
		return resultat;
	}

}
