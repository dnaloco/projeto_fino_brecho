package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.arthur.utils.JNumericField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProdutoCadastroDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel label_1;
	private JTextField txtDescricao;
	private JTextField txtQuantidade;
	private JLabel label_2;
	private JTextField txtComis;
	private JTextField txtVenda;
	private JTextField txtMargem;
	private JTextField txtLocal;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblEntrada;
	private JLabel lblVencimento;
	private JLabel label_7;
	private JCheckBox chkNEncontrado;
	private JCheckBox chkDevolvido;
	private JButton button_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProdutoCadastroDialog dialog = new ProdutoCadastroDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProdutoCadastroDialog() {
		setBounds(100, 100, 601, 662);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			label_1 = new JLabel("C\u00F3d. Produto");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, label_1, 5, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, contentPanel);
			label_1.setFont(new Font("SansSerif", Font.BOLD, 14));
			contentPanel.add(label_1);
		}
		{
			label_2 = new JLabel("Consignat\u00E1rio Nome");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, label_2, 2, SpringLayout.NORTH, label_1);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_2, -261, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, label_2, -10, SpringLayout.EAST, contentPanel);
			contentPanel.add(label_2);
		}
		{
			panel_1 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 11, SpringLayout.SOUTH, label_1);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, label_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, contentPanel);
			panel_1.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			{
				JLabel label = new JLabel("Descri\u00E7\u00E3o:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel_1.add(label, gbc_label);
			}
			{
				txtDescricao = new JTextField();
				txtDescricao.setEnabled(false);
				txtDescricao.setColumns(10);
				GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
				gbc_txtDescricao.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDescricao.gridwidth = 10;
				gbc_txtDescricao.insets = new Insets(0, 0, 5, 0);
				gbc_txtDescricao.gridx = 1;
				gbc_txtDescricao.gridy = 0;
				panel_1.add(txtDescricao, gbc_txtDescricao);
			}
			{
				JLabel label = new JLabel("Categoria:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				panel_1.add(label, gbc_label);
			}
			{
				JComboBox cmbCategoria = new JComboBox();
				cmbCategoria.setEnabled(false);
				GridBagConstraints gbc_cmbCategoria = new GridBagConstraints();
				gbc_cmbCategoria.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbCategoria.gridwidth = 5;
				gbc_cmbCategoria.insets = new Insets(0, 0, 5, 5);
				gbc_cmbCategoria.gridx = 1;
				gbc_cmbCategoria.gridy = 1;
				panel_1.add(cmbCategoria, gbc_cmbCategoria);
			}
			{
				JLabel label = new JLabel("Marca:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.gridwidth = 2;
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 7;
				gbc_label.gridy = 1;
				panel_1.add(label, gbc_label);
			}
			{
				JComboBox cmbMarca = new JComboBox();
				cmbMarca.setEnabled(false);
				GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
				gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbMarca.gridwidth = 2;
				gbc_cmbMarca.insets = new Insets(0, 0, 5, 0);
				gbc_cmbMarca.gridx = 9;
				gbc_cmbMarca.gridy = 1;
				panel_1.add(cmbMarca, gbc_cmbMarca);
			}
			{
				JLabel label = new JLabel("Tamanho:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 2;
				panel_1.add(label, gbc_label);
			}
			
			JComboBox cmbTamanho = new JComboBox();
			GridBagConstraints gbc_cmbTamanho = new GridBagConstraints();
			gbc_cmbTamanho.gridwidth = 4;
			gbc_cmbTamanho.insets = new Insets(0, 0, 5, 5);
			gbc_cmbTamanho.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbTamanho.gridx = 1;
			gbc_cmbTamanho.gridy = 2;
			panel_1.add(cmbTamanho, gbc_cmbTamanho);
			{
				JLabel label = new JLabel("Cor:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 5;
				gbc_label.gridy = 2;
				panel_1.add(label, gbc_label);
			}
			
			JComboBox cmbCor = new JComboBox();
			GridBagConstraints gbc_cmbCor = new GridBagConstraints();
			gbc_cmbCor.gridwidth = 3;
			gbc_cmbCor.insets = new Insets(0, 0, 5, 5);
			gbc_cmbCor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbCor.gridx = 6;
			gbc_cmbCor.gridy = 2;
			panel_1.add(cmbCor, gbc_cmbCor);
			{
				JLabel label = new JLabel("Quantidade:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 9;
				gbc_label.gridy = 2;
				panel_1.add(label, gbc_label);
			}
			{
				txtQuantidade = new JTextField();
				txtQuantidade.setText("1");
				txtQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
				txtQuantidade.setEnabled(false);
				txtQuantidade.setColumns(10);
				GridBagConstraints gbc_txtQuantidade = new GridBagConstraints();
				gbc_txtQuantidade.insets = new Insets(0, 0, 5, 0);
				gbc_txtQuantidade.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtQuantidade.gridx = 10;
				gbc_txtQuantidade.gridy = 2;
				panel_1.add(txtQuantidade, gbc_txtQuantidade);
			}
		}
		{
			panel_2 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_1, -6, SpringLayout.NORTH, panel_2);
			
			JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
			GridBagConstraints gbc_lblObservao = new GridBagConstraints();
			gbc_lblObservao.insets = new Insets(0, 0, 5, 5);
			gbc_lblObservao.gridx = 0;
			gbc_lblObservao.gridy = 3;
			panel_1.add(lblObservao, gbc_lblObservao);
			
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridheight = 2;
			gbc_scrollPane.gridwidth = 10;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			panel_1.add(scrollPane, gbc_scrollPane);
			
			JTextArea textAreaObserv = new JTextArea();
			textAreaObserv.setEnabled(false);
			scrollPane.setViewportView(textAreaObserv);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, label_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, contentPanel);
			panel_2.setBorder(new TitledBorder(null,
									"Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING,
									TitledBorder.TOP, null, null));
			contentPanel.add(panel_2);
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			{
				JLabel label = new JLabel("Comis. %:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel_2.add(label, gbc_label);
			}
			{
				txtComis = new JTextField();
				txtComis.setEnabled(false);
				txtComis.setColumns(10);
				GridBagConstraints gbc_txtComis = new GridBagConstraints();
				gbc_txtComis.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtComis.gridwidth = 2;
				gbc_txtComis.insets = new Insets(0, 0, 5, 5);
				gbc_txtComis.gridx = 1;
				gbc_txtComis.gridy = 0;
				panel_2.add(txtComis, gbc_txtComis);
			}
			{
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
			}
			{
				JLabel label = new JLabel("Venda R$:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 3;
				gbc_label.gridy = 0;
				panel_2.add(label, gbc_label);
			}
			{
				txtVenda = new JTextField();
				txtVenda.setEnabled(false);
				txtVenda.setColumns(10);
				GridBagConstraints gbc_txtVenda = new GridBagConstraints();
				gbc_txtVenda.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtVenda.insets = new Insets(0, 0, 5, 5);
				gbc_txtVenda.gridx = 4;
				gbc_txtVenda.gridy = 0;
				panel_2.add(txtVenda, gbc_txtVenda);
			}
			{
				JNumericField numericField = new JNumericField();
				numericField.setMaxLength(6);
				numericField.setEnabled(false);
				numericField.setColumns(10);
				GridBagConstraints gbc_numericField = new GridBagConstraints();
				gbc_numericField.fill = GridBagConstraints.HORIZONTAL;
				gbc_numericField.insets = new Insets(0, 0, 5, 5);
				gbc_numericField.gridx = 4;
				gbc_numericField.gridy = 0;
				panel_2.add(numericField, gbc_numericField);
			}
			{
				JLabel label = new JLabel("Margem. %");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 5;
				gbc_label.gridy = 0;
				panel_2.add(label, gbc_label);
			}
			{
				txtMargem = new JTextField();
				txtMargem.setEnabled(false);
				txtMargem.setColumns(10);
				GridBagConstraints gbc_txtMargem = new GridBagConstraints();
				gbc_txtMargem.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtMargem.gridwidth = 2;
				gbc_txtMargem.insets = new Insets(0, 0, 5, 0);
				gbc_txtMargem.gridx = 6;
				gbc_txtMargem.gridy = 0;
				panel_2.add(txtMargem, gbc_txtMargem);
			}
			{
				JNumericField numericField = new JNumericField();
				numericField.setMaxLength(2);
				numericField.setEnabled(false);
				numericField.setColumns(10);
				GridBagConstraints gbc_numericField = new GridBagConstraints();
				gbc_numericField.fill = GridBagConstraints.HORIZONTAL;
				gbc_numericField.gridwidth = 2;
				gbc_numericField.insets = new Insets(0, 0, 5, 0);
				gbc_numericField.gridx = 6;
				gbc_numericField.gridy = 0;
				panel_2.add(numericField, gbc_numericField);
			}
			{
				JLabel label = new JLabel("Data In\u00EDcio:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				panel_2.add(label, gbc_label);
			}
			{
				JFormattedTextField txtDataInicio = new JFormattedTextField();
				txtDataInicio.setEnabled(false);
				txtDataInicio.setColumns(10);
				GridBagConstraints gbc_txtDataInicio = new GridBagConstraints();
				gbc_txtDataInicio.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDataInicio.anchor = GridBagConstraints.NORTH;
				gbc_txtDataInicio.gridwidth = 3;
				gbc_txtDataInicio.insets = new Insets(0, 0, 5, 5);
				gbc_txtDataInicio.gridx = 1;
				gbc_txtDataInicio.gridy = 1;
				panel_2.add(txtDataInicio, gbc_txtDataInicio);
			}
			{
				JLabel label = new JLabel("Validade:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 4;
				gbc_label.gridy = 1;
				panel_2.add(label, gbc_label);
			}
			{
				JComboBox cmbValidade = new JComboBox();
				cmbValidade.setEnabled(false);
				GridBagConstraints gbc_cmbValidade = new GridBagConstraints();
				gbc_cmbValidade.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbValidade.gridwidth = 3;
				gbc_cmbValidade.insets = new Insets(0, 0, 5, 0);
				gbc_cmbValidade.gridx = 5;
				gbc_cmbValidade.gridy = 1;
				panel_2.add(cmbValidade, gbc_cmbValidade);
			}
			{
				JLabel label = new JLabel("Tipo:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 2;
				panel_2.add(label, gbc_label);
			}
			{
				JComboBox cmbTipo = new JComboBox();
				cmbTipo.setEnabled(false);
				GridBagConstraints gbc_cmbTipo = new GridBagConstraints();
				gbc_cmbTipo.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbTipo.gridwidth = 3;
				gbc_cmbTipo.insets = new Insets(0, 0, 5, 5);
				gbc_cmbTipo.gridx = 1;
				gbc_cmbTipo.gridy = 2;
				panel_2.add(cmbTipo, gbc_cmbTipo);
			}
			{
				JLabel label = new JLabel("Localiza\u00E7\u00E3o:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 4;
				gbc_label.gridy = 2;
				panel_2.add(label, gbc_label);
			}
			{
				txtLocal = new JTextField();
				txtLocal.setEnabled(false);
				txtLocal.setColumns(10);
				GridBagConstraints gbc_txtLocal = new GridBagConstraints();
				gbc_txtLocal.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtLocal.gridwidth = 3;
				gbc_txtLocal.insets = new Insets(0, 0, 5, 0);
				gbc_txtLocal.gridx = 5;
				gbc_txtLocal.gridy = 2;
				panel_2.add(txtLocal, gbc_txtLocal);
			}
			{
				JLabel label = new JLabel("Comiss\u00E3o R$:");
				label.setFont(new Font("SansSerif", Font.BOLD, 13));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 3;
				panel_2.add(label, gbc_label);
			}
			{
				JLabel lblComis = new JLabel("R$ 0,00");
				lblComis.setFont(new Font("SansSerif", Font.BOLD, 13));
				GridBagConstraints gbc_lblComis = new GridBagConstraints();
				gbc_lblComis.gridwidth = 3;
				gbc_lblComis.insets = new Insets(0, 0, 0, 5);
				gbc_lblComis.gridx = 1;
				gbc_lblComis.gridy = 3;
				panel_2.add(lblComis, gbc_lblComis);
			}
			{
				JLabel label = new JLabel("Custo R$:");
				label.setFont(new Font("SansSerif", Font.BOLD, 13));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 4;
				gbc_label.gridy = 3;
				panel_2.add(label, gbc_label);
			}
			{
				JLabel lblCusto = new JLabel("R$ 0,00");
				lblCusto.setFont(new Font("SansSerif", Font.BOLD, 13));
				GridBagConstraints gbc_lblCusto = new GridBagConstraints();
				gbc_lblCusto.gridwidth = 3;
				gbc_lblCusto.gridx = 5;
				gbc_lblCusto.gridy = 3;
				panel_2.add(lblCusto, gbc_lblCusto);
			}
		}
		
		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, label_1);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPanel.add(panel);
		{
			label_3 = new JLabel("Data Entrada:");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_2, -6, SpringLayout.NORTH, label_3);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, label_3);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -6, SpringLayout.WEST, label_3);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label_2);
			contentPanel.add(label_3);
		}
		{
			lblEntrada = new JLabel("00/00/0000");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEntrada, 0, SpringLayout.NORTH, label_3);
			contentPanel.add(lblEntrada);
		}
		{
			label_4 = new JLabel("Data Vencimento:");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, label_3, -6, SpringLayout.NORTH, label_4);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_4, 0, SpringLayout.WEST, label_2);
			contentPanel.add(label_4);
		}
		{
			lblVencimento = new JLabel("00/00/0000");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblVencimento, 24, SpringLayout.EAST, label_4);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEntrada, 0, SpringLayout.WEST, lblVencimento);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblVencimento, 0, SpringLayout.NORTH, label_4);
			contentPanel.add(lblVencimento);
		}
		{
			label_7 = new JLabel("Possui Devolu\u00E7\u00E3o:");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, label_4, -6, SpringLayout.NORTH, label_7);
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_7, 0, SpringLayout.WEST, label_2);
			contentPanel.add(label_7);
		}
		{
			JLabel lblTemDevolucao = new JLabel("sim/n\u00E3o");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTemDevolucao, 0, SpringLayout.NORTH, label_7);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblTemDevolucao, 18, SpringLayout.EAST, label_7);
			contentPanel.add(lblTemDevolucao);
		}
		{
			chkNEncontrado = new JCheckBox("Produto n\u00E3o encontrado");
			sl_contentPanel.putConstraint(SpringLayout.WEST, chkNEncontrado, 6, SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, label_7, -6, SpringLayout.NORTH, chkNEncontrado);
			contentPanel.add(chkNEncontrado);
		}
		{
			chkDevolvido = new JCheckBox("Produto Devolvido p/ Consign.");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, chkNEncontrado, -6, SpringLayout.NORTH, chkDevolvido);
			sl_contentPanel.putConstraint(SpringLayout.WEST, chkDevolvido, 0, SpringLayout.WEST, label_2);
			contentPanel.add(chkDevolvido);
		}
		{
			button_1 = new JButton("Salvar Produto");
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, button_1);
			sl_contentPanel.putConstraint(SpringLayout.WEST, button_1, 0, SpringLayout.WEST, label_2);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, button_1, 0, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, button_1, -5, SpringLayout.EAST, contentPanel);
			contentPanel.add(button_1);
		}
		{
			btnNewButton = new JButton("Excluir Produto");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, label_2);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, -1, SpringLayout.NORTH, button_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnNewButton, -5, SpringLayout.EAST, contentPanel);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnTrocarFoto = new JButton("Trocar Foto");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnTrocarFoto, 6, SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnTrocarFoto, -5, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, chkDevolvido, -6, SpringLayout.NORTH, btnTrocarFoto);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnTrocarFoto, -6, SpringLayout.NORTH, btnNewButton);
			contentPanel.add(btnTrocarFoto);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
