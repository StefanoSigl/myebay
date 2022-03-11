package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/manageutente/PrepareSearchUtenteServlet")
public class PrepareSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<Ruolo, Boolean> mappaRuoli = UtilityUtente
					.buildCheckedRolesForPages(MyServiceFactory.getRuoloServiceInstance().listAll(), null);

			request.setAttribute("mappaRuoli_attr", mappaRuoli);
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore caricamento Ruoli");
			response.sendRedirect(response.encodeRedirectURL("utente/AreaPersonaleServlet?operationResult=ERROR"));
			return;
		}
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
