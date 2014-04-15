package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import br.arthur.entities.FormaPagto;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.User;
import br.arthur.models.ContaPagarModel;
import br.arthur.models.ContaReceberModel;
import br.arthur.models.FormaPagtoModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.models.SaidaModel;
import br.arthur.models.UserModel;
import br.arthur.utils.ButtonConfirmOptionEditorUtil;
import br.arthur.utils.ButtonRendererUtil;

public class ConsultarVendas extends JInternalFrame {
	private JTextField txtCodVenda;
	private JTextField txtCliente;
	private JTextField txtDataInicio;
	private JTextField txtDataFim;
	private JComboBox cmbVendedor;
	private JComboBox cmbFormaPagto;
	
	private Object colNames[] = {
		"Cód. Venda",
		"Vendedor",
		"Cliente",
		"Forma Pagto",
		"Data Venda",
		"Total Venda",		
		"Total Parcelas",
		"EXTORNO"
	};
	
	private Object[][] data = {};
	private DefaultTableModel dtm;
	private JTable table;
	
	private HeaderSaidaModel hsm = new HeaderSaidaModel();
	private ContaReceberModel crm = new ContaReceberModel();
	private ContaPagarModel cpm = new ContaPagarModel();
	private SaidaModel sm = new SaidaModel();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarVendas frame = new ConsultarVendas();
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
	public ConsultarVendas() {
		setTitle("Consultar Vendas");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1119, 429);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("C\u00F3d. Venda:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 16, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		txtCodVenda = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCodVenda, 6, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, txtCodVenda, 89, SpringLayout.EAST, lblNewLabel);
		getContentPane().add(txtCodVenda);
		txtCodVenda.setColumns(10);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		springLayout.putConstraint(SpringLayout.NORTH, lblVendedor, 0, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(lblVendedor);
		
		cmbVendedor = new JComboBox();
		cmbVendedor.addItem("todos");
		Iterator vendedores = UserModel.findAll().iterator();
		while (vendedores.hasNext()) {
			User u = (User) vendedores.next();
			cmbVendedor.addItem(u.getName());
		}
		cmbVendedor.setSelectedItem("todos");
		
		springLayout.putConstraint(SpringLayout.WEST, cmbVendedor, 499, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblVendedor, -6, SpringLayout.WEST, cmbVendedor);
		springLayout.putConstraint(SpringLayout.NORTH, cmbVendedor, -5, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(cmbVendedor);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.EAST, cmbVendedor, -6, SpringLayout.WEST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(lblNewLabel_1);
		
		txtCliente = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCliente, 668, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, txtCliente);
		springLayout.putConstraint(SpringLayout.NORTH, txtCliente, -6, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblFormaPagto = new JLabel("Forma Pagto:");
		springLayout.putConstraint(SpringLayout.EAST, txtCliente, -6, SpringLayout.WEST, lblFormaPagto);
		springLayout.putConstraint(SpringLayout.NORTH, lblFormaPagto, 0, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(lblFormaPagto);
		
		cmbFormaPagto = new JComboBox();
		cmbFormaPagto.addItem("todos");
		Iterator formasPagto = FormaPagtoModel.findAll().iterator();
		while (formasPagto.hasNext()) {
			FormaPagto fp = (FormaPagto) formasPagto.next();
			cmbFormaPagto.addItem(fp.getName());
		}
		cmbFormaPagto.setSelectedItem("todos");
		
		springLayout.putConstraint(SpringLayout.WEST, cmbFormaPagto, 847, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblFormaPagto, -6, SpringLayout.WEST, cmbFormaPagto);
		springLayout.putConstraint(SpringLayout.NORTH, cmbFormaPagto, -5, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(cmbFormaPagto);
		
		JButton btnFiltrarVendas = new JButton("Filtrar Vendas");
		btnFiltrarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrar();
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, cmbFormaPagto, -6, SpringLayout.WEST, btnFiltrarVendas);
		springLayout.putConstraint(SpringLayout.WEST, btnFiltrarVendas, 977, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnFiltrarVendas, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnFiltrarVendas, -6, SpringLayout.NORTH, lblNewLabel);
		getContentPane().add(btnFiltrarVendas);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 44, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtCodVenda, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		dtm = new DefaultTableModel(data, colNames) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { 
					false, false, false,
					false, false, false, 
					false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};
		
		table = new JTable();
		
		popularTabela();
		
		table.getModel().addTableModelListener(new TableModelListener() {

		  public void tableChanged(TableModelEvent e) {
			  if (e.getColumn() == 7) {
				  String val = (String) table.getValueAt(e.getLastRow(), e.getColumn());
				  if (val.contains("aguarde")) {
					  Long idVenda = Long.parseLong((String) table.getValueAt(e.getLastRow(), 0));
					  extornarVenda(idVenda);
				  }
			  }
		  }
	    });
		
		scrollPane.setViewportView(table);
		
		JLabel lblDataVenda = new JLabel("Data Venda:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDataVenda, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblDataVenda, 6, SpringLayout.EAST, txtCodVenda);
		getContentPane().add(lblDataVenda);
		
		try {
			txtDataInicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.WEST, txtDataInicio, 6, SpringLayout.EAST, lblDataVenda);
		springLayout.putConstraint(SpringLayout.SOUTH, txtDataInicio, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, txtDataInicio, -788, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtDataInicio);
		txtDataInicio.setColumns(10);
		
		JLabel lblAt = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, lblAt, 0, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblAt, 6, SpringLayout.EAST, txtDataInicio);
		getContentPane().add(lblAt);
		
		try {
			txtDataFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		springLayout.putConstraint(SpringLayout.NORTH, txtDataFim, -6, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, txtDataFim, 6, SpringLayout.EAST, lblAt);
		springLayout.putConstraint(SpringLayout.EAST, txtDataFim, -15, SpringLayout.WEST, lblVendedor);
		txtDataFim.setColumns(10);
		getContentPane().add(txtDataFim);
		
		TableColumn tcButton = table.getColumnModel().getColumn(7);
		tcButton.setCellRenderer(new ButtonRendererUtil());
		tcButton.setCellEditor(new ButtonConfirmOptionEditorUtil(new JCheckBox()));
		
	}
	
	protected void extornarVenda(Long idVenda) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Realizando o extorno da venda cód.: " + idVenda);
		
		System.out.println(idVenda);
		crm.deleteWhere("headerSaida", String.valueOf(idVenda));
		cpm.deleteWhere("headerVenda", String.valueOf(idVenda));
		sm.devolverWhere("headerSaida", String.valueOf(idVenda));
		hsm.deleteById(idVenda);
		
		popularTabela();
		
		JOptionPane.showMessageDialog(null, "Venda extornada com sucesso!");
	}

	private void popularTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		    	dtm.removeRow(i);
		    }
		}
		
		for (Object o : HeaderSaidaModel.findAll()) {
			HeaderSaida hs = (HeaderSaida) o;

			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(String.format("%09d", hs.getId()));
			oneRow.add(hs.getVendedor().getName());
			oneRow.add(hs.getCliente().getNome());
			
			if (hs.getFormaPagto() == null) {
				oneRow.add("sem registro");
			} else {
				oneRow.add(hs.getFormaPagto().getName());
			}
			
			oneRow.add(df.format(hs.getDataVenda()));
			
			oneRow.add(hs.getTotalVenda());
			oneRow.add(hs.getTotalParcela());
			oneRow.add("EXTORNAR");

			dtm.addRow(oneRow);
		}
		
		table.setModel(dtm);
	}
	
