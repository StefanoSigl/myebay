package it.finemodulo.myebay.dao.utente;

import java.util.List;
import java.util.Optional;

import it.finemodulo.myebay.dao.IBaseDAO;
import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> findAllByRuolo(Ruolo ruoloInput);
	public Optional<Utente> findByUsernameAndPassword(String username,String password);
	public Optional<Utente> login(String username,String password);
	public List<Utente> findByExample(Utente example);
	public Optional<Utente> findOneEager(long parseLong);
	

}
