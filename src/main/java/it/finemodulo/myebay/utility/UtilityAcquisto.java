package it.finemodulo.myebay.utility;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Acquisto;

public class UtilityAcquisto {

	public static Acquisto formCreateAcquisto(String descrizioneParam, String prezzoParam, String dataParam) {
		Acquisto instanceAcquisto = new Acquisto();
		int prezzo = 0;
		instanceAcquisto.setDescrizione(descrizioneParam);
		if (NumberUtils.isCreatable(prezzoParam)) {
			prezzo = Integer.parseInt(prezzoParam);
		}
		instanceAcquisto.setPrezzo(prezzo);
		instanceAcquisto.setDataAcquisto(UtilityUtente.parseDateCreazioneFromString(dataParam));

		return instanceAcquisto;
	}

}
