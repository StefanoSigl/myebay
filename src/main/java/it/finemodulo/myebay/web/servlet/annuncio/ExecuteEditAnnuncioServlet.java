package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityAnnuncio;
import it.finemodulo.myebay.utility.UtilityCategoria;

/**
 * Servlet implementation class ExecuteEditAnnuncioServlet
 */
@WebServlet("/annuncio/ExecuteEditAnnuncioServlet")
public class ExecuteEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String statoParam = request.getParameter("stato");
		String dataInserimento = request.getParameter("dataInserimento");
		String[] categorieChecked = request.getParameterValues("categoriaEntry");
		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idAnnuncioParam) || !NumberUtils.isCreatable(idUtenteParam)) {
			request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?operationResult=ERROR").forward(request,
					response);
			return;
		}

		Annuncio annuncioPerEdit = UtilityAnnuncio.formCreateAnnuncioForEdit(testoAnnuncioParam, prezzoParam,
				categorieChecked, statoParam, dataInserimento);
		annuncioPerEdit.setId(Long.parseLong(idAnnuncioParam));
		annuncioPerEdit.setUtente(new Utente(Long.parseLong(idUtenteParam)));

		if (!UtilityAnnuncio.validateAnnuncioBeanEdit(annuncioPerEdit)) {

			try {
				request.setAttribute("edit_annuncio", annuncioPerEdit);
				request.setAttribute("mappaCategorie_attr",
						UtilityCategoria.buildCheckedCategoriaAlredyCHeckedInAnnuncio(
								MyServiceFactory.getCategoriaServiceInstance().listAll(),
								annuncioPerEdit.getCategorie()));
				request.setAttribute("stati_attr", Arrays.asList(true, false));
				request.setAttribute("errorMessage", "Errore validazione campi");

				request.getRequestDispatcher("edit.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("?operationResult=ERROR");
				return;
			}

		}

		try {
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncioPerEdit);

			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByUtente(annuncioPerEdit.getUtente().getId()));
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		request.getRequestDispatcher("listPersonali.jsp").forward(request, response);
		request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?idUtente="
				+ ((Utente) request.getSession().getAttribute("userInfo")).getId() + "&operationaResult=SUCCESS")
				.forward(request, response);
	}

}
