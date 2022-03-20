package it.prova.gestionecontribuenti.execptions;

public class ContribuenteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContribuenteNotFoundException(String m) {
		super(m);
	}
}
