package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;


@WebServlet("/manageutente/ExecuteDeleteUtenteServlet")
public class ExecuteDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}

		try {
			Utente loadedUtenteForDelete = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idUtenteParam));
			loadedUtenteForDelete.setStato(StatoUtente.DISABILITATO);
			MyServiceFactory.getUtenteServiceInstance().aggiorna(loadedUtenteForDelete);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;

		}
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
