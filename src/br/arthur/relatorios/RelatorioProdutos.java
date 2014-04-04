package br.arthur.relatorios;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import br.arthur.utils.RelatorioUtil;

public class RelatorioProdutos {
	public static void gerarFiltro(List dataReport) {
		
		boolean isCreated = RelatorioUtil.gerarRelatorio(dataReport, "RelatorioProdutos");
		
		if(isCreated) {
			JOptionPane.showMessageDialog(null, "Relatório criado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. Verifique se o arquivo está aberto.");
		}
	}
}
