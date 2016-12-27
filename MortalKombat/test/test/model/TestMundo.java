package test.model;

import static org.junit.Assert.*;
import model.entity.Mundo;

import org.junit.Test;

public class TestMundo {

	@Test
	public void testConstrutorNulo() {
		Mundo m = new Mundo();
		assertEquals(null, m.getIdMundo());
		assertEquals(null, m.getNomeMundo());
	}
		
	@Test
	public void testConstrutorPadrao() {
		Mundo m = new Mundo();
		m.setIdMundo(null);
		m.setNomeMundo("Netherrealm");
		assertEquals(null, m.getIdMundo());
		assertEquals("Netherrealm", m.getNomeMundo());
	}
		
	@Test
	public void testConstrutorCheio() {
		Mundo m = new Mundo(null, "Netherrealm");
		assertEquals(null, m.getIdMundo());
		assertEquals("Netherrealm", m.getNomeMundo());
	}

}
