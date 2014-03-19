package br.arthur.interfaces.consultas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ConsultarAPagar extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setTitle("Consultar Contas \u00E0 Pagar");
		setBounds(100, 100, 856, 446);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		getContentPane().add(lblConsignatrio);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 94, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblConsignatrio, 6, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblConsignatrio, -6, SpringLayout.WEST, textField);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblVencimento = new JLabel("Vencimento:");
		springLayout.putConstraint(SpringLayout.NORTH, lblVencimento, 0, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, lblVencimento, 26, SpringLayout.EAST, textField);
		getContentPane().add(lblVencimento);
		
		JLabel lblAt = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, lblAt, 0, SpringLayout.NORTH, lblConsignatrio);
		getContentPane().add(lblAt);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, lblAt, 6, SpringLayout.EAST, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblVencimento);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 87, SpringLayout.EAST, lblVencimento);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -6, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblAt);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 507, SpringLayout.WEST, getContentPane());
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		JCheckBox chckbxPagar = new JCheckBox("\u00C0 Pagar");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxPagar, -1, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, chckbxPagar, 19, SpringLayout.EAST, textField_2);
		getContentPane().add(chckbxPagar);
		
		JCheckBox chckbxQuitados = new JCheckBox("Quitados");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxQuitados, 0, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, chckbxQuitados, 16, SpringLayout.EAST, chckbxPagar);
		getContentPane().add(chckbxQuitados);
		
		JButton btnFiltrarResultados = new JButton("Filtrar Resultados");
		springLayout.putConstraint(SpringLayout.NORTH, btnFiltrarResultados, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnFiltrarResultados, 6, SpringLayout.EAST, chckbxQuitados);
		springLayout.putConstraint(SpringLayout.EAST, btnFiltrarResultados, 155, SpringLayout.EAST, chckbxQuitados);
		getContentPane().add(btnFiltrarResultados);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 7, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 367, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnFiltrarResultados);
		getContentPane().add(scrollPane);

	}
}
