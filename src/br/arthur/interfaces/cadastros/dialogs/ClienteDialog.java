package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.arthur.entities.Cliente;
import br.arthur.models.ClienteModel;

public class ClienteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNome;
	private Long theId;
	private TableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton okButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClienteDialog dialog = new ClienteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClienteDialog() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		Vector<String> colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("Telefone");
		colunas.add("Celular");
		colunas.add("E-mail");
		colunas.add("Site");
		colunas.add("Aniversário");
		colunas.add("Pendente");
		
		Vector tableData = new Vector();
		
		for(Object o : ClienteModel.findAll()) {
			Cliente c = (Cliente) o;
			
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(c.getId());
			oneRow.add(c.getNome());
			oneRow.add(c.getTelefone());
			oneRow.add(c.getCelular());
			oneRow.add(c.getEmail());
			oneRow.add(c.getSite());
			
			if (c.getAniversario() != null) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				oneRow.add(df.format(c.getAniversario()));
			} else {
				oneRow.add("");
			}
			
			if(c.isPendencia()) {
				oneRow.add("sim");
			} else {
				oneRow.add("não");
			}
			
			tableData.add(oneRow);
		}
		
		model = new DefaultTableModel(tableData, colunas) {
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
		
		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 29, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, contentPanel);
		contentPanel.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		
		table = new JTable(model);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha_selecionada = table.getSelectedRow();
				theId = (Long) table.getValueAt(linha_selecionada, 0);
				
				if (theId > 0) {
					okButton.setEnabled(true);
				}
			}
		});
		
		
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
		      public boolean include(Entry entry) {
		        Long population = (Long) entry.getValue(0);
		        return population.longValue() > 0;
		      }
		    };
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    sorter.setRowFilter(filter);
	    table.setRowSorter(sorter);
		
	    scrollPane = new JScrollPane(table);
		panel.add(scrollPane, "name_18400489789867");
		{
			lblNome = new JLabel("Nome:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNome, 6, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblNome, 0, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblNome);
		}
		{
			textField = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, -6, SpringLayout.NORTH, lblNome);
			sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblNome);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JButton btnFiltrar = new JButton("Filtrar");
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnFiltrar);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnFiltrar, -5, SpringLayout.EAST, contentPanel);
		contentPanel.add(btnFiltrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "Cliente Selecionado: " + theId);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						theId = (long) 0;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public long getTheId() {
		return theId;
	}

	public void setTheId(long theId) {
		this.theId = theId;
	}
}
