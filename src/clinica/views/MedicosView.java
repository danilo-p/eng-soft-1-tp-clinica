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

import clinica.controllers.MedicosController;
import clinica.models.Medico;

/**
 * View para listagem de medicos.
 */
public class MedicosView extends JPanel {

	private static final long serialVersionUID = 1L;

	public MedicosView() {
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
		JPanel tituloPanel = new JPanel();
		tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JLabel titulo = new JLabel("Médicos");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		tituloPanel.add(titulo);

		JButton novoButton = new JButton("Novo");
		novoButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new CadastroMedicoView());
		});
		tituloPanel.add(novoButton);

		this.add(tituloPanel, BorderLayout.NORTH);
	}

//	Adiciona corpo da view
	private void addViewBody() {
		MedicosController medicosController = new MedicosController();
		List<Medico> medicos = medicosController.getMedicos();

		String[] titulos = { "ID", "Nome", "CPF", "Telefone", "Especialidade" };
		Object[][] linhas = new Object[medicos.size()][5];
		for (int i = 0; i < medicos.size(); i++) {
			Medico medico = medicos.get(i);
			linhas[i][0] = medico.getId();
			linhas[i][1] = medico.getNome();
			linhas[i][2] = medico.getCpf();
			linhas[i][3] = medico.getTelefone();
			linhas[i][4] = medico.getEspecialidade().getNome();
		}

		JTable medicosTable = new JTable(linhas, titulos);
		medicosTable.setDefaultEditor(Object.class, null);
		JPanel voltarView = this;
		medicosTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Medico medicoSelecionado = medicos.get(table.getSelectedRow());
					Router.getInstance().goToView(new AgendaMedicoView(medicoSelecionado, voltarView, 0));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(medicosTable);
		medicosTable.setFillsViewportHeight(true);
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
