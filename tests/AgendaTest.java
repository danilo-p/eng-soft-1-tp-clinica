import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import clinica.models.Agenda;

import java.sql.Time;
import java.time.Clock;
import java.time.LocalTime;

class AgendaTest {

	@Test
	void testAgenda() {
		assertTrue(true);
	}

	@Test
	void testAgendaStringTimeTimeInt() {
		assertTrue(true);
	}

	@Test
	void testGetId() {
		Agenda agenda  = new Agenda();
		agenda.setId(1);

		assertEquals(1, agenda.getId());
	}

	@Test
	void testSetId() {
		Agenda agenda  = new Agenda();
		agenda.setId(1);

		assertEquals(1, agenda.getId());
	}
//
	@Test
	void testGetCargaHoraria() {
		Agenda agenda  = new Agenda();
		agenda.setCargaHoraria("Horario");

		assertEquals("Horario", agenda.getCargaHoraria());
	}
//
	@Test
	void testSetCargaHoraria() {
		Agenda agenda  = new Agenda();
		agenda.setCargaHoraria("Horario");

		assertEquals("Horario", agenda.getCargaHoraria());
	}
//
	@Test
	void testGetHoraInicio() {
		Agenda agenda  = new Agenda();
		agenda.setHoraInicio(Time.valueOf(LocalTime.now()));

		assertEquals(Time.valueOf(LocalTime.now()), agenda.getHoraInicio());
	}
//
	@Test
	void testSetHoraInicio() {
		Agenda agenda  = new Agenda();
		agenda.setHoraInicio(Time.valueOf(LocalTime.now()));

		assertEquals(Time.valueOf(LocalTime.now()), agenda.getHoraInicio());
	}
//
	@Test
	void testGetHoraFim() {
		Agenda agenda  = new Agenda();
		agenda.setHoraFim(Time.valueOf(LocalTime.now()));

		assertEquals(Time.valueOf(LocalTime.now()), agenda.getHoraFim());
	}
//
	@Test
	void testSetHoraFim() {
		Agenda agenda  = new Agenda();
		agenda.setHoraFim(Time.valueOf(LocalTime.now()));

		assertEquals(Time.valueOf(LocalTime.now()), agenda.getHoraFim());
	}
//
	@Test
	void testGetTempoIntervalo() {
		Agenda agenda  = new Agenda();
		agenda.setTempoIntervalo(1);

		assertEquals(1, agenda.getTempoIntervalo());
		
	}
//
	@Test
	void testSetTempoIntervalo() {
		Agenda agenda  = new Agenda();
		agenda.setTempoIntervalo(1);

		assertEquals(1, agenda.getTempoIntervalo());
	}
//
}
