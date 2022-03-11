package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityAnnuncio;
import it.finemodulo.myebay.utility.UtilityCategoria;

@WebServlet("/annuncio/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieChecked = request.getParameterValues("categoriaEntry");
		Utente utenteInSession = (Utente) request.getSession().getAttribute("userInfo");

		Map<Categoria, Boolean> mapCategorie;
		try {
			mapCategorie = UtilityCategoria.buildCheckedCategorieForPages(
					MyServiceFactory.getCategoriaServiceInstance().listAll(), categorieChecked);
		} catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?operationResult=ERROR").forward(request,
					response);
			return;
		}

		if (!NumberUtils.isCreatable(prezzoParam)) {
			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByUtente(utenteInSession.getId()));
			request.setAttribute("mappaCategorie_attr", mapCategorie);
			request.setAttribute("errorMessage", "Errore validazione campi");

			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		Annuncio annuncioInsert = UtilityAnnuncio.formCreateAnnuncioForInsert(testoAnnuncioParam, prezzoParam,
				categorieChecked, utenteInSession.getId());

		if (!UtilityAnnuncio.validateAnnuncioBean(annuncioInsert)) {
			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByUtente(utenteInSession.getId()));
			request.setAttribute("mappaCategorie_attr", mapCategorie);
			request.setAttribute("errorMessage", "Errore validazione campi");

			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}
		try {
			MyServiceFactory.getAnnuncioServiceInstance().inserisciNuovo(annuncioInsert);

			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByUtente(utenteInSession.getId()));
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?operationResult=ERROR").forward(request,
					response);
			return;
		}
		// request.getRequestDispatcher("listPersonali.jsp").forward(request, response);
		request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?idUtente="
				+ ((Utente) request.getSession().getAttribute("userInfo")).getId() + "&operationaResult=SUCCESS")
				.forward(request, response);
	}
}
