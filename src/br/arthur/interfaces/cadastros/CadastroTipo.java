package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CadastroTipo extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroTipo frame = new CadastroTipo();
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
	public CadastroTipo() {
		setBounds(100, 100, 228, 330);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNovaTipo = new JLabel("Nova Tipo");
		springLayout.putConstraint(SpringLayout.NORTH, lblNovaTipo, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNovaTipo, 10, SpringLayout.WEST, getContentPane());
		lblNovaTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNovaTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		getContentPane().add(lblTipo);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 43, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTipo, 6, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblTipo, -6, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblNovaTipo);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, textField);
		panel.setBorder(new TitledBorder(null, "Lista de Tipos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnSalvar);
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, -88, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnCancelar);

	}
}
