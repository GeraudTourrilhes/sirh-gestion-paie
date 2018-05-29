package dev.paie.web.controller;

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
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin(Model model) {

		ModelAndView mv = new ModelAndView();

		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		// Liaison du modèle et de l'objet.
		model.addAttribute("bulletinSalaire", bulletinSalaire);
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodeRepository.findAll());
		mv.addObject("remunerationEmployes", remunerationEmployeRepository.findDistinctMatriculeBy());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Transactional
	public String submitFormCreerBulletin(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		bulletinSalaireRepository.save(bulletinSalaire);

		return "redirect:/mvc/bulletins/lister";

	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("bulletinsSalaire", bulletinSalaireRepository.findAll());
		return mv;
	}

}
