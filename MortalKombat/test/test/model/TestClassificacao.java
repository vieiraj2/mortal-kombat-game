package test.model;

import static org.junit.Assert.*;
import model.entity.Classificacao;

import org.junit.Test;

public class TestClassificacao {

	@Test
	public void testConstrutorNulo() {
		Classificacao c = new Classificacao();
		assertEquals(null, c.getIdClassificacao());
		assertEquals(null, c.getNomeClassificacao());
	}
		
	@Test
	public void testConstrutorPadrao() {
		Classificacao c = new Classificacao();
		c.setIdClassificacao(null);
		c.setNomeClassificacao("Guerreiro");
		assertEquals(null, c.getIdClassificacao());
		assertEquals("Guerreiro", c.getNomeClassificacao());
	}
		
	@Test
	public void testConstrutorCheio() {
		Classificacao c = new Classificacao(null, "Guerreiro");
		assertEquals(null, c.getIdClassificacao());
		assertEquals("Guerreiro", c.getNomeClassificacao());
	}

}
