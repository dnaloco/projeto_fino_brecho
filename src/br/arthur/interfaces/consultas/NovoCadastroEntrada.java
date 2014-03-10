package br.arthur.interfaces.consultas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

public class NovoCadastroEntrada extends JInternalFrame {
	private JTextField txtCodprod;
	private JTextField txtSituacao;
	private JTextField txtConsign;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoCadastroEntrada frame = new NovoCadastroEntrada();
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
	public NovoCadastroEntrada() {
		setBounds(100, 100, 919, 561);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCdProduto = new JLabel("C\u00F3d. Produto:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCdProduto, 14, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCdProduto, -801, SpringLayout.EAST, getContentPane());
		lblCdProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblCdProduto);
		
		txtCodprod = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCodprod, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtCodprod, 6, SpringLayout.EAST, lblCdProduto);
		springLayout.putConstraint(SpringLayout.EAST, txtCodprod, 95, SpringLayout.EAST, lblCdProduto);
		txtCodprod.setEditable(false);
		txtCodprod.setText("CodProd");
		getContentPane().add(txtCodprod);
		txtCodprod.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Situa\u00E7\u00E3o:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, lblCdProduto);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -610, SpringLayout.EAST, getContentPane());
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblNewLabel);
		
		txtSituacao = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtSituacao, -4, SpringLayout.NORTH, lblCdProduto);
		springLayout.putConstraint(SpringLayout.WEST, txtSituacao, 6, SpringLayout.EAST, lblNewLabel);
		txtSituacao.setEditable(false);
		txtSituacao.setText("Situacao");
		getContentPane().add(txtSituacao);
		txtSituacao.setColumns(10);
		
		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		springLayout.putConstraint(SpringLayout.NORTH, lblConsignatrio, 0, SpringLayout.NORTH, lblCdProduto);
		lblConsignatrio.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblConsignatrio);
		
		txtConsign = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtConsign, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtConsign, 6, SpringLayout.EAST, lblConsignatrio);
		txtConsign.setText("Consign");
		getContentPane().add(txtConsign);
		txtConsign.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		springLayout.putConstraint(SpringLayout.EAST, btnBuscar, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtConsign, -6, SpringLayout.WEST, btnBuscar);
		springLayout.putConstraint(SpringLayout.WEST, btnBuscar, 787, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscar, 10, SpringLayout.NORTH, getContentPane());
		getContentPane().add(btnBuscar);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, lblConsignatrio, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 461, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, btnBuscar);
		panel.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 308, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, getContentPane());
		panel_1.setBorder(new TitledBorder(null, "Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 9;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 0;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 4;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Marca:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanho.gridx = 0;
		gbc_lblTamanho.gridy = 2;
		panel.add(lblTamanho, gbc_lblTamanho);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cor:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 2;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Qtde.:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 7;
		gbc_lblNewLabel_5.gridy = 2;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 8;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblObservao = new GridBagConstraints();
		gbc_lblObservao.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservao.gridx = 0;
		gbc_lblObservao.gridy = 3;
		panel.add(lblObservao, gbc_lblObservao);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 9;
		gbc_textArea.gridheight = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panel.add(textArea, gbc_textArea);
		getContentPane().add(panel_1);
		
		JButton btnImportarFotos = new JButton("IMPORTAR FOTOS");
		springLayout.putConstraint(SpringLayout.NORTH, btnImportarFotos, 6, SpringLayout.SOUTH, txtConsign);
		springLayout.putConstraint(SpringLayout.WEST, btnImportarFotos, 461, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnImportarFotos);
		
		JButton button = new JButton(">>");
		springLayout.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, btnBuscar);
		springLayout.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(button);
		
		JButton button_1 = new JButton(">");
		springLayout.putConstraint(SpringLayout.NORTH, button_1, 6, SpringLayout.SOUTH, btnBuscar);
		springLayout.putConstraint(SpringLayout.EAST, button_1, -6, SpringLayout.WEST, button);
		getContentPane().add(button_1);
		
		JLabel label = new JLabel("999/999");
		springLayout.putConstraint(SpringLayout.NORTH, label, 13, SpringLayout.SOUTH, txtConsign);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, label, -65, SpringLayout.WEST, button_1);
		springLayout.putConstraint(SpringLayout.EAST, label, -6, SpringLayout.WEST, button_1);
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(label);
		
		JButton button_2 = new JButton("<");
		springLayout.putConstraint(SpringLayout.NORTH, button_2, 6, SpringLayout.SOUTH, txtConsign);
		springLayout.putConstraint(SpringLayout.EAST, button_2, -6, SpringLayout.WEST, label);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("<<");
		springLayout.putConstraint(SpringLayout.EAST, btnImportarFotos, -6, SpringLayout.WEST, button_3);
		springLayout.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, button);
		springLayout.putConstraint(SpringLayout.EAST, button_3, -6, SpringLayout.WEST, button_2);
		getContentPane().add(button_3);
		
		JButton btnExcluirProd = new JButton("EXCLUIR PRODUTO");
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirProd, 6, SpringLayout.SOUTH, button);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProd, 0, SpringLayout.EAST, btnBuscar);
		getContentPane().add(btnExcluirProd);
		
		JButton btnExcluirFoto = new JButton("EXCLUIR FOTO");
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirFoto, 270, SpringLayout.SOUTH, txtCodprod);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirFoto, 8, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnExcluirFoto);
		
		JButton btnTrocarFoto = new JButton("TROCAR FOTO");
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 6, SpringLayout.EAST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.WEST, btnTrocarFoto, 233, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnTrocarFoto, -452, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirFoto, -6, SpringLayout.WEST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnTrocarFoto, -171, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnTrocarFoto);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, txtCodprod);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, lblCdProduto);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, 1, SpringLayout.NORTH, btnExcluirFoto);
		springLayout.putConstraint(SpringLayout.EAST, panel_2, -6, SpringLayout.WEST, panel);
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_3, -452, SpringLayout.EAST, getContentPane());
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 6, SpringLayout.SOUTH, btnExcluirFoto);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 161, SpringLayout.SOUTH, btnExcluirFoto);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblMargem = new JLabel("Margem %:");
		GridBagConstraints gbc_lblMargem = new GridBagConstraints();
		gbc_lblMargem.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargem.gridx = 0;
		gbc_lblMargem.gridy = 0;
		panel_1.add(lblMargem, gbc_lblMargem);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.gridwidth = 2;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 0;
		panel_1.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblVendaR = new JLabel("Venda R$:");
		GridBagConstraints gbc_lblVendaR = new GridBagConstraints();
		gbc_lblVendaR.anchor = GridBagConstraints.EAST;
		gbc_lblVendaR.insets = new Insets(0, 0, 5, 5);
		gbc_lblVendaR.gridx = 3;
		gbc_lblVendaR.gridy = 0;
		panel_1.add(lblVendaR, gbc_lblVendaR);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 0;
		panel_1.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblComisso = new JLabel("Comis. %");
		GridBagConstraints gbc_lblComisso = new GridBagConstraints();
		gbc_lblComisso.anchor = GridBagConstraints.EAST;
		gbc_lblComisso.insets = new Insets(0, 0, 5, 5);
		gbc_lblComisso.gridx = 5;
		gbc_lblComisso.gridy = 0;
		panel_1.add(lblComisso, gbc_lblComisso);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.gridwidth = 2;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 6;
		gbc_textField_6.gridy = 0;
		panel_1.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio:");
		GridBagConstraints gbc_lblDataIncio = new GridBagConstraints();
		gbc_lblDataIncio.anchor = GridBagConstraints.EAST;
		gbc_lblDataIncio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataIncio.gridx = 0;
		gbc_lblDataIncio.gridy = 1;
		panel_1.add(lblDataIncio, gbc_lblDataIncio);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 3;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblValidade = new JLabel("Validade:");
		GridBagConstraints gbc_lblValidade = new GridBagConstraints();
		gbc_lblValidade.anchor = GridBagConstraints.EAST;
		gbc_lblValidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidade.gridx = 4;
		gbc_lblValidade.gridy = 1;
		panel_1.add(lblValidade, gbc_lblValidade);
		
		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.gridwidth = 3;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 5;
		gbc_comboBox_3.gridy = 1;
		panel_1.add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 2;
		panel_1.add(lblTipo, gbc_lblTipo);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 2;
		panel_1.add(comboBox_2, gbc_comboBox_2);
		
		JLabel lblDataVencimento = new JLabel("Localiza\u00E7\u00E3o:");
		GridBagConstraints gbc_lblDataVencimento = new GridBagConstraints();
		gbc_lblDataVencimento.anchor = GridBagConstraints.EAST;
		gbc_lblDataVencimento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataVencimento.gridx = 4;
		gbc_lblDataVencimento.gridy = 2;
		panel_1.add(lblDataVencimento, gbc_lblDataVencimento);
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.gridwidth = 3;
		gbc_textField_8.insets = new Insets(0, 0, 5, 0);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 5;
		gbc_textField_8.gridy = 2;
		panel_1.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblComissoR = new JLabel("Comiss\u00E3o R$:");
		lblComissoR.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblComissoR = new GridBagConstraints();
		gbc_lblComissoR.insets = new Insets(0, 0, 0, 5);
		gbc_lblComissoR.gridx = 0;
		gbc_lblComissoR.gridy = 3;
		panel_1.add(lblComissoR, gbc_lblComissoR);
		
		JLabel lblNewLabel_6 = new JLabel("R$ 0,00");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 3;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 3;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblCustoR = new JLabel("Custo R$:");
		lblCustoR.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblCustoR = new GridBagConstraints();
		gbc_lblCustoR.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustoR.gridx = 4;
		gbc_lblCustoR.gridy = 3;
		panel_1.add(lblCustoR, gbc_lblCustoR);
		
		JLabel lblNewLabel_7 = new JLabel("R$ 0,00");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridwidth = 3;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 5;
		gbc_lblNewLabel_7.gridy = 3;
		panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
		getContentPane().add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "name_7514832869496");
		
		JButton btnExcluirLista = new JButton("EXCLUIR LISTA");
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirLista, 6, SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirLista, -253, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProd, 6, SpringLayout.EAST, btnExcluirLista);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirLista, 6, SpringLayout.SOUTH, btnImportarFotos);
		getContentPane().add(btnExcluirLista);
		
		JButton btnNewButton = new JButton("ADICIONAR PRODUTO");
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -6, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 6, SpringLayout.EAST, panel_3);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 225, SpringLayout.EAST, panel_3);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SALVAR PRODUTO");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnBuscar);
		getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenu mnProdutos = new JMenu("Produtos");
		mnCadastro.add(mnProdutos);
		
		JMenuItem mntmImportarFotos = new JMenuItem("Importar Fotos");
		mnProdutos.add(mntmImportarFotos);
		
		JMenuItem mntmExcluirLista = new JMenuItem("Excluir Lista");
		mnProdutos.add(mntmExcluirLista);
		
		JMenuItem mntmAdicionarProduto = new JMenuItem("Adicionar Produto");
		mnProdutos.add(mntmAdicionarProduto);
		
		JMenuItem mntmExcluirProduto = new JMenuItem("Excluir Produto");
		mnProdutos.add(mntmExcluirProduto);
		
		JMenuItem mntmConsignatrio = new JMenuItem("Consignat\u00E1rio");
		mnCadastro.add(mntmConsignatrio);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mnCadastro.add(mntmCategoria);
		
		JMenuItem mntmMarca = new JMenuItem("Marca");
		mnCadastro.add(mntmMarca);
		
		JMenuItem mntmTipo = new JMenuItem("Tipo");
		mnCadastro.add(mntmTipo);
		
		JMenu mnCdigoDeBarras = new JMenu("C\u00F3digo de Barras");
		menuBar.add(mnCdigoDeBarras);
		
		JMenuItem mntmImprimirCdigos = new JMenuItem("Imprimir C\u00F3digos");
		mnCdigoDeBarras.add(mntmImprimirCdigos);

	}
}
