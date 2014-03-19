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

import br.arthur.entities.Tamanho;
import br.arthur.models.TamanhoModel;

public class CadastroTamanho extends JInternalFrame {
	private int theId = 0;
	private int linha_selecionada;

	private JTextField txtTamanho;
	private JLabel lblNovoTamanho;

	private DefaultTableModel model;
	private JTable table;

	private JButton btnExcluir;

	private TamanhoModel tm = new TamanhoModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroTamanho frame = new CadastroTamanho();
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
	public CadastroTamanho() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Cadastro de Tamanho");
		setBounds(100, 100, 230, 445);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JLabel lblCodTamanho = new JLabel("C\u00F3d. Tamanho:");
		lblCodTamanho.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblCodTamanho, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCodTamanho, 10,
				SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCodTamanho);

		lblNovoTamanho = new JLabel("NovaCor");
		springLayout.putConstraint(SpringLayout.NORTH, lblNovoTamanho, 0,
				SpringLayout.NORTH, lblCodTamanho);
		springLayout.putConstraint(SpringLayout.EAST, lblNovoTamanho, -10,
				SpringLayout.EAST, getContentPane());
		lblNovoTamanho.setFont(new Font("SansSerif", Font.BOLD, 14));
		getContentPane().add(lblNovoTamanho);

		txtTamanho = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtTamanho, 6,
				SpringLayout.SOUTH, lblCodTamanho);
		springLayout.putConstraint(SpringLayout.EAST, txtTamanho, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(txtTamanho);
		txtTamanho.setColumns(10);

		JLabel lblCor = new JLabel("Tamanho:");
		springLayout.putConstraint(SpringLayout.WEST, txtTamanho, 6,
				SpringLayout.EAST, lblCor);
		springLayout.putConstraint(SpringLayout.NORTH, lblCor, 12,
				SpringLayout.SOUTH, lblCodTamanho);
		springLayout.putConstraint(SpringLayout.WEST, lblCor, 0,
				SpringLayout.WEST, lblCodTamanho);
		getContentPane().add(lblCor);

		btnExcluir = new JButton("Excluir");
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 7,
				SpringLayout.SOUTH, txtTamanho);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -114,
				SpringLayout.EAST, getContentPane());

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao;
				opcao = JOptionPane.showConfirmDialog(null,
						"Deseja Realmente excluir o tamanho " + theId + "?",
						"Atencão", JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					try {
						tm.deleteById(theId);
						removerTabela();
						limparCampos();
						JOptionPane
								.showMessageDialog(null, "Registro excluido");
					} catch (ConstraintViolationException ex) {
						JOptionPane
								.showMessageDialog(null,
										"Não é possível excluir este tamanho. A entradas ligadas ao mesmo.");
						ex.printStackTrace();
					}
				}
			}
		});

		getContentPane().add(btnExcluir);

		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6,
				SpringLayout.SOUTH, txtTamanho);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, 6,
				SpringLayout.EAST, btnExcluir);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10,
				SpringLayout.EAST, getContentPane());

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;

				if (txtTamanho.getText().trim().isEmpty()) {
					msgErro += "O campo 'tamanho' deve ser preenchido.\n";
					isValid = false;
				}

				if (isValid) {
					if (theId > 0) {
						tm.saveTamanho(theId, txtTamanho.getText());
						alterarTabela();
					} else {
						try {
							theId = tm.createTamanho(txtTamanho.getText());
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

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6,
				SpringLayout.SOUTH, btnExcluir);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 306,
				SpringLayout.SOUTH, btnExcluir);
		springLayout.putConstraint(SpringLayout.EAST, panel, 0,
				SpringLayout.EAST, lblNovoTamanho);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		Vector<String> colunas = new Vector();

		colunas.add("Código");
		colunas.add("Tamanho");

		Vector tableData = new Vector();

		for (Object o : TamanhoModel.findAll()) {
			Tamanho t = (Tamanho) o;

			Vector<Object> oneRow = new Vector<Object>();

			oneRow.add(t.getId());
			oneRow.add(t.getName());

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
		panel.add(scrollPane, "name_17821691486519");

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
			lblNovoTamanho.setText(String.valueOf(theId));
			txtTamanho.setText((String) table.getValueAt(linha_selecionada, 1));
			
			btnExcluir.setEnabled(true);
		}
	}

	protected void limparCampos() {
		theId = 0;
		
		lblNovoTamanho.setText("Novo Tamanho");
		txtTamanho.setText("");

		btnExcluir.setEnabled(false);
	}

	protected void adicionarTabela() {
		Tamanho te = tm.findOneWhere("id", String.valueOf(theId));
		Vector<Object> oneRow = new Vector<Object>();
		oneRow.add(te.getId());
		oneRow.add(te.getName());
		model.addRow(oneRow);
	}

	protected void alterarTabela() {
		Tamanho te = tm.findOneWhere("id", String.valueOf(theId));
		Object[] linhaAlterar = new Object[] { te.getId(), te.getName() };
		for (int i = 0; i < table.getRowCount(); i += 1) {
			if (theId == (int) table.getValueAt(i, 0)) {
				model.removeRow(i);
				model.insertRow(i, linhaAlterar);
			}
		}
	}
}
