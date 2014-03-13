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

import br.arthur.entities.Cor;
import br.arthur.models.CorModel;

public class CadastroCor extends JInternalFrame {
	private int theId = 0;
	private int linha_selecionada;
	
	private JTextField txtCor;
	private JLabel lblNovaCor;
	
	private DefaultTableModel model;
	private JTable table;

	private JButton btnExcluir;

	private CorModel cm = new CorModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCor frame = new CadastroCor();
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
	public CadastroCor() {
		setTitle("Cadastro de Cor");
		setBounds(100, 100, 204, 459);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCdCor = new JLabel("C\u00F3d. Cor:");
		lblCdCor.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblCdCor, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCdCor, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCdCor);
		
		lblNovaCor = new JLabel("Nova Cor");
		springLayout.putConstraint(SpringLayout.NORTH, lblNovaCor, 0, SpringLayout.NORTH, lblCdCor);
		springLayout.putConstraint(SpringLayout.EAST, lblNovaCor, -10, SpringLayout.EAST, getContentPane());
		lblNovaCor.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblNovaCor);
		
		txtCor = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCor, 6, SpringLayout.SOUTH, lblCdCor);
		springLayout.putConstraint(SpringLayout.EAST, txtCor, 0, SpringLayout.EAST, lblNovaCor);
		getContentPane().add(txtCor);
		txtCor.setColumns(10);
		
		JLabel lblCor = new JLabel("Cor:");
		springLayout.putConstraint(SpringLayout.WEST, txtCor, 6, SpringLayout.EAST, lblCor);
		springLayout.putConstraint(SpringLayout.NORTH, lblCor, 12, SpringLayout.SOUTH, lblCdCor);
		springLayout.putConstraint(SpringLayout.WEST, lblCor, 0, SpringLayout.WEST, lblCdCor);
		getContentPane().add(lblCor);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel);
		
		btnExcluir = new JButton("Excluir");
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnExcluir);
		panel.setLayout(new CardLayout(0, 0));
		
		Vector<String> colunas = new Vector();

		colunas.add("Código");
		colunas.add("Cor");

		Vector tableData = new Vector();

		for (Object o : CorModel.findAll()) {
			Cor c = (Cor) o;

			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(c.getId());
			oneRow.add(c.getName());

			tableData.add(oneRow);
		}

		model = new DefaultTableModel(tableData, colunas) {
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

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
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				model);
		sorter.setRowFilter(filter);
		table.setRowSorter(sorter);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, "name_20670299968847");
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 6, SpringLayout.SOUTH, txtCor);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -102, SpringLayout.EAST, getContentPane());
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir a cor " + theId + "?",
						"Atencão", JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						cm.deleteById(theId);
						removerTabela();
						limparCampos();
						JOptionPane
								.showMessageDialog(null, "Registro excluido");
					} catch (ConstraintViolationException ex) {
						JOptionPane
								.showMessageDialog(null,
										"Não é possível excluir esta cor. A entradas ligadas a mesma.");
						ex.printStackTrace();
					}
				}
			}
		});

		
		getContentPane().add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtCor);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, 0, SpringLayout.EAST, btnExcluir);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;

				if (txtCor.getText().trim().isEmpty()) {
					msgErro += "O campo 'tamanho' deve ser preenchido.\n";
					isValid = false;
				}

				if (isValid) {
					if (theId > 0) {
						cm.saveCor(theId, txtCor.getText());
						alterarTabela();
					} else {
						try {
							theId = cm.createCor(txtCor.getText());
							adicionarTabela();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (theId > 0) {
						JOptionPane.showMessageDialog(null,
								"Marca inserida com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possível inserir a marca!");
					}

					limparCampos();
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		
		getContentPane().add(btnSalvar);

	}

	protected void populateMe() {
		if(theId > 0) {
			lblNovaCor.setText(String.valueOf(theId));
			txtCor.setText((String) table.getValueAt(linha_selecionada, 1));
			
			btnExcluir.setEnabled(true);
		}
	}

	protected void adicionarTabela() {
		Cor ce = cm.findOneWhere("id", String.valueOf(theId));
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(ce.getId());
		oneRow.add(ce.getName());
		model.addRow(oneRow);
	}

	protected void alterarTabela() {
		Cor ce = cm.findOneWhere("id", String.valueOf(theId));
		Object[] linhaAlterar = new Object[] { ce.getId(), ce.getName() };
		for (int i = 0; i < table.getRowCount(); i += 1) {
			if (theId == (int) table.getValueAt(i, 0)) {
				model.removeRow(i);
				model.insertRow(i, linhaAlterar);
			}
		}
	}

	protected void limparCampos() {
		theId = 0;
		
		lblNovaCor.setText("Nova Cor");
		txtCor.setText("");

		btnExcluir.setEnabled(false);
	}

	protected void removerTabela() {
		for(int i = 0; i < table.getRowCount(); i += 1) {
			if(theId == (int) table.getValueAt(i, 0)) {
				model.removeRow(i);
			}
		}
	}

}
