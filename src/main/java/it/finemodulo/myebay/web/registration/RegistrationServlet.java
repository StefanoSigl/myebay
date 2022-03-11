package it.finemodulo.myebay.web.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");
		String nomeInput= request.getParameter("nomeInput");
		String cognomeInput= request.getParameter("cognomeInput");
		String creditoInput= request.getParameter("creditoInput");
		
		Utente utenteRegistrato= UtilityUtente.createUtenteFormsRegistration();
		
		
		
	}

}
