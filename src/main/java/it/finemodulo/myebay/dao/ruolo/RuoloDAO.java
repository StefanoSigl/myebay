package it.finemodulo.myebay.dao.ruolo;

import it.finemodulo.myebay.dao.IBaseDAO;
import it.finemodulo.myebay.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
