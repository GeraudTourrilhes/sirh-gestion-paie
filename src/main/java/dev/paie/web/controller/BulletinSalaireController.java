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

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	private CalculerRemunerationService remunerationService;

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin(Model model) {

		BulletinSalaire bulletinSalaire = new BulletinSalaire();

		model.addAttribute("bulletinSalaire", bulletinSalaire);

		ModelAndView mv = new ModelAndView();

		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodeRepository.findAll());
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findDistinctMatriculeBy());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Transactional
	public String submitFormCreerBulletin(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		bulletinSalaire.setDateCreation(ZonedDateTime.now());
		bulletinSalaireRepository.save(bulletinSalaire);

		return "redirect:/mvc/bulletins/lister";

	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("remunerationService", remunerationService);
		mv.addObject("bulletinsSalaire", bulletinSalaireRepository.findAll());
		return mv;
	}

}
