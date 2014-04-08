package br.arthur.interfaces.consultas;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import org.hibernate.LobHelper;
import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Cor;
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tamanho;
import br.arthur.entities.Tipo;
import br.arthur.interfaces.cadastros.CadastroCategoria;
import br.arthur.interfaces.cadastros.CadastroCor;
import br.arthur.interfaces.cadastros.CadastroEntrada;
import br.arthur.interfaces.cadastros.CadastroMarca;
import br.arthur.interfaces.cadastros.CadastroTamanho;
import br.arthur.interfaces.cadastros.CadastroTipo;
import br.arthur.interfaces.cadastros.dialogs.PicZoomDialog;
import br.arthur.interfaces.cadastros.dialogs.ProdutoCadastroDialog;
import br.arthur.models.CategoriaModel;
import br.arthur.models.CorModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.MarcaModel;
import br.arthur.models.SaidaModel;
import br.arthur.models.SituacaoModel;
import br.arthur.models.TamanhoModel;
import br.arthur.models.TipoModel;
import br.arthur.relatorios.RelatorioProdutos;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;
import br.arthur.utils.MyIteratorUtil;

import com.lowagie.text.pdf.codec.Base64.InputStream;

public class ConsultarProduto extends JInternalFrame {
	private JTextField txtDesc;
	private JNumericField txtVendaMaiorQ;

	private DefaultTableModel model;
	private JTable table;

	private MyIteratorUtil imgListIter;
	private int imgCount = 0;
	private int imgTotalCount;
	private JButton btnNextImg;
	private JComboBox cmbCor;
	private JComboBox cmbTipo;
	private JComboBox cmbSit;
	private JComboBox cmbCat;
	private JComboBox cmbMarca;
	private JComboBox cmbTam;
	private JLabel lblDescrioDoProduto;

	private JLabel picProduto;

	private EntradaModel em = new EntradaModel();
	private SaidaModel sm = new SaidaModel();
	private JTextField txtConsig;
	private JTextField txtDataInicio;
	private JTextField txtDataVenc;

	private JLabel lblDataIncio;
	
