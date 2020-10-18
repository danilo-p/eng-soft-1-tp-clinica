package clinica.models;

/**
 * Modelo para especialidades.
 */
public class Especialidade {
	private int id;
	private String nome;

	public Especialidade() {

	}

	Especialidade(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
