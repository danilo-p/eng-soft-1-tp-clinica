package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import clinica.models.Cliente;
import clinica.models.Consulta;
import clinica.models.Medico;

/**
 * Data Access Object para o modelo Consulta.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class ConsultaDAO extends GenericDAO {
	private final MedicoDAO medicoDAO;
	private final ClienteDAO clienteDAO;

	public ConsultaDAO() {
		this.medicoDAO = new MedicoDAO();
		this.clienteDAO = new ClienteDAO();
	}

//	Salva uma nova consulta no banco de dados
	public void salvar(Consulta consulta) throws SQLException {
		String insert = "INSERT INTO consultas(data, medico_id, cliente_id) VALUES(?, ?, ?)";
		int id = save(insert, consulta.getData(), consulta.getMedico().getId(), consulta.getCliente().getId());
		if (id > 0) {
			consulta.setId(id);
		}
	}

//	Altera a dada consulta no banco de dados
	public void alterar(Consulta consulta) throws SQLException {
		String update = "UPDATE consultas SET data = ?, medico_id = ?, cliente_id = ? WHERE id = ?";
		update(update, consulta.getData(), consulta.getMedico().getId(), consulta.getCliente().getId());
	}

//	Pesquisa consultas para um dado cliente
	public List<Consulta> findByCliente(Cliente cliente) throws SQLException {
		List<Consulta> consultas = new ArrayList<Consulta>();

		String select = "SELECT * FROM consultas WHERE cliente_id = ?";
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, cliente.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setId(rs.getInt("id"));
			consulta.setData(rs.getTimestamp("data"));
			consulta.setCliente(cliente);

			int medicoId = rs.getInt("medico_id");
			Medico medico = this.medicoDAO.findById(medicoId);
			consulta.setMedico(medico);

			consultas.add(consulta);
		}

		rs.close();
		stmt.close();
		connection.close();

		return consultas;
	}

//	Pesquisa consultas para um dado medico
	public List<Consulta> findByMedico(Medico medico, Timestamp inicio, Timestamp fim) throws SQLException {
		List<Consulta> consultas = new ArrayList<Consulta>();

		String select = "SELECT * FROM consultas WHERE medico_id = ? AND data BETWEEN ? AND ?";
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, medico.getId());
		stmt.setTimestamp(2, inicio);
		stmt.setTimestamp(3, fim);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Consulta consulta = new Consulta();
			consulta.setId(rs.getInt("id"));
			consulta.setData(rs.getTimestamp("data"));
			consulta.setMedico(medico);

			int clienteId = rs.getInt("cliente_id");
			Cliente cliente = this.clienteDAO.findById(clienteId);
			consulta.setCliente(cliente);

			consultas.add(consulta);
		}

		rs.close();
		stmt.close();
		connection.close();

		return consultas;
	}
}
