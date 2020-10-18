package clinica;

import clinica.views.HomeView;
import clinica.views.Router;

/**
 * Classe principal que deve somente iniciar a aplicação na página home.
 */
public class Clinica {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
//		Cria a home view
		Router.getInstance().goToView(new HomeView());
	}

}
