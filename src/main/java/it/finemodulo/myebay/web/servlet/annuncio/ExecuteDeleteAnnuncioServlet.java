package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;

@WebServlet("/annuncio/ExecuteDeleteAnnuncioServlet")
public class ExecuteDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAnnuncioParam = request.getParameter("idAnnuncio");
		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		try {
			MyServiceFactory.getAnnuncioServiceInstance().rimuovi(Long.parseLong(idAnnuncioParam));
		} catch (Exception e) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		request.setAttribute("successMessage", "Rimozione effettuata con successo");
		request.getRequestDispatcher("ExecuteAnnunciPersonaliServlet?idUtente="
				+ ((Utente) request.getSession().getAttribute("userInfo")).getId()).forward(request, response);
	}

}
