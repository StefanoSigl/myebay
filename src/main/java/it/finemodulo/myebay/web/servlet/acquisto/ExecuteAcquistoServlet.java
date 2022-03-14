package it.finemodulo.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Annuncio;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;

@WebServlet("/public/ExecuteAcquistoServlet")
public class ExecuteAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utenteInfo = (Utente) request.getSession().getAttribute("userInfo");
		String idAnnuncioParam = request.getParameter("idAnnuncio");
		if (!NumberUtils.isCreatable(idAnnuncioParam)) {
			response.sendRedirect("?operationResult=ERROR");
			return;
		}

		if (utenteInfo == null) {
			// request.getSession().setAttribute("visual_annuncio", annuncioInAcquisto);
			// request.getSession().setAttribute("compra_senza_login", true);

			response.sendRedirect(response.encodeRedirectURL(
					request.getContextPath() + "/public/PrepareLoginByAnnuncioServlet?idAnnuncio=" + idAnnuncioParam));
			return;
		}
		Annuncio annuncioInAcquisto = MyServiceFactory.getAnnuncioServiceInstance()
				.findOneWithUtenteECategorie(Long.parseLong(idAnnuncioParam));
		if (utenteInfo.getCreditoResiduo() < annuncioInAcquisto.getPrezzo()) {

			request.setAttribute("visual_annuncio", annuncioInAcquisto);
			request.setAttribute("errorMessage", "Mi dispiace il credito residuo non basta");
			request.getRequestDispatcher("show.jsp").forward(request, response);
			return;

		}
		try {
			MyServiceFactory.getAnnuncioServiceInstance().effettuaAcquisto(annuncioInAcquisto, utenteInfo);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("visual_annuncio", annuncioInAcquisto);
			request.setAttribute("errorMessage", "Qualcosa è andato storto");
			request.getRequestDispatcher("show.jsp").forward(request, response);
		}
		response.sendRedirect(response.encodeRedirectURL(
				request.getContextPath() + "/acquisto/ExecuteAcquistiListUtenteServlet?operationResult=SUCCESS"));

	}

}
