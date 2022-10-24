package br.com.luciano.cm;

import br.com.luciano.cm.modelo.Tabuleiro;
import br.com.luciano.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6,6,1);
		
		new TabuleiroConsole(tabuleiro);
	
		
	}
}
