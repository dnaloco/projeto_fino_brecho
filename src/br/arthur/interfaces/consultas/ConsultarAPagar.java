package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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

import br.arthur.entities.ContaPagar;
import br.arthur.models.ContaPagarModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.utils.CheckBoxHeaderUtil;

public class ConsultarAPagar extends JInternalFrame {
	private JTextField txtConsig;
	private JTextField txtVencInicio;
	private JTextField txtVencFim;
	
	private Object colNames[] = {
		"Consignatário", 	// 0
		"Cód. Venda", 		// 1
		"Cód. Duplicata",	// 2
		"Data Pagto", 		// 3
		"Vencimento", 		// 4
		"Comissão",			// 5
		"" 					// 6
	};
	
	private Object[][] data = {};
	
	private DefaultTableModel dtm;
	private JTable table;
	
	private JRadioButton rbPagados;
	private JRadioButton rbNoPagados;
	private JRadioButton rbTodos;
	
	private HeaderSaidaModel hsm;
	private ContaPagarModel cpm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarAPagar frame = new ConsultarAPagar();
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
	public ConsultarAPagar() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Consultar Contas \u00E0 Pagar");
		setBounds(100, 100, 1083, 446);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblConsignatrio = new JLabel("Consignat\u00E1rio:");
		getContentPane().add(lblConsignatrio);
		
