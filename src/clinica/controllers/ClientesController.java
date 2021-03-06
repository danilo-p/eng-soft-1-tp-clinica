package clinica.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clinica.dao.ClienteDAO;
import clinica.models.Agenda;
import clinica.models.Cliente;

/**
 * Regras de negócio para operações relacionadas a clientes.
 */
public class ClientesController {

	private final ClienteDAO clienteDAO;

	public ClientesController() {
		this.clienteDAO = new ClienteDAO();
	}

//	Cadastra um novo cliente
	public void criarCliente(String nome, String cpf, String telefone) {
		Cliente novoCliente = new Cliente();

		novoCliente.setNome(nome);
		novoCliente.setCpf(cpf);
		novoCliente.setTelefone(telefone);

		Agenda novaAgenda = new Agenda();
		novoCliente.setAgenda(novaAgenda);

		try {
			this.clienteDAO.salvar(novoCliente);
		} catch (SQLException ex) {
			System.out.println("ClientesController: Falha ao salvar cliente.");
			System.out.println(ex);
		}
	}

//	Lista os clientes cadastrados
	public List<Cliente> getClientes() {
		try {
			return this.clienteDAO.findClientes();
		} catch (SQLException ex) {
			System.out.println("ClientesController: Falha ao recuperar clientes.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

//	Pesquisa clientes por nome
	public Cliente getClienteByName(String nome) {
		try {
			return this.clienteDAO.findByName(nome);
		} catch (SQLException ex) {
			System.out.println("ClientesController: Falha ao recuperar cliente.");
			System.out.println(ex);
			return new Cliente();
		}
	}
}
