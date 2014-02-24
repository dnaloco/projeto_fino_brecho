package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.arthur.entities.Pedido;
import br.arthur.models.PedidoModel;

import com.lowagie.text.pdf.events.IndexEvents.Entry;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;

public class PedidoDialog extends JDialog {
	
	private JTable table;
	private int theId;

	public int getTheId() {
		return theId;
	}

	public void setTheId(int theId) {
		this.theId = theId;
	}

	private final JPanel contentPanel = new JPanel();

	public PedidoDialog(int consigId) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("Pedido ID");
		colunas.add("Consig. ID");
		colunas.add("Nome Consignatário");
		
		Vector tableData = new Vector();
		
		// TODO Procurar apenas pelo id do consignatário
		
		for(Object o : PedidoModel.findWhere("consignatario_fk", String.valueOf(consigId))) {
			Pedido p = (Pedido) o;
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(p.getId());
			oneRow.add(p.getConsignaratio().getId());
			oneRow.add(p.getConsignaratio().getNome());
			
			tableData.add(oneRow);
		}
		
		TableModel model = new DefaultTableModel(tableData, colunas) {
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
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		table = new JTable(model);
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
		      public boolean include(Entry entry) {
		        Integer population = (Integer) entry.getValue(0);
		        return population.intValue() > 0;
		      }
		    };
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    sorter.setRowFilter(filter);
	    table.setRowSorter(sorter);
	    
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha_selecionada = table.getSelectedRow();
				theId = (int) table.getValueAt(linha_selecionada, 0);
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, 214, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, 419, SpringLayout.WEST, contentPanel);
		contentPanel.add(scrollPane);
		{		    
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
