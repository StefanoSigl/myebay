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
import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;

public class UtilityUtente {

	public static Utente createUtenteFormsRegistration(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, String ceditoParam) throws Exception {
		int credito = -1;
		if (NumberUtils.isCreatable(ceditoParam)) {
			credito = Integer.parseInt(ceditoParam);
		}
		Utente result = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam, credito);
		result.setDateCreated(new Date());
		result.getRuoli().add(MyServiceFactory.getRuoloServiceInstance().cercaPerDescrizioneECodice("Classic User",
				Ruolo.ROLE_CLASSIC_USER));
		result.setStato(StatoUtente.CREATO);
		return result;
	}

	public static boolean validateUtenteBean(Utente utenteInstance) {
		// prima controlliamo che non siano vuoti i parametri
		if (utenteInstance == null)
			return false;
		if (StringUtils.isBlank(utenteInstance.getNome()) || StringUtils.isBlank(utenteInstance.getCognome())
				|| StringUtils.isBlank(utenteInstance.getUsername())
				|| StringUtils.isBlank(utenteInstance.getPassword()) || utenteInstance.getCreditoResiduo() < 0
				|| utenteInstance.getRuoli().size() < 1 || utenteInstance.getStato() == null
				|| !utenteInstance.getStato().equals(StatoUtente.CREATO))
			return false;

		return true;
	}

	public static Utente createUtenteFormSearchManage(String nomeParam, String cognomeParam, String userNameParam,
			String dataDiCreazioneParam, String[] roleCheck) {
		Utente example = new Utente(userNameParam, nomeParam, cognomeParam,
				parseDateCreazioneFromString(dataDiCreazioneParam));
		if (roleCheck != null && roleCheck.length > 0) {
			for (String itemRuolo : roleCheck) {
				if (NumberUtils.isCreatable(itemRuolo)) {
					example.getRuoli().add(new Ruolo(Long.parseLong(itemRuolo)));
				}
			}
		}
		return example;
	}

	public static Utente createUtenteFormWithPassword(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, Date dataDiCreazioneParam, String[] roleCheck) {
		Utente example = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam, dataDiCreazioneParam);

		if (roleCheck != null && roleCheck.length > 0) {
			for (String itemRuolo : roleCheck) {
				if (NumberUtils.isCreatable(itemRuolo)) {
					example.getRuoli().add(new Ruolo(Long.parseLong(itemRuolo)));
				}
			}
		}
		return example;
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

	public static Utente createUtenteFormsEdit(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, String dataParam, String statoParam, String[] roleCheck, String creditoParam) {
		int credito = -1;
		if (NumberUtils.isCreatable(creditoParam)) {
			credito = Integer.parseInt(creditoParam);
		}
		Utente result = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam,
				parseDateCreazioneFromString(dataParam), credito);
		result.setStato(StatoUtente.valueOf(statoParam));

		if (roleCheck != null && roleCheck.length > 0) {
			for (String itemRuolo : roleCheck) {
				if (NumberUtils.isCreatable(itemRuolo)) {
					result.getRuoli().add(new Ruolo(Long.parseLong(itemRuolo)));
				}
			}
		}
		return result;

	}
}
