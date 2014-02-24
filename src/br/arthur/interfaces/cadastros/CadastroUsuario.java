package br.arthur.interfaces.cadastros;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import br.arthur.entities.Group;
import br.arthur.entities.Status;
import br.arthur.models.GroupModel;
import br.arthur.models.StatusModel;
import br.arthur.models.UserModel;

public class CadastroUsuario extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passField;
	private JPasswordField confPassField;
	private JComboBox cmbStatus;
	private JComboBox cmbGroups;
	private StatusModel sm;
	private GroupModel gm;
	private UserModel um; 
	private JTextField txtMobile;
	private JTextField txtPhone;
	
	private static long theId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario(theId);
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
	public CadastroUsuario(long id) throws PropertyVetoException {
		theId = id;
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Usuário");
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);
		setBounds(100, 100, 357, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		String subtitle = id > 0 ? "Usuário ID: " + String.valueOf(id) : "Cadastrando um Novo Usuário.";
		
		JLabel lblSubtitle = new JLabel(subtitle);
		lblSubtitle.setForeground(Color.BLUE);
		lblSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSubtitle = new GridBagConstraints();
		gbc_lblSubtitle.gridwidth = 2;
		gbc_lblSubtitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubtitle.gridx = 0;
		gbc_lblSubtitle.gridy = 0;
		contentPane.add(lblSubtitle, gbc_lblSubtitle);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 1;
		contentPane.add(lblUsuario, gbc_lblUsuario);
		
		txtUser = new JTextField();
		txtUser.setToolTipText("O usu\u00E1rio deve ser \u00FAnico.");
		GridBagConstraints gbc_txtUser = new GridBagConstraints();
		gbc_txtUser.insets = new Insets(0, 0, 5, 0);
		gbc_txtUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUser.gridx = 1;
		gbc_txtUser.gridy = 1;
		contentPane.add(txtUser, gbc_txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 2;
		contentPane.add(lblNome, gbc_lblNome);
		
		txtName = new JTextField();
		txtName.setToolTipText("Escreva o seu nome completo.");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 2;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("Informe o seu e-mail para contato.");
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.EAST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 4;
		contentPane.add(lblSenha, gbc_lblSenha);
		
		passField = new JPasswordField();
		passField.setToolTipText("Digite a sua senha.");
		GridBagConstraints gbc_passField = new GridBagConstraints();
		gbc_passField.anchor = GridBagConstraints.NORTH;
		gbc_passField.insets = new Insets(0, 0, 5, 0);
		gbc_passField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passField.gridx = 1;
		gbc_passField.gridy = 4;
		contentPane.add(passField, gbc_passField);
		
		JLabel lblConfirmar = new JLabel("Confirmar");
		lblConfirmar.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmar.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblConfirmar = new GridBagConstraints();
		gbc_lblConfirmar.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmar.gridx = 0;
		gbc_lblConfirmar.gridy = 5;
		contentPane.add(lblConfirmar, gbc_lblConfirmar);
		
		confPassField = new JPasswordField();
		confPassField.setToolTipText("Confirme a sua senha.");
		GridBagConstraints gbc_confPassField = new GridBagConstraints();
		gbc_confPassField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confPassField.insets = new Insets(0, 0, 5, 0);
		gbc_confPassField.gridx = 1;
		gbc_confPassField.gridy = 5;
		contentPane.add(confPassField, gbc_confPassField);

		
		cmbStatus = new JComboBox();
		cmbStatus.setToolTipText("Selecione o status deste usu\u00E1rio.");

		sm = new StatusModel();
		Iterator<Status> status = sm.findAll().iterator();
		
		while(status.hasNext()) {
			Status s = (Status) status.next();
			cmbStatus.addItem(s.getName());
		}
		
		cmbGroups = new JComboBox();
		cmbGroups.setToolTipText("A qual grupo de permiss\u00F5es este usu\u00E1rio pertence?");
		
		gm = new GroupModel();
		Iterator groups = gm.findAll().iterator();
		
		while(groups.hasNext()) {
			Group g = (Group) groups.next();
			cmbGroups.addItem(g.getName());
		}
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnSalvarUsurio = new JButton("Salvar Usu\u00E1rio");
		btnSalvarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashMap<String, Object> dataUser = new HashMap();
				String msgErro = "";
				
				boolean isValid = true;
				
				if(txtUser.getText().trim().isEmpty()) {
					msgErro += "O campo 'user' deve ser informado!\n";
					isValid = false;
				}
				if(txtName.getText().trim().isEmpty()) {
					msgErro += "O campo 'nome' deve ser informado!\n";
					isValid = false;
				}
				if(txtEmail.getText().trim().isEmpty()) {
					msgErro += "O campo 'email' deve ser informado!\n";
					isValid = false;
				}
				if(new String(passField.getPassword()).trim().isEmpty()) {
					msgErro += "O campo 'senha' deve ser informado!\n";
					isValid = false;
				}
				if(new String(confPassField.getPassword()).trim().isEmpty() || confPassField.getPassword().equals(passField.getPassword())) {
					msgErro += "O campo 'confirmar' deve ser o idéntico ao digitado em senha!\n";
					isValid = false;
				}
				if(txtMobile.getText().trim().isEmpty() && txtPhone.getText().trim().isEmpty()) {
					msgErro += "Um número de 'celular' ou 'telefone' deve ser informado!\n";
					isValid = false;
				}

				if(isValid) {
					dataUser.put("user", txtUser.getText());
					dataUser.put("name", txtName.getText());
					dataUser.put("email", txtEmail.getText());
					dataUser.put("pass", new String(passField.getPassword()));
					Status se = sm.findOneWhere("name", "'" + cmbStatus.getSelectedItem() + "'"); 
					Group ge = gm.findOneWhere("name", "'" + cmbGroups.getSelectedItem() + "'");
					dataUser.put("status", se);
					dataUser.put("group", ge);
					dataUser.put("mobile", txtMobile.getText());
					dataUser.put("phone", txtPhone.getText());

					um = new UserModel();
					int id = 0;
					try {
						if(theId > 0) {
							
						} else {
							id = um.createUser(dataUser);
						}						
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					
					if(id > 0) {
						txtUser.setText("");
						txtName.setText("");
						txtEmail.setText("");
						passField.setText("");
						confPassField.setText("");
						txtMobile.setText("");
						txtPhone.setText("");
						
						JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível inserir o usuário. Campos 'usuário' e 'email' podem já existir(escolha outro).");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}

			}
		});
		
		JLabel lblCelular = new JLabel("Celular");
		GridBagConstraints gbc_lblCelular = new GridBagConstraints();
		gbc_lblCelular.anchor = GridBagConstraints.EAST;
		gbc_lblCelular.insets = new Insets(0, 0, 5, 5);
		gbc_lblCelular.gridx = 0;
		gbc_lblCelular.gridy = 6;
		contentPane.add(lblCelular, gbc_lblCelular);
		
		txtMobile = new JTextField();
		txtMobile.setToolTipText("Precisamos de um n\u00FAmero de contato.");
		GridBagConstraints gbc_txtMobile = new GridBagConstraints();
		gbc_txtMobile.insets = new Insets(0, 0, 5, 0);
		gbc_txtMobile.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMobile.gridx = 1;
		gbc_txtMobile.gridy = 6;
		contentPane.add(txtMobile, gbc_txtMobile);
		txtMobile.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 7;
		contentPane.add(lblTelefone, gbc_lblTelefone);
		
		txtPhone = new JTextField();
		txtPhone.setToolTipText("Informe o seu telefone.");
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(0, 0, 5, 0);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 7;
		contentPane.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 8;
		contentPane.add(lblStatus, gbc_lblStatus);
		
		GridBagConstraints gbc_cmbStatus = new GridBagConstraints();
		gbc_cmbStatus.insets = new Insets(0, 0, 5, 0);
		gbc_cmbStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbStatus.gridx = 1;
		gbc_cmbStatus.gridy = 8;
		contentPane.add(cmbStatus, gbc_cmbStatus);

		JLabel lblGrupo = new JLabel("Grupo");
		GridBagConstraints gbc_lblGrupo = new GridBagConstraints();
		gbc_lblGrupo.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrupo.anchor = GridBagConstraints.EAST;
		gbc_lblGrupo.gridx = 0;
		gbc_lblGrupo.gridy = 9;
		contentPane.add(lblGrupo, gbc_lblGrupo);
		
		GridBagConstraints gbc_cmbGroups = new GridBagConstraints();
		gbc_cmbGroups.insets = new Insets(0, 0, 5, 0);
		gbc_cmbGroups.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbGroups.gridx = 1;
		gbc_cmbGroups.gridy = 9;
		contentPane.add(cmbGroups, gbc_cmbGroups);
		GridBagConstraints gbc_btnSalvarUsurio = new GridBagConstraints();
		gbc_btnSalvarUsurio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalvarUsurio.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvarUsurio.gridx = 1;
		gbc_btnSalvarUsurio.gridy = 10;
		contentPane.add(btnSalvarUsurio, gbc_btnSalvarUsurio);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 11;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		checkPermissions();
	}
	
	public void checkPermissions() {
		
	}
}
