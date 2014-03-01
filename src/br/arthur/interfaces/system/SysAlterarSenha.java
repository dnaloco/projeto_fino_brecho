package br.arthur.interfaces.system;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class SysAlterarSenha extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SysAlterarSenha frame = new SysAlterarSenha();
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
	public SysAlterarSenha() {
		setBounds(100, 100, 450, 300);

	}

}
