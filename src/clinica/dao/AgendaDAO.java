package clinica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clinica.models.Agenda;

/**
 * Data Access Object para o modelo Agenda.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class AgendaDAO extends GenericDAO {

//	Salva a agenda no banco de dados
	public void salvar(Agenda agenda) throws SQLException {
		String insert = "INSERT INTO agendas(carga_horaria, hora_inicio, hora_fim, tempo_intervalo) VALUES(?, ?, ?, ?)";
		int id = save(insert, agenda.getCargaHoraria(), agenda.getHoraInicio(), agenda.getHoraFim(),
				agenda.getTempoIntervalo());
		if (id > 0) {
			agenda.setId(id);
		}
	}

//	Altera a agenda no banco de dados
	public void alterar(Agenda agenda) throws SQLException {
		String update = "UPDATE agendas SET carga_horaria = ?, hora_inicio = ?, hora_fim = ?, tempo_intervalo = ? WHERE id = ?";
		update(update, agenda.getId(), agenda.getCargaHoraria(), agenda.getHoraInicio(), agenda.getHoraFim(),
				agenda.getTempoIntervalo());
	}

//	Encontra a agenda para o dado id no banco de dados
	public Agenda findById(int id) throws SQLException {
		String select = "SELECT * FROM agendas WHERE id = ?";
		Agenda agenda = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			agenda = new Agenda();
			agenda.setId(rs.getInt("id"));
			agenda.setCargaHoraria(rs.getString("carga_horaria"));
			agenda.setHoraInicio(rs.getTime("hora_inicio"));
			agenda.setHoraFim(rs.getTime("hora_fim"));
			agenda.setTempoIntervalo(rs.getInt("tempo_intervalo"));
		}

		rs.close();
		stmt.close();
		connection.close();

		return agenda;
	}
}
