package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityCategoria;

@WebServlet("/annuncio/PrepareEditAnnuncioServlet")
public class PrepareEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idAnnuncioParam = request.getParameter("idAnnuncio");
		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		try {
			Annuncio annuncioPerEdit = MyServiceFactory.getAnnuncioServiceInstance()
					.findOneWithUtenteECategorie(Long.parseLong(idAnnuncioParam));
			request.setAttribute("edit_annuncio", annuncioPerEdit);
			request.setAttribute("stati_attr", Arrays.asList(true,false));
			request.setAttribute("mappaCategorie_attr", UtilityCategoria.buildCheckedCategoriaAlredyCHeckedInAnnuncio(
					MyServiceFactory.getCategoriaServiceInstance().listAll(), annuncioPerEdit.getCategorie()));

		} catch (Exception e) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}

		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

}
