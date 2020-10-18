package clinica.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clinica.dao.EspecialidadeDAO;
import clinica.models.Especialidade;

/**
 * Regras de negócio para operações relacionadas a especialidades.
 */
public class EspecialidadesController {

	private final EspecialidadeDAO especialidadeDAO;

	public EspecialidadesController() {
		this.especialidadeDAO = new EspecialidadeDAO();
	}

//	Cria uma nova especialidade
	public void criarEspecialidade(String nome) {
		Especialidade novaEspecialidade = new Especialidade();

		novaEspecialidade.setNome(nome);

		try {
			this.especialidadeDAO.salvar(novaEspecialidade);
		} catch (SQLException ex) {
			System.out.println("EspecialidadesController: Falha ao salvar especialidades.");
			System.out.println(ex);
		}
	}

//	Lista todas as especialidades cadastradas
	public List<Especialidade> getEspecialidades() {
		try {
			return this.especialidadeDAO.findEspecialidades();
		} catch (SQLException ex) {
			System.out.println("EspecialidadesController: Falha ao recuperar especialidades.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

//	Pesquisa especialidades por nome
	public Especialidade getEspecialidadesByName(String nome) {
		try {
			return this.especialidadeDAO.findByName(nome);
		} catch (SQLException ex) {
			System.out.println("EspecialidadesController: Falha ao recuperar especialidades.");
			System.out.println(ex);
			return new Especialidade();
		}
	}

}
