package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityForm;

@WebServlet("/manageutente/ExecuteEditUtenteServlet")
public class ExecuteEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String idUtenteParam = request.getParameter("idUtenteEdit");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String userNameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String statoParam = request.getParameter("stato");
		String[] roleCheck = request.getParameterValues("ruoloInput");

		// check id dell'utente is creatable
		if (!NumberUtils.isCreatable(idUtenteParam)) {

			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Utente utenteInstance = UtilityForm.createUtenteWithStatoForms(nomeParam, cognomeParam, userNameParam,
				passwordParam, new Date(), statoParam);
		utenteInstance.setId(Long.parseLong(idUtenteParam));

		// se la validazione non risulta ok si torna nell pagina
		if (!UtilityForm.validateUtenteBean(utenteInstance)) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("stati_attr", StatoUtente.values());
			request.setAttribute("edit_utente_attr", utenteInstance);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			return;
		}
		// recupero i ruoli reali facendo anche check id se sono creatable
		Set<Ruolo> ruoliUtente = new HashSet<Ruolo>();
		for (String itemRole : roleCheck) {
			if (!NumberUtils.isCreatable(itemRole)) {
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request,
						response);
				return;
			}

			try {
				ruoliUtente.add(
						MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(Long.parseLong(itemRole)));
			} catch (Exception e) {

				e.printStackTrace();
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request,
						response);
				return;
			}

			// carico i ruoli disponibili e creo la mappa per il checkbox
			try {
				Map<Ruolo, Boolean> mapRuoliChecked = UtilityForm.buildCheckedRolesFromRolesAlreadyInUtente(
						MyServiceFactory.getRuoloServiceInstance().listAll(), ruoliUtente);
				request.setAttribute("mappaRuoliConSelezionati_attr", mapRuoliChecked);
			} catch (Exception e2) {

				e2.printStackTrace();
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request,
						response);
				return;
			}

			try {

				MyServiceFactory.getUtenteServiceInstance().aggiornaUtenteConRuoli(utenteInstance,
						MyServiceFactory.getRuoloServiceInstance().listAll(), roleCheck);

			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request,
						response);
				return;
			}

			// andiamo ai risultati
			// uso il sendRedirect con parametro per evitare il problema del double save on
			// refresh
			response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");

		}
	}
}
