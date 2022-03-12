package it.finemodulo.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteVisualizzaAcquistoServlet
 */
@WebServlet("/acquisto/ExecuteVisualizzaAcquistoServlet")
public class ExecuteVisualizzaAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAcquistoParam = request.getParameter("idAcquisto");

		if (!NumberUtils.isCreatable(idAcquistoParam)) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}
		try {
			request.setAttribute("visual_acquisto",
					MyServiceFactory.getAcquistoServiceInstance().findOne(Long.parseLong(idAcquistoParam)));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("?operationResult=ERROR");
			return;
			
		}

		request.getRequestDispatcher("show.jsp").forward(request, response);

	}
}
