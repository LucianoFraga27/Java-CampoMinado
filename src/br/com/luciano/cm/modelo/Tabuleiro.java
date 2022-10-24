package br.com.luciano.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.luciano.cm.excecao.ExplosaoException;

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
	
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
			  .filter(c->c.getLinha() == linha && c.getColuna() == coluna)
			  .findFirst()
			  .ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alterarMarcarcao(int linha, int coluna) {
		campos.parallelStream()
			  .filter(c->c.getLinha() == linha && c.getColuna() == coluna)
			  .findFirst()
			  .ifPresent(c -> c.alternarMarcacao());;
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
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		do {
			minasArmadas = campos.stream()
								 .filter(minado)
								 .count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasArmadas < this.minas);
		
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream()
					 .allMatch(c->c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream()
			  .forEach(c->c.reiniciar());
		sortearMina();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i =0;
		for (int L = 0; L < this.linhas; L++) {
			for (int C = 0; C < this.colunas; C++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
