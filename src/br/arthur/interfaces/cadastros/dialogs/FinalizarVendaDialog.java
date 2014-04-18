package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
import br.arthur.utils.ButtonDoubleValueEditorUtil;
import br.arthur.utils.ButtonRendererUtil;
import br.arthur.utils.JNumericField;

public class FinalizarVendaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JNumericField txtDesconto;
	private JPanel panel_1;
	private JButton cancelButton;

	private static HeaderSaida hSaida;
	private static Cliente cliente;
	private static User vendedor;

	private JLabel lblNumNF;
	private JLabel lblNomeCli;
	private JLabel lblTotalQtde;
	private JLabel lblTotalVenda;
	private JLabel lblTroco;
	private static double totalVenda = 0;
	private int totalQtde = 0;
	private double desconto = 0;
	private double totalPago = 0;
	private byte parc = 1;
	private Vector<Double> valores = new Vector();

	public boolean vendaFinalizada = false;

	private JLabel lblTotalPago;

	private HeaderSaidaModel hsm = new HeaderSaidaModel();
	private SaidaModel sm = new SaidaModel();
	private FormaPagtoModel fpm = new FormaPagtoModel();

	private ContaPagarModel cpm = new ContaPagarModel();
	private ContaReceberModel crm = new ContaReceberModel();

	private boolean isValid = true;
	private String msgErro = "";
	private JTable table;

	private Object colNames[] = { "Cód. Venda", "Valor Pago" };

	private Object[][] data = { { "Dinheiro", 0.0 }, { "Débito", 0.0 },
			{ "Crédito", 0.0 }, { "Cheque", 0.0 }, { "Pendência", 0.0 } };

	private DefaultTableModel dtm;
	private JTextField txtData1Pagto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FinalizarVendaDialog dialog = new FinalizarVendaDialog(hSaida,
					cliente, vendedor);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FinalizarVendaDialog(final HeaderSaida hSaida, Cliente cliente,
			User vendedor) {
		List produtos = sm.findWhere("header_saida_fk",
				String.valueOf(hSaida.getId()));

		totalVenda = 0;
		totalQtde = 0;
		for (Object o : produtos) {
			Saida s = (Saida) o;

			totalVenda += s.getEntrada().getVenda() * s.getQuantidate();
			totalQtde += s.getQuantidate();
		}

		setTitle("Finalizar venda");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 523, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			panel_1 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 5,
					SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 5,
					SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_1, -5,
					SpringLayout.EAST, contentPanel);
			panel_1.setBorder(new TitledBorder(null, "Resumo da Venda",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_1);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
			gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, Double.MIN_VALUE };
			gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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
				gbc_lblNumNF.anchor = GridBagConstraints.WEST;
				gbc_lblNumNF.gridwidth = 3;
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
				gbc_label.gridx = 5;
				gbc_label.gridy = 0;
				panel_1.add(label, gbc_label);
			}
			{
				lblNomeCli = new JLabel("Arthur Santos Costa de Alcantara");
				GridBagConstraints gbc_lblNomeCli = new GridBagConstraints();
				gbc_lblNomeCli.insets = new Insets(0, 0, 5, 0);
				gbc_lblNomeCli.gridx = 6;
				gbc_lblNomeCli.gridy = 0;
				panel_1.add(lblNomeCli, gbc_lblNomeCli);
			}
			lblNomeCli.setText(hSaida.getCliente().getNome());
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
			{

				Iterator formasPgto = fpm.findAll().iterator();

				while (formasPgto.hasNext()) {
					FormaPagto fp = (FormaPagto) formasPgto.next();
					// cmbPagto.addItem(fp.getName());
				}
			}
		}

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();

		lblNumNF.setText(String.valueOf(hSaida.getId()));
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

		JPanel panel_2 = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_2, 6,
				SpringLayout.SOUTH, panel_1);
		panel_2.setBorder(new TitledBorder(null, "Formas de Pagamento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel_2, 0,
				SpringLayout.WEST, panel_1);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel_2, 0,
				SpringLayout.EAST, panel_1);
		contentPanel.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel_2.add(scrollPane, "name_5280528436765");

			dtm = new DefaultTableModel(data, colNames) {
				public Class<?> getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}

				boolean[] columnEditables = new boolean[] { false, true};

				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}

			};

			table = new JTable(dtm);
			
			table.getModel().addTableModelListener(new TableModelListener() {

				  public void tableChanged(TableModelEvent e) {
					  if (e.getColumn() == 1) {
						  Double val = Double.parseDouble( (String) table.getValueAt(e.getLastRow(), e.getColumn()));
						  
						  if (val >= 0.0) {
							  valores.setElementAt(val, e.getLastRow());
						  }
						  
						  totalPago = 0.0;
						  
						  for (int i = 0; i < valores.size(); i += 1) {
							  totalPago +=  valores.get(i);
						  }
						 
						  lblTotalPago.setText("R$ " + totalPago); 
						  
						  calcularValorPagoETroco();
						  
					  }
				  }
			    });

			TableColumn tcButton = table.getColumnModel().getColumn(1);
			
			tcButton.setCellRenderer(new ButtonRendererUtil());
			tcButton.setCellEditor(new ButtonDoubleValueEditorUtil(new JCheckBox()));
			
			for(int rc = 0; rc < table.getRowCount(); rc += 1) {
				valores.add(0.0);
			}

			scrollPane.setViewportView(table);
		}
		{
			JPanel panel_3 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_3, 226,
					SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_3, -10,
					SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel_2, -6,
					SpringLayout.NORTH, panel_3);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_3, 0,
					SpringLayout.WEST, panel_1);
			sl_contentPanel.putConstraint(SpringLayout.EAST, panel_3, 0,
					SpringLayout.EAST, panel_1);
			panel_3.setBorder(new TitledBorder(null, "Finalizando a Venda",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[] { 0, 90, 0, 0, 0, 0, 0 };
			gbl_panel_3.rowHeights = new int[] { 0, 0, 0 };
			gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, Double.MIN_VALUE };
			gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			panel_3.setLayout(gbl_panel_3);
			{
				JLabel label = new JLabel("Desconto:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel_3.add(label, gbc_label);
			}
			{
				txtDesconto = new JNumericField();
				GridBagConstraints gbc_txtDesconto = new GridBagConstraints();
				gbc_txtDesconto.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDesconto.insets = new Insets(0, 0, 5, 5);
				gbc_txtDesconto.gridx = 1;
				gbc_txtDesconto.gridy = 0;
				panel_3.add(txtDesconto, gbc_txtDesconto);
				txtDesconto.setMaxLength(6);

				txtDesconto.setText("0.00");
				txtDesconto.setColumns(10);
			}
			{
				JLabel label = new JLabel("Total Pago:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 2;
				gbc_label.gridy = 0;
				panel_3.add(label, gbc_label);
			}
			{
				lblTotalPago = new JLabel("R$ 0,0");
				GridBagConstraints gbc_lblTotalPago = new GridBagConstraints();
				gbc_lblTotalPago.insets = new Insets(0, 0, 5, 5);
				gbc_lblTotalPago.gridx = 3;
				gbc_lblTotalPago.gridy = 0;
				panel_3.add(lblTotalPago, gbc_lblTotalPago);
			}
			{
				JLabel label = new JLabel("Troco:");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 4;
				gbc_label.gridy = 0;
				panel_3.add(label, gbc_label);
			}
			{
				lblTroco = new JLabel("R$ 0,0");
				GridBagConstraints gbc_lblTroco = new GridBagConstraints();
				gbc_lblTroco.insets = new Insets(0, 0, 5, 0);
				gbc_lblTroco.gridx = 5;
				gbc_lblTroco.gridy = 0;
				panel_3.add(lblTroco, gbc_lblTroco);
			}
			{

				JLabel lblNewLabel = new JLabel("Data 1\u00BA Pagto:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 1;
				panel_3.add(lblNewLabel, gbc_lblNewLabel);
			}

			try {
				txtData1Pagto = new JFormattedTextField(new MaskFormatter(
						"##/##/####"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txtData1Pagto.setText(df.format(today));
			GridBagConstraints gbc_txtData1Pagto = new GridBagConstraints();
			gbc_txtData1Pagto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtData1Pagto.insets = new Insets(0, 0, 0, 5);
			gbc_txtData1Pagto.gridx = 1;
			gbc_txtData1Pagto.gridy = 1;
			panel_3.add(txtData1Pagto, gbc_txtData1Pagto);
			txtData1Pagto.setColumns(10);

			// txtData1Pagto.setText(df.format(today));
			{
				cancelButton = new JButton("Retornar para compras");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_cancelButton.gridwidth = 2;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 2;
				gbc_cancelButton.gridy = 1;
				panel_3.add(cancelButton, gbc_cancelButton);
				sl_contentPanel.putConstraint(SpringLayout.SOUTH, cancelButton,
						0, SpringLayout.SOUTH, contentPanel);
				cancelButton.setActionCommand("Cancel");
			}
			JButton okButton = new JButton("Finalizar esta venda");
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_okButton.gridwidth = 2;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 4;
			gbc_okButton.gridy = 1;
			panel_3.add(okButton, gbc_okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Date today = new Date();

					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy");
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
						msgErro += "\nA data de hoje não pode ser maior do que a data do primeiro pagamento!\n";
						isValid = false;
					} else {
						isValid = true;
					}
			    	
			    	calcularValorPagoETroco();
			    	
			    	if (!isValid) {
						JOptionPane.showMessageDialog(null, msgErro);
					} else {
						byte parc = 0;
						
						HashMap<String, Object> dataHSaida = new HashMap<String, Object>();
						
						for (int i = 0; i < table.getRowCount(); i += 1) {
													
							
							System.out.println("\nForma Pagto = " + table.getValueAt(i, 0) +
									"Valor Pago = " + table.getValueAt(i, 1) + "\n");
							
							System.out.println(table.getValueAt(i, 1).getClass().getName());
							
							if ( table.getValueAt(i, 1).getClass().getName().equals("java.lang.String") ) {
								if (Double.parseDouble((String) table.getValueAt(i, 1)) > 0) {
									HashMap<String, Object> dataReceber = new HashMap<String, Object>();
									HashMap<String, Object> dataPagar = new HashMap<String, Object>();	
									
									double valorPago = Double.parseDouble((String) table.getValueAt(i, 1));
											
									parc += 1;

									dataReceber.put("headerSaida", hSaida);
									dataReceber.put("dataVencimento", new java.sql.Date(cal2.getTimeInMillis()));
									dataReceber.put("formaPagto", fpm.findOneWhere("name", "'" +  table.getValueAt(i, 0) + "'"));
									dataReceber.put("valor", valorPago);
									dataReceber.put("parcela", parc);
									
									if (sameDay && !((String) table.getValueAt(i, 0)).equals("Pendência")) {
										dataReceber.put("pagto", true);
										dataReceber.put("dataPagto", new java.sql.Date(new Date().getTime()));
										
										List produtos = sm.findWhere("header_saida_fk", String.valueOf(hSaida.getId()));
										
										Calendar c = Calendar.getInstance();
										c.setTime(new Date());
										c.set(Calendar.DATE, c.getMaximum(Calendar.DATE));
										
										for(Object o : produtos) {
											
											Saida s = (Saida) o;
											
											double margemConsig = (100 - s.getEntrada().getMargeVenda()) / 100 ;
											
											Consignatario consig = s.getEntrada().getConsignatario();
											
											dataPagar.put("consignatario", consig);
											dataPagar.put("headerSaida", hSaida);
											dataPagar.put("dataVencimento", new java.sql.Date(c.getTimeInMillis()));
											dataPagar.put("pagto", false);
											dataPagar.put("valorPago", (s.getEntrada().getVenda() * margemConsig));
											
											cpm.criarContaPagar(dataPagar);									
										}
										
									} else {
										dataReceber.put("pagto", false);
										dataReceber.put("dataPagto", null);
									}
									
									crm.criarContaReceber(dataReceber);
									
								}

							}
							
						}
						
						dataHSaida.put("totalVenda", totalPago);
						dataHSaida.put("desconto", desconto);
						dataHSaida.put("totalParcela", parc);
						
						hsm.updateVenda(hSaida.getId(), dataHSaida);
						
						vendaFinalizada = true;
						dispose();
						
					} 
				}
			});
			sl_contentPanel.putConstraint(SpringLayout.NORTH, okButton, 215,
					SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, okButton, 0,
					SpringLayout.EAST, panel_1);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			sl_contentPanel.putConstraint(SpringLayout.EAST, cancelButton,
					-129, SpringLayout.WEST, okButton);

			txtDesconto.getDocument().addDocumentListener(
					new DocumentListener() {

						@Override
						public void changedUpdate(DocumentEvent arg0) {
							calcularValorPagoETroco();
							if (!txtDesconto.getText().trim().isEmpty()
									&& totalVenda > 0) {
								desconto = Double.parseDouble(txtDesconto.getText());
								calcularValorPagoETroco();
							} else {
								lblTotalPago.setText("R$ 0.00");
								lblTroco.setText("R$ 0.00");
							}
						}

						@Override
						public void insertUpdate(DocumentEvent arg0) {
							calcularValorPagoETroco();
							if (!txtDesconto.getText().trim().isEmpty()
									&& totalVenda > 0) {
								desconto = Double.parseDouble(txtDesconto.getText());
								calcularValorPagoETroco();
							} else {
								lblTotalPago.setText("R$ 0.00");
								lblTroco.setText("R$ 0.00");
							}
						}

						@Override
						public void removeUpdate(DocumentEvent arg0) {
							calcularValorPagoETroco();
							if (!txtDesconto.getText().trim().isEmpty()
									&& totalVenda > 0) {
								desconto = Double.parseDouble(txtDesconto.getText());
								calcularValorPagoETroco();
							} else {
								lblTotalPago.setText("R$ 0.00");
								lblTroco.setText("R$ 0.00");
							}
						}

					});
		}
	}
	
	protected void calcularValorPagoETroco() {
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