		txtConsig = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtConsig, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtConsig, 94, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblConsignatrio, 6, SpringLayout.NORTH, txtConsig);
		springLayout.putConstraint(SpringLayout.EAST, lblConsignatrio, -6, SpringLayout.WEST, txtConsig);
		getContentPane().add(txtConsig);
		txtConsig.setColumns(10);
		
		JLabel lblVencimento = new JLabel("Vencimento:");
		springLayout.putConstraint(SpringLayout.NORTH, lblVencimento, 0, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, lblVencimento, 26, SpringLayout.EAST, txtConsig);
		getContentPane().add(lblVencimento);
		
		JLabel lblAt = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, lblAt, 0, SpringLayout.NORTH, lblConsignatrio);
		getContentPane().add(lblAt);
		
		try {
			txtVencInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.WEST, lblAt, 6, SpringLayout.EAST, txtVencInicio);
		springLayout.putConstraint(SpringLayout.NORTH, txtVencInicio, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtVencInicio, 6, SpringLayout.EAST, lblVencimento);
		springLayout.putConstraint(SpringLayout.EAST, txtVencInicio, 87, SpringLayout.EAST, lblVencimento);
		getContentPane().add(txtVencInicio);
		txtVencInicio.setColumns(10);
		
		try {
			txtVencFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.NORTH, txtVencFim, -6, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, txtVencFim, 6, SpringLayout.EAST, lblAt);
		springLayout.putConstraint(SpringLayout.EAST, txtVencFim, 507, SpringLayout.WEST, getContentPane());
		txtVencFim.setColumns(10);
		getContentPane().add(txtVencFim);
		
		JButton btnFiltrarResultados = new JButton("Filtrar Resultados");
		btnFiltrarResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnFiltrarResultados, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnFiltrarResultados, -175, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnFiltrarResultados);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, txtConsig);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 7, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 367, SpringLayout.SOUTH, txtConsig);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		rbPagados = new JRadioButton("Pagados");
		springLayout.putConstraint(SpringLayout.NORTH, rbPagados, -1, SpringLayout.NORTH, lblConsignatrio);
		getContentPane().add(rbPagados);
		
		rbNoPagados = new JRadioButton("N\u00E3o pagados");
		springLayout.putConstraint(SpringLayout.WEST, rbPagados, 6, SpringLayout.EAST, rbNoPagados);
		springLayout.putConstraint(SpringLayout.NORTH, rbNoPagados, -1, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, rbNoPagados, 6, SpringLayout.EAST, txtVencFim);
		getContentPane().add(rbNoPagados);
		
		rbTodos = new JRadioButton("Todos");
		springLayout.putConstraint(SpringLayout.WEST, btnFiltrarResultados, 6, SpringLayout.EAST, rbTodos);
		springLayout.putConstraint(SpringLayout.NORTH, rbTodos, -1, SpringLayout.NORTH, lblConsignatrio);
		springLayout.putConstraint(SpringLayout.WEST, rbTodos, 6, SpringLayout.EAST, rbPagados);
		rbTodos.setSelected(true);
		getContentPane().add(rbTodos);
		
		ButtonGroup grupoPagados = new ButtonGroup();
		grupoPagados.add(rbPagados);
		grupoPagados.add(rbNoPagados);
		grupoPagados.add(rbTodos);
		
		JButton btnAtualizarContas = new JButton("Atualizar Contas");
		btnAtualizarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, Object> duplicata = new HashMap<String, Object>();
				cpm = new ContaPagarModel();
				
				for (int i = 0; i < table.getRowCount(); i += 1) {
					
					long idDuplicata = (int) table.getValueAt(i, 2);
							
					// verificar se checado para pagar!
					boolean pagarConsig = (boolean) table.getValueAt(i, 6);

					if (pagarConsig) {
						
						duplicata.put("pagto", true);						
						
						cpm.salvarDuplicataPagar(idDuplicata, duplicata);
						
					}
					
				}
				
				popularTabela();
			}
		});
		
		dtm = new DefaultTableModel(data, colNames) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		table = new JTable();

		popularTabela();
		
		scrollPane.setViewportView(table);
		
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarContas, 6, SpringLayout.EAST, btnFiltrarResultados);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAtualizarContas, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizarContas, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(btnAtualizarContas);
		
		TableColumn tcCheckBox = table.getColumnModel().getColumn(6);
		tcCheckBox.setCellEditor(table.getDefaultEditor(Boolean.class));
		tcCheckBox.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		tcCheckBox.setHeaderRenderer(new CheckBoxHeaderUtil(new MyItemListener(), "Pagar Todos"));

	}
	
	private void popularTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		    	dtm.removeRow(i);
		    }
		}
		
		for (Object o : ContaPagarModel.findAll()) {
			ContaPagar cp = (ContaPagar) o;

			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(cp.getHeaderVenda().getVendedor().getName());	
			oneRow.add(String.format("%09d", cp.getHeaderVenda().getId()));
			oneRow.add(cp.getId());
			if (cp.getDataPagto() != null) {
				oneRow.add(df.format(cp.getDataPagto()));
			} else {
				oneRow.add("");
			}
			oneRow.add(df.format(cp.getDataVencimento()));
			oneRow.add(cp.getValor());
			oneRow.add(cp.isPagto());

			dtm.addRow(oneRow);
		}
		
		table.setModel(dtm);
	}
	
	private HashMap<String, Object> verificarDataFiltro() {
		HashMap<String, Object> filtro = new HashMap<String, Object>();
		
		if (!txtConsig.getText().trim().isEmpty()) {
			filtro.put("consig", txtConsig.getText());
		}

		if (!txtVencInicio.getText().trim().isEmpty() && txtVencInicio.getText().trim().length() == 10) {
			filtro.put("vencInicio", txtVencInicio.getText());
		}
		
		if (!txtVencFim.getText().trim().isEmpty() && txtVencFim.getText().trim().length() == 10) {
			filtro.put("vencFim", txtVencFim.getText());
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
				
				if (filter.containsKey("consig")) {
					isValid = (((String) entry.getValue(0)).toLowerCase().contains(((String) filter.get("consig")).toLowerCase()) && isValid);
				}

				if (filter.containsKey("vencInicio")) {
					
					java.util.Date dataInicioTabela = null;
					try {
						dataInicioTabela = (java.util.Date) formatter.parse((String) entry
								.getValue(4));
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.util.Date dataInicioFiltro = null;
					try {
						dataInicioFiltro = (java.util.Date) formatter.parse((String) filter
								.get("vencInicio"));
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();

					cal1.setTime(dataInicioTabela);
					cal2.setTime(dataInicioFiltro);

					
					isValid = ((cal1.after(cal2) || cal1.equals(cal2)) && isValid);

				}

				if (filter.containsKey("vencFim")) {
					
					java.util.Date dataVencimentoTabela = null;
					try {
						dataVencimentoTabela = (java.util.Date) formatter
								.parse((String) entry.getValue(4));
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.util.Date dataVencimentoFiltro = null;
					try {
						dataVencimentoFiltro = (java.util.Date) formatter
								.parse((String) filter.get("vencFim"));
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();

					cal1.setTime(dataVencimentoTabela);
					cal2.setTime(dataVencimentoFiltro);

					isValid = ((cal1.before(cal2) || cal1.equals(cal2)) && isValid);
				}

				if (!rbTodos.isSelected()) {
					if (rbPagados.isSelected()) {
						isValid = (boolean) entry.getValue(6) && isValid;
					} else {
						isValid = !(boolean) entry.getValue(6) && isValid;
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
				table.setValueAt(new Boolean(checked), x, 6);
			}
		}
	}
}