	private long prodId = 0;
	private JTextField txtCodigo;
	private JCheckBox ckDevolvidos;
	private JCheckBox ckEstoque;
	private JCheckBox ckNEncontrados;
	private JCheckBox ckVencidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarProduto frame = new ConsultarProduto();
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
	public ConsultarProduto() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Gerenciar Produtos");
		setBounds(100, 100, 1185, 498);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		panel.setBorder(new TitledBorder(null, "Filtrar Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -152, SpringLayout.SOUTH, getContentPane());
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 59, 160, 120, 90, 90, 81, 120,
				90, 130, 0 };
		gbl_panel.rowHeights = new int[] { 28, 28, 28, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.EAST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 0;
		panel.add(lblDescrio, gbc_lblDescrio);

		txtDesc = new JTextField();
		GridBagConstraints gbc_txtDesc = new GridBagConstraints();
		gbc_txtDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDesc.anchor = GridBagConstraints.NORTH;
		gbc_txtDesc.insets = new Insets(0, 0, 5, 5);
		gbc_txtDesc.gridx = 1;
		gbc_txtDesc.gridy = 0;
		panel.add(txtDesc, gbc_txtDesc);
		txtDesc.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 2;
		gbc_lblCategoria.gridy = 0;
		panel.add(lblCategoria, gbc_lblCategoria);

		cmbCat = new JComboBox();
		GridBagConstraints gbc_cmbCat = new GridBagConstraints();
		gbc_cmbCat.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCat.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCat.gridx = 3;
		gbc_cmbCat.gridy = 0;
		panel.add(cmbCat, gbc_cmbCat);

		JLabel lblCor = new JLabel("Cor:");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.EAST;
		gbc_lblCor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCor.gridx = 4;
		gbc_lblCor.gridy = 0;
		panel.add(lblCor, gbc_lblCor);

		cmbCor = new JComboBox();
		GridBagConstraints gbc_cmbCor = new GridBagConstraints();
		gbc_cmbCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCor.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCor.gridx = 5;
		gbc_cmbCor.gridy = 0;
		panel.add(cmbCor, gbc_cmbCor);

		JLabel lblTamanho = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanho.gridx = 6;
		gbc_lblTamanho.gridy = 0;
		panel.add(lblTamanho, gbc_lblTamanho);

		cmbTam = new JComboBox();
		GridBagConstraints gbc_cmbTam = new GridBagConstraints();
		gbc_cmbTam.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTam.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTam.gridx = 7;
		gbc_cmbTam.gridy = 0;
		panel.add(cmbTam, gbc_cmbTam);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo Produto:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 8;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtCodigo = new JTextField();
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigo.gridx = 9;
		gbc_txtCodigo.gridy = 0;
		panel.add(txtCodigo, gbc_txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 0;
		gbc_lblMarca.gridy = 1;
		panel.add(lblMarca, gbc_lblMarca);

		cmbMarca = new JComboBox();
		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMarca.gridx = 1;
		gbc_cmbMarca.gridy = 1;
		panel.add(cmbMarca, gbc_cmbMarca);

		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblSituao = new GridBagConstraints();
		gbc_lblSituao.anchor = GridBagConstraints.EAST;
		gbc_lblSituao.insets = new Insets(0, 0, 5, 5);
		gbc_lblSituao.gridx = 2;
		gbc_lblSituao.gridy = 1;
		panel.add(lblSituao, gbc_lblSituao);

		cmbSit = new JComboBox();
		GridBagConstraints gbc_cmbSit = new GridBagConstraints();
		gbc_cmbSit.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSit.insets = new Insets(0, 0, 5, 5);
		gbc_cmbSit.gridx = 3;
		gbc_cmbSit.gridy = 1;
		panel.add(cmbSit, gbc_cmbSit);

		JLabel lblPreco = new JLabel("Pre\u00E7o (>=):");
		GridBagConstraints gbc_lblPreco = new GridBagConstraints();
		gbc_lblPreco.anchor = GridBagConstraints.EAST;
		gbc_lblPreco.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreco.gridx = 4;
		gbc_lblPreco.gridy = 1;
		panel.add(lblPreco, gbc_lblPreco);

		txtVendaMaiorQ = new JNumericField();
		txtVendaMaiorQ.setMaxLength(6);
		GridBagConstraints gbc_txtVendaMaiorQ = new GridBagConstraints();
		gbc_txtVendaMaiorQ.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVendaMaiorQ.anchor = GridBagConstraints.NORTH;
		gbc_txtVendaMaiorQ.insets = new Insets(0, 0, 5, 5);
		gbc_txtVendaMaiorQ.gridx = 5;
		gbc_txtVendaMaiorQ.gridy = 1;
		panel.add(txtVendaMaiorQ, gbc_txtVendaMaiorQ);
		txtVendaMaiorQ.setColumns(10);

		lblDataIncio = new JLabel("Data In\u00EDcio (>=):");
		GridBagConstraints gbc_lblDataIncio = new GridBagConstraints();
		gbc_lblDataIncio.anchor = GridBagConstraints.EAST;
		gbc_lblDataIncio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataIncio.gridx = 6;
		gbc_lblDataIncio.gridy = 1;
		panel.add(lblDataIncio, gbc_lblDataIncio);
		try {
			txtDataInicio = new JFormattedTextField(new MaskFormatter(
					"##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GridBagConstraints gbc_txtDataInicio = new GridBagConstraints();
		gbc_txtDataInicio.anchor = GridBagConstraints.NORTH;
		gbc_txtDataInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataInicio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataInicio.gridx = 7;
		gbc_txtDataInicio.gridy = 1;
		panel.add(txtDataInicio, gbc_txtDataInicio);
		txtDataInicio.setColumns(10);
		
		ckDevolvidos = new JCheckBox("Devolvidos");
		GridBagConstraints gbc_ckDevolvidos = new GridBagConstraints();
		gbc_ckDevolvidos.insets = new Insets(0, 0, 5, 5);
		gbc_ckDevolvidos.gridx = 8;
		gbc_ckDevolvidos.gridy = 1;
		panel.add(ckDevolvidos, gbc_ckDevolvidos);
		
		ckEstoque = new JCheckBox("Em Estoque");
		GridBagConstraints gbc_ckEstoque = new GridBagConstraints();
		gbc_ckEstoque.insets = new Insets(0, 0, 5, 0);
		gbc_ckEstoque.gridx = 9;
		gbc_ckEstoque.gridy = 1;
		panel.add(ckEstoque, gbc_ckEstoque);

		JLabel lblDataEntrada = new JLabel("Consignat\u00E1rio:");
		GridBagConstraints gbc_lblDataEntrada = new GridBagConstraints();
		gbc_lblDataEntrada.anchor = GridBagConstraints.WEST;
		gbc_lblDataEntrada.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataEntrada.gridx = 0;
		gbc_lblDataEntrada.gridy = 2;
		panel.add(lblDataEntrada, gbc_lblDataEntrada);

		txtConsig = new JTextField();
		GridBagConstraints gbc_txtConsig = new GridBagConstraints();
		gbc_txtConsig.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtConsig.anchor = GridBagConstraints.NORTH;
		gbc_txtConsig.insets = new Insets(0, 0, 0, 5);
		gbc_txtConsig.gridx = 1;
		gbc_txtConsig.gridy = 2;
		panel.add(txtConsig, gbc_txtConsig);
		txtConsig.setColumns(10);

		JLabel lblVencimento = new JLabel("Vencimento (=):");
		GridBagConstraints gbc_lblVencimento = new GridBagConstraints();
		gbc_lblVencimento.anchor = GridBagConstraints.EAST;
		gbc_lblVencimento.insets = new Insets(0, 0, 0, 5);
		gbc_lblVencimento.gridx = 2;
		gbc_lblVencimento.gridy = 2;
		panel.add(lblVencimento, gbc_lblVencimento);
		try {
			txtDataVenc = new JFormattedTextField(new MaskFormatter(
					"##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GridBagConstraints gbc_txtDataVenc = new GridBagConstraints();
		gbc_txtDataVenc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataVenc.anchor = GridBagConstraints.NORTH;
		gbc_txtDataVenc.insets = new Insets(0, 0, 0, 5);
		gbc_txtDataVenc.gridx = 3;
		gbc_txtDataVenc.gridy = 2;
		panel.add(txtDataVenc, gbc_txtDataVenc);
		txtDataVenc.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipo.gridx = 4;
		gbc_lblTipo.gridy = 2;
		panel.add(lblTipo, gbc_lblTipo);

		cmbTipo = new JComboBox();
		GridBagConstraints gbc_cmbTipo = new GridBagConstraints();
		gbc_cmbTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipo.insets = new Insets(0, 0, 0, 5);
		gbc_cmbTipo.gridx = 5;
		gbc_cmbTipo.gridy = 2;
		panel.add(cmbTipo, gbc_cmbTipo);

		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -6, SpringLayout.WEST, panel_2);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 305, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_2, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -144,
				SpringLayout.SOUTH, getContentPane());
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setLayout(new CardLayout(0, 0));

		picProduto = new JLabel("Imagem");
		picProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		picProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (picProduto.getIcon() != null) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					picProduto.setCursor(Cursor
							.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Entrada eSelecionada = em.findOneWhere("id",
							String.valueOf(prodId));
					Blob blob_img = eSelecionada.getImagemBlob();
					String descricao = eSelecionada.getDescricao();
					PicZoomDialog picDiag = new PicZoomDialog(blob_img,
							descricao);
					picDiag.setVisible(true);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					picProduto.setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					JOptionPane.showMessageDialog(null,
							"Não há nenhuma imagem para ser visualizada!");
				}
			}
		});
		picProduto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(picProduto, "name_11027006424649");
		getContentPane().add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		table = new JTable();

		populateModel();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linhaSelecionadaProduto = table.getSelectedRow();

				prodId = (long) table.getValueAt(linhaSelecionadaProduto, 0);

				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				table.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				selecionarItem(prodId);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		table.getColumnModel().getColumn(9).setPreferredWidth(120);
		table.getColumnModel().getColumn(10).setPreferredWidth(120);
		table.getColumnModel().getColumn(11).setPreferredWidth(120);
		table.getColumnModel().getColumn(12).setPreferredWidth(120);
		table.getColumnModel().getColumn(13).setPreferredWidth(120);
		table.getColumnModel().getColumn(14).setPreferredWidth(120);
		table.getColumnModel().getColumn(15).setPreferredWidth(120);
		table.getColumnModel().getColumn(16).setPreferredWidth(120);

		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				Long population = (Long) entry.getValue(0);
				return population.intValue() > 0;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);

		JScrollPane scrollPaneProdutos = new JScrollPane(table);
		panel_2.add(scrollPaneProdutos, "name_10326065285220");
		
				JButton btnFiltrar = new JButton("Filtrar");
				btnFiltrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						filtrar();
					}
				});
				
