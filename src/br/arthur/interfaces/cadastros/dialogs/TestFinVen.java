package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import br.arthur.entities.Cliente;
import br.arthur.entities.Consignatario;
import br.arthur.entities.FormaPagto;
import br.arthur.entities.HeaderSaida;
import br.arthur.entities.Saida;
import br.arthur.entities.User;
import br.arthur.models.ContaPagarModel;
import br.arthur.models.ContaReceberModel;
import br.arthur.models.FormaPagtoModel;
import br.arthur.models.HeaderSaidaModel;
import br.arthur.models.SaidaModel;
import br.arthur.utils.JNumericField;

public class TestFinVen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JNumericField txtValorPago;
	private JNumericField txtDesconto;
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
	private JLabel lblTroco;
	private static double totalVenda = 0;
	private int totalQtde = 0;
	private double desconto = 0;
	private double totalPago = 0;
	private byte parc = 1;
	private double valorPago;
	
	public boolean vendaFinalizada = false;
	
	private JLabel lblTotalPago;
	
	private HeaderSaidaModel hsm = new HeaderSaidaModel();
	private SaidaModel sm = new SaidaModel();
	private FormaPagtoModel fpm = new FormaPagtoModel();
	
	private ContaPagarModel cpm = new ContaPagarModel();
	private ContaReceberModel crm = new ContaReceberModel();
	
	private boolean isValid = true;
	private String msgErro = "";

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
	public TestFinVen(final HeaderSaida hSaida, Cliente cliente, User vendedor) {
		List produtos = sm.findWhere("header_saida_fk", String.valueOf(hSaida.getId()));
		
		totalVenda = 0;
		totalQtde = 0;
		for(Object o : produtos) {
			Saida s = (Saida) o;
			
			totalVenda += s.getEntrada().getVenda() * s.getQuantidate();
			totalQtde += s.getQuantidate();
		}
		
		valorPago = totalVenda;
		
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
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				gbc_lblNomeCli.insets = new Insets(0, 0, 5, 5);
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
				gbc_lblTotalVenda.insets = new Insets(0, 0, 0, 5);
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
			        	if (!txtValorPago.getText().trim().isEmpty() && !txtDesconto.getText().trim().isEmpty()) {
			        		calcularValorPagoETroco();
				        	txtValorPago.setText( String.format(Locale.US, "%.2f" , ( (totalVenda - desconto) / parc) ) );
			        	}			        	
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
				txtValorPago = new JNumericField();
				txtValorPago.setMaxLength(6);
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
				txtDesconto = new JNumericField();
				txtDesconto.setMaxLength(6);
				
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
				lblTotalPago = new JLabel("R$ 0,00");
				GridBagConstraints gbc_lblTotalPago = new GridBagConstraints();
				gbc_lblTotalPago.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotalPago.gridx = 6;
				gbc_lblTotalPago.gridy = 1;
				panel.add(lblTotalPago, gbc_lblTotalPago);
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
				lblTroco = new JLabel("R$ 0,00");
				GridBagConstraints gbc_lblTroco = new GridBagConstraints();
				gbc_lblTroco.insets = new Insets(0, 0, 5, 0);
				gbc_lblTroco.gridx = 8;
				gbc_lblTroco.gridy = 1;
				panel.add(lblTroco, gbc_lblTroco);
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
			
			try {
				txtData1Pagto = new JFormattedTextField(new MaskFormatter("##/##/####"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
					Date today = new Date();
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String data1pagto = txtData1Pagto.getText();
					Date datePagto = null;
					try {
						datePagto = formatter.parse(data1pagto);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Calendar cal1 = Calendar.getInstance();
			    	Calendar cal2 = Calendar.getInstance();

			    	
			    	cal1.setTime(today);
			    	cal2.setTime(datePagto);
			    	
			    	boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
			                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
			    	
			    	if(!cal1.after(cal2) || !sameDay) {
						JOptionPane.showMessageDialog(null, "A data de hoje não pode ser maior do que a data do primeiro pagamento!");
						isValid = false;
					} else {
						isValid = true;
					}
			    	
			    	calcularValorPagoETroco();
					
					if (!isValid) {
						JOptionPane.showMessageDialog(null, msgErro);
					} else {
						HashMap<String, Object> data = new HashMap<String, Object>();
						
						data.put("totalVenda", (totalVenda - desconto));

						FormaPagto pagtoSelecionado = FormaPagtoModel.findOneWhere("name",
								"'" + cmbPagto.getSelectedItem() + "'");
						data.put("formaPagto", pagtoSelecionado);
						
						hsm.updateVenda(hSaida.getId(), data);
						
						for (int i = 0; i < parc; i += 1) {
							HashMap<String, Object> dataReceber = new HashMap<String, Object>();
							
							dataReceber.put("headerSaida", hSaida);
							dataReceber.put("dataVencimento", new java.sql.Date(cal2.getTimeInMillis()));
							dataReceber.put("valor", valorPago);
							dataReceber.put("desconto", desconto);
							dataReceber.put("dataPagto", null);
							dataReceber.put("parcela", (byte) (i + 1) );
							dataReceber.put("totalParcela", (byte) parc);
							
							if (sameDay && i == 0) {
								dataReceber.put("pagto", true);
							} else {
								dataReceber.put("pagto", false);
							}
							
							crm.criarContaReceber(dataReceber);
							
							HashMap<String, Object> dataPagar = new HashMap<String, Object>();
							
							List produtos = sm.findWhere("header_saida_fk", String.valueOf(hSaida.getId()));
							
							for(Object o : produtos) {
								
								Saida s = (Saida) o;
								
								double margemConsig = (100 - s.getEntrada().getMargeVenda()) / 100 ;
								double totalValorConsig = s.getEntrada().getVenda() * margemConsig;
								double valorParc = totalValorConsig / parc;
								
								Consignatario consig = s.getEntrada().getConsignatario();
								
								dataPagar.put("consignatario", consig);
								dataPagar.put("contaReceber", crm.getEntity());
								dataPagar.put("dataVencimento", new java.sql.Date(cal2.getTimeInMillis()));
								dataPagar.put("pagto", false);
								dataPagar.put("valorPago", valorParc);
								
								cpm.criarContaPagar(dataPagar);									
							}
							cal2.add(Calendar.MONTH, 1);
						}
						
						vendaFinalizada = true;
						dispose();
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
		
		

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		
		txtData1Pagto.setText(df.format(today));
		
		txtValorPago.setText(String.format(Locale.US, "%.2f", totalVenda));
		
		lblNumNF.setText(String.valueOf(hSaida.getId()));
		lblNomeCli.setText(hSaida.getCliente().getNome());
		lblTotalQtde.setText(String.valueOf(totalQtde));
		lblTotalVenda.setText("R$ " + totalVenda);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		GridBagConstraints gbc_lblVendedor = new GridBagConstraints();
		gbc_lblVendedor.insets = new Insets(0, 0, 0, 5);
		gbc_lblVendedor.gridx = 5;
		gbc_lblVendedor.gridy = 1;
		panel_1.add(lblVendedor, gbc_lblVendedor);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setText(vendedor.getName());
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 6;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtValorPago.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (!txtValorPago.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!txtValorPago.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (!txtValorPago.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

		});
		
		txtDesconto.getDocument().addDocumentListener(new DocumentListener() {


			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (!txtDesconto.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!txtDesconto.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (!txtDesconto.getText().trim().isEmpty() && totalVenda > 0) {
					calcularValorPagoETroco();
				} else {
					lblTotalPago.setText("R$ 0.00");
					lblTroco.setText("R$ 0.00");
				}
			}

		});
	}

	protected void calcularValorPagoETroco() {
		String p = cmbParcela.getSelectedItem().toString();
		parc = Byte.parseByte(p.substring(0, p.length() - 1));
		desconto = Double.parseDouble(txtDesconto.getText());	
		valorPago = Double.parseDouble(txtValorPago.getText());		
		totalPago = valorPago * parc;
		
		double troco = (totalPago + desconto) - totalVenda;
		
		if ((totalPago + desconto) < totalVenda) {
			isValid = false;
			msgErro = "O 'valor pago' não pode ser inferior ao 'total da venda'! \nValor Pago: " + totalPago + 
					"\nDesconto Concedido: " + desconto + 
					"\nValor Pago + Desconto: " + (totalPago + desconto) + 
					"\nTotal da Venda: " + totalVenda;
			lblTroco.setText("R$ 0.00");
			lblTotalPago.setText("R$ " + String.format("%.2f", totalPago));
		} else {
			isValid = true;
			lblTroco.setText("R$ " + String.format("%.2f", troco));
			lblTotalPago.setText("R$ " + String.format("%.2f", totalPago));
		}
	}

}
