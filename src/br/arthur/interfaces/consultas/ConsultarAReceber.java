package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.arthur.entities.ContaReceber;
import br.arthur.models.ContaReceberModel;
import br.arthur.utils.ButtonEditorUtil;
import br.arthur.utils.ButtonRendererUtil;
import br.arthur.utils.CheckBoxHeaderUtil;

public class ConsultarAReceber extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private Object colNames[] = {
			"Cód. Duplicata", // 0
			"Cód. NF/Série", // 1
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
	private JTextField textField_3;

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
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 16, SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblCliente);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblCliente);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Vencimento:");
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.NORTH, label, 16, SpringLayout.NORTH, getContentPane());
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 373, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label, -6, SpringLayout.WEST, textField_1);
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("at\u00E9");
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 16, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_1, 456, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -6, SpringLayout.WEST, label_1);
		getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -560, SpringLayout.EAST, getContentPane());
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Filtrar Resultados");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -156, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 44, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < table.getRowCount(); i += 1) {
					
					
					Double valorPago = 0.0;
					
					if (table.getValueAt(i, 10).getClass().getName().contains("String")) {
						valorPago = Double.parseDouble((String) table.getValueAt(i, 10));
					} else {
						valorPago = (Double) table.getValueAt(i, 10);
					}

					Double valorParcel = (Double) table.getValueAt(i, 5);
					
					if (valorPago < valorParcel && valorPago > 0.0) {
						int opcao;
						opcao = JOptionPane
								.showConfirmDialog(null,
										"ATENÇÃO: O valor pago é menor do que a parcela devida para a duplicata CÓD.: " + String.valueOf(table.getValueAt(i, 0))+ " ! Deseja que o sistema crie uma duplicata para armazenar o valor restante?", "Atencão",
										JOptionPane.YES_NO_OPTION);
						
						if (opcao == JOptionPane.YES_OPTION) {
							String days = null;
							
							boolean isPushed = true;
	
							while (isPushed) {
								
								days = JOptionPane.showInputDialog("Daqui a quantos dias será o novo vencimento?");
								
								try
								{
									Integer.parseInt(days);
									isPushed = false;
								}
								catch(NumberFormatException ex)
								{
									ex.printStackTrace();
									JOptionPane.showMessageDialog(null, "Esse não é um número válido!");
								}
							}
						}
					}
//					if (entradaId == Long.parseLong((String) jtableProdutos.getValueAt(
//							i, 0))) {
//						model.removeRow(i);
//					}
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 6, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAtualizar, -6, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizar, 0, SpringLayout.EAST, scrollPane);
		getContentPane().add(btnAtualizar);
		
		dtm = new DefaultTableModel(data, colNames) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { 
					false, false, false, false, false, false, false, false, false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		};
		table = new JTable(dtm);
		
		scrollPane.setViewportView(table);
		
		JRadioButton rdbtnRecebidos = new JRadioButton("Recebidos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnRecebidos, 15, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnRecebidos, 6, SpringLayout.EAST, textField_2);
		getContentPane().add(rdbtnRecebidos);
		
		JRadioButton rdbtnNoRecebidos = new JRadioButton("N\u00E3o recebidos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnNoRecebidos, 15, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnNoRecebidos, 6, SpringLayout.EAST, rdbtnRecebidos);
		getContentPane().add(rdbtnNoRecebidos);
		
		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnTodos, 15, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 6, SpringLayout.EAST, rdbtnTodos);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnTodos, 6, SpringLayout.EAST, rdbtnNoRecebidos);
		getContentPane().add(rdbtnTodos);
		
		ButtonGroup groupRecebidos = new ButtonGroup();
		groupRecebidos.add(rdbtnRecebidos);
		groupRecebidos.add(rdbtnNoRecebidos);
		groupRecebidos.add(rdbtnTodos);		
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_3, -971, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 6, SpringLayout.EAST, textField_3);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNumNf = new JLabel("Num. NF:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNumNf, 16, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, lblNumNf);
		springLayout.putConstraint(SpringLayout.WEST, lblNumNf, 0, SpringLayout.WEST, scrollPane);
		getContentPane().add(lblNumNf);
		
		TableColumn tcCheckBox = table.getColumnModel().getColumn(9);
		tcCheckBox.setCellEditor(table.getDefaultEditor(Boolean.class));
		tcCheckBox.setCellRenderer(table.getDefaultRenderer(Boolean.class));
		tcCheckBox.setHeaderRenderer(new CheckBoxHeaderUtil(new MyItemListener(), "Receber Todos"));
		
		TableColumn tcButton = table.getColumnModel().getColumn(10);
		tcButton.setCellRenderer(new ButtonRendererUtil());
		tcButton.setCellEditor(new ButtonEditorUtil(new JCheckBox()));
		
		popularTabela();
	}
	
	private void popularTabela() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Object o : ContaReceberModel.findAll()) {
			ContaReceber cr = (ContaReceber) o;

			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(cr.getId());
			oneRow.add(String.format("%09d", cr.getHeaderSaida().getId()));
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
