package it.prova.gestionecontribuenti.repository.cartellaesattoriale;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends CrudRepository<CartellaEsattoriale, Long>,
		PagingAndSortingRepository<CartellaEsattoriale, Long>, JpaSpecificationExecutor<CartellaEsattoriale> {

	@EntityGraph(attributePaths = { "contribuenti" })
	Optional<CartellaEsattoriale> findById(Long id);
}
