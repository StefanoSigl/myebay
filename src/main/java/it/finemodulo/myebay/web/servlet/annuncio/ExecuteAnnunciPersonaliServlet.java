package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.service.MyServiceFactory;

@WebServlet("/annuncio/ExecuteAnnunciPersonaliServlet")
public class ExecuteAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		try {
			String operationResult = request.getParameter("operationResult");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
				request.setAttribute("successMessage", "Operazione effettuata con successo");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
				request.setAttribute("errorMessage", "Elemento non trovato.");
			
			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByUtente(Long.parseLong(idParam)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}

		request.getRequestDispatcher("listPersonali.jsp").forward(request, response);
	}

}
