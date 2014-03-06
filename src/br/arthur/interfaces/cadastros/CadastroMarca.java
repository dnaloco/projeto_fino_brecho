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

import br.arthur.entities.Marca;
import br.arthur.models.MarcaModel;

public class CadastroMarca extends JInternalFrame {
	
	private int theId;
	private int linha_selecionada;
	
	private JLabel lblMarcaTtl;
	private JTextField txtMarca;
	
	private DefaultTableModel model;
	private JTable table;
	
	private JButton btnCancelar;
	private JButton btnExcluir;
	
	MarcaModel mm = new MarcaModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMarca frame = new CadastroMarca();
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
	public CadastroMarca() {
		setFrameIcon(new ImageIcon(
				"images/Letter-M-blue-icon.png"));
		setClosable(true);
		setIconifiable(true);
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);
		setTitle("Cadastro de Marca");
		setBounds(100, 100, 272, 439);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblMarcaID = new JLabel("Nova Marca");
		lblMarcaID.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblMarcaID);
		
		txtMarca = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtMarca, 33, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblMarcaID, -6, SpringLayout.NORTH, txtMarca);
		springLayout.putConstraint(SpringLayout.EAST, lblMarcaID, 0, SpringLayout.EAST, txtMarca);
		springLayout.putConstraint(SpringLayout.WEST, txtMarca, 62, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtMarca, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		springLayout.putConstraint(SpringLayout.NORTH, lblMarca, 6, SpringLayout.NORTH, txtMarca);
		springLayout.putConstraint(SpringLayout.EAST, lblMarca, -6, SpringLayout.WEST, txtMarca);
		getContentPane().add(lblMarca);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;
				
				if(txtMarca.getText().trim().isEmpty()) {
					msgErro +=  "O campo 'marca' deve ser preenchido.\n";
					isValid = false;
				}
				
				if(isValid) {
					try {
						theId = mm.createMarca(txtMarca.getText());
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					if(theId > 0) {
						adicionarTabela();
						limparCampos();
						JOptionPane.showMessageDialog(null, "Marca inserida com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir a marca!");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtMarca);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, txtMarca);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, 60, SpringLayout.EAST, btnCancelar);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnSalvar);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("ID");
		colunas.add("Categoria");
		
		Vector tableData = new Vector();
		
		for(Object o : MarcaModel.findAll()) {
			Marca m = (Marca) o;
			
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(m.getId());
			oneRow.add(m.getName());
			
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, "name_43397711290727");
		
		btnExcluir = new JButton("X");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir a categoria " + theId + "?", "Atencão",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						removerTabela();
						mm.deleteById(theId);
						limparCampos();
						JOptionPane.showMessageDialog(null, "Registro excluido");
					} catch (ConstraintViolationException ex) {
						JOptionPane.showMessageDialog(null, "Não é possível excluir esta categoria. A entradas ligados a mesma.");
						ex.printStackTrace();
					}
				}				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 6, SpringLayout.SOUTH, txtMarca);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -6, SpringLayout.WEST, btnSalvar);
		getContentPane().add(btnExcluir);
		
		lblMarcaTtl = new JLabel("Marca ID:");
		lblMarcaTtl.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblMarcaTtl, 0, SpringLayout.NORTH, lblMarcaID);
		springLayout.putConstraint(SpringLayout.WEST, lblMarcaTtl, 0, SpringLayout.WEST, btnCancelar);
		getContentPane().add(lblMarcaTtl);

	}

	protected void removerTabela() {
		for(int i = 0; i < table.getRowCount(); i += 1) {
			if(theId == (int) table.getValueAt(i, 0)) {
				model.removeRow(i);
			}
		}
	}

	protected void populateMe() {
		if(theId > 0) {
			lblMarcaTtl.setText(String.valueOf(theId));
			txtMarca.setText((String) table.getValueAt(linha_selecionada, 1));
			
			btnCancelar.setEnabled(true);
			btnExcluir.setEnabled(true);
		}
	}

	protected void limparCampos() {
		theId = 0;
		
		lblMarcaTtl.setText("Nova Marca");
		txtMarca.setText("");
		
		btnCancelar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	protected void adicionarTabela() {
		Marca me = mm.findOneWhere("id", String.valueOf(theId));
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(me.getId());
		oneRow.add(me.getName());
		model.addRow(oneRow);
	}
}
