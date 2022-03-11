package it.finemodulo.myebay.utility;

import java.util.Date;

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
}
