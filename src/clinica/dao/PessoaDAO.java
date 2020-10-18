package clinica.dao;

import clinica.models.Agenda;
import clinica.models.Pessoa;
import java.sql.SQLException;

/**
 * Data Access Object para o modelo Pessoa.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class PessoaDAO extends GenericDAO {

	private AgendaDAO agendaDAO;

	public PessoaDAO() {
		this.agendaDAO = new AgendaDAO();
	}

//	Salva uma pessoa no banco de dados
	public void salvar(Pessoa pessoa) throws SQLException {
		Agenda agenda = pessoa.getAgenda();
		this.agendaDAO.salvar(agenda);
		String insert = "INSERT INTO pessoas(nome, cpf, telefone, tipo, agenda_id) VALUES(?,?,?,?,?)";
		int id = save(insert, pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getTipo(),
				agenda.getId());
		if (id > 0) {
			pessoa.setId(id);
		}
	}

//	Altear uma pessoa no banco de dado
	public void alterar(Pessoa pessoa) throws SQLException {
		String update = "UPDATE pessoas " + "SET nome = ?, cpf = ?, telefone = ? " + "WHERE id = ?";
		update(update, pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone());
	}
}
