package it.finemodulo.myebay.service.annuncio;

import it.finemodulo.myebay.dao.annuncio.AnnuncioDAO;

public class AnnuncioServiceImpl implements AnnuncioService {

	AnnuncioDAO annuncioDAO;

	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;

	}

}
