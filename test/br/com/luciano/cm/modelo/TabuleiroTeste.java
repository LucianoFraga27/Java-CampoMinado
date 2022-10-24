package br.com.luciano.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabuleiroTeste {
	
	private Tabuleiro tabuleiro;
	@BeforeEach
	void iniciarTabuleiro() {
		tabuleiro = new Tabuleiro(6,6,1);	
	}	
	
	
	@Test
	void testeAbrirTabuleiro() {
		tabuleiro.abrir(1, 1);
	}

	@Test
	void testeAlterarMarcacao() {
		tabuleiro.alterarMarcarcao(2, 2);
	}
	
	@Test
	void testeObjetivoAlcancao() {
		assertTrue(tabuleiro.objetivoAlcancado());
	}
	
	@Test
	void testeNaoObjetivoAlcancao() {
		assertFalse(tabuleiro.objetivoAlcancado());
	}
	
	@Test
	void testeReiniciar() {
		tabuleiro.reiniciar();
	}

	@Test
	void testeToString() {
		tabuleiro.toString();
	}
	
	
}
