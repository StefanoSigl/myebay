package it.finemodulo.myebay.utility;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Utente;

public class UtilityUtente {

	public static Utente createUtenteFormsRegistration(String nomeParam, String cognomeParam, String userNameParam,
			String passwordParam, String ceditoParam) {
		if (!NumberUtils.isCreatable(ceditoParam)) {
			return null;
		}
		Utente result = new Utente(userNameParam, passwordParam, nomeParam, cognomeParam,
				Integer.parseInt(ceditoParam));
		return result;
	}
}
