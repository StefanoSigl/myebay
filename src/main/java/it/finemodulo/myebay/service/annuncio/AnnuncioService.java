package it.finemodulo.myebay.service.annuncio;

import java.util.List;

import it.finemodulo.myebay.dao.annuncio.AnnuncioDAO;
import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Utente;

public interface AnnuncioService {

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public List<Annuncio> findByExample(Annuncio example);

	public  List<Annuncio> findByUtente(long parseLong);

	public void inserisciNuovo(Annuncio annuncioInsert) throws Exception;

	public Annuncio findOneWithUtenteECategorie(long idAnnuncio);

	public void effettuaAcquisto(Annuncio annuncioInAcquisto, Utente utenteInfo) throws Exception;

	public void rimuovi(long idAnnuncio) throws Exception;

	public void aggiorna(Annuncio annuncioPerEdit) throws Exception;

	public List<Annuncio> findByExamplePersonali(Annuncio example);

}
