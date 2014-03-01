package br.arthur.interfaces.consultas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ConsultarAPagar extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarAPagar frame = new ConsultarAPagar();
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
	public ConsultarAPagar() {
		setBounds(100, 100, 450, 300);

	}

}
