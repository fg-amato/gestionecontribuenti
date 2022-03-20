package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/insert")
	public String createFilm(Model model) {
		model.addAttribute("insert_cartella_esattoriale_attr", new CartellaEsattorialeDTO());
		return "cartella_esattoriale/insert";
	}

	// inietto la request perché ci potrebbe tornare utile per ispezionare i
	// parametri
	@PostMapping("/save")
	public String saveFilm(
			@Valid @ModelAttribute("insert_cartella_esattoriale_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per
		// visualizzarne nome e cognome nel campo testo
		if (cartellaEsattorialeDTO.getContribuente() == null
				|| cartellaEsattorialeDTO.getContribuente().getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaEsattorialeDTO.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(
					contribuenteService.caricaSingoloElemento(cartellaEsattorialeDTO.getContribuente().getId())));

		if (result.hasErrors()) {
			return "cartella_esattoriale/insert";
		}
		cartellaEsattorialeService.inserisciNuovo(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartella_esattoriale";
	}

	@GetMapping("/show/{idCartellaEsattoriale}")
	public String showFilm(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
		model.addAttribute("show_cartella_esattoriale_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		return "cartella_esattoriale/show";
	}

	@GetMapping("/delete/{idCartellaEsattoriale}")
	public String deleteFilm(@PathVariable(required = true) Long idCartellaEsattoriale, Model model) {
		model.addAttribute("delete_cartella_esattoriale_attr",
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartellaEsattoriale));
		return "cartella_esattoriale/delete";
	}

	@PostMapping("/remove")
	public String removeFilm(@RequestParam(required = true) Long idCartellaEsattoriale,
			RedirectAttributes redirectAttrs) {

		cartellaEsattorialeService.rimuovi(cartellaEsattorialeService.caricaSingoloElemento(idCartellaEsattoriale));
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartella_esattoriale";
	}
}
