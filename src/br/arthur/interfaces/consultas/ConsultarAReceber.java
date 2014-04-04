package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import br.arthur.entities.ContaReceber;
import br.arthur.models.ContaReceberModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.utils.ButtonDoubleValueEditorUtil;
import br.arthur.utils.ButtonRendererUtil;
import br.arthur.utils.CheckBoxHeaderUtil;

public class ConsultarAReceber extends JInternalFrame {
	private JTextField txtCliente;
	private JTextField txtDataVencInicio;
	private JTextField txtDataVencFim;

	private Object colNames[] = { 
			"Cód. NF/Série", // 0
			"Cód. Duplicata", // 1
			"Data Venda", // 2
			"Cliente", // 3
			"Vencimento", // 4
			"Valor Parc.", // 5
			"Valor Total", // 6
			"Parcela", // 7
			"Total Parc.", // 8
			"", // 9
			"Valor Pago" // 10
	};

	private Object[][] data = {};
	private DefaultTableModel dtm;
	private JTable table;
	private JTextField txtNumNf;
	private JRadioButton rdbtnRecebidos;
	private JRadioButton rdbtnNoRecebidos;
	private JRadioButton rdbtnTodos;
	
	private HeaderSaidaModel hsm;
	private ContaReceberModel crm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarAReceber frame = new ConsultarAReceber();
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
	public ConsultarAReceber() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Consultar Contas \u00E0 Receber");
		setBounds(100, 100, 1128, 455);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JLabel lblCliente = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 16,
				SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblCliente);

		txtCliente = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCliente, 6, SpringLayout.EAST, lblCliente);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);

		JLabel label = new JLabel("Vencimento:");
		springLayout.putConstraint(SpringLayout.EAST, txtCliente, -6,
				SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.NORTH, label, 16,
				SpringLayout.NORTH, getContentPane());
		getContentPane().add(label);

		try {
			txtDataVencInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.NORTH, txtCliente, 0, SpringLayout.NORTH, txtDataVencInicio);
		springLayout.putConstraint(SpringLayout.NORTH, txtDataVencInicio, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtDataVencInicio, 373,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label, -6,
				SpringLayout.WEST, txtDataVencInicio);
		txtDataVencInicio.setColumns(10);
		getContentPane().add(txtDataVencInicio);

		JLabel label_1 = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 16,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1, 456,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtDataVencInicio, -6,
				SpringLayout.WEST, label_1);
		getContentPane().add(label_1);

		try {
			txtDataVencFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.NORTH, txtDataVencFim, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtDataVencFim, 6,
				SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.EAST, txtDataVencFim, -560,
				SpringLayout.EAST, getContentPane());
		txtDataVencFim.setColumns(10);
		getContentPane().add(txtDataVencFim);

		JButton btnNewButton = new JButton("Filtrar Resultados");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 820, SpringLayout.WEST, getContentPane());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10,
				SpringLayout.NORTH, getContentPane());
		getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 44,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10,
				SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);

		JButton btnAtualizar = new JButton("ATUALIZAR");
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 966, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -6, SpringLayout.WEST, btnAtualizar);
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizar, -1, SpringLayout.NORTH, txtCliente);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizar, -10, SpringLayout.EAST, getContentPane());
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> newDuplicata = new HashMap<String, Object>();
				HashMap<String, Object> oldDuplicata = new HashMap<String, Object>();
				crm = new ContaReceberModel();
				
				for (int i = 0; i < table.getRowCount(); i += 1) {

					double valorPago = 0.0;
					
					long idCodVenda = Long.parseLong((String) table.getValueAt(i, 0));
					long idDuplicata = (int) table.getValueAt(i, 1);					
							
					// verificar se checado para receber!
					boolean pagarIntegral = (boolean) table.getValueAt(i, 9);
					
					byte totalParcela = (byte) table.getValueAt(i, 8);

					if (pagarIntegral) {
						// atualizar duplicata...
						valorPago = (double) table.getValueAt(i, 5);
						
						// setar como parcela paga e valor tb
						oldDuplicata.put("valor", valorPago);
						oldDuplicata.put("pagto", true);						
						
						crm.salvarDuplicataReceber(idDuplicata, oldDuplicata);
						
					} else {
						System.out.println("Não será pago INTEGRALMENTE...");
						
						if (table.getValueAt(i, 10).getClass().getName()
								.contains("String")) {
							valorPago = Double.parseDouble((String) table
									.getValueAt(i, 10));
						} else {
							valorPago = (Double) table.getValueAt(i, 10);
						}

						Double valorParcel = (Double) table.getValueAt(i, 5);

						if (valorPago < valorParcel && valorPago > 0.0) {
							int opcao;
							opcao = JOptionPane
									.showConfirmDialog(
											null,
											"ATENÇÃO: O valor pago é menor do que a parcela devida para a duplicata CÓD.: "
													+ String.valueOf(table
															.getValueAt(i, 0))
													+ " ! Deseja que o sistema crie uma duplicata para armazenar o valor restante?",
											"Atencão",
											JOptionPane.YES_NO_OPTION);

							if (opcao == JOptionPane.YES_OPTION) {
								String days = null;

								boolean isPushed = true;

								while (isPushed) {

									days = JOptionPane
											.showInputDialog("Daqui a quantos dias será o novo vencimento?");
									
									if (days == null || days.isEmpty()) {
										isPushed = false;
									}

									try {
										int d = Integer.parseInt(days);
										isPushed = false;
										if (d > 0) {
											// adicionar mais um número no total de parcela
											System.out.println(totalParcela+1);
											System.out.println(idCodVenda);
											
											
											HeaderSaidaModel.adicionarParcela(idCodVenda, (byte) (totalParcela + 1));

											// adicionar a nova parcela com o valor da diferença
											newDuplicata.put("headerSaida", HeaderSaidaModel.findOneWhere("id", String.valueOf(idCodVenda)));
											
											String inicioDate = (String) table.getValueAt(i, 4);
											DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
											
											Calendar venc = Calendar.getInstance();
											try {
												venc.setTime(df.parse(inicioDate));
											} catch (ParseException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
											venc.add(Calendar.DATE, d);
											
											newDuplicata.put("dataVencimento", new java.sql.Date(venc.getTime().getTime()));
											
											newDuplicata.put("valor", valorPago);
	
											newDuplicata.put("parcela", (byte) (totalParcela + 1));
											newDuplicata.put("pagto", false);
											
											
											crm.criarContaReceber(newDuplicata);
											
											// setar a antiga com o valor pago e também com pagto true
											double oldValorPago = valorParcel - valorPago;
											oldDuplicata.put("valor", oldValorPago);
											oldDuplicata.put("pagto", true);						
											
											crm.salvarDuplicataReceber(idDuplicata, oldDuplicata);
											
											// atualizar tabela
											JOptionPane.showMessageDialog(null, "Atualizada...");
										}
									} catch (NumberFormatException ex) {
										ex.printStackTrace();
									}
								}

								

							}
						}
					}
					
				}
				
				popularTabela();
			}
		});
		getContentPane().add(btnAtualizar);
		
		dtm = new DefaultTableModel(data, colNames) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};

		table = new JTable();
		
		popularTabela();

		scrollPane.setViewportView(table);

		rdbtnRecebidos = new JRadioButton("Recebidos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnRecebidos, -1, SpringLayout.NORTH, lblCliente);
		getContentPane().add(rdbtnRecebidos);

		rdbtnNoRecebidos = new JRadioButton("N\u00E3o recebidos");
		springLayout.putConstraint(SpringLayout.EAST, rdbtnRecebidos, -6, SpringLayout.WEST, rdbtnNoRecebidos);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNoRecebidos, -1, SpringLayout.NORTH, lblCliente);
		getContentPane().add(rdbtnNoRecebidos);

		rdbtnTodos = new JRadioButton("Todos");
		springLayout.putConstraint(SpringLayout.EAST, rdbtnNoRecebidos, -6, SpringLayout.WEST, rdbtnTodos);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnTodos, -1, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.EAST, rdbtnTodos, -6, SpringLayout.WEST, btnNewButton);
		getContentPane().add(rdbtnTodos);

		ButtonGroup groupRecebidos = new ButtonGroup();
		groupRecebidos.add(rdbtnRecebidos);
		groupRecebidos.add(rdbtnNoRecebidos);
		groupRecebidos.add(rdbtnTodos);

		rdbtnTodos.setSelected(true);
		
		txtNumNf = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtNumNf, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtNumNf, -971,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 6,
				SpringLayout.EAST, txtNumNf);
		getContentPane().add(txtNumNf);
		txtNumNf.setColumns(10);

		JLabel lblNumNf = new JLabel("Num. NF:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumNf, 16,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtNumNf, 6,
				SpringLayout.EAST, lblNumNf);
		springLayout.putConstraint(SpringLayout.WEST, lblNumNf, 0,
				SpringLayout.WEST, scrollPane);
		getContentPane().add(lblNumNf);

		TableColumn tcCheckBox = table.getColumnModel().getColumn(9);
		tcCheckBox.setCellEditor(table.getDefaultEditor(Boolean.class));
		tcCheckBox.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		tcCheckBox.setHeaderRenderer(new CheckBoxHeaderUtil(
				new MyItemListener(), "Receber Todos"));

		TableColumn tcButton = table.getColumnModel().getColumn(10);
		tcButton.setCellRenderer(new ButtonRendererUtil());
		tcButton.setCellEditor(new ButtonDoubleValueEditorUtil(new JCheckBox()));
	}

	private void popularTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		    	dtm.removeRow(i);
		    }
		}
		
		for (Object o : ContaReceberModel.findAll()) {
			ContaReceber cr = (ContaReceber) o;

			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(String.format("%09d", cr.getHeaderSaida().getId()));
			oneRow.add(cr.getId());			
			oneRow.add(df.format(cr.getHeaderSaida().getDataVenda()));
			oneRow.add(cr.getHeaderSaida().getCliente().getNome());
			oneRow.add(df.format(cr.getDataVencimento()));
			oneRow.add(cr.getValor());
			oneRow.add(cr.getHeaderSaida().getTotalVenda());
			oneRow.add(cr.getParcela());
			oneRow.add(cr.getHeaderSaida().getTotalParcela());
			oneRow.add(cr.isPagto());

			if (cr.isPagto()) {
				oneRow.add(cr.getValor());
			} else {
				oneRow.add(0.00);
			}

			dtm.addRow(oneRow);
		}
		
		table.setModel(dtm);
	}
	
	private HashMap<String, Object> verificarDataFiltro() {
		HashMap<String, Object> filtro = new HashMap<String, Object>();
		
		if (!txtNumNf.getText().trim().isEmpty()) {
			filtro.put("numNf", txtNumNf.getText());
		}
		
		if (!txtCliente.getText().trim().isEmpty()) {
			filtro.put("cliente", txtCliente.getText());
		}

		if (!txtDataVencInicio.getText().trim().isEmpty() && txtDataVencInicio.getText().trim().length() == 10) {
			filtro.put("dataVencInicio", txtDataVencInicio.getText());
		}
		
		if (!txtDataVencFim.getText().trim().isEmpty() && txtDataVencFim.getText().trim().length() == 10) {
			filtro.put("dataVencFim", txtDataVencFim.getText());
		}
		
		return filtro;
	}
	
	protected void filtrar() {
		RowFilter<Object, Object> f = new RowFilter<Object, Object>() {
			public boolean include(Entry entry) {
				HashMap<String, Object> filter = verificarDataFiltro();
				boolean isValid = true;
				
				SimpleDateFormat formatter = new SimpleDateFormat(
						"dd/MM/yyyy");
				
				// TODO
				
				if (filter.containsKey("numNf")) {
					isValid = (Long.parseLong((String) entry.getValue(0)) == (Long.parseLong((String) filter.get("numNf"))) && isValid);
				}

				if (filter.containsKey("cliente")) {
					isValid = (((String) entry.getValue(3)).toLowerCase().contains(((String) filter.get("cliente")).toLowerCase()) && isValid);
				}

				if (filter.containsKey("dataVencInicio")) {
					
					try {
						java.util.Date dataInicioTabela = (java.util.Date) formatter.parse((String) entry
								.getValue(4));
						java.util.Date dataInicioFiltro = (java.util.Date) formatter.parse((String) filter
								.get("dataVencInicio"));

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

				if (filter.containsKey("dataVencFim")) {
					
					try {
						java.util.Date dataVencimentoTabela = (java.util.Date) formatter
								.parse((String) entry.getValue(4));
						java.util.Date dataVencimentoFiltro = (java.util.Date) formatter
								.parse((String) filter.get("dataVencFim"));

						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();

						cal1.setTime(dataVencimentoTabela);
						cal2.setTime(dataVencimentoFiltro);

						isValid = ((cal1.before(cal2) || cal1.equals(cal2)) && isValid);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (!rdbtnTodos.isSelected()) {
					if (rdbtnRecebidos.isSelected()) {
						isValid = (boolean) entry.getValue(9) && isValid;
					} else {
						isValid = !(boolean) entry.getValue(9) && isValid;
					}
				}

				return isValid;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
		sorter.setRowFilter(f);
		table.setRowSorter(sorter);
	}

	class MyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if (source instanceof AbstractButton == false)
				return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for (int x = 0, y = table.getRowCount(); x < y; x++) {
				table.setValueAt(new Boolean(checked), x, 9);
			}
		}
	}
}

