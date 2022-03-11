package it.finemodulo.myebay.web.registration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Utente;


@WebServlet("/public/PrepareRegistrationUtenteServlet")
public class PrepareRegistrationUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userInfo")!=null) {
			request.setAttribute("errorMessage","Sei gia registrato");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		request.setAttribute("reg_utente_attr", new Utente());
		request.getRequestDispatcher("registration.jsp").forward(request, response);
		
	}

	
}
