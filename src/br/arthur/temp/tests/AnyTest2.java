package br.arthur.temp.tests;

import java.awt.Desktop;
import java.io.File;

import javax.swing.JOptionPane;

public class AnyTest2 {

	public static void main(String[] args) {		

        File pdf = new File("relatorios/RelatorioProdutos.pdf");  
        try {  
          Desktop.getDesktop().open(pdf);  
        } catch(Exception ex) {  
          ex.printStackTrace();  
          JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
        } 
	}

}
