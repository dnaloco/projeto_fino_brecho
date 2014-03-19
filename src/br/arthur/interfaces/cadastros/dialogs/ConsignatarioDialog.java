package br.arthur.interfaces.cadastros.dialogs;

import groovyjarjarcommonscli.ParseException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
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
import javax.swing.text.MaskFormatter;

import br.arthur.entities.Consignatario;
import br.arthur.models.ConsignatarioModel;

public class ConsignatarioDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private int theId;
	private TableModel model;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCelular;

	public int getTheId() {
		return theId;
	}

	public void setTheId(int theId) {
		this.theId = theId;
	}
	

	public ConsignatarioDialog() {
		setTitle("Buscar Consignat\u00E1rio");
		setModal(true);
		setBounds(100, 100, 679, 431);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Vector<String> colunas = new Vector();
		colunas.add("ID");
		colunas.add("Nome");
		colunas.add("Telefone");
		colunas.add("Celular");
		colunas.add("E-mail");
		colunas.add("Site");
		colunas.add("CPF");
		colunas.add("RG");
		
		Vector tableData = new Vector();
		
		for(Object o : ConsignatarioModel.findAll()) {
			Consignatario c = (Consignatario) o;
			Vector<Object> oneRow = new Vector<Object>();
			oneRow.add(c.getId());
			oneRow.add(c.getNome());
			oneRow.add(c.getTelefone());
			oneRow.add(c.getCelular());
			oneRow.add(c.getEmail());
			oneRow.add(c.getSite());
			oneRow.add(c.getCpf());
			oneRow.add(c.getRg());
			tableData.add(oneRow);
		}
		
		model = new DefaultTableModel(tableData, colunas) {
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
		
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
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
					theId = (int) table.getValueAt(linha_selecionada, 0);
				}
			});
			
			
			RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			      public boolean include(Entry entry) {
			        Integer population = (Integer) entry.getValue(0);
			        return population.intValue() > 0;
			      }
			    };
		    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		    sorter.setRowFilter(filter);
		    table.setRowSorter(sorter);
		    
			scrollPane = new JScrollPane(table);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 33, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, contentPanel);
			contentPanel.add(scrollPane);
		}
		
		JButton btnFiltrar = new JButton("Filtrar");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnFiltrar, -2, SpringLayout.NORTH, contentPanel);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> filter = new HashMap<String, String>();
				
				if(!txtNome.getText().trim().isEmpty()) {
					filter.put("nome", txtNome.getText());
				} else if (!txtCelular.getText().trim().isEmpty()) {
					filter.put("cpf", txtCelular.getText());
				} else if (!txtTelefone.getText().trim().isEmpty()) {
					filter.put("rg", txtTelefone.getText());
				} else {
					RowFilter<Object, Object> f = new RowFilter<Object, Object>() {
					      public boolean include(Entry entry) {
					        Integer population = (Integer) entry.getValue(0);
					        return population.intValue() > 0;
					      }
					    };
				    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
				    sorter.setRowFilter(f);
				    table.setRowSorter(sorter);
				}
				
				if(filter.size() > 0) {
					filtrar(filter);
				}
			}
		});
		contentPanel.add(btnFiltrar);
		
		txtNome = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, txtNome, 48, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtNome, -11, SpringLayout.NORTH, scrollPane);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblNome, 6, SpringLayout.NORTH, btnFiltrar);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblNome, -6, SpringLayout.WEST, txtNome);
		contentPanel.add(lblNome);
		
		JLabel lblRg = new JLabel("Telefone:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblRg, 6, SpringLayout.NORTH, btnFiltrar);
		contentPanel.add(lblRg);
		
		txtTelefone = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblRg, -6, SpringLayout.WEST, txtTelefone);
		sl_contentPanel.putConstraint(SpringLayout.WEST, txtTelefone, 248, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtTelefone, -11, SpringLayout.NORTH, scrollPane);
		contentPanel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		MaskFormatter frmtCpf = null;
		try {
			frmtCpf = new MaskFormatter("###.###.###-##");
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtCelular = new javax.swing.JFormattedTextField(frmtCpf);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, txtCelular, -11, SpringLayout.NORTH, scrollPane);
		sl_contentPanel.putConstraint(SpringLayout.EAST, txtCelular, -68, SpringLayout.EAST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnFiltrar, 6, SpringLayout.EAST, txtCelular);
		contentPanel.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblCpf = new JLabel("Celular:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCpf, 6, SpringLayout.NORTH, btnFiltrar);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblCpf, -5, SpringLayout.WEST, txtCelular);
		contentPanel.add(lblCpf);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null, "Consignatário Selecionado: " + theId);
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
					public void actionPerformed(ActionEvent e) {
						theId = 0;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void filtrar(final HashMap<String, String> filter) {
		RowFilter<Object, Object> f = new RowFilter<Object, Object>() {
		      public boolean include(Entry entry) {
		    	  boolean isValid = true;
//		    	String population = (String) entry.getValue(1);
//		        return population.contentEquals(nome);
		    	  if(filter.containsKey("nome")) {
		    		  isValid = ((String)entry.getValue(1)).toLowerCase().contains(filter.get("nome").toLowerCase());
		    	  }
		    	  
		    	  if(filter.containsKey("cpf")) {
		    		  isValid = ((String)entry.getValue(6)).contains(filter.get("cpf"));
		    	  }
		    	  
		    	  if(filter.containsKey("rg")) {
		    		  isValid = ((String)entry.getValue(7)).contains(filter.get("rg"));
		    	  }
		    	  
		    	  return isValid;
		      }
		    };
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    sorter.setRowFilter(f);
	    table.setRowSorter(sorter);
	}
}