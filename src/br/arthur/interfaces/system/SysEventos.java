package br.arthur.interfaces.system;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class SysEventos extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysEventos frame = new SysEventos();
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
	public SysEventos() {
		setBounds(100, 100, 450, 300);

	}

}
