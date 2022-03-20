package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.model.StatoCartellaEsattoriale;

public class ContribuenteConCartelleEsattorialiDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceFiscale;

	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	private Set<CartellaEsattorialeDTO> cartelleEsattoriali = new HashSet<>();

	public ContribuenteConCartelleEsattorialiDTO() {
	}

	public ContribuenteConCartelleEsattorialiDTO(Long id) {
		this.id = id;
	}

	public ContribuenteConCartelleEsattorialiDTO(Long id, String nome, String cognome, String codiceFiscale,
			Date dataDiNascita, String indirizzo, Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public ContribuenteConCartelleEsattorialiDTO(String nome, String cognome, String codiceFiscale, String indirizzo,
			Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public ContribuenteConCartelleEsattorialiDTO(String nome, String cognome, String codiceFiscale, Date dataDiNascita,
			String indirizzo, Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataDiNascita, this.codiceFiscale,
				this.indirizzo);
	}

	public static ContribuenteConCartelleEsattorialiDTO buildContribuenteConCartelleEsattorialiDTOFromModel(
			Contribuente contribuenteModel) {
		return new ContribuenteConCartelleEsattorialiDTO(contribuenteModel.getId(), contribuenteModel.getNome(),
				contribuenteModel.getCognome(), contribuenteModel.getCodiceFiscale(),
				contribuenteModel.getDataDiNascita(), contribuenteModel.getIndirizzo(), CartellaEsattorialeDTO
						.createCartellaEsattorialeDTOSetFromModelSet(contribuenteModel.getCartelleEsattoriali(), true));
	}

	public Set<CartellaEsattorialeDTO> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

//	public static Set<ContribuenteConCartelleEsattorialiDTO> createContribuenteConCartelleEsattorialiDTOSetFromModelSet(
//			Set<Contribuente> modelSetInput) {
//		return modelSetInput.stream().map(contribuenteEntity -> {
//			return ContribuenteConCartelleEsattorialiDTO
//					.buildContribuenteConCartelleEsattorialiDTOFromModel(contribuenteEntity);
//		}).collect(Collectors.toSet());
//	}

	// aggiungere metodi:
	// -calcolo importi da complessivi, versati e da versare

	public boolean isInContenzioso() {
		if (this.getCartelleEsattoriali().isEmpty()) {
			return false;
		}

		for (CartellaEsattorialeDTO item : this.getCartelleEsattoriali()) {
			if (item.getStato() == StatoCartellaEsattoriale.IN_CONTENZIOSO) {
				return true;
			}
		}
		return false;
	}

	public static List<ContribuenteConCartelleEsattorialiDTO> createContribuenteConCartelleEsattorialiDTOSetFromModelList(
			List<Contribuente> modelListInput, boolean b) {
		return modelListInput.stream().map(contribuenteEntity -> {
			return ContribuenteConCartelleEsattorialiDTO
					.buildContribuenteConCartelleEsattorialiDTOFromModel(contribuenteEntity);
		}).collect(Collectors.toList());
	}

	public Integer calcolaTotaleImportoCartelle() {
		int totale = 0;
		if (this.getCartelleEsattoriali().isEmpty()) {
			return totale;
		}
		for (CartellaEsattorialeDTO item : this.getCartelleEsattoriali()) {
			totale += item.getImporto();
		}
		
		return totale;
	}
	
	public Integer calcolaTotaleConclusoEPagato() {
		int totale = 0;
		if (this.getCartelleEsattoriali().isEmpty()) {
			return totale;
		}
		for (CartellaEsattorialeDTO item : this.getCartelleEsattoriali()) {
			
			if (item.getStato() == StatoCartellaEsattoriale.CONCLUSA) {
				totale += item.getImporto();
			}
		}
		
		return totale;
	}
	
	public Integer calcolaTotaleInContenzioso() {
		int totale = 0;
		if (this.getCartelleEsattoriali().isEmpty()) {
			return totale;
		}
		for (CartellaEsattorialeDTO item : this.getCartelleEsattoriali()) {
			
			if (item.getStato() == StatoCartellaEsattoriale.IN_CONTENZIOSO) {
				totale += item.getImporto();
			}
		}
		
		return totale;
	}
}
