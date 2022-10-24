package br.com.luciano.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.luciano.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);					// [2,2] [2,3] [2,4]
	}												// [3,2] [3,3] [3,4]
 													// [4,2] [4,3] [4,4]
	
	// Testando valores dentro da linha e coluna
	
	@Test
	void testeValorLinha() {
		assertTrue(campo.getLinha() > -1);
	}
	
	@Test
	void testeValorLinha2() {
		assertFalse(campo.getLinha() < -1);
	}
	
	@Test
	void testeValorColuna() {
		assertTrue(campo.getColuna() > -1);
	}
	
	@Test
	void testeValorColuna2() {
		assertFalse(campo.getColuna() < -1);
	}
	
	// Testando vizinhos
	
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoRealDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Embaixo() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	// Testando bandeira
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	

	@Test
	void testeAlternarMarcacao2Chamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	
	// Testando abrir campo
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> campo.abrir());
	}
	
	
	@Test
	void testeAbrirComVizinho1() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	
	@Test
	void testeAbrirComVizinho2() {
		
		Campo campo11 = new Campo(1,1);
		
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	// Teste minado
	
	@Test
	void testeNaoEstaMinado() {
		assertFalse(campo.isMinado());
	}
	
	// Teste reinicio
	
	@Test
	void testeReiniciar() {
		boolean rodando = true;
		campo.reiniciar();
		if(campo.isAberto() == false && campo.isMarcado() == false && campo.isMinado() == false) {
			rodando = false;
			assertFalse(campo.isMinado());
			}
		}
		
	
	// Objetivo alcançado
	/*
	@Test
	void testeObjetivoAlcancado() {
		assertTrue(campo.objetivoAlcancado());
		}
	*/
	
	// Teste minas na vizinhaca
	
	@Test
	void testeMinasNaVizinhaca() {
		assertTrue(campo.minasNaVizinhaca() == 0 || campo.minasNaVizinhaca() > 0);
		} 
	
	@Test
	void testeMinasNaVizinhacaFalse() {
		assertFalse(campo.minasNaVizinhaca() < 0);
		} 
	
	// Teste minas na vizinhaca
	
	@Test
	void testeToString1() {
		assertTrue(campo.toString() != "X");
		} 
	@Test
	void testeToString2() {
		assertTrue(campo.toString() != "*");
		} 
	@Test
	void testeToString3() {
		assertTrue(campo.toString() != " ");
		} 
	@Test
	void testeToStrin4() {
		assertTrue(campo.toString() == "?");
		} 


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

