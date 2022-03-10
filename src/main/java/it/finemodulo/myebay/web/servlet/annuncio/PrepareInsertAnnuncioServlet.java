package it.finemodulo.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityCategoria;

@WebServlet("/annuncio/PrepareInsertAnnuncioServlet")
public class PrepareInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Categoria, Boolean> mappaCategorie;
		try {
			Annuncio annuncioInstance = new Annuncio();
			request.setAttribute("annuncioInsert", annuncioInstance);
			mappaCategorie = UtilityCategoria
					.createMapForAttribute(MyServiceFactory.getCategoriaServiceInstance().listAll());
			request.setAttribute("mappaCategorie_attr", mappaCategorie);

		} catch (Exception e) {

			e.printStackTrace();
		}

		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

}
