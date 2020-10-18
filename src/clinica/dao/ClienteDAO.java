package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clinica.models.Agenda;
import clinica.models.Cliente;

/**
 * Data Access Object para o modelo Cliente.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class ClienteDAO extends GenericDAO {

	private final PessoaDAO pessoaDAO;
	private final AgendaDAO agendaDAO;

	public ClienteDAO() {
		this.pessoaDAO = new PessoaDAO();
		this.agendaDAO = new AgendaDAO();
	}

//	Salva o cliente no banco de dados
	public void salvar(Cliente cliente) throws SQLException {
		this.pessoaDAO.salvar(cliente);
	}

//	Lista todos os clientes cadastrados no banco de dados
	public List<Cliente> findClientes() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();

		String select = "SELECT * FROM pessoas WHERE tipo = 0";

		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Cliente cliente = new Cliente();

			cliente.setId(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setTipo(rs.getInt("tipo"));

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			cliente.setAgenda(agenda);

			clientes.add(cliente);
		}

		rs.close();
		stmt.close();
		connection.close();

		return clientes;
	}

//	Encontra o cliente para o dado id no banco de dados
	public Cliente findById(int id) throws SQLException {
		String select = "SELECT * FROM pessoas WHERE id = ? AND tipo = 0";
		Cliente cliente = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cliente = new Cliente();

			cliente.setId(rs.getInt("id"));
			cliente.setId(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setTipo(rs.getInt("tipo"));

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			cliente.setAgenda(agenda);
		}

		rs.close();
		stmt.close();
		connection.close();

		return cliente;
	}

//	Encontra clientes por um nome no banco de dados
	public Cliente findByName(String nome) throws SQLException {
		String select = "SELECT * FROM pessoas WHERE nome = ? AND tipo = 0";
		Cliente cliente = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cliente = new Cliente();

			cliente.setId(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setTipo(rs.getInt("tipo"));

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			cliente.setAgenda(agenda);
		}

		rs.close();
		stmt.close();
		connection.close();

		return cliente;
	}
}
