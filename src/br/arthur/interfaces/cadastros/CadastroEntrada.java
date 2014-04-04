package br.arthur.interfaces.cadastros;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.LobHelper;
import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Consignatario;
import br.arthur.entities.Cor;
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tamanho;
import br.arthur.entities.Tipo;
import br.arthur.interfaces.Principal;
import br.arthur.interfaces.cadastros.dialogs.ConsignatarioDialog;
import br.arthur.interfaces.cadastros.dialogs.PicZoomDialog;
import br.arthur.models.CategoriaModel;
import br.arthur.models.ConsignatarioModel;
import br.arthur.models.CorModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.MarcaModel;
import br.arthur.models.SituacaoModel;
import br.arthur.models.TamanhoModel;
import br.arthur.models.TipoModel;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;
import br.arthur.utils.MyIteratorUtil;

public class CadastroEntrada extends JInternalFrame {
	private JTextField txtCodprod;
	private JComboBox cmbCor;
	private JComboBox cmbTamanho;
	private JTextField txtConsign;
	private JNumericField txtQtde;
	private JTextField txtDescricao;
	private JTextArea textAreaObserv;
	private JTextField txtDataInicio;
	private JNumericField txtComis;
	private JNumericField txtMargem;
	private JNumericField txtVenda;
	private JTextField txtLocal;

	private ConsignatarioModel cm = new ConsignatarioModel();
	private int consigId = 0;

	private EntradaModel em = new EntradaModel();
	private long entradaId = 0;

	private static int itemNum = 0;

	private JButton btnImportarFotos;

	private JButton btnExcluirProd;
	private JButton btnAdicionarFoto;
	private JComboBox cmbCategoria;
	private JComboBox cmbMarca;
	private JComboBox cmbValidade;
	private JComboBox cmbTipo;
	private JComboBox cmbSituacao;
	private JLabel lblComis;
	private JLabel lblCusto;
	private JButton btnSalvarProduto;
	private JButton btnTrocarFoto;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnAtualizarCads;
	private JLabel lblEntradasList;

	private ArrayList<Long> entradas = new ArrayList<Long>();
	private MyIteratorUtil entradasListIter;
	private int entradasTotal;
	private int entradaSelecionada;
	private JLabel picProduto;

	private double custo;
	private double comissao;

	private String[] colunas = new String[] { "Cód. Produto", "Descricao",
			"Consignatário", "Categoria", "Marca", "Situacao", "Venda", "Qtde",
			"Vencimento" };

	private String[][] dataTable = new String[][] {};

