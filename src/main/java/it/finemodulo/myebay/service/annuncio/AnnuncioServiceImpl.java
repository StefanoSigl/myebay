package it.finemodulo.myebay.service.annuncio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.dao.annuncio.AnnuncioDAO;
import it.finemodulo.myebay.model.Acquisto;
import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Utente;
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

	@Override
	public Annuncio findOneWithUtenteECategorie(long idAnnuncioParam) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			annuncioDAO.setEntityManager(entityManager);

			return annuncioDAO.findOneWithUtenteECategorie(idAnnuncioParam).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void effettuaAcquisto(Annuncio annuncioInAcquisto, Utente utenteInfo) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);

			annuncioInAcquisto = entityManager.merge(annuncioInAcquisto);
			utenteInfo.setCreditoResiduo(utenteInfo.getCreditoResiduo() - annuncioInAcquisto.getPrezzo());
			annuncioInAcquisto.setIsAperto(false);
			entityManager.persist(new Acquisto(annuncioInAcquisto.getTestoAnnuncio(), new Date(),
					annuncioInAcquisto.getPrezzo(), utenteInfo));

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
