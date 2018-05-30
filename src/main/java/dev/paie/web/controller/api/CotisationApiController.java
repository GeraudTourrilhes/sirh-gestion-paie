package dev.paie.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
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
	public ResponseEntity<Object> visualiserCotisation(@PathVariable String code) {
		ResponseEntity<Object> result = null;
		Cotisation cotisation = cotisationRepository.findByCode(code);
		if (cotisation == null) {
			Map<String, String> message = new HashMap<>();
			message.put("message", "Code de cotisations non trouvé");
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(cotisation);
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
