package clinica.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import clinica.controllers.ClientesController;
import clinica.models.Cliente;

/**
 * View para a listagem de clientes.
 */
public class ClientesView extends JPanel {

	private static final long serialVersionUID = 1L;

	public ClientesView() {
		this.setViewLayout();
		this.addViewHeader();
		this.addViewBody();
		this.addViewFooter();
	}

//	Configura o layout da view
	private void setViewLayout() {
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(new BorderLayout(15, 15));
	}

//	Adiciona cabeçalho da view
	private void addViewHeader() {
		JPanel tituloPanel = new JPanel();
		tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JLabel titulo = new JLabel("Clientes");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		tituloPanel.add(titulo);

		JButton novoButton = new JButton("Novo");
		novoButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new CadastroClienteView());
		});
		tituloPanel.add(novoButton);
		this.add(tituloPanel, BorderLayout.NORTH);
	}

//	Adiciona corpo da view
	private void addViewBody() {
		ClientesController clientesController = new ClientesController();
		List<Cliente> clientes = clientesController.getClientes();

		String[] titulos = { "ID", "Nome", "CPF", "Telefone" };
		Object[][] linhas = new Object[clientes.size()][4];
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			linhas[i][0] = cliente.getId();
			linhas[i][1] = cliente.getNome();
			linhas[i][2] = cliente.getCpf();
			linhas[i][3] = cliente.getTelefone();
		}

		JTable clientesTable = new JTable(linhas, titulos);
		clientesTable.setDefaultEditor(Object.class, null);
		clientesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Cliente clienteSelecionado = clientes.get(table.getSelectedRow());
					Router.getInstance().goToView(new AgendaClienteView(clienteSelecionado));
					// TODO: Redirecionar para a página do cliente quando tivermos uma
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(clientesTable);
		clientesTable.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);
	}

//	Adiciona rodapé da view
	private void addViewFooter() {
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new HomeView());
		});
		this.add(voltarButton, BorderLayout.SOUTH);
	}
}
