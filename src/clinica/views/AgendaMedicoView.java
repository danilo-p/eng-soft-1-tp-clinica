package clinica.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import clinica.controllers.ConsultasController;
import clinica.models.Cliente;
import clinica.models.Consulta;
import clinica.models.Medico;

/**
 * View para a agenda de um medico.
 */
public class AgendaMedicoView extends JPanel {

	private static final long serialVersionUID = 1L;

	public AgendaMedicoView(Medico medico, JPanel voltarView, int controleSemana) {
		this.setViewLayout();
		this.addViewHeader(medico, voltarView, controleSemana);
		this.addViewBody(medico, controleSemana);
		this.addViewFooter(voltarView);
	}

//	Layout da view
	private void setViewLayout() {
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(new BorderLayout(15, 15));
	}

//	Adiciona cabeçalho da view
	private void addViewHeader(Medico medico, JPanel voltarView, int controleSemana) {
		JPanel tituloPanel = new JPanel();
		tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JLabel titulo = new JLabel("Agenda " + medico.getNome());
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		tituloPanel.add(titulo);

		JPanel controlesPanel = new JPanel();
		controlesPanel.setLayout(new GridLayout(1, 2, 0, 0));
		JButton semanaAnterior = new JButton("Semana ant.");
		semanaAnterior.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new AgendaMedicoView(medico, voltarView, controleSemana - 1));
		});
		controlesPanel.add(semanaAnterior);
		JButton proximaSemana = new JButton("Próx. semana");
		proximaSemana.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new AgendaMedicoView(medico, voltarView, controleSemana + 1));
		});
		controlesPanel.add(proximaSemana);
		tituloPanel.add(controlesPanel);

		this.add(tituloPanel, BorderLayout.NORTH);
	}

//	Adiciona corpo da view
	private void addViewBody(Medico medico, int controleSemana) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.add(Calendar.WEEK_OF_MONTH, controleSemana);

		String aux = "Horário";
		String[] cargaHoraria = medico.getAgenda().getCargaHoraria().split(",");
		int[] cargaHorariaInteiros = new int[cargaHoraria.length];
		for (int i = 0; i < cargaHorariaInteiros.length; i++) {
			cargaHorariaInteiros[i] = Integer.parseInt(cargaHoraria[i]);
		}
		for (int dia : cargaHorariaInteiros) {
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.add(Calendar.DATE, dia);
			String diaNome = "";
			switch (dia) {
			case 1:
				diaNome = "Seg. ";
				break;
			case 2:
				diaNome = "Ter. ";
				break;
			case 3:
				diaNome = "Qua. ";
				break;
			case 4:
				diaNome = "Qui. ";
				break;
			case 5:
				diaNome = "Sex. ";
				break;
			default:
				diaNome = "Dia inválido ";
				break;
			}
			aux += "," + diaNome + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
		}
		String[] titulos = aux.split(",");

		int intervalo = medico.getAgenda().getTempoIntervalo();
		int n_linhas = (60 / intervalo) * 6;
		Object[][] dados = new Object[n_linhas][4];
		dados[0][0] = medico.getAgenda().getHoraInicio();
		for (int j = 1; j < n_linhas; j++) {
			dados[j][0] = new Time(medico.getAgenda().getHoraInicio().getTime() + intervalo * j * 60 * 1000);
		}

		ConsultasController consultasController = new ConsultasController();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, cargaHorariaInteiros[0]);
		Timestamp cargaHorariaInicio = new Timestamp(cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, cargaHorariaInteiros[cargaHorariaInteiros.length - 1] + 1);
		Timestamp cargaHorariaFim = new Timestamp(cal.getTimeInMillis());
		List<Consulta> consultas = consultasController.getByMedico(medico, cargaHorariaInicio, cargaHorariaFim);
		for (int i = 0; i < consultas.size(); i++) {
			Consulta consulta = consultas.get(i);
			Timestamp dataConsulta = consulta.getData();

			cal.setTimeInMillis(dataConsulta.getTime());
			int diaSemanaConsultaIndice = cal.get(Calendar.DAY_OF_WEEK) - 1;

			int horaConsultaIndice = 0;
			for (int j = 1; j < n_linhas; j++) {
				String horaConsulta = new Time(dataConsulta.getTime()).toString();
				String horaDados = dados[j][0].toString();
				if (horaConsulta.equals(horaDados)) {
					horaConsultaIndice = j;
				}
			}

			if (diaSemanaConsultaIndice > 0 && horaConsultaIndice >= 0) {
				Cliente cliente = consulta.getCliente();
				dados[horaConsultaIndice][diaSemanaConsultaIndice] = cliente.getNome() + " - " + cliente.getTelefone();
			}
		}

		JTable medicosTable = new JTable(dados, titulos);
		medicosTable.setDefaultEditor(Object.class, null);
		medicosTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				if (mouseEvent.getClickCount() == 2 && linha >= 0 && coluna > 0) {
					cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
					cal.add(Calendar.DATE, cargaHorariaInteiros[coluna - 1]);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dataNovaConsulta = sdf.format(cal.getTime()) + " " + dados[linha][0];
					Router.getInstance().goToView(new ConfirmaConsultaView(medico, dataNovaConsulta));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(medicosTable);
		medicosTable.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);
	}

//	Adiciona o rodapé da view
	private void addViewFooter(JPanel voltarView) {
		JPanel rodapePanel = new JPanel();
		rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(voltarView);
		});
		rodapePanel.add(voltarButton);
		this.add(rodapePanel, BorderLayout.SOUTH);
	}
}
