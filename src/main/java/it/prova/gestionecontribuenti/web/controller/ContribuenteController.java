package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllRegisti() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> contribuenti = contribuenteService.listAllElements();
		// trasformiamo in DTO
		mv.addObject("contribuenti_list_attribute",
				ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenti));
		mv.setViewName("contribuente/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchContribuente() {
		return "contribuente/search";
	}

	@GetMapping("/insert")
	public String createContribuente(Model model) {
		model.addAttribute("insert_contribuente_attr", new ContribuenteDTO());
		return "contribuente/insert";
	}

	@PostMapping("/save")
	public String saveContribuente(@Valid @ModelAttribute("insert_contribuente_attr") ContribuenteDTO contribuenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "contribuente/insert";
		}
		contribuenteService.inserisciNuovo(contribuenteDTO.buildContribuenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
}