	private HashMap<String, Object> verificarDataFiltro() {
		HashMap<String, Object> filtro = new HashMap<String, Object>();
		
		if (!txtCodVenda.getText().trim().isEmpty()) {
			filtro.put("codVenda", txtCodVenda.getText());
		}
		
		if (!txtDataInicio.getText().trim().isEmpty() && txtDataInicio.getText().trim().length() == 10) {
			filtro.put("dataInicio", txtDataInicio.getText());
		}
		
		if (!txtDataFim.getText().trim().isEmpty() && txtDataFim.getText().trim().length() == 10) {
			filtro.put("dataFim", txtDataFim.getText());
		}
		
		if (!((String) cmbVendedor.getSelectedItem()).contains("todos")) {
			filtro.put("vendedor", (String) cmbVendedor.getSelectedItem());
		}
		
		if (!txtCliente.getText().trim().isEmpty()) {
			filtro.put("cliente", txtCliente.getText());
		}

		if (!((String) cmbFormaPagto.getSelectedItem()).contains("todos")) {
			filtro.put("formaPagto", (String) cmbFormaPagto.getSelectedItem());
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
				
				if (filter.containsKey("codVenda")) {
					isValid = (Long.parseLong((String) entry.getValue(0)) == (Long.parseLong((String) filter.get("codVenda"))) && isValid);
				}

				if (filter.containsKey("dataInicio")) {
					
					try {
						java.util.Date dataInicioTabela = (java.util.Date) formatter.parse((String) entry
								.getValue(4));
						java.util.Date dataInicioFiltro = (java.util.Date) formatter.parse((String) filter
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

				if (filter.containsKey("dataFim")) {
					
					try {
						java.util.Date dataVencimentoTabela = (java.util.Date) formatter
								.parse((String) entry.getValue(4));
						java.util.Date dataVencimentoFiltro = (java.util.Date) formatter
								.parse((String) filter.get("dataFim"));

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
				
				if (filter.containsKey("vendedor")) {
					isValid = (((String) entry.getValue(1)).toLowerCase().contains(((String) filter.get("vendedor")).toLowerCase()) && isValid);
				}
				
				if (filter.containsKey("cliente")) {
					isValid = (((String) entry.getValue(2)).toLowerCase().contains(((String) filter.get("cliente")).toLowerCase()) && isValid);
				}
				
				if (filter.containsKey("formaPagto")) {
					isValid = (((String) entry.getValue(3)).toLowerCase().contains(((String) filter.get("formaPagto")).toLowerCase()) && isValid);
				}

				return isValid;
			}
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
		sorter.setRowFilter(f);
		table.setRowSorter(sorter);
	}
}
