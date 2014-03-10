package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.arthur.entities.Cliente;
import br.arthur.entities.FormaPagto;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.Saida;
import br.arthur.entities.User;
import br.arthur.models.ContaPagarModel;
import br.arthur.models.ContaReceberModel;
import br.arthur.models.FormaPagtoModel;
import br.arthur.models.SaidaModel;

public class TestFinVen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtValorPago;
	private JTextField txtDesconto;
	private JPanel panel_1;
	private JButton cancelButton;
	private JPanel panel;
	private JTextField txtData1Pagto;
	
	private static HeaderSaida hSaida;
	private static Cliente cliente;
	private static User vendedor;

	private JLabel lblNumNF;
	private JLabel lblNomeCli;
	private JLabel lblTotalQtde;
	private JLabel lblTotalVenda;
	private JComboBox cmbPagto;
	private JComboBox cmbParcela;
	
	private double totalVenda = 0;
	private int totalQtde = 0;
	
	private SaidaModel sm = new SaidaModel();
	private FormaPagtoModel fpm = new FormaPagtoModel();
	private ContaPagarModel cpm = new ContaPagarModel();
	private ContaReceberModel crm = new ContaReceberModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TestFinVen dialog = new TestFinVen(hSaida, cliente, vendedor);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TestFinVen(HeaderSaida hSaida, Cliente cliente, User vendedor) {
		setTitle("Finalizar venda");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
		
		setBounds(100, 100, 523, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			panel_1 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 5, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 5, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_1, -5, SpringLayout.EAST, contentPanel);
			panel_1.setBorder(new TitledBorder(null, "Resumo da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			{
				JLabel label = new JLabel("Num. NF / S\u00E9rie:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel_1.add(label, gbc_label);
			}
			{
				lblNumNF = new JLabel("000000");
				GridBagConstraints gbc_lblNumNF = new GridBagConstraints();
				gbc_lblNumNF.insets = new Insets(0, 0, 5, 5);
				gbc_lblNumNF.gridx = 1;
				gbc_lblNumNF.gridy = 0;
				panel_1.add(lblNumNF, gbc_lblNumNF);
			}
			{
				JLabel label = new JLabel("Cliente:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 3;
				gbc_label.gridy = 0;
				panel_1.add(label, gbc_label);
			}
			{
				lblNomeCli = new JLabel("Arthur Santos Costa de Alcantara");
				GridBagConstraints gbc_lblNomeCli = new GridBagConstraints();
				gbc_lblNomeCli.insets = new Insets(0, 0, 5, 0);
				gbc_lblNomeCli.gridx = 4;
				gbc_lblNomeCli.gridy = 0;
				panel_1.add(lblNomeCli, gbc_lblNomeCli);
			}
			{
				JLabel label = new JLabel("Quantidade Itens:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				panel_1.add(label, gbc_label);
			}
			{
				lblTotalQtde = new JLabel("10");
				GridBagConstraints gbc_lblTotalQtde = new GridBagConstraints();
				gbc_lblTotalQtde.anchor = GridBagConstraints.EAST;
				gbc_lblTotalQtde.insets = new Insets(0, 0, 0, 5);
				gbc_lblTotalQtde.gridx = 1;
				gbc_lblTotalQtde.gridy = 1;
				panel_1.add(lblTotalQtde, gbc_lblTotalQtde);
			}
			{
				JLabel label = new JLabel("Valor Total:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 3;
				gbc_label.gridy = 1;
				panel_1.add(label, gbc_label);
			}
			{
				lblTotalVenda = new JLabel("R$ 1515,35");
				GridBagConstraints gbc_lblTotalVenda = new GridBagConstraints();
				gbc_lblTotalVenda.anchor = GridBagConstraints.WEST;
				gbc_lblTotalVenda.gridx = 4;
				gbc_lblTotalVenda.gridy = 1;
				panel_1.add(lblTotalVenda, gbc_lblTotalVenda);
			}
		}
		{
			panel = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, panel_1);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, panel_1);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, contentPanel);
			panel.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("Forma Pagto:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				
				cmbPagto = new JComboBox();
				
				Iterator formasPgto = fpm.findAll().iterator();
				
				while(formasPgto.hasNext()) {
					FormaPagto fp = (FormaPagto) formasPgto.next();
					cmbPagto.addItem(fp.getName());
				}
				
				cmbPagto.setSelectedItem("dinheiro");
				GridBagConstraints gbc_cmbPagto = new GridBagConstraints();
				gbc_cmbPagto.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbPagto.gridwidth = 4;
				gbc_cmbPagto.insets = new Insets(0, 0, 5, 5);
				gbc_cmbPagto.gridx = 1;
				gbc_cmbPagto.gridy = 0;
				panel.add(cmbPagto, gbc_cmbPagto);
			}
			{
				JLabel label = new JLabel("Parcelas:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 5;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				cmbParcela = new JComboBox();
				cmbParcela.addItemListener(new ItemListener() {
			        public void itemStateChanged(ItemEvent arg0) {
			            System.out.println("mudou o combo da parcela");
			        }
			    });
				cmbParcela.setModel(new DefaultComboBoxModel(new String[] {"1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x"}));
				GridBagConstraints gbc_cmbParcela = new GridBagConstraints();
				gbc_cmbParcela.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbParcela.insets = new Insets(0, 0, 5, 5);
				gbc_cmbParcela.gridx = 6;
				gbc_cmbParcela.gridy = 0;
				panel.add(cmbParcela, gbc_cmbParcela);
			}
			{
				JLabel label = new JLabel("Valor Pago:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 7;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				txtValorPago = new JTextField();
				txtValorPago.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {

					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {

					}

				});
				txtValorPago.setText("0.00");
				txtValorPago.setColumns(10);
				GridBagConstraints gbc_txtValorPago = new GridBagConstraints();
				gbc_txtValorPago.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtValorPago.insets = new Insets(0, 0, 5, 0);
				gbc_txtValorPago.gridx = 8;
				gbc_txtValorPago.gridy = 0;
				panel.add(txtValorPago, gbc_txtValorPago);
			}
			{
				JLabel label = new JLabel("Desconto:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				txtDesconto = new JTextField();
				txtDesconto.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {

					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {

					}

				});
				txtDesconto.setText("0.00");
				txtDesconto.setColumns(10);
				GridBagConstraints gbc_txtDesconto = new GridBagConstraints();
				gbc_txtDesconto.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDesconto.gridwidth = 4;
				gbc_txtDesconto.insets = new Insets(0, 0, 5, 5);
				gbc_txtDesconto.gridx = 1;
				gbc_txtDesconto.gridy = 1;
				panel.add(txtDesconto, gbc_txtDesconto);
			}
			{
				JLabel label = new JLabel("Total Pago:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 5;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblR = new JLabel("R$ 0,00");
				GridBagConstraints gbc_lblR = new GridBagConstraints();
				gbc_lblR.insets = new Insets(0, 0, 5, 5);
				gbc_lblR.gridx = 6;
				gbc_lblR.gridy = 1;
				panel.add(lblR, gbc_lblR);
			}
			{
				JLabel label = new JLabel("Troco:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 7;
				gbc_label.gridy = 1;
				panel.add(label, gbc_label);
			}
			{
				JLabel lblR_1 = new JLabel("R$ 0,00");
				GridBagConstraints gbc_lblR_1 = new GridBagConstraints();
				gbc_lblR_1.insets = new Insets(0, 0, 5, 0);
				gbc_lblR_1.gridx = 8;
				gbc_lblR_1.gridy = 1;
				panel.add(lblR_1, gbc_lblR_1);
			}
		}
		{
			
			JLabel lblNewLabel = new JLabel("Data 1\u00BA Pagto:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			panel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			
			txtData1Pagto = new JTextField();
			GridBagConstraints gbc_txtData1Pagto = new GridBagConstraints();
			gbc_txtData1Pagto.gridwidth = 4;
			gbc_txtData1Pagto.insets = new Insets(0, 0, 0, 5);
			gbc_txtData1Pagto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtData1Pagto.gridx = 1;
			gbc_txtData1Pagto.gridy = 2;
			panel.add(txtData1Pagto, gbc_txtData1Pagto);
			txtData1Pagto.setColumns(10);
			cancelButton = new JButton("Retornar p/ Venda");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_cancelButton.gridwidth = 2;
			gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
			gbc_cancelButton.gridx = 5;
			gbc_cancelButton.gridy = 2;
			panel.add(cancelButton, gbc_cancelButton);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, contentPanel);
			cancelButton.setActionCommand("Cancel");
			JButton okButton = new JButton("Confirmar Venda");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boolean isValid = true;
					double valorPago = 0, desconto = 0;
					if(txtValorPago.getText().trim().isEmpty() && txtDesconto.getText().trim().isEmpty()) {
						isValid = false;
					} else {
						valorPago = Double.parseDouble(txtValorPago.getText()) * (cmbParcela.getSelectedIndex() + 1);
						if (!txtDesconto.getText().trim().isEmpty()) {
							desconto +=  Double.parseDouble(txtDesconto.getText());
						}
						if(totalVenda > (valorPago + desconto)) {
							isValid = false;
							JOptionPane.showMessageDialog(null, "O Valor pago não pode ser inferior ao valor total da venda. Verifique se o desconto concedido está correto!");
						}
					}
					if(isValid) {
						JOptionPane.showMessageDialog(null, "Liberado para prosseguir com a venda");
					}
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_okButton.gridwidth = 2;
			gbc_okButton.gridx = 7;
			gbc_okButton.gridy = 2;
			panel.add(okButton, gbc_okButton);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, okButton, 215, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, okButton, 0, SpringLayout.EAST, panel_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, cancelButton, -129, SpringLayout.WEST, okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		List produtos = sm.findWhere("header_saida_fk", String.valueOf(hSaida.getId()));
		
		for(Object o : produtos) {
			Saida s = (Saida) o;
			
			totalVenda += s.getEntrada().getVenda() * s.getQuantidate();
			totalQtde += s.getQuantidate();
		}
		
		lblNumNF.setText(String.valueOf(hSaida.getId()));
		lblNomeCli.setText(hSaida.getCliente().getNome());
		lblTotalQtde.setText(String.valueOf(totalQtde));
		lblTotalVenda.setText("R$ " + totalVenda);
		
				
	}
}
