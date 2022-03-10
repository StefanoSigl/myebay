package it.finemodulo.myebay.service.annuncio;

import java.util.List;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.dao.annuncio.AnnuncioDAO;
import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.web.listner.LocalEntityManagerFactoryListener;

public class AnnuncioServiceImpl implements AnnuncioService {

	AnnuncioDAO annuncioDAO;

	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;

	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			annuncioDAO.setEntityManager(entityManager);

			return annuncioDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> findByUtente(long parseLong) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			annuncioDAO.setEntityManager(entityManager);

			return annuncioDAO.findByUtente(parseLong);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInsert) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);

			annuncioDAO.insert(annuncioInsert);

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
