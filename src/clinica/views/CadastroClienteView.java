package clinica.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clinica.controllers.ClientesController;

/**
 * View para o cadastro de um cliente.
 */
public class CadastroClienteView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField nomeField;
	private JTextField cpfField;
	private JTextField telefoneField;
	private JLabel cpfError;
	private JLabel telefoneError;
	private JLabel nomeError;

	public CadastroClienteView() {
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

//	Adiciona o cabeçalho da view
	private void addViewHeader() {
		JLabel titulo = new JLabel("Novo cliente");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(titulo, BorderLayout.NORTH);
	}

//	Adiciona o corpo da view
	private void addViewBody() {
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

		JPanel nomeFieldPanel = new JPanel();
		JLabel nomeLabel = new JLabel("Nome completo:");
		nomeFieldPanel.add(nomeLabel);
		this.nomeField = new JTextField(1);
		this.nomeField.setColumns(20);
		nomeFieldPanel.add(this.nomeField);
		this.nomeError = new JLabel("Insira o nome completo do paciente");
		this.nomeError.setForeground(Color.red);
		this.nomeError.setVisible(false);
		nomeFieldPanel.add(this.nomeError);
		fieldsPanel.add(nomeFieldPanel);

		JPanel cpfFieldPanel = new JPanel();
		JLabel cpfLabel = new JLabel("CPF:");
		cpfFieldPanel.add(cpfLabel);
		this.cpfField = new JTextField(1);
		this.cpfField.setColumns(20);
		cpfFieldPanel.add(this.cpfField);
		this.cpfError = new JLabel("Insira um CPF válido no formato XXX.XXX.XXX-XX");
		this.cpfError.setForeground(Color.red);
		this.cpfError.setVisible(false);
		cpfFieldPanel.add(this.cpfError);
		fieldsPanel.add(cpfFieldPanel);

		JPanel telefoneFieldPanel = new JPanel();
		JLabel telefoneLabel = new JLabel("Telefone:");
		telefoneFieldPanel.add(telefoneLabel);
		this.telefoneField = new JTextField(1);
		this.telefoneField.setColumns(20);
		telefoneFieldPanel.add(this.telefoneField);
		this.telefoneError = new JLabel("Insira o telefone no formato (XX) XXXXX-XXXX");
		this.telefoneError.setForeground(Color.red);
		this.telefoneError.setVisible(false);
		telefoneFieldPanel.add(this.telefoneError);
		fieldsPanel.add(telefoneFieldPanel);

		this.add(fieldsPanel, BorderLayout.CENTER);
	}

//	Adiciona o rodapé da view
	private void addViewFooter() {
		JPanel rodapePanel = new JPanel();
		rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new ClientesView());
		});
		rodapePanel.add(voltarButton);

		ClientesController clientesController = new ClientesController();
		JButton cadastrarButton = new JButton("Confirmar");
		cadastrarButton.addActionListener((ActionEvent e) -> {
			if (this.validateFields()) {
				clientesController.criarCliente(this.nomeField.getText(), this.cpfField.getText(),
						this.telefoneField.getText());
				Router.getInstance().goToView(new ClientesView());
			}
		});
		rodapePanel.add(cadastrarButton);

		this.add(rodapePanel, BorderLayout.SOUTH);
	}

//	Valida todos os campos
	private boolean validateFields() {
		return this.validateNomeField() & this.validateCpfField() & this.validateTelefoneField();
	}

//	Valida o campo de nome do cliente
	private boolean validateNomeField() {
		String nome = this.nomeField.getText();

		if (nome.length() > 0) {
			this.nomeError.setVisible(false);
			return true;
		}

		this.nomeError.setVisible(true);
		return false;
	}

//	Valida o campo de CPF do cliente
	private boolean validateCpfField() {
		String cpf = this.cpfField.getText();

		if (cpf.matches("[\\d]{3}[\\.][\\d]{3}[\\.][\\d]{3}[\\-][\\d]{2}")) {
			this.cpfError.setVisible(false);
			return true;
		}

		this.cpfError.setVisible(true);
		return false;
	}

//	Valida o campo de telefone do cliente
	private boolean validateTelefoneField() {
		String telefone = this.telefoneField.getText();

		if (telefone.matches("[\\(][\\d]{2}[\\)][\\s][\\d]{5}[\\-][\\d]{4}")) {
			this.telefoneError.setVisible(false);
			return true;
		}

		this.telefoneError.setVisible(true);
		return false;
	}
}
