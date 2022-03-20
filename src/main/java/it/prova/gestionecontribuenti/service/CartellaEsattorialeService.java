package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {

	public List<CartellaEsattoriale> listAllElements();

	public CartellaEsattoriale caricaSingoloElemento(Long id);

	public CartellaEsattoriale caricaSingoloElementoEager(Long id);

	public void aggiorna(CartellaEsattoriale filmInstance);

	public void inserisciNuovo(CartellaEsattoriale filmInstance);

	public void rimuovi(CartellaEsattoriale filmInstance);

	public Page<CartellaEsattoriale> findByExampleWithPagination(CartellaEsattoriale example, Integer pageNo,
			Integer pageSize, String sortBy);
}
