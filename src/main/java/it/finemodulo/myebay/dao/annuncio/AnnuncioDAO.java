package it.finemodulo.myebay.dao.annuncio;

import java.util.List;

import it.finemodulo.myebay.dao.IBaseDAO;
import it.finemodulo.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {

	public List<Annuncio> findByExample(Annuncio example);

	public List<Annuncio> findByUtente(long parseLong);

}
