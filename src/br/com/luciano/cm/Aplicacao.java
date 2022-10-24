package br.com.luciano.cm;

import br.com.luciano.cm.modelo.Tabuleiro;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6,6,1);
		
		tabuleiro.abrir(5, 4);
		System.out.println(tabuleiro);
		
	}
}
