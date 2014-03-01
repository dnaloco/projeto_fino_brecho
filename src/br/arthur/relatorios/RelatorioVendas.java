package br.arthur.relatorios;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class RelatorioVendas extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioVendas frame = new RelatorioVendas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RelatorioVendas() {
		setBounds(100, 100, 450, 300);

	}

}
