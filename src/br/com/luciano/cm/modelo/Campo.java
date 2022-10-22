package br.com.luciano.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.luciano.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha	; 							// [linha,coluna]
	private final int coluna;
	
	private boolean aberto 	= false;  					// Livre 
	private boolean minado 	= false;  					// Mina
	private boolean marcado = false; 					// Bandeirinha

	private List<Campo> vizinhos = new ArrayList<>(); 	// Auto relacionamento 1,N
	
	
	Campo(int linha, int coluna){
		this.linha 	= linha	;
		this.coluna = coluna;
	};
	
	
	boolean adicionarVizinho(Campo vizinho) { 
		
		boolean linhaDiferente = this.linha != vizinho.linha   	;
		boolean colunaDiferente = this.coluna != vizinho.coluna	;
		boolean diagonal = linhaDiferente && colunaDiferente   	;
		
		int deltaLinha = Math.abs(this.linha - vizinho.linha)	;
		int deltaColuna = Math.abs(this.coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha				;
		
		boolean caso = false;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			caso = true;
		}
		else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			caso = true;
		} else {
			caso = false;
		}	
		return caso;
	}
	
	
	void alternarMarcacao() {
		if(!this.aberto) {
			this.marcado = !this.marcado;
		}
	}
	
	
	boolean abrir() {
		if(!this.aberto && !this.marcado) {
			this.aberto = true;
			if(this.minado) {
				throw new ExplosaoException(); 
			}
			if(vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {
			return false;		
		}
	}
	
	
	public boolean isAberto() {
		return aberto;
	} 

	
	public boolean isFechado() {
		return !isAberto();
	} 
	
	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	
	void minar() {
		if(this.minado == false) {
			this.minado = true;
		}
	}
	
	
	public boolean isMarcado() {
		return this.marcado;
	}
}
