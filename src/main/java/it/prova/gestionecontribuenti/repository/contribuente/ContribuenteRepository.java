package it.prova.gestionecontribuenti.repository.contribuente;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>,
		PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {

}
