package it.finemodulo.myebay.service.categoria;

import java.util.List;

import it.finemodulo.myebay.dao.categoria.CategoriaDAO;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Ruolo;

public interface CategoriaService {

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public List<Categoria> listAll() throws Exception;

	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice);

	public void inserisciNuovo(Categoria categoria) throws Exception;
}
