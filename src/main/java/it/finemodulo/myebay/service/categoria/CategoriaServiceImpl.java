package it.finemodulo.myebay.service.categoria;

import java.util.List;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.dao.categoria.CategoriaDAO;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.web.listner.LocalEntityManagerFactoryListener;

public class CategoriaServiceImpl implements CategoriaService {

	CategoriaDAO categoriaDAO;

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;

	}

	@Override
	public List<Categoria> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria cercaPerDescrizioneECodice(String descrizione, String codice) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			categoriaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return categoriaDAO.findByDescrizioneAndCodice(descrizione, codice);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Categoria categoria) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
		
			entityManager.getTransaction().begin();

			
			categoriaDAO.setEntityManager(entityManager);

			
			categoriaDAO.insert(categoria);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

}
