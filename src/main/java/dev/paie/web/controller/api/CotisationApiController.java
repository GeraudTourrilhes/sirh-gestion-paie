package dev.paie.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Message;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping("/api/cotisations")
public class CotisationApiController {

	@Autowired
	CotisationRepository cotisationRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Cotisation> listerCotisation() {
		return cotisationRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{code}")
	public Object visualiserCotisation(@PathVariable String code) {
		Object result = null;
		if (cotisationRepository.findByCode(code) == null) {
			result = new Message("Code de cotisations non trouvé");
		} else {
			result = cotisationRepository.findByCode(code);
		}

		return result;

	}

	@RequestMapping(method = RequestMethod.POST)
	public void ajouterCotisation(@RequestBody Cotisation cotisation) {
		cotisationRepository.save(cotisation);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{code}")
	public void modifierCotisation(@PathVariable String code, @RequestBody Cotisation cotisation) {
		cotisation.setId(cotisationRepository.findByCode(code).getId());
		cotisationRepository.save(cotisation);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{code}")
	public void modifierCotisation(@PathVariable String code) {
		cotisationRepository.delete(cotisationRepository.findByCode(code));
		;
	}

}
