package clinica.controllers;

import java.sql.SQLException;

import clinica.dao.PagamentoDAO;
import clinica.models.Pagamento;

/**
 * Regras de negócio para operações relacionadas a pagamentos.
 */
public class PagamentosController {

	private final PagamentoDAO pagamentoDAO;

	public PagamentosController() {
		this.pagamentoDAO = new PagamentoDAO();
	}

//	Cria um novo pagamento no modo particular
	public void criarPagamentoParticular(int valor, int tipoPagamento, String pagamento, int idConsulta) {
		Pagamento novoPagamento = new Pagamento();

		novoPagamento.setValor(valor);
		novoPagamento.setTipo(tipoPagamento);
		novoPagamento.setMetodo(pagamento);
		novoPagamento.setConsultaId(idConsulta);

		try {
			this.pagamentoDAO.salvarPagamentoParticular(novoPagamento);
		} catch (SQLException ex) {
			System.out.println("PagamentosController: Falha ao salvar pagamento.");
			System.out.println(ex);
		}
	}

//	Cria um novo pagamento por convenio
	public void criarPagamentoConvenio(int valor, int tipoPagamento, String pagamento, String matricula,
			int idConsulta) {
		Pagamento novoPagamento = new Pagamento();

		novoPagamento.setValor(valor);
		novoPagamento.setTipo(tipoPagamento);
		novoPagamento.setConvenio(pagamento);
		novoPagamento.setMatricula(matricula);
		novoPagamento.setConsultaId(idConsulta);

		try {
			this.pagamentoDAO.salvarPagamentoConvenio(novoPagamento);
		} catch (SQLException ex) {
			System.out.println("PagamentosController: Falha ao salvar pagamento.");
			System.out.println(ex);
		}
	}

}
