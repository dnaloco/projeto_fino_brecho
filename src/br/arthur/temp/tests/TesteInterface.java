package br.arthur.temp.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TesteInterface extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteInterface frame = new TesteInterface();
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
	public TesteInterface() {
		setBounds(100, 100, 546, 373);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		Vector<String> colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		
		Vector tableData = new Vector();
		
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(1);
		JCheckBox chckbxPagar = new JCheckBox("Pagar");
		oneRow.add(chckbxPagar);
		
		tableData.add(oneRow);
		
		DefaultTableModel model = new DefaultTableModel(tableData, colunas) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
	    	boolean[] columnEditables = new boolean[] {
	    		false, false, false, false, false, false, false, false
	    	};
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}

	    };
		
		table = new JTable(model);
		scrollPane.setViewportView(table);

	}

}
