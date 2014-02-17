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
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, panel_2, 148, SpringLayout.SOUTH, lblNovoProduto);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, panel_2, 421, SpringLayout.WEST, pecarPanel);
		pecarPanel.add(panel_2);
		
		JPanel imgPanel = new JPanel();
		tabbedPane.addTab("Inserir Imagens", null, imgPanel, null);
		
		JPanel finalizarPanel = new JPanel();
		tabbedPane.addTab("Finalizar", null, finalizarPanel, null);

		System.out.println(panel_1.getComponents().length);
		for (Component c : panel_1.getComponents()) {
			c.setEnabled(false);
		}
	}
}
