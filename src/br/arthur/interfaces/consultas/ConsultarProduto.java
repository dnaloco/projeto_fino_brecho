package br.arthur.interfaces.consultas;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import br.arthur.entities.Categoria;
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
import br.arthur.models.SituacaoModel;
import br.arthur.models.TamanhoModel;
import br.arthur.models.TipoModel;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;
import br.arthur.utils.MyIteratorUtil;

public class ConsultarProduto extends JInternalFrame {
	private JTextField txtDesc;
	private JNumericField txtVendaMaiorQ;

	private int prodId;

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

	private JLabel picProduto;

	private EntradaModel em = new EntradaModel();
	private JTextField txtConsig;
	private JTextField txtDataInicio;
	private JTextField txtDataVenc;

	private JLabel lblDataIncio;

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
		setBounds(100, 100, 1059, 498);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10,
				SpringLayout.SOUTH, getContentPane());
		panel.setBorder(new TitledBorder(null, "Filtrar Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 265,
				SpringLayout.WEST, getContentPane());
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 59, 210, 120, 90, 90, 81, 120,
				122, 120 };
		gbl_panel.rowHeights = new int[] { 28, 28, 28, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
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
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6,
				SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -144,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 6,
				SpringLayout.EAST, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, panel_2, -10,
				SpringLayout.EAST, getContentPane());
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setLayout(new CardLayout(0, 0));

		picProduto = new JLabel("Imagem");
		picProduto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(picProduto, "name_11027006424649");
		getContentPane().add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));

		Vector<String> colunas = new Vector();

		colunas.add("Cód. Produto");
		colunas.add("Descrição");
		colunas.add("Consignatário");
		colunas.add("Categoria");
		colunas.add("Marca");
		colunas.add("Tamanho");
		colunas.add("Cor");
		colunas.add("Preço");
		colunas.add("Data Entrada");
		colunas.add("Data Início");
		colunas.add("Data Vencim.");
		colunas.add("Situação");
		colunas.add("Tipo");

		Vector tableData = new Vector();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Object o : EntradaModel.findAll()) {
			Entrada e = (Entrada) o;

			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(e.getId()); // 0
			oneRow.add(e.getDescricao()); // 1
			oneRow.add(e.getConsignatario().getNome()); // 2
			oneRow.add(e.getCategoria().getName()); // 3
			oneRow.add(e.getMarca().getName()); // 4
			oneRow.add(e.getTamanho().getName()); // 5
			oneRow.add(e.getCor().getName()); // 6
			oneRow.add(String.valueOf(e.getVenda())); // 7
			oneRow.add(df.format(e.getDataEntrada())); // 8
			oneRow.add(df.format(e.getDataInicio())); // 9
			oneRow.add(df.format(e.getDataVencimento())); // 10
			oneRow.add(e.getSituacao().getName()); // 11
			oneRow.add(e.getTipo().getName()); // 12

			tableData.add(oneRow);
		}

		model = new DefaultTableModel(tableData, colunas) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linhaSelecionadaProduto = table.getSelectedRow();

				long prodId = (long) table.getValueAt(linhaSelecionadaProduto,
						0);

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
		table.getColumnModel().getColumn(11).setPreferredWidth(120);

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

		JButton btnTrocarFoto = new JButton("Trocar Foto");
		springLayout.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.NORTH, btnTrocarFoto, 272,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -6,
				SpringLayout.NORTH, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.WEST, btnTrocarFoto, 10,
				SpringLayout.WEST, getContentPane());
		getContentPane().add(btnTrocarFoto);

		JButton btnExcluirProduto = new JButton("Excluir Produto");
		springLayout.putConstraint(SpringLayout.SOUTH, btnExcluirProduto, -144,
				SpringLayout.SOUTH, getContentPane());

		JButton btnLimparFiltro = new JButton("Limpar Filtro");
		btnLimparFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				filtrar();
			}
		});
		GridBagConstraints gbc_btnLimparFiltro = new GridBagConstraints();
		gbc_btnLimparFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimparFiltro.anchor = GridBagConstraints.NORTH;
		gbc_btnLimparFiltro.insets = new Insets(0, 0, 0, 5);
		gbc_btnLimparFiltro.gridx = 6;
		gbc_btnLimparFiltro.gridy = 2;
		panel.add(btnLimparFiltro, gbc_btnLimparFiltro);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnFiltrar.anchor = GridBagConstraints.NORTH;
		gbc_btnFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFiltrar.gridx = 7;
		gbc_btnFiltrar.gridy = 2;
		panel.add(btnFiltrar, gbc_btnFiltrar);
		springLayout.putConstraint(SpringLayout.EAST, btnTrocarFoto, -6,
				SpringLayout.WEST, btnExcluirProduto);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProduto, 145,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProduto, 0,
				SpringLayout.EAST, panel_1);
		getContentPane().add(btnExcluirProduto);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		JMenuItem mntmNovaEntrada = new JMenuItem("Nova entrada");
		mnCadastrar.add(mntmNovaEntrada);

		JMenuItem mntmNovaCategoria = new JMenuItem("Nova categoria");
		mnCadastrar.add(mntmNovaCategoria);

		JMenuItem mntmNovaMarca = new JMenuItem("Nova marca");
		mnCadastrar.add(mntmNovaMarca);

		JMenuItem mntmNovoTipo = new JMenuItem("Novo tipo");
		mnCadastrar.add(mntmNovoTipo);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnCadastrar.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnCadastrar.add(mntmNewMenuItem_1);

		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);

		JMenuItem mntmAlterarImagem = new JMenuItem("Alterar imagem");
		mnEditar.add(mntmAlterarImagem);

		JMenuItem mntmAlterarProdutoSelecionado = new JMenuItem(
				"Alterar produto selecionado");
		mnEditar.add(mntmAlterarProdutoSelecionado);

		JMenuItem mntmExcluirProdutoSelecionado = new JMenuItem(
				"Excluir produto selecionado");
		mnEditar.add(mntmExcluirProdutoSelecionado);

		JMenuItem mntmExcluirTodosDo = new JMenuItem("Excluir todos do filtro");
		mnEditar.add(mntmExcluirTodosDo);

		JMenu mnTabela = new JMenu("Tabela");
		menuBar.add(mnTabela);

		JMenuItem mntmAtualizarTabela = new JMenuItem("Atualizar tabela");
		mnTabela.add(mntmAtualizarTabela);

		JMenuItem mntmLimparFiltros = new JMenuItem("Limpar filtros");
		mnTabela.add(mntmLimparFiltros);

		JMenuItem mntmFiltrarTabela = new JMenuItem("Filtrar tabela");
		mnTabela.add(mntmFiltrarTabela);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmGerarRelatrioDo = new JMenuItem(
				"Gerar relat\u00F3rio do filtro");
		mnRelatrios.add(mntmGerarRelatrioDo);

		JMenuItem mntmGerarVencidos = new JMenuItem("Gerar vencidos");
		mnRelatrios.add(mntmGerarVencidos);

		JMenuItem mntmGerarTodos = new JMenuItem("Gerar todos");
		mnRelatrios.add(mntmGerarTodos);

		atualizarCombos();

	}

	protected void selecionarItem(long idRow) {
		Entrada eSelecionada = em.findOneWhere("id", String.valueOf(idRow));

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			drawPicture(bi);
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
		return filtro;
	}

	protected void filtrar() {
		RowFilter<Object, Object> f = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				HashMap<String, Object> filter = verificarDataFiltro();
				boolean isValid = true;

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

						isValid = (sameDay);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (filter.containsKey("tipo")) {
					isValid = (((String) entry.getValue(12))
							.equals((String) filter.get("tipo")) && isValid);
				}

				return isValid;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		sorter.setRowFilter(f);
		table.setRowSorter(sorter);
	}

	private static class __Tmp {
		private static void __tmp() {
			javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
