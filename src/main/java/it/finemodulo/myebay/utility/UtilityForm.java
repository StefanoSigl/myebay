package it.finemodulo.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;

public class UtilityForm {



	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Utente createUtenteForms(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, Date date) {
		Utente result = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam, date);
		return result;
	}

	public static boolean validateUtenteBean(Utente utenteInstance) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteInstance.getNome()) || StringUtils.isBlank(utenteInstance.getCognome())
				|| StringUtils.isBlank(utenteInstance.getUsername())
				|| StringUtils.isBlank(utenteInstance.getPassword()))
			return false;

		return true;
	}

	public static Map<Ruolo, Boolean> buildCheckedRolesFromRolesAlreadyInUtente(List<Ruolo> listaTotaleRuoli,
			Set<Ruolo> listaRuoliPossedutiDaUtente) {
		Map<Ruolo, Boolean> result = new HashMap<>();

		// converto array di ruoli in List di Long
		List<Long> ruoliConvertitiInIds = new ArrayList<>();
		for (Ruolo ruoloDiUtenteItem : listaRuoliPossedutiDaUtente != null ? listaRuoliPossedutiDaUtente
				: new ArrayList<Ruolo>()) {
			ruoliConvertitiInIds.add(ruoloDiUtenteItem.getId());
		}

		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliConvertitiInIds.contains(ruoloItem.getId()));
		}

		return result;
	}

	public static Map<Ruolo, Boolean> buildCheckedRolesForPages(List<Ruolo> listaTotaleRuoli,
			String[] ruoliFromParams) {
		Map<Ruolo, Boolean> result = new HashMap<>();

		// converto array di string in List di Long
		List<Long> ruoliIdConvertiti = new ArrayList<>();
		for (String stringItem : ruoliFromParams != null ? ruoliFromParams : new String[] {}) {
			ruoliIdConvertiti.add(Long.valueOf(stringItem));
		}

		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliIdConvertiti.contains(ruoloItem.getId()));
		}

		return result;
	}

	public static Utente createUtenteWithStatoForms(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, Date date, String statoParam) {
		Utente result = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam, date);
		result.setStato(StatoUtente.valueOf(statoParam));
		return result;

	}
}
