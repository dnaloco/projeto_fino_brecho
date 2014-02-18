package br.arthur.interfaces.cadastros;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;

public class CadastroPedido extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPedido frame = new CadastroPedido();
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
	public CadastroPedido() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Cadastro de Pedido");
		setBounds(100, 100, 443, 483);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				System.out.println("Mudei");
			}
		});
		getContentPane().add(tabbedPane);
		
		JPanel consigPanel = new JPanel();
		consigPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Consignatário", null, consigPanel, null);
		SpringLayout sl_consigPanel = new SpringLayout();
		consigPanel.setLayout(sl_consigPanel);
		
		JLabel lblId = new JLabel("ID:");
		sl_consigPanel.putConstraint(SpringLayout.NORTH, lblId, 10, SpringLayout.NORTH, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.WEST, lblId, 54, SpringLayout.WEST, consigPanel);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		consigPanel.add(lblId);
		
		JLabel lblNovo = new JLabel("NOVO");
		sl_consigPanel.putConstraint(SpringLayout.NORTH, lblNovo, 0, SpringLayout.NORTH, lblId);
		sl_consigPanel.putConstraint(SpringLayout.WEST, lblNovo, 6, SpringLayout.EAST, lblId);
		lblNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		consigPanel.add(lblNovo);
		
		JButton btnNewButton = new JButton(new ImageIcon(((new ImageIcon(
	            "C:\\Users\\arthur\\Pictures\\brecho\\Zoom-icon.png").getImage()
	            .getScaledInstance(16, 16,
	                    java.awt.Image.SCALE_SMOOTH)))));
		sl_consigPanel.putConstraint(SpringLayout.NORTH, btnNewButton, 8, SpringLayout.NORTH, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.WEST, btnNewButton, 87, SpringLayout.EAST, lblNovo);
		sl_consigPanel.putConstraint(SpringLayout.EAST, btnNewButton, -2, SpringLayout.EAST, consigPanel);
		btnNewButton.setText("Buscar Consignat\u00E1rio");
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		consigPanel.add(btnNewButton);
		
		JPanel panel = new JPanel();
		sl_consigPanel.putConstraint(SpringLayout.NORTH, panel, 8, SpringLayout.SOUTH, btnNewButton);
		sl_consigPanel.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, panel, -215, SpringLayout.SOUTH, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.EAST, panel, -2, SpringLayout.EAST, consigPanel);
		panel.setBorder(new TitledBorder(null, "Contato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		consigPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.gridwidth = 3;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 0;
		panel.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 1;
		panel.add(lblTelefone, gbc_lblTelefone);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.EAST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 5);
		gbc_lblCelular.gridx = 2;
		gbc_lblCelular.gridy = 1;
		panel.add(lblCelular, gbc_lblCelular);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSite = new JLabel("Site:");
		GridBagConstraints gbc_lblSite = new GridBagConstraints();
		gbc_lblSite.anchor = GridBagConstraints.EAST;
		gbc_lblSite.insets = new Insets(0, 0, 5, 5);
		gbc_lblSite.gridx = 2;
		gbc_lblSite.gridy = 2;
		panel.add(lblSite, gbc_lblSite);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 2;
		panel.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF.:");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 0, 5);
		gbc_lblCpf.gridx = 0;
		gbc_lblCpf.gridy = 3;
		panel.add(lblCpf, gbc_lblCpf);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 0, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 3;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblRg = new JLabel("R.G.:");
		GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.anchor = GridBagConstraints.EAST;
		gbc_lblRg.insets = new Insets(0, 0, 0, 5);
		gbc_lblRg.gridx = 2;
		gbc_lblRg.gridy = 3;
		panel.add(lblRg, gbc_lblRg);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 3;
		panel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		sl_consigPanel.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		sl_consigPanel.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, btnNewButton);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		consigPanel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		GridBagConstraints gbc_lblLogradouro = new GridBagConstraints();
		gbc_lblLogradouro.anchor = GridBagConstraints.EAST;
		gbc_lblLogradouro.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogradouro.gridx = 0;
		gbc_lblLogradouro.gridy = 0;
		panel_1.add(lblLogradouro, gbc_lblLogradouro);
		
		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.gridwidth = 4;
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 0;
		panel_1.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNmero = new GridBagConstraints();
		gbc_lblNmero.anchor = GridBagConstraints.EAST;
		gbc_lblNmero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmero.gridx = 0;
		gbc_lblNmero.gridy = 1;
		panel_1.add(lblNmero, gbc_lblNmero);
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 1;
		panel_1.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
		gbc_lblComplemento.anchor = GridBagConstraints.EAST;
		gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplemento.gridx = 2;
		gbc_lblComplemento.gridy = 1;
		panel_1.add(lblComplemento, gbc_lblComplemento);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.gridwidth = 2;
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 1;
		panel_1.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 2;
		panel_1.add(lblBairro, gbc_lblBairro);
		
		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.gridwidth = 2;
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 2;
		panel_1.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		GridBagConstraints gbc_lblCep = new GridBagConstraints();
		gbc_lblCep.insets = new Insets(0, 0, 5, 5);
		gbc_lblCep.anchor = GridBagConstraints.EAST;
		gbc_lblCep.gridx = 3;
		gbc_lblCep.gridy = 2;
		panel_1.add(lblCep, gbc_lblCep);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 2;
		panel_1.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 3;
		panel_1.add(lblCidade, gbc_lblCidade);
		
		textField_12 = new JTextField();
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.gridwidth = 2;
		gbc_textField_12.insets = new Insets(0, 0, 0, 5);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 1;
		gbc_textField_12.gridy = 3;
		panel_1.add(textField_12, gbc_textField_12);
		textField_12.setColumns(10);
		
		JLabel lblUf = new JLabel("U.F.:");
		GridBagConstraints gbc_lblUf = new GridBagConstraints();
		gbc_lblUf.anchor = GridBagConstraints.EAST;
		gbc_lblUf.insets = new Insets(0, 0, 0, 5);
		gbc_lblUf.gridx = 3;
		gbc_lblUf.gridy = 3;
		panel_1.add(lblUf, gbc_lblUf);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 3;
		panel_1.add(comboBox, gbc_comboBox);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, panel_1, -12, SpringLayout.NORTH, btnNewButton_1);
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -10, SpringLayout.SOUTH, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		consigPanel.add(btnNewButton_1);
		
		JButton btnLimpar = new JButton("Limpar");
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, btnLimpar, 0, SpringLayout.SOUTH, btnNewButton_1);
		sl_consigPanel.putConstraint(SpringLayout.EAST, btnLimpar, -6, SpringLayout.WEST, btnNewButton_1);
		consigPanel.add(btnLimpar);
		
		JButton btnNovo = new JButton("Novo");
		sl_consigPanel.putConstraint(SpringLayout.NORTH, btnNovo, 0, SpringLayout.NORTH, btnNewButton_1);
		sl_consigPanel.putConstraint(SpringLayout.WEST, btnNovo, 0, SpringLayout.WEST, panel);
		consigPanel.add(btnNovo);
		
		JPanel pecarPanel = new JPanel();
		tabbedPane.addTab("Produto", null, pecarPanel, null);
		SpringLayout sl_pecarPanel = new SpringLayout();
		pecarPanel.setLayout(sl_pecarPanel);
		
		JLabel lblNovoProduto = new JLabel("Novo Produto");
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, lblNovoProduto, 10, SpringLayout.NORTH, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, lblNovoProduto, 10, SpringLayout.WEST, pecarPanel);
		lblNovoProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
		pecarPanel.add(lblNovoProduto);
		
		JPanel panel_2 = new JPanel();
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, lblNovoProduto);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, panel_2, 130, SpringLayout.SOUTH, lblNovoProduto);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, panel_2, 421, SpringLayout.WEST, pecarPanel);
		panel_2.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pecarPanel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		panel_2.add(lblTtulo, gbc_lblTtulo);
		
		textField_16 = new JTextField();
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.gridwidth = 7;
		gbc_textField_16.insets = new Insets(0, 0, 5, 0);
		gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_16.gridx = 1;
		gbc_textField_16.gridy = 0;
		panel_2.add(textField_16, gbc_textField_16);
		textField_16.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 1;
		panel_2.add(lblCategoria, gbc_lblCategoria);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 4;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		panel_2.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 5;
		gbc_lblMarca.gridy = 1;
		panel_2.add(lblMarca, gbc_lblMarca);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.gridwidth = 2;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 6;
		gbc_comboBox_2.gridy = 1;
		panel_2.add(comboBox_2, gbc_comboBox_2);
		
		JLabel lblCor2 = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblCor2 = new GridBagConstraints();
		gbc_lblCor2.anchor = GridBagConstraints.EAST;
		gbc_lblCor2.insets = new Insets(0, 0, 0, 5);
		gbc_lblCor2.gridx = 0;
		gbc_lblCor2.gridy = 2;
		panel_2.add(lblCor2, gbc_lblCor2);
		
		textField_15 = new JTextField();
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.gridwidth = 2;
		gbc_textField_15.insets = new Insets(0, 0, 0, 5);
		gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15.gridx = 1;
		gbc_textField_15.gridy = 2;
		panel_2.add(textField_15, gbc_textField_15);
		textField_15.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Cor:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 0, 5);
		gbc_lblTamanho.gridx = 4;
		gbc_lblTamanho.gridy = 2;
		panel_2.add(lblTamanho, gbc_lblTamanho);
		
		textField_14 = new JTextField();
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 0, 5);
		gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14.gridx = 5;
		gbc_textField_14.gridy = 2;
		panel_2.add(textField_14, gbc_textField_14);
		textField_14.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantidade.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidade.gridx = 6;
		gbc_lblQuantidade.gridy = 2;
		panel_2.add(lblQuantidade, gbc_lblQuantidade);
		
		textField_13 = new JTextField();
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 7;
		gbc_textField_13.gridy = 2;
		panel_2.add(textField_13, gbc_textField_13);
		textField_13.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, panel_3, 20, SpringLayout.SOUTH, panel_2);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, panel_3, 0, SpringLayout.WEST, lblNovoProduto);
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, panel_3, 200, SpringLayout.SOUTH, panel_2);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, panel_2);
		panel_3.setBorder(new TitledBorder(null, "Condi\u00E7\u00F5es do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pecarPanel.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblCusto = new JLabel("Custo:");
		GridBagConstraints gbc_lblCusto = new GridBagConstraints();
		gbc_lblCusto.anchor = GridBagConstraints.EAST;
		gbc_lblCusto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCusto.gridx = 0;
		gbc_lblCusto.gridy = 0;
		panel_3.add(lblCusto, gbc_lblCusto);
		
		textField_18 = new JTextField();
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.insets = new Insets(0, 0, 5, 5);
		gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_18.gridx = 1;
		gbc_textField_18.gridy = 0;
		panel_3.add(textField_18, gbc_textField_18);
		textField_18.setColumns(10);
		
		JLabel lblMargem = new JLabel("Margem:");
		GridBagConstraints gbc_lblMargem = new GridBagConstraints();
		gbc_lblMargem.anchor = GridBagConstraints.EAST;
		gbc_lblMargem.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargem.gridx = 2;
		gbc_lblMargem.gridy = 0;
		panel_3.add(lblMargem, gbc_lblMargem);
		
		textField_17 = new JTextField();
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.insets = new Insets(0, 0, 5, 0);
		gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_17.gridx = 3;
		gbc_textField_17.gridy = 0;
		panel_3.add(textField_17, gbc_textField_17);
		textField_17.setColumns(10);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		GridBagConstraints gbc_lblDataEntrada = new GridBagConstraints();
		gbc_lblDataEntrada.anchor = GridBagConstraints.EAST;
		gbc_lblDataEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataEntrada.gridx = 0;
		gbc_lblDataEntrada.gridy = 1;
		panel_3.add(lblDataEntrada, gbc_lblDataEntrada);
		
		textField_19 = new JTextField();
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.insets = new Insets(0, 0, 5, 5);
		gbc_textField_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_19.gridx = 1;
		gbc_textField_19.gridy = 1;
		panel_3.add(textField_19, gbc_textField_19);
		textField_19.setColumns(10);
		
		JLabel lblValidade = new JLabel("Validade:");
		GridBagConstraints gbc_lblValidade = new GridBagConstraints();
		gbc_lblValidade.anchor = GridBagConstraints.EAST;
		gbc_lblValidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidade.gridx = 2;
		gbc_lblValidade.gridy = 1;
		panel_3.add(lblValidade, gbc_lblValidade);
		
		textField_20 = new JTextField();
		GridBagConstraints gbc_textField_20 = new GridBagConstraints();
		gbc_textField_20.insets = new Insets(0, 0, 5, 0);
		gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_20.gridx = 3;
		gbc_textField_20.gridy = 1;
		panel_3.add(textField_20, gbc_textField_20);
		textField_20.setColumns(10);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblSituao = new GridBagConstraints();
		gbc_lblSituao.anchor = GridBagConstraints.EAST;
		gbc_lblSituao.insets = new Insets(0, 0, 5, 5);
		gbc_lblSituao.gridx = 0;
		gbc_lblSituao.gridy = 2;
		panel_3.add(lblSituao, gbc_lblSituao);
		
		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 2;
		panel_3.add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 2;
		gbc_lblTipo.gridy = 2;
		panel_3.add(lblTipo, gbc_lblTipo);
		
		JComboBox comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 3;
		gbc_comboBox_4.gridy = 2;
		panel_3.add(comboBox_4, gbc_comboBox_4);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		GridBagConstraints gbc_lblObservaes = new GridBagConstraints();
		gbc_lblObservaes.anchor = GridBagConstraints.EAST;
		gbc_lblObservaes.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservaes.gridx = 0;
		gbc_lblObservaes.gridy = 3;
		panel_3.add(lblObservaes, gbc_lblObservaes);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.gridheight = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panel_3.add(textArea, gbc_textArea);
		
		JButton btnSalvar = new JButton("Salvar");
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, btnSalvar, -10, SpringLayout.SOUTH, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, panel_2);
		pecarPanel.add(btnSalvar);
		
		JButton btnLimpar_1 = new JButton("Limpar");
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, btnLimpar_1, 0, SpringLayout.NORTH, btnSalvar);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, btnLimpar_1, -6, SpringLayout.WEST, btnSalvar);
		pecarPanel.add(btnLimpar_1);
		
		JPanel imgPanel = new JPanel();
		tabbedPane.addTab("Inserir Imagens", null, imgPanel, null);
		SpringLayout sl_imgPanel = new SpringLayout();
		imgPanel.setLayout(sl_imgPanel);
		
		JPanel panel_4 = new JPanel();
		sl_imgPanel.putConstraint(SpringLayout.NORTH, panel_4, 10, SpringLayout.NORTH, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.WEST, panel_4, 10, SpringLayout.WEST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.EAST, panel_4, 421, SpringLayout.WEST, imgPanel);
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		imgPanel.add(panel_4);
		
		JButton btnInserir = new JButton("Inserir");
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnInserir, 10, SpringLayout.WEST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.SOUTH, panel_4, -6, SpringLayout.NORTH, btnInserir);
		sl_imgPanel.putConstraint(SpringLayout.SOUTH, btnInserir, -10, SpringLayout.SOUTH, imgPanel);
		imgPanel.add(btnInserir);
		
		JButton btnTrocar = new JButton("Trocar");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnTrocar, 0, SpringLayout.NORTH, btnInserir);
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnTrocar, 6, SpringLayout.EAST, btnInserir);
		imgPanel.add(btnTrocar);
		
		JButton btnExcluir = new JButton("Excluir");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnExcluir, 0, SpringLayout.NORTH, btnInserir);
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnTrocar);
		imgPanel.add(btnExcluir);
		
		JButton button = new JButton("<<");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, panel_4);
		sl_imgPanel.putConstraint(SpringLayout.WEST, button, 6, SpringLayout.EAST, btnExcluir);
		imgPanel.add(button);
		
		JButton button_1 = new JButton("<");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, button_1, 6, SpringLayout.SOUTH, panel_4);
		sl_imgPanel.putConstraint(SpringLayout.WEST, button_1, 6, SpringLayout.EAST, button);
		imgPanel.add(button_1);
		
		JButton button_2 = new JButton(">");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, btnInserir);
		imgPanel.add(button_2);
		
		JButton button_3 = new JButton(">>");
		sl_imgPanel.putConstraint(SpringLayout.EAST, button_3, -10, SpringLayout.EAST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.EAST, button_2, -6, SpringLayout.WEST, button_3);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, btnInserir);
		imgPanel.add(button_3);
		
		JLabel label = new JLabel("1/5");
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, label, 12, SpringLayout.SOUTH, panel_4);
		sl_imgPanel.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, button_1);
		sl_imgPanel.putConstraint(SpringLayout.EAST, label, -8, SpringLayout.WEST, button_2);
		imgPanel.add(label);
		
		JPanel finalizarPanel = new JPanel();
		tabbedPane.addTab("Finalizar", null, finalizarPanel, null);
		SpringLayout sl_finalizarPanel = new SpringLayout();
		finalizarPanel.setLayout(sl_finalizarPanel);
		
		JButton btnNewButton_2 = new JButton(new ImageIcon(((new ImageIcon(
	            "C:\\Users\\arthur\\Pictures\\brecho\\registration_queue.png").getImage()
	            .getScaledInstance(16, 16,
	                    java.awt.Image.SCALE_SMOOTH)))));
		btnNewButton_2.setText("Recuperar Pedido"); 
		// btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\arthur\\Pictures\\brecho\\registration_queue.png"));
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, btnNewButton_2, 4, SpringLayout.NORTH, finalizarPanel);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, btnNewButton_2, -10, SpringLayout.EAST, finalizarPanel);
		finalizarPanel.add(btnNewButton_2);
		
		JLabel lblNmeroPedido = new JLabel("N\u00FAmero Pedido:");
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, lblNmeroPedido, 10, SpringLayout.NORTH, finalizarPanel);
		lblNmeroPedido.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, lblNmeroPedido, 10, SpringLayout.WEST, finalizarPanel);
		finalizarPanel.add(lblNmeroPedido);
		
		JLabel label_1 = new JLabel("000000");
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, btnNewButton_2, 6, SpringLayout.EAST, label_1);
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, lblNmeroPedido);
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, label_1, 6, SpringLayout.EAST, lblNmeroPedido);
		finalizarPanel.add(label_1);
		
		JButton btnSalvar_1 = new JButton("Salvar Pedido");
		sl_finalizarPanel.putConstraint(SpringLayout.SOUTH, btnSalvar_1, -10, SpringLayout.SOUTH, finalizarPanel);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, btnSalvar_1, -10, SpringLayout.EAST, finalizarPanel);
		finalizarPanel.add(btnSalvar_1);
		
		JButton btnExcluirPedido = new JButton("Excluir Pedido");
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, btnExcluirPedido, 0, SpringLayout.NORTH, btnSalvar_1);
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, btnExcluirPedido, 0, SpringLayout.WEST, lblNmeroPedido);
		finalizarPanel.add(btnExcluirPedido);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Resumo do Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, panel_5, 6, SpringLayout.SOUTH, btnNewButton_2);
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, panel_5, 10, SpringLayout.WEST, finalizarPanel);
		sl_finalizarPanel.putConstraint(SpringLayout.SOUTH, panel_5, -6, SpringLayout.NORTH, btnSalvar_1);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, panel_5, 0, SpringLayout.EAST, btnNewButton_2);
		finalizarPanel.add(panel_5);
	}
}
