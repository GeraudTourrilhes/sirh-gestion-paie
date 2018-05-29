package dev.paie.web.controller;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView creerEmploye(Model model) {

		ModelAndView mv = new ModelAndView();

		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		// Liaison du modèle et de l'objet.
		model.addAttribute("remunerationEmploye", remunerationEmploye);
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprises", entrepriseRepository.findAll());
		mv.addObject("profilsRemuneration", profilRemunerationsRepository.findAll());
		mv.addObject("grades", gradeRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Transactional
	public String submitFormCreerEmploye(
			@ModelAttribute("remunerationEmploye") RemunerationEmploye remunerationEmploye) {
		remunerationEmploye.setDateCreation(ZonedDateTime.now());
		remunerationEmployeRepository.save(remunerationEmploye);

		return "redirect:/mvc/employes/lister";

	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findAll());
		return mv;
	}

}
