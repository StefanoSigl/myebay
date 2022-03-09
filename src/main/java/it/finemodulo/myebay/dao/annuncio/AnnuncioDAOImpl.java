package it.finemodulo.myebay.dao.annuncio;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.finemodulo.myebay.model.Annuncio;

public class AnnuncioDAOImpl implements AnnuncioDAO {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Annuncio> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Annuncio> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Annuncio input) throws Exception {
		// TODO Auto-generated method stub

	}

	public void insert(Annuncio input) throws Exception {
		// TODO Auto-generated method stub

	}

	public void delete(Annuncio input) throws Exception {
		// TODO Auto-generated method stub

	}

}
