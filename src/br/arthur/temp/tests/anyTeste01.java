package br.arthur.temp.tests;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import br.arthur.utils.RelatorioUtil;

public class anyTeste01 {
	// http://www.richardnichols.net/2010/02/simple-guide-to-sub-reports-in-jasperreports-ireport/
	
	public static class Pais {
		private String pais;
		
		public Pais(String pais) {
			this.pais = pais;
		}
		
		public String getPais() {
			return pais;
		}
	}
	
	public static class Teste {
		private String nome;
		private List<Pais> paises = Arrays.asList(new Pais("Buenos Aires"), new Pais("Córdoba"), new Pais("La Plata"));
		
		public Teste(String nome) {
			this.nome = nome;
		}
		
		public String getNome() {
			return nome;
		}
		
		public List<Pais> getPaises() {
			return paises;
		}
	}
	
	public static void main(String[] args) throws IOException {
		List data = new ArrayList();

		data.add(new Teste("Arthur"));
		

		boolean isCreated = RelatorioUtil.gerarRelatorio(data, "Blank_A4");
		
		if(isCreated) {
			JOptionPane.showMessageDialog(null, "Relatório criado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. Verifique se o arquivo está aberto.");
		}
		
	}
}
