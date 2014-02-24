package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CadastroVenda extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVenda frame = new CadastroVenda();
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
	public CadastroVenda() {
		setIconifiable(true);
		setClosable(true);
		setTitle("Caixa");
		setBounds(100, 100, 586, 410);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblData = new JLabel("Data:");
		springLayout.putConstraint(SpringLayout.NORTH, lblData, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblData, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblData);
		
		JLabel label = new JLabel("01/01/1900");
		springLayout.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, lblData);
		springLayout.putConstraint(SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, lblData);
		getContentPane().add(label);
		
		JLabel lblCliente = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 0, SpringLayout.NORTH, lblData);
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 36, SpringLayout.EAST, label);
		getContentPane().add(lblCliente);
		
		JLabel lblNewLabel = new JLabel("Arthur Santos Costa");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 6, SpringLayout.EAST, lblCliente);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 213, SpringLayout.EAST, lblCliente);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Buscar Cliente");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, -6, SpringLayout.NORTH, lblData);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 6, SpringLayout.EAST, lblNewLabel);
		getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 564, SpringLayout.WEST, getContentPane());
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "name_45136322692478");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Produtos", null, panel_1, null);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JPanel panel_3 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_3, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_3, 10, SpringLayout.WEST, panel_1);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_3);
		
		JButton btnCancelarItem = new JButton("Cancelar Item");
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancelarItem, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_3, -6, SpringLayout.NORTH, btnCancelarItem);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCancelarItem, 0, SpringLayout.SOUTH, panel_1);
		panel_1.add(btnCancelarItem);
		
		JLabel lblCdigoDeBarras = new JLabel("C\u00F3digo de Barras:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblCdigoDeBarras, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblCdigoDeBarras, 296, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_3, -6, SpringLayout.WEST, lblCdigoDeBarras);
		panel_1.add(lblCdigoDeBarras);
		
		textField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblCdigoDeBarras);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, panel_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField, -10, SpringLayout.EAST, panel_1);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Buscar Produto");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, textField);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, panel_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton_1, -10, SpringLayout.EAST, panel_1);
		panel_1.add(btnNewButton_1);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblQuantidade, 12, SpringLayout.SOUTH, btnNewButton_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblQuantidade, 6, SpringLayout.EAST, panel_3);
		panel_1.add(lblQuantidade);
		
		textField_1 = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_1, -57, SpringLayout.EAST, textField);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		table = new JTable();
		sl_panel_1.putConstraint(SpringLayout.NORTH, table, 4, SpringLayout.SOUTH, textField_1);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_1.putConstraint(SpringLayout.WEST, table, 6, SpringLayout.EAST, panel_3);
		sl_panel_1.putConstraint(SpringLayout.EAST, table, -10, SpringLayout.EAST, panel_1);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Camiseta", "5,26", "1", "5,56"},
				{"2", null, null, null, null},
				{"3", "dasdas", "asd", null, null},
				{"4", null, null, null, null},
				{"5", null, null, null, null},
				{"6", null, "asasd", null, null},
				{"7", null, null, null, null},
				{"8", null, null, null, null},
				{"9", null, null, null, null},
			},
			new String[] {
				"Item", "Produto", "Pre\u00E7o", "Qtde", "Subtotal"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(37);
		panel_1.add(table);
		
		JLabel lblTotal = new JLabel("Total:");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, table, -16, SpringLayout.NORTH, lblTotal);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, lblTotal);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancelarItem, -90, SpringLayout.WEST, lblTotal);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblTotal, 0, SpringLayout.SOUTH, btnCancelarItem);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblTotal, -138, SpringLayout.EAST, panel_1);
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(lblTotal);
		
		JLabel lblR = new JLabel("R$ 56,56");
		sl_panel_1.putConstraint(SpringLayout.WEST, lblR, -122, SpringLayout.EAST, textField);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblR, 0, SpringLayout.SOUTH, btnCancelarItem);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblR, -10, SpringLayout.EAST, panel_1);
		lblR.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblR.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblR);
		
		JButton btnO = new JButton("O");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnO, 6, SpringLayout.SOUTH, btnNewButton_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnO, 0, SpringLayout.EAST, textField);
		panel_1.add(btnO);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Finalizar Venda", null, panel_2, null);
		panel_2.setLayout(new SpringLayout());

	}
}
