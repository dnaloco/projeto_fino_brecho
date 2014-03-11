package br.arthur.interfaces.cadastros;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.LobHelper;
import org.hibernate.Session;

import br.arthur.entities.Categoria;
import br.arthur.entities.Consignatario;
import br.arthur.entities.Entrada;
import br.arthur.entities.Estado;
import br.arthur.entities.Imagem;
import br.arthur.entities.Marca;
import br.arthur.entities.Situacao;
import br.arthur.entities.Tipo;
import br.arthur.interfaces.cadastros.dialogs.ConsignatarioDialog;
import br.arthur.interfaces.cadastros.dialogs.HeaderEntradaDialog;
import br.arthur.models.CategoriaModel;
import br.arthur.models.ConsignatarioModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.EstadoModel;
import br.arthur.models.HeaderEntradaModel;
import br.arthur.models.ImagemModel;
import br.arthur.models.MarcaModel;
import br.arthur.models.SituacaoModel;
import br.arthur.models.TipoModel;
import br.arthur.utils.HibernateUtil;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyIteratorUtil;
import br.arthur.utils.MyValidateUtil;

public class CadastroEntrada extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPanePedido;

	private JTextField txtCelular;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtSite;
	private JTextField txtRg;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtNum;
	private JTextField txtComplem;
	private JTextField txtBairro;
	private JTextField txtLograd;
	private JTextField txtCidade;
	private JComboBox cmbUf;
	private JTextField txtQtde;
	private JTextField txtCor;
	private JTextField txtTamanho;
	private JTextField txtDescricao;
	private JTextField txtInicio;
	private JTextField txtCep;

	private JButton btnBuscarConsig;
	private JLabel lblNovoConsig;
	private JButton btnCancelarConsig;
	private JButton btnExcluirConsig;
	private JLabel lblProdutoSelecionado;
	private JLabel lblNovaEntrada;
	private JButton btnCancelarProduto;
	private JButton btnExcluirProd;

	private JComboBox cmbCategoria;
	private JComboBox cmbMarca;
	private JComboBox cmbSituacao;

	private JPanel panelPicture;
	private JLabel picture;
	private JLabel lblCountImg;
	private int imgCount = 0;
	private int imgTotalCount;
	private JButton btnInserirImg;
	private JButton btnTrocarImg;
	private JButton btnExcluirImg;
	private JButton btnFirstImg;
	private JButton btnPreviousImg;
	private JButton btnNextImg;
	private JButton btnLastImg;

	private double custo;
	private double comissao;

	private MyIteratorUtil imgListIter;

	String[] colunasProduto = new String[] { "Código (ID)", "Produto",
			"Categoria", "Marca", "Tamanho", "Cor", "Qtde", "Situação",
			"Entrada" };
	String[] colunasAvaliar = new String[] { "Código (ID)", "Produto", "Venda",
			"Margem", "Comissão", "Situação", "Data Inicio", "Data Vencimento",
			"Tipo", "Valor Custo", "Valor Comissão" };
	String[][] dataProdutoTable = new String[][] {};

	DefaultTableModel modelProduto = new DefaultTableModel(dataProdutoTable,
			colunasProduto) {
		public Class<?> getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false,
				false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

	};
	DefaultTableModel modelAvaliar = new DefaultTableModel(dataProdutoTable,
			colunasAvaliar) {
		public Class<?> getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false,
				false, false, false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}

	};
	JTable jtableProdutos = new JTable(modelProduto);
	JTable jtableAvaliar = new JTable(modelAvaliar);

	private int consigId = 0;
	private int pedidoId = 0;
	private int entradaProdutoId = 0;
	private int entradaAvaliarId = 0;
	private int imagemId = 0;
	private int linhaSelecionadaProduto;
	private int linhaSelecionadaAvaliar;

	private int countListaImg;

	private ConsignatarioModel cm = new ConsignatarioModel();
	private HeaderEntradaModel pm = new HeaderEntradaModel();
	private EntradaModel em = new EntradaModel();
	private ImagemModel im = new ImagemModel();

	private JLabel lblNumPedido;

	private JTextArea tAreaObserv;
	private JPanel panel_7;
	private JButton btnAtualizarAval;
	private JComboBox cmbValidade;
	private JComboBox cmbTipo;
	private JLabel lblCusto;
	private JLabel lblComis;
	private JNumericField txtVenda;
	private JNumericField txtMargem;
	private JNumericField txtComissao;

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
		setFrameIcon(new ImageIcon("images/entradas-small.png"));
		setIconifiable(true);
		setClosable(true);
		setTitle("Cadastro de Entrada");
		setBounds(100, 100, 510, 483);
		getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPanePedido = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPanePedido);

		JPanel consigPanel = new JPanel();
		consigPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		tabbedPanePedido.addTab("Consignatário", null, consigPanel, null);
		SpringLayout sl_consigPanel = new SpringLayout();
		consigPanel.setLayout(sl_consigPanel);

		lblNovoConsig = new JLabel("Novo Consignat\u00E1rio");
		sl_consigPanel.putConstraint(SpringLayout.NORTH, lblNovoConsig, 13,
				SpringLayout.NORTH, consigPanel);
		lblNovoConsig.setFont(new Font("Tahoma", Font.BOLD, 14));
		consigPanel.add(lblNovoConsig);

		btnBuscarConsig = new JButton(new ImageIcon("images/Zoom-icon.png"));
		sl_consigPanel.putConstraint(SpringLayout.WEST, btnBuscarConsig, 115,
				SpringLayout.EAST, lblNovoConsig);
		sl_consigPanel.putConstraint(SpringLayout.EAST, btnBuscarConsig, -2,
				SpringLayout.EAST, consigPanel);
		btnBuscarConsig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cm.findAll().size() > 0) {
					ConsignatarioDialog cDiag = new ConsignatarioDialog();
					cDiag.setVisible(true);

					consigId = cDiag.getTheId();

					if (consigId > 0) {
						populateConsignatario();
						limparTabelas();
						limparProduto();
						limparAvaliacao();
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Não existe ainda nenhum consignatário registrado no sistema!");
				}

			}
		});
		sl_consigPanel.putConstraint(SpringLayout.NORTH, btnBuscarConsig, 8,
				SpringLayout.NORTH, consigPanel);
		btnBuscarConsig.setText("Buscar Consignat\u00E1rio");
		btnBuscarConsig.setHorizontalTextPosition(SwingConstants.LEFT);
		consigPanel.add(btnBuscarConsig);

		JPanel panel = new JPanel();
		sl_consigPanel.putConstraint(SpringLayout.NORTH, panel, 4,
				SpringLayout.SOUTH, btnBuscarConsig);
		sl_consigPanel.putConstraint(SpringLayout.WEST, lblNovoConsig, 0,
				SpringLayout.WEST, panel);
		sl_consigPanel.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.EAST, panel, -2,
				SpringLayout.EAST, consigPanel);
		panel.setBorder(new TitledBorder(null, "Contato", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		consigPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 3;
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 1;
		panel.add(lblTelefone, gbc_lblTelefone);

		MaskFormatter frmtTefelone = formatString("####-####");

		txtTelefone = new javax.swing.JFormattedTextField(frmtTefelone);
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.anchor = GridBagConstraints.NORTH;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 1;
		panel.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblCelular = new JLabel("Celular");
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.EAST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 5);
		gbc_lblCelular.gridx = 2;
		gbc_lblCelular.gridy = 1;
		panel.add(lblCelular, gbc_lblCelular);

		txtCelular = new javax.swing.JFormattedTextField(frmtTefelone);
		GridBagConstraints gbc_txtCelular = new GridBagConstraints();
		gbc_txtCelular.insets = new Insets(0, 0, 5, 0);
		gbc_txtCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCelular.gridx = 3;
		gbc_txtCelular.gridy = 1;
		panel.add(txtCelular, gbc_txtCelular);
		txtCelular.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 2;
		panel.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		JLabel lblSite = new JLabel("Site:");
		GridBagConstraints gbc_lblSite = new GridBagConstraints();
		gbc_lblSite.anchor = GridBagConstraints.EAST;
		gbc_lblSite.insets = new Insets(0, 0, 5, 5);
		gbc_lblSite.gridx = 2;
		gbc_lblSite.gridy = 2;
		panel.add(lblSite, gbc_lblSite);

		txtSite = new JTextField();
		GridBagConstraints gbc_txtSite = new GridBagConstraints();
		gbc_txtSite.insets = new Insets(0, 0, 5, 0);
		gbc_txtSite.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSite.gridx = 3;
		gbc_txtSite.gridy = 2;
		panel.add(txtSite, gbc_txtSite);
		txtSite.setColumns(10);

		JLabel lblCpf = new JLabel("CPF.:");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 0, 5);
		gbc_lblCpf.gridx = 0;
		gbc_lblCpf.gridy = 3;
		panel.add(lblCpf, gbc_lblCpf);

		MaskFormatter frmtCpf = formatString("###.###.###-##");

		txtCpf = new javax.swing.JFormattedTextField(frmtCpf);
		GridBagConstraints gbc_txtCpf = new GridBagConstraints();
		gbc_txtCpf.insets = new Insets(0, 0, 0, 5);
		gbc_txtCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCpf.gridx = 1;
		gbc_txtCpf.gridy = 3;
		panel.add(txtCpf, gbc_txtCpf);
		txtCpf.setColumns(10);

		JLabel lblRg = new JLabel("R.G.:");
		GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.anchor = GridBagConstraints.EAST;
		gbc_lblRg.insets = new Insets(0, 0, 0, 5);
		gbc_lblRg.gridx = 2;
		gbc_lblRg.gridy = 3;
		panel.add(lblRg, gbc_lblRg);

		txtRg = new JTextField();
		GridBagConstraints gbc_txtRg = new GridBagConstraints();
		gbc_txtRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRg.gridx = 3;
		gbc_txtRg.gridy = 3;
		panel.add(txtRg, gbc_txtRg);
		txtRg.setColumns(10);

		JPanel panel_1 = new JPanel();
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, panel, -6,
				SpringLayout.NORTH, panel_1);
		sl_consigPanel.putConstraint(SpringLayout.NORTH, panel_1, 209,
				SpringLayout.NORTH, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.WEST, panel_1, 10,
				SpringLayout.WEST, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.EAST, panel_1, -2,
				SpringLayout.EAST, consigPanel);
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		consigPanel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblLogradouro = new JLabel("Logradouro:");
		GridBagConstraints gbc_lblLogradouro = new GridBagConstraints();
		gbc_lblLogradouro.anchor = GridBagConstraints.EAST;
		gbc_lblLogradouro.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogradouro.gridx = 0;
		gbc_lblLogradouro.gridy = 0;
		panel_1.add(lblLogradouro, gbc_lblLogradouro);

		txtLograd = new JTextField();
		GridBagConstraints gbc_txtLograd = new GridBagConstraints();
		gbc_txtLograd.anchor = GridBagConstraints.NORTH;
		gbc_txtLograd.gridwidth = 4;
		gbc_txtLograd.insets = new Insets(0, 0, 5, 0);
		gbc_txtLograd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLograd.gridx = 1;
		gbc_txtLograd.gridy = 0;
		panel_1.add(txtLograd, gbc_txtLograd);
		txtLograd.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNmero = new GridBagConstraints();
		gbc_lblNmero.anchor = GridBagConstraints.EAST;
		gbc_lblNmero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmero.gridx = 0;
		gbc_lblNmero.gridy = 1;
		panel_1.add(lblNmero, gbc_lblNmero);

		txtNum = new JTextField();
		GridBagConstraints gbc_txtNum = new GridBagConstraints();
		gbc_txtNum.insets = new Insets(0, 0, 5, 5);
		gbc_txtNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNum.gridx = 1;
		gbc_txtNum.gridy = 1;
		panel_1.add(txtNum, gbc_txtNum);
		txtNum.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento:");
		GridBagConstraints gbc_lblComplemento = new GridBagConstraints();
		gbc_lblComplemento.anchor = GridBagConstraints.EAST;
		gbc_lblComplemento.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplemento.gridx = 2;
		gbc_lblComplemento.gridy = 1;
		panel_1.add(lblComplemento, gbc_lblComplemento);

		txtComplem = new JTextField();
		GridBagConstraints gbc_txtComplem = new GridBagConstraints();
		gbc_txtComplem.gridwidth = 2;
		gbc_txtComplem.insets = new Insets(0, 0, 5, 0);
		gbc_txtComplem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComplem.gridx = 3;
		gbc_txtComplem.gridy = 1;
		panel_1.add(txtComplem, gbc_txtComplem);
		txtComplem.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 2;
		panel_1.add(lblBairro, gbc_lblBairro);

		txtBairro = new JTextField();
		GridBagConstraints gbc_txtBairro = new GridBagConstraints();
		gbc_txtBairro.gridwidth = 2;
		gbc_txtBairro.insets = new Insets(0, 0, 5, 5);
		gbc_txtBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBairro.gridx = 1;
		gbc_txtBairro.gridy = 2;
		panel_1.add(txtBairro, gbc_txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");
		GridBagConstraints gbc_lblCep = new GridBagConstraints();
		gbc_lblCep.insets = new Insets(0, 0, 5, 5);
		gbc_lblCep.anchor = GridBagConstraints.EAST;
		gbc_lblCep.gridx = 3;
		gbc_lblCep.gridy = 2;
		panel_1.add(lblCep, gbc_lblCep);

		MaskFormatter frmtCep = formatString("#####-###");

		txtCep = new javax.swing.JFormattedTextField(frmtCep);
		GridBagConstraints gbc_txtCep = new GridBagConstraints();
		gbc_txtCep.insets = new Insets(0, 0, 5, 0);
		gbc_txtCep.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCep.gridx = 4;
		gbc_txtCep.gridy = 2;
		panel_1.add(txtCep, gbc_txtCep);
		txtCep.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 3;
		panel_1.add(lblCidade, gbc_lblCidade);

		txtCidade = new JTextField();
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.gridwidth = 2;
		gbc_txtCidade.insets = new Insets(0, 0, 0, 5);
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.gridx = 1;
		gbc_txtCidade.gridy = 3;
		panel_1.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);

		JLabel lblUf = new JLabel("U.F.:");
		GridBagConstraints gbc_lblUf = new GridBagConstraints();
		gbc_lblUf.anchor = GridBagConstraints.EAST;
		gbc_lblUf.insets = new Insets(0, 0, 0, 5);
		gbc_lblUf.gridx = 3;
		gbc_lblUf.gridy = 3;
		panel_1.add(lblUf, gbc_lblUf);

		cmbUf = new JComboBox();

		Iterator estados = EstadoModel.findAll().iterator();

		while (estados.hasNext()) {
			Estado e = (Estado) estados.next();
			cmbUf.addItem(e.getName());
		}

		cmbUf.setSelectedItem("SP");

		GridBagConstraints gbc_cmbUf = new GridBagConstraints();
		gbc_cmbUf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbUf.gridx = 4;
		gbc_cmbUf.gridy = 3;
		panel_1.add(cmbUf, gbc_cmbUf);

		JButton btnSalvarConsig = new JButton("Salvar", new ImageIcon(
				"images/Save-icon.png"));
		sl_consigPanel.putConstraint(SpringLayout.EAST, btnSalvarConsig, -2,
				SpringLayout.EAST, consigPanel);
		btnSalvarConsig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> data = new HashMap();

				String msgErro = "";

				boolean isValid = true;

				if (txtNome.getText().trim().isEmpty()) {
					msgErro += "O campo 'nome' deve ser informado!\n";
					isValid = false;
				}

				if (txtTelefone.getText().trim().length() < 9
						&& txtCelular.getText().trim().length() < 9) {
					msgErro += "Um número de 'celular' ou 'telefone' deve ser informado\n";
					isValid = false;
				}

				if (!txtEmail.getText().trim().isEmpty()) {
					if (!MyValidateUtil.validate(txtEmail.getText())) {
						isValid = false;
						msgErro += "O 'e-mail' informado não é válido!";
					}
				}

				if (isValid) {
					data.put("nome", txtNome.getText().trim());
					data.put("telefone", txtTelefone.getText().trim());
					data.put("celular", txtCelular.getText().trim());
					data.put("email", txtEmail.getText().trim());
					data.put("site", txtSite.getText().trim());
					data.put("cpf", txtCpf.getText().trim());
					data.put("rg", txtRg.getText().trim());
					data.put("logra", txtLograd.getText().trim());
					data.put("num", txtNum.getText().trim());
					data.put("complem", txtComplem.getText().trim());
					data.put("bairro", txtBairro.getText().trim());
					data.put("cep", txtCep.getText().trim());
					data.put("cidade", txtCidade.getText().trim());
					Estado ee = EstadoModel.findOneWhere("name",
							"'" + cmbUf.getSelectedItem() + "'");
					data.put("estado", ee);
					String msgSuccess = "";
					if (consigId > 0) {
						cm.saveConsignatario(consigId, data);
						msgSuccess = "Consignatário salvo com sucesso!";
					} else {
						consigId = cm.createConsignatario(data);
						msgSuccess = "Consignatário inserido com sucesso!";
						btnCancelarConsig.setEnabled(true);
						btnExcluirConsig.setEnabled(true);
						populateConsignatario();
					}
					if (consigId > 0) {
						JOptionPane.showMessageDialog(null, msgSuccess);
					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possível inserir o consignatário.");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
				limparTabelas();
				limparProduto();
				limparAvaliacao();
			}
		});
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, panel_1, -12,
				SpringLayout.NORTH, btnSalvarConsig);
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, btnSalvarConsig, -10,
				SpringLayout.SOUTH, consigPanel);
		consigPanel.add(btnSalvarConsig);

		btnCancelarConsig = new JButton("Cancelar", new ImageIcon(
				"images/cancel-icon.png"));
		btnCancelarConsig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparConsignatario();
			}
		});
		btnCancelarConsig.setEnabled(false);
		sl_consigPanel.putConstraint(SpringLayout.WEST, btnCancelarConsig, 10,
				SpringLayout.WEST, consigPanel);
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, btnCancelarConsig, 0,
				SpringLayout.SOUTH, btnSalvarConsig);
		consigPanel.add(btnCancelarConsig);

		btnExcluirConsig = new JButton("Excluir", new ImageIcon(
				"images/delete-icon.png"));
		btnExcluirConsig.setEnabled(false);
		sl_consigPanel.putConstraint(SpringLayout.WEST, btnExcluirConsig, 6,
				SpringLayout.EAST, btnCancelarConsig);
		sl_consigPanel.putConstraint(SpringLayout.SOUTH, btnExcluirConsig, 0,
				SpringLayout.SOUTH, btnSalvarConsig);
		consigPanel.add(btnExcluirConsig);

		JPanel pecarPanel = new JPanel();
		tabbedPanePedido.addTab("Produto", null, pecarPanel, null);
		SpringLayout sl_pecarPanel = new SpringLayout();
		pecarPanel.setLayout(sl_pecarPanel);

		JPanel panel_2 = new JPanel();
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, panel_2, 35,
				SpringLayout.NORTH, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, panel_2, 10,
				SpringLayout.WEST, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, panel_2, 488,
				SpringLayout.WEST, pecarPanel);
		panel_2.setBorder(new TitledBorder(null, "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pecarPanel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblTtulo = new JLabel("Descri\u00E7\u00E3o:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		panel_2.add(lblTtulo, gbc_lblTtulo);

		txtDescricao = new JTextField();
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.gridwidth = 7;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 0;
		panel_2.add(txtDescricao, gbc_txtTitulo);
		txtDescricao.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 1;
		panel_2.add(lblCategoria, gbc_lblCategoria);

		cmbCategoria = new JComboBox();

		Iterator categorias = CategoriaModel.findAll().iterator();

		while (categorias.hasNext()) {
			Categoria c = (Categoria) categorias.next();
			cmbCategoria.addItem(c.getName());
		}

		GridBagConstraints gbc_cmbCategoria = new GridBagConstraints();
		gbc_cmbCategoria.gridwidth = 4;
		gbc_cmbCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategoria.gridx = 1;
		gbc_cmbCategoria.gridy = 1;
		panel_2.add(cmbCategoria, gbc_cmbCategoria);

		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 5;
		gbc_lblMarca.gridy = 1;
		panel_2.add(lblMarca, gbc_lblMarca);

		cmbMarca = new JComboBox();

		Iterator marcas = MarcaModel.findAll().iterator();

		while (marcas.hasNext()) {
			Marca m = (Marca) marcas.next();
			cmbMarca.addItem(m.getName());
		}

		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.gridwidth = 2;
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 0);
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.gridx = 6;
		gbc_cmbMarca.gridy = 1;
		panel_2.add(cmbMarca, gbc_cmbMarca);

		JLabel lblCor = new JLabel("Tamanho:");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.EAST;
		gbc_lblCor.insets = new Insets(0, 0, 0, 5);
		gbc_lblCor.gridx = 0;
		gbc_lblCor.gridy = 2;
		panel_2.add(lblCor, gbc_lblCor);

		txtTamanho = new JTextField();
		GridBagConstraints gbc_txtTamanho = new GridBagConstraints();
		gbc_txtTamanho.gridwidth = 2;
		gbc_txtTamanho.insets = new Insets(0, 0, 0, 5);
		gbc_txtTamanho.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTamanho.gridx = 1;
		gbc_txtTamanho.gridy = 2;
		panel_2.add(txtTamanho, gbc_txtTamanho);
		txtTamanho.setColumns(10);

		JLabel lblTamanho = new JLabel("Cor:");
		GridBagConstraints gbc_lblTamanho = new GridBagConstraints();
		gbc_lblTamanho.anchor = GridBagConstraints.EAST;
		gbc_lblTamanho.insets = new Insets(0, 0, 0, 5);
		gbc_lblTamanho.gridx = 4;
		gbc_lblTamanho.gridy = 2;
		panel_2.add(lblTamanho, gbc_lblTamanho);

		txtCor = new JTextField();
		GridBagConstraints gbc_txtCor = new GridBagConstraints();
		gbc_txtCor.insets = new Insets(0, 0, 0, 5);
		gbc_txtCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCor.gridx = 5;
		gbc_txtCor.gridy = 2;
		panel_2.add(txtCor, gbc_txtCor);
		txtCor.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantidade.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidade.gridx = 6;
		gbc_lblQuantidade.gridy = 2;
		panel_2.add(lblQuantidade, gbc_lblQuantidade);

		txtQtde = new JTextField();
		txtQtde.setHorizontalAlignment(SwingConstants.RIGHT);
		txtQtde.setText("1");
		GridBagConstraints gbc_txtQtde = new GridBagConstraints();
		gbc_txtQtde.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtde.gridx = 7;
		gbc_txtQtde.gridy = 2;
		panel_2.add(txtQtde, gbc_txtQtde);
		txtQtde.setColumns(10);

		JButton btnSalvarProduto = new JButton("Salvar", new ImageIcon(
				"images/Save-icon.png"));
		sl_pecarPanel.putConstraint(SpringLayout.EAST, btnSalvarProduto, 0,
				SpringLayout.EAST, panel_2);
		btnSalvarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Consignatario ce = cm.findOneWhere("id",
						String.valueOf(consigId));

				if (pedidoId == 0) {
					System.out.println("aasdqwe");
					pedidoId = pm.createPedido(ce);
					JOptionPane
							.showMessageDialog(null,
									"Novo número de entrada gerado! ["
											+ pedidoId + "]");
					lblNovaEntrada.setText("Cód. Entrada: " + pedidoId);
				}

				if (pedidoId > 0) {
					btnBuscarConsig.setEnabled(false);

					HashMap<String, Object> data = new HashMap();

					String msgErro = "";

					boolean isValid = true;

					if (txtDescricao.getText().trim().isEmpty()) {
						msgErro += "O campo 'título' deve ser informado!\n";
						isValid = false;
					}

					if (txtQtde.getText().trim().isEmpty()) {
						msgErro += "O campo 'quantidade' deve ser informado!\n";
						isValid = false;
					}

					if (isValid) {
						limparAvaliacao();

						data.put("header_entrada",
								pm.findOneWhere("id", String.valueOf(pedidoId)));

						data.put("descricao", txtDescricao.getText());

						Categoria cate = CategoriaModel.findOneWhere("name",
								"'" + cmbCategoria.getSelectedItem() + "'");
						data.put("categoria", cate);

						Marca me = MarcaModel.findOneWhere("name", "'"
								+ cmbMarca.getSelectedItem() + "'");
						data.put("marca", me);

						data.put("tamanho", txtTamanho.getText());
						data.put("cor", txtCor.getText());
						data.put("qtde", Integer.parseInt(txtQtde.getText()));

						String msgSuccess = "";

						if (entradaProdutoId > 0) {
							em.saveProduto(entradaProdutoId, data);
							msgSuccess = "Produto salvo com sucesso!";
							updateTProduto(txtDescricao.getText(),
									(String) cmbCategoria.getSelectedItem(),
									(String) cmbMarca.getSelectedItem(),
									txtTamanho.getText(), txtCor.getText(),
									txtQtde.getText());
							limparProduto();
						} else {
							data.put("venda", 0.0);
							data.put("margem", 40.0);
							data.put("comissao", 8.0);

							// Teste

							Date inicioDate = new Date();

							// fim
							data.put("inicio", inicioDate);

							Calendar venc = Calendar.getInstance();
							venc.setTime(inicioDate);
							venc.add(Calendar.MONTH, 3);

							Date vencDate = venc.getTime();

							data.put("vencimento", vencDate);

							data.put("situacao", SituacaoModel.findOneWhere(
									"name", "'avaliando'"));
							data.put("tipo",
									TipoModel.findOneWhere("name", "'usado'"));

							entradaProdutoId = em.createEntrada(data);
							msgSuccess = "Produto inserido com sucesso com. Código gerado: "
									+ String.format("%06d", entradaProdutoId);
							// btnCancelarConsig.setEnabled(true);
							// btnExcluirConsig.setEnabled(true);
							DateFormat df = new SimpleDateFormat(
									"dd/MM/yyyy HH:mm");
							Date today = Calendar.getInstance().getTime();

							addTProduto(txtDescricao.getText(),
									(String) cmbCategoria.getSelectedItem(),
									(String) cmbMarca.getSelectedItem(),
									txtTamanho.getText(), txtCor.getText(),
									txtQtde.getText(),
									(String) cmbSituacao.getSelectedItem(),
									df.format(today), df.format(inicioDate),
									df.format(vencDate),
									(String) cmbTipo.getSelectedItem(),
									String.valueOf(0), String.valueOf(40),
									String.valueOf(8), String.valueOf(0),
									String.valueOf(0));
							limparProduto();
						}
						if (consigId > 0) {
							JOptionPane.showMessageDialog(null, msgSuccess);
							tabbedPanePedido.setEnabledAt(3, true);
							tabbedPanePedido.setEnabledAt(4, true);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foi possível inserir o produto.");
						}
					} else {
						JOptionPane.showMessageDialog(null, msgErro);
					}
				}

				if (jtableProdutos.getRowCount() > 0) {
					tabbedPanePedido.setEnabledAt(3, true);
					tabbedPanePedido.setEnabledAt(4, true);
				}

			}
		});
		pecarPanel.add(btnSalvarProduto);

		btnCancelarProduto = new JButton("Cancelar", new ImageIcon(
				"images/cancel-icon.png"));
		sl_pecarPanel.putConstraint(SpringLayout.WEST, btnCancelarProduto, 10,
				SpringLayout.WEST, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, btnSalvarProduto, 0,
				SpringLayout.NORTH, btnCancelarProduto);
		btnCancelarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparProduto();
				limparAvaliacao();
			}
		});
		btnCancelarProduto.setEnabled(false);
		pecarPanel.add(btnCancelarProduto);
		jtableProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				linhaSelecionadaProduto = jtableProdutos.getSelectedRow();
				limparAvaliacao();
				populateProduto();
			}
		});

		jtableProdutos.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						System.out.println(value);
						
						final Component c = super
								.getTableCellRendererComponent(table, value,
										isSelected, hasFocus, row, column);
						c.setBackground(row % 2 == 1 ? Color.LIGHT_GRAY
								: Color.WHITE);
						
						if(isSelected) {
							c.setBackground(Color.GREEN);
							c.setForeground(Color.DARK_GRAY);
						}
						
						return c;
					}
				});

		jtableProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtableProdutos.getColumnModel().getColumn(0).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtableProdutos.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtableProdutos.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(5).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtableProdutos.getColumnModel().getColumn(7).setPreferredWidth(90);
		jtableProdutos.getColumnModel().getColumn(8).setPreferredWidth(120);

		JScrollPane scrollPaneProdutos = new JScrollPane(jtableProdutos);
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, btnCancelarProduto, 6,
				SpringLayout.SOUTH, scrollPaneProdutos);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, scrollPaneProdutos, 10,
				SpringLayout.WEST, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.EAST, scrollPaneProdutos, 0,
				SpringLayout.EAST, panel_2);
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, scrollPaneProdutos,
				-44, SpringLayout.SOUTH, pecarPanel);
		pecarPanel.add(scrollPaneProdutos);

		JLabel lblListaDosProdudosclique = new JLabel(
				"Lista dos Produdos(clique para editar)");
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, panel_2, -8,
				SpringLayout.NORTH, lblListaDosProdudosclique);
		sl_pecarPanel
				.putConstraint(SpringLayout.SOUTH, lblListaDosProdudosclique,
						-239, SpringLayout.SOUTH, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, scrollPaneProdutos, 6,
				SpringLayout.SOUTH, lblListaDosProdudosclique);
		sl_pecarPanel.putConstraint(SpringLayout.WEST,
				lblListaDosProdudosclique, -465, SpringLayout.EAST, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.EAST,
				lblListaDosProdudosclique, -254, SpringLayout.EAST, pecarPanel);
		pecarPanel.add(lblListaDosProdudosclique);

		JButton btnRecuperarPedido = new JButton("Recuperar Entrada",
				new ImageIcon("images/Documents-icon.png"));
		sl_pecarPanel.putConstraint(SpringLayout.EAST, btnRecuperarPedido, -10,
				SpringLayout.EAST, pecarPanel);
		btnRecuperarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pm.findWhere("consignatario_fk", String.valueOf(consigId))
						.size() > 0) {
					HeaderEntradaDialog pedidoDiag = new HeaderEntradaDialog(
							consigId);
					pedidoDiag.setVisible(true);

					pedidoId = pedidoDiag.getTheId();
					System.out.println("ENTRADA " + pedidoId);
					if (pedidoId > 0) {
						populatePedido();
					}
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Não existe ainda nenhuma entrada registrada no sistema com este consignatário!");
				}

			}
		});
		pecarPanel.add(btnRecuperarPedido);

		lblNovaEntrada = new JLabel("Nova Entrada");
		sl_pecarPanel.putConstraint(SpringLayout.SOUTH, lblNovaEntrada, -393,
				SpringLayout.SOUTH, pecarPanel);
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, btnRecuperarPedido, -4,
				SpringLayout.NORTH, lblNovaEntrada);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, btnRecuperarPedido, 132,
				SpringLayout.EAST, lblNovaEntrada);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, lblNovaEntrada, 0,
				SpringLayout.WEST, panel_2);
		lblNovaEntrada.setFont(new Font("SansSerif", Font.BOLD, 14));
		pecarPanel.add(lblNovaEntrada);

		btnExcluirProd = new JButton("Excluir", new ImageIcon(
				"images/delete-icon.png"));
		sl_pecarPanel.putConstraint(SpringLayout.NORTH, btnExcluirProd, 6,
				SpringLayout.SOUTH, scrollPaneProdutos);
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir o produto "
								+ entradaProdutoId + "?", "Atencão",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					removeTProduto();
					try {
						em.deleteById(entradaProdutoId);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					limparProduto();
					limparAvaliacao();
				}

			}
		});
		btnExcluirProd.setEnabled(false);
		sl_pecarPanel.putConstraint(SpringLayout.WEST, btnExcluirProd, 6,
				SpringLayout.EAST, btnCancelarProduto);
		pecarPanel.add(btnExcluirProd);

		JPanel imgPanel = new JPanel();
		tabbedPanePedido.addTab("Imagens", null, imgPanel, null);
		SpringLayout sl_imgPanel = new SpringLayout();
		imgPanel.setLayout(sl_imgPanel);

		panelPicture = new JPanel();
		sl_imgPanel.putConstraint(SpringLayout.NORTH, panelPicture, 23,
				SpringLayout.NORTH, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.WEST, panelPicture, 10,
				SpringLayout.WEST, imgPanel);
		panelPicture.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		imgPanel.add(panelPicture);

		btnInserirImg = new JButton("Inserir", new ImageIcon(
				"images/add-icon.png"));

		btnInserirImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entrada ee = EntradaModel.findOneWhere("id",
						String.valueOf(entradaProdutoId));

				JFileChooser arquivo = new JFileChooser();

				int retorno = arquivo.showOpenDialog(null);
				String caminhoArquivo = "";
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
							InputStream fileStream = new FileInputStream(file);
							LobHelper lobHelper = session.getLobHelper();
							Blob dataBlob = lobHelper.createBlob(fileStream,
									fileSize);

							imagemId = im.createImagem(dataBlob);
							Imagem ie = im.findOneWhere("id",
									String.valueOf(imagemId));

							// imgs.add(ie);

							// em.saveImagens(entradaProdutoId, imgs);

							em.saveOneImage(entradaProdutoId, ie);
							checkImgCount();
							imgCount = 0;
							setFirstImage();
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
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnInserirImg, 10,
				SpringLayout.WEST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.SOUTH, panelPicture, -6,
				SpringLayout.NORTH, btnInserirImg);
		sl_imgPanel.putConstraint(SpringLayout.SOUTH, btnInserirImg, -10,
				SpringLayout.SOUTH, imgPanel);
		imgPanel.add(btnInserirImg);

		btnTrocarImg = new JButton("Trocar", new ImageIcon(
				"images/switch-img-icon.png"));
		btnTrocarImg.setEnabled(false);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnTrocarImg, 0,
				SpringLayout.NORTH, btnInserirImg);
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnTrocarImg, 6,
				SpringLayout.EAST, btnInserirImg);
		imgPanel.add(btnTrocarImg);

		btnExcluirImg = new JButton("Excluir", new ImageIcon(
				"images/delete-icon.png"));
		btnExcluirImg.setEnabled(false);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnExcluirImg, 0,
				SpringLayout.NORTH, btnInserirImg);
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnExcluirImg, 6,
				SpringLayout.EAST, btnTrocarImg);
		imgPanel.add(btnExcluirImg);

		btnFirstImg = new JButton(new ImageIcon("images/first-view-icon.png"));
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnFirstImg, 6,
				SpringLayout.SOUTH, panelPicture);
		btnFirstImg.setEnabled(false);
		btnFirstImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imgCount = 0;
				setFirstImage();
			}
		});
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnFirstImg, 6,
				SpringLayout.EAST, btnExcluirImg);
		imgPanel.add(btnFirstImg);

		btnPreviousImg = new JButton(new ImageIcon(
				"images/previous-view-icon.png"));
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnPreviousImg, 6,
				SpringLayout.SOUTH, panelPicture);
		btnPreviousImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (imgListIter.hasPrevious()) {

					setPreviousImage();

				}
			}
		});
		btnPreviousImg.setEnabled(false);
		sl_imgPanel.putConstraint(SpringLayout.WEST, btnPreviousImg, 6,
				SpringLayout.EAST, btnFirstImg);
		imgPanel.add(btnPreviousImg);

		btnNextImg = new JButton(new ImageIcon("images/next-view-icon.png"));
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imgListIter.hasNext()) {
					setNextImage();
				}
			}
		});
		btnNextImg.setEnabled(false);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnNextImg, 0,
				SpringLayout.NORTH, btnInserirImg);
		imgPanel.add(btnNextImg);

		btnLastImg = new JButton(new ImageIcon("images/last-view-icon.png"));
		btnLastImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setLastImage();
			}
		});
		sl_imgPanel.putConstraint(SpringLayout.EAST, panelPicture, 0,
				SpringLayout.EAST, btnLastImg);
		btnLastImg.setEnabled(false);
		sl_imgPanel.putConstraint(SpringLayout.EAST, btnLastImg, -10,
				SpringLayout.EAST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.EAST, btnNextImg, -6,
				SpringLayout.WEST, btnLastImg);
		sl_imgPanel.putConstraint(SpringLayout.NORTH, btnLastImg, 0,
				SpringLayout.NORTH, btnInserirImg);
		imgPanel.add(btnLastImg);

		lblCountImg = new JLabel("0/0");
		sl_imgPanel.putConstraint(SpringLayout.NORTH, lblCountImg, 0,
				SpringLayout.NORTH, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.EAST, lblCountImg, -10,
				SpringLayout.EAST, imgPanel);
		lblCountImg.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCountImg.setHorizontalAlignment(SwingConstants.RIGHT);
		imgPanel.add(lblCountImg);

		lblProdutoSelecionado = new JLabel("Nenhum Produto Selecionado");
		sl_imgPanel.putConstraint(SpringLayout.SOUTH, lblProdutoSelecionado,
				-6, SpringLayout.NORTH, panelPicture);
		sl_imgPanel.putConstraint(SpringLayout.EAST, lblProdutoSelecionado,
				-54, SpringLayout.EAST, imgPanel);
		sl_imgPanel.putConstraint(SpringLayout.WEST, lblCountImg, 6,
				SpringLayout.EAST, lblProdutoSelecionado);
		sl_imgPanel.putConstraint(SpringLayout.WEST, lblProdutoSelecionado, 10,
				SpringLayout.WEST, imgPanel);
		lblProdutoSelecionado.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelPicture.setLayout(new CardLayout(0, 0));

		picture = new JLabel("");
		panelPicture.add(picture, "name_30957025553916");
		imgPanel.add(lblProdutoSelecionado);

		JPanel panel_6 = new JPanel();
		tabbedPanePedido.addTab("Avaliar", null, panel_6, null);
		SpringLayout sl_panel_6 = new SpringLayout();
		panel_6.setLayout(sl_panel_6);

		panel_7 = new JPanel();
		sl_panel_6.putConstraint(SpringLayout.NORTH, panel_7, 10,
				SpringLayout.NORTH, panel_6);
		sl_panel_6.putConstraint(SpringLayout.WEST, panel_7, 10,
				SpringLayout.WEST, panel_6);
		sl_panel_6.putConstraint(SpringLayout.SOUTH, panel_7, -216,
				SpringLayout.SOUTH, panel_6);
		sl_panel_6.putConstraint(SpringLayout.EAST, panel_7, -10,
				SpringLayout.EAST, panel_6);
		panel_7.setBorder(new TitledBorder(null,
				"Selecione um Produto na Tabela Abaixo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.add(panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_7.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_7.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_7.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_7.setLayout(gbl_panel_7);

		JLabel lblVenda = new JLabel("Venda R$:");
		GridBagConstraints gbc_lblVenda = new GridBagConstraints();
		gbc_lblVenda.anchor = GridBagConstraints.EAST;
		gbc_lblVenda.insets = new Insets(0, 0, 5, 5);
		gbc_lblVenda.gridx = 0;
		gbc_lblVenda.gridy = 0;
		panel_7.add(lblVenda, gbc_lblVenda);

		NumberFormat f = NumberFormat.getNumberInstance();

		txtVenda = new JNumericField();
		txtVenda.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtVenda.getText().trim().isEmpty()) {
					calcularCusto();
					calcularComissao();
				}
			}

		});

		txtVenda.setMaxLength(6);
		GridBagConstraints gbc_txtVenda = new GridBagConstraints();
		gbc_txtVenda.gridwidth = 4;
		gbc_txtVenda.insets = new Insets(0, 0, 5, 5);
		gbc_txtVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVenda.gridx = 1;
		gbc_txtVenda.gridy = 0;
		panel_7.add(txtVenda, gbc_txtVenda);
		txtVenda.setColumns(10);

		JLabel lblMargem = new JLabel("Margem (%):");
		GridBagConstraints gbc_lblMargem = new GridBagConstraints();
		gbc_lblMargem.anchor = GridBagConstraints.EAST;
		gbc_lblMargem.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargem.gridx = 5;
		gbc_lblMargem.gridy = 0;
		panel_7.add(lblMargem, gbc_lblMargem);

		txtMargem = new JNumericField();
		txtMargem.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtMargem.getText().trim().isEmpty()) {
					calcularCusto();
				}
			}

		});
		txtMargem.setMaxLength(2);
		GridBagConstraints gbc_txtMargem = new GridBagConstraints();
		gbc_txtMargem.anchor = GridBagConstraints.NORTH;
		gbc_txtMargem.insets = new Insets(0, 0, 5, 5);
		gbc_txtMargem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMargem.gridx = 6;
		gbc_txtMargem.gridy = 0;
		panel_7.add(txtMargem, gbc_txtMargem);
		txtMargem.setColumns(10);

		JLabel lblComisso = new JLabel("Comiss\u00E3o (%):");
		GridBagConstraints gbc_lblComisso = new GridBagConstraints();
		gbc_lblComisso.anchor = GridBagConstraints.EAST;
		gbc_lblComisso.insets = new Insets(0, 0, 5, 5);
		gbc_lblComisso.gridx = 7;
		gbc_lblComisso.gridy = 0;
		panel_7.add(lblComisso, gbc_lblComisso);

		txtComissao = new JNumericField();

		GridBagConstraints gbc_txtComissao = new GridBagConstraints();
		gbc_txtComissao.insets = new Insets(0, 0, 5, 0);
		gbc_txtComissao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComissao.gridx = 8;
		gbc_txtComissao.gridy = 0;
		panel_7.add(txtComissao, gbc_txtComissao);
		txtComissao.setColumns(10);
		txtComissao.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtComissao.getText().trim().isEmpty()) {
					calcularComissao();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtComissao.getText().trim().isEmpty()) {
					calcularComissao();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (entradaAvaliarId > 0
						&& !txtComissao.getText().trim().isEmpty()) {
					calcularComissao();
				}
			}

		});
		txtComissao.setMaxLength(2);
		JLabel lblEntrada = new JLabel("Data In\u00EDcio:");
		GridBagConstraints gbc_lblEntrada = new GridBagConstraints();
		gbc_lblEntrada.anchor = GridBagConstraints.EAST;
		gbc_lblEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrada.gridx = 0;
		gbc_lblEntrada.gridy = 1;
		panel_7.add(lblEntrada, gbc_lblEntrada);

		try {
			txtInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		;
		GridBagConstraints gbc_txtInicio = new GridBagConstraints();
		gbc_txtInicio.gridwidth = 5;
		gbc_txtInicio.insets = new Insets(0, 0, 5, 5);
		gbc_txtInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInicio.gridx = 1;
		gbc_txtInicio.gridy = 1;
		panel_7.add(txtInicio, gbc_txtInicio);
		txtInicio.setColumns(10);

		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		GridBagConstraints gbc_lblSituacao = new GridBagConstraints();
		gbc_lblSituacao.anchor = GridBagConstraints.EAST;
		gbc_lblSituacao.insets = new Insets(0, 0, 5, 5);
		gbc_lblSituacao.gridx = 6;
		gbc_lblSituacao.gridy = 1;
		panel_7.add(lblSituacao, gbc_lblSituacao);

		cmbSituacao = new JComboBox();

		Iterator situacoes = SituacaoModel.findAll().iterator();

		while (situacoes.hasNext()) {
			Situacao s = (Situacao) situacoes.next();
			cmbSituacao.addItem(s.getName());
		}

		cmbSituacao.setSelectedItem("avaliando");

		cmbSituacao.setMaximumRowCount(3);
		GridBagConstraints gbc_cmbSituacao = new GridBagConstraints();
		gbc_cmbSituacao.gridwidth = 2;
		gbc_cmbSituacao.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSituacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSituacao.gridx = 7;
		gbc_cmbSituacao.gridy = 1;
		panel_7.add(cmbSituacao, gbc_cmbSituacao);

		JLabel lblValidade = new JLabel("Validade:");
		GridBagConstraints gbc_lblValidade = new GridBagConstraints();
		gbc_lblValidade.anchor = GridBagConstraints.EAST;
		gbc_lblValidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidade.gridx = 0;
		gbc_lblValidade.gridy = 2;
		panel_7.add(lblValidade, gbc_lblValidade);

		cmbValidade = new JComboBox();
		cmbValidade.setModel(new DefaultComboBoxModel(new String[] {"3 meses", "75 dias", "2 meses", "45 dias", "1 m\u00EAs", "15 dias", "10 dias", "5 dias", "1 dia"}));
		GridBagConstraints gbc_cmbValidade = new GridBagConstraints();
		gbc_cmbValidade.gridwidth = 5;
		gbc_cmbValidade.insets = new Insets(0, 0, 5, 5);
		gbc_cmbValidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbValidade.gridx = 1;
		gbc_cmbValidade.gridy = 2;
		panel_7.add(cmbValidade, gbc_cmbValidade);

		JLabel lblValidade_1 = new JLabel("Tipo:");
		GridBagConstraints gbc_lblValidade_1 = new GridBagConstraints();
		gbc_lblValidade_1.anchor = GridBagConstraints.EAST;
		gbc_lblValidade_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidade_1.gridx = 6;
		gbc_lblValidade_1.gridy = 2;
		panel_7.add(lblValidade_1, gbc_lblValidade_1);

		cmbTipo = new JComboBox();

		Iterator tipos = TipoModel.findAll().iterator();

		while (tipos.hasNext()) {
			Tipo t = (Tipo) tipos.next();
			cmbTipo.addItem(t.getName());
		}

		cmbTipo.setSelectedItem("usado");

		GridBagConstraints gbc_cmbTipo = new GridBagConstraints();
		gbc_cmbTipo.gridwidth = 2;
		gbc_cmbTipo.insets = new Insets(0, 0, 5, 0);
		gbc_cmbTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipo.gridx = 7;
		gbc_cmbTipo.gridy = 2;
		panel_7.add(cmbTipo, gbc_cmbTipo);

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		GridBagConstraints gbc_lblObservaes = new GridBagConstraints();
		gbc_lblObservaes.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservaes.gridx = 0;
		gbc_lblObservaes.gridy = 3;
		panel_7.add(lblObservaes, gbc_lblObservaes);

		tAreaObserv = new JTextArea();
		GridBagConstraints gbc_tAreaObserv = new GridBagConstraints();
		gbc_tAreaObserv.insets = new Insets(0, 0, 5, 0);
		gbc_tAreaObserv.gridheight = 2;
		gbc_tAreaObserv.gridwidth = 8;
		gbc_tAreaObserv.fill = GridBagConstraints.BOTH;
		gbc_tAreaObserv.gridx = 1;
		gbc_tAreaObserv.gridy = 3;
		panel_7.add(tAreaObserv, gbc_tAreaObserv);

		JLabel lblValorVenda = new JLabel("Valor Custo:");
		lblValorVenda.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblValorVenda = new GridBagConstraints();
		gbc_lblValorVenda.anchor = GridBagConstraints.EAST;
		gbc_lblValorVenda.insets = new Insets(0, 0, 0, 5);
		gbc_lblValorVenda.gridx = 0;
		gbc_lblValorVenda.gridy = 5;
		panel_7.add(lblValorVenda, gbc_lblValorVenda);

		lblCusto = new JLabel("R$ 0,00");
		lblCusto.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblCusto = new GridBagConstraints();
		gbc_lblCusto.gridwidth = 4;
		gbc_lblCusto.insets = new Insets(0, 0, 0, 5);
		gbc_lblCusto.gridx = 2;
		gbc_lblCusto.gridy = 5;
		panel_7.add(lblCusto, gbc_lblCusto);

		JLabel lblValorCom = new JLabel("Valor Comiss\u00E3o:");
		lblValorCom.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblValorCom = new GridBagConstraints();
		gbc_lblValorCom.anchor = GridBagConstraints.EAST;
		gbc_lblValorCom.gridwidth = 2;
		gbc_lblValorCom.insets = new Insets(0, 0, 0, 5);
		gbc_lblValorCom.gridx = 6;
		gbc_lblValorCom.gridy = 5;
		panel_7.add(lblValorCom, gbc_lblValorCom);

		lblComis = new JLabel("R$ 0,00");
		lblComis.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblComis = new GridBagConstraints();
		gbc_lblComis.gridx = 8;
		gbc_lblComis.gridy = 5;
		panel_7.add(lblComis, gbc_lblComis);

		JLabel lblNewLabel = new JLabel(
				"Lista dos Produdos(clique para editar)");
		sl_panel_6.putConstraint(SpringLayout.NORTH, lblNewLabel, 6,
				SpringLayout.SOUTH, panel_7);
		sl_panel_6.putConstraint(SpringLayout.WEST, lblNewLabel, 0,
				SpringLayout.WEST, panel_7);
		panel_6.add(lblNewLabel);

		btnAtualizarAval = new JButton("Atualizar");
		btnAtualizarAval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				if (entradaAvaliarId > 0) {
					HashMap<String, Object> data = new HashMap();
					boolean isValid = true;

					if (isValid) {
						data.put("venda",
								Double.parseDouble(txtVenda.getText()));

						String[] strinicio = txtInicio.getText().split("/");

						Calendar inicio = Calendar.getInstance();
						inicio.set(Calendar.YEAR,
								Integer.parseInt(strinicio[2]));
						inicio.set(Calendar.MONTH,
								Integer.parseInt(strinicio[1]) - 1);
						inicio.set(Calendar.DAY_OF_MONTH,
								Integer.parseInt(strinicio[0]));
						Date inicioDate = inicio.getTime();

						data.put("inicio", inicioDate);

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
						venc.setTime(inicioDate);
						venc.add(calendarConst, soma);

						Date vencDate = venc.getTime();

						data.put("vencimento", vencDate);

						Situacao situacao = SituacaoModel.findOneWhere("name",
								"'" + cmbSituacao.getSelectedItem() + "'");
						data.put("situacao", situacao);

						Tipo tipo = TipoModel.findOneWhere("name", "'"
								+ cmbTipo.getSelectedItem() + "'");
						data.put("tipo", tipo);

						data.put("observacoes", tAreaObserv.getText());

						data.put("venda",
								Double.parseDouble(txtVenda.getText()));

						data.put("margemCusto",
								Double.parseDouble(txtMargem.getText()));
						data.put("margemComissao",
								Double.parseDouble(txtComissao.getText()));

						data.put("custo", custo);
						data.put("comissao", comissao);

						SimpleDateFormat sdf1 = new SimpleDateFormat(
								"dd/MM/yyyy");

						em.saveAvaliacao(entradaAvaliarId, data);

						updateTAvaliar(txtVenda.getText(), txtMargem.getText(),
								txtComissao.getText(), situacao.getName(),
								sdf1.format(inicioDate), sdf1.format(vencDate),
								tipo.getName(), String.valueOf(custo),
								String.valueOf(comissao));
						JOptionPane.showMessageDialog(null,
								"Produto atualizado com sucesso");
					}
				}
			}
		});
		btnAtualizarAval.setEnabled(false);
		sl_panel_6.putConstraint(SpringLayout.NORTH, btnAtualizarAval, -6,
				SpringLayout.NORTH, lblNewLabel);
		sl_panel_6.putConstraint(SpringLayout.EAST, btnAtualizarAval, 0,
				SpringLayout.EAST, panel_7);
		panel_6.add(btnAtualizarAval);
		jtableAvaliar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO
				linhaSelecionadaAvaliar = jtableAvaliar.getSelectedRow();
				populateAvaliar();
			}
		});

		jtableAvaliar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jtableAvaliar.getColumnModel().getColumn(0).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtableAvaliar.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(5).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(6).setPreferredWidth(120);
		jtableAvaliar.getColumnModel().getColumn(7).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(8).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(9).setPreferredWidth(90);
		jtableAvaliar.getColumnModel().getColumn(10).setPreferredWidth(90);

		jtableAvaliar.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						final Component c = super
								.getTableCellRendererComponent(table, value,
										isSelected, hasFocus, row, column);
						if(((String) table.getValueAt(row, 5)).contains("avaliando")) {
							c.setBackground(Color.RED);
							c.setForeground(Color.WHITE);
	
						} else {
							c.setBackground(Color.WHITE);
							c.setForeground(Color.BLACK);
						}
						
						if(isSelected) {
							c.setBackground(Color.GREEN);
							c.setForeground(Color.DARK_GRAY);
						}
						
						return c;
					}
				});
		
		jtableAvaliar.setSelectionBackground(Color.DARK_GRAY);
		jtableAvaliar.setSelectionForeground(Color.WHITE);
		
		JScrollPane scrollPaneAvaliar = new JScrollPane(jtableAvaliar);
		sl_panel_6.putConstraint(SpringLayout.NORTH, scrollPaneAvaliar, 0,
				SpringLayout.SOUTH, btnAtualizarAval);
		sl_panel_6.putConstraint(SpringLayout.WEST, scrollPaneAvaliar, 0,
				SpringLayout.WEST, panel_7);
		sl_panel_6.putConstraint(SpringLayout.SOUTH, scrollPaneAvaliar, 178,
				SpringLayout.SOUTH, btnAtualizarAval);
		sl_panel_6.putConstraint(SpringLayout.EAST, scrollPaneAvaliar, 0,
				SpringLayout.EAST, panel_7);
		panel_6.add(scrollPaneAvaliar);

		JPanel finalizarPanel = new JPanel();
		tabbedPanePedido.addTab("Resumo", null, finalizarPanel, null);
		SpringLayout sl_finalizarPanel = new SpringLayout();
		finalizarPanel.setLayout(sl_finalizarPanel);

		JButton btnNewButton_2 = new JButton(new ImageIcon(
				"images/Documents-icon.png"));
		btnNewButton_2.setText("Recuperar Entrada");
		// btnNewButton_2.setIcon(new
		// ImageIcon("C:\\Users\\arthur\\Pictures\\brecho\\registration_queue.png"));
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, btnNewButton_2, 4,
				SpringLayout.NORTH, finalizarPanel);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, btnNewButton_2, -10,
				SpringLayout.EAST, finalizarPanel);
		finalizarPanel.add(btnNewButton_2);

		JLabel lblNmeroPedido = new JLabel("N\u00FAmero Entrada:");
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, lblNmeroPedido, 10,
				SpringLayout.NORTH, finalizarPanel);
		lblNmeroPedido.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, lblNmeroPedido, 10,
				SpringLayout.WEST, finalizarPanel);
		finalizarPanel.add(lblNmeroPedido);

		lblNumPedido = new JLabel("000000");
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, btnNewButton_2, 6,
				SpringLayout.EAST, lblNumPedido);
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, lblNumPedido, 0,
				SpringLayout.NORTH, lblNmeroPedido);
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, lblNumPedido, 6,
				SpringLayout.EAST, lblNmeroPedido);
		finalizarPanel.add(lblNumPedido);

		JButton btnExcluirPedido = new JButton("Excluir Entrada",
				new ImageIcon("images/delete-icon.png"));
		btnExcluirPedido.setEnabled(false);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, btnExcluirPedido, 0,
				SpringLayout.EAST, btnNewButton_2);
		finalizarPanel.add(btnExcluirPedido);

		JPanel panel_5 = new JPanel();
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, btnExcluirPedido,
				6, SpringLayout.SOUTH, panel_5);
		sl_finalizarPanel.putConstraint(SpringLayout.NORTH, panel_5, 6,
				SpringLayout.SOUTH, btnNewButton_2);
		sl_finalizarPanel.putConstraint(SpringLayout.SOUTH, panel_5, -44,
				SpringLayout.SOUTH, finalizarPanel);
		panel_5.setBorder(new TitledBorder(null, "Resumo da Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_finalizarPanel.putConstraint(SpringLayout.WEST, panel_5, 10,
				SpringLayout.WEST, finalizarPanel);
		sl_finalizarPanel.putConstraint(SpringLayout.EAST, panel_5, 0,
				SpringLayout.EAST, btnNewButton_2);
		finalizarPanel.add(panel_5);

		tabbedPanePedido.setEnabledAt(1, false);
		tabbedPanePedido.setEnabledAt(2, false);
		tabbedPanePedido.setEnabledAt(3, false);
		tabbedPanePedido.setEnabledAt(4, false);

		tabbedPanePedido.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabbedPanePedido.getSelectedIndex() == 2) {
					Entrada entSelect = em.findOneWhere("id",
							String.valueOf(entradaProdutoId));

					lblProdutoSelecionado.setText("ID: " + entSelect.getId()
							+ " - " + entSelect.getDescricao());

					checkImgCount();

					picture.setIcon(null);

					if (imgTotalCount > 0) {
						imgCount = 0;
						setFirstImage();

					}
				}
			}
		});

	}

	protected void populatePedido() {
		// TODO Auto-generated method stub
		limparTabelas();

		List entradasPedido = em.findWhere("header_entrada_fk",
				String.valueOf(pedidoId));

		for (Object entrada : entradasPedido) {
			Entrada e = (Entrada) entrada;
			entradaProdutoId = e.getId();
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

			String tipo = e.getTipo() != null ? e.getTipo().getName()
					: "sem tipo";

			addTProduto(e.getDescricao(), e.getCategoria().getName(), e
					.getMarca().getName(), e.getTamanho(), e.getCor(),
					String.valueOf(e.getQuantidate()), e.getSituacao()
							.getName(), sdf1.format(e.getDataEntrada()),
					sdf1.format(e.getDataInicio()), sdf1.format(e
							.getDataVencimento()), e.getTipo().getName(),
					String.valueOf(e.getVenda()), String.valueOf(e
							.getMargemCusto()), String.valueOf(e
							.getMargemComissao()),
					String.valueOf(e.getCusto()), String.valueOf(e
							.getComissao()));

			limparProduto();
			limparAvaliacao();

			lblNovaEntrada.setText("Cód. Entrada: " + pedidoId);
		}
		tabbedPanePedido.setEnabledAt(3, true);
		tabbedPanePedido.setEnabledAt(4, true);
	}

	private void limparTabelas() {
		// TODO Auto-generated method stub
		modelProduto.setRowCount(0);
		modelAvaliar.setRowCount(0);
	}

	protected void calcularCusto() {
		this.custo = 0;

		if (!txtVenda.getText().trim().isEmpty()
				&& !txtMargem.getText().trim().isEmpty()) {
			if (!txtVenda.getText().trim().isEmpty()
					&& Double.parseDouble(txtVenda.getText()) >= 0
					&& Double.parseDouble(txtMargem.getText()) >= 0) {

				Double venda = Double.parseDouble(txtVenda.getText());
				Double margem = Double.parseDouble(txtMargem.getText()) / 100;

				Double custo = venda * margem;

				DecimalFormat df = new DecimalFormat("#.##");
				lblCusto.setText("R$ " + df.format(custo));

				this.custo = custo;
			}
		}

	}

	protected void calcularComissao() {
		this.comissao = 0;

		if (!txtVenda.getText().trim().isEmpty()
				&& !txtComissao.getText().trim().isEmpty()) {
			if (!txtVenda.getText().trim().isEmpty()
					&& Double.parseDouble(txtVenda.getText()) > 0
					&& Double.parseDouble(txtComissao.getText()) >= 0) {
				Double venda = Double.parseDouble(txtVenda.getText());
				Double comis = Double.parseDouble(txtComissao.getText()) / 100;

				Double comissao = venda * comis;

				DecimalFormat df = new DecimalFormat("#.##");
				lblComis.setText("R$ " + df.format(comissao));

				this.comissao = comissao;
			}
		}

	}

	protected void populateAvaliar() {
		// TODO Auto-generated method stub
		entradaAvaliarId = Integer.parseInt((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 0));

		panel_7.setBorder(new TitledBorder(null, "Cód. Produto: "
				+ String.format("%06d", entradaAvaliarId)
				+ " | "
				+ (String) jtableProdutos
						.getValueAt(linhaSelecionadaAvaliar, 1),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		txtVenda.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 2));
		txtMargem.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 3));
		txtComissao.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 4));

		cmbSituacao.setSelectedItem((String) jtableAvaliar.getValueAt(
				linhaSelecionadaProduto, 5));
		txtInicio.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 6));
		cmbValidade.setSelectedItem((String) jtableAvaliar.getValueAt(
				linhaSelecionadaProduto, 7));
		cmbTipo.setSelectedItem((String) jtableAvaliar.getValueAt(
				linhaSelecionadaProduto, 8));
		lblCusto.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 9));
		lblComis.setText((String) jtableAvaliar.getValueAt(
				linhaSelecionadaAvaliar, 10));

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		String x = (String) jtableAvaliar
				.getValueAt(linhaSelecionadaAvaliar, 6);

		try {
			txtInicio.setText(sdf1.format(sdf1.parse(x)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnAtualizarAval.setEnabled(true);
	}

	private MaskFormatter formatString(String fomater) {
		MaskFormatter frmtTefelone = null;
		try {
			frmtTefelone = new MaskFormatter(fomater);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return frmtTefelone;
	}

	protected void populateProduto() {
		entradaProdutoId = Integer.parseInt((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 0));

		txtDescricao.setText((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 1));

		cmbCategoria.setSelectedItem((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 2));
		cmbMarca.setSelectedItem((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 3));

		txtTamanho.setText((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 4));
		txtCor.setText((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 5));
		txtQtde.setText((String) jtableProdutos.getValueAt(
				linhaSelecionadaProduto, 6));

		tabbedPanePedido.setEnabledAt(2, true);
		tabbedPanePedido.setEnabledAt(3, true);
		tabbedPanePedido.setEnabledAt(4, true);

		btnCancelarProduto.setEnabled(true);
		btnExcluirProd.setEnabled(true);
	}

	protected void limparConsignatario() {
		lblNovoConsig.setText("Novo Consignatário");
		lblNovaEntrada.setText("Nova Entrada");

		limparProduto();
		limparAvaliacao();

		consigId = 0;

		txtNome.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
		txtSite.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtLograd.setText("");
		txtNum.setText("");
		txtComplem.setText("");
		txtBairro.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		cmbUf.setSelectedItem("SP");

		btnCancelarConsig.setEnabled(false);
		btnExcluirConsig.setEnabled(false);

		tabbedPanePedido.setEnabledAt(1, false);
		tabbedPanePedido.setEnabledAt(2, false);
		tabbedPanePedido.setEnabledAt(3, false);
		tabbedPanePedido.setEnabledAt(4, false);

	}

	private void limparAvaliacao() {
		// TODO Auto-generated method stub
		panel_7.setBorder(new TitledBorder(null,
				"Selecione um Produto na Tabela Abaixo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		txtVenda.setText("");
		txtMargem.setText("");
		txtComissao.setText("");
		txtInicio.setText("");
		tAreaObserv.setText("");
		lblCusto.setText("R$ 0,00");
		lblComis.setText("R$ 0,00");

		cmbSituacao.setSelectedItem("avaliando");
		cmbTipo.setSelectedItem("usado");
		btnAtualizarAval.setEnabled(false);
	}

	private void limparProduto() {
		// TODO Auto-generated method stub
		entradaProdutoId = 0;

		txtDescricao.setText("");
		txtTamanho.setText("");
		txtCor.setText("");
		txtQtde.setText("1");

		btnCancelarProduto.setEnabled(false);
		btnExcluirProd.setEnabled(false);

		tabbedPanePedido.setEnabledAt(2, false);
	}

	private void addTProduto(String titulo, String categoria, String marca,
			String tamanho, String cor, String qtde, String situacao,
			String entrada, String inicio, String vencimento, String tipo,
			String venda, String margemCusto, String margemComissao,
			String custo, String comissao) {
		// TODO
		String[] linhaProd = new String[] {
				String.format("%06d", entradaProdutoId), titulo, categoria,
				marca, tamanho, cor, qtde, situacao, entrada };

		String[] linhaAval = new String[] {
				String.format("%06d", entradaProdutoId), titulo, venda,
				margemCusto, margemComissao, situacao, inicio, vencimento,
				tipo, custo, comissao };

		modelProduto.addRow(linhaProd);
		modelAvaliar.addRow(linhaAval);
	}

	protected void updateTAvaliar(String venda, String margemCusto,
			String margemComissao, String situacao, String inicio,
			String vencimento, String tipo, String custo, String comissao) {
		String[] linhaAval = new String[] { String.valueOf(entradaAvaliarId),
				(String) jtableProdutos.getValueAt(linhaSelecionadaAvaliar, 1),
				venda, margemCusto, margemComissao, situacao, inicio,
				vencimento, tipo, custo, comissao };

		for (int i = 0; i < jtableAvaliar.getRowCount(); i += 1) {
			if (entradaAvaliarId == Integer.parseInt((String) jtableAvaliar
					.getValueAt(i, 0))) {
				modelAvaliar.removeRow(i);
				modelAvaliar.insertRow(i, linhaAval);
			}
			if (entradaAvaliarId == Integer.parseInt((String) jtableProdutos
					.getValueAt(i, 0))) {
				modelProduto.setValueAt(situacao, i, 7);
			}
		}
	}

	protected void updateTProduto(String titulo, String categoria,
			String marca, String tamanho, String cor, String qtde) {
		// TODO Auto-generated method stub
		String[] linhaProd = new String[] { String.valueOf(entradaProdutoId),
				titulo, categoria, marca, tamanho, cor, qtde };

		for (int i = 0; i < jtableProdutos.getRowCount(); i += 1) {
			if (entradaProdutoId == Integer.parseInt((String) jtableProdutos
					.getValueAt(i, 0))) {
				modelProduto.removeRow(i);
				modelProduto.insertRow(i, linhaProd);
			}
		}

	}

	private void removeTProduto() {
		for (int i = 0; i < jtableProdutos.getRowCount(); i += 1) {
			if (entradaProdutoId == Integer.parseInt((String) jtableProdutos
					.getValueAt(i, 0))) {
				modelProduto.removeRow(i);
			}
			if (entradaProdutoId == Integer.parseInt((String) jtableAvaliar
					.getValueAt(i, 0))) {
				modelAvaliar.removeRow(i);
			}
		}

		if (jtableProdutos.getRowCount() == 0) {
			tabbedPanePedido.setEnabledAt(2, false);
			tabbedPanePedido.setEnabledAt(3, false);
			tabbedPanePedido.setEnabledAt(4, false);
		}
	}

	protected void populateConsignatario() {
		Consignatario ce = cm.findOneWhere("id", String.valueOf(consigId));

		lblNovoConsig.setText("Cód. Consignatário: " + consigId);

		txtNome.setText(ce.getNome());
		txtTelefone.setText(ce.getTelefone());
		txtCelular.setText(ce.getCelular());
		txtEmail.setText(ce.getEmail());
		txtSite.setText(ce.getSite());
		txtCpf.setText(ce.getCpf());
		txtRg.setText(ce.getRg());
		txtLograd.setText(ce.getLogradouro());
		txtNum.setText(ce.getNumero());
		txtComplem.setText(ce.getComplemento());
		txtBairro.setText(ce.getBairro());
		txtCep.setText(ce.getCep());
		txtCidade.setText(ce.getCidade());
		cmbUf.setSelectedItem(ce.getEstado().getName());

		btnCancelarConsig.setEnabled(true);
		btnExcluirConsig.setEnabled(true);

		tabbedPanePedido.setEnabledAt(1, true);
	}

	private int checkImgCount() {
		imgListIter = getImageIterator();

		if (imgTotalCount > 0) {
			lblCountImg.setText(imgCount + "/" + imgTotalCount);
		} else {
			System.out.println("Nenhuma imagem inserida!");

			lblCountImg.setText("0/0");

			btnTrocarImg.setEnabled(false);
			btnExcluirImg.setEnabled(false);
			btnFirstImg.setEnabled(false);
			btnPreviousImg.setEnabled(false);
			btnNextImg.setEnabled(false);
			btnLastImg.setEnabled(false);
		}

		return imgTotalCount;
	}

	private MyIteratorUtil getImageIterator() {
//		imgTotalCount = 0;
//
//		Entrada ee = EntradaModel.findOneWhere("id",
//				String.valueOf(entradaProdutoId));
//
//		try {
//			imgTotalCount = ee.getImagens().size();
//
//			Iterator imgIter = ee.getImagens().iterator();
//
//			ArrayList<Imagem> imgList = new ArrayList<Imagem>();
//
//			while (imgIter.hasNext()) {
//				imgList.add((Imagem) imgIter.next());
//			}
//
//			imgListIter = new MyIteratorUtil(imgList);
//			return imgListIter;
//		} catch (ClassCastException ex) {
//			ex.printStackTrace();
//		} catch (LazyInitializationException ex) {
//			ex.printStackTrace();
//		}

		return null;
	}

	private void setFirstImage() {
		imgListIter = getImageIterator();

		Blob blob_img = ((Imagem) imgListIter.first()).getImagemBlob();

		try {
			BufferedImage bi = setMaxWidthHeight(blob_img);
			imgCount += 1;
			drawPicture(bi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ifHasPrevAndNext();
	}

	private void setPreviousImage() {

		Blob blob_img = ((Imagem) imgListIter.previous()).getImagemBlob();

		try {
			BufferedImage bi = setMaxWidthHeight(blob_img);
			imgCount -= 1;
			drawPicture(bi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ifHasPrevAndNext();

	}

	private void setNextImage() {

		Blob blob_img = ((Imagem) imgListIter.next()).getImagemBlob();

		try {
			BufferedImage bi = setMaxWidthHeight(blob_img);
			imgCount += 1;
			drawPicture(bi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ifHasPrevAndNext();
	}
	
	protected void setLastImage() {
		Blob blob_img = ((Imagem) imgListIter.last()).getImagemBlob();

		try {
			BufferedImage bi = setMaxWidthHeight(blob_img);
			imgCount = imgTotalCount;
			drawPicture(bi);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ifHasPrevAndNext();
	}

	private void ifHasPrevAndNext() {
		if (imgListIter.hasPrevious()) {
			btnFirstImg.setEnabled(true);
			btnPreviousImg.setEnabled(true);
		} else {
			btnFirstImg.setEnabled(false);
			btnPreviousImg.setEnabled(false);
		}

		if (imgListIter.hasNext()) {
			btnLastImg.setEnabled(true);
			btnNextImg.setEnabled(true);
		} else {
			btnLastImg.setEnabled(false);
			btnNextImg.setEnabled(false);
		}
	}

	private void drawPicture(BufferedImage bi) {
		picture.setIcon(new ImageIcon(bi));
		lblCountImg.setText(imgCount + "/" + imgTotalCount);
	}

	private BufferedImage setMaxWidthHeight(Blob blob_img) throws SQLException, IOException {
		InputStream is;
		Image img;
		is = blob_img.getBinaryStream();
		img = ImageIO.read(is);

		int origW = img.getWidth(null), origH = img.getHeight(null), newW = origW, newH = origH;

		if (origW > picture.getWidth() || origH > picture.getHeight()) {
			if (origW >= origH) {
				newW = picture.getWidth();
				newH = origH * newW / origW;
			} else {
				newH = picture.getHeight();
				newW = origW * newH / origH;
			}
		}

		BufferedImage bi = new BufferedImage(newW, newH,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(img, 0, 0, newW, newH, null);
		g.setBackground(new Color(255, 255, 255, 0));
		g.dispose();
		return bi;
	}
}