package it.finemodulo.myebay.web.servlet.manageutente;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.utility.UtilityUtente;

@WebServlet("/manageutente/PrepareEditUtenteServlet")
public class PrepareEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idUtenteParam));
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			Map<Ruolo, Boolean> ruoliChecked = UtilityUtente.buildCheckedRolesFromRolesAlreadyInUtente(
					MyServiceFactory.getRuoloServiceInstance().listAll(), utenteInstance.getRuoli());
			request.setAttribute("stati_attr", StatoUtente.values());
			request.setAttribute("mappaRuoliConSelezionati_attr", ruoliChecked);
			request.setAttribute("edit_utente_attr", utenteInstance);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=ERROR").forward(request, response);
			return;
		}

		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

}
