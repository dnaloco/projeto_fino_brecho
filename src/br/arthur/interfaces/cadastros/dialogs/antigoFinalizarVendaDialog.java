package br.arthur.interfaces.cadastros.dialogs;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import br.arthur.entities.Cliente;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.Saida;
import br.arthur.entities.User;
import br.arthur.models.SaidaModel;

public class antigoFinalizarVendaDialog extends JInternalFrame {
	private JTextField txtPago;
	private JTextField txtDesconto;
	private static HeaderSaida hSaida;
	private static Cliente cliente;
	private static User vendedor;

	private SaidaModel sm = new SaidaModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					antigoFinalizarVendaDialog frame = new antigoFinalizarVendaDialog(hSaida, cliente, vendedor);
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
	public antigoFinalizarVendaDialog(HeaderSaida hSaida, Cliente cliente, User vendedor) {

		
		List produtos = sm.findWhere("header_saida_fk", String.valueOf(hSaida.getId()));
		
		for(Object o : produtos) {
			String s = ((Saida) o).getEntrada().getDescricao();
		}
		
		setTitle("Finalizar Venda");
		setBounds(100, 100, 520, 266);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Resumo Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNumNf = new JLabel("Num. NF / S\u00E9rie:");
		lblNumNf.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblNumNf = new GridBagConstraints();
		gbc_lblNumNf.anchor = GridBagConstraints.EAST;
		gbc_lblNumNf.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumNf.gridx = 0;
		gbc_lblNumNf.gridy = 0;
		panel.add(lblNumNf, gbc_lblNumNf);
		
		JLabel label = new JLabel("000000");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 3;
		gbc_lblCliente.gridy = 0;
		panel.add(lblCliente, gbc_lblCliente);
		
		JLabel lblNomeCliente = new JLabel("Arthur Santos Costa de Alcantara");
		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 0);
		gbc_lblNomeCliente.gridx = 4;
		gbc_lblNomeCliente.gridy = 0;
		panel.add(lblNomeCliente, gbc_lblNomeCliente);
		
		JLabel lblQuantidadeItens = new JLabel("Quantidade Itens:");
		lblQuantidadeItens.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblQuantidadeItens = new GridBagConstraints();
		gbc_lblQuantidadeItens.insets = new Insets(0, 0, 0, 5);
		gbc_lblQuantidadeItens.gridx = 0;
		gbc_lblQuantidadeItens.gridy = 1;
		panel.add(lblQuantidadeItens, gbc_lblQuantidadeItens);
		
		JLabel lblQtde = new JLabel("10");
		GridBagConstraints gbc_lblQtde = new GridBagConstraints();
		gbc_lblQtde.anchor = GridBagConstraints.EAST;
		gbc_lblQtde.insets = new Insets(0, 0, 0, 5);
		gbc_lblQtde.gridx = 1;
		gbc_lblQtde.gridy = 1;
		panel.add(lblQtde, gbc_lblQtde);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblValorTotal = new GridBagConstraints();
		gbc_lblValorTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblValorTotal.gridx = 3;
		gbc_lblValorTotal.gridy = 1;
		panel.add(lblValorTotal, gbc_lblValorTotal);
		
		JLabel lblTotal = new JLabel("R$ 1515,35");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.gridx = 4;
		gbc_lblTotal.gridy = 1;
		panel.add(lblTotal, gbc_lblTotal);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 98, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -44, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, panel_1);
		panel_1.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Confirmar Venda");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -265, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar p/ Venda");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, -6, SpringLayout.WEST, btnNewButton);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblFormaPagto = new JLabel("Forma Pagto:");
		lblFormaPagto.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblFormaPagto = new GridBagConstraints();
		gbc_lblFormaPagto.anchor = GridBagConstraints.EAST;
		gbc_lblFormaPagto.insets = new Insets(0, 0, 5, 5);
		gbc_lblFormaPagto.gridx = 0;
		gbc_lblFormaPagto.gridy = 0;
		panel_1.add(lblFormaPagto, gbc_lblFormaPagto);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 4;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		panel_1.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Parcelas:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);
		
		JLabel lblValorPago = new JLabel("Valor Pago:");
		lblValorPago.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblValorPago = new GridBagConstraints();
		gbc_lblValorPago.anchor = GridBagConstraints.EAST;
		gbc_lblValorPago.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorPago.gridx = 7;
		gbc_lblValorPago.gridy = 0;
		panel_1.add(lblValorPago, gbc_lblValorPago);
		
		txtPago = new JTextField();
		txtPago.setText("2000,00");
		GridBagConstraints gbc_txtPago = new GridBagConstraints();
		gbc_txtPago.insets = new Insets(0, 0, 5, 0);
		gbc_txtPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPago.gridx = 8;
		gbc_txtPago.gridy = 0;
		panel_1.add(txtPago, gbc_txtPago);
		txtPago.setColumns(10);
		
		JLabel lblDesconto = new JLabel("Desconto:");
		lblDesconto.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblDesconto = new GridBagConstraints();
		gbc_lblDesconto.anchor = GridBagConstraints.EAST;
		gbc_lblDesconto.insets = new Insets(0, 0, 0, 5);
		gbc_lblDesconto.gridx = 0;
		gbc_lblDesconto.gridy = 1;
		panel_1.add(lblDesconto, gbc_lblDesconto);
		
		txtDesconto = new JTextField();
		txtDesconto.setText("15.00");
		GridBagConstraints gbc_txtDesconto = new GridBagConstraints();
		gbc_txtDesconto.gridwidth = 4;
		gbc_txtDesconto.insets = new Insets(0, 0, 0, 5);
		gbc_txtDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDesconto.gridx = 1;
		gbc_txtDesconto.gridy = 1;
		panel_1.add(txtDesconto, gbc_txtDesconto);
		txtDesconto.setColumns(10);
		
		JLabel lblTotalPago = new JLabel("Total Pago:");
		lblTotalPago.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblTotalPago = new GridBagConstraints();
		gbc_lblTotalPago.anchor = GridBagConstraints.EAST;
		gbc_lblTotalPago.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalPago.gridx = 5;
		gbc_lblTotalPago.gridy = 1;
		panel_1.add(lblTotalPago, gbc_lblTotalPago);
		
		JLabel lblTotalpago = new JLabel("R$ 1500,35");
		GridBagConstraints gbc_lblTotalpago = new GridBagConstraints();
		gbc_lblTotalpago.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalpago.gridx = 6;
		gbc_lblTotalpago.gridy = 1;
		panel_1.add(lblTotalpago, gbc_lblTotalpago);
		
		JLabel lblTroco = new JLabel("Troco:");
		lblTroco.setFont(new Font("SansSerif", Font.BOLD, 12));
		GridBagConstraints gbc_lblTroco = new GridBagConstraints();
		gbc_lblTroco.insets = new Insets(0, 0, 0, 5);
		gbc_lblTroco.anchor = GridBagConstraints.EAST;
		gbc_lblTroco.gridx = 7;
		gbc_lblTroco.gridy = 1;
		panel_1.add(lblTroco, gbc_lblTroco);
		
		JLabel lblNewLabel_1 = new JLabel("R$ 499,65");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 8;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		getContentPane().add(btnNewButton_1);

	}
}