						JButton btnLimparFiltro = new JButton("Limpar Filtro");
						btnLimparFiltro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								resetarFiltro();
								
								filtrar();
							}
						});
						
						ckNEncontrados = new JCheckBox("N\u00E3o Encontrados");
						GridBagConstraints gbc_ckNEncontrados = new GridBagConstraints();
						gbc_ckNEncontrados.insets = new Insets(0, 0, 0, 5);
						gbc_ckNEncontrados.gridx = 6;
						gbc_ckNEncontrados.gridy = 2;
						panel.add(ckNEncontrados, gbc_ckNEncontrados);
						
						ckVencidos = new JCheckBox("Vencidos");
						GridBagConstraints gbc_ckVencidos = new GridBagConstraints();
						gbc_ckVencidos.insets = new Insets(0, 0, 0, 5);
						gbc_ckVencidos.gridx = 7;
						gbc_ckVencidos.gridy = 2;
						panel.add(ckVencidos, gbc_ckVencidos);
						GridBagConstraints gbc_btnLimparFiltro = new GridBagConstraints();
						gbc_btnLimparFiltro.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnLimparFiltro.anchor = GridBagConstraints.NORTH;
						gbc_btnLimparFiltro.insets = new Insets(0, 0, 0, 5);
						gbc_btnLimparFiltro.gridx = 8;
						gbc_btnLimparFiltro.gridy = 2;
						panel.add(btnLimparFiltro, gbc_btnLimparFiltro);
				GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
				gbc_btnFiltrar.anchor = GridBagConstraints.NORTH;
				gbc_btnFiltrar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnFiltrar.gridx = 9;
				gbc_btnFiltrar.gridy = 2;
				panel.add(btnFiltrar, gbc_btnFiltrar);
				
				lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
				lblDescrioDoProduto.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
				springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, lblDescrioDoProduto);
				springLayout.putConstraint(SpringLayout.NORTH, lblDescrioDoProduto, 0, SpringLayout.NORTH, panel_2);
				springLayout.putConstraint(SpringLayout.WEST, lblDescrioDoProduto, 10, SpringLayout.WEST, getContentPane());
				getContentPane().add(lblDescrioDoProduto);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		JMenuItem mntmNovaEntrada = new JMenuItem("Nova Entrada");
		mntmNovaEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroEntrada obj = new CadastroEntrada();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastrar.add(mntmNovaEntrada);

		JMenuItem mntmNovaCategoria = new JMenuItem("Nova Categoria");
		mntmNovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoria obj = new CadastroCategoria();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastrar.add(mntmNovaCategoria);

		JMenuItem mntmNovaMarca = new JMenuItem("Nova Marca");
		mntmNovaMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroMarca obj = new CadastroMarca();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastrar.add(mntmNovaMarca);
		
				JMenuItem mntmNewMenuItem = new JMenuItem("Nova Cor");
				mntmNewMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CadastroCor obj = new CadastroCor();
						getDesktopPane().add(obj);
						obj.setVisible(true);
					}
				});
				mnCadastrar.add(mntmNewMenuItem);
		
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Novo Tamanho");
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CadastroTamanho obj = new CadastroTamanho();
						getDesktopPane().add(obj);
						obj.setVisible(true);
					}
				});
				mnCadastrar.add(mntmNewMenuItem_1);

		JMenuItem mntmNovoTipo = new JMenuItem("Novo Tipo");
		mntmNovoTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTipo obj = new CadastroTipo();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastrar.add(mntmNovoTipo);

		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);

		JMenuItem mntmAlterarImagem = new JMenuItem("Alterar imagem");
		mntmAlterarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				
				if (prodId > 0) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					// TODO
					JFileChooser arquivo = new JFileChooser();

					int retorno = arquivo.showOpenDialog(null);
					String caminhoArquivo = "";

					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if (retorno == JFileChooser.APPROVE_OPTION) {

						caminhoArquivo = arquivo.getSelectedFile()
								.getAbsolutePath();
						File file = new File(caminhoArquivo);
						long fileSize = file.length();

						if ((fileSize / 1024) > (long) 1 * 1024) {
							String erro = "Não foi possível carregar o arquivo: o tamanho máximo permitido é de 1mb.";
							JOptionPane.showMessageDialog(null, erro,
									"Erro ao carregar o arquivo!",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Session session = HibernateUtil.getSessionFactory().openSession();
							try {

								FileInputStream fileStream = new FileInputStream(file);
								LobHelper lobHelper = session.getLobHelper();
								Blob imgBlob = lobHelper.createBlob(fileStream,
										fileSize);

								em.changePicture(prodId, imgBlob);

								try {
									Entrada ei = em.findOneWhere("id", String.valueOf(prodId));
									BufferedImage bi = MyImageUtil.setMaxWidthHeight(ei.getImagemBlob(),
											picProduto.getWidth(), picProduto.getHeight());
									drawPicture(bi);
								} catch (SQLException err) {
									err.printStackTrace();
								} catch (IOException err) {
									err.printStackTrace();
								}
							} catch (FileNotFoundException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
						}
					} else {
						System.out.println("Não Abriu");
					}
				}
			}
		});
		mnEditar.add(mntmAlterarImagem);

		JMenuItem mntmAlterarProdutoSelecionado = new JMenuItem(
				"Alterar produto selecionado");
		mntmAlterarProdutoSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (prodId > 0) {
					ProdutoCadastroDialog pcDiag = new ProdutoCadastroDialog(prodId);
					pcDiag.setVisible(true);
					atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(null, "Não há nenhum produto selecionado para a edição");
				}
			}
		});
		mnEditar.add(mntmAlterarProdutoSelecionado);

		JMenuItem mntmExcluirProdutoSelecionado = new JMenuItem(
				"Excluir produto selecionado");
		mntmExcluirProdutoSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				int opcao;
				opcao = JOptionPane
						.showConfirmDialog(null,
								"Deseja Realmente excluir o produto "
										+ prodId + "?", "Atencão",
								JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						em.deleteById(prodId);
						prodId = 0;
						picProduto.setIcon(null);
						
						atualizarTabela();
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		mnEditar.add(mntmExcluirProdutoSelecionado);

		JMenu mnTabela = new JMenu("Tabela");
		menuBar.add(mnTabela);

		JMenuItem mntmAtualizarTabela = new JMenuItem("Atualizar tabela");
		mntmAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		mnTabela.add(mntmAtualizarTabela);
		
		JSeparator separator = new JSeparator();
		mnTabela.add(separator);

		JMenuItem mntmLimparFiltros = new JMenuItem("Limpar filtros");
		mntmLimparFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarFiltro();
				
				filtrar();
			}
		});
		mnTabela.add(mntmLimparFiltros);

		JMenuItem mntmFiltrarTabela = new JMenuItem("Filtrar tabela");
		mntmFiltrarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		mnTabela.add(mntmFiltrarTabela);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmGerarRelatrioDo = new JMenuItem(
				"Gerar relat\u00F3rio do filtro");
		mntmGerarRelatrioDo.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				List produtos = new ArrayList();
				
				for (int i = 0; i < table.getRowCount(); i += 1) {
					produtos.add(em.findOneWhere("id", String.valueOf(table.getValueAt(i, 0))));
				}
				
				boolean proceed = false;
				
				if (produtos.size() > 100) {
					JOptionPane.showMessageDialog(null, "Atenção! Há uma enorme quantidade de produtos. O relatório demorará alguns minutos para ser gerado. Tem certeza de que deseja continuar?");
					proceed = true;
				} else {
					proceed = true;
				}
				
				if (proceed) {
					gerarRelatorio(produtos);
				}
			}
		});
		mnRelatrios.add(mntmGerarRelatrioDo);

		JMenuItem mntmGerarTodos = new JMenuItem("Gerar todos");
		mntmGerarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List produtos = em.findAll();
				
				boolean proceed = false;
				
				if (produtos.size() > 100) {
					JOptionPane.showMessageDialog(null, "Atenção! Há uma enorme quantidade de produtos. O relatório demorará alguns minutos para ser gerado. Tem certeza de que deseja continuar?");
					proceed = true;
				} else {
					proceed = true;
				}
				
				if (proceed) {
					gerarRelatorio(produtos);
				}
			}
		});
		mnRelatrios.add(mntmGerarTodos);

		atualizarCombos();

	}
	
	private void atualizarTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		    	model.removeRow(i);
		    }
		}
		
		for (Object o : EntradaModel.findAll()) {
			Entrada e = (Entrada) o;

			boolean hasSaida = sm.hasSaida(e.getId());
			int qtdeDisponivel = 0;

			if (hasSaida) {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido() - sm.getQtdeSaida();
			} else {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido();
			}
			
			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(e.getId()); 									// 0
			oneRow.add(e.getDescricao()); 							// 1
			oneRow.add(e.getConsignatario().getNome());				// 2
			oneRow.add(e.getCategoria().getName());		 			// 3
			oneRow.add(e.getMarca().getName()); 					// 4
			oneRow.add(e.getTamanho().getName()); 					// 5
			oneRow.add(e.getCor().getName()); 						// 6
			oneRow.add(String.valueOf(e.getVenda())); 				// 7
			oneRow.add(String.valueOf(qtdeDisponivel));				// 8
			oneRow.add(String.valueOf(sm.getQtdeSaida()));			// 9
			oneRow.add(String.valueOf(e.getDevolvido()));			// 10
			oneRow.add(String.valueOf(e.getnEncontrado()));			// 11
			oneRow.add(df.format(e.getDataEntrada())); 				// 12
			oneRow.add(df.format(e.getDataInicio())); 				// 13
			oneRow.add(df.format(e.getDataVencimento())); 			// 14
			oneRow.add(e.getSituacao().getName()); 					// 15
			oneRow.add(e.getTipo().getName()); 						// 16

			model.addRow(oneRow);
		}
		
		table.setModel(model);
		
	}

	private void populateModel() {
		Vector<String> colunas = new Vector();

		colunas.add("Cód. Produto"); 		// 0
		colunas.add("Descrição");			// 1
		colunas.add("Consignatário");		// 2
		colunas.add("Categoria");			// 3
		colunas.add("Marca");				// 4
		colunas.add("Tamanho");				// 5
		colunas.add("Cor");					// 6
		colunas.add("Preço");				// 7
		colunas.add("qt. estoque");				// 8
		colunas.add("qt. vendidos");			// 9
		colunas.add("qt. devolvidos");			// 10
		colunas.add("qt. não encontrados");		// 11
		colunas.add("Data Entrada");		// 12
		colunas.add("Data Início");			// 13
		colunas.add("Data Vencim.");		// 14
		colunas.add("Situação");			// 15
		colunas.add("Tipo");				// 16

		Vector tableData = new Vector();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Object o : EntradaModel.findAll()) {
			Entrada e = (Entrada) o;

			boolean hasSaida = sm.hasSaida(e.getId());
			int qtdeDisponivel = 0;

			if (hasSaida) {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido() - sm.getQtdeSaida();
			} else {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido();
			}
			
			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(e.getId()); 								// 0
			oneRow.add(e.getDescricao()); 						// 1
			oneRow.add(e.getConsignatario().getNome());			// 2
			oneRow.add(e.getCategoria().getName());		 		// 3
			oneRow.add(e.getMarca().getName()); 				// 4
			oneRow.add(e.getTamanho().getName()); 				// 5
			oneRow.add(e.getCor().getName()); 					// 6
			oneRow.add(String.format("%.2f", e.getVenda())); 	// 7
			oneRow.add(qtdeDisponivel);							// 8
			oneRow.add(sm.getQtdeSaida());						// 9
			oneRow.add(e.getDevolvido());						// 10
			oneRow.add(e.getnEncontrado());						// 11
			oneRow.add(df.format(e.getDataEntrada())); 			// 12
			oneRow.add(df.format(e.getDataInicio())); 			// 13
			oneRow.add(df.format(e.getDataVencimento())); 		// 14
			oneRow.add(e.getSituacao().getName()); 				// 15
			oneRow.add(e.getTipo().getName()); 					// 16

			tableData.add(oneRow);
		}

		model = new DefaultTableModel(tableData, colunas) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};
		
		table.setModel(model);
	}

	protected void selecionarItem(long idRow) {
		Entrada eSelecionada = em.findOneWhere("id", String.valueOf(idRow));

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			drawPicture(bi);
			lblDescrioDoProduto.setText(eSelecionada.getDescricao());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawPicture(BufferedImage bi) {
		if (bi != null) {
			picProduto.setText("");
			picProduto.setIcon(new ImageIcon(bi));
			
		}
	}

	public void atualizarCombos() {
		cmbCat.removeAllItems();
		cmbCat.addItem("todos");
		Iterator categorias = CategoriaModel.findAll().iterator();
		while (categorias.hasNext()) {
			Categoria c = (Categoria) categorias.next();
			cmbCat.addItem(c.getName());
		}
		cmbCat.setSelectedItem("todos");

		cmbMarca.removeAllItems();
		cmbMarca.addItem("todos");
		Iterator marcas = MarcaModel.findAll().iterator();
		while (marcas.hasNext()) {
			Marca m = (Marca) marcas.next();
			cmbMarca.addItem(m.getName());
		}
		cmbMarca.setSelectedItem("todos");

		cmbCor.removeAllItems();
		cmbCor.addItem("todos");
		Iterator cores = CorModel.findAll().iterator();
		while (cores.hasNext()) {
			Cor c = (Cor) cores.next();
			cmbCor.addItem(c.getName());
		}
		cmbCor.setSelectedItem("todos");

		cmbTam.removeAllItems();
		cmbTam.addItem("todos");
		Iterator tamanhos = TamanhoModel.findAll().iterator();
		while (tamanhos.hasNext()) {
			Tamanho t = (Tamanho) tamanhos.next();
			cmbTam.addItem(t.getName());
		}
		cmbTam.setSelectedItem("todos");

		cmbTipo.removeAllItems();
		cmbTipo.addItem("todos");
		Iterator tipos = TipoModel.findAll().iterator();

		while (tipos.hasNext()) {
			Tipo t = (Tipo) tipos.next();
			cmbTipo.addItem(t.getName());
		}
		cmbTipo.setSelectedItem("todos");

		cmbSit.removeAllItems();
		cmbSit.addItem("todos");
		Iterator situacoes = SituacaoModel.findAll().iterator();
		while (situacoes.hasNext()) {
			Situacao s = (Situacao) situacoes.next();
			cmbSit.addItem(s.getName());
		}
		cmbSit.setSelectedItem("todos");
	}

	private HashMap<String, Object> verificarDataFiltro() {
		HashMap<String, Object> filtro = new HashMap<String, Object>();
		if (!txtDesc.getText().trim().isEmpty()) {
			filtro.put("descricao", txtDesc.getText());
		}
		if (!cmbMarca.getSelectedItem().equals("todos")) {
			filtro.put("marca", cmbMarca.getSelectedItem());
		}
		if (!cmbCat.getSelectedItem().equals("todos")) {
			filtro.put("categoria", cmbCat.getSelectedItem());
		}
		if (!cmbTam.getSelectedItem().equals("todos")) {
			filtro.put("tamanho", cmbTam.getSelectedItem());
		}
		if (!cmbCor.getSelectedItem().equals("todos")) {
			filtro.put("cor", cmbCor.getSelectedItem());
		}
		if (!cmbSit.getSelectedItem().equals("todos")) {
			filtro.put("situacao", cmbSit.getSelectedItem());
		}
		if (!txtVendaMaiorQ.getText().trim().isEmpty()) {
			filtro.put("vendaMaior", txtVendaMaiorQ.getText());
		}
		if (!txtConsig.getText().trim().isEmpty()) {
			filtro.put("consignatario", txtConsig.getText());
		}
		if (!(txtDataInicio.getText().trim().length() < 10)) {
			filtro.put("dataInicio", txtDataInicio.getText());
		}
		if (!(txtDataVenc.getText().trim().length() < 10)) {
			filtro.put("dataVencimento", txtDataVenc.getText());
		}
		if (!cmbTipo.getSelectedItem().equals("todos")) {
			filtro.put("tipo", cmbTipo.getSelectedItem());
		}
		if (ckDevolvidos.isSelected()) {
			filtro.put("devolvidos", true);
		}
		if (ckEstoque.isSelected()) {
			filtro.put("estoque", true);
		}
		if (ckNEncontrados.isSelected()) {
			filtro.put("nencontrados", true);
		}
		if (ckVencidos.isSelected()) {
			filtro.put("vencidos", true);
		}
		return filtro;
	}

	protected void filtrar() {
		RowFilter<Object, Object> f = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				HashMap<String, Object> filter = verificarDataFiltro();
				boolean isValid = true;
				Entrada ef = em.findOneWhere("id", String.valueOf( (long) entry.getValue(0) ) );

				if (filter.containsKey("descricao")) {
					isValid = (((String) entry.getValue(1)).toLowerCase().contains(((String) filter.get("descricao")).toLowerCase()) && isValid);
				}

				if (filter.containsKey("categoria")) {
					isValid = (((String) entry.getValue(3))
							.equals((String) filter.get("categoria")) && isValid);
				}

				if (filter.containsKey("marca")) {
					isValid = (((String) entry.getValue(4))
							.equals((String) filter.get("marca")) && isValid);
				}

				if (filter.containsKey("tamanho")) {
					isValid = (((String) entry.getValue(5))
							.equals((String) filter.get("tamanho")) && isValid);
				}

				if (filter.containsKey("cor")) {
					isValid = (((String) entry.getValue(6))
							.equals((String) filter.get("cor")) && isValid);
				}

				if (filter.containsKey("situacao")) {
					isValid = (((String) entry.getValue(11))
							.equals((String) filter.get("situacao")) && isValid);
				}

				if (filter.containsKey("vendaMaior")) {
					isValid = ((Double.parseDouble((String) entry.getValue(7)) >= Double
							.parseDouble((String) filter.get("vendaMaior"))) && isValid);
				}

				if (filter.containsKey("consignatario")) {
					isValid = (((String) entry.getValue(2))
							.contains((String) filter.get("consignatario")) && isValid);
				}

				if (filter.containsKey("dataInicio")) {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy");
					try {
						Date dataInicioTabela = formatter.parse((String) entry
								.getValue(9));
						Date dataInicioFiltro = formatter.parse((String) filter
								.get("dataInicio"));

						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();

						cal1.setTime(dataInicioTabela);
						cal2.setTime(dataInicioFiltro);

						
						isValid = ((cal1.after(cal2) || cal1.equals(cal2)) && isValid);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				if (filter.containsKey("dataVencimento")) {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy");
					try {
						Date dataVencimentoTabela = formatter
								.parse((String) entry.getValue(10));
						Date dataVencimentoFiltro = formatter
								.parse((String) filter.get("dataVencimento"));

						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();

						cal1.setTime(dataVencimentoTabela);
						cal2.setTime(dataVencimentoFiltro);

						boolean sameDay = cal1.get(Calendar.YEAR) == cal2
								.get(Calendar.YEAR)
								&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
										.get(Calendar.DAY_OF_YEAR);

						isValid = (sameDay) && isValid;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (filter.containsKey("tipo")) {
					isValid = (((String) entry.getValue(12))
							.equals((String) filter.get("tipo")) && isValid);
				}
				
				if (filter.containsKey("devolvidos")) {
					isValid = (int) entry.getValue(10) > 0;
				}
				if (filter.containsKey("estoque")) {					
					
					isValid = (int) entry.getValue(8) > 0;
				}
				if (filter.containsKey("vencidos")) {
					isValid = (int) entry.getValue(8) > 0;
					
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();

					cal1.setTime(new Date());
					cal2.setTime(ef.getDataVencimento());

					boolean sameDay = cal1.get(Calendar.YEAR) == cal2
							.get(Calendar.YEAR)
							&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
									.get(Calendar.DAY_OF_YEAR);

					isValid = ( cal1.after(cal2) || (sameDay) ) && isValid;
				}
				if (filter.containsKey("nencontrados")) {
					isValid = (int) entry.getValue(11) > 0;
				}
				
				return isValid;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		sorter.setRowFilter(f);
		table.setRowSorter(sorter);
	}

	private void gerarRelatorio(List produtos) {
		List dataReport = new ArrayList();
		Map<String, Object> data;
		
		SimpleDateFormat df = new SimpleDateFormat(
				"dd/MM/yyyy");
		
		for (Object o : produtos) {
			Entrada e = (Entrada) o;
			
			boolean hasSaida = sm.hasSaida(e.getId());
			int qtdeDisponivel = 0;

			if (hasSaida) {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido() - sm.getQtdeSaida();
			} else {
				qtdeDisponivel = e.getQuantidate() - e.getnEncontrado()
						- e.getDevolvido();
			}
			
			data = new HashMap<>();
			
			data.put("codigo", String.valueOf(e.getId()));
			data.put("descricao", e.getDescricao());
			data.put("consig", e.getConsignatario().getNome());
			data.put("preco", "R$ " + String.format("%.2f", (e.getVenda())));
			data.put("estoque", String.valueOf(qtdeDisponivel));
			data.put("vendidos", String.valueOf(sm.getQtdeSaida()));
			data.put("devolvidos", String.valueOf(e.getDevolvido()));
			data.put("nencontrados", String.valueOf(e.getnEncontrado()));
			data.put("dataEntrada", df.format(e.getDataEntrada()));
			data.put("dataInicio", df.format(e.getDataInicio()));
			data.put("dataVencimento", df.format(e.getDataVencimento()));
			
			Blob blob_img = e.getImagemBlob();
			data.put("image", null);
			try {
				BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
						170, 120);
				data.put("image", bi);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dataReport.add(data);
		}
		
		RelatorioProdutos.gerarFiltro(dataReport);
		
		File pdf = new File("relatorios/RelatorioProdutos.pdf");  
        try {  
          Desktop.getDesktop().open(pdf);  
        } catch(Exception ex) {  
          ex.printStackTrace();  
          JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);  
        } 
	}

	private void resetarFiltro() {
		txtDesc.setText("");
		cmbMarca.setSelectedItem("todos");
		cmbCat.setSelectedItem("todos");
		cmbTam.setSelectedItem("todos");
		cmbCor.setSelectedItem("todos");
		cmbSit.setSelectedItem("todos");
		txtVendaMaiorQ.setText("");
		txtConsig.setText("");
		txtDataInicio.setText("");
		txtDataVenc.setText("");
		cmbTipo.setSelectedItem("todos");
		ckDevolvidos.setSelected(false);
		ckEstoque.setSelected(false);
		ckNEncontrados.setSelected(false);
		ckVencidos.setSelected(false);
	}
}
