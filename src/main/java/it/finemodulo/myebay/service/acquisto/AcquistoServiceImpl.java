package it.finemodulo.myebay.service.acquisto;

import java.util.List;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.dao.acquisto.AcquistoDAO;
import it.finemodulo.myebay.model.Acquisto;
import it.finemodulo.myebay.web.listner.LocalEntityManagerFactoryListener;

public class AcquistoServiceImpl implements AcquistoService {

	AcquistoDAO acquistoDAO;

	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;

	}

	@Override
	public List<Acquisto> findAllByUtente(Long idUtente) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			acquistoDAO.setEntityManager(entityManager);

			return acquistoDAO.findByUtente(idUtente);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			acquistoDAO.setEntityManager(entityManager);

			return acquistoDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto findOne(long idAcquisto) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			acquistoDAO.setEntityManager(entityManager);

			return acquistoDAO.findOne(idAcquisto).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
