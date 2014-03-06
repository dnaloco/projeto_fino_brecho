package br.arthur.interfaces.cadastros;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.arthur.entities.Cliente;
import br.arthur.interfaces.cadastros.dialogs.ClienteDialog;
import br.arthur.models.ClienteModel;

public class CadastroCliente extends JInternalFrame {
	private JTextField txtCelular;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtSite;
	private JTextField txtAniversario;
	private JTextField txtNome;
	private JTextArea textAreaObserv;
	private static int theId;
	private JLabel lblNovo;
	
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	
	private JLabel lblUltCompra;
	private JLabel lblPend;

	private ClienteModel cm = new ClienteModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente(theId);
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
	public CadastroCliente(int id) {
		setFrameIcon(new ImageIcon(
				"images/clients.png"));
		this.theId = id;
		setClosable(true);
		setIconifiable(true);
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 442, 323);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblClienteId = new JLabel("Cliente ID:");
		lblClienteId.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblClienteId, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblClienteId, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblClienteId);
		
		lblNovo = new JLabel("Novo");
		lblNovo.setFont(new Font("SansSerif", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblNovo, 0, SpringLayout.NORTH, lblClienteId);
		springLayout.putConstraint(SpringLayout.WEST, lblNovo, 6, SpringLayout.EAST, lblClienteId);
		getContentPane().add(lblNovo);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -44, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				HashMap<String, Object> data= new HashMap();
				
				String msgErro = "";
				
				boolean isValid = true;
				
				if(txtNome.getText().trim().isEmpty()) {
					msgErro += "O campo 'nome' deve ser informado!\n";
					isValid = false;
				}
				
				if(isValid) {
					data.put("nome", txtNome.getText());
					data.put("telefone", txtTelefone.getText());
					data.put("celular", txtCelular.getText());
					data.put("email", txtEmail.getText());
					data.put("site", txtSite.getText());
					Calendar aniverCal = Calendar.getInstance();
					if(txtAniversario.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
						String[] aniverStr = txtAniversario.getText().split("/");
						int ano = Integer.parseInt(aniverStr[2]);
						int mes = Integer.parseInt(aniverStr[1]) + 1;
						int dia = Integer.parseInt(aniverStr[0]);
						aniverCal.set(ano, mes, dia);
						data.put("aniver", new java.sql.Date(aniverCal.getTime().getTime()));
					} else {
						data.put("aniver", null);
					}
					data.put("pendencia", false);
					data.put("observ", textAreaObserv.getText());
					String msgSuccess =  "";
					
					
					if(theId > 0) {
						cm.saveCliente(theId, data);
						msgSuccess = "Cliente salvo com sucesso!";
					} else {
						theId = cm.createCliente(data);
						lblNovo.setText(String.valueOf(theId));
						
						msgSuccess = "Cliente criado com sucesso!";
					}
					
					if(theId > 0) {
						JOptionPane.showMessageDialog(null, msgSuccess);
						enabledEditionButtons();
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir o cliente.");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}

			}
			
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, -127, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theId = 0;
				limparCampos();
			}
		});
		btnCancelar.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 0, SpringLayout.WEST, lblClienteId);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, 0, SpringLayout.EAST, lblNovo);
		getContentPane().add(btnCancelar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 0, SpringLayout.NORTH, btnSalvar);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -72, SpringLayout.WEST, btnSalvar);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 3;
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 1;
		panel.add(lblTelefone, gbc_lblTelefone);
		
		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 1;
		panel.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.EAST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 5);
		gbc_lblCelular.gridx = 2;
		gbc_lblCelular.gridy = 1;
		panel.add(lblCelular, gbc_lblCelular);
		
		txtCelular = new JTextField();
		GridBagConstraints gbc_txtCelular = new GridBagConstraints();
		gbc_txtCelular.anchor = GridBagConstraints.NORTH;
		gbc_txtCelular.insets = new Insets(0, 0, 5, 0);
		gbc_txtCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCelular.gridx = 3;
		gbc_txtCelular.gridy = 1;
		panel.add(txtCelular, gbc_txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 2;
		panel.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSite = new JLabel("Site:");
		GridBagConstraints gbc_lblSite = new GridBagConstraints();
		gbc_lblSite.anchor = GridBagConstraints.EAST;
		gbc_lblSite.insets = new Insets(0, 0, 5, 5);
		gbc_lblSite.gridx = 2;
		gbc_lblSite.gridy = 2;
		panel.add(lblSite, gbc_lblSite);
		
		txtSite = new JTextField();
		GridBagConstraints gbc_txtSite = new GridBagConstraints();
		gbc_txtSite.insets = new Insets(0, 0, 5, 0);
		gbc_txtSite.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSite.gridx = 3;
		gbc_txtSite.gridy = 2;
		panel.add(txtSite, gbc_txtSite);
		txtSite.setColumns(10);
		
		JLabel lblAniversrio = new JLabel("Anivers\u00E1rio:");
		GridBagConstraints gbc_lblAniversrio = new GridBagConstraints();
		gbc_lblAniversrio.anchor = GridBagConstraints.EAST;
		gbc_lblAniversrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAniversrio.gridx = 0;
		gbc_lblAniversrio.gridy = 3;
		panel.add(lblAniversrio, gbc_lblAniversrio);
		
		try {
			txtAniversario = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GridBagConstraints gbc_txtAniversario = new GridBagConstraints();
		gbc_txtAniversario.insets = new Insets(0, 0, 5, 5);
		gbc_txtAniversario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAniversario.gridx = 1;
		gbc_txtAniversario.gridy = 3;
		panel.add(txtAniversario, gbc_txtAniversario);
		txtAniversario.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel lblPossuiPendncia = new JLabel("Possui pend\u00EAncia?");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblPossuiPendncia, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblPossuiPendncia, 0, SpringLayout.WEST, panel_1);
		panel_1.add(lblPossuiPendncia);
		
		lblPend = new JLabel("[S/N]");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblPend, 0, SpringLayout.NORTH, lblPossuiPendncia);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblPend, 0, SpringLayout.EAST, panel_1);
		panel_1.add(lblPend);
		
		JLabel lblltimaCompraid = new JLabel("\u00DAltima Compra(ID):");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblltimaCompraid, 6, SpringLayout.SOUTH, lblPossuiPendncia);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblltimaCompraid, 0, SpringLayout.EAST, lblPossuiPendncia);
		panel_1.add(lblltimaCompraid);
		
		lblUltCompra = new JLabel("000000");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblUltCompra, 6, SpringLayout.SOUTH, lblPend);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblUltCompra, 0, SpringLayout.EAST, lblPend);
		panel_1.add(lblUltCompra);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		GridBagConstraints gbc_lblObservaes = new GridBagConstraints();
		gbc_lblObservaes.anchor = GridBagConstraints.EAST;
		gbc_lblObservaes.insets = new Insets(0, 0, 0, 5);
		gbc_lblObservaes.gridx = 0;
		gbc_lblObservaes.gridy = 4;
		panel.add(lblObservaes, gbc_lblObservaes);
		
		textAreaObserv = new JTextArea();
		GridBagConstraints gbc_textAreaObserv = new GridBagConstraints();
		gbc_textAreaObserv.gridwidth = 3;
		gbc_textAreaObserv.fill = GridBagConstraints.BOTH;
		gbc_textAreaObserv.gridx = 1;
		gbc_textAreaObserv.gridy = 4;
		panel.add(textAreaObserv, gbc_textAreaObserv);
		getContentPane().add(btnExcluir);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm.findAll().size() > 0) {
					ClienteDialog cDiag = new ClienteDialog();
					cDiag.setVisible(true);

					theId = cDiag.getTheId();

					if (theId > 0) {
						populateCampos();
					}
				} else {
					JOptionPane
							.showMessageDialog(null,
									"Não existe ainda nenhum cliente registrado no sistema!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnBuscarCliente, -258, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel, 1, SpringLayout.SOUTH, btnBuscarCliente);
		springLayout.putConstraint(SpringLayout.WEST, btnBuscarCliente, -180, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBuscarCliente, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnBuscarCliente);

	}

	protected void populateCampos() {
		Cliente c = ClienteModel.findOneWhere("id", String.valueOf(theId));
		
		lblNovo.setText(String.valueOf(c.getId()));
		
		txtNome.setText(c.getNome());
		txtTelefone.setText(c.getTelefone());
		txtCelular.setText(c.getCelular());
		txtEmail.setText(c.getEmail());
		txtSite.setText(c.getSite());

		if (c.getAniversario() != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			txtAniversario.setText(df.format(c.getAniversario()));
		}
		
		if (c.isPendencia()) {
			lblPend.setText("Sim");
			lblPend.setForeground (Color.red);
		} else {
			lblPend.setText("Não");
			lblPend.setForeground (Color.black);
		}
		
		if (c.getUltimaCompra() != null) {
			lblUltCompra.setText(String.valueOf(c.getUltimaCompra().getId()));
		} else {
			lblUltCompra.setText("000000");
		}
		
		enabledEditionButtons();
	}

	protected void enabledEditionButtons() {
		btnCancelar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}
	protected void disabledEditionButtons() {
		btnCancelar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
	
	private void limparCampos() {
		lblNovo.setText("NOVO");
		
		txtNome.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
		txtSite.setText("");
		txtAniversario.setText("");
		textAreaObserv.setText("");
		
		lblPend.setText("S/N");
		lblUltCompra.setText("000000");
		
		disabledEditionButtons();
	}
}
