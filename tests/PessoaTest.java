import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import clinica.models.Pessoa;

public class PessoaTest {

	@Test
	public void testPessoa() {
		assertTrue(true);
	}

	@Test
	public void testPessoaStringStringStringInt() {
		assertTrue(true);
	}

	@Test
	public void testGetId() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setId(1);

		assertEquals(1, pessoa.getId());
	}

	@Test
	public void testSetId() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setId(1);

		assertEquals(1, pessoa.getId());
	}

	@Test
	public void testGetNome() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setNome("Jonas");

		assertEquals("Jonas", pessoa.getNome());
	}

	@Test
	public void testSetNome() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setNome("Jonas");

		assertEquals("Jonas", pessoa.getNome());
	}

	@Test
	public void testGetCpf() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setCpf("12345678901");

		assertEquals("12345678901", pessoa.getCpf());
	}

	@Test
	public void testSetCpf() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setCpf("12345678901");

		assertEquals("12345678901", pessoa.getCpf());
	}

	@Test
	public void testGetTelefone() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setTelefone("31999999999");

		assertEquals("31999999999", pessoa.getTelefone());
	}

	@Test
	public void testSetTelefone() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setTelefone("31999999999");

		assertEquals("31999999999", pessoa.getTelefone());
	}

	@Test
	public void testGetTipo() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setTipo(1);

		assertEquals(1, pessoa.getTipo());
	}

	@Test
	public void testSetTipo() {
		Pessoa pessoa  = new Pessoa();
		pessoa.setTipo(1);

		assertEquals(1, pessoa.getTipo());
	}

}
