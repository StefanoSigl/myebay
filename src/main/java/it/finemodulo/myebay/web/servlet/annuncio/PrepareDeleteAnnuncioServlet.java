package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteAnnuncioServlet
 */
@WebServlet("/annuncio/PrepareDeleteAnnuncioServlet")
public class PrepareDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String idAnnuncioParam = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		request.setAttribute("delete_annuncio", MyServiceFactory.getAnnuncioServiceInstance()
				.findOneWithUtenteECategorie(Long.parseLong(idAnnuncioParam)));

		request.getRequestDispatcher("delete.jsp").forward(request, response);

	}

	

}
