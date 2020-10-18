/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.controllers;

import clinica.dao.EspecialidadeDAO;
import clinica.models.Especialidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Regras de negócio para operações relacionadas a especialidades.
 */
public class EspecialidadesController {

	private final EspecialidadeDAO especialidadeDAO;

	public EspecialidadesController() {
		this.especialidadeDAO = new EspecialidadeDAO();
	}

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

	public List<Especialidade> getEspecialidades() {
		try {
			return this.especialidadeDAO.findEspecialidades();
		} catch (SQLException ex) {
			System.out.println("EspecialidadesController: Falha ao recuperar especialidades.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

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
