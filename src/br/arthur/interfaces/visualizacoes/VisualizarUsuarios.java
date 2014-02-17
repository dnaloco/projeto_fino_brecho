package br.arthur.interfaces.visualizacoes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.arthur.models.UserModel;

public class VisualizarUsuarios extends JInternalFrame {
	private JTable table;
	private JTextField txtId;
	private JTextField txtUser;
	private JTextField txtName;
	private JTextField textField;
	private UserModel um;
	private JTable tabUsers;
	private JScrollPane scrollTabUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarUsuarios frame = new VisualizarUsuarios();
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
	public VisualizarUsuarios() {
		setBounds(100, 100, 827, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		
	}

}
