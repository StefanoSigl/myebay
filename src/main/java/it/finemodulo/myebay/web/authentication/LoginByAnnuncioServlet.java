package it.finemodulo.myebay.web.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;


@WebServlet("/public/LoginByAnnuncioServlet")
public class LoginByAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		String destinazione = null;

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			//check se utente e null 
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Utente non trovato.");
				destinazione = "login.jsp";
			} /*else {
				//tramite la sessione controlle se il login è stato effettuato attraverso il tasto compra
				if (request.getSession().getAttribute("compra_senza_login")!=null&&(boolean) request.getSession().getAttribute("compra_senza_login")) {
					request.getSession().setAttribute("userInfo", utenteInstance);
					//mando redirect alla show annuncio
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/public/ExecuteVisualizzaAnnuncioServlet"));
					return;
				 } */
			// altrimenti va alla sua area personale
			request.getSession().setAttribute("userInfo", utenteInstance);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()
					+ "/public/ExecuteVisualizzaAnnuncioServlet?idAnnuncio=" + request.getParameter("idAnnuncio")));
			return;

		} catch (Exception e) {
			e.printStackTrace();
			destinazione = "login.jsp";
			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
		}

		request.getRequestDispatcher(destinazione).forward(request, response);

	}

}
