package clinica.dao;

import clinica.models.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para o modelo Especialidade.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class EspecialidadeDAO extends GenericDAO {

//	Salva uma nova especialidade no banco de dados
	public void salvar(Especialidade especialidade) throws SQLException {
		String insert = "INSERT INTO especialidades(nome) VALUES(?)";
		int id = save(insert, especialidade.getNome());
		if (id > 0) {
			especialidade.setId(id);
		}
	}

//	Altera a especialidade no banco de dados
	public void alterar(Especialidade especialidade) throws SQLException {
		String update = "UPDATE especialidades SET nome = ? WHERE id = ?";
		update(update, especialidade.getId(), especialidade.getNome());
	}

//	Encontra uma especialidade para o id
	public Especialidade findById(int id) throws SQLException {
		String select = "SELECT * FROM especialidades WHERE id = ?";
		Especialidade especialidade = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			especialidade = new Especialidade();
			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));
		}

		rs.close();
		stmt.close();
		connection.close();

		return especialidade;
	}

//	Pesquisa especialidades por nome
	public Especialidade findByName(String nome) throws SQLException {
		String select = "SELECT * FROM especialidades WHERE nome = ?";
		Especialidade especialidade = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			especialidade = new Especialidade();
			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));
		}

		rs.close();
		stmt.close();
		connection.close();

		return especialidade;
	}

//	Lista todas as especialidades cadastradas no banco de dados
	public List<Especialidade> findEspecialidades() throws SQLException {

		List<Especialidade> listEspecialidades = new ArrayList<Especialidade>();

		String select = "SELECT * FROM especialidades";

		Connection connection = getConnection();

		PreparedStatement stmt = connection.prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Especialidade especialidade = new Especialidade();

			especialidade.setId(rs.getInt("id"));
			especialidade.setNome(rs.getString("nome"));

			listEspecialidades.add(especialidade);
		}

		rs.close();
		stmt.close();
		connection.close();

		return listEspecialidades;
	}

}
