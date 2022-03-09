package it.finemodulo.myebay.model;

public enum StatoUtente {
	ATTIVO("attivo"), DISABILITATO("disabilitato"), CREATO("creato");

	private String tipo;

	public static StatoUtente[] statiUtente=StatoUtente.values();

	private StatoUtente(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
