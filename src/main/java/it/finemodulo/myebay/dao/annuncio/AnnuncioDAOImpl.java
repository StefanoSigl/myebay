package it.finemodulo.myebay.dao.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.Utente;

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
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(input);

	}

	public void delete(Annuncio input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c where a.id = a.id ");

		whereClauses.add("a.aperto = :aperto ");
		paramaterMap.put("aperto", true);
		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() > -1) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", example.getPrezzo());
		}

		if (example.getDataInserimento() != null) {
			whereClauses.add("a.dataInserimento >= :dataInserimento ");
			paramaterMap.put("dataInserimento", example.getDataInserimento());
		}
		if (example.getCategorie() != null && example.getCategorie().size() > 0) {
			List<Long> idCategorie = new ArrayList<>();
			for (Categoria categoriaItem : example.getCategorie()) {
				idCategorie.add(categoriaItem.getId());
			}
			whereClauses.add("c.id in :categoria");
			paramaterMap.put("categoria", idCategorie);

		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Annuncio> findByUtente(long parseLong) {

		return entityManager
				.createQuery("from Annuncio a left join fetch a.utente u where u.id= :idUtente ", Annuncio.class)
				.setParameter("idUtente", parseLong).getResultList();
	}

}
