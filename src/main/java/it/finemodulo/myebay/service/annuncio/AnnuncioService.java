package it.finemodulo.myebay.service.annuncio;

import java.util.List;

import it.finemodulo.myebay.dao.annuncio.AnnuncioDAO;
import it.finemodulo.myebay.model.Annuncio;

public interface AnnuncioService {

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public List<Annuncio> findByExample(Annuncio example);

	public  List<Annuncio> findByUtente(long parseLong);

	public void inserisciNuovo(Annuncio annuncioInsert) throws Exception;

}
