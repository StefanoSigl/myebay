package it.finemodulo.myebay.dao.acquisto;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.model.Acquisto;
import it.finemodulo.myebay.model.Annuncio;

public class AcquistoDAOImpl implements AcquistoDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Acquisto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Acquisto> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Acquisto input) throws Exception {
		// TODO Auto-generated method stub

	}

	public void insert(Acquisto input) throws Exception {
		// TODO Auto-generated method stub

	}

	public void delete(Acquisto input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Acquisto> findByUtente(Long idUtente) {
		return entityManager
				.createQuery("from Acquisto a left join fetch a.utente u where u.id= :idUtente ", Acquisto.class)
				.setParameter("idUtente", idUtente).getResultList();
	}



}
