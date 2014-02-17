package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroCondicao extends JInternalFrame {
	private JTextField txtCondicao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCondicao frame = new CadastroCondicao();
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
	public CadastroCondicao() {
		setTitle("Cadastro de Condi\u00E7\u00E3o");
		setBounds(100, 100, 271, 118);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCondicaoID = new JLabel("Nova Condi\u00E7\u00E3o");
		lblCondicaoID.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblCondicaoID, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCondicaoID, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCondicaoID);
		
		txtCondicao = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCondicao, 6, SpringLayout.SOUTH, lblCondicaoID);
		springLayout.putConstraint(SpringLayout.WEST, txtCondicao, 79, SpringLayout.WEST, getContentPane());
		getContentPane().add(txtCondicao);
		txtCondicao.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtCondicao);
		springLayout.putConstraint(SpringLayout.EAST, txtCondicao, 0, SpringLayout.EAST, btnSalvar);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSalvar);
		
		JLabel lblNewLabel = new JLabel("Condi\u00E7\u00E3o:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, txtCondicao);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, lblCondicaoID);
		getContentPane().add(lblNewLabel);

	}

}
