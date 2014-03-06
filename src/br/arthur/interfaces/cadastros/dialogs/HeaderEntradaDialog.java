package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import antlr.debug.Event;
import br.arthur.entities.HeaderEntrada;
import br.arthur.models.HeaderEntradaModel;

import com.lowagie.text.pdf.events.IndexEvents.Entry;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class HeaderEntradaDialog extends JDialog {
	
	private JTable table;
	private int theId;

	public int getTheId() {
		return theId;
	}

	public void setTheId(int theId) {
		this.theId = theId;
	}

	private final JPanel contentPanel = new JPanel();

	public HeaderEntradaDialog(int consigId) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("Entrada ID");
		colunas.add("Nome Consignatário");
		
		Vector tableData = new Vector();
		
		// TODO Procurar apenas pelo id do consignatário
		
		for(Object o : HeaderEntradaModel.findWhere("consignatario_fk", String.valueOf(consigId))) {
			HeaderEntrada he = (HeaderEntrada) o;
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(he.getId());
			oneRow.add(he.getConsignatario().getNome());
			
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
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, contentPanel);
		contentPanel.add(scrollPane);
		
		JLabel lblPedidosFiltradosPor = new JLabel("Entradas Filtradas Por Consignat\u00E1rio:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblPedidosFiltradosPor);
		sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblPedidosFiltradosPor);
		lblPedidosFiltradosPor.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPedidosFiltradosPor, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPedidosFiltradosPor, 0, SpringLayout.NORTH, contentPanel);
		contentPanel.add(lblPedidosFiltradosPor);
		{		    
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent evt) {
						JOptionPane.showMessageDialog(null, "Entrada selecionada: " +  theId);
						dispose();
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						theId = 0;
						dispose();
					}
					
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
