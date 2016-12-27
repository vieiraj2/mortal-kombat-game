package test.model;

import static org.junit.Assert.*;
import model.entity.Alinhamento;

import org.junit.Test;

public class TestAlinhamento {

	@Test
	public void testConstrutorNulo() {
		Alinhamento a = new Alinhamento();
		assertEquals(null, a.getIdAlinhamento());
		assertEquals(null, a.getNomeAlinhamento());
	}
		
	@Test
	public void testConstrutorPadrao() {
		Alinhamento a = new Alinhamento();
		a.setIdAlinhamento(null);
		a.setNomeAlinhamento("Mal");
		assertEquals(null, a.getIdAlinhamento());
		assertEquals("Mal", a.getNomeAlinhamento());
	}
		
	@Test
	public void testConstrutorCheio() {
		Alinhamento a = new Alinhamento(null, "Mal");
		assertEquals(null, a.getIdAlinhamento());
		assertEquals("Mal", a.getNomeAlinhamento());
	}

}
