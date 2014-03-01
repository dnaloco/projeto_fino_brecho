package br.arthur.interfaces.consultas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultarCaixar extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarCaixar frame = new ConsultarCaixar();
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
	public ConsultarCaixar() {
		setBounds(100, 100, 450, 300);

	}

}
