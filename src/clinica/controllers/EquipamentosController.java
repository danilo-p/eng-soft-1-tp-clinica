package clinica.controllers;

import clinica.dao.EquipamentoDAO;
import clinica.models.Agenda;
import clinica.models.Especialidade;
import clinica.models.Equipamento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Regras de negócio para operações relacionadas a equipamentos.
 */
public class EquipamentosController {

	private final EquipamentoDAO equipamentoDAO;

	public EquipamentosController() {
		this.equipamentoDAO = new EquipamentoDAO();
	}

//	Cria um novo equipamento
	public void criarEquipamento(String nome, Especialidade especialidade) {
		Equipamento novoEquipamento = new Equipamento(nome, especialidade);

		Agenda novaAgenda = new Agenda();
		novoEquipamento.setAgenda(novaAgenda);

		try {
			this.equipamentoDAO.salvar(novoEquipamento);
		} catch (SQLException ex) {
			System.out.println("EquipamentosController: Falha ao salvar equipamento.");
			System.out.println(ex);
		}
	}

//	Lista todos os equipamentos cadastrados
	public List<Equipamento> getEquipamentos() {
		try {
			return this.equipamentoDAO.findEquipamentos();
		} catch (SQLException ex) {
			System.out.println("EquipamentosController: Falha ao recuperar equipamentos.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}
}
