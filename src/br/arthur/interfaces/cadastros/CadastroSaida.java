package br.arthur.interfaces.cadastros;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.arthur.controllers.LoginController;
import br.arthur.entities.Cliente;
import br.arthur.entities.Entrada;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.Saida;
import br.arthur.interfaces.cadastros.dialogs.ClienteDialog;
import br.arthur.interfaces.cadastros.dialogs.PicZoomDialog;
import br.arthur.interfaces.cadastros.dialogs.FinalizarVendaDialog;
import br.arthur.models.ClienteModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.models.SaidaModel;
import br.arthur.utils.JNumericField;
import br.arthur.utils.MyImageUtil;

public class CadastroSaida extends JInternalFrame {
	EntradaModel em = new EntradaModel();
	ClienteModel cm = new ClienteModel();
	SaidaModel sm = new SaidaModel();

	HeaderSaidaModel hsm = new HeaderSaidaModel();

	private JPanel panel;

	private JLabel lblNomeCliente;
	private JLabel lblNumVenda;

	private JNumericField txtCodigoProduto;
	private JNumericField txtQtde;

	private DefaultTableModel model;
	private JTable table;
	private Vector tableData = new Vector();

	private long hSaidaId = 0;
	private long clienteId = 0;

	private JLabel picProduto;
	private JLabel lblDescProduto;
	private JLabel lblTotalVenda;
	private JLabel lblItensQtdeTotal;

	private JButton btnExcluirProd;
	private JButton btnFinalizarVenda;
	private JButton btnCancelarVenda;

	private long entradaId;

	HashMap<Long, Double> total = new HashMap<Long, Double>();
	HashMap<Long, Integer> itensQtde = new HashMap<Long, Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroSaida frame = new CadastroSaida(null);
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
	public CadastroSaida(final LoginController login) {
		setIconifiable(true);
		setClosable(true);
		setTitle("Venda");
		setBounds(100, 100, 773, 448);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JLabel lblData = new JLabel("Data Venda:");
		springLayout.putConstraint(SpringLayout.NORTH, lblData, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblData, 10,
				SpringLayout.WEST, getContentPane());
		lblData.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblData);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();

		JLabel label = new JLabel(df.format(today));
		springLayout.putConstraint(SpringLayout.NORTH, label, 0,
				SpringLayout.NORTH, lblData);
		springLayout.putConstraint(SpringLayout.WEST, label, 6,
				SpringLayout.EAST, lblData);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(label);

