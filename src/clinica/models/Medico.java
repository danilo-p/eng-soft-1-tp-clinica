package clinica.models;

/**
 * Modelo para medicos.
 */
public class Medico extends Pessoa {

	protected Especialidade especialidade;
	protected Agenda cargaHoraria;
	protected Agenda horaInicio;
	protected Agenda horaFim;
	protected Agenda tempoIntervalo;

	public Medico() {
		super();
	}

	public Medico(Especialidade especialidade) {
		super();
		this.especialidade = especialidade;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
