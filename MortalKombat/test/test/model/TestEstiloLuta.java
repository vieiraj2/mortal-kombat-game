package test.model;

import static org.junit.Assert.*;
import model.entity.EstiloLuta;

import org.junit.Test;

public class TestEstiloLuta {

	@Test
	public void testConstrutorNulo() {
		EstiloLuta e = new EstiloLuta();
		assertEquals(null, e.getIdEstilo());
		assertEquals(null, e.getNomeEstilo());
	}
		
	@Test
	public void testConstrutorPadrao() {
		EstiloLuta e = new EstiloLuta();
		e.setIdEstilo(null);
		e.setNomeEstilo("KungFu");
		assertEquals(null, e.getIdEstilo());
		assertEquals("KungFu", e.getNomeEstilo());
	}
		
	@Test
	public void testConstrutorCheio() {
		EstiloLuta e = new EstiloLuta(null, "KungFu");
		assertEquals(null, e.getIdEstilo());
		assertEquals("KungFu", e.getNomeEstilo());
	}
}
