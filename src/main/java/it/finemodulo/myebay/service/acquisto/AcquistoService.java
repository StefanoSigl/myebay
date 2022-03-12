package it.finemodulo.myebay.service.acquisto;

import java.util.List;

import it.finemodulo.myebay.dao.acquisto.AcquistoDAO;
import it.finemodulo.myebay.model.Acquisto;

public interface AcquistoService {

	public void setAcquistoDAO(AcquistoDAO acquistoDAO);

	public List<Acquisto> findAllByUtente(Long id);

	public  List<Acquisto> findByExample(Acquisto example);

}
