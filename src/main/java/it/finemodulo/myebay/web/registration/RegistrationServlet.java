package it.finemodulo.myebay.web.registration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/public/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");
		String nomeInput = request.getParameter("inputNome");
		String cognomeInput = request.getParameter("inputCognome");
		String creditoInput = request.getParameter("inputCredito");

		try {
			Utente utenteRegistrato = UtilityUtente.createUtenteFormsRegistration(loginInput, nomeInput, cognomeInput,
					passwordInput, creditoInput);
			
				
			if (!UtilityUtente.validateUtenteBean(utenteRegistrato)) {
				request.setAttribute("reg_utente_attr", utenteRegistrato);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
				return;
			}
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteRegistrato);
			request.setAttribute("succesMessage", "Registrazione effettuata, prova a fare il login");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
