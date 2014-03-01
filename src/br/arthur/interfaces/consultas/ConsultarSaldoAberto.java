package br.arthur.interfaces.consultas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultarSaldoAberto extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarSaldoAberto frame = new ConsultarSaldoAberto();
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
	public ConsultarSaldoAberto() {
		setBounds(100, 100, 450, 300);

	}

}
