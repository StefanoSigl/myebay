package it.finemodulo.myebay.dao.acquisto;

import java.util.List;

import it.finemodulo.myebay.dao.IBaseDAO;
import it.finemodulo.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {

	List<Acquisto> findByUtente(Long idUtente);

	List<Acquisto> findByExample(Acquisto example);

}
