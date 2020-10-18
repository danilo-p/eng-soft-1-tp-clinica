/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Roteador do aplicativo usado para mudança de views.
 *
 * @author danilo
 */
public class Router extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Router uniqueInstance = null;

	public static Router getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Router();
		}
		return uniqueInstance;
	}

	public Router() {
		super();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);

	}

	public void goToView(JPanel view) {
		this.setContentPane(view);
		this.revalidate();
	}
}
