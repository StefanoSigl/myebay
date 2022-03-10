package it.finemodulo.myebay.utility;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;

public class UtilityAnnuncio {

	public static Annuncio formCreateAnnuncio(String testoAnnuncioParam, String prezzoParam,
			String[] categorieChecked) {
		int prezzo = 0;

		if (NumberUtils.isCreatable(prezzoParam)) {
			prezzo = Integer.parseInt(prezzoParam);
		}
		Annuncio example = new Annuncio(testoAnnuncioParam, prezzo);
		if (categorieChecked!=null&&categorieChecked.length > 0) {
			for (String itemCategoria : categorieChecked) {
				if (NumberUtils.isCreatable(itemCategoria)) {
					example.getCategorie().add(new Categoria(Long.parseLong(itemCategoria)));
				}
			}
		}
		return example;
	}

}
