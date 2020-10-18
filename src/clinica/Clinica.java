/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica;

import clinica.views.HomeView;
import clinica.views.Router;

/**
 * Classe principal que deve somente iniciar a aplicação na página home.
 *
 * @author danilo
 */
public class Clinica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Router.getInstance().goToView(new HomeView());
    }

}
