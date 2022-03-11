package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/manageutente/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String userNameParam = request.getParameter("username");
		String dataDiCreazioneParam = request.getParameter("dataDiCreazione");
		String[] roleCheck = request.getParameterValues("ruoloInput");
		
		Utente example = UtilityUtente.createUtenteFormSearchManage(nomeParam, cognomeParam, userNameParam,dataDiCreazioneParam,roleCheck);
		
		try {
			request.setAttribute("utenti_list_attribute",
					MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			response.sendRedirect(response.encodeRedirectURL("utente/AreaPersonaleServlet?operationResult=ERROR"));
			return;
		}
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

}
