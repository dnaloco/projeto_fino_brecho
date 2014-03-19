package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.arthur.entities.ContaReceber;
import br.arthur.models.ContaReceberModel;
import br.arthur.utils.CheckBoxHeaderUtil;

public class ConsultarAReceber extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private Object colNames[] = {
			"Cód. NF/Série", // 0
			"Data Venda", // 1
			"Vencimento", // 2
			"Valor Parc.", // 3
			"Valor Total", // 4
			"Parcela", // 5
			"Total Parc.", // 6
			"" // 7
	};
	
	private Object[][] data = {};
	private DefaultTableModel dtm;
	private JTable table;

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
		setTitle("Consultar Contas \u00E0 Receber");
		setBounds(100, 100, 990, 455);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCliente = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 16, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCliente);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblCliente);
		springLayout.putConstraint(SpringLayout.EAST, textField, 103, SpringLayout.EAST, lblCliente);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Vencimento:");
		springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, textField);
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -6, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -666, SpringLayout.EAST, getContentPane());
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 6, SpringLayout.EAST, textField_1);
		getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -6, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -560, SpringLayout.EAST, getContentPane());
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Filtrar Resultados");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, -6, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -156, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 6, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAtualizar, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizar, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(btnAtualizar);
		
		dtm = new DefaultTableModel(data, colNames) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { 
					false, false, false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};
		table = new JTable(dtm);
		
		scrollPane.setViewportView(table);
		
		JRadioButton rdbtnRecebidos = new JRadioButton("Recebidos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnRecebidos, -1, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnRecebidos, 6, SpringLayout.EAST, textField_2);
		getContentPane().add(rdbtnRecebidos);
		
		JRadioButton rdbtnNoRecebidos = new JRadioButton("N\u00E3o recebidos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNoRecebidos, -1, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNoRecebidos, 6, SpringLayout.EAST, rdbtnRecebidos);
		getContentPane().add(rdbtnNoRecebidos);
		
		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 6, SpringLayout.EAST, rdbtnTodos);
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnTodos, -1, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnTodos, 6, SpringLayout.EAST, rdbtnNoRecebidos);
		getContentPane().add(rdbtnTodos);
		
		ButtonGroup groupRecebidos = new ButtonGroup();
		groupRecebidos.add(rdbtnRecebidos);
		groupRecebidos.add(rdbtnNoRecebidos);
		groupRecebidos.add(rdbtnTodos);		
		
		TableColumn tc = table.getColumnModel().getColumn(7);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		tc.setHeaderRenderer(new CheckBoxHeaderUtil(new MyItemListener(), "Receber Todos"));
		
		popularTabela();
	}
	
	private void popularTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Object o : ContaReceberModel.findAll()) {
			ContaReceber cr = (ContaReceber) o;

			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(cr.getHeaderSaida().getId());
			oneRow.add(df.format(cr.getHeaderSaida().getDataVenda()));
			oneRow.add(df.format(cr.getDataVencimento()));
			oneRow.add(cr.getValor());
			oneRow.add(cr.getHeaderSaida().getTotalVenda());
			oneRow.add(cr.getParcela());
			oneRow.add(cr.getTotalParcela());
			oneRow.add(cr.isPagto());
			
			dtm.addRow(oneRow);
		}
	}

	class MyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if (source instanceof AbstractButton == false)
				return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for (int x = 0, y = table.getRowCount(); x < y; x++) {
				table.setValueAt(new Boolean(checked), x, 7);
			}
		}
	}
}
