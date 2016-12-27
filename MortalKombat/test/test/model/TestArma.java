package test.model;

import static org.junit.Assert.*;
import model.entity.Arma;

import org.junit.Test;

public class TestArma {

	@Test
	public void testConstrutorNulo() {
		Arma a = new Arma();
		assertEquals(null, a.getIdArma());
		assertEquals(null, a.getNomeArma());
		assertEquals(null, a.getAtaqueArma());
		
	}
		
	@Test
	public void testConstrutorPadrao() {
		Arma a = new Arma();
		a.setIdArma(null);
		a.setNomeArma("Bast�o");
		a.setAtaque(50);
		assertEquals(null, a.getIdArma());
		assertEquals("Bast�o", a.getNomeArma());
		assertEquals((Integer)50, a.getAtaqueArma());
	}
		
	@Test
	public void testConstrutorCheio() {
		Arma a = new Arma(null, "Bast�o", 50);
		assertEquals(null, a.getIdArma());
		assertEquals("Bast�o", a.getNomeArma());
		assertEquals((Integer)50, a.getAtaqueArma());
	}

}
