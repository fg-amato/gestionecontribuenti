package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionecontribuenti.dto.CartellaEsattorialeDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.service.CartellaEsattorialeService;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartella_esattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllFilms() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_esattoriale_list_attribute",
				CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartelle, false));
		mv.setViewName("cartella_esattoriale/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchFilm(Model model) {
		model.addAttribute("contribuenti_list_attribute",
				ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.listAllElements()));
		return "cartella_esattoriale/search";
	}

	@PostMapping("/list")
	public String listFilms(CartellaEsattorialeDTO cartellaExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "9") Integer pageSize,
			@RequestParam(defaultValue = "contribuente.id") String sortBy, ModelMap model) {

		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService
				.findByExampleWithPagination(cartellaExample.buildCartellaEsattorialeModel(), pageNo, pageSize, sortBy)
				.getContent();

		model.addAttribute("cartella_esattoriale_list_attribute",
				CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartelle, false));
		return "cartella_esattoriale/list";
	}
}
