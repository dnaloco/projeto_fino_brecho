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

import br.arthur.entities.Tipo;
import br.arthur.models.TipoModel;

public class CadastroTipo extends JInternalFrame {
	
	private int theId;
	private int linha_selecionada;
	
	private JLabel lblTipoid;
	private JTextField txtTipo;
	
	private DefaultTableModel model;
	private JTable table;
	
	private JButton btnCancelar;
	private JButton btnExcluir;
	
	TipoModel tm = new TipoModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroTipo frame = new CadastroTipo();
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
	public CadastroTipo() {
		setFrameIcon(new ImageIcon(
				"images/Letter-T-blue-icon.png"));
		setClosable(true);
		setIconifiable(true);
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);
		setTitle("Cadastro de Tipo");
		setBounds(100, 100, 250, 330);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNovaTipo = new JLabel("Tipo ID:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNovaTipo, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNovaTipo, 10, SpringLayout.WEST, getContentPane());
		lblNovaTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblNovaTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		getContentPane().add(lblTipo);
		
		txtTipo = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtTipo, 43, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtTipo, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTipo, 6, SpringLayout.NORTH, txtTipo);
		springLayout.putConstraint(SpringLayout.EAST, lblTipo, -6, SpringLayout.WEST, txtTipo);
		springLayout.putConstraint(SpringLayout.NORTH, txtTipo, 6, SpringLayout.SOUTH, lblNovaTipo);
		getContentPane().add(txtTipo);
		txtTipo.setColumns(10);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, txtTipo);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;
				
				if(txtTipo.getText().trim().isEmpty()) {
					msgErro +=  "O campo 'tipo' deve ser preenchido.\n";
					isValid = false;
				}
				
				if(isValid) {
					try {
						theId = tm.createTipo(txtTipo.getText());
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					if(theId > 0) {
						adicionarTabela();
						limparCampos();
						JOptionPane.showMessageDialog(null, "Tipo inserida com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir o tipo!");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnSalvar);
		panel.setLayout(new CardLayout(0, 0));
		
		Vector<String> colunas = new Vector();
		
		colunas.add("ID");
		colunas.add("Tipo");
		
		Vector tableData = new Vector();
		
		for(Object o : TipoModel.findAll()) {
			Tipo t = (Tipo) o;
			
			Vector<Object> oneRow = new Vector<Object>();
			
			oneRow.add(t.getId());
			oneRow.add(t.getName());
			
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
		panel.add(scrollPane, "name_43490446037793");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtTipo);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, -88, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");		
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, txtTipo);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnCancelar);
		
		btnExcluir = new JButton("X");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir o tipo " + theId + "?", "Atencão",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						removerTabela();
						tm.deleteById(theId);
						limparCampos();
						JOptionPane.showMessageDialog(null, "Registro excluido");
					} catch (ConstraintViolationException ex) {
						JOptionPane.showMessageDialog(null, "Não é possível excluir este tipo. A entradas ligados a mesma.");
						ex.printStackTrace();
					}
				}				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 6, SpringLayout.SOUTH, txtTipo);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -6, SpringLayout.WEST, btnSalvar);
		getContentPane().add(btnExcluir);
		
		lblTipoid = new JLabel("Novo Tipo");
		lblTipoid.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblTipoid, 0, SpringLayout.NORTH, lblNovaTipo);
		springLayout.putConstraint(SpringLayout.EAST, lblTipoid, 0, SpringLayout.EAST, txtTipo);
		getContentPane().add(lblTipoid);

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
			lblTipoid.setText(String.valueOf(theId));
			txtTipo.setText((String) table.getValueAt(linha_selecionada, 1));
			
			btnCancelar.setEnabled(true);
			btnExcluir.setEnabled(true);
		}
	}

	protected void limparCampos() {
		theId = 0;
		
		lblTipoid.setText("Novo Tipo");
		txtTipo.setText("");
		
		btnCancelar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	protected void adicionarTabela() {
		Tipo te = tm.findOneWhere("id", String.valueOf(theId));
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(te.getId());
		oneRow.add(te.getName());
		model.addRow(oneRow);
	}
}
