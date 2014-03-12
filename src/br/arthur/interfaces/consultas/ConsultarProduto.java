package br.arthur.interfaces.consultas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.hibernate.LazyInitializationException;

import br.arthur.entities.Entrada;
import br.arthur.entities.Imagem;
import br.arthur.models.EntradaModel;
import br.arthur.utils.MyIteratorUtil;

import com.lowagie.text.Image;

public class ConsultarProduto extends JInternalFrame {
	private JTextField txtCor;
	private JTextField txtTam;
	private JTextField txtDesc;
	private JTextField txtDataEntrada;
	private JTextField txtDataInicio;
	/**
	 * @wbp.nonvisual location=871,-11
	 */
	private final JTextField textField_5 = new JTextField();
	private JTextField txtDataVenc;
	private JTextField txtVendaMaiorQ;
	
	private int prodId;
	
	private DefaultTableModel model;
	private JTable table;
	
	private MyIteratorUtil imgListIter;
	private int imgCount = 0;
	private int imgTotalCount;
	private JLabel lblCountImg;
	private JButton btnFirstImg;
	private JButton btnPreviousImg;
	private JButton btnLastImg;
	private JButton btnNextImg;
	
	private EntradaModel em = new EntradaModel();

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
		textField_5.setColumns(10);
		setBounds(100, 100, 1037, 464);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 306, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 1015, SpringLayout.WEST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Filtrar Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 265, SpringLayout.WEST, getContentPane());
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel_1);
		
		btnFirstImg = new JButton("<<");
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -6, SpringLayout.NORTH, btnFirstImg);
		springLayout.putConstraint(SpringLayout.WEST, btnFirstImg, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, btnFirstImg, -6, SpringLayout.NORTH, panel);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.EAST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 0;
		panel.add(lblDescrio, gbc_lblDescrio);
		
		txtDesc = new JTextField();
		GridBagConstraints gbc_txtDesc = new GridBagConstraints();
		gbc_txtDesc.insets = new Insets(0, 0, 5, 5);
		gbc_txtDesc.fill = GridBagConstraints.HORIZONTAL;
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
		
		JComboBox cmbCat = new JComboBox();
		GridBagConstraints gbc_cmbCat = new GridBagConstraints();
		gbc_cmbCat.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCat.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCat.gridx = 3;
		gbc_cmbCat.gridy = 0;
		panel.add(cmbCat, gbc_cmbCat);
		
		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 4;
		gbc_lblMarca.gridy = 0;
		panel.add(lblMarca, gbc_lblMarca);
		
		JComboBox cmbMarca = new JComboBox();
		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.gridx = 5;
		gbc_cmbMarca.gridy = 0;
		panel.add(cmbMarca, gbc_cmbMarca);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanho.gridx = 6;
		gbc_lblTamanho.gridy = 0;
		panel.add(lblTamanho, gbc_lblTamanho);
		
		txtTam = new JTextField();
		GridBagConstraints gbc_txtTam = new GridBagConstraints();
		gbc_txtTam.insets = new Insets(0, 0, 5, 5);
		gbc_txtTam.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTam.gridx = 7;
		gbc_txtTam.gridy = 0;
		panel.add(txtTam, gbc_txtTam);
		txtTam.setColumns(10);
		
		JLabel lblCor = new JLabel("Cor:");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.EAST;
		gbc_lblCor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCor.gridx = 8;
		gbc_lblCor.gridy = 0;
		panel.add(lblCor, gbc_lblCor);
		
		txtCor = new JTextField();
		GridBagConstraints gbc_txtCor = new GridBagConstraints();
		gbc_txtCor.insets = new Insets(0, 0, 5, 5);
		gbc_txtCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCor.gridx = 9;
		gbc_txtCor.gridy = 0;
		panel.add(txtCor, gbc_txtCor);
		txtCor.setColumns(10);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblSituao = new GridBagConstraints();
		gbc_lblSituao.anchor = GridBagConstraints.EAST;
		gbc_lblSituao.insets = new Insets(0, 0, 5, 5);
		gbc_lblSituao.gridx = 10;
		gbc_lblSituao.gridy = 0;
		panel.add(lblSituao, gbc_lblSituao);
		
		JComboBox cmbSit = new JComboBox();
		GridBagConstraints gbc_cmbSit = new GridBagConstraints();
		gbc_cmbSit.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSit.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSit.gridx = 11;
		gbc_cmbSit.gridy = 0;
		panel.add(cmbSit, gbc_cmbSit);
		
		JLabel lblVenda = new JLabel("Venda(>):");
		GridBagConstraints gbc_lblVenda = new GridBagConstraints();
		gbc_lblVenda.anchor = GridBagConstraints.EAST;
		gbc_lblVenda.insets = new Insets(0, 0, 0, 5);
		gbc_lblVenda.gridx = 0;
		gbc_lblVenda.gridy = 1;
		panel.add(lblVenda, gbc_lblVenda);
		
		txtVendaMaiorQ = new JTextField();
		GridBagConstraints gbc_txtVendaMaiorQ = new GridBagConstraints();
		gbc_txtVendaMaiorQ.insets = new Insets(0, 0, 0, 5);
		gbc_txtVendaMaiorQ.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVendaMaiorQ.gridx = 1;
		gbc_txtVendaMaiorQ.gridy = 1;
		panel.add(txtVendaMaiorQ, gbc_txtVendaMaiorQ);
		txtVendaMaiorQ.setColumns(10);
		
		JLabel lblDataEntrada = new JLabel("Data entrada:");
		GridBagConstraints gbc_lblDataEntrada = new GridBagConstraints();
		gbc_lblDataEntrada.anchor = GridBagConstraints.EAST;
		gbc_lblDataEntrada.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataEntrada.gridx = 2;
		gbc_lblDataEntrada.gridy = 1;
		panel.add(lblDataEntrada, gbc_lblDataEntrada);
		
		txtDataEntrada = new JTextField();
		GridBagConstraints gbc_txtDataEntrada = new GridBagConstraints();
		gbc_txtDataEntrada.insets = new Insets(0, 0, 0, 5);
		gbc_txtDataEntrada.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataEntrada.gridx = 3;
		gbc_txtDataEntrada.gridy = 1;
		panel.add(txtDataEntrada, gbc_txtDataEntrada);
		txtDataEntrada.setColumns(10);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio");
		GridBagConstraints gbc_lblDataIncio = new GridBagConstraints();
		gbc_lblDataIncio.anchor = GridBagConstraints.EAST;
		gbc_lblDataIncio.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataIncio.gridx = 4;
		gbc_lblDataIncio.gridy = 1;
		panel.add(lblDataIncio, gbc_lblDataIncio);
		
		txtDataInicio = new JTextField();
		GridBagConstraints gbc_txtDataInicio = new GridBagConstraints();
		gbc_txtDataInicio.insets = new Insets(0, 0, 0, 5);
		gbc_txtDataInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataInicio.gridx = 5;
		gbc_txtDataInicio.gridy = 1;
		panel.add(txtDataInicio, gbc_txtDataInicio);
		txtDataInicio.setColumns(10);
		
		JLabel lblVencimento = new JLabel("Vencimento:");
		GridBagConstraints gbc_lblVencimento = new GridBagConstraints();
		gbc_lblVencimento.anchor = GridBagConstraints.EAST;
		gbc_lblVencimento.insets = new Insets(0, 0, 0, 5);
		gbc_lblVencimento.gridx = 6;
		gbc_lblVencimento.gridy = 1;
		panel.add(lblVencimento, gbc_lblVencimento);
		
		txtDataVenc = new JTextField();
		GridBagConstraints gbc_txtDataVenc = new GridBagConstraints();
		gbc_txtDataVenc.insets = new Insets(0, 0, 0, 5);
		gbc_txtDataVenc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataVenc.gridx = 7;
		gbc_txtDataVenc.gridy = 1;
		panel.add(txtDataVenc, gbc_txtDataVenc);
		txtDataVenc.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipo.gridx = 8;
		gbc_lblTipo.gridy = 1;
		panel.add(lblTipo, gbc_lblTipo);
		
		JComboBox cmbTipo = new JComboBox();
		GridBagConstraints gbc_cmbTipo = new GridBagConstraints();
		gbc_cmbTipo.insets = new Insets(0, 0, 0, 5);
		gbc_cmbTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipo.gridx = 9;
		gbc_cmbTipo.gridy = 1;
		panel.add(cmbTipo, gbc_cmbTipo);
		
		JButton btnLimparFiltro = new JButton("Limpar Filtro");
		GridBagConstraints gbc_btnLimparFiltro = new GridBagConstraints();
		gbc_btnLimparFiltro.insets = new Insets(0, 0, 0, 5);
		gbc_btnLimparFiltro.gridx = 10;
		gbc_btnLimparFiltro.gridy = 1;
		panel.add(btnLimparFiltro, gbc_btnLimparFiltro);
		
		JButton btnFiltrar = new JButton("Filtrar");
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFiltrar.gridx = 11;
		gbc_btnFiltrar.gridy = 1;
		panel.add(btnFiltrar, gbc_btnFiltrar);
		getContentPane().add(btnFirstImg);
		
		btnPreviousImg = new JButton("<");
		springLayout.putConstraint(SpringLayout.WEST, btnPreviousImg, 4, SpringLayout.EAST, btnFirstImg);
		springLayout.putConstraint(SpringLayout.SOUTH, btnPreviousImg, -6, SpringLayout.NORTH, panel);
		btnPreviousImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(btnPreviousImg);
		
		btnLastImg = new JButton(">>");
		springLayout.putConstraint(SpringLayout.NORTH, btnLastImg, 0, SpringLayout.NORTH, btnFirstImg);
		springLayout.putConstraint(SpringLayout.EAST, btnLastImg, -760, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLastImg);
		
		JButton btnNextImg = new JButton(">");
		springLayout.putConstraint(SpringLayout.NORTH, btnNextImg, 0, SpringLayout.NORTH, btnFirstImg);
		springLayout.putConstraint(SpringLayout.EAST, btnNextImg, -6, SpringLayout.WEST, btnLastImg);
		getContentPane().add(btnNextImg);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 6, SpringLayout.EAST, panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JLabel lblImagem = new JLabel("Imagem");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblImagem, "name_11027006424649");
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, panel);
		getContentPane().add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("Cód. Produto");
		colunas.add("Cód. Entrada");
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
//		for(Object o : EntradaModel.findAll()) {
//			Entrada e = (Entrada) o;
//			
//			Vector<Object> oneRow = new Vector<Object>();
//			
//			oneRow.add(e.getId());
//			oneRow.add(e.getHeaderEntrada().getId());
//			oneRow.add(e.getHeaderEntrada().getConsignatario().getNome());
//			oneRow.add(e.getCategoria().getName());
//			oneRow.add(e.getMarca().getName());
//			oneRow.add(e.getTamanho());
//			oneRow.add(e.getCor());
//			oneRow.add(String.valueOf(e.getVenda()));
//			oneRow.add(df.format(e.getDataEntrada()));
//			oneRow.add(df.format(e.getDataInicio()));
//			oneRow.add(df.format(e.getDataVencimento()));
//			oneRow.add(e.getSituacao().getName());
//			oneRow.add(e.getTipo().getName());
//			
//			tableData.add(oneRow);
//		}
		
		model = new DefaultTableModel(tableData, colunas) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
	    	boolean[] columnEditables = new boolean[] {
	    		false, false, false, false, false, false
	    	};
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}

	    };
	    
	    table = new JTable(model);
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		int linhaSelecionadaProduto = table.getSelectedRow();
	    		
	    		int prodId = (int) table.getValueAt(linhaSelecionadaProduto, 0);    		
	    		
	    	}
	    });
	    
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
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
		
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
		      public boolean include(Entry entry) {
		        Integer population = (Integer) entry.getValue(0);
		        return population.intValue() > 0;
		      }
		    };
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    sorter.setRowFilter(filter);
	    table.setRowSorter(sorter);
		
		JScrollPane scrollPaneProdutos = new JScrollPane(table);
		panel_2.add(scrollPaneProdutos, "name_10326065285220");
		
		lblCountImg = new JLabel("0/0");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountImg, -25, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblCountImg, 6, SpringLayout.EAST, btnPreviousImg);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCountImg, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, lblCountImg, -6, SpringLayout.WEST, btnNextImg);
		lblCountImg.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCountImg.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCountImg);
		
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
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmAdicionarImagem = new JMenuItem("Adicionar imagem");
		mnEditar.add(mntmAdicionarImagem);
		
		JMenuItem mntmAlterarImagem = new JMenuItem("Alterar imagem");
		mnEditar.add(mntmAlterarImagem);
		
		JMenuItem mntmExcluirImagem = new JMenuItem("Excluir imagem");
		mnEditar.add(mntmExcluirImagem);
		
		JMenuItem mntmAlterarProdutoSelecionado = new JMenuItem("Alterar produto selecionado");
		mnEditar.add(mntmAlterarProdutoSelecionado);
		
		JMenuItem mntmExcluirProdutoSelecionado = new JMenuItem("Excluir produto selecionado");
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
		
		JMenuItem mntmGerarRelatrioDo = new JMenuItem("Gerar relat\u00F3rio do filtro");
		mnRelatrios.add(mntmGerarRelatrioDo);
		
		JMenuItem mntmGerarVencidos = new JMenuItem("Gerar vencidos");
		mnRelatrios.add(mntmGerarVencidos);
		
		JMenuItem mntmGerarTodos = new JMenuItem("Gerar todos");
		mnRelatrios.add(mntmGerarTodos);

	}

}
