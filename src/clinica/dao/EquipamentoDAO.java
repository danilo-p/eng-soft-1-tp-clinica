package clinica.dao;

import clinica.models.Especialidade;
import clinica.models.Equipamento;
import clinica.models.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para o modelo Equipamento.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class EquipamentoDAO extends GenericDAO {
	private EspecialidadeDAO especialidadeDAO;
	private AgendaDAO agendaDAO;

	public EquipamentoDAO() {
		this.especialidadeDAO = new EspecialidadeDAO();
		this.agendaDAO = new AgendaDAO();
	}

//	Salva um equipamento no banco de dados
	public void salvar(Equipamento equip) throws SQLException {
		Agenda agenda = equip.getAgenda();
		this.agendaDAO.salvar(agenda);
		String insert = "INSERT INTO equipamentos(nome, especialidade_id, agenda_id) VALUES(?,?,?)";
		int id = save(insert, equip.getNome(), equip.getEspecialidade().getId(), agenda.getId());
		if (id > 0) {
			equip.setId(id);
		}
	}

//	Lista todos os equipamentos salvos no banco de dados
	public List<Equipamento> findEquipamentos() throws SQLException {
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();

		String select = "SELECT * FROM equipamentos";

		Connection connection = getConnection();

		PreparedStatement stmt = connection.prepareStatement(select);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Equipamento equip = new Equipamento();

			equip.setId(rs.getInt("id"));
			equip.setNome(rs.getString("nome"));

			int especialidadeId = rs.getInt("especialidade_id");
			Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
			equip.setEspecialidade(especialidade);

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			equip.setAgenda(agenda);

			equipamentos.add(equip);
		}

		rs.close();
		stmt.close();
		connection.close();

		return equipamentos;
	}

//	Encontra um equipamento no banco de dados
	public Equipamento findById(int id) throws SQLException {
		String select = "SELECT * FROM equipamentos WHERE id = ?";
		Equipamento equip = null;
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			equip = new Equipamento();

			equip.setId(rs.getInt("id"));
			equip.setNome(rs.getString("nome"));

			int especialidadeId = rs.getInt("especialidade_id");
			Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
			equip.setEspecialidade(especialidade);

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			equip.setAgenda(agenda);
		}

		rs.close();
		stmt.close();
		connection.close();

		return equip;
	}

//	Pesquisa equipamentos para uma dada especialidade
	public List<Equipamento> findByEspecialidade(Especialidade especialidade) throws SQLException {
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();

		String select = "SELECT * FROM equipamentos WHERE especialidade_id = ?";
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(select);

		stmt.setInt(1, especialidade.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Equipamento equip = new Equipamento();

			equip.setId(rs.getInt("id"));
			equip.setNome(rs.getString("nome"));

			int especialidadeId = rs.getInt("especialidade_id");
			Especialidade espc = this.especialidadeDAO.findById(especialidadeId);
			equip.setEspecialidade(espc);

			int agendaId = rs.getInt("agenda_id");
			Agenda agenda = this.agendaDAO.findById(agendaId);
			equip.setAgenda(agenda);

			equipamentos.add(equip);
		}

		rs.close();
		stmt.close();
		connection.close();

		return equipamentos;
	}
}