		JLabel lblCliente = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 0,
				SpringLayout.NORTH, lblData);
		springLayout.putConstraint(SpringLayout.EAST, lblCliente, -479,
				SpringLayout.EAST, getContentPane());
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblCliente);

		lblNomeCliente = new JLabel("Selecione um cliente        >>>");
		springLayout.putConstraint(SpringLayout.NORTH, lblNomeCliente, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNomeCliente, 6, SpringLayout.EAST, lblCliente);
		lblNomeCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblNomeCliente);

		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		springLayout.putConstraint(SpringLayout.EAST, lblNomeCliente, -6, SpringLayout.WEST, btnBuscarCliente);
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscarCliente, -6, SpringLayout.NORTH, lblData);
		springLayout.putConstraint(SpringLayout.WEST, btnBuscarCliente, 484,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBuscarCliente, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnBuscarCliente);
		panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.SOUTH, btnBuscarCliente);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, btnBuscarCliente);
		panel.setVisible(false);
		panel.setBorder(new TitledBorder(null, "Venda", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, getContentPane());
		getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm.findAll().size() > 0) {
					ClienteDialog cDiag = new ClienteDialog();
					cDiag.setVisible(true);

					clienteId = cDiag.getTheId();

					if (clienteId > 0) {
						panel.setVisible(true);
						lblNomeCliente.setText(cm.findOneWhere("id",
								String.valueOf(clienteId)).getNome());
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"N�o existe ainda nenhum cliente registrado no sistema!");
				}
			}
		});

		JLabel lblNumNf = new JLabel("Num.\u00BA N.F. / S\u00E9rie: ");
		lblNumNf.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, lblNumNf, 10,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNumNf, 0,
				SpringLayout.WEST, panel);
		panel.add(lblNumNf);

		JLabel label_2 = new JLabel("C\u00F3digo de Barras:");
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, label_2, 0,
				SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, label_2, 185,
				SpringLayout.EAST, lblNumNf);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Quantidade:");
		sl_panel.putConstraint(SpringLayout.NORTH, label_3, 12,
				SpringLayout.SOUTH, label_2);
		sl_panel.putConstraint(SpringLayout.EAST, label_3, 0,
				SpringLayout.EAST, label_2);
		panel.add(label_3);

		txtCodigoProduto = new JNumericField();
		txtCodigoProduto.setMaxLength(8);
		txtCodigoProduto.setFormat(JNumericField.NUMERIC);
		txtCodigoProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == 10) {
					verificarProduto(login);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, txtCodigoProduto, -6,
				SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, txtCodigoProduto, 6,
				SpringLayout.EAST, label_2);
		txtCodigoProduto.setColumns(10);
		panel.add(txtCodigoProduto);

		txtQtde = new JNumericField();
		txtQtde.setEnabled(false);
		txtQtde.setMaxLength(3);
		txtQtde.setFormat(JNumericField.NUMERIC);
		txtQtde.setText("1");
		sl_panel.putConstraint(SpringLayout.NORTH, txtQtde, 0,
				SpringLayout.SOUTH, txtCodigoProduto);
		sl_panel.putConstraint(SpringLayout.WEST, txtQtde, 6,
				SpringLayout.EAST, label_3);
		txtQtde.setColumns(10);
		panel.add(txtQtde);

		btnExcluirProd = new JButton("Excluir Produto");
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcao;
				opcao = JOptionPane
						.showConfirmDialog(null,
								"Deseja Realmente excluir o produto "
										+ entradaId + "?", "Atenc�o",
								JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						sm.deleteByFks(hSaidaId, entradaId);
						removerProdutoTabela();
						double vendaTotal = calcTotalRem(entradaId);
						int itensQtdeTotal = calcQtdeTotalRem(entradaId);
						lblTotalVenda.setText("R$ "
								+ String.format("%.2f", (double) vendaTotal));
						lblItensQtdeTotal.setText(String
								.valueOf(itensQtdeTotal));
						entradaId = 0;
						btnExcluirProd.setEnabled(false);
						picProduto.setIcon(null);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, btnExcluirProd, 5,
				SpringLayout.EAST, txtQtde);
		sl_panel.putConstraint(SpringLayout.EAST, btnExcluirProd, 0,
				SpringLayout.EAST, panel);
		btnExcluirProd.setEnabled(false);
		sl_panel.putConstraint(SpringLayout.NORTH, btnExcluirProd, -6,
				SpringLayout.NORTH, label_3);
		panel.add(btnExcluirProd);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				verificarProduto(login);
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnConsultar, -6,
				SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, btnConsultar, 6,
				SpringLayout.EAST, txtCodigoProduto);
		sl_panel.putConstraint(SpringLayout.EAST, btnConsultar, 0,
				SpringLayout.EAST, panel);
		panel.add(btnConsultar);

		btnCancelarVenda = new JButton("Cancelar Venda");
		btnCancelarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.NO_OPTION;
				if (hSaidaId > 0) {
					opcao = JOptionPane.showConfirmDialog(
							null,
							"Deseja Realmente excluir a venda "
									+ String.format("%09d", hSaidaId) + "?",
							"Atenc�o", JOptionPane.YES_NO_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "N�o existe ainda nenhuma venda gerada. Precisa existir ao menos um produto inserido!");
				}
				if (opcao == JOptionPane.YES_OPTION) {
					List produtos = sm.findWhere("header_saida_fk",
							String.valueOf(hSaidaId));

					for (Object o : produtos) {
						Saida s = (Saida) o;
						sm.deleteById(s.getId());
					}

					hsm.deleteById(hSaidaId);

					JOptionPane.showMessageDialog(null,
							"Venda excluida com sucesso");

					resetarSaida();
				}

			}
		});
		panel.add(btnCancelarVenda);

		JLabel label_5 = new JLabel("Total:");
		sl_panel.putConstraint(SpringLayout.SOUTH, label_5, -2,
				SpringLayout.SOUTH, panel);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(label_5);

		lblTotalVenda = new JLabel("R$ 0,00");
		sl_panel.putConstraint(SpringLayout.EAST, label_5, -30,
				SpringLayout.WEST, lblTotalVenda);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblTotalVenda, -2,
				SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblTotalVenda, -10,
				SpringLayout.EAST, panel);
		lblTotalVenda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalVenda.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblTotalVenda);

		JPanel panel_2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_2, -12,
				SpringLayout.NORTH, label_5);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		sl_panel.putConstraint(SpringLayout.WEST, panel_2, 0,
				SpringLayout.WEST, label_2);
		sl_panel.putConstraint(SpringLayout.EAST, panel_2, 0,
				SpringLayout.EAST, btnExcluirProd);
		panel.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));

		String[] colunas = new String[] { "C�d. Produto", "Descri��o", "Qtde",
				"Pre�o Un", "Total" };
		String[][] dataProdutoTable = new String[][] {};

		model = new DefaultTableModel(dataProdutoTable, colunas) {
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
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				table.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				entradaId = Long.parseLong((String) table.getValueAt(
						table.getSelectedRow(), 0));
				selecionarItem();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

				if (entradaId > 0) {
					btnExcluirProd.setEnabled(true);
				}
			}
		});

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(34);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.add(scrollPane, "name_33668483752645");

		JLabel lblProdutosVend = new JLabel("Produtos:");
		sl_panel.putConstraint(SpringLayout.NORTH, panel_2, 3,
				SpringLayout.SOUTH, lblProdutosVend);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblProdutosVend, -223,
				SpringLayout.SOUTH, panel);
		lblProdutosVend.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_panel.putConstraint(SpringLayout.WEST, lblProdutosVend, 0,
				SpringLayout.WEST, label_2);
		panel.add(lblProdutosVend);

		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, panel_1, 0,
				SpringLayout.NORTH, label_3);
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 0,
				SpringLayout.WEST, lblNumNf);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, -30,
				SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_1, -12,
				SpringLayout.WEST, panel_2);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel.add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));

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
							String.valueOf(entradaId));
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
							"N�o h� nenhuma imagem para ser visualizada!");
				}
			}
		});
		picProduto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(picProduto, "name_98043277413625");

		lblNumVenda = new JLabel("000000000");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNumVenda, 0,
				SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, lblNumVenda, 6,
				SpringLayout.EAST, lblNumNf);
		panel.add(lblNumVenda);

		btnFinalizarVenda = new JButton("    * Finalizar Venda *   ");
		sl_panel.putConstraint(SpringLayout.NORTH, btnCancelarVenda, 0, SpringLayout.NORTH, btnFinalizarVenda);
		sl_panel.putConstraint(SpringLayout.EAST, btnCancelarVenda, -6, SpringLayout.WEST, btnFinalizarVenda);
		sl_panel.putConstraint(SpringLayout.NORTH, btnFinalizarVenda, 6,
				SpringLayout.SOUTH, btnExcluirProd);
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				System.out.println(hSaidaId + " " + clienteId);
				if (hSaidaId > 0 && clienteId > 0) {
					HeaderSaida hse = hsm.findOneWhere("id",
							String.valueOf(hSaidaId));
					Cliente ce = cm.findOneWhere("id",
							String.valueOf(clienteId));

					FinalizarVendaDialog fvDiag = new FinalizarVendaDialog(hse, ce, login
							.getLoggedUser());
					fvDiag.setVisible(true);

					if (fvDiag.vendaFinalizada) {
						JOptionPane.showMessageDialog(null,
								"Venda finalizada com sucesso.");
						resetarSaida();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"N�o existe nenhuma N.F. gerada!");
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.EAST, btnFinalizarVenda, 0,
				SpringLayout.EAST, btnExcluirProd);
		panel.add(btnFinalizarVenda);

		lblDescProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
		sl_panel.putConstraint(SpringLayout.WEST, lblDescProduto, 0,
				SpringLayout.WEST, lblNumNf);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblDescProduto, -2,
				SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblDescProduto, 274,
				SpringLayout.WEST, lblNumNf);
		lblDescProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescProduto.setFont(new Font("SansSerif", Font.ITALIC, 14));
		panel.add(lblDescProduto);

		JLabel lblNewLabel = new JLabel("Qtde. Total:");
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, -2,
				SpringLayout.SOUTH, panel);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 0,
				SpringLayout.WEST, label_2);
		panel.add(lblNewLabel);

		lblItensQtdeTotal = new JLabel("0");
		sl_panel.putConstraint(SpringLayout.WEST, lblItensQtdeTotal, 6,
				SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblItensQtdeTotal, -2,
				SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblItensQtdeTotal, 74,
				SpringLayout.EAST, lblNewLabel);
		lblItensQtdeTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblItensQtdeTotal);

	}

	protected void resetarSaida() {
		// TODO
		// TODO
		// TODO
		// TODO
		hSaidaId = 0;
		clienteId = 0;
		picProduto.setIcon(null);
		lblNumVenda.setText("000000000");
		lblNomeCliente.setText("Selecione um cliente        >>>");
		lblItensQtdeTotal.setText("0");
		lblTotalVenda.setText("R$ 0,00");
		lblDescProduto.setText("Descri��o do Produto");
		txtCodigoProduto.setText("");
		btnExcluirProd.setEnabled(false);
		btnFinalizarVenda.setEnabled(false);
		panel.setVisible(false);
		while (table.getRowCount() > 0) {
			((DefaultTableModel) table.getModel()).removeRow(0);
		}
		
		// HashMap<Long, Double> total = new HashMap<Long, Double>();
		// HashMap<Long, Integer> itensQtde = new HashMap<Long, Integer>();
		
		total.clear();
		itensQtde.clear();
	}

	protected void removerProdutoTabela() {
		for (int i = 0; i < table.getRowCount(); i += 1) {
			if (entradaId == Long.parseLong((String) table.getValueAt(i, 0))) {
				model.removeRow(i);
			}
		}
	}

	protected void checkProdutoAndInsert(long prodId, LoginController login) {
		int qtde = Integer.parseInt(txtQtde.getText());
		if (qtde > 0) {
			boolean hasEntrada = em.hasEntrada(prodId);

			if (hasEntrada) {
				Entrada ee = em.getEntity();

				boolean hasSaida = sm.hasSaida(prodId);
				int qtdeDisponivel = 0;

				if (hasSaida) {
					qtdeDisponivel = ee.getQuantidate() - ee.getnEncontrado()
							- ee.getDevolvido() - sm.getQtdeSaida();
				} else {
					qtdeDisponivel = ee.getQuantidate() - ee.getnEncontrado()
							- ee.getDevolvido();
				}

				if (qtdeDisponivel >= Integer.parseInt(txtQtde.getText())) {
					if (hSaidaId <= 0) {
						HashMap<String, Object> data = new HashMap();

						data.put("vendedor", login.getLoggedUser());
						data.put("cliente", cm.findOneWhere("id",
								String.valueOf(clienteId)));

						hSaidaId = hsm.createVenda(data);

						JOptionPane
								.showMessageDialog(
										null,
										"Novo n�mero (N.F / S�rie) de venda gerado! ["
												+ String.format("%09d",
														hSaidaId) + "]");

						lblNumVenda.setText(String.format("%09d", hSaidaId));
					}

					entradaId = ee.getId();

					gerarImagem();

					addTProduto(ee);

					HashMap<String, Object> data = new HashMap();

					data.put("entrada", ee);
					data.put("qtde", Integer.parseInt(txtQtde.getText()));
					data.put("dataSaida",
							new Timestamp(System.currentTimeMillis()));
					data.put("headerSaida",
							hsm.findOneWhere("id", String.valueOf(hSaidaId)));

					sm.createSaida(data);

					txtCodigoProduto.setText("");
					txtQtde.setText("1");
					
					btnFinalizarVenda.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"N�o h� quantidade dispon�vel deste produto");
				}

			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Erro ao tentar localizar o produto. Poss�veis causas: o c�digo pode ser inv�lido ou o produto n�o se encontra em situa��o dispon�vel.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade inv�lida");
		}

	}

	private void gerarImagem() {
		// TODO Auto-generated method stub
		Entrada eSelecionada = em.findOneWhere("id", String.valueOf(entradaId));

		Blob blob_img = (eSelecionada).getImagemBlob();

		try {
			BufferedImage bi = MyImageUtil.setMaxWidthHeight(blob_img,
					picProduto.getWidth(), picProduto.getHeight());
			drawPicture(bi);
			lblDescProduto.setText(eSelecionada.getDescricao());
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

	private void addTProduto(Entrada entrada) {
		String[] novaLinha = new String[] {
				String.valueOf(entrada.getId()),
				entrada.getDescricao(),
				txtQtde.getText(),
				String.valueOf(entrada.getVenda()),
				String.valueOf(Integer.parseInt(txtQtde.getText())
						* entrada.getVenda()) };
		boolean isUpdated = false;
		int linhaSelecionada = 0;

		long id = entrada.getId();
		double subtotal = 0;
		int itemQtde = 0;

		for (int i = 0; i < table.getRowCount(); i += 1) {
			if (entrada.getId() == Integer.parseInt((String) table.getValueAt(
					i, 0))) {
				isUpdated = true;
				linhaSelecionada = i;
			}
		}

		if (isUpdated) {
			int oldQtde = Integer.parseInt((String) table.getValueAt(
					linhaSelecionada, 2));
			int totalQtde = oldQtde + Integer.parseInt(txtQtde.getText());
			subtotal = totalQtde * entrada.getVenda();
			itemQtde = totalQtde;
			model.removeRow(linhaSelecionada);
			model.insertRow(linhaSelecionada, novaLinha);
			model.setValueAt(String.valueOf(totalQtde), linhaSelecionada, 2);
			model.setValueAt(String.valueOf(subtotal), linhaSelecionada, 4);
		} else {
			model.addRow(novaLinha);
			subtotal = Double.parseDouble(String.valueOf(Integer
					.parseInt(txtQtde.getText()) * entrada.getVenda()));
			itemQtde = Integer.parseInt(txtQtde.getText());
		}

		double vendaTotal = calcTotal(id, subtotal);
		int itensQtdeTotal = calcQtdeTotal(id, itemQtde);

		lblTotalVenda.setText("R$ "
				+ String.format("%.2f", (double) vendaTotal));
		lblItensQtdeTotal.setText(String.valueOf(itensQtdeTotal));

		// if(table.getRowCount() == 1) {
		// RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
		// public boolean include(Entry entry) {
		// Integer population = (Integer) entry.getValue(0);
		// return population.intValue() > 0;
		// }
		// };
		// TableRowSorter<TableModel> sorter = new
		// TableRowSorter<TableModel>(model);
		// sorter.setRowFilter(filter);
		// table.setRowSorter(sorter);
		// }
	}

	private double calcTotal(long id, double subtotal) {

		this.total.put(id, subtotal);

		double t = 0;

		for (Double sT : this.total.values()) {
			t += sT;
		}

		return t;
	}

	private double calcTotalRem(long id) {

		this.total.remove(id);

		double t = 0;

		for (Double sT : this.total.values()) {
			t += sT;
		}

		return t;
	}

	private int calcQtdeTotal(long id, int itemQtde) {
		this.itensQtde.put(id, itemQtde);
		int iQtde = 0;

		for (Integer q : this.itensQtde.values()) {
			iQtde += q;
		}

		return iQtde;
	}

	private int calcQtdeTotalRem(long id) {
		this.itensQtde.remove(id);
		int iQtde = 0;

		for (Integer q : this.itensQtde.values()) {
			iQtde += q;
		}

		return iQtde;
	}

	private void verificarProduto(final LoginController login) {
		if (!txtCodigoProduto.getText().trim().isEmpty()) {

			long prodId = Long.parseLong(txtCodigoProduto.getText());

			checkProdutoAndInsert(prodId, login);
		}
	}

	private void selecionarItem() {
		Entrada eSelecionada = em.findOneWhere("id", String.valueOf(entradaId));

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

		popularEntrada();
	}

	private void popularEntrada() {
		// TODO Auto-generated method stub

	}
}
