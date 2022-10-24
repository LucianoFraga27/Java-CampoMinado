package br.com.luciano.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.luciano.cm.excecao.ExplosaoException;
import br.com.luciano.cm.excecao.SairException;
import br.com.luciano.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar == true) {
				
				clicoDoJogo();
				
				System.out.println("Outra partida ? (S/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
				
			}
		} catch (SairException e) {
			System.out.println("Tchau!");
		} finally {
			entrada.close();
		}
		
	}

	private void clicoDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro.toString());
				
				String digitado = capturaValorDigitado("Digite valores de ( x , y ) -> ");
				
				
				Iterator<Integer> xy =	 Arrays.stream(digitado.split(","))
											   .map(e -> Integer.parseInt(e.trim()))
											   .iterator();
				
				digitado = capturaValorDigitado("1-Abrir \n2-(Des)Marcar");
				
				if("1".equalsIgnoreCase(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(digitado)) {
					tabuleiro.alterarMarcarcao(xy.next(), xy.next());
				}
				
			}
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce Perdeu!!");
		} finally {
			System.out.println("Fim");
		}
		
	}
	
	private String capturaValorDigitado(String texto) {
		
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
	
}
