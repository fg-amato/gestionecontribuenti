package it.prova.gestionecontribuenti.repository.cartellaesattoriale;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>,
		PagingAndSortingRepository<CartellaEsattoriale, Long>, JpaSpecificationExecutor<CartellaEsattoriale> {

	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEager(Long id);
}
