package it.finemodulo.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class AreaPersonaleServlet
 */
@WebServlet("/utente/AreaPersonaleServlet")
public class AreaPersonaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operationResult = request.getParameter("operationResult");
		if(StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
			request.setAttribute("errorMessage", "Elemento non trovato.");
		request.getRequestDispatcher("areaPersonaleUtente.jsp").forward(request, response);

	}

}
