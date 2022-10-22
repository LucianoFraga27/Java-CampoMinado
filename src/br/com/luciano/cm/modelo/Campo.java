package br.com.luciano.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int linha; 
	private final int coluna;
	
	private boolean aberto = false;  // Livre 
	private boolean minado = false;  // Mina
	private boolean marcado = false; // Bandeirinha

	private List<Campo> vizinhos = new ArrayList<>(); // Auto relacionamento 1,N
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	};
	
	
	
	
}
