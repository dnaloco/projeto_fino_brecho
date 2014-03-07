package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CadastroProduto extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static int theId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto(theId);
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
	public CadastroProduto(int theId) {
		this.theId = theId;
		setBounds(100, 100, 530, 484);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblProduto = new JLabel("Cód. Produto");
		lblProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblProduto);
		
		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio Nome");
		springLayout.putConstraint(SpringLayout.EAST, lblConsignatrio, -197, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblConsignatrio);
		
		JButton btnAvaliarProduto = new JButton("Avaliar Produto");
		springLayout.putConstraint(SpringLayout.NORTH, btnAvaliarProduto, 6, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAvaliarProduto, 243, SpringLayout.EAST, lblProduto);
		springLayout.putConstraint(SpringLayout.EAST, btnAvaliarProduto, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblProduto, 4, SpringLayout.NORTH, btnAvaliarProduto);
		getContentPane().add(btnAvaliarProduto);
		
		JLabel lblConsigId = new JLabel("Consig. ID");
		springLayout.putConstraint(SpringLayout.WEST, lblProduto, 0, SpringLayout.WEST, lblConsigId);
		springLayout.putConstraint(SpringLayout.WEST, lblConsigId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblConsigId);
		
		JLabel label = new JLabel("00000");
		springLayout.putConstraint(SpringLayout.WEST, lblConsignatrio, 31, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, lblConsigId);
		getContentPane().add(label);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 62, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblConsignatrio, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, label, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblConsigId, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_1 = new JLabel("T\u00EDtulo:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		
		JLabel label_2 = new JLabel("Categoria:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel.add(label_2, gbc_label_2);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel label_3 = new JLabel("Marca:");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 5;
		gbc_label_3.gridy = 1;
		panel.add(label_3, gbc_label_3);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.gridx = 6;
		gbc_comboBox_1.gridy = 1;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JLabel label_4 = new JLabel("Tamanho:");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 0, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 2;
		panel.add(label_4, gbc_label_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		
		JLabel label_5 = new JLabel("Cor:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 4;
		gbc_label_5.gridy = 2;
		panel.add(label_5, gbc_label_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		
		JLabel label_6 = new JLabel("Quantidade:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 6;
		gbc_label_6.gridy = 2;
		panel.add(label_6, gbc_label_6);
		
		textField_3 = new JTextField();
		textField_3.setText("1");
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 7;
		gbc_textField_3.gridy = 2;
		panel.add(textField_3, gbc_textField_3);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 257, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 302, SpringLayout.WEST, getContentPane());
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel_1);
		
		JButton button = new JButton(">");
		getContentPane().add(button);
		
		JButton button_1 = new JButton(">>");
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, button_1);
		springLayout.putConstraint(SpringLayout.EAST, button, -5, SpringLayout.WEST, button_1);
		springLayout.putConstraint(SpringLayout.EAST, button_1, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("<");
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, button_1);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("<<");
		springLayout.putConstraint(SpringLayout.WEST, button_2, 6, SpringLayout.EAST, button_3);
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 0, SpringLayout.NORTH, button_3);
		springLayout.putConstraint(SpringLayout.NORTH, button_3, 92, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, button_3, 6, SpringLayout.EAST, panel_1);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Inserir");
		springLayout.putConstraint(SpringLayout.WEST, button_4, 6, SpringLayout.EAST, panel_1);
		getContentPane().add(button_4);
		
		JButton button_5 = new JButton("Trocar");
		springLayout.putConstraint(SpringLayout.NORTH, button_5, 0, SpringLayout.NORTH, button_4);
		springLayout.putConstraint(SpringLayout.WEST, button_5, 6, SpringLayout.EAST, button_4);
		getContentPane().add(button_5);
		
		JButton button_6 = new JButton("Excluir");
		springLayout.putConstraint(SpringLayout.NORTH, button_6, 0, SpringLayout.NORTH, button_4);
		springLayout.putConstraint(SpringLayout.EAST, button_6, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(button_6);
		
		JButton btnNewButton_1 = new JButton("Salvar Produto");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 206, SpringLayout.EAST, panel_1);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar Modifica\u00E7\u00F5es");
		springLayout.putConstraint(SpringLayout.SOUTH, button_4, -6, SpringLayout.NORTH, btnNewButton_2);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, 206, SpringLayout.EAST, panel_1);
		getContentPane().add(btnNewButton_2);
		
		JButton btnExcluirProduto = new JButton("Excluir Produto");
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -6, SpringLayout.NORTH, btnExcluirProduto);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProduto, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExcluirProduto, -7, SpringLayout.NORTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProduto, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(btnExcluirProduto);
		
		JLabel lblNewLabel = new JLabel("Produto Indispon\u00EDvel (05/02/2014)");
		lblNewLabel.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 2, SpringLayout.NORTH, lblProduto);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, panel_1);
		getContentPane().add(lblNewLabel);
		
		JLabel label_7 = new JLabel("1/5");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, label_7, 6, SpringLayout.EAST, button_2);
		springLayout.putConstraint(SpringLayout.SOUTH, label_7, -12, SpringLayout.NORTH, button_5);
		springLayout.putConstraint(SpringLayout.EAST, label_7, -6, SpringLayout.WEST, button);
		label_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(label_7);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataEntrada, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblDataEntrada, 6, SpringLayout.EAST, panel_1);
		getContentPane().add(lblDataEntrada);
		
		JLabel label_8 = new JLabel("01/01/2014");
		springLayout.putConstraint(SpringLayout.NORTH, label_8, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, label_8, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(label_8);
		
		JLabel lblDataVencimento = new JLabel("Data Vencimento:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataVencimento, 6, SpringLayout.SOUTH, lblDataEntrada);
		springLayout.putConstraint(SpringLayout.WEST, lblDataVencimento, 6, SpringLayout.EAST, panel_1);
		getContentPane().add(lblDataVencimento);
		
		JLabel label_9 = new JLabel("01/01/2014");
		springLayout.putConstraint(SpringLayout.SOUTH, label_9, 0, SpringLayout.SOUTH, lblDataVencimento);
		springLayout.putConstraint(SpringLayout.EAST, label_9, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(label_9);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Produto Recolhido");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, 6, SpringLayout.SOUTH, label_9);
		springLayout.putConstraint(SpringLayout.EAST, chckbxNewCheckBox, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(chckbxNewCheckBox);

	}
}
