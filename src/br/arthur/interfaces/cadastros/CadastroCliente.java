package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class CadastroCliente extends JInternalFrame {
	private JTextField txtNome;
	private JTextField textField;
	private JTextField txtCelular;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
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
	public CadastroCliente() {
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 397, 352);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNovoCliente = new JLabel("Novo Cliente");
		lblNovoCliente.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblNovoCliente, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNovoCliente, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNovoCliente);
		
		txtNome = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtNome, 6, SpringLayout.SOUTH, lblNovoCliente);
		springLayout.putConstraint(SpringLayout.EAST, txtNome, -16, SpringLayout.EAST, getContentPane());
		txtNome.setText("Nome");
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		springLayout.putConstraint(SpringLayout.WEST, txtNome, 23, SpringLayout.EAST, lblNome);
		springLayout.putConstraint(SpringLayout.NORTH, lblNome, 12, SpringLayout.SOUTH, lblNovoCliente);
		springLayout.putConstraint(SpringLayout.WEST, lblNome, 0, SpringLayout.WEST, lblNovoCliente);
		getContentPane().add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		springLayout.putConstraint(SpringLayout.WEST, lblTelefone, 0, SpringLayout.WEST, lblNovoCliente);
		getContentPane().add(lblTelefone);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, txtNome);
		springLayout.putConstraint(SpringLayout.WEST, textField, 9, SpringLayout.EAST, lblTelefone);
		springLayout.putConstraint(SpringLayout.NORTH, lblTelefone, 6, SpringLayout.NORTH, textField);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		springLayout.putConstraint(SpringLayout.WEST, lblCelular, 6, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCelular, 0, SpringLayout.SOUTH, lblTelefone);
		getContentPane().add(lblCelular);
		
		txtCelular = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCelular, 6, SpringLayout.SOUTH, txtNome);
		springLayout.putConstraint(SpringLayout.EAST, txtCelular, 0, SpringLayout.EAST, txtNome);
		txtCelular.setText("Celular");
		getContentPane().add(txtCelular);
		txtCelular.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, txtNome);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, txtNome);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 6, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblNovoCliente);
		getContentPane().add(lblEmail);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -39, SpringLayout.EAST, lblCelular);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAniversrio = new JLabel("Anivers\u00E1rio:");
		springLayout.putConstraint(SpringLayout.NORTH, lblAniversrio, 12, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, lblAniversrio, 0, SpringLayout.WEST, lblNovoCliente);
		getContentPane().add(lblAniversrio);
		
		JCheckBox chckbxPendncia = new JCheckBox("Pend\u00EAncia");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxPendncia, 5, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, chckbxPendncia, -23, SpringLayout.EAST, getContentPane());
		getContentPane().add(chckbxPendncia);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		springLayout.putConstraint(SpringLayout.NORTH, lblObservaes, 6, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, lblObservaes, 0, SpringLayout.WEST, lblNovoCliente);
		getContentPane().add(lblObservaes);
		
		JTextArea textArea = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, lblObservaes);
		springLayout.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, lblNovoCliente);
		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -46, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textArea, 0, SpringLayout.EAST, txtNome);
		getContentPane().add(textArea);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 8, SpringLayout.SOUTH, textArea);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, txtNome);
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 0, SpringLayout.WEST, lblNovoCliente);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnSalvar);
		getContentPane().add(btnCancelar);
		
		JButton btnExcluir = new JButton("Excluir");
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExcluir, 0, SpringLayout.SOUTH, btnSalvar);
		getContentPane().add(btnExcluir);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscarCliente, -4, SpringLayout.NORTH, lblNovoCliente);
		springLayout.putConstraint(SpringLayout.EAST, btnBuscarCliente, 0, SpringLayout.EAST, txtNome);
		getContentPane().add(btnBuscarCliente);

	}
}
