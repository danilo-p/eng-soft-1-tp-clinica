package clinica.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinica.controllers.ClientesController;
import clinica.controllers.ConsultasController;
import clinica.controllers.PagamentosController;
import clinica.models.Cliente;
import clinica.models.Consulta;
import clinica.models.Medico;

/**
 * View para confirmação de cadastro de consulta.
 */
public class ConfirmaConsultaView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private String textoClienteComboBox, textoPagamentoComboBox, textoMatriculaConveniado;
	private int tipoPagamento;
	private JTextField matriculaField, valorField;
	private JComboBox<String> pagamentoComboBox;

	public ConfirmaConsultaView(Medico medico, String dataHoraConsulta) {
		this.addViewBody();
		this.addViewFooter(medico, dataHoraConsulta);
	}

//	Adiciona corpo da view retornando o combobox de pagamento pra uso no rodape
	private void addViewBody() {
		JPanel clienteFieldPanel = new JPanel();
		JLabel ClienteLabel = new JLabel("Selecione o cliente:");
		clienteFieldPanel.add(ClienteLabel);
		JComboBox<String> clienteComboBox = new JComboBox<String>();
		clienteFieldPanel.add(clienteComboBox);

		ClientesController clientesController = new ClientesController();
		List<Cliente> clientes = clientesController.getClientes();
		for (int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			clienteComboBox.addItem(cliente.getNome());
		}
		this.add(clienteFieldPanel, BorderLayout.NORTH);

//		Recupera texto do combobox de clientes e gera uma variavel com os dados daquele cliente.
		cliente = clientes.get(0);
		clienteComboBox.addActionListener((ActionEvent e) -> {
			textoClienteComboBox = (String) clienteComboBox.getSelectedItem();
			cliente = clientesController.getClienteByName(textoClienteComboBox);
		});

		JPanel tipoFieldPanel = new JPanel();
		JLabel PagamentoLabel = new JLabel("Selecione o tipo de pagamento:");
		tipoFieldPanel.add(PagamentoLabel);
		JComboBox<String> tipoPagamentoComboBox = new JComboBox<String>();
		tipoFieldPanel.add(tipoPagamentoComboBox);

		this.add(tipoFieldPanel, BorderLayout.CENTER);

		tipoPagamentoComboBox.addItem("Particular");
		tipoPagamentoComboBox.addItem("Convenio");

		JPanel escolhaFieldPanel = new JPanel();
		JLabel PagamentoLabel2 = new JLabel("Selecione a forma de pagamento/convenio:");
		escolhaFieldPanel.add(PagamentoLabel2);
		this.pagamentoComboBox = new JComboBox<String>();
		this.pagamentoComboBox.setEnabled(false);
		escolhaFieldPanel.add(this.pagamentoComboBox);

		this.add(escolhaFieldPanel, BorderLayout.CENTER);

		JPanel matriculaFieldPanel = new JPanel();
		JLabel matriculaLabel = new JLabel("Digite a matricula do conveniado:");
		matriculaFieldPanel.add(matriculaLabel);
		matriculaField = new JTextField(1);
		matriculaField.setColumns(21);
		matriculaField.setEnabled(false);
		matriculaField.setBackground(Color.LIGHT_GRAY);
		matriculaFieldPanel.add(matriculaField);

		this.add(matriculaFieldPanel, BorderLayout.CENTER);

		JPanel valorFieldPanel = new JPanel();
		JLabel valorLabel = new JLabel("Valor (R$):");
		valorFieldPanel.add(valorLabel);
		valorField = new JTextField(1);
		valorField.setColumns(21);
		valorFieldPanel.add(valorField);

		this.add(valorFieldPanel, BorderLayout.CENTER);

		textoPagamentoComboBox = (String) tipoPagamentoComboBox.getSelectedItem();
		tipoPagamentoComboBox.addActionListener((ActionEvent e) -> {

			textoPagamentoComboBox = (String) tipoPagamentoComboBox.getSelectedItem();

			if ("Particular".equals(textoPagamentoComboBox)) {
				pagamentoComboBox.setEnabled(true);
				pagamentoComboBox.removeAllItems();
				pagamentoComboBox.addItem("Cartao Credito");
				pagamentoComboBox.addItem("Cartao Debito");
				pagamentoComboBox.addItem("Cheque");
				pagamentoComboBox.addItem("Dinheiro");

				matriculaField.setEnabled(false);
				matriculaField.setBackground(Color.LIGHT_GRAY);

//			Pagamento particular
				tipoPagamento = 0;
			} else if ("Convenio".equals(textoPagamentoComboBox)) {
				pagamentoComboBox.setEnabled(true);
				pagamentoComboBox.removeAllItems();
				pagamentoComboBox.addItem("Amil");
				pagamentoComboBox.addItem("Bradesco");
				pagamentoComboBox.addItem("Golden Cross");
				pagamentoComboBox.addItem("Promed");
				pagamentoComboBox.addItem("Unimed");

				matriculaField.setEnabled(true);
				matriculaField.setBackground(Color.WHITE);

//				Pagamento por convenio
				tipoPagamento = 1;
			} else {
				pagamentoComboBox.setEnabled(false);
			}
		});
	}

// 	Adiciona rodapé da view
	private void addViewFooter(Medico medico, String dataHoraConsulta) {
		JPanel botoesFieldPanel = new JPanel();
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new AgendaMedicoView(medico, this, 0));
		});
		botoesFieldPanel.add(voltarButton);

		JButton confirmarButton = new JButton("Confirmar");
		confirmarButton.addActionListener((ActionEvent e) -> {
			PagamentosController pagamentosController = new PagamentosController();

			int valor = Integer.parseInt(valorField.getText());
			String pagamento = (String) pagamentoComboBox.getSelectedItem();
			textoMatriculaConveniado = matriculaField.getText();

			ConsultasController consultasController = new ConsultasController();
			Consulta consulta = consultasController.criarConsulta(dataHoraConsulta, medico, cliente);

			if (tipoPagamento == 0) {
				pagamentosController.criarPagamentoParticular(valor, tipoPagamento, pagamento, consulta.getId());
			} else {
				pagamentosController.criarPagamentoConvenio(valor, tipoPagamento, pagamento, textoMatriculaConveniado,
						1);
			}

			Router.getInstance().goToView(new HomeView());
		});
		botoesFieldPanel.add(confirmarButton);
		this.add(botoesFieldPanel, BorderLayout.SOUTH);
	}

}
