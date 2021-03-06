package clinica.models;

/**
 * Modelo para equipamentos.
 */
public class Equipamento extends Agendavel {
	private int id;
	private String nome;
	private Especialidade especialidade;

	public Equipamento() {
		super();
	}

	public Equipamento(String nome, Especialidade especialidade) {
		super();
		this.nome = nome;
		this.especialidade = especialidade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public int getId() {
		return id;
	}
}
