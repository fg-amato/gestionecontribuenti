package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteService {
	public List<Contribuente> listAllElements();

	public Contribuente caricaSingoloElemento(Long id);

	public Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id);

	public void aggiorna(Contribuente contribuenteInstance);

	public void inserisciNuovo(Contribuente contribuenteInstance);

	public void rimuovi(Contribuente contribuenteInstance);

	public void rimuoviById(Long id);

	public Page<Contribuente> findByExampleWithPagination(Contribuente example, Integer pageNo, Integer pageSize,
			String sortBy);

	public List<Contribuente> cercaByCognomeENomeILike(String term);
}
