package it.finemodulo.myebay.utility;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Utente;

public class UtilityAnnuncio {

	public static Annuncio formCreateAnnuncio(String testoAnnuncioParam, String prezzoParam,
			String[] categorieChecked) {
		int prezzo = 0;

		if (NumberUtils.isCreatable(prezzoParam)) {
			prezzo = Integer.parseInt(prezzoParam);
		}
		Annuncio example = new Annuncio(testoAnnuncioParam, prezzo);
		if (categorieChecked != null && categorieChecked.length > 0) {
			for (String itemCategoria : categorieChecked) {
				if (NumberUtils.isCreatable(itemCategoria)) {
					example.getCategorie().add(new Categoria(Long.parseLong(itemCategoria)));
				}
			}
		}
		return example;
	}

	public static Annuncio formCreateAnnuncioForInsert(String testoAnnuncioParam, String prezzoParam,
			String[] categorieChecked, Long idUtente) {

		Annuncio annuncioInstance = new Annuncio(testoAnnuncioParam, Integer.parseInt(prezzoParam));
		annuncioInstance.setDataInserimento(new Date());
		annuncioInstance.setUtente(new Utente(idUtente));
		annuncioInstance.setIsAperto(true);
		if (categorieChecked != null && categorieChecked.length > 0) {
			for (String categoriaItemId : categorieChecked) {
				annuncioInstance.getCategorie().add(new Categoria(Long.parseLong(categoriaItemId)));
			}
		}
		return annuncioInstance;
	}

	public static boolean validateAnnuncioBean(Annuncio annuncioInsert) {

		if (StringUtils.isBlank(annuncioInsert.getTestoAnnuncio()) || annuncioInsert.getDataInserimento() == null
				|| annuncioInsert.getPrezzo() < 0 || annuncioInsert.getCategorie() == null
				|| annuncioInsert.getCategorie().size() < 1)
			return false;
		return true;
	}
}
