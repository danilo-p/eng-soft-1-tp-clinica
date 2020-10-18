package clinica.controllers;

import clinica.dao.MedicoDAO;
import clinica.models.Agenda;
import clinica.models.Medico;
import clinica.models.Especialidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 * Regras de negócio para operações relacionadas a medicos.
 */
public class MedicosController {

	private final MedicoDAO medicoDAO;
	private Time horaInicio;
	private Time horaFim;

	public MedicosController() {
		this.medicoDAO = new MedicoDAO();
	}

	public void criarMedico(String nome, String cpf, String telefone, Especialidade especialidade, String horaInicial,
			int intervalo, String cargaHoraria) {
		Medico novoMedico = new Medico();

		novoMedico.setNome(nome);
		novoMedico.setCpf(cpf);
		novoMedico.setTelefone(telefone);
		novoMedico.setTipo(1);

		novoMedico.setEspecialidade(especialidade);

		Agenda novaAgenda = new Agenda();
		novoMedico.setAgenda(novaAgenda);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			this.horaInicio = new Time(sdf.parse(horaInicial).getTime());
			this.horaFim = new Time(sdf.parse(horaInicial).getTime() + 360 * 60 * 1000); // somar 6 horas ao horario de
																							// inicio
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "O horário deve ser no formato HH:mm.");
			return;
		}
		novaAgenda.setCargaHoraria(cargaHoraria);
		novaAgenda.setHoraInicio(horaInicio);
		novaAgenda.setHoraFim(horaFim);
		novaAgenda.setTempoIntervalo(intervalo);

		try {
			this.medicoDAO.salvarMedico(novoMedico);
		} catch (SQLException ex) {
			System.out.println("MedicosController: Falha ao salvar medico.");
			System.out.println(ex);
		}
	}

	public List<Medico> getMedicos() {
		try {
			return this.medicoDAO.findMedicos();
		} catch (SQLException ex) {
			System.out.println("MedicosController: Falha ao recuperar medicos.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

	public List<Medico> getMedicosByEspecialidadeId(int id) {
		try {
			return this.medicoDAO.findMedicosByEspecialidadeId(id);
		} catch (SQLException ex) {
			System.out.println("MedicosController: Falha ao recuperar medicos.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}
}
