package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.hibernate.exception.ConstraintViolationException;

import br.arthur.entities.Consignatario;
import br.arthur.entities.Estado;
import br.arthur.interfaces.cadastros.dialogs.ConsignatarioDialog;
import br.arthur.models.ConsignatarioModel;
import br.arthur.models.EstadoModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class CadastroConsignatario extends JInternalFrame {
	private JTextField txtCelular;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtSite;
	private JTextField txtNome;
	private JTextField txtLogra;
	private JTextField txtComplem;
	private JTextField txtNumero;
	private JTextField txtCep;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cmbEstado;
	private JLabel lblSubtitle;
	private JButton btnCancelar;
	private JButton btnExcluir;
	
	private static int theId;
	
	private ConsignatarioModel cm = new ConsignatarioModel();
	private JTextField txtCpf;
	private JTextField txtRg;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroConsignatario frame = new CadastroConsignatario(theId);
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
	public CadastroConsignatario(int id) {
		this.theId = id;
		setClosable(true);
		setIconifiable(true);
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);
		setTitle("Cadastro de Consignatário");
		setBounds(100, 100, 484, 433);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		lblSubtitle = new JLabel("Cadastrando Novo Consignatário");
		lblSubtitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblSubtitle, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSubtitle, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblSubtitle);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, lblSubtitle);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 165, SpringLayout.SOUTH, lblSubtitle);
		springLayout.putConstraint(SpringLayout.EAST, panel, 468, SpringLayout.WEST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Contato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 3;
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 2;
		gbc_txtNome.gridy = 0;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 1;
		gbc_lblTelefone.gridy = 1;
		panel.add(lblTelefone, gbc_lblTelefone);
		
		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.anchor = GridBagConstraints.NORTH;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 2;
		gbc_txtTelefone.gridy = 1;
		panel.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.EAST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 5);
		gbc_lblCelular.gridx = 3;
		gbc_lblCelular.gridy = 1;
		panel.add(lblCelular, gbc_lblCelular);
		
		txtCelular = new JTextField();
		GridBagConstraints gbc_txtCelular = new GridBagConstraints();
		gbc_txtCelular.insets = new Insets(0, 0, 5, 0);
		gbc_txtCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCelular.gridx = 4;
		gbc_txtCelular.gridy = 1;
		panel.add(txtCelular, gbc_txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 2;
		panel.add(txtEmail, gbc_txtEmail);
		
		JLabel lblSite = new JLabel("Site:");
		GridBagConstraints gbc_lblSite = new GridBagConstraints();
		gbc_lblSite.anchor = GridBagConstraints.EAST;
		gbc_lblSite.insets = new Insets(0, 0, 5, 5);
		gbc_lblSite.gridx = 3;
		gbc_lblSite.gridy = 2;
		panel.add(lblSite, gbc_lblSite);
		
		txtSite = new JTextField();
		txtSite.setColumns(10);
		GridBagConstraints gbc_txtSite = new GridBagConstraints();
		gbc_txtSite.insets = new Insets(0, 0, 5, 0);
		gbc_txtSite.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSite.gridx = 4;
		gbc_txtSite.gridy = 2;
		panel.add(txtSite, gbc_txtSite);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -44, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endereco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		GridBagConstraints gbc_lblLogradouro = new GridBagConstraints();
		gbc_lblLogradouro.anchor = GridBagConstraints.EAST;
		gbc_lblLogradouro.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogradouro.gridx = 1;
		gbc_lblLogradouro.gridy = 0;
		panel_1.add(lblLogradouro, gbc_lblLogradouro);
		
		txtLogra = new JTextField();
		txtLogra.setColumns(10);
		GridBagConstraints gbc_txtLogra = new GridBagConstraints();
		gbc_txtLogra.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLogra.gridwidth = 3;
		gbc_txtLogra.insets = new Insets(0, 0, 5, 0);
		gbc_txtLogra.gridx = 2;
		gbc_txtLogra.gridy = 0;
		panel_1.add(txtLogra, gbc_txtLogra);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		GridBagConstraints gbc_lblNmero = new GridBagConstraints();
		gbc_lblNmero.anchor = GridBagConstraints.EAST;
		gbc_lblNmero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmero.gridx = 1;
		gbc_lblNmero.gridy = 1;
		panel_1.add(lblNmero, gbc_lblNmero);
		
		txtNumero = new JTextField();
		GridBagConstraints gbc_txtNumero = new GridBagConstraints();
		gbc_txtNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumero.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumero.gridx = 2;
		gbc_txtNumero.gridy = 1;
		panel_1.add(txtNumero, gbc_txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblComplem = new JLabel("Complem.:");
		GridBagConstraints gbc_lblComplem = new GridBagConstraints();
		gbc_lblComplem.anchor = GridBagConstraints.EAST;
		gbc_lblComplem.insets = new Insets(0, 0, 5, 5);
		gbc_lblComplem.gridx = 3;
		gbc_lblComplem.gridy = 1;
		panel_1.add(lblComplem, gbc_lblComplem);
		
		txtComplem = new JTextField();
		txtComplem.setColumns(10);
		GridBagConstraints gbc_txtComplem = new GridBagConstraints();
		gbc_txtComplem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComplem.insets = new Insets(0, 0, 5, 0);
		gbc_txtComplem.gridx = 4;
		gbc_txtComplem.gridy = 1;
		panel_1.add(txtComplem, gbc_txtComplem);
		
		JLabel lblBairro = new JLabel("Bairro:");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 1;
		gbc_lblBairro.gridy = 2;
		panel_1.add(lblBairro, gbc_lblBairro);
		
		txtBairro = new JTextField();
		GridBagConstraints gbc_txtBairro = new GridBagConstraints();
		gbc_txtBairro.insets = new Insets(0, 0, 5, 5);
		gbc_txtBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBairro.gridx = 2;
		gbc_txtBairro.gridy = 2;
		panel_1.add(txtBairro, gbc_txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		GridBagConstraints gbc_lblCep = new GridBagConstraints();
		gbc_lblCep.anchor = GridBagConstraints.EAST;
		gbc_lblCep.insets = new Insets(0, 0, 5, 5);
		gbc_lblCep.gridx = 3;
		gbc_lblCep.gridy = 2;
		panel_1.add(lblCep, gbc_lblCep);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		GridBagConstraints gbc_txtCep = new GridBagConstraints();
		gbc_txtCep.anchor = GridBagConstraints.NORTH;
		gbc_txtCep.insets = new Insets(0, 0, 5, 0);
		gbc_txtCep.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCep.gridx = 4;
		gbc_txtCep.gridy = 2;
		panel_1.add(txtCep, gbc_txtCep);
		
		JLabel lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 0, 5);
		gbc_lblCidade.gridx = 1;
		gbc_lblCidade.gridy = 3;
		panel_1.add(lblCidade, gbc_lblCidade);
		
		txtCidade = new JTextField();
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.insets = new Insets(0, 0, 0, 5);
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.gridx = 2;
		gbc_txtCidade.gridy = 3;
		panel_1.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.EAST;
		gbc_lblEstado.insets = new Insets(0, 0, 0, 5);
		gbc_lblEstado.gridx = 3;
		gbc_lblEstado.gridy = 3;
		panel_1.add(lblEstado, gbc_lblEstado);
		
		cmbEstado = new JComboBox();
		
		Iterator estados = EstadoModel.findAll().iterator();
		
		while(estados.hasNext()) {
			Estado e = (Estado) estados.next();
			cmbEstado.addItem(e.getName());
		}
		
		cmbEstado.setSelectedItem("SP");
		
		GridBagConstraints gbc_cmbEstado = new GridBagConstraints();
		gbc_cmbEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbEstado.gridx = 4;
		gbc_cmbEstado.gridy = 3;
		panel_1.add(cmbEstado, gbc_cmbEstado);
		
		JButton btnSalvar = new JButton("Salvar");
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnSalvar, 371, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, -4, SpringLayout.EAST, getContentPane());
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> data = new HashMap();
				String msgErro = "";
				
				boolean isValid = true;
				
				if(txtNome.getText().trim().isEmpty()) {
					msgErro += "O campo 'nome' deve ser informado!\n";
					isValid = false;
				}
				
				if(txtTelefone.getText().trim().isEmpty() && txtCelular.getText().trim().isEmpty()) {
					msgErro += "Um número de 'celular' ou 'telefone' deve ser informado\n";
					isValid = false;
				}

				if(isValid) {
					data.put("nome", txtNome.getText());
					data.put("telefone", txtTelefone.getText());
					data.put("celular", txtCelular.getText());
					data.put("email", txtEmail.getText());
					data.put("site", txtSite.getText());
					data.put("logra", txtLogra.getText());
					data.put("num", txtNumero.getText());
					data.put("complem", txtComplem.getText());
					data.put("bairro", txtBairro.getText());
					data.put("cep", txtCep.getText());
					data.put("cidade", txtCidade.getText());
					Estado ee = EstadoModel.findOneWhere("name", "'" + cmbEstado.getSelectedItem() + "'");
					data.put("estado", ee);
					data.put("cpf", txtCpf.getText());
					data.put("rg", txtRg.getText());
	
					String msgSuccess = "";
					if(theId > 0) {
						cm.saveConsignatario(theId, data);
						msgSuccess = "Consignatário salvo com sucesso!";
					} else {
						theId = cm.createConsignatario(data);
						lblSubtitle.setText("Usuário ID: " + theId);
						msgSuccess = "Consignatário inserido com sucesso!";
						btnCancelar.setEnabled(true);
						btnExcluir.setEnabled(true);
					}	
					if(theId > 0) {
						JOptionPane.showMessageDialog(null, msgSuccess);
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir o consignatário.");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, -365, SpringLayout.EAST, getContentPane());
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theId = 0;
				limparCampos();
			}
		});
		btnCancelar.setEnabled(false);
		getContentPane().add(btnCancelar);
		
		JButton button = new JButton((Icon) null);
		springLayout.putConstraint(SpringLayout.WEST, button, 6, SpringLayout.EAST, lblSubtitle);
		springLayout.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.NORTH, panel);
		
		JLabel lblCpf = new JLabel("CPF:");
		GridBagConstraints gbc_lblCpf = new GridBagConstraints();
		gbc_lblCpf.anchor = GridBagConstraints.EAST;
		gbc_lblCpf.insets = new Insets(0, 0, 0, 5);
		gbc_lblCpf.gridx = 1;
		gbc_lblCpf.gridy = 3;
		panel.add(lblCpf, gbc_lblCpf);
		
		txtCpf = new JTextField();
		GridBagConstraints gbc_txtCpf = new GridBagConstraints();
		gbc_txtCpf.insets = new Insets(0, 0, 0, 5);
		gbc_txtCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCpf.gridx = 2;
		gbc_txtCpf.gridy = 3;
		panel.add(txtCpf, gbc_txtCpf);
		txtCpf.setColumns(10);
		
		JLabel lblRg = new JLabel("R.G.:");
		GridBagConstraints gbc_lblRg = new GridBagConstraints();
		gbc_lblRg.anchor = GridBagConstraints.EAST;
		gbc_lblRg.insets = new Insets(0, 0, 0, 5);
		gbc_lblRg.gridx = 3;
		gbc_lblRg.gridy = 3;
		panel.add(lblRg, gbc_lblRg);
		
		txtRg = new JTextField();
		GridBagConstraints gbc_txtRg = new GridBagConstraints();
		gbc_txtRg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRg.gridx = 4;
		gbc_txtRg.gridy = 3;
		panel.add(txtRg, gbc_txtRg);
		txtRg.setColumns(10);
		springLayout.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, getContentPane());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ConsignatarioDialog cDiag = new ConsignatarioDialog();
				cDiag.setVisible(true);
				
				theId = cDiag.getTheId();
				
				if (theId > 0) {
					populateMe();
				}
			}
		});
		button.setText("Buscar Consignat\u00E1rio");
		button.setHorizontalTextPosition(SwingConstants.LEFT);
		getContentPane().add(button);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.deleteById(theId);
					limparCampos();
					JOptionPane.showMessageDialog(null, "Registro excluido");
				} catch (MySQLIntegrityConstraintViolationException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Não é possível excluir este consignatário. A pedidos ligados ao mesmo.");
					e1.printStackTrace();
				} catch (ConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null, "Não é possível excluir este consignatário. A pedidos ligados ao mesmo.");
					ex.printStackTrace();
				}
				
			}
		});
		btnExcluir.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 6, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, btnExcluir, 6, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -161, SpringLayout.WEST, btnSalvar);
		getContentPane().add(btnExcluir);
		
		if (theId >  0) {
			populateMe();
		}
	}
	
	private void limparCampos() {
		lblSubtitle.setText("Cadastrando Novo Consignatário");
		
		txtNome.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtEmail.setText("");
		txtSite.setText("");
		txtLogra.setText("");
		txtNumero.setText("");
		txtBairro.setText("");
		txtComplem.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		btnCancelar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	protected void populateMe() {		
		Consignatario c = ConsignatarioModel.findOneWhere("id", String.valueOf(this.theId));
		lblSubtitle.setText("Usuário ID: " + c.getId());
		txtNome.setText(c.getNome());
		txtTelefone.setText(c.getTelefone());
		txtCelular.setText(c.getCelular());
		txtEmail.setText(c.getEmail());
		txtSite.setText(c.getSite());
		txtLogra.setText(c.getLogradouro());
		txtNumero.setText(c.getBairro());
		txtBairro.setText(c.getNumero());
		txtComplem.setText(c.getComplemento());
		txtCep.setText(c.getCep());
		txtCidade.setText(c.getCidade());
		txtCpf.setText(c.getCpf());
		txtRg.setText(c.getRg());
		cmbEstado.setSelectedItem(c.getEstado().getName());
		btnCancelar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}
}
