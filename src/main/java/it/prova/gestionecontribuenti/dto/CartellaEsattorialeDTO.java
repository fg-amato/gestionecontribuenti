package it.prova.gestionecontribuenti.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.StatoCartellaEsattoriale;

public class CartellaEsattorialeDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	@Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String descrizione;

	@NotNull(message = "{importo.notnull}")
	@Min(1)
	private Integer importo;

	@NotNull(message = "{stato.notnull}")
	private StatoCartellaEsattoriale stato;

	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;

	public CartellaEsattorialeDTO() {
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, StatoCartellaEsattoriale stato,
			ContribuenteDTO contribuente) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, StatoCartellaEsattoriale stato) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattorialeDTO(String descrizione, Integer importo) {
		this.descrizione = descrizione;
		this.importo = importo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public StatoCartellaEsattoriale getStato() {
		return stato;
	}

	public void setStato(StatoCartellaEsattoriale stato) {
		this.stato = stato;
	}

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}

}
