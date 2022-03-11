package it.finemodulo.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;

@WebServlet("/acquisto/ExecuteAcquistiListUtenteServlet")
public class ExecuteAcquistiListUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utenteInfo = (Utente) request.getSession().getAttribute("userInfo");

		request.setAttribute("acquisti_attr",
				MyServiceFactory.getAcquistoServiceInstance().findAllByUtente(utenteInfo.getId()));
		String operationResult = request.getParameter("operationResult");
		if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("acquistiList.jsp").forward(request, response);

	}

}
