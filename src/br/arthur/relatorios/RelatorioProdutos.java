package br.arthur.relatorios;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import br.arthur.utils.RelatorioUtil;

public class RelatorioProdutos {
	public static void gerarFiltro(List dataReport) {
		
		boolean isCreated = RelatorioUtil.gerarRelatorio(dataReport, "RelatorioProdutos");
		
		if(isCreated) {
			JOptionPane.showMessageDialog(null, "Relat�rio criado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar o relat�rio. Verifique se o arquivo est� aberto.");
		}
	}
}
