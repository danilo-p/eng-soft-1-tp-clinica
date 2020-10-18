package clinica.models;

/**
 * Abstração de entidades que são agendáveis. Se ela é agendável, então ela tem
 * uma agenda.
 */
public abstract class Agendavel {

	protected Agenda agenda;

	public Agendavel() {
	}

	public Agendavel(Agenda agenda) {
		this.agenda = agenda;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
}