	DefaultTableModel model = new DefaultTableModel(dataTable, colunas) {
		public Class<?> getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false,
				false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

	};
	private JTable jtableProdutos = new JTable(model);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEntrada frame = new CadastroEntrada();
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
	public CadastroEntrada() {
		setTitle("Cadastro de Entradas");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 941, 584);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JLabel lblCdProduto = new JLabel("C\u00F3d. Produto:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCdProduto, 14,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCdProduto, -801,
				SpringLayout.EAST, getContentPane());
		lblCdProduto.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblCdProduto);

		txtCodprod = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCodprod, 6,
				SpringLayout.EAST, lblCdProduto);
		springLayout.putConstraint(SpringLayout.EAST, txtCodprod, -680,
				SpringLayout.EAST, getContentPane());
		txtCodprod.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, txtCodprod, 10,
				SpringLayout.NORTH, getContentPane());
		txtCodprod.setText("CodProd");
		getContentPane().add(txtCodprod);
		txtCodprod.setColumns(10);

		JLabel lblNewLabel = new JLabel("Situa\u00E7\u00E3o:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0,
				SpringLayout.NORTH, lblCdProduto);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 6,
				SpringLayout.EAST, txtCodprod);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblNewLabel);

		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		springLayout.putConstraint(SpringLayout.WEST, lblConsignatrio, 461,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblConsignatrio, 0,
				SpringLayout.NORTH, lblCdProduto);
		lblConsignatrio.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblConsignatrio);

		txtConsign = new JTextField();
		txtConsign.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, txtConsign, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtConsign, 6,
				SpringLayout.EAST, lblConsignatrio);
		txtConsign.setText("Nome do Consignat\u00E1rio");
		getContentPane().add(txtConsign);
		txtConsign.setColumns(10);

		JButton btnBuscarConsig = new JButton("Buscar");
		btnBuscarConsig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cm.findAll().size() > 0) {
					ConsignatarioDialog cDiag = new ConsignatarioDialog();
					cDiag.setVisible(true);

					consigId = cDiag.getTheId();

					if (consigId > 0) {
						Consignatario ce = cm.findOneWhere("id",
								String.valueOf(consigId));
						txtConsign.setText(ce.getNome());
						btnImportarFotos.setEnabled(true);
						btnAdicionarFoto.setEnabled(true);
						btnAtualizarCads.setEnabled(true);
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Não existe ainda nenhum consignatário registrado no sistema!");
				}

			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnBuscarConsig, -10,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtConsign, -6,
				SpringLayout.WEST, btnBuscarConsig);
		springLayout.putConstraint(SpringLayout.WEST, btnBuscarConsig, 787,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscarConsig, 10,
				SpringLayout.NORTH, getContentPane());
		getContentPane().add(btnBuscarConsig);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, btnBuscarConsig);
		panel.setBorder(new TitledBorder(null,
				"Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 0,
				SpringLayout.WEST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 342, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10,
				SpringLayout.EAST, getContentPane());
		panel_1.setBorder(new TitledBorder(null,
				"Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtDescricao = new JTextField();

		txtDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtVenda.requestFocus();
					txtVenda.selectAll();
				}
			}
		});
		txtDescricao.setEnabled(false);
		GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
		gbc_txtDescricao.gridwidth = 11;
		gbc_txtDescricao.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescricao.gridx = 1;
		gbc_txtDescricao.gridy = 0;
		panel.add(txtDescricao, gbc_txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Categoria:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		cmbCategoria = new JComboBox();

		cmbCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					cmbMarca.requestFocus();
				}
			}
		});

		cmbCategoria.setEnabled(false);
		GridBagConstraints gbc_cmbCategoria = new GridBagConstraints();
		gbc_cmbCategoria.gridwidth = 6;
		gbc_cmbCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategoria.gridx = 1;
		gbc_cmbCategoria.gridy = 1;
		panel.add(cmbCategoria, gbc_cmbCategoria);

		JLabel lblNewLabel_3 = new JLabel("Marca:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 7;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		cmbMarca = new JComboBox();

		cmbMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					cmbCor.requestFocus();
				}
			}
		});

		cmbMarca.setEnabled(false);
		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.gridwidth = 4;
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 0);
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.gridx = 8;
		gbc_cmbMarca.gridy = 1;
		panel.add(cmbMarca, gbc_cmbMarca);

		JLabel lblTamanho = new JLabel("Qtde.:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanho.gridx = 0;
		gbc_lblTamanho.gridy = 2;
		panel.add(lblTamanho, gbc_lblTamanho);
		
				txtQtde = new JNumericField();
				txtQtde.setMaxLength(3);
				txtQtde.setFormat(JNumericField.NUMERIC);
				txtQtde.setEnabled(false);
				GridBagConstraints gbc_txtQtde = new GridBagConstraints();
				gbc_txtQtde.gridwidth = 4;
				gbc_txtQtde.insets = new Insets(0, 0, 5, 5);
				gbc_txtQtde.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtQtde.gridx = 1;
				gbc_txtQtde.gridy = 2;
				panel.add(txtQtde, gbc_txtQtde);
				txtQtde.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cor:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 5;
		gbc_lblNewLabel_4.gridy = 2;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		cmbCor = new JComboBox();

		cmbCor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					cmbTamanho.requestFocus();
				}
			}
		});

		cmbCor.setEnabled(false);
		GridBagConstraints gbc_cmbCor = new GridBagConstraints();
		gbc_cmbCor.gridwidth = 3;
		gbc_cmbCor.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCor.gridx = 6;
		gbc_cmbCor.gridy = 2;
		panel.add(cmbCor, gbc_cmbCor);

		JLabel lblNewLabel_5 = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 9;
		gbc_lblNewLabel_5.gridy = 2;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		cmbTamanho = new JComboBox();

		cmbTamanho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtVenda.requestFocus();
					txtVenda.selectAll();
				}
			}
		});

		cmbTamanho.setEnabled(false);
		GridBagConstraints gbc_cmbTamanho = new GridBagConstraints();
		gbc_cmbTamanho.gridwidth = 2;
		gbc_cmbTamanho.insets = new Insets(0, 0, 5, 0);
		gbc_cmbTamanho.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTamanho.gridx = 10;
		gbc_cmbTamanho.gridy = 2;
		panel.add(cmbTamanho, gbc_cmbTamanho);

		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblObservao = new GridBagConstraints();
		gbc_lblObservao.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservao.gridx = 0;
		gbc_lblObservao.gridy = 3;
		panel.add(lblObservao, gbc_lblObservao);
		getContentPane().add(panel_1);

		btnImportarFotos = new JButton("IMPORTAR FOTOS");
		springLayout.putConstraint(SpringLayout.WEST, btnImportarFotos, 0, SpringLayout.WEST, panel);
		btnImportarFotos.setEnabled(false);
		btnImportarFotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparProdutoData();
				boolean hasImg = false;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				File fotos_produtos = new File("fotos_produtos/");
				File[] listOfPics = fotos_produtos.listFiles();

				for (File file : listOfPics) {
					String ext_file = "";
					int i = file.getName().lastIndexOf('.');
					if (file.isFile() && i >= 0 && consigId > 0) {

						ext_file = file.getName().substring(i + 1);
						List<String> exts_prm = Arrays.asList("jpg", "jpeg",
								"png", "gif");

						if (exts_prm.contains(ext_file)) {
							hasImg = true;

							itemNum += 1;

							InputStream fileStream = null;
							LobHelper lobHelper;
							Blob imgBlob = null;

							Session session = HibernateUtil.getSessionFactory()
									.openSession();
							try {
								fileStream = new FileInputStream(file);
								lobHelper = session.getLobHelper();
								imgBlob = lobHelper.createBlob(fileStream,
										file.length());

							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							HashMap<String, Object> data = pegarValoresProduto();
							data.put("id", String.valueOf(itemNum));
							
							data.put("image", imgBlob);

							entradaId = em.criarEntradaComPic(data);
							Entrada ee = em.getEntity();
							entradas.add(entradaId);

							try {
								imgBlob.free();
								lobHelper = null;
								fileStream.close();
								file.delete();
							} catch (SQLException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							adicionarProdutoTabela(String.valueOf(ee.getId()),
									ee.getDescricao(), ee.getConsignatario()
											.getNome(), ee.getCategoria()
											.getName(),
									ee.getMarca().getName(), ee.getSituacao()
											.getName(), String.valueOf(ee
											.getVenda()), String.valueOf(ee
											.getQuantidate()), sdf.format(ee
											.getDataVencimento()));

							data.clear();
						}

					}

				}

				if (!hasImg) {
					JOptionPane
							.showMessageDialog(null,
									"Não há nenhuma imagem na pasta 'fotos_produto' para ser inserida!");
				} else {
					pupularIterator();

					selecionarPrimeira();

					habilitarEdicaoProduto();

				}

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}
		});
		getContentPane().add(btnImportarFotos);

		btnLast = new JButton(new ImageIcon("images/last-view-icon.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnImportarFotos, 0,
				SpringLayout.NORTH, btnLast);
		springLayout.putConstraint(SpringLayout.EAST, btnLast, 0,
				SpringLayout.EAST, btnBuscarConsig);
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarUltima();
			}
		});
		btnLast.setEnabled(false);
		getContentPane().add(btnLast);

		btnNext = new JButton(new ImageIcon("images/next-view-icon.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 0,
				SpringLayout.NORTH, btnLast);
		springLayout.putConstraint(SpringLayout.EAST, btnNext, -6,
				SpringLayout.WEST, btnLast);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entradasListIter.hasNext()) {
					selecionarProxima();
				}
			}
		});
		btnNext.setEnabled(false);
		getContentPane().add(btnNext);

		lblEntradasList = new JLabel("0/0");
		springLayout.putConstraint(SpringLayout.NORTH, lblEntradasList, 4,
				SpringLayout.NORTH, btnLast);
		springLayout.putConstraint(SpringLayout.WEST, lblEntradasList, 746,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblEntradasList, -6,
				SpringLayout.WEST, btnNext);
		lblEntradasList.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradasList.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblEntradasList);

		btnPrevious = new JButton(
				new ImageIcon("images/previous-view-icon.png"));
		springLayout.putConstraint(SpringLayout.EAST, btnPrevious, -6, SpringLayout.WEST, lblEntradasList);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entradasListIter.hasPrevious()) {
					selecionarAnterior();
				}
			}
		});
		btnPrevious.setEnabled(false);
		getContentPane().add(btnPrevious);

		btnFirst = new JButton(new ImageIcon("images/first-view-icon.png"));
		springLayout.putConstraint(SpringLayout.EAST, btnFirst, -6, SpringLayout.WEST, btnPrevious);
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarPrimeira();
			}
		});
		btnFirst.setEnabled(false);
		getContentPane().add(btnFirst);

		btnExcluirProd = new JButton("EXCLUIR PRODUTO");
		springLayout.putConstraint(SpringLayout.SOUTH, btnFirst, -6, SpringLayout.NORTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.SOUTH, btnPrevious, -6, SpringLayout.NORTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLast, -6, SpringLayout.NORTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProd, 0, SpringLayout.WEST, btnFirst);
		springLayout.putConstraint(SpringLayout.SOUTH, btnExcluirProd, -390,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProd, -10, SpringLayout.EAST, getContentPane());
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				int opcao;
				opcao = JOptionPane
						.showConfirmDialog(null,
								"Deseja Realmente excluir o produto "
										+ entradaId + "?", "Atencão",
								JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						em.deleteById(entradaId);
						entradas.remove(entradaId);
						removerProdutoTabela();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					pupularIterator();

					if (entradas.size() > 0) {
						selecionarPrimeira();

					} else {
						entradaId = 0;
						entradaSelecionada = 0;
						picProduto.setIcon(null);
					}

					habilitarEdicaoProduto();

					lblEntradasList.setText(entradaSelecionada + "/"
							+ entradasTotal);
				}
			}
		});
		btnExcluirProd.setEnabled(false);
		getContentPane().add(btnExcluirProd);

		btnTrocarFoto = new JButton("TROCAR FOTO");
		springLayout.putConstraint(SpringLayout.WEST, btnTrocarFoto, 10,
				SpringLayout.WEST, getContentPane());
		btnTrocarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entradaId > 0) {
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

								InputStream fileStream = new FileInputStream(file);
								LobHelper lobHelper = session.getLobHelper();
								Blob imgBlob = lobHelper.createBlob(fileStream,
										fileSize);

								em.changePicture(entradaId, imgBlob);

								try {
									Entrada ei = em.findOneWhere("id", String.valueOf(entradaId));
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
		btnTrocarFoto.setEnabled(false);
		getContentPane().add(btnTrocarFoto);

		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_2, -6, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, btnTrocarFoto, 6, SpringLayout.SOUTH, panel_2);
		springLayout.putConstraint(SpringLayout.EAST, btnTrocarFoto, 0,
				SpringLayout.EAST, panel_2);
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 6,
				SpringLayout.SOUTH, txtCodprod);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -228,
				SpringLayout.SOUTH, getContentPane());
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 6,
				SpringLayout.EAST, panel_3);
		springLayout.putConstraint(SpringLayout.EAST, panel_3, -499,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 342,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, -10,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 10,
				SpringLayout.WEST, getContentPane());
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0,
				1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblMargem = new JLabel("Comis. %:");
		GridBagConstraints gbc_lblMargem = new GridBagConstraints();
		gbc_lblMargem.anchor = GridBagConstraints.EAST;
		gbc_lblMargem.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargem.gridx = 0;
		gbc_lblMargem.gridy = 0;
		panel_1.add(lblMargem, gbc_lblMargem);

		txtComis = new JNumericField();

		txtComis.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtComis.getText().trim().isEmpty()) {
					calcularComissao();
				} else {
					lblComis.setText("R$ 0.00");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtComis.getText().trim().isEmpty()) {
					calcularComissao();
				} else {
					lblComis.setText("R$ 0.00");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtComis.getText().trim().isEmpty()) {
					calcularComissao();
				} else {
					lblComis.setText("R$ 0.00");
				}
			}

		});
		txtComis.setMaxLength(2);

		txtComis.setEnabled(false);
		GridBagConstraints gbc_txtComis = new GridBagConstraints();
		gbc_txtComis.gridwidth = 2;
		gbc_txtComis.insets = new Insets(0, 0, 5, 5);
		gbc_txtComis.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComis.gridx = 1;
		gbc_txtComis.gridy = 0;
		panel_1.add(txtComis, gbc_txtComis);
		txtComis.setColumns(10);

		JLabel lblVendaR = new JLabel("Venda R$:");
		GridBagConstraints gbc_lblVendaR = new GridBagConstraints();
		gbc_lblVendaR.anchor = GridBagConstraints.EAST;
		gbc_lblVendaR.insets = new Insets(0, 0, 5, 5);
		gbc_lblVendaR.gridx = 3;
		gbc_lblVendaR.gridy = 0;
		panel_1.add(lblVendaR, gbc_lblVendaR);

		txtVenda = new JNumericField();

		txtVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnSalvarProduto.doClick();
				}
			}
		});

		txtVenda.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}
		});

		txtVenda.setMaxLength(6);

		txtVenda.setEnabled(false);
		GridBagConstraints gbc_txtVenda = new GridBagConstraints();
		gbc_txtVenda.insets = new Insets(0, 0, 5, 5);
		gbc_txtVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVenda.gridx = 4;
		gbc_txtVenda.gridy = 0;
		panel_1.add(txtVenda, gbc_txtVenda);
		txtVenda.setColumns(10);

		JLabel lblComisso = new JLabel("Margem. %");
		GridBagConstraints gbc_lblComisso = new GridBagConstraints();
		gbc_lblComisso.anchor = GridBagConstraints.EAST;
		gbc_lblComisso.insets = new Insets(0, 0, 5, 5);
		gbc_lblComisso.gridx = 5;
		gbc_lblComisso.gridy = 0;
		panel_1.add(lblComisso, gbc_lblComisso);

		txtMargem = new JNumericField();

		txtMargem.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaId > 0 && !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				} else {
					lblCusto.setText("R$ 0.00");
				}
			}

		});
		txtMargem.setMaxLength(2);

		txtMargem.setEnabled(false);
		GridBagConstraints gbc_txtMargem = new GridBagConstraints();
		gbc_txtMargem.gridwidth = 2;
		gbc_txtMargem.insets = new Insets(0, 0, 5, 0);
		gbc_txtMargem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMargem.gridx = 6;
		gbc_txtMargem.gridy = 0;
		panel_1.add(txtMargem, gbc_txtMargem);
		txtMargem.setColumns(10);

		JLabel lblDataIncio = new JLabel("Data In\u00EDcio:");
		GridBagConstraints gbc_lblDataIncio = new GridBagConstraints();
		gbc_lblDataIncio.anchor = GridBagConstraints.EAST;
		gbc_lblDataIncio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataIncio.gridx = 0;
		gbc_lblDataIncio.gridy = 1;
		panel_1.add(lblDataIncio, gbc_lblDataIncio);

		try {
			txtDataInicio = new JFormattedTextField(new MaskFormatter(
					"##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
		txtDataInicio.setEnabled(false);
		GridBagConstraints gbc_txtDataInicio = new GridBagConstraints();
		gbc_txtDataInicio.anchor = GridBagConstraints.NORTH;
		gbc_txtDataInicio.gridwidth = 3;
		gbc_txtDataInicio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataInicio.gridx = 1;
		gbc_txtDataInicio.gridy = 1;
		panel_1.add(txtDataInicio, gbc_txtDataInicio);
		txtDataInicio.setColumns(10);

		JLabel lblValidade = new JLabel("Validade:");
		GridBagConstraints gbc_lblValidade = new GridBagConstraints();
		gbc_lblValidade.anchor = GridBagConstraints.EAST;
		gbc_lblValidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidade.gridx = 4;
		gbc_lblValidade.gridy = 1;
		panel_1.add(lblValidade, gbc_lblValidade);

		cmbValidade = new JComboBox();
		cmbValidade.setModel(new DefaultComboBoxModel(new String[] {"3 meses", "75 dias", "2 meses", "45 dias", "1 m\u00EAs", "15 dias", "10 dias", "5 dias", "1 dia"}));
		cmbValidade.setEnabled(false);
		GridBagConstraints gbc_cmbValidade = new GridBagConstraints();
		gbc_cmbValidade.gridwidth = 3;
		gbc_cmbValidade.insets = new Insets(0, 0, 5, 0);
		gbc_cmbValidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbValidade.gridx = 5;
		gbc_cmbValidade.gridy = 1;
		panel_1.add(cmbValidade, gbc_cmbValidade);

		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 2;
		panel_1.add(lblTipo, gbc_lblTipo);

		cmbTipo = new JComboBox();

		cmbTipo.setEnabled(false);
		GridBagConstraints gbc_cmbTipo = new GridBagConstraints();
		gbc_cmbTipo.gridwidth = 3;
		gbc_cmbTipo.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipo.gridx = 1;
		gbc_cmbTipo.gridy = 2;
		panel_1.add(cmbTipo, gbc_cmbTipo);

		JLabel lblDataVencimento = new JLabel("Localiza\u00E7\u00E3o:");
		GridBagConstraints gbc_lblDataVencimento = new GridBagConstraints();
		gbc_lblDataVencimento.anchor = GridBagConstraints.EAST;
		gbc_lblDataVencimento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataVencimento.gridx = 4;
		gbc_lblDataVencimento.gridy = 2;
		panel_1.add(lblDataVencimento, gbc_lblDataVencimento);

		txtLocal = new JTextField();
		txtLocal.setEnabled(false);
		GridBagConstraints gbc_txtLocal = new GridBagConstraints();
		gbc_txtLocal.gridwidth = 3;
		gbc_txtLocal.insets = new Insets(0, 0, 5, 0);
		gbc_txtLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLocal.gridx = 5;
		gbc_txtLocal.gridy = 2;
		panel_1.add(txtLocal, gbc_txtLocal);
		txtLocal.setColumns(10);

		JLabel lblComissoR = new JLabel("Comiss\u00E3o R$:");
		lblComissoR.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblComissoR = new GridBagConstraints();
		gbc_lblComissoR.insets = new Insets(0, 0, 0, 5);
		gbc_lblComissoR.gridx = 0;
		gbc_lblComissoR.gridy = 3;
		panel_1.add(lblComissoR, gbc_lblComissoR);

		lblComis = new JLabel("R$ 0,00");
		lblComis.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblComis = new GridBagConstraints();
		gbc_lblComis.gridwidth = 3;
		gbc_lblComis.insets = new Insets(0, 0, 0, 5);
		gbc_lblComis.gridx = 1;
		gbc_lblComis.gridy = 3;
		panel_1.add(lblComis, gbc_lblComis);

		JLabel lblCustoR = new JLabel("Custo R$:");
		lblCustoR.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblCustoR = new GridBagConstraints();
		gbc_lblCustoR.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustoR.gridx = 4;
		gbc_lblCustoR.gridy = 3;
		panel_1.add(lblCustoR, gbc_lblCustoR);

		lblCusto = new JLabel("R$ 0,00");
		lblCusto.setFont(new Font("SansSerif", Font.BOLD, 13));
		GridBagConstraints gbc_lblCusto = new GridBagConstraints();
		gbc_lblCusto.gridwidth = 3;
		gbc_lblCusto.insets = new Insets(0, 0, 0, 5);
		gbc_lblCusto.gridx = 5;
		gbc_lblCusto.gridy = 3;
		panel_1.add(lblCusto, gbc_lblCusto);
		getContentPane().add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		jtableProdutos
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jtableProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				limparProdutoData();
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				jtableProdutos.setCursor(Cursor
						.getPredefinedCursor(Cursor.WAIT_CURSOR));

				long idRow = Long.parseLong((String) jtableProdutos.getValueAt(
						jtableProdutos.getSelectedRow(), 0));
				selecionarItem(idRow);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				jtableProdutos.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		jtableProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtableProdutos.getColumnModel().getColumn(0).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(2).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(3).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(6).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(7).setPreferredWidth(60);
		jtableProdutos.getColumnModel().getColumn(8).setPreferredWidth(100);

		JScrollPane scrollPaneTabela = new JScrollPane(jtableProdutos);
		panel_3.add(scrollPaneTabela, "name_7514832869496");

		btnAdicionarFoto = new JButton("ADICIONAR FOTO");
		springLayout.putConstraint(SpringLayout.EAST, btnImportarFotos, 0,
				SpringLayout.EAST, btnAdicionarFoto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAdicionarFoto, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, btnAdicionarFoto, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdicionarFoto, -297, SpringLayout.EAST, getContentPane());
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 11;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		textAreaObserv = new JTextArea();
		scrollPane.setViewportView(textAreaObserv);
		btnAdicionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				// TODO
				JFileChooser arquivo = new JFileChooser();

				int retorno = arquivo.showOpenDialog(null);
				String caminhoArquivo = "";

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (retorno == JFileChooser.APPROVE_OPTION) {
					System.out.println("Abriu");
					caminhoArquivo = arquivo.getSelectedFile()
							.getAbsolutePath();
					File file = new File(caminhoArquivo);
					long fileSize = file.length();
					System.out.println(fileSize);
					if ((fileSize / 1024) > (long) 1 * 1024) {
						String erro = "Não foi possível carregar o arquivo: o tamanho máximo permitido é de 1mb.";
						JOptionPane.showMessageDialog(null, erro,
								"Erro ao carregar o arquivo!",
								JOptionPane.ERROR_MESSAGE);
					} else {

						Session session = HibernateUtil.getSessionFactory()
								.openSession();
						try {
							limparProdutoData();
							InputStream fileStream = new FileInputStream(file);
							LobHelper lobHelper = session.getLobHelper();
							Blob imgBlob = lobHelper.createBlob(fileStream,
									fileSize);

							itemNum += 1;

							HashMap<String, Object> data = pegarValoresProduto();
							data.put("id", String.valueOf(itemNum));
							data.put("image", imgBlob);

							entradaId = em.criarEntradaComPic(data);
							Entrada ee = em.getEntity();
							entradas.add(entradaId);
							SimpleDateFormat sdf = new SimpleDateFormat(
									"dd/MM/yyyy");
							adicionarProdutoTabela(String.valueOf(ee.getId()),
									ee.getDescricao(), ee.getConsignatario()
											.getNome(), ee.getCategoria()
											.getName(),
									ee.getMarca().getName(), ee.getSituacao()
											.getName(), String.valueOf(ee
											.getVenda()), String.valueOf(ee
											.getQuantidate()), sdf.format(ee
											.getDataVencimento()));

							data.clear();

							pupularIterator();

							selecionarUltima();

							habilitarEdicaoProduto();
						} catch (FileNotFoundException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
				} else {
					System.out.println("Não Abriu");
				}
			}
		});
		btnAdicionarFoto.setEnabled(false);
		panel_2.setLayout(new CardLayout(0, 0));

		picProduto = new JLabel("Imagem do Produto");
		picProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		picProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (picProduto.getIcon() != null) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					picProduto.setCursor(Cursor
							.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Entrada eSelecionada = em.findOneWhere("id",
							String.valueOf(entradasListIter.actual()));
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
		panel_2.add(picProduto, "name_689553550956");
		getContentPane().add(btnAdicionarFoto);

		btnSalvarProduto = new JButton("SALVAR PRODUTO");
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -1, SpringLayout.NORTH, btnSalvarProduto);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvarProduto, 0,
				SpringLayout.WEST, panel_1);
		btnSalvarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consignatario ce = cm.findOneWhere("id",
						String.valueOf(consigId));
				System.out.println(entradaId);
				if (entradaId > 0) {
					String msgErro = "";
					boolean isValid = true;
					System.out.println(entradaId);
					Entrada ee = em.findOneWhere("id",
							String.valueOf(entradaId));

					if (txtDescricao.getText().trim().isEmpty()) {
						msgErro += "O campo 'título' deve ser informado!\n";
						isValid = false;
					}

					if (txtQtde.getText().trim().isEmpty()
							|| Integer.parseInt(txtQtde.getText()) <= 0) {
						msgErro += "O campo 'quantidade' deve ser informado e ser maior do que zero!\n";
						isValid = false;
					}

					if (ee.getCategoria().isVestimenta()) {
						if (cmbCategoria.getSelectedItem().toString()
								.contains("selecionar")) {
							msgErro += "O campo 'categoria' deve ser informado selecionado!\n";
							isValid = false;
						}
						
						if (cmbMarca.getSelectedItem().toString()
								.contains("selecionar")) {
							msgErro += "O campo 'marca' deve ser informado selecionado!\n";
							isValid = false;
						}

						if (cmbCor.getSelectedItem().toString()
								.contains("selecionar")) {
							msgErro += "O campo 'cor' deve ser informado selecionado!\n";
							isValid = false;
						}

						if (cmbTamanho.getSelectedItem().toString()
								.contains("selecionar")) {
							msgErro += "O campo 'tamanho' deve ser informado selecionado!\n";
							isValid = false;
						}
					} else {
						System.out.println("NÃO É VESTIMENTA????");
						System.out.println(ee.getCategoria().isVestimenta());
					}

					if (Double.parseDouble(txtComis.getText()) <= 0.0) {
						msgErro += "O campo 'comissão' deve ser informado e ser maior do que zero!\n";
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

					if (isValid) {
						cmbSituacao.setSelectedItem("disponível");

						HashMap<String, Object> data = pegarValoresProduto();

						Entrada nee = em.salvarEntrada(entradaId, data);

						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy");

						alterarProdutoTabela(String.valueOf(nee.getId()), nee
								.getDescricao(), nee.getConsignatario()
								.getNome(), nee.getCategoria().getName(), nee
								.getMarca().getName(), nee.getSituacao()
								.getName(), String.valueOf(nee.getVenda()),
								String.valueOf(nee.getQuantidate()), sdf
										.format(nee.getDataVencimento()));

						data.clear();

						long newId = nee.getId();

						if (newId != entradaId) {
							int index = entradas.indexOf(entradaId);
							entradas.set(index, newId);
							entradasListIter.setObject(index, newId);
							entradaId = newId;
						}

						txtCodprod.setText(String.valueOf(nee.getId()));

						if (entradasListIter.hasNext()) {
							selecionarProxima();
						} else {
							selecionarPrimeira();
						}
						
						JOptionPane.showMessageDialog(null, "Produto salvo");
					} else {
						JOptionPane.showMessageDialog(null, msgErro);
					}
				}
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnSalvarProduto, -10,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvarProduto, 0,
				SpringLayout.EAST, btnBuscarConsig);
		btnSalvarProduto.setEnabled(false);
		getContentPane().add(btnSalvarProduto);

		btnAtualizarCads = new JButton("ATUALIZAR CAIXAS SELETORAS DO CADASTRO");
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarCads, 0,
				SpringLayout.WEST, panel);
		btnAtualizarCads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCombos();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarCads, 6,
				SpringLayout.SOUTH, txtConsign);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizarCads, 0,
				SpringLayout.EAST, btnBuscarConsig);
		getContentPane().add(btnAtualizarCads);

		cmbSituacao = new JComboBox();
		springLayout.putConstraint(SpringLayout.SOUTH, cmbSituacao, -8, SpringLayout.NORTH, panel_2);
		cmbSituacao.setBackground(Color.WHITE);
		cmbSituacao.setFont(new Font("SansSerif", Font.BOLD, 12));
		cmbSituacao.setEnabled(false);

		springLayout.putConstraint(SpringLayout.WEST, cmbSituacao, 6,
				SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, cmbSituacao, -6,
				SpringLayout.WEST, lblConsignatrio);
		getContentPane().add(cmbSituacao);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmConsignatrio = new JMenuItem("Novo Consignat\u00E1rio");
		mntmConsignatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroConsignatario obj = new CadastroConsignatario(0);
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmConsignatrio);

		JMenuItem mntmCategoria = new JMenuItem("Nova Categoria");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCategoria obj = new CadastroCategoria();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmCategoria);

		JMenuItem mntmMarca = new JMenuItem("Nova Marca");
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroMarca obj = new CadastroMarca();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmMarca);

		JMenuItem mntmCor = new JMenuItem("Nova Cor");
		mntmCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCor obj = new CadastroCor();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmCor);

		JMenuItem mntmTamanho = new JMenuItem("Novo Tamanho");
		mntmTamanho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTamanho obj = new CadastroTamanho();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmTamanho);

		JMenuItem mntmTipo = new JMenuItem("Novo Tipo");
		mntmTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroTipo obj = new CadastroTipo();
				getDesktopPane().add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmTipo);

		JMenu mnCdigoDeBarras = new JMenu("C\u00F3digo de Barras");
		menuBar.add(mnCdigoDeBarras);

		JMenuItem mntmImprimirCdigos = new JMenuItem("Imprimir C\u00F3digos (TODO)");
		mnCdigoDeBarras.add(mntmImprimirCdigos);

		atualizarCombos();
	}

	protected void alterarProdutoTabela(String cod, String desc, String consig,
			String categ, String marca, String situacao, String venda,
			String qtde, String vencimento) {
		String[] linha = new String[] { cod, desc, consig, categ, marca,
				situacao, venda, qtde, vencimento };

		for (int i = 0; i < jtableProdutos.getRowCount(); i += 1) {
			if (entradaId == Long.parseLong((String) jtableProdutos.getValueAt(
					i, 0))) {
				System.out.println();
				model.removeRow(i);
				model.insertRow(i, linha);
			}
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

	protected void removerProdutoTabela() {
		for (int i = 0; i < jtableProdutos.getRowCount(); i += 1) {
			if (entradaId == Long.parseLong((String) jtableProdutos.getValueAt(
					i, 0))) {
				model.removeRow(i);
			}
		}
	}

	protected void pupularIterator() {
		entradasTotal = entradas.size();

		if (entradasTotal > 0) {
			entradasListIter = new MyIteratorUtil(entradas);
		}
	}

	protected void adicionarProdutoTabela(String cod, String desc,
			String consig, String categ, String marca, String situacao,
			String venda, String qtde, String vencimento) {

		String[] linha = new String[] { cod, desc, consig, categ, marca,
				situacao, venda, qtde, vencimento };

		model.addRow(linha);
	}

	protected void habilitarEdicaoProduto() {
		if (entradas.size() > 0) {
			btnAdicionarFoto.setEnabled(true);
			btnExcluirProd.setEnabled(true);
			txtDescricao.setEnabled(true);
			cmbCategoria.setEnabled(true);
			cmbMarca.setEnabled(true);
			txtQtde.setEnabled(true);
			cmbCor.setEnabled(true);
			cmbTamanho.setEnabled(true);
			textAreaObserv.setEnabled(true);
			txtComis.setEnabled(true);
			txtVenda.setEnabled(true);
			txtMargem.setEnabled(true);
			txtDataInicio.setEnabled(true);
			cmbValidade.setEnabled(true);
			cmbTipo.setEnabled(true);
			txtLocal.setEnabled(true);
			lblComis.setEnabled(true);
			lblCusto.setEnabled(true);
			btnSalvarProduto.setEnabled(true);
			btnTrocarFoto.setEnabled(true);
		} else {
			btnAdicionarFoto.setEnabled(false);
			btnExcluirProd.setEnabled(false);
			txtDescricao.setEnabled(false);
			cmbCategoria.setEnabled(false);
			cmbMarca.setEnabled(false);
			txtQtde.setEnabled(false);
			cmbCor.setEnabled(false);
			cmbTamanho.setEnabled(false);
			textAreaObserv.setEnabled(false);
			txtComis.setEnabled(false);
			txtVenda.setEnabled(false);
			txtMargem.setEnabled(false);
			txtDataInicio.setEnabled(false);
			cmbValidade.setEnabled(false);
			cmbTipo.setEnabled(false);
			txtLocal.setEnabled(false);
			lblComis.setEnabled(false);
			lblCusto.setEnabled(false);
			btnSalvarProduto.setEnabled(false);
			btnTrocarFoto.setEnabled(false);
			limparProdutoData();
		}
	}

	private void limparProdutoData() {
		cmbSituacao.setSelectedItem("avaliando");
		txtDescricao.setText("");
		cmbCategoria.setSelectedItem("genérica");
		cmbMarca.setSelectedItem("genérica");
		txtQtde.setText("1");
		cmbCor.setSelectedItem("genérica");
		cmbTamanho.setSelectedItem("genérica");
		textAreaObserv.setText("");
		txtComis.setText("");
		txtVenda.setText("");
		txtMargem.setText("");
		cmbTipo.setSelectedItem("usado");
		txtDataInicio.setText("");
		cmbValidade.setSelectedItem("3 meses");
		txtLocal.setText("");
		lblComis.setText("R$ 0,00");
		lblCusto.setText("R$ 0,00");
	}

	protected void selecionarPrimeira() {

		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf((Long) entradasListIter.first()));

		entradaId = (long) entradasListIter.actual();

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada = 1;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada();
		ifHasPrevAndNext();
	}

	private void selecionarAnterior() {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf((Long) entradasListIter.previous()));

		entradaId = (long) entradasListIter.actual();

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada -= 1;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada();
		ifHasPrevAndNext();
	}

	private void selecionarProxima() {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf((Long) entradasListIter.next()));

		entradaId = (long) entradasListIter.actual();

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada += 1;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada();
		ifHasPrevAndNext();
	}

	private void selecionarUltima() {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf((Long) entradasListIter.last()));

		entradaId = (long) entradasListIter.actual();

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada = entradasTotal;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada();
		ifHasPrevAndNext();
	}

	private void selecionarItem(long id) {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf((Long) entradasListIter.getObject(id)));

		entradaId = (long) entradasListIter.actual();

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada = entradasListIter.getIndexOf(id) + 1;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada();
		ifHasPrevAndNext();
	}

	private void popularEntrada() {
		limparProdutoData();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Entrada ee = em.findOneWhere("id", String.valueOf(entradaId));

		txtCodprod.setText(String.valueOf(ee.getId()));

		consigId = ee.getConsignatario().getId();
		txtConsign.setText(ee.getConsignatario().getNome());

		txtDescricao.setText(ee.getDescricao());
		cmbCor.setSelectedItem(ee.getCor().getName());
		cmbTamanho.setSelectedItem(ee.getTamanho().getName());
		textAreaObserv.setText(ee.getObservacao());
		txtMargem.setText(String.valueOf(ee.getMargeVenda()));
		txtComis.setText(String.valueOf(ee.getMargemComissao()));
		cmbCategoria.setSelectedItem(ee.getCategoria().getName());
		cmbMarca.setSelectedItem(ee.getMarca().getName());
		txtQtde.setText(String.valueOf(ee.getQuantidate()));
		txtVenda.setText(String.valueOf(ee.getVenda()));
		txtMargem.setText(String.valueOf(ee.getMargeVenda()));
		lblCusto.setText("R$ " + String.format("%.2f", (double) ee.getCusto()));
		lblComis.setText("R$ "
				+ String.format("%.2f", (double) ee.getComissao()));
		txtDataInicio.setText(sdf.format(ee.getDataInicio()));
		cmbValidade.setSelectedItem("3 meses");
		cmbSituacao.setSelectedItem(ee.getSituacao().getName());
		cmbTipo.setSelectedItem(ee.getTipo().getName());
		txtLocal.setText(ee.getLocalizacao());

		txtDescricao.requestFocus();
		txtDescricao.selectAll();
	}

	private void ifHasPrevAndNext() {
		if (entradasListIter.hasPrevious()) {
			btnFirst.setEnabled(true);
			btnPrevious.setEnabled(true);
		} else {
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
		}

		if (entradasListIter.hasNext()) {
			btnLast.setEnabled(true);
			btnNext.setEnabled(true);
		} else {
			btnLast.setEnabled(false);
			btnNext.setEnabled(false);
		}
	}

	private void drawPicture(BufferedImage bi) {
		if (bi != null) {
			picProduto.setText("");
			picProduto.setIcon(new ImageIcon(bi));
			lblEntradasList.setText(entradaSelecionada + "/" + entradasTotal);
		}
	}

	private HashMap<String, Object> pegarValoresProduto() {
		HashMap<String, Object> data = new HashMap();

		Consignatario ce = cm.findOneWhere("id", String.valueOf(consigId));

		data.put("consig", ce);

		if (txtDescricao.getText().trim().isEmpty()) {
			data.put("descricao", "Novo Produto " + itemNum);
		} else {
			data.put("descricao", txtDescricao.getText());
		}

		if (txtQtde.getText().trim().isEmpty()) {
			data.put("qtde", 1);
		} else {
			data.put("qtde", Integer.parseInt(txtQtde.getText().trim()));
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
		case "1 mês":
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

		return data;
	}

	public void atualizarCombos() {
		cmbCategoria.removeAllItems();
		Iterator categorias = CategoriaModel.findAll().iterator();
		while (categorias.hasNext()) {
			Categoria c = (Categoria) categorias.next();
			cmbCategoria.addItem(c.getName());
		}
		cmbCategoria.setSelectedItem("genérica");

		cmbMarca.removeAllItems();
		Iterator marcas = MarcaModel.findAll().iterator();
		while (marcas.hasNext()) {
			Marca m = (Marca) marcas.next();
			cmbMarca.addItem(m.getName());
		}
		cmbMarca.setSelectedItem("genérica");

		cmbCor.removeAllItems();
		Iterator cores = CorModel.findAll().iterator();
		while (cores.hasNext()) {
			Cor c = (Cor) cores.next();
			cmbCor.addItem(c.getName());
		}
		cmbCor.setSelectedItem("genérica");

		cmbTamanho.removeAllItems();
		Iterator tamanhos = TamanhoModel.findAll().iterator();
		while (tamanhos.hasNext()) {
			Tamanho t = (Tamanho) tamanhos.next();
			cmbTamanho.addItem(t.getName());
		}
		cmbTamanho.setSelectedItem("genérica");

		cmbTipo.removeAllItems();
		Iterator tipos = TipoModel.findAll().iterator();
		while (tipos.hasNext()) {
			Tipo t = (Tipo) tipos.next();
			cmbTipo.addItem(t.getName());
		}
		cmbTipo.setSelectedItem("usado");
		
		cmbSituacao.removeAllItems();
		Iterator situacoes = SituacaoModel.findAll().iterator();
		while (situacoes.hasNext()) {
			Situacao s = (Situacao) situacoes.next();
			cmbSituacao.addItem(s.getName());
		}
		cmbSituacao.setSelectedItem("avaliando");
	}
}
