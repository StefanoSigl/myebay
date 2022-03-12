package it.finemodulo.myebay.dao.acquisto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Acquisto;

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
		Acquisto result = entityManager.find(Acquisto.class, id);

		return result != null ? Optional.of(result) : Optional.empty();
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

	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Acquisto a left join a.utente u where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" a.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getPrezzo() > -1) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzo());
		}

		if (example.getDataAcquisto() != null) {
			whereClauses.add("a.dataAcquisto >= :dataAcquisto ");
			paramaterMap.put("dataAcquisto", example.getDataAcquisto());
		}
		if (example.getUtente() != null && example.getUtente().getId() > 0) {
			whereClauses.add("u.id = :idUtente ");
			paramaterMap.put("idUtente", example.getUtente().getId());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Acquisto> typedQuery = entityManager.createQuery(queryBuilder.toString(), Acquisto.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
