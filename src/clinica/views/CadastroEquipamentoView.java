package clinica.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clinica.controllers.EquipamentosController;
import clinica.controllers.EspecialidadesController;
import clinica.dao.EspecialidadeDAO;
import clinica.models.Especialidade;

/**
 * View para o cadastro de um equipamento.
 */
public class CadastroEquipamentoView extends JPanel {
	private static final long serialVersionUID = 1L;

	EspecialidadesController especialidadesController = new EspecialidadesController();
	private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

	private JComboBox<String> especialidadeComboBox;
	private JTextField nomeField;
	private JLabel especialidadeError, nomeError;

	public CadastroEquipamentoView() {
		this.setViewLayout();
		this.addViewHeader();
		this.addViewBody();
		this.addViewFooter();
	}

//	Configura layout da view
	private void setViewLayout() {
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(new BorderLayout(15, 15));
	}

//	Adiciona cabeçalho da view
	private void addViewHeader() {
		JLabel titulo = new JLabel("Novo equipamento");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(titulo, BorderLayout.NORTH);
	}

//	Adiciona corpo da view
	private void addViewBody() {
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

		JPanel nomeFieldPanel = new JPanel();
		JLabel nomeLabel = new JLabel("Nome do equipamento:");
		nomeFieldPanel.add(nomeLabel);
		this.nomeField = new JTextField(1);
		this.nomeField.setColumns(20);
		nomeFieldPanel.add(this.nomeField);
		this.nomeError = new JLabel("Insira o nome do equipamento.");
		this.nomeError.setForeground(Color.red);
		this.nomeError.setVisible(false);
		nomeFieldPanel.add(this.nomeError);
		fieldsPanel.add(nomeFieldPanel);

		JPanel especialidadeFieldPanel = new JPanel();
		JLabel especialidadeLabel = new JLabel("Especialidade:");
		especialidadeFieldPanel.add(especialidadeLabel);
		especialidadeComboBox = new JComboBox<String>();
		especialidadeFieldPanel.add(especialidadeComboBox);
		especialidadeError = new JLabel("Selecione a especialidade que utiliza este equipamento.");
		especialidadeError.setForeground(Color.red);
		especialidadeError.setVisible(false);
		especialidadeFieldPanel.add(especialidadeError);
		fieldsPanel.add(especialidadeFieldPanel);

		List<Especialidade> especialidades = especialidadesController.getEspecialidades();
		especialidadeComboBox.addItem("");
		for (int i = 0; i < especialidades.size(); i++) {
			Especialidade especialidade = especialidades.get(i);
			especialidadeComboBox.addItem(especialidade.getNome());
		}

		this.add(fieldsPanel, BorderLayout.CENTER);
	}

//	Adiciona rodapé da view
	private void addViewFooter() {
		JPanel rodapePanel = new JPanel();
		rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new HomeView());
		});
		rodapePanel.add(voltarButton);

		EquipamentosController equipamentosController = new EquipamentosController();
		JButton cadastrarButton = new JButton("Novo");
		cadastrarButton.addActionListener((ActionEvent e) -> {
			try {
				Especialidade especialidade = this.especialidadeDAO
						.findByName((String) especialidadeComboBox.getSelectedItem());
				if (this.validateFields()) {
					equipamentosController.criarEquipamento(this.nomeField.getText(), especialidade);
					Router.getInstance().goToView(new HomeView());
				}
			} catch (SQLException ex) {
				Logger.getLogger(CadastroMedicoView.class.getName()).log(Level.SEVERE, null, ex);
			}

		});
		rodapePanel.add(cadastrarButton);

		this.add(rodapePanel, BorderLayout.SOUTH);
	}

//	Valida todos os campos
	private boolean validateFields() {
		return this.validateNomeField() & this.validateEspecialidadeComboBox();
	}

//	Valida o campo de nome do equipamento
	private boolean validateNomeField() {
		String nome = this.nomeField.getText();

		if (nome.length() > 0) {
			this.nomeError.setVisible(false);
			return true;
		}

		this.nomeError.setVisible(true);
		return false;
	}

//	Valida o campo de especialidade do equipamento
	private boolean validateEspecialidadeComboBox() {
		String especialidade = (String) this.especialidadeComboBox.getSelectedItem();

		if (especialidade.length() == 0) {
			this.especialidadeError.setText("Selecione a especialidade do médico");
			this.especialidadeError.setVisible(true);
			return false;
		}

		this.especialidadeError.setVisible(false);
		return true;
	}
}
