package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JFormattedTextField.AbstractFormatter;
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

import br.arthur.utils.JNumericField;

import javax.swing.JFormattedTextField;

public class CadastroProduto extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static long theId;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
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
	public CadastroProduto(long theId) {
		setTitle("Cadastro de Produto");
		this.theId = theId;
		setBounds(100, 100, 530, 622);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblProduto = new JLabel("Cód. Produto");
		lblProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblProduto);
		
		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio Nome");
		springLayout.putConstraint(SpringLayout.EAST, lblConsignatrio, -197, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblConsignatrio);
		
		JButton btnAvaliarProduto = new JButton("Buscar Produto");
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
		panel.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.EAST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 0;
		panel.add(lblDescrio, gbc_lblDescrio);
		
		textField = new JTextField();
		textField.setEnabled(false);
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
		comboBox.setEnabled(false);
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
		comboBox_1.setEnabled(false);
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
		textField_1.setEnabled(false);
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
		textField_2.setEnabled(false);
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
		textField_3.setEnabled(false);
		textField_3.setText("1");
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 7;
		gbc_textField_3.gridy = 2;
		panel.add(textField_3, gbc_textField_3);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, lblProduto);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel_1);
		
		JButton btnTrocarFoto = new JButton("Trocar Foto");
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -6, SpringLayout.WEST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.WEST, btnTrocarFoto, 308, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnTrocarFoto, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnTrocarFoto);
		
		JButton btnNewButton_1 = new JButton("Salvar Produto");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, btnNewButton_1);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnNewButton_1);
		
		JButton btnExcluirProduto = new JButton("Excluir Produto");
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProduto, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProduto, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnTrocarFoto, -6, SpringLayout.NORTH, btnExcluirProduto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExcluirProduto, -7, SpringLayout.NORTH, btnNewButton_1);
		getContentPane().add(btnExcluirProduto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVisible(false);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -43, SpringLayout.WEST, btnAvaliarProduto);
		lblNewLabel.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 2, SpringLayout.NORTH, lblProduto);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		springLayout.putConstraint(SpringLayout.WEST, lblDataEntrada, 8, SpringLayout.EAST, panel_1);
		getContentPane().add(lblDataEntrada);
		
		JLabel label_8 = new JLabel("00/00/0000");
		springLayout.putConstraint(SpringLayout.NORTH, label_8, 0, SpringLayout.NORTH, lblDataEntrada);
		springLayout.putConstraint(SpringLayout.EAST, label_8, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(label_8);
		
		JLabel lblDataVencimento = new JLabel("Data Vencimento:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataVencimento, 6, SpringLayout.SOUTH, lblDataEntrada);
		springLayout.putConstraint(SpringLayout.WEST, lblDataVencimento, 6, SpringLayout.EAST, panel_1);
		getContentPane().add(lblDataVencimento);
		
		JLabel label_9 = new JLabel("00/00/0000");
		springLayout.putConstraint(SpringLayout.NORTH, label_9, 6, SpringLayout.SOUTH, label_8);
		springLayout.putConstraint(SpringLayout.EAST, label_9, 0, SpringLayout.EAST, btnAvaliarProduto);
		getContentPane().add(label_9);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Produto Devolvido p/ Consign.");
		springLayout.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, chckbxNewCheckBox, -6, SpringLayout.NORTH, btnTrocarFoto);
		getContentPane().add(chckbxNewCheckBox);
		
		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, lblDataEntrada, 6, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, lblProduto);
		springLayout.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, btnAvaliarProduto);
		panel_2.setBorder(new TitledBorder(null,
						"Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		getContentPane().add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label_7 = new JLabel("Comis. %:");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 0;
		panel_2.add(label_7, gbc_label_7);
		
		JNumericField numericField = new JNumericField();
		numericField.setMaxLength(2);
		numericField.setEnabled(false);
		numericField.setColumns(10);
		GridBagConstraints gbc_numericField = new GridBagConstraints();
		gbc_numericField.fill = GridBagConstraints.HORIZONTAL;
		gbc_numericField.gridwidth = 2;
		gbc_numericField.insets = new Insets(0, 0, 5, 5);
		gbc_numericField.gridx = 1;
		gbc_numericField.gridy = 0;
		panel_2.add(numericField, gbc_numericField);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.gridwidth = 2;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 0;
		panel_2.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel label_10 = new JLabel("Venda R$:");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 3;
		gbc_label_10.gridy = 0;
		panel_2.add(label_10, gbc_label_10);
		
		JNumericField numericField_1 = new JNumericField();
		numericField_1.setMaxLength(6);
		numericField_1.setEnabled(false);
		numericField_1.setColumns(10);
		GridBagConstraints gbc_numericField_1 = new GridBagConstraints();
		gbc_numericField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_numericField_1.insets = new Insets(0, 0, 5, 5);
		gbc_numericField_1.gridx = 4;
		gbc_numericField_1.gridy = 0;
		panel_2.add(numericField_1, gbc_numericField_1);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 0;
		panel_2.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel label_11 = new JLabel("Margem. %");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 5;
		gbc_label_11.gridy = 0;
		panel_2.add(label_11, gbc_label_11);
		
		JNumericField numericField_2 = new JNumericField();
		numericField_2.setMaxLength(2);
		numericField_2.setEnabled(false);
		numericField_2.setColumns(10);
		GridBagConstraints gbc_numericField_2 = new GridBagConstraints();
		gbc_numericField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_numericField_2.gridwidth = 2;
		gbc_numericField_2.insets = new Insets(0, 0, 5, 0);
		gbc_numericField_2.gridx = 6;
		gbc_numericField_2.gridy = 0;
		panel_2.add(numericField_2, gbc_numericField_2);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.gridwidth = 2;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 6;
		gbc_textField_7.gridy = 0;
		panel_2.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel label_12 = new JLabel("Data In\u00EDcio:");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.EAST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 0;
		gbc_label_12.gridy = 1;
		panel_2.add(label_12, gbc_label_12);
		
		JFormattedTextField formattedTextField = new JFormattedTextField((AbstractFormatter) null);
		formattedTextField.setEnabled(false);
		formattedTextField.setColumns(10);
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.anchor = GridBagConstraints.NORTH;
		gbc_formattedTextField.gridwidth = 3;
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextField.gridx = 1;
		gbc_formattedTextField.gridy = 1;
		panel_2.add(formattedTextField, gbc_formattedTextField);
		
		JLabel label_13 = new JLabel("Validade:");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.anchor = GridBagConstraints.EAST;
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.gridx = 4;
		gbc_label_13.gridy = 1;
		panel_2.add(label_13, gbc_label_13);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.gridx = 5;
		gbc_comboBox_2.gridy = 1;
		panel_2.add(comboBox_2, gbc_comboBox_2);
		
		JLabel label_14 = new JLabel("Tipo:");
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.EAST;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 0;
		gbc_label_14.gridy = 2;
		panel_2.add(label_14, gbc_label_14);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setEnabled(false);
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridwidth = 3;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 2;
		panel_2.add(comboBox_3, gbc_comboBox_3);
		
		JLabel label_15 = new JLabel("Localiza\u00E7\u00E3o:");
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.anchor = GridBagConstraints.EAST;
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.gridx = 4;
		gbc_label_15.gridy = 2;
		panel_2.add(label_15, gbc_label_15);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 2;
		panel_2.add(textField_4, gbc_textField_4);
		
		JLabel label_16 = new JLabel("Comiss\u00E3o R$:");
		label_16.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_label_16 = new GridBagConstraints();
		gbc_label_16.insets = new Insets(0, 0, 0, 5);
		gbc_label_16.gridx = 0;
		gbc_label_16.gridy = 3;
		panel_2.add(label_16, gbc_label_16);
		
		JLabel label_17 = new JLabel("R$ 0,00");
		label_17.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_label_17 = new GridBagConstraints();
		gbc_label_17.gridwidth = 3;
		gbc_label_17.insets = new Insets(0, 0, 0, 5);
		gbc_label_17.gridx = 1;
		gbc_label_17.gridy = 3;
		panel_2.add(label_17, gbc_label_17);
		
		JLabel label_18 = new JLabel("Custo R$:");
		label_18.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_label_18 = new GridBagConstraints();
		gbc_label_18.insets = new Insets(0, 0, 0, 5);
		gbc_label_18.gridx = 4;
		gbc_label_18.gridy = 3;
		panel_2.add(label_18, gbc_label_18);
		
		JLabel label_19 = new JLabel("R$ 0,00");
		label_19.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_label_19 = new GridBagConstraints();
		gbc_label_19.gridwidth = 3;
		gbc_label_19.gridx = 5;
		gbc_label_19.gridy = 3;
		panel_2.add(label_19, gbc_label_19);
		
		JCheckBox chckbxProdutoNoEncontrado = new JCheckBox("Produto n\u00E3o encontrado");
		springLayout.putConstraint(SpringLayout.WEST, chckbxProdutoNoEncontrado, 6, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, chckbxProdutoNoEncontrado, -6, SpringLayout.NORTH, chckbxNewCheckBox);
		getContentPane().add(chckbxProdutoNoEncontrado);
		
		JLabel lblPossuiDevoluo = new JLabel("Possui Devolu\u00E7\u00E3o:");
		springLayout.putConstraint(SpringLayout.WEST, lblPossuiDevoluo, 10, SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, lblPossuiDevoluo, -6, SpringLayout.NORTH, chckbxProdutoNoEncontrado);
		getContentPane().add(lblPossuiDevoluo);
		
		JLabel lblSimno = new JLabel("sim/n\u00E3o");
		springLayout.putConstraint(SpringLayout.NORTH, lblSimno, 0, SpringLayout.NORTH, lblPossuiDevoluo);
		springLayout.putConstraint(SpringLayout.WEST, lblSimno, 6, SpringLayout.EAST, lblPossuiDevoluo);
		getContentPane().add(lblSimno);

	}
}
