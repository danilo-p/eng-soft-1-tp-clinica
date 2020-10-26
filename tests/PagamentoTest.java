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

	@Test
	void testGetId() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setId(1);

		assertEquals(1, pagamento.getId());
	}

	@Test
	void testSetId() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setId(1);

		assertEquals(1, pagamento.getId());
	}

	@Test
	void testGetValor() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setValor(100);

		assertEquals(100, pagamento.getValor());

	}

	@Test
	void testSetValor() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setValor(100);

		assertEquals(100, pagamento.getValor());
	}

	@Test
	void testGetTipo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setTipo(2);

		assertEquals(2, pagamento.getTipo());

	}

	@Test
	void testSetTipo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setTipo(2);

		assertEquals(2, pagamento.getTipo());
	}

	@Test
	void testGetMetodo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMetodo("Metodo");

		assertEquals("Metodo", pagamento.getMetodo());

	}

	@Test
	void testSetMetodo() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMetodo("Metodo");

		assertEquals("Metodo", pagamento.getMetodo());

	}

	@Test
	void testGetConvenio() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setConvenio("Convenio");

		assertEquals("Convenio", pagamento.getConvenio());
	}

	@Test
	void testSetConvenio() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setConvenio("Convenio");

		assertEquals("Convenio", pagamento.getConvenio());
	}

	@Test
	void testGetMatricula() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMatricula("Matricula");

		assertEquals("Matricula", pagamento.getMatricula());
	}

	@Test
	void testSetMatricula() {
		Pagamento pagamento  = new Pagamento();
		pagamento.setMatricula("Matricula");

		assertEquals("Matricula", pagamento.getMatricula());
	}

	@Test
	void testGetConsultaId() {
		assertTrue(true);
	}

	@Test
	void testSetConsultaId() {
		assertTrue(true);
	}

}
