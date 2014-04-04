package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ExtornoProduto extends JInternalFrame {
	private JTextField txtCodVenda;
	private JLabel lblNumNota;
	private JLabel lblCliente;
	private JLabel lblVendedor;
	private JLabel lblDataVenda;
	private JLabel lblFormaPagto;
	private JLabel lblTotalVenda;
	private JLabel lblParcelas;
	private JLabel lblQtdeProdutos;
	
	private Object colNames[] = {
			"Cód. Produto",
			"Qtde",
			"Descrição",
			"Consignatário",
			"EXTORNAR"
	};
	
	private Object[][] data = {};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExtornoProduto frame = new ExtornoProduto();
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
	public ExtornoProduto() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Extorno de Produto");
		setBounds(100, 100, 666, 467);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCdVenda = new JLabel("C\u00F3d. Venda: ");
		getContentPane().add(lblCdVenda);
		
		txtCodVenda = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCodVenda, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtCodVenda, 86, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblCdVenda, 6, SpringLayout.NORTH, txtCodVenda);
		springLayout.putConstraint(SpringLayout.EAST, lblCdVenda, -6, SpringLayout.WEST, txtCodVenda);
		getContentPane().add(txtCodVenda);
		txtCodVenda.setColumns(10);
		
		JButton btnBuscarCdigo = new JButton("Buscar C\u00F3digo");
		springLayout.putConstraint(SpringLayout.NORTH, btnBuscarCdigo, 0, SpringLayout.NORTH, txtCodVenda);
		springLayout.putConstraint(SpringLayout.WEST, btnBuscarCdigo, 6, SpringLayout.EAST, txtCodVenda);
		getContentPane().add(btnBuscarCdigo);
		
		JButton btnConsultarVenda = new JButton("Consultar Vendas");
		springLayout.putConstraint(SpringLayout.WEST, btnConsultarVenda, 508, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnConsultarVenda);
		
		JLabel label = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label, 16, SpringLayout.SOUTH, lblCdVenda);
		springLayout.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, lblCdVenda);
		getContentPane().add(label);
		
		lblNumNota = new JLabel(" << Busque o n\u00FAmero de nota!");
		springLayout.putConstraint(SpringLayout.WEST, lblNumNota, 6, SpringLayout.EAST, btnBuscarCdigo);
		springLayout.putConstraint(SpringLayout.EAST, lblNumNota, -6, SpringLayout.WEST, btnConsultarVenda);
		getContentPane().add(lblNumNota);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 15, SpringLayout.SOUTH, lblCdVenda);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblCdVenda);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblCliente = new JLabel("(Nome do Cliente)");
		springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 9, SpringLayout.SOUTH, txtCodVenda);
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 6, SpringLayout.EAST, lblNewLabel_1);
		getContentPane().add(lblCliente);
		
		JLabel lblVendedor123 = new JLabel("Vendedor:");
		springLayout.putConstraint(SpringLayout.EAST, lblCliente, -6, SpringLayout.WEST, lblVendedor123);
		springLayout.putConstraint(SpringLayout.NORTH, lblVendedor123, 0, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, lblVendedor123, 0, SpringLayout.EAST, btnBuscarCdigo);
		getContentPane().add(lblVendedor123);
		
		JLabel lblVendedor = new JLabel("(Nome do Vendedor)");
		springLayout.putConstraint(SpringLayout.NORTH, lblVendedor, 47, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNumNota, -15, SpringLayout.NORTH, lblVendedor);
		springLayout.putConstraint(SpringLayout.WEST, lblVendedor, 6, SpringLayout.EAST, lblVendedor123);
		getContentPane().add(lblVendedor);
		
		JLabel lblDataVendadaw123 = new JLabel("Data Venda:");
		springLayout.putConstraint(SpringLayout.EAST, lblVendedor, -6, SpringLayout.WEST, lblDataVendadaw123);
		springLayout.putConstraint(SpringLayout.SOUTH, btnConsultarVenda, -9, SpringLayout.NORTH, lblDataVendadaw123);
		springLayout.putConstraint(SpringLayout.WEST, lblDataVendadaw123, 508, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblDataVendadaw123, 0, SpringLayout.SOUTH, lblNewLabel_1);
		getContentPane().add(lblDataVendadaw123);
		
		JLabel lblDataVenda = new JLabel("00/00/0000");
		springLayout.putConstraint(SpringLayout.EAST, btnConsultarVenda, 0, SpringLayout.EAST, lblDataVenda);
		springLayout.putConstraint(SpringLayout.WEST, lblDataVenda, 581, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblDataVenda, 0, SpringLayout.SOUTH, lblNewLabel_1);
		getContentPane().add(lblDataVenda);
		
		JLabel lblFormaPagto3123 = new JLabel("Forma Pagto.:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFormaPagto3123, 6, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblFormaPagto3123, 0, SpringLayout.WEST, lblCdVenda);
		getContentPane().add(lblFormaPagto3123);
		
		JLabel lblFormaPagto = new JLabel("(Forma de Pagamento)");
		springLayout.putConstraint(SpringLayout.NORTH, lblFormaPagto, 6, SpringLayout.SOUTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, lblFormaPagto, 6, SpringLayout.EAST, lblFormaPagto3123);
		springLayout.putConstraint(SpringLayout.EAST, lblFormaPagto, -22, SpringLayout.EAST, lblCliente);
		getContentPane().add(lblFormaPagto);
		
		JLabel lblTotalVenda13231 = new JLabel("Total Venda:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTotalVenda13231, 0, SpringLayout.NORTH, lblFormaPagto3123);
		springLayout.putConstraint(SpringLayout.EAST, lblTotalVenda13231, 0, SpringLayout.EAST, btnBuscarCdigo);
		getContentPane().add(lblTotalVenda13231);
		
		JLabel lblTotalVenda = new JLabel("R$ 0,00");
		springLayout.putConstraint(SpringLayout.NORTH, lblTotalVenda, 6, SpringLayout.SOUTH, lblVendedor);
		springLayout.putConstraint(SpringLayout.WEST, lblTotalVenda, 10, SpringLayout.EAST, lblTotalVenda13231);
		springLayout.putConstraint(SpringLayout.EAST, lblTotalVenda, 87, SpringLayout.EAST, lblTotalVenda13231);
		getContentPane().add(lblTotalVenda);
		
		JLabel lblParcelas3211 = new JLabel("Parcelas:");
		springLayout.putConstraint(SpringLayout.NORTH, lblParcelas3211, 6, SpringLayout.SOUTH, lblVendedor);
		springLayout.putConstraint(SpringLayout.WEST, lblParcelas3211, 6, SpringLayout.EAST, lblTotalVenda);
		getContentPane().add(lblParcelas3211);
		
		JLabel lblParcelas = new JLabel("0");
		springLayout.putConstraint(SpringLayout.NORTH, lblParcelas, 6, SpringLayout.SOUTH, lblVendedor);
		springLayout.putConstraint(SpringLayout.WEST, lblParcelas, 6, SpringLayout.EAST, lblParcelas3211);
		springLayout.putConstraint(SpringLayout.EAST, lblParcelas, 0, SpringLayout.EAST, lblNumNota);
		getContentPane().add(lblParcelas);
		
		JLabel lblQtdeProdutos12312 = new JLabel("Qtde. Produtos:");
		springLayout.putConstraint(SpringLayout.NORTH, lblQtdeProdutos12312, 6, SpringLayout.SOUTH, lblDataVendadaw123);
		springLayout.putConstraint(SpringLayout.WEST, lblQtdeProdutos12312, 0, SpringLayout.WEST, btnConsultarVenda);
		getContentPane().add(lblQtdeProdutos12312);
		
		JLabel lblQtdeProdutos = new JLabel("0");
		springLayout.putConstraint(SpringLayout.NORTH, lblQtdeProdutos, 6, SpringLayout.SOUTH, lblDataVenda);
		springLayout.putConstraint(SpringLayout.WEST, lblQtdeProdutos, 6, SpringLayout.EAST, lblQtdeProdutos12312);
		springLayout.putConstraint(SpringLayout.EAST, lblQtdeProdutos, 1, SpringLayout.EAST, btnConsultarVenda);
		getContentPane().add(lblQtdeProdutos);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblFormaPagto3123);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblCdVenda);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 341, SpringLayout.SOUTH, lblFormaPagto3123);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnConsultarVenda);
		getContentPane().add(scrollPane);

	}
}
