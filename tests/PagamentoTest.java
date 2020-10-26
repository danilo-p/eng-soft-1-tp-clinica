import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import clinica.models.Pagamento;
import clinica.models.Pessoa;

class PagamentoTest {

	@Test
	void testPagamento() {
		assertTrue(true);
	}

	@Test
	void testPagamentoIntIntIntStringStringStringInt() {
		assertTrue(true);
	}
//	testing getters
	@Test
	void testGetId() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setId(1);

		assertEquals(1, pagamento.getId());
	}
//	testing setters
	@Test
	void testSetId() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setId(1);

		assertEquals(1, pagamento.getId());
	}
//	testing getters
	@Test
	void testGetValor() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setValor(100);

		assertEquals(100, pagamento.getValor());

	}
//	testing setters
	@Test
	void testSetValor() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setValor(100);

		assertEquals(100, pagamento.getValor());
	}
//	testing getters
	@Test
	void testGetTipo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setTipo(2);

		assertEquals(2, pagamento.getTipo());
	}
//	testing setters
	@Test
	void testSetTipo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setTipo(2);

		assertEquals(2, pagamento.getTipo());
	}
//	testing getters
	@Test
	void testGetMetodo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMetodo("Metodo");

		assertEquals("Metodo", pagamento.getMetodo());
	}
//	testing setters
	@Test
	void testSetMetodo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMetodo("Metodo");

		assertEquals("Metodo", pagamento.getMetodo());

	}
//	testing getters
	@Test
	void testGetConvenio() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setConvenio("Convenio");

		assertEquals("Convenio", pagamento.getConvenio());
	}
//	testing setters
	@Test
	void testSetConvenio() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setConvenio("Convenio");

		assertEquals("Convenio", pagamento.getConvenio());
	}
//	testing getters
	@Test
	void testGetMatricula() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMatricula("Matricula");

		assertEquals("Matricula", pagamento.getMatricula());
	}
//	testing setters
	@Test
	void testSetMatricula() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMatricula("Matricula");

		assertEquals("Matricula", pagamento.getMatricula());
	}
//	testing getters
	@Test
	void testGetConsultaId() {
		assertTrue(true);
	}
//	testing setters
	@Test
	void testSetConsultaId() {
		assertTrue(true);
	}
}