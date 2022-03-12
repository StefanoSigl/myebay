package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/manageutente/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String userNameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String[] roleCheck = request.getParameterValues("ruoloInput");

		Utente utenteInstance = UtilityUtente.createUtenteFormWithPassword(nomeParam, cognomeParam, userNameParam,
				passwordParam, new Date(), roleCheck);
		utenteInstance.setCreditoResiduo(0);

		if (!UtilityUtente.validateUtenteBean(utenteInstance)) {
			request.setAttribute("insert_utente_attr", utenteInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				request.setAttribute("mappaRuoliConSelezionati_attr",
						UtilityUtente.buildCheckedRolesFromRolesAlreadyInUtente(
								MyServiceFactory.getRuoloServiceInstance().listAll(), utenteInstance.getRuoli()));
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");

	}

}
