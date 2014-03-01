package br.arthur.interfaces.system;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class SysBackup extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysBackup frame = new SysBackup();
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
	public SysBackup() {
		setBounds(100, 100, 450, 300);

	}

}
