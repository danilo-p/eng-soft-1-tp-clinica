package clinica;

import clinica.views.HomeView;
import clinica.views.Router;

/**
 * Classe principal que deve somente iniciar a aplicação na página home.
 */
public class Clinica {

	/**
	 * Cria a janela principal e vai para a home view
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Router.getInstance().goToView(new HomeView());
	}

}
