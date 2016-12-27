package test.model;

import static org.junit.Assert.*;
import model.entity.Personagem;

import org.junit.Test;

public class TestPersonagem {

	@Test
	public void testConstrutorNulo() {
		Personagem p = new Personagem();
		assertEquals(null, p.getIdPersonagem());
		assertEquals(null, p.getNome());
		assertEquals(null, p.getIdMundo());
		assertEquals(null, p.getIdClassificacao());
		assertEquals(null, p.getIdEstilo());
		assertEquals(null, p.getIdArma());
		assertEquals(null, p.getIdAlinhamento());
	}
	
	@Test
	public void testConstrutorPadrao() {
		Personagem p = new Personagem();
		p.setIdPersonagem(null);
		p.setNome("SubZero");
		p.setIdMundo(2);
		p.setIdClassificacao(3);
		p.setIdEstilo(2);
		p.setIdArma(4);
		p.setIdAlinhamento(6);
		assertEquals(null, p.getIdPersonagem());
		assertEquals("SubZero", p.getNome());
		assertEquals((Integer)2, p.getIdMundo());
		assertEquals((Integer)3, p.getIdClassificacao());
		assertEquals((Integer)2, p.getIdEstilo());
		assertEquals((Integer)4, p.getIdArma());
		assertEquals((Integer)6, p.getIdAlinhamento());
	}
	
	@Test
	public void testConstrutorCheio() {
		Personagem p = new Personagem(null,"SubZero", 2, 3, 2, 4, 6);
		assertEquals(null, p.getIdPersonagem());
		assertEquals("SubZero", p.getNome());
		assertEquals((Integer)2, p.getIdMundo());
		assertEquals((Integer)3, p.getIdClassificacao());
		assertEquals((Integer)2, p.getIdEstilo());
		assertEquals((Integer)4, p.getIdArma());
		assertEquals((Integer)6, p.getIdAlinhamento());
	}

}
