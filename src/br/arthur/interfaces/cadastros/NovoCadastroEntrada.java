package br.arthur.interfaces.cadastros;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import br.arthur.entities.Entrada;
import br.arthur.entities.Marca;
import br.arthur.entities.Tipo;
import br.arthur.interfaces.cadastros.dialogs.ConsignatarioDialog;
import br.arthur.interfaces.cadastros.dialogs.PicZoomDialog;
import br.arthur.models.CategoriaModel;
import br.arthur.models.ConsignatarioModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.MarcaModel;
import br.arthur.models.SituacaoModel;
import br.arthur.models.TipoModel;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;
import br.arthur.utils.MyIteratorUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class NovoCadastroEntrada extends JInternalFrame {
	private JTextField txtCodprod;
	private JTextField txtSituacao;
	private JTextField txtConsign;
	private JTextField txtQtde;
	private JTextField txtCor;
	private JTextField txtTamanho;
	private JTextField txtDescricao;
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
	private JButton btnAdicionarProduto;

	private JButton btnExcluirProd;
	private JButton btnExcluirLista;
	private JComboBox cmbCategoria;
	private JComboBox cmbMarca;
	private JTextArea textAreaObserv;
	private JComboBox cmbValidade;
	private JComboBox cmbTipo;
	private JLabel lblComis;
	private JLabel lblCusto;
	private JButton btnSalvarProduto;
	private JButton btnExcluirFoto;
	private JButton btnTrocarFoto;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JLabel lblEntradasList;

	private ArrayList<Entrada> entradas = new ArrayList<Entrada>();
	private MyIteratorUtil entradasListIter;
	private int entradasTotal;
	private int entradaSelecionada;
	private int itemCount;
	private JLabel picProduto;

	private String[] colunas = new String[] { "item", "Cód. Produto",
			"Descricao", "Categoria", "Marca", "Situacao", "Venda", "Qtde" };

	private String[][] dataTable = new String[][] {};

	DefaultTableModel model = new DefaultTableModel(dataTable, colunas) {
		public Class<?> getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false,
				false, false, false, false };

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

		txtSituacao = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtSituacao, -4,
				SpringLayout.NORTH, lblCdProduto);
		springLayout.putConstraint(SpringLayout.WEST, txtSituacao, 6,
				SpringLayout.EAST, lblNewLabel);
		txtSituacao.setEditable(false);
		txtSituacao.setText("Situacao");
		getContentPane().add(txtSituacao);
		txtSituacao.setColumns(10);

		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		springLayout.putConstraint(SpringLayout.EAST, txtSituacao, -6,
				SpringLayout.WEST, lblConsignatrio);
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
						btnAdicionarProduto.setEnabled(true);
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
		springLayout.putConstraint(SpringLayout.WEST, lblConsignatrio, 0,
				SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 461,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, btnBuscarConsig);
		panel.setBorder(new TitledBorder(null,
				"Descri\u00E7\u00E3o do Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6,
				SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 308,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10,
				SpringLayout.EAST, getContentPane());
		panel_1.setBorder(new TitledBorder(null,
				"Avalia\u00E7\u00E3o do Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,
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
		txtDescricao.setEnabled(false);
		GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
		gbc_txtDescricao.gridwidth = 9;
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

		Iterator categorias = CategoriaModel.findAll().iterator();

		while (categorias.hasNext()) {
			Categoria c = (Categoria) categorias.next();
			cmbCategoria.addItem(c.getName());
		}

		cmbCategoria.setEnabled(false);
		GridBagConstraints gbc_cmbCategoria = new GridBagConstraints();
		gbc_cmbCategoria.gridwidth = 4;
		gbc_cmbCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategoria.gridx = 1;
		gbc_cmbCategoria.gridy = 1;
		panel.add(cmbCategoria, gbc_cmbCategoria);

		JLabel lblNewLabel_3 = new JLabel("Marca:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 5;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		cmbMarca = new JComboBox();

		Iterator marcas = MarcaModel.findAll().iterator();

		while (marcas.hasNext()) {
			Marca m = (Marca) marcas.next();
			cmbMarca.addItem(m.getName());
		}

		cmbMarca.setEnabled(false);
		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.gridwidth = 4;
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 0);
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.gridx = 6;
		gbc_cmbMarca.gridy = 1;
		panel.add(cmbMarca, gbc_cmbMarca);

		JLabel lblTamanho = new JLabel("Qtde.:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 5, 5);
		gbc_lblTamanho.gridx = 0;
		gbc_lblTamanho.gridy = 2;
		panel.add(lblTamanho, gbc_lblTamanho);

		txtQtde = new JTextField();
		txtQtde.setEnabled(false);
		GridBagConstraints gbc_txtQtde = new GridBagConstraints();
		gbc_txtQtde.gridwidth = 2;
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
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 2;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtCor = new JTextField();
		txtCor.setEnabled(false);
		GridBagConstraints gbc_txtCor = new GridBagConstraints();
		gbc_txtCor.gridwidth = 3;
		gbc_txtCor.insets = new Insets(0, 0, 5, 5);
		gbc_txtCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCor.gridx = 4;
		gbc_txtCor.gridy = 2;
		panel.add(txtCor, gbc_txtCor);
		txtCor.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 7;
		gbc_lblNewLabel_5.gridy = 2;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		txtTamanho = new JTextField();
		txtTamanho.setEnabled(false);
		GridBagConstraints gbc_txtTamanho = new GridBagConstraints();
		gbc_txtTamanho.anchor = GridBagConstraints.NORTH;
		gbc_txtTamanho.insets = new Insets(0, 0, 5, 0);
		gbc_txtTamanho.gridwidth = 2;
		gbc_txtTamanho.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanho.gridx = 8;
		gbc_txtTamanho.gridy = 2;
		panel.add(txtTamanho, gbc_txtTamanho);
		txtTamanho.setColumns(10);

		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblObservao = new GridBagConstraints();
		gbc_lblObservao.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservao.gridx = 0;
		gbc_lblObservao.gridy = 3;
		panel.add(lblObservao, gbc_lblObservao);

		textAreaObserv = new JTextArea();
		textAreaObserv.setEnabled(false);
		GridBagConstraints gbc_textAreaObserv = new GridBagConstraints();
		gbc_textAreaObserv.gridwidth = 9;
		gbc_textAreaObserv.gridheight = 2;
		gbc_textAreaObserv.fill = GridBagConstraints.BOTH;
		gbc_textAreaObserv.gridx = 1;
		gbc_textAreaObserv.gridy = 3;
		panel.add(textAreaObserv, gbc_textAreaObserv);
		getContentPane().add(panel_1);

		btnImportarFotos = new JButton("IMPORTAR FOTOS");
		btnImportarFotos.setEnabled(false);
		btnImportarFotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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

							Consignatario ce = cm.findOneWhere("id",
									String.valueOf(consigId));

							HashMap<String, Object> data = new HashMap();

							data.put("id", String.valueOf(itemNum));
							data.put("consig", ce);
							data.put("descricao", "Novo Produto " + itemNum);
							data.put("qtde", 1);
							data.put("venda", 0.0);
							data.put("margemv", 60.0);
							data.put("margemc", 8.0);
							data.put("custo", 0.0);
							data.put("comis", 0.0);

							Date dataInicio = new Date();
							data.put("inicio", dataInicio);

							Calendar venc = Calendar.getInstance();
							venc.setTime(dataInicio);
							venc.add(Calendar.MONTH, 3);
							data.put("venc", venc.getTime());

							data.put("categoria", CategoriaModel.findOneWhere(
									"name", "'genérica'"));
							data.put("marca", MarcaModel.findOneWhere("name",
									"'genérica'"));
							data.put("situacao", SituacaoModel.findOneWhere(
									"name", "'avaliando'"));
							data.put("tipo",
									TipoModel.findOneWhere("name", "'usado'"));

							try {
								Session session = HibernateUtil
										.getSessionFactory().openSession();
								InputStream fileStream = new FileInputStream(
										file);
								LobHelper lobHelper = session.getLobHelper();
								Blob imgBlob = lobHelper.createBlob(fileStream,
										file.length());

								data.put("image", imgBlob);
							} catch (FileNotFoundException ex) {
								ex.printStackTrace();
							}

							entradaId = em.criarEntradaComPic(data);
							Entrada ee = em.getEntity();
							entradas.add(ee);

							adicionarProdutoTabela(String.valueOf(ee.getId()),
									ee.getDescricao(), ee.getCategoria()
											.getName(),
									ee.getMarca().getName(), ee.getSituacao()
											.getName(), String.valueOf(ee
											.getVenda()), String.valueOf(ee
											.getQuantidate()));

						}

						if(file.delete()){
			    			System.out.println(file.getName() + " is deleted!");
			    		}else{
			    			System.out.println("Delete operation is failed.");
			    		}
					}

				}

				pupularIterator();

				selecionarPrimeira();

				habilitarEdicaoProduto();

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				if (!hasImg) {
					JOptionPane
							.showMessageDialog(null,
									"Não há nenhuma imagem na pasta 'fotos_produto' para ser inserida!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnImportarFotos, 6,
				SpringLayout.SOUTH, txtConsign);
		springLayout.putConstraint(SpringLayout.WEST, btnImportarFotos, 461,
				SpringLayout.WEST, getContentPane());
		getContentPane().add(btnImportarFotos);

		btnLast = new JButton(new ImageIcon("images/last-view-icon.png"));
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarUltima();
			}
		});
		btnLast.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnLast, 6,
				SpringLayout.SOUTH, btnBuscarConsig);
		springLayout.putConstraint(SpringLayout.EAST, btnLast, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnLast);

		btnNext = new JButton(new ImageIcon("images/next-view-icon.png"));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entradasListIter.hasNext()) {
					selecionarProxima();
				}
			}
		});
		btnNext.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 6,
				SpringLayout.SOUTH, btnBuscarConsig);
		springLayout.putConstraint(SpringLayout.EAST, btnNext, -6,
				SpringLayout.WEST, btnLast);
		getContentPane().add(btnNext);

		lblEntradasList = new JLabel("0/0");
		springLayout.putConstraint(SpringLayout.NORTH, lblEntradasList, 13,
				SpringLayout.SOUTH, txtConsign);
		lblEntradasList.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, lblEntradasList, -65,
				SpringLayout.WEST, btnNext);
		springLayout.putConstraint(SpringLayout.EAST, lblEntradasList, -6,
				SpringLayout.WEST, btnNext);
		lblEntradasList.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblEntradasList);

		btnPrevious = new JButton(
				new ImageIcon("images/previous-view-icon.png"));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entradasListIter.hasPrevious()) {
					selecionarAnterior();
				}
			}
		});
		btnPrevious.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnPrevious, 6,
				SpringLayout.SOUTH, txtConsign);
		springLayout.putConstraint(SpringLayout.EAST, btnPrevious, -6,
				SpringLayout.WEST, lblEntradasList);
		getContentPane().add(btnPrevious);

		btnFirst = new JButton(new ImageIcon("images/first-view-icon.png"));
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarPrimeira();
			}
		});
		btnFirst.setEnabled(false);
		springLayout.putConstraint(SpringLayout.EAST, btnImportarFotos, -6,
				SpringLayout.WEST, btnFirst);
		springLayout.putConstraint(SpringLayout.NORTH, btnFirst, 0,
				SpringLayout.NORTH, btnLast);
		springLayout.putConstraint(SpringLayout.EAST, btnFirst, -6,
				SpringLayout.WEST, btnPrevious);
		getContentPane().add(btnFirst);

		btnExcluirProd = new JButton("EXCLUIR PRODUTO");
		btnExcluirProd.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6,
				SpringLayout.SOUTH, btnExcluirProd);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirProd, 6,
				SpringLayout.SOUTH, btnLast);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirProd, 0,
				SpringLayout.EAST, btnBuscarConsig);
		getContentPane().add(btnExcluirProd);

		btnExcluirFoto = new JButton("EXCLUIR FOTO");
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirFoto, 270,
				SpringLayout.SOUTH, txtCodprod);
		btnExcluirFoto.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirFoto, 8,
				SpringLayout.WEST, getContentPane());
		getContentPane().add(btnExcluirFoto);

		btnTrocarFoto = new JButton("TROCAR FOTO");
		btnTrocarFoto.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 6,
				SpringLayout.EAST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.WEST, btnTrocarFoto, 233,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnTrocarFoto, -452,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirFoto, -6,
				SpringLayout.WEST, btnTrocarFoto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnTrocarFoto, -171,
				SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnTrocarFoto);

		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 6,
				SpringLayout.SOUTH, txtCodprod);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 0,
				SpringLayout.WEST, lblCdProduto);
		springLayout.putConstraint(SpringLayout.EAST, panel_2, -6,
				SpringLayout.WEST, panel);
		getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -33,
				SpringLayout.NORTH, panel_3);
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_3, -452,
				SpringLayout.EAST, getContentPane());
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 6,
				SpringLayout.SOUTH, btnExcluirFoto);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 161,
				SpringLayout.SOUTH, btnExcluirFoto);
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

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

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

		txtVenda.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

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

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

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
		cmbValidade.setModel(new DefaultComboBoxModel(new String[] { "3 meses",
				"75 dias", "2 meses", "45 dias", "1 m\u00EAs", "15 dias",
				"10 dias", "5 dias", "1 dia" }));
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

		Iterator tipos = TipoModel.findAll().iterator();

		while (tipos.hasNext()) {
			Tipo t = (Tipo) tipos.next();
			cmbTipo.addItem(t.getName());
		}

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
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				jtableProdutos.setCursor(Cursor
						.getPredefinedCursor(Cursor.WAIT_CURSOR));
				int idRow = jtableProdutos.getSelectedRow();
				selecionarItem(idRow);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				jtableProdutos.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		jtableProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtableProdutos.getColumnModel().getColumn(0).setPreferredWidth(60);
		jtableProdutos.getColumnModel().getColumn(1).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(2).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(3).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(6).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(7).setPreferredWidth(60);

		JScrollPane scrollPaneTabela = new JScrollPane(jtableProdutos);
		panel_3.add(scrollPaneTabela, "name_7514832869496");

		btnExcluirLista = new JButton("EXCLUIR LISTA");
		btnExcluirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcluirLista.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirLista, 6,
				SpringLayout.EAST, panel_2);
		panel_2.setLayout(new CardLayout(0, 0));

		picProduto = new JLabel("Imagem do Produto");
		picProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		picProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				picProduto.setCursor(Cursor
						.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Entrada eSelecionada = em.findOneWhere("id", String
						.valueOf(((Entrada) entradasListIter.actual()).getId()));
				Blob blob_img = eSelecionada.getImagemBlob();
				String descricao = eSelecionada.getDescricao();
				PicZoomDialog picDiag = new PicZoomDialog(blob_img, descricao);
				picDiag.setVisible(true);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				picProduto.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		picProduto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(picProduto, "name_689553550956");
		springLayout.putConstraint(SpringLayout.EAST, btnExcluirLista, -253,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnExcluirProd, 6,
				SpringLayout.EAST, btnExcluirLista);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluirLista, 6,
				SpringLayout.SOUTH, btnImportarFotos);
		getContentPane().add(btnExcluirLista);

		btnAdicionarProduto = new JButton("ADICIONAR PRODUTO");
		btnAdicionarProduto.setEnabled(false);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -6,
				SpringLayout.NORTH, btnAdicionarProduto);
		springLayout.putConstraint(SpringLayout.WEST, btnAdicionarProduto, 6,
				SpringLayout.EAST, panel_3);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAdicionarProduto,
				-10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAdicionarProduto, 225,
				SpringLayout.EAST, panel_3);
		getContentPane().add(btnAdicionarProduto);

		btnSalvarProduto = new JButton("SALVAR PRODUTO");
		btnSalvarProduto.setEnabled(false);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvarProduto, 6,
				SpringLayout.EAST, btnAdicionarProduto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSalvarProduto, -10,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvarProduto, 0,
				SpringLayout.EAST, btnBuscarConsig);
		getContentPane().add(btnSalvarProduto);

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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnCadastro.add(mntmNewMenuItem);

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

	protected void pupularIterator() {
		entradasTotal = entradas.size();

		entradasListIter = new MyIteratorUtil(entradas);
	}

	protected void adicionarProdutoTabela(String cod, String desc,
			String categ, String marca, String situacao, String venda,
			String qtde) {
		itemCount += 1;

		String[] linha = new String[] { String.valueOf(itemCount), cod, desc,
				categ, marca, situacao, venda, qtde };

		model.addRow(linha);
	}

	protected void habilitarEdicaoProduto() {
		if (entradas.size() > 0) {
			btnExcluirLista.setEnabled(true);
			btnExcluirProd.setEnabled(true);
			txtDescricao.setEnabled(true);
			cmbCategoria.setEnabled(true);
			cmbMarca.setEnabled(true);
			txtQtde.setEnabled(true);
			txtCor.setEnabled(true);
			txtTamanho.setEnabled(true);
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
		}
	}

	protected void selecionarPrimeira() {

		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf(((Entrada) entradasListIter.first()).getId()));
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

		popularEntrada(eSelecionada.getId());
		ifHasPrevAndNext();
	}

	private void selecionarAnterior() {
		Entrada eSelecionada = em
				.findOneWhere("id", String.valueOf(((Entrada) entradasListIter
						.previous()).getId()));
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

		popularEntrada(eSelecionada.getId());
		ifHasPrevAndNext();
	}

	private void selecionarProxima() {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf(((Entrada) entradasListIter.next()).getId()));
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

		popularEntrada(eSelecionada.getId());
		ifHasPrevAndNext();
	}

	private void selecionarUltima() {
		Entrada eSelecionada = em.findOneWhere("id",
				String.valueOf(((Entrada) entradasListIter.last()).getId()));
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

		popularEntrada(eSelecionada.getId());
		ifHasPrevAndNext();
	}

	private void selecionarItem(int index) {
		Entrada eSelecionada = em
				.findOneWhere("id", String.valueOf(((Entrada) entradasListIter
						.getObject(index)).getId()));
		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			entradaSelecionada = index + 1;
			drawPicture(bi);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		popularEntrada(eSelecionada.getId());
		ifHasPrevAndNext();
	}

	private void popularEntrada(long id) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Entrada ee = em.findOneWhere("id", String.valueOf(id));

		txtCodprod.setText(String.valueOf(ee.getId()));
		txtDescricao.setText(ee.getDescricao());
		txtCor.setText(ee.getCor());
		txtTamanho.setText(ee.getTamanho());
		textAreaObserv.setText(ee.getObservacao());
		txtMargem.setText(String.valueOf(ee.getMargeVenda()));
		txtComis.setText(String.valueOf(ee.getMargemComissao()));
		cmbCategoria.setSelectedItem(ee.getCategoria().getName());
		cmbMarca.setSelectedItem(ee.getMarca().getName());
		txtQtde.setText(String.valueOf(ee.getQuantidate()));
		txtVenda.setText(String.valueOf(ee.getVenda()));
		txtMargem.setText(String.valueOf(ee.getMargeVenda()));
		lblCusto.setText("R$ " + ee.getVenda());
		lblComis.setText("R$ " + ee.getComissao());
		txtDataInicio.setText(sdf.format(ee.getDataInicio()));
		cmbValidade.setSelectedItem("3 meses");
		txtSituacao.setText(ee.getSituacao().getName());
		cmbTipo.setSelectedItem(ee.getTipo().getName());
		txtLocal.setText(ee.getLocalizacao());
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
}
