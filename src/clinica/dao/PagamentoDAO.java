package clinica.dao;

import java.sql.SQLException;

import clinica.models.Pagamento;

/**
 * Data Access Object para o modelo Pagamento.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 */
public class PagamentoDAO extends GenericDAO {

//	Salva um pagamento particular no banco de dado
	public void salvarPagamentoParticular(Pagamento pagamento) throws SQLException {
		String insert = "INSERT INTO pagamentos(valor, tipo, metodo, consulta_id) VALUES(?,?,?,?)";
		int id = save(insert, pagamento.getValor(), pagamento.getTipo(), pagamento.getMetodo(),
				pagamento.getConsultaId());
		if (id > 0) {
			pagamento.setId(id);
		}
	}

//	Salva um pagamento por convernio no banco de dados
	public void salvarPagamentoConvenio(Pagamento pagamento) throws SQLException {
		String insert = "INSERT INTO pagamentos(valor, tipo, convenio, matricula, consulta_id) VALUES(?,?,?,?,?)";
		int id = save(insert, pagamento.getValor(), pagamento.getTipo(), pagamento.getConvenio(),
				pagamento.getMatricula(), pagamento.getConsultaId());
		if (id > 0) {
			pagamento.setId(id);
		}
	}

}
