package br.arthur.interfaces.cadastros;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.hibernate.exception.ConstraintViolationException;

import br.arthur.entities.Categoria;
import br.arthur.models.CategoriaModel;

public class CadastroCategoria extends JInternalFrame {
	
	private int theId;
	private int linha_selecionada;
	
	private JLabel lblCategoriaID;
	private JTextField txtCategoria;
	
	private DefaultTableModel model;
	private JTable table;
	
	private JButton btnCancelar;
	private JButton btnExcluir;
	
	CategoriaModel cm = new CategoriaModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCategoria frame = new CadastroCategoria();
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

	public CadastroCategoria() {
		setFrameIcon(new ImageIcon(
				"images/Letter-C-blue-icon.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Categoria");
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);

		setBounds(100, 100, 269, 438);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		lblCategoriaID = new JLabel("Nova Categoria");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoriaID, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCategoriaID, -10, SpringLayout.EAST, getContentPane());
		lblCategoriaID.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblCategoriaID);
		
		txtCategoria = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCategoria, 37, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtCategoria, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoria, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCategoria, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtCategoria, 6, SpringLayout.EAST, lblCategoria);
		getContentPane().add(lblCategoria);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtCategoria);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;
				
				if(txtCategoria.getText().trim().isEmpty()) {
					msgErro +=  "O campo 'categoria' deve ser preenchido.\n";
					isValid = false;
				}
				
				if(isValid) {
					try {
						theId = cm.createCategoria(txtCategoria.getText());
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					if(theId > 0) {
						adicionarTabela();
						limparCampos();
						JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir a categoria!");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		getContentPane().add(btnSalvar);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnSalvar);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancelar, -6, SpringLayout.NORTH, panel);
		panel.setLayout(new CardLayout(0, 0));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("ID");
		colunas.add("Categoria");
		
		Vector tableData = new Vector();
		
		for(Object o : CategoriaModel.findAll()) {
			Categoria c = (Categoria) o;
			
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(c.getId());
			oneRow.add(c.getName());
			
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
	    
	    table = new JTable(model);
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		linha_selecionada = table.getSelectedRow();
	    		theId = (int) table.getValueAt(linha_selecionada, 0);
	    		populateMe();
	    	}
	    });
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(166);
		
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
		      public boolean include(Entry entry) {
		        Integer population = (Integer) entry.getValue(0);
		        return population.intValue() > 0;
		      }
		    };
	    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    sorter.setRowFilter(filter);
	    table.setRowSorter(sorter);
		
		JScrollPane scrollPaneCategoria = new JScrollPane(table);
		panel.add(scrollPaneCategoria, "name_43359325961995");
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, 81, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnCancelar);
		
		btnExcluir = new JButton("X");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir a categoria " + theId + "?", "Atencão",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						removerTabela();
						cm.deleteById(theId);
						limparCampos();
						JOptionPane.showMessageDialog(null, "Registro excluido");
					} catch (ConstraintViolationException ex) {
						JOptionPane.showMessageDialog(null, "Não é possível excluir esta categoria. A entradas ligados a mesma.");
						ex.printStackTrace();
					}
				}				
			}
		});
		btnExcluir.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 6, SpringLayout.SOUTH, txtCategoria);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -6, SpringLayout.WEST, btnSalvar);
		getContentPane().add(btnExcluir);
		
		JLabel lblCategoriaId = new JLabel("Categoria ID:");
		lblCategoriaId.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoriaId, 1, SpringLayout.NORTH, lblCategoriaID);
		springLayout.putConstraint(SpringLayout.WEST, lblCategoriaId, 0, SpringLayout.WEST, lblCategoria);
		getContentPane().add(lblCategoriaId);
	}
	
	protected void removerTabela() {
		for(int i = 0; i < table.getRowCount(); i += 1) {
			if(theId == (int) table.getValueAt(i, 0)) {
				model.removeRow(i);
			}
		}
	}

	protected void adicionarTabela() {
		Categoria ce = cm.findOneWhere("id", String.valueOf(theId));
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(ce.getId());
		oneRow.add(ce.getName());
		model.addRow(oneRow);
	}

	protected void limparCampos() {
		theId = 0;
		
		lblCategoriaID.setText("Nova Categoria");
		txtCategoria.setText("");
		
		btnCancelar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	protected void populateMe() {
		if(theId > 0) {
			lblCategoriaID.setText(String.valueOf(theId));
			txtCategoria.setText((String) table.getValueAt(linha_selecionada, 1));
			
			btnCancelar.setEnabled(true);
			btnExcluir.setEnabled(true);
		}
	}
}
