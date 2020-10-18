package clinica.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clinica.controllers.EspecialidadesController;
import clinica.models.Especialidade;

/**
 * View para o cadastro de uma consulta.
 */
public class CadastroConsultaView extends JPanel {

	private static final long serialVersionUID = 1L;

	int especialidadeId;

	public CadastroConsultaView() {
//		Layout da view
		{
			this.setBorder(new EmptyBorder(15, 15, 15, 15));
			this.setLayout(new BorderLayout(15, 15));
		}

//		Corpo da view
		{
			// Cria o combobox com as especialidades a serem escolhidas.
			JPanel especialidadeFieldPanel = new JPanel();
			JLabel especialidadeLabel = new JLabel("Filtrar médicos por especialidade:");
			especialidadeFieldPanel.add(especialidadeLabel);
			JComboBox<String> especialidadeComboBox = new JComboBox<String>();
			especialidadeFieldPanel.add(especialidadeComboBox);

			// Preenche o combobox com as especialidades existentes no banco de dados.
			EspecialidadesController especialidadesController = new EspecialidadesController();
			List<Especialidade> especialidades = especialidadesController.getEspecialidades();
			for (int i = 0; i < especialidades.size(); i++) {
				Especialidade especialidade = especialidades.get(i);
				especialidadeComboBox.addItem(especialidade.getNome());
			}

			especialidadeComboBox.addActionListener((ActionEvent e) -> {

				String textoComboBox = (String) especialidadeComboBox.getSelectedItem();

				Especialidade especialidade = especialidadesController.getEspecialidadesByName(textoComboBox);

				especialidadeId = especialidade.getId();

			});

			// Somente pra buscar a especialidade que ja vem "selecionada" no ComboBox
			String textoComboBox = (String) especialidadeComboBox.getSelectedItem();
			Especialidade especialidade = especialidadesController.getEspecialidadesByName(textoComboBox);
			especialidadeId = especialidade.getId();

			JPanel botoesFieldPanel = new JPanel();
			JButton voltarButton = new JButton("Voltar");
			voltarButton.addActionListener((ActionEvent e) -> {
				Router.getInstance().goToView(new HomeView());
			});
			botoesFieldPanel.add(voltarButton);

			JButton BuscarButton = new JButton("Buscar");
			BuscarButton.addActionListener((ActionEvent e) -> {
				Router.getInstance().goToView(new ConsultaMedicosByEspecialidadesView(especialidadeId));
			});
			botoesFieldPanel.add(BuscarButton);

			especialidadeFieldPanel.add(botoesFieldPanel);
			this.add(especialidadeFieldPanel);
		}
	}

}
