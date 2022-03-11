package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/manageutente/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Utente utenteInstance = new Utente();
			utenteInstance.getRuoli().add(new Ruolo());

			Map<Ruolo, Boolean> mapRuoli = UtilityUtente.buildCheckedRolesFromRolesAlreadyInUtente(
					MyServiceFactory.getRuoloServiceInstance().listAll(), utenteInstance.getRuoli());

			request.setAttribute("insert_utente_attr", utenteInstance);
			request.setAttribute("mappaRuoliConSelezionati_attr", mapRuoli);

		} catch (Exception e) {
			response.sendRedirect("ExecuteListUtenteServlet?operationResult=ERROR");
			e.printStackTrace();
		}
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

}
