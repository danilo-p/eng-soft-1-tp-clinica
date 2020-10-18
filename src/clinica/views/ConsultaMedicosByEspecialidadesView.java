package clinica.views;

import java.awt.BorderLayout;
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
 * View para consultar medicos por uma especialidade.
 */
public class ConsultaMedicosByEspecialidadesView extends JPanel {

	private static final long serialVersionUID = 1L;

	public ConsultaMedicosByEspecialidadesView(int id) {
//		Layout da view
		{
			this.setBorder(new EmptyBorder(15, 15, 15, 15));
			this.setLayout(new BorderLayout(15, 15));
		}

//		Cabeçalho da view
		{
			JPanel tituloFieldPanel = new JPanel();
			JLabel tituloLabel = new JLabel("Selecione o médico:");
			tituloFieldPanel.add(tituloLabel);
			this.add(tituloFieldPanel, BorderLayout.NORTH);
		}

//		Corpo da view
		{
			MedicosController medicosController = new MedicosController();
			List<Medico> medicos = medicosController.getMedicosByEspecialidadeId(id);

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

//		Rodapé da view
		{
			JButton voltarButton = new JButton("Voltar");
			voltarButton.addActionListener((ActionEvent e) -> {
				Router.getInstance().goToView(new CadastroConsultaView());
			});
			this.add(voltarButton, BorderLayout.SOUTH);
		}
	}
}
