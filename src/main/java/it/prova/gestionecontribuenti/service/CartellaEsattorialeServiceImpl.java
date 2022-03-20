package it.prova.gestionecontribuenti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.repository.cartellaesattoriale.CartellaEsattorialeRepository;
@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {

	@Autowired
	private CartellaEsattorialeRepository repository;

	@Transactional(readOnly = true)
	@Override
	public List<CartellaEsattoriale> listAllElements() {
		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEager(id);
	}

	@Override
	@Transactional
	public void aggiorna(CartellaEsattoriale filmInstance) {
		repository.save(filmInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(CartellaEsattoriale filmInstance) {
		repository.save(filmInstance);

	}

	@Override
	@Transactional
	public void rimuovi(CartellaEsattoriale filmInstance) {
		repository.delete(filmInstance);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<CartellaEsattoriale> findByExampleWithPagination(CartellaEsattoriale example, Integer pageNo,
			Integer pageSize, String sortBy) {
		Specification<CartellaEsattoriale> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(
						cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			if (example.getImporto() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("importo"), example.getImporto()));

			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));

			if (example.getContribuente() != null && example.getContribuente().getId() != null)
				predicates.add(cb.equal(cb.upper(root.get("contribuente")), example.getContribuente().getId()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

}
