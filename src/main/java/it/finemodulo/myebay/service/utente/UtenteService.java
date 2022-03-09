package it.finemodulo.myebay.service.utente;

import java.util.List;

import it.finemodulo.myebay.dao.utente.UtenteDAO;
import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.Utente;

public interface UtenteService  {
	
	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public Utente findByUsernameAndPassword(String username, String password) throws Exception;
	
	public Utente accedi(String username, String password) throws Exception;

	//per injection
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public  List<Utente> findByExample(Utente example) throws Exception;

	public Utente caricaSingoloElementoEager(long parseLong);

	public void disassociaRuoli(Utente loadedUtente);

	public void aggiornaUtenteConRuoli(Utente utenteInstance,List<Ruolo> listRuoli, String[] roleCheck);

}
