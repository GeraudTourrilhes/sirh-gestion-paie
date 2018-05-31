package dev.paie.web.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private ProfilRemunerationRepository profilRemunerationsRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye(Model model) {

		ModelAndView mv = new ModelAndView();

		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		// Liaison du modèle et de l'objet.
		model.addAttribute("remunerationEmploye", remunerationEmploye);
		mv.setViewName("employes/creerEmploye");
		RestTemplate rt = new RestTemplate();
		Collegue[] collegues = rt.getForObject("http://collegues-api.cleverapps.io/collegues/", Collegue[].class);
		List<String> matricules = new ArrayList<String>();
		for (Collegue collegue : collegues) {
			matricules.add(collegue.getMatricule());
		}
		mv.addObject("matricules", matricules);
		mv.addObject("entreprises", entrepriseRepository.findAll());
		mv.addObject("profilsRemuneration", profilRemunerationsRepository.findAll());
		mv.addObject("grades", gradeRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Transactional
	@Secured("ROLE_ADMINISTRATEUR")
	public String submitFormCreerEmploye(
			@ModelAttribute("remunerationEmploye") RemunerationEmploye remunerationEmploye) {
		remunerationEmploye.setDateCreation(ZonedDateTime.now());
		remunerationEmployeRepository.save(remunerationEmploye);

		return "redirect:/mvc/employes/lister";

	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findAll());
		return mv;
	}

}
