package br.arthur.interfaces.cadastros;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.arthur.controllers.LoginController;
import br.arthur.entities.Cliente;
import br.arthur.entities.Entrada;
import br.arthur.entities.HeaderSaida;
import br.arthur.interfaces.cadastros.dialogs.ClienteDialog;
import br.arthur.interfaces.cadastros.dialogs.TestFinVen;
import br.arthur.models.ClienteModel;
import br.arthur.models.EntradaModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.models.SaidaModel;

public class CadastroSaida extends JInternalFrame {
	EntradaModel em = new EntradaModel();
	ClienteModel cm = new ClienteModel();
	SaidaModel sm = new SaidaModel();
	HeaderSaidaModel hsm = new HeaderSaidaModel();
	
	private	JPanel panel;
	
	private JLabel lblNomeCliente;
	private JLabel lblNumVenda;
	
	private JTextField txtCodigoProduto;
	private JTextField txtQtde;
	
	private DefaultTableModel model;
	private JTable table;
	private Vector tableData = new Vector();
	
	private int hSaidaId = 0;
	private int clienteId = 0;
	
	
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
		setTitle("Cadastro de Venda");
		setBounds(100, 100, 701, 431);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblData);
		
		DateFormat df = new SimpleDateFormat(
				"dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		
		JLabel label = new JLabel(df.format(today));
		springLayout.putConstraint(SpringLayout.NORTH, lblData, 0, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, lblData, -8, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label, 64, SpringLayout.WEST, getContentPane());
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(label);
		
		JLabel lblCliente = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 10, SpringLayout.NORTH, getContentPane());
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblCliente);
		
		lblNomeCliente = new JLabel("Selecione um cliente!");
		springLayout.putConstraint(SpringLayout.NORTH, lblNomeCliente, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCliente, -6, SpringLayout.WEST, lblNomeCliente);
		springLayout.putConstraint(SpringLayout.WEST, lblNomeCliente, 237, SpringLayout.WEST, getContentPane());
		lblNomeCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
		getContentPane().add(lblNomeCliente);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscarCliente, 4, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBuscarCliente, 422, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBuscarCliente, -23, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNomeCliente, -6, SpringLayout.WEST, btnBuscarCliente);
		getContentPane().add(btnBuscarCliente);
		panel = new JPanel();
		panel.setVisible(false);
		panel.setBorder(new TitledBorder(null, "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.SOUTH, btnBuscarCliente);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 679, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
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
						lblNomeCliente.setText(cm.findOneWhere("id", String.valueOf(clienteId)).getNome());
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Não existe ainda nenhum cliente registrado no sistema!");
				}
			}
		});
		
		JLabel lblNumNf = new JLabel("Num.\u00BA N.F. / S\u00E9rie: ");
		lblNumNf.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, lblNumNf, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNumNf, 0, SpringLayout.WEST, panel);
		panel.add(lblNumNf);
		
		JLabel label_2 = new JLabel("C\u00F3digo de Barras:");
		label_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, label_2, 0, SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, label_2, 185, SpringLayout.EAST, lblNumNf);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Quantidade:");
		sl_panel.putConstraint(SpringLayout.EAST, label_3, 0, SpringLayout.EAST, label_2);
		panel.add(label_3);
		
		txtCodigoProduto = new JTextField();
		txtCodigoProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				
				if(e.getKeyCode() == 10) {
					if(!txtCodigoProduto.getText().trim().isEmpty()) {
						int prodId = Integer.parseInt(txtCodigoProduto.getText());
						checkProduto(prodId, login);
					}
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, txtCodigoProduto, -6, SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, txtCodigoProduto, 6, SpringLayout.EAST, label_2);
		txtCodigoProduto.setColumns(10);
		panel.add(txtCodigoProduto);
		
		txtQtde = new JTextField();
		txtQtde.setText("1");
		sl_panel.putConstraint(SpringLayout.NORTH, txtQtde, 0, SpringLayout.SOUTH, txtCodigoProduto);
		sl_panel.putConstraint(SpringLayout.WEST, txtQtde, 6, SpringLayout.EAST, label_3);
		sl_panel.putConstraint(SpringLayout.NORTH, label_3, 6, SpringLayout.NORTH, txtQtde);
		txtQtde.setColumns(10);
		panel.add(txtQtde);
		
		JButton button = new JButton("Atualizar");
		sl_panel.putConstraint(SpringLayout.NORTH, button, -6, SpringLayout.NORTH, label_3);
		sl_panel.putConstraint(SpringLayout.WEST, button, 6, SpringLayout.EAST, txtQtde);
		panel.add(button);
		
		JButton btnBuscar = new JButton("Buscar");
		sl_panel.putConstraint(SpringLayout.EAST, button, 0, SpringLayout.EAST, btnBuscar);
		sl_panel.putConstraint(SpringLayout.NORTH, btnBuscar, -6, SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, btnBuscar, 6, SpringLayout.EAST, txtCodigoProduto);
		sl_panel.putConstraint(SpringLayout.EAST, btnBuscar, 0, SpringLayout.EAST, panel);
		panel.add(btnBuscar);
		
		JButton button_4 = new JButton("Cancelar Venda");
		sl_panel.putConstraint(SpringLayout.NORTH, button_4, 6, SpringLayout.SOUTH, txtQtde);
		sl_panel.putConstraint(SpringLayout.WEST, button_4, 0, SpringLayout.WEST, txtCodigoProduto);
		panel.add(button_4);
		
		JButton button_6 = new JButton(">>");
		panel.add(button_6);
		
		JButton button_7 = new JButton(">");
		sl_panel.putConstraint(SpringLayout.NORTH, button_7, 0, SpringLayout.NORTH, button_6);
		sl_panel.putConstraint(SpringLayout.EAST, button_7, -6, SpringLayout.WEST, button_6);
		panel.add(button_7);
		
		JButton button_8 = new JButton("<");
		panel.add(button_8);
		
		JButton button_9 = new JButton("<<");
		sl_panel.putConstraint(SpringLayout.WEST, button_8, 6, SpringLayout.EAST, button_9);
		sl_panel.putConstraint(SpringLayout.WEST, button_9, 0, SpringLayout.WEST, lblNumNf);
		panel.add(button_9);
		
		JLabel label_5 = new JLabel("Total:");
		sl_panel.putConstraint(SpringLayout.SOUTH, label_5, -2, SpringLayout.SOUTH, panel);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("R$ 56,56");
		sl_panel.putConstraint(SpringLayout.SOUTH, label_6, -2, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, label_5, -28, SpringLayout.WEST, label_6);
		sl_panel.putConstraint(SpringLayout.EAST, label_6, -10, SpringLayout.EAST, panel);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel.add(label_6);
		
		JPanel panel_2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_2, -12, SpringLayout.NORTH, label_5);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sl_panel.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, label_2);
		sl_panel.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, button);
		panel.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
	
		
		String[] colunas = new String[] { "Cód. Produto", "Descrição", "Qtde",
				"Preço Un", "Subtotal"};
		String[][] dataProdutoTable = new String[][] {};
		
	    model = new DefaultTableModel(dataProdutoTable, colunas) {
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
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
	    
		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.add(scrollPane, "name_33668483752645");
		
		JLabel lblProdutosVend = new JLabel("Produtos:");
		sl_panel.putConstraint(SpringLayout.SOUTH, lblProdutosVend, -223, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, panel_2, 3, SpringLayout.SOUTH, lblProdutosVend);
		lblProdutosVend.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_panel.putConstraint(SpringLayout.WEST, lblProdutosVend, 0, SpringLayout.WEST, label_2);
		panel.add(lblProdutosVend);
		
		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, -36, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, button_6, 6, SpringLayout.SOUTH, panel_1);
		sl_panel.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.EAST, button_6, 0, SpringLayout.EAST, panel_1);
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_1, -12, SpringLayout.WEST, panel_2);
		sl_panel.putConstraint(SpringLayout.NORTH, button_9, 6, SpringLayout.SOUTH, panel_1);
		sl_panel.putConstraint(SpringLayout.NORTH, button_8, 6, SpringLayout.SOUTH, panel_1);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JLabel lblImg = new JLabel("Imagem do Produto");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblImg, "name_98043277413625");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.NORTH, button_6);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 6, SpringLayout.EAST, button_8);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, 190, SpringLayout.WEST, panel);
		panel.add(lblNewLabel_1);
		
		lblNumVenda = new JLabel("000000");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNumVenda, 0, SpringLayout.NORTH, lblNumNf);
		sl_panel.putConstraint(SpringLayout.WEST, lblNumVenda, 6, SpringLayout.EAST, lblNumNf);
		panel.add(lblNumVenda);
		
		JButton btnFinalizarVenda = new JButton("* Finalizar Venda *");
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO
				System.out.println("aksdahshdjqkwe");
				System.out.println(hSaidaId + " " + clienteId);
				if (hSaidaId > 0 && clienteId > 0) {
					HeaderSaida hse = hsm.findOneWhere("id", String.valueOf(hSaidaId));
					Cliente ce = cm.findOneWhere("id", String.valueOf(clienteId));
					
					TestFinVen fvDiag = new TestFinVen(hse, ce, login.getLoggedUser());
					fvDiag.setVisible(true);
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnFinalizarVenda, 6, SpringLayout.SOUTH, txtQtde);
		sl_panel.putConstraint(SpringLayout.EAST, btnFinalizarVenda, 0, SpringLayout.EAST, button);
		panel.add(btnFinalizarVenda);

	}

	protected void checkProduto(int prodId, LoginController login) {
	/*	int qtde = Integer.parseInt(txtQtde.getText());
		if (qtde > 0) {
			if(em.hasEntrada(prodId)) {
				Entrada ee = em.getEntity();
				
				boolean hasSaida = sm.hasSaida(prodId);
				int qtdeDisponivel = 0;
				
				if(hasSaida) {
					qtdeDisponivel = ee.getQuantidate() - sm.getQtdeSaida();
				} else {
					qtdeDisponivel = ee.getQuantidate();
				}
				if(qtdeDisponivel >= Integer.parseInt(txtQtde.getText())) {
					if(hSaidaId <= 0) {
						HashMap<String, Object> data = new HashMap();
						
						data.put("vendedor", login.getLoggedUser());
						data.put("cliente", cm.findOneWhere("id", String.valueOf(clienteId)));
						
						hSaidaId = hsm.createVenda(data);
						
						JOptionPane.showMessageDialog(null, "Novo número (N.F / Série) de venda gerado! [" + hSaidaId + "]");
						
						lblNumVenda.setText(String.valueOf(hSaidaId));
					}
					
					addTProduto(ee);
					
					HashMap<String, Object> data = new HashMap();					
					
					data.put("entrada", ee);
					data.put("qtde", Integer.parseInt(txtQtde.getText()));
					data.put("dataSaida", new Timestamp(System.currentTimeMillis()));
					data.put("headerSaida", hsm.findOneWhere("id", String.valueOf(hSaidaId)));
					
					sm.createSaida(data);
					
					txtCodigoProduto.setText("");
					txtQtde.setText("1");
				} else {
					JOptionPane.showMessageDialog(null, "Não há quantidade disponível deste produto");
				}
				
			} 
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade inválida");
		}
*/
	}

	private void addTProduto(Entrada entrada) {
		String[] novaLinha = new String[] { String.valueOf(entrada.getId()), entrada.getDescricao(), txtQtde.getText(), String.valueOf(entrada.getVenda()), String.valueOf(Integer.parseInt(txtQtde.getText()) * entrada.getVenda()) };
		boolean isUpdated = false;
		int linhaSelecionada = 0;
		
		for (int i = 0; i < table.getRowCount(); i += 1) {
			if(entrada.getId() == Integer.parseInt((String) table.getValueAt(i, 0))) {
				isUpdated = true;
				linhaSelecionada = i;
			}
		}
		
		if(isUpdated) {
			int oldQtde = Integer.parseInt((String) table.getValueAt(linhaSelecionada, 2));
			int totalQtde = oldQtde + Integer.parseInt(txtQtde.getText());
			double subtotal = totalQtde * entrada.getVenda();
			model.removeRow(linhaSelecionada);
			model.insertRow(linhaSelecionada, novaLinha);
			model.setValueAt(String.valueOf(totalQtde), linhaSelecionada, 2);
			model.setValueAt(String.valueOf(subtotal), linhaSelecionada, 4);
		} else {
			model.addRow(novaLinha);
		}
//		
//		if(table.getRowCount() == 1) {
//			RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
//			      public boolean include(Entry entry) {
//			        Integer population = (Integer) entry.getValue(0);
//			        return population.intValue() > 0;
//			      }
//			    };
//		    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
//		    sorter.setRowFilter(filter);
//		    table.setRowSorter(sorter);
//		}
	}
}
