package it.finemodulo.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Acquisto;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityAcquisto;

@WebServlet("/acquisto/ExecuteAcquistiListUtenteServlet")
public class ExecuteAcquistiListUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operationResult = request.getParameter("operationResult");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
			request.setAttribute("errorMessage", "Elemento non trovato.");

		String descrizioneParam = request.getParameter("descrizione");
		String prezzoParam = request.getParameter("prezzo");
		String dataParam = request.getParameter("dataDiAcquisto");

		Acquisto example = UtilityAcquisto.formCreateAcquisto(descrizioneParam, prezzoParam, dataParam);
		example.setUtente((Utente) (request.getSession().getAttribute("userInfo")));

		request.setAttribute("acquisti_attr", MyServiceFactory.getAcquistoServiceInstance().findByExample(example));

		request.getRequestDispatcher("acquistiList.jsp").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
