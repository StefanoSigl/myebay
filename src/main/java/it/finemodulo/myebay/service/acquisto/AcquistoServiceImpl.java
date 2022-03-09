package it.finemodulo.myebay.service.acquisto;

import it.finemodulo.myebay.dao.acquisto.AcquistoDAO;

public class AcquistoServiceImpl implements AcquistoService {

	AcquistoDAO acquistoDAO;
	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO=acquistoDAO;

	}

}
