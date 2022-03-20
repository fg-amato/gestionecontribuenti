package it.prova.gestionecontribuenti.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>,
		PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);
	
	@EntityGraph(attributePaths = { "cartelleEsattoriali" })
	Contribuente getById(Long id);
}
