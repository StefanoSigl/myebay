package it.finemodulo.myebay.web.listner;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.finemodulo.myebay.model.Categoria;
import it.finemodulo.myebay.model.Ruolo;
import it.finemodulo.myebay.model.StatoUtente;
import it.finemodulo.myebay.model.Utente;
import it.finemodulo.myebay.service.MyServiceFactory;
import it.finemodulo.myebay.service.categoria.CategoriaService;
import it.finemodulo.myebay.service.ruolo.RuoloService;
import it.finemodulo.myebay.service.utente.UtenteService;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("myebay_unit");
			// questa chiamata viene fatta qui per semplicit√† ma in genere √® meglio trovare
			// altri modi per fare init
			initAdminUserAndRuoli();
			initCategorie();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initAdminUserAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}

		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setCreditoResiduo(50000);
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
		if (utenteServiceInstance.findByUsernameAndPassword("user", "user") == null) {
			Utente user = new Utente("user", "user", "Peppe", "Bocci", new Date());
			user.setStato(StatoUtente.ATTIVO);
			user.setCreditoResiduo(50000);
			utenteServiceInstance.inserisciNuovo(user);
			utenteServiceInstance.aggiungiRuolo(user,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
		}

	}

	private void initCategorie() throws Exception {
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Elettronica", "CAT_ELETTR") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Elettronica", "CAT_ELETTR"));
		}
		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Casa", "CAT_CASA") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Casa", "CAT_CASA"));
		}
		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Giardino", "CAT_GIARDINO") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Giardino", "CAT_GIARDINO"));
		}
		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Guerra", "CAT_GUERRA") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Guerra", "CAT_GUERRA"));
		}
		if (categoriaServiceInstance.cercaPerDescrizioneECodice("Idraulica", "CAT_IDRAULICA") == null) {
			categoriaServiceInstance.inserisciNuovo(new Categoria("Idraulica", "CAT_IDRAULICA"));
		}

	}

}
