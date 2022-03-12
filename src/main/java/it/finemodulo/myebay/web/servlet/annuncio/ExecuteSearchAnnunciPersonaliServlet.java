package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityAnnuncio;

@WebServlet("/annuncio/ExecuteSearchAnnunciPersonaliServlet")
public class ExecuteSearchAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Ricevo i param da input
		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] categorieChecked = request.getParameterValues("categoriaEntry");

		Annuncio example = UtilityAnnuncio.formCreateAnnuncio(testoAnnuncioParam, prezzoParam, categorieChecked);
		example.setUtente((Utente) (request.getSession().getAttribute("userInfo")));
		try {
			request.setAttribute("annunciPersonali_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByExamplePersonali(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("listPersonali.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}
