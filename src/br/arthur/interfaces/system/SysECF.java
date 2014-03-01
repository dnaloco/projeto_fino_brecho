package br.arthur.interfaces.system;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class SysECF extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysECF frame = new SysECF();
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
	public SysECF() {
		setBounds(100, 100, 450, 300);

	}

}
