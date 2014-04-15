package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import br.arthur.entities.Categoria;
import br.arthur.entities.Consignatario;
import br.arthur.entities.Cor;
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tamanho;
import br.arthur.entities.Tipo;
import br.arthur.models.CategoriaModel;
import br.arthur.models.CorModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.MarcaModel;
import br.arthur.models.SaidaModel;
import br.arthur.models.SituacaoModel;
import br.arthur.models.TamanhoModel;
import br.arthur.models.TipoModel;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;

public class ProdutoCadastroDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCodProd;
	private JTextField txtDescricao;
	private JNumericField txtQuantidade;
	private JLabel lblConsigName;
	private JNumericField txtComis;
	private JNumericField txtVenda;
	private JNumericField txtMargem;
	private JTextField txtLocal;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblDataEnt;
	private JLabel lblDataVenc;
	private JLabel lblEntrada;
	private JLabel lblVencimento;
	private JLabel label_7;
	private JButton button_1;
	private JButton btnNewButton;
	private JComboBox cmbCategoria;
	private JComboBox cmbMarca;
	private JComboBox cmbTamanho;
	private JComboBox cmbCor;
	private JComboBox cmbValidade;
	private JComboBox cmbTipo;
	private JComboBox cmbSituacao;
	private JTextArea textAreaObserv;
	private JFormattedTextField txtDataInicio;
	private JLabel lblTemDevolucao;
	private JLabel lblSituao;
	private JButton btnTrocarFoto;
	private JTextField txtQtdeNEncontrado;
	private JLabel lblCdProduto;
	private JLabel picProduto;
	private JLabel lblComis;
	private JLabel lblCusto;

	protected static long theId;

	private double comissao;
	private double custo;

	private EntradaModel em = new EntradaModel();
	private SaidaModel sm = new SaidaModel();
	private JTextField txtQtdeDevolvido;
	private JLabel lblQuantidadeEmEstoque;
	private JLabel lblQtdeEstoque;
	private JLabel lblQtdeDevolvidaP;
	private JLabel lblQtdeNoEncontrada;
	private JLabel lblVendidas;
	private JLabel lblQtdeNEncontrado;
	private JLabel lblQtdeDevolvido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			System.out.println("PRODUTO ID " + theId);
			ProdutoCadastroDialog dialog = new ProdutoCadastroDialog(theId);
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProdutoCadastroDialog(final long theId) {
		setResizable(false);
		this.theId = theId;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 691, 662);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblCodProd = new JLabel("C\u00F3d. Produto");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCodProd, 5, SpringLayout.NORTH, contentPanel);
			lblCodProd.setFont(new Font("SansSerif", Font.BOLD, 14));
			contentPanel.add(lblCodProd);
		}
		{
			lblConsigName = new JLabel("Consignat\u00E1rio Nome");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblConsigName, 1,
					SpringLayout.NORTH, lblCodProd);
			lblConsigName.setFont(new Font("SansSerif", Font.BOLD, 14));
			lblConsigName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblConsigName);
		}
		{
			panel_1 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblConsigName, 0, SpringLayout.EAST, panel_1);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 11,
					SpringLayout.SOUTH, lblCodProd);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 10,
					SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_1, 0,
					SpringLayout.EAST, contentPanel);
			panel_1.setBorder(new TitledBorder(null,
					"Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			contentPanel.add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0 };
			gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0,
					0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
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
				cmbCategoria = new JComboBox();
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
				cmbMarca = new JComboBox();
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

			cmbTamanho = new JComboBox();
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

			cmbCor = new JComboBox();
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
				txtQuantidade = new JNumericField();
				txtQuantidade.setMaxLength(3);
				txtQuantidade.setFormat(JNumericField.NUMERIC);
				txtQuantidade.setText("1");
				txtQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
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
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_1, -6,
					SpringLayout.NORTH, panel_2);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_2, 213,
					SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_2, 10,
					SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_2, 0,
					SpringLayout.EAST, contentPanel);

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

			textAreaObserv = new JTextArea();
			scrollPane.setViewportView(textAreaObserv);
			panel_2.setBorder(new TitledBorder(null,

			"Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING,

			TitledBorder.TOP, null, null));
			contentPanel.add(panel_2);
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0 };
			gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0,
					1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
					Double.MIN_VALUE };
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
				txtComis = new JNumericField();

				txtComis.getDocument().addDocumentListener(
						new DocumentListener() {
							@Override
							public void changedUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtComis.getText().trim().isEmpty()) {
									calcularComissao();
								} else {
									lblComis.setText("R$ 0.00");
								}
							}

							@Override
							public void insertUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtComis.getText().trim().isEmpty()) {
									calcularComissao();
								} else {
									lblComis.setText("R$ 0.00");
								}
							}

							@Override
							public void removeUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtComis.getText().trim().isEmpty()) {
									calcularComissao();
								} else {
									lblComis.setText("R$ 0.00");
								}
							}

						});
				txtComis.setMaxLength(2);
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
				txtVenda = new JNumericField();
				txtVenda.getDocument().addDocumentListener(
						new DocumentListener() {

							@Override
							public void changedUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtVenda.getText().trim().isEmpty()) {
									calcularCusto();
									calcularComissao();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}

							@Override
							public void insertUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtVenda.getText().trim().isEmpty()) {
									calcularCusto();
									calcularComissao();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}

							@Override
							public void removeUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtVenda.getText().trim().isEmpty()) {
									calcularCusto();
									calcularComissao();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}
						});
				txtVenda.setMaxLength(6);
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
				txtMargem = new JNumericField();

				txtMargem.getDocument().addDocumentListener(
						new DocumentListener() {
							@Override
							public void changedUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtMargem.getText().trim()
												.isEmpty()) {
									calcularCusto();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}

							@Override
							public void insertUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtMargem.getText().trim()
												.isEmpty()) {
									calcularCusto();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}

							@Override
							public void removeUpdate(DocumentEvent arg0) {
								if (theId > 0
										&& !txtMargem.getText().trim()
												.isEmpty()) {
									calcularCusto();
								} else {
									lblCusto.setText("R$ 0.00");
								}
							}

						});
				txtMargem.setMaxLength(2);
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
				try {
					txtDataInicio = new JFormattedTextField(new MaskFormatter(
							"##/##/####"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				cmbValidade = new JComboBox();
				cmbValidade
						.setModel(new DefaultComboBoxModel(new String[] {
								"3 meses", "75 dias", "2 meses", "45 dias",
								"1 m\u00EAs", "15 dias", "10 dias", "5 dias",
								"1 dia" }));
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
				cmbTipo = new JComboBox();
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
				lblComis = new JLabel("R$ 0,00");
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
				lblCusto = new JLabel("R$ 0,00");
				lblCusto.setFont(new Font("SansSerif", Font.BOLD, 13));
				GridBagConstraints gbc_lblCusto = new GridBagConstraints();
				gbc_lblCusto.gridwidth = 3;
				gbc_lblCusto.gridx = 5;
				gbc_lblCusto.gridy = 3;
				panel_2.add(lblCusto, gbc_lblCusto);
			}
		}

		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 6,
				SpringLayout.SOUTH, panel_2);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -282,
				SpringLayout.EAST, contentPanel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		contentPanel.add(panel);
		{
			lblDataEnt = new JLabel("Data Ent:");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblDataEnt, 6,
					SpringLayout.EAST, panel);
			contentPanel.add(lblDataEnt);
		}
		{
			lblEntrada = new JLabel("00/00/0000");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEntrada, 0, SpringLayout.NORTH, lblDataEnt);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEntrada, 6, SpringLayout.EAST, lblDataEnt);
			contentPanel.add(lblEntrada);
		}
		{
			lblDataVenc = new JLabel("Venc.:");
			contentPanel.add(lblDataVenc);
		}
		{
			lblVencimento = new JLabel("00/00/0000");
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblVencimento, -10, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblDataVenc, -6,
					SpringLayout.WEST, lblVencimento);
			contentPanel.add(lblVencimento);
		}
		{
			label_7 = new JLabel("Possui Devolu\u00E7\u00E3o:");
			sl_contentPanel.putConstraint(SpringLayout.WEST, label_7, 6, SpringLayout.EAST, panel);
			contentPanel.add(label_7);
		}
		{
			lblTemDevolucao = new JLabel("sim/n\u00E3o");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTemDevolucao, 6, SpringLayout.SOUTH, lblDataVenc);
			contentPanel.add(lblTemDevolucao);
		}
		{
			button_1 = new JButton("Salvar Produto");
			button_1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					salvarCadastro(theId);

				}
			});
			sl_contentPanel.putConstraint(SpringLayout.WEST, button_1, 11,
					SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, button_1, -5,
					SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 0,
					SpringLayout.SOUTH, button_1);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, button_1, 0,
					SpringLayout.SOUTH, contentPanel);
			contentPanel.add(button_1);
		}
		{
			btnNewButton = new JButton("Excluir Produto");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnNewButton, 11,
					SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, -1,
					SpringLayout.NORTH, button_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnNewButton, -5,
					SpringLayout.EAST, contentPanel);
			contentPanel.add(btnNewButton);
		}
		{
			btnTrocarFoto = new JButton("Trocar Foto");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnTrocarFoto, 11,
					SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnTrocarFoto, -5,
					SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnTrocarFoto,
					-6, SpringLayout.NORTH, btnNewButton);
			contentPanel.add(btnTrocarFoto);
		}

		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblConsigName, 6, SpringLayout.EAST, lblConsignatrio);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblConsignatrio, 1, SpringLayout.NORTH, lblCodProd);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblConsignatrio, 94, SpringLayout.EAST, lblCodProd);
		lblConsignatrio.setFont(new Font("SansSerif", Font.BOLD, 14));
		contentPanel.add(lblConsignatrio);
		{
			lblSituao = new JLabel("SITUA\u00C7\u00C3O:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDataEnt, 6, SpringLayout.SOUTH, lblSituao);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblSituao, 11,
					SpringLayout.EAST, panel);
			panel.setLayout(null);
			{
				picProduto = new JLabel("New label");
				picProduto.setHorizontalAlignment(SwingConstants.CENTER);
				picProduto.setBounds(6, 6, 381, 258);
				panel.add(picProduto);
			}
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSituao, 6,
					SpringLayout.SOUTH, panel_2);
			contentPanel.add(lblSituao);
		}

		cmbSituacao = new JComboBox();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblVencimento, 1,
				SpringLayout.SOUTH, cmbSituacao);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDataVenc, 1,
				SpringLayout.SOUTH, cmbSituacao);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, cmbSituacao, -5,
				SpringLayout.NORTH, lblSituao);
		sl_contentPanel.putConstraint(SpringLayout.WEST, cmbSituacao, 7,
				SpringLayout.EAST, lblSituao);
		sl_contentPanel.putConstraint(SpringLayout.EAST, cmbSituacao, 0,
				SpringLayout.EAST, panel_1);
		contentPanel.add(cmbSituacao);

		txtQtdeNEncontrado = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtQtdeNEncontrado, -14, SpringLayout.NORTH, btnTrocarFoto);
		sl_contentPanel.putConstraint(SpringLayout.EAST, txtQtdeNEncontrado, -10, SpringLayout.EAST, contentPanel);
		txtQtdeNEncontrado.setText("0");
		txtQtdeNEncontrado.setFont(new Font("SansSerif", Font.PLAIN, 10));
		txtQtdeNEncontrado.setColumns(10);
		contentPanel.add(txtQtdeNEncontrado);
		{
			lblCdProduto = new JLabel("C\u00F3d. Produto:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCdProduto, 5,
					SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblCodProd, 6,
					SpringLayout.EAST, lblCdProduto);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblCdProduto, 0,
					SpringLayout.WEST, panel_1);
			lblCdProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
			contentPanel.add(lblCdProduto);
		}
		{
			txtQtdeDevolvido = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtQtdeDevolvido, -37, SpringLayout.NORTH, btnTrocarFoto);
			sl_contentPanel.putConstraint(SpringLayout.EAST, txtQtdeDevolvido, -10, SpringLayout.EAST, contentPanel);
			txtQtdeDevolvido.setText("0");
			txtQtdeDevolvido.setFont(new Font("SansSerif", Font.PLAIN, 10));
			txtQtdeDevolvido.setColumns(10);
			contentPanel.add(txtQtdeDevolvido);
		}
		{
			lblQuantidadeEmEstoque = new JLabel("Quantidade  em Estoque:");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblQuantidadeEmEstoque, 6, SpringLayout.EAST, panel);
			contentPanel.add(lblQuantidadeEmEstoque);
		}
		{
			lblQtdeEstoque = new JLabel("0");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblQtdeEstoque, 33, SpringLayout.EAST, lblQuantidadeEmEstoque);
			sl_contentPanel.putConstraint(SpringLayout.EAST, lblQtdeEstoque, -55, SpringLayout.EAST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQtdeEstoque, 0, SpringLayout.NORTH, lblQuantidadeEmEstoque);
			contentPanel.add(lblQtdeEstoque);
		}
		{
			lblQtdeDevolvidaP = new JLabel("Qtde Devolvida p/ Consig.:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQtdeDevolvidaP, 4, SpringLayout.NORTH, txtQtdeDevolvido);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblQtdeDevolvidaP, 6, SpringLayout.EAST, panel);
			contentPanel.add(lblQtdeDevolvidaP);
		}
		{
			lblQtdeNoEncontrada = new JLabel("Qtde N\u00E3o Encontrada:");
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblQtdeNoEncontrada, 8, SpringLayout.EAST, panel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQtdeNoEncontrada, 4, SpringLayout.NORTH, txtQtdeNEncontrado);
			contentPanel.add(lblQtdeNoEncontrada);
		}
		
		JLabel lblQuantidadeVendida = new JLabel("Quantidade Vendida:");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblQuantidadeVendida, -190, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQuantidadeEmEstoque, 6, SpringLayout.SOUTH, lblQuantidadeVendida);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, label_7, -6, SpringLayout.NORTH, lblQuantidadeVendida);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblQuantidadeVendida, 6, SpringLayout.EAST, panel);
		contentPanel.add(lblQuantidadeVendida);
		
		lblVendidas = new JLabel("0");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblVendidas, 2, SpringLayout.SOUTH, lblTemDevolucao);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblTemDevolucao, 0, SpringLayout.EAST, lblVendidas);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblVendidas, 62, SpringLayout.EAST, lblQuantidadeVendida);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblVendidas, -54, SpringLayout.EAST, contentPanel);
		contentPanel.add(lblVendidas);
		
		lblQtdeDevolvido = new JLabel("0");
		sl_contentPanel.putConstraint(SpringLayout.WEST, txtQtdeDevolvido, 6, SpringLayout.EAST, lblQtdeDevolvido);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQtdeDevolvido, 4, SpringLayout.NORTH, txtQtdeDevolvido);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblQtdeDevolvido, 0, SpringLayout.WEST, lblVencimento);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblQtdeDevolvido, -6, SpringLayout.WEST, txtQtdeNEncontrado);
		contentPanel.add(lblQtdeDevolvido);
		
		lblQtdeNEncontrado = new JLabel("0");
		sl_contentPanel.putConstraint(SpringLayout.WEST, txtQtdeNEncontrado, 11, SpringLayout.EAST, lblQtdeNEncontrado);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblQtdeNEncontrado, 4, SpringLayout.NORTH, txtQtdeNEncontrado);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblQtdeNEncontrado, 0, SpringLayout.WEST, lblVencimento);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblQtdeNEncontrado, -5, SpringLayout.EAST, lblTemDevolucao);
		contentPanel.add(lblQtdeNEncontrado);

		if (this.theId > 0) {
			popularCombos();
			populateMe();
		} else {
			dispose();
		}

	}

	private void populateMe() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		Entrada p = em.findOneWhere("id", String.valueOf(theId));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		lblCodProd.setText(String.valueOf(p.getId()));
		lblConsigName.setText(p.getConsignatario().getNome());

		txtDescricao.setText(p.getDescricao());
		cmbCategoria.setSelectedItem(p.getCategoria().getName());
		cmbMarca.setSelectedItem(p.getMarca().getName());
		cmbTamanho.setSelectedItem(p.getTamanho().getName());
		cmbCor.setSelectedItem(p.getCor().getName());
		txtQuantidade.setText(String.valueOf(p.getQuantidate()));
		textAreaObserv.setText(p.getObservacao());
		txtComis.setText(String.valueOf(p.getMargemComissao()));
		txtVenda.setText(String.valueOf(p.getVenda()));
		txtMargem.setText(String.valueOf(p.getMargeVenda()));
		txtDataInicio.setText(sdf.format(p.getDataInicio()));
		cmbTipo.setSelectedItem(p.getTipo().getName());
		txtLocal.setText(p.getLocalizacao());
		lblComis.setText(String.format("%.2f", p.getComissao()));
		lblCusto.setText(String.format("%.2f", p.getCusto()));

		cmbSituacao.setSelectedItem(p.getSituacao().getName());

		lblEntrada.setText(sdf.format(p.getDataEntrada()));
		lblVencimento.setText(sdf.format(p.getDataVencimento()));

		// TODO L�GICA PARA CONSULTAR A SA�DA para ver se tem devolu��o
		lblTemDevolucao.setText("n�o");

		if (p.getnEncontrado() > 0) {
			lblQtdeNEncontrado.setText(String.valueOf(p.getnEncontrado()));
			txtQtdeNEncontrado.setText(String.valueOf(p.getnEncontrado()));
		} else {
			lblQtdeNEncontrado.setText("0");
		}

		if (p.getDevolvido() > 0) {
			lblQtdeDevolvido.setText(String.valueOf(p.getDevolvido()));
			txtQtdeDevolvido.setText(String.valueOf(p.getDevolvido()));
		} else {
			lblQtdeDevolvido.setText("0");
		}

		boolean hasSaida = sm.hasSaida(theId);

		int qtdeDisponivel = 0;
		int devolvido = 0;
		int nEncontrado = 0;

		if (hasSaida) {
			qtdeDisponivel = p.getQuantidate() - p.getnEncontrado()
					- p.getDevolvido() - sm.getQtdeSaida();
		} else {
			qtdeDisponivel = p.getQuantidate() - p.getnEncontrado()
					- p.getDevolvido();
		}

		lblVendidas.setText(String.valueOf(sm.getQtdeSaida()));
		lblQtdeEstoque.setText(String.valueOf(qtdeDisponivel));

		// TODO Setar a imagem do produto
		picProduto.setText("");
		Blob blob_img = p.getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void drawPicture(BufferedImage bi) {
		if (bi != null) {
			picProduto.setText("");
			picProduto.setIcon(new ImageIcon(bi));
		}
	}

	public void popularCombos() {

		Iterator categorias = CategoriaModel.findAll().iterator();
		while (categorias.hasNext()) {
			Categoria c = (Categoria) categorias.next();
			cmbCategoria.addItem(c.getName());
		}

		Iterator marcas = MarcaModel.findAll().iterator();
		while (marcas.hasNext()) {
			Marca m = (Marca) marcas.next();
			cmbMarca.addItem(m.getName());
		}

		Iterator cores = CorModel.findAll().iterator();
		while (cores.hasNext()) {
			Cor c = (Cor) cores.next();
			cmbCor.addItem(c.getName());
		}

		Iterator tamanhos = TamanhoModel.findAll().iterator();
		while (tamanhos.hasNext()) {
			Tamanho t = (Tamanho) tamanhos.next();
			cmbTamanho.addItem(t.getName());
		}

		Iterator tipos = TipoModel.findAll().iterator();
		while (tipos.hasNext()) {
			Tipo t = (Tipo) tipos.next();
			cmbTipo.addItem(t.getName());
		}

		Iterator situacoes = SituacaoModel.findAll().iterator();
		while (situacoes.hasNext()) {
			Situacao s = (Situacao) situacoes.next();
			cmbSituacao.addItem(s.getName());
		}

	}

	protected void calcularComissao() {
		this.comissao = 0;

		if (!txtVenda.getText().trim().isEmpty()
				&& !txtComis.getText().trim().isEmpty()) {
			if (!txtVenda.getText().trim().isEmpty()
					&& Double.parseDouble(txtVenda.getText()) > 0
					&& Double.parseDouble(txtComis.getText()) >= 0) {
				Double venda = Double.parseDouble(txtVenda.getText());
				Double comis = Double.parseDouble(txtComis.getText()) / 100;

				Double comissao = venda * comis;

				DecimalFormat df = new DecimalFormat("#.##");
				lblComis.setText("R$ " + df.format(comissao));

				this.comissao = comissao;
			}
		}
	}

	protected void calcularCusto() {
		// TODO Auto-generated method stub
		this.custo = 0;
		if (!txtVenda.getText().trim().isEmpty()
				&& !txtMargem.getText().trim().isEmpty()) {
			if (!txtVenda.getText().trim().isEmpty()
					&& Double.parseDouble(txtVenda.getText()) >= 0
					&& Double.parseDouble(txtMargem.getText()) >= 0) {

				Double venda = Double.parseDouble(txtVenda.getText());
				Double margem = (100 - Double.parseDouble(txtMargem.getText())) / 100;

				Double custo = venda * margem;

				DecimalFormat df = new DecimalFormat("#.##");
				lblCusto.setText("R$ " + df.format(custo));

				this.custo = custo;
			}
		}
	}

	private HashMap<String, Object> pegarValoresProduto(Consignatario ce) {
		HashMap<String, Object> data = new HashMap();

		data.put("consig", ce);

		data.put("descricao", txtDescricao.getText());

		if (txtQuantidade.getText().trim().isEmpty()) {
			data.put("qtde", 1);
		} else {
			data.put("qtde", Integer.parseInt(txtQuantidade.getText().trim()));
		}

		if (txtVenda.getText().trim().isEmpty()) {
			data.put("venda", 0.0);
		} else {
			data.put("venda", Double.parseDouble(txtVenda.getText()));
		}

		if (txtMargem.getText().trim().isEmpty()) {
			data.put("margemv", 60.0);
		} else {
			data.put("margemv", Double.parseDouble(txtMargem.getText()));
		}

		if (txtComis.getText().trim().isEmpty()) {
			data.put("margemc", 8.0);
		} else {
			data.put("margemc", Double.parseDouble(txtComis.getText()));
		}

		if (this.custo <= 0) {
			data.put("custo", 0.0);
		} else {
			data.put("custo", this.custo);
		}

		if (this.comissao <= 0) {
			data.put("comis", 0.0);
		} else {
			data.put("comis", this.comissao);
		}

		Date dataInicio = new Date();
		data.put("inicio", dataInicio);

		int calendarConst = Calendar.DATE;
		int soma = 0;
		switch ((String) cmbValidade.getSelectedItem()) {
		case "3 meses":
			calendarConst = Calendar.MONTH;
			soma = 3;
			break;
		case "75 dias":
			calendarConst = Calendar.DATE;
			soma = 75;
			break;
		case "2 meses":
			calendarConst = Calendar.MONTH;
			soma = 2;
			break;
		case "45 dias":
			calendarConst = Calendar.DATE;
			soma = 45;
			break;
		case "1 m�s":
			calendarConst = Calendar.MONTH;
			soma = 1;
			break;
		case "15 dias":
			calendarConst = Calendar.DATE;
			soma = 15;
			break;
		case "10 dias":
			calendarConst = Calendar.DATE;
			soma = 10;
			break;
		case "5 dias":
			calendarConst = Calendar.DATE;
			soma = 5;
			break;
		case "1 dia":
			calendarConst = Calendar.DATE;
			soma = 1;
			break;
		}

		Calendar venc = Calendar.getInstance();
		venc.setTime(dataInicio);
		venc.add(calendarConst, soma);

		data.put("venc", venc.getTime());

		data.put(
				"categoria",
				CategoriaModel.findOneWhere("name",
						"'" + cmbCategoria.getSelectedItem() + "'"));

		data.put(
				"marca",
				MarcaModel.findOneWhere("name",
						"'" + cmbMarca.getSelectedItem() + "'"));

		data.put(
				"cor",
				CorModel.findOneWhere("name", "'" + cmbCor.getSelectedItem()
						+ "'"));

		data.put(
				"tamanho",
				TamanhoModel.findOneWhere("name",
						"'" + cmbTamanho.getSelectedItem() + "'"));

		data.put(
				"tipo",
				TipoModel.findOneWhere("name", "'" + cmbTipo.getSelectedItem()
						+ "'"));

		data.put(
				"situacao",
				SituacaoModel.findOneWhere("name",
						"'" + cmbSituacao.getSelectedItem() + "'"));

		data.put("observ", textAreaObserv.getText());

		data.put("local", txtLocal.getText());

		// Campos devolvido e n�o encontrado

		data.put("qtdeDevolvido", Integer.parseInt(txtQtdeDevolvido.getText()));

		data.put("qtdeNEncontrado", 
				Integer.parseInt(txtQtdeNEncontrado.getText()));

		return data;
	}

	private void salvarCadastro(long theId) {
		
		Entrada ee = em.findOneWhere("id", String.valueOf(theId));

		Consignatario ce = ee.getConsignatario();

		String msgErro = "";
		boolean isValid = true;

		if (txtDescricao.getText().trim().isEmpty()) {
			msgErro += "O campo 't�tulo' deve ser informado!\n";
			isValid = false;
		}

		if (txtQuantidade.getText().trim().isEmpty()
				|| Integer.parseInt(txtQuantidade.getText()) <= 0) {
			msgErro += "O campo 'quantidade' deve ser informado e ser maior do que zero!\n";
			isValid = false;
		}

		/*if (ee.getCategoria().isVestimenta()) {
			if (cmbCategoria.getSelectedItem().toString()
					.contains("selecionar")) {
				msgErro += "O campo 'categoria' deve ser informado selecionado!\n";
				isValid = false;
			}

			if (cmbMarca.getSelectedItem().toString().contains("selecionar")) {
				msgErro += "O campo 'marca' deve ser informado selecionado!\n";
				isValid = false;
			}

			if (cmbCor.getSelectedItem().toString().contains("selecionar")) {
				msgErro += "O campo 'cor' deve ser informado selecionado!\n";
				isValid = false;
			}

			if (cmbTamanho.getSelectedItem().toString().contains("selecionar")) {
				msgErro += "O campo 'tamanho' deve ser informado selecionado!\n";
				isValid = false;
			}
		}*/

		if (Double.parseDouble(txtComis.getText()) <= 0.0) {
			msgErro += "O campo 'comiss�o' deve ser informado e ser maior do que zero!\n";
			isValid = false;
		}

		if (Double.parseDouble(txtVenda.getText()) <= 0.0) {
			msgErro += "O campo 'venda' deve ser informado e ser maior do que zero!\n";
			isValid = false;
		}

		if (Double.parseDouble(txtMargem.getText()) <= 0.0) {
			msgErro += "O campo 'margem' deve ser informado e ser maior do que zero!\n";
			isValid = false;
		}

		boolean hasSaida = sm.hasSaida(theId);
		int qtdeDisponivel = 0;
		int devolvido = 0;
		int nEncontrado = 0;

		if (hasSaida) {
			qtdeDisponivel = ee.getQuantidate() - ee.getnEncontrado()
					- ee.getDevolvido() - sm.getQtdeSaida();
		} else {
			qtdeDisponivel = ee.getQuantidate() - ee.getnEncontrado()
					- ee.getDevolvido();
		}

		boolean calcularEstoque = false;
		
		if (!txtQtdeDevolvido.getText().trim().isEmpty() && Integer.parseInt((String)txtQtdeDevolvido.getText()) != ee.getDevolvido()) {			
			calcularEstoque = true;
		} else {
			txtQtdeDevolvido.setText(String.valueOf(ee.getDevolvido()));
		}
		devolvido = Integer.parseInt(txtQtdeDevolvido.getText());	

		if (!txtQtdeNEncontrado.getText().trim().isEmpty() && Integer.parseInt((String)txtQtdeNEncontrado.getText()) != ee.getnEncontrado()) {			
			calcularEstoque = true;
		} else {
			
			txtQtdeNEncontrado.setText(String.valueOf(ee.getnEncontrado()));
		}
		nEncontrado = Integer.parseInt(txtQtdeNEncontrado.getText());
		
		if (calcularEstoque && qtdeDisponivel < (devolvido + nEncontrado)) {
			msgErro += "A quantidade de itens devolvidos e n�o encontrados n�o podem ser maior do que a quantidade dispon�vel em estoque!\n";
			isValid = false;
		}
		
		System.out.println("DEVOLVIDO " + devolvido);
		System.out.println("N ENCONTRADO " + nEncontrado);
		
		System.out.println("D + NE = " + (devolvido + nEncontrado));
		
		System.out.println("� V ou F ? " + (qtdeDisponivel < (devolvido + nEncontrado)));
		
		
		
		if (isValid) {
			if (((String) cmbSituacao.getSelectedItem()).contains("avaliando")) {
				JOptionPane
						.showMessageDialog(
								null,
								"ATEN��O! Voc� setou este produto como avaliando. Ele n�o estar� dispon�vel para venda, at� que voc� o coloque como dispon�vel");
			}

			HashMap<String, Object> data = pegarValoresProduto(ce);

			Entrada nee = em.salvarEntrada(this.theId, data);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			data.clear();

			this.theId = nee.getId();

			lblCodProd.setText(String.valueOf(nee.getId()));

			JOptionPane.showMessageDialog(null, "Produto salvo");
			
			populateMe();
		} else {
			JOptionPane.showMessageDialog(null, msgErro);
		}
	}
}
