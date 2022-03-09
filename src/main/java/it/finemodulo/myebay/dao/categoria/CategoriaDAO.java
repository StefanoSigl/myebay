package it.finemodulo.myebay.dao.categoria;

import it.finemodulo.myebay.dao.IBaseDAO;
import it.finemodulo.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	public Categoria findByDescrizioneAndCodice(String descrizione, String codice);

}
