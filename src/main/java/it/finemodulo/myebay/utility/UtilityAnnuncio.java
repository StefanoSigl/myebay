package it.finemodulo.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static boolean validateAnnuncioBeanEdit(Annuncio annuncioEdit) {

		if (StringUtils.isBlank(annuncioEdit.getTestoAnnuncio()) || annuncioEdit.getDataInserimento() == null
				|| annuncioEdit.getPrezzo() < 0 || annuncioEdit.getCategorie() == null
				|| annuncioEdit.getCategorie().size() < 1 || annuncioEdit.getIsAperto() == null)
			return false;
		return true;
	}

	public static Annuncio formCreateAnnuncioForEdit(String testoAnnuncioParam, String prezzoParam,
			String[] categorieChecked, String statoParam, String dataInserimento) {

		Annuncio annuncioInstance = new Annuncio(testoAnnuncioParam, Integer.parseInt(prezzoParam));
		annuncioInstance.setDataInserimento(parseDateCreazioneFromString(dataInserimento));
		annuncioInstance.setIsAperto(Boolean.valueOf(statoParam));
		if (categorieChecked != null && categorieChecked.length > 0) {
			for (String categoriaItemId : categorieChecked) {
				annuncioInstance.getCategorie().add(new Categoria(Long.parseLong(categoriaItemId)));
			}
		}
		return annuncioInstance;
	}
	public static Date parseDateCreazioneFromString(String dataCreazioneStringParam) {
		if (StringUtils.isBlank(dataCreazioneStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataCreazioneStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
