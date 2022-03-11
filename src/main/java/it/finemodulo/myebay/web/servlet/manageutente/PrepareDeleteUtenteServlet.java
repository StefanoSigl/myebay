package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.exception.ElementNotFoundException;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;

@WebServlet("/manageutente/PrepareDeleteUtenteServlet")
public class PrepareDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}

		try {
			Utente loadedUtenteForDelete = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idUtenteParam));

			request.setAttribute("delete_utente_attr", loadedUtenteForDelete);
			request.setAttribute("ruoliDiUtente", loadedUtenteForDelete.getRuoli());
		} catch (ElementNotFoundException ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}
		request.getRequestDispatcher("delete.jsp").forward(request, response);
	}
}
