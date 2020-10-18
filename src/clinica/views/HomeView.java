package clinica.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * View para a página inicial.
 */
public class HomeView extends JPanel {

	private static final long serialVersionUID = 1L;

	public HomeView() {
		this.setViewLayout();
		this.addViewBody();
	}

//	Configura layout da view
	private void setViewLayout() {
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(new GridLayout(2, 2, 15, 15));
	}

//	Adiciona corpo da view
	private void addViewBody() {
		JButton medicosButton = new JButton("Médicos");
		medicosButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new MedicosView());
		});
		this.add(medicosButton);

		JButton clientesButton = new JButton("Clientes");
		clientesButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new ClientesView());
		});
		this.add(clientesButton);

		JButton novaConsultaButton = new JButton("Nova Consulta");
		novaConsultaButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new CadastroConsultaView());
		});
		this.add(novaConsultaButton);

		this.add(new JButton("Novo Exame"));
	}
}
