package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastroMarca extends JInternalFrame {
	private JTextField txtMarca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMarca frame = new CadastroMarca();
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
	public CadastroMarca() {
		setTitle("Cadastro de Categoria");
		setBounds(100, 100, 260, 125);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblMarcaID = new JLabel("Nova Marca");
		lblMarcaID.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblMarcaID, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMarcaID, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblMarcaID);
		
		txtMarca = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtMarca, 6, SpringLayout.SOUTH, lblMarcaID);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		springLayout.putConstraint(SpringLayout.NORTH, lblMarca, 12, SpringLayout.SOUTH, lblMarcaID);
		springLayout.putConstraint(SpringLayout.EAST, lblMarca, -171, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMarca, 6, SpringLayout.EAST, lblMarca);
		springLayout.putConstraint(SpringLayout.EAST, txtMarca, 161, SpringLayout.EAST, lblMarca);
		getContentPane().add(lblMarca);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 10, SpringLayout.SOUTH, txtMarca);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, txtMarca);
		getContentPane().add(btnSalvar);

	}

}
