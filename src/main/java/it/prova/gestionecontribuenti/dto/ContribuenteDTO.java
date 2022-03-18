package it.prova.gestionecontribuenti.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContribuenteDTO {

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

	@NotNull(message = "{indirizzo.notblank}")
	private String indirizzo;

	public ContribuenteDTO() {
	}

	public ContribuenteDTO(Long id) {
		this.id = id;
	}

	public ContribuenteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataDiNascita) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
	}

	public ContribuenteDTO(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	public ContribuenteDTO(String nome, String cognome, String codiceFiscale, Date dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
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

}
