package br.arthur.temp.tests;

import javax.swing.JOptionPane;

public class AnyTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String value = JOptionPane.showInputDialog("Qual o valor que ser� pago desta parcela?");
		boolean isPushed = true;
		while (isPushed) {
			value = JOptionPane.showInputDialog("Qual o valor que ser� pago desta parcela?");
			
			try
			{
				value = value.replace(",", ".").trim();
				JOptionPane.showMessageDialog(null, value);
				Double.parseDouble(value);
				isPushed = false;
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Esse n�o � um valor v�lido!");
			}
		}
	}

}
