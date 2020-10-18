package clinica.models;

/**
 * Modelo para medicos.
 */
public class Medico extends Pessoa {

	private Especialidade especialidade;

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
