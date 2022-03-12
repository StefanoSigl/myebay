package it.finemodulo.myebay.dao.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;

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
		Annuncio result = entityManager.find(Annuncio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	public void update(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);

	}

	public void insert(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(input);

	}

	public void delete(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));

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
	public List<Annuncio> findByExamplePersonali(Annuncio example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select a from Annuncio a left join a.categorie c left join a.utente u where a.id = a.id ");

		

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
		if (example.getUtente()!=null && example.getUtente().getId() > 0) {
			whereClauses.add("u.id = :idUtente ");
			paramaterMap.put("idUtente", example.getUtente().getId());
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

	@Override
	public Optional<Annuncio> findOneWithUtenteECategorie(long idAnnuncioParam) {

		return entityManager.createQuery(
				"from Annuncio a left join fetch a.utente u left join fetch a.categorie c where a.id= :idAnnuncio",
				Annuncio.class).setParameter("idAnnuncio", idAnnuncioParam).getResultList().stream().findFirst();
	}

}
