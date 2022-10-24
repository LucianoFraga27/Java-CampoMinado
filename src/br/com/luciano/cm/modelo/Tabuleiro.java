package br.com.luciano.cm.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas; 
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMina();
	}
	
	private void gerarCampos() {
		for(int L = 0; L < this.linhas; L++) {				// L -> Linha
			for (int C = 0; C < this.colunas; C++) {		// C -> Coluna
				campos.add(new Campo(L,C));
			}
		}
	}
	
	private void associarVizinhos() {
		for(Campo campo1: campos) {
			for(Campo campo2: campos) {
				campo1.adicionarVizinho(campo2);
			}
		}
	}

	private void sortearMina() {
	
	}
	
	
	
}
