/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.controllers;

import clinica.dao.EquipamentoDAO;
import clinica.models.Agenda;
import clinica.models.Especialidade;
import clinica.models.Equipamento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardomelgaco
 */
public class EquipamentosController {

	private final EquipamentoDAO equipamentoDAO;

	public EquipamentosController() {
		this.equipamentoDAO = new EquipamentoDAO();
	}

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
