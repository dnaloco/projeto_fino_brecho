package br.arthur.interfaces;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import br.arthur.controllers.LoginController;
import br.arthur.entities.User;
import br.arthur.interfaces.cadastros.CadastroCategoria;
import br.arthur.interfaces.cadastros.CadastroCliente;
import br.arthur.interfaces.cadastros.CadastroConsignatario;
import br.arthur.interfaces.cadastros.CadastroMarca;
import br.arthur.interfaces.cadastros.CadastroSaida;
import br.arthur.interfaces.cadastros.CadastroTipo;
// import br.arthur.interfaces.cadastros.CadastroPedido;
import br.arthur.interfaces.cadastros.CadastroUsuario;
import br.arthur.interfaces.cadastros.CadastroEntrada;
import br.arthur.interfaces.consultas.ConsultarAPagar;
import br.arthur.interfaces.consultas.ConsultarAReceber;
import br.arthur.interfaces.consultas.ConsultarProduto;
import br.arthur.interfaces.consultas.ConsultarVendas;
import br.arthur.relatorios.RelatorioUsuarios;

public class Principal extends JFrame {

	public JDesktopPane jDesktopPanelPrincipal;
	private User ulog;
	/**
	 * Create the frame.
	 */
	public Principal(final LoginController login) {
		ulog = login.getLoggedUser();
		setIconImage((new ImageIcon(
				"images/Store-icon.png").getImage()));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Sitema de Loja Fino Brecho - [ ID: " + ulog.getId() + " | Usu�rio: " + ulog.getUser() + " | Grupo: " + ulog.getGroup().getName() +" ]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 733);
		setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		ImageIcon icon = new ImageIcon("images/finoBrechoBg.jpg");
		final Image img = icon.getImage();

		jDesktopPanelPrincipal = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(img, 0, 0, getSize().width, getSize().height, this);
			}
		};
		
		getContentPane().add(jDesktopPanelPrincipal);
		jDesktopPanelPrincipal.setLayout(null);
		
		JButton btnNewButton = new JButton("Entrada");
		if (ulog.getGroup().getName().equals("gerente")) {
			btnNewButton.setVisible(true);;
		} else {
			btnNewButton.setVisible(false);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroEntrada obj = new CadastroEntrada();				
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setIcon(new ImageIcon(
				"images/entradas.png"));
		btnNewButton.setBounds(528, 393, 122, 109);
		jDesktopPanelPrincipal.add(btnNewButton);
		
		JLabel lblSistemaLoja = new JLabel("Sistema de Loja - Fino Brech\u00F3");
		lblSistemaLoja.setBackground(new Color(102, 51, 0));
		lblSistemaLoja.setFont(new Font("Perpetua", Font.BOLD, 36));
		lblSistemaLoja.setForeground(new Color(51, 0, 0));
		lblSistemaLoja.setBounds(20, 11, 464, 48);
		jDesktopPanelPrincipal.add(lblSistemaLoja);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setOpaque(false);
		panel.setBackground(new Color(204, 153, 51));
		panel.setForeground(UIManager.getColor("MenuBar:Menu[Enabled].textForeground"));
		panel.setBounds(486, 11, 211, 128);
		jDesktopPanelPrincipal.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("   ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio Logado");
		lblNewLabel.setForeground(UIManager.getColor("MenuBar:Menu[Enabled].textForeground"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblUsurio = new JLabel("ID Usu\u00E1rio: ");
		lblUsurio.setForeground(UIManager.getColor("MenuBar:Menu[Enabled].textForeground"));
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 1;
		gbc_lblUsurio.gridy = 1;
		panel.add(lblUsurio, gbc_lblUsurio);
		
		JLabel lblUserid = new JLabel(String.valueOf(ulog.getId()));
		lblUserid.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUserid.setForeground(new Color(0, 0, 255));
		GridBagConstraints gbc_lblUserid = new GridBagConstraints();
		gbc_lblUserid.anchor = GridBagConstraints.WEST;
		gbc_lblUserid.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserid.gridx = 2;
		gbc_lblUserid.gridy = 1;
		panel.add(lblUserid, gbc_lblUserid);
		
		JLabel lblNomeUsurio = new JLabel("Nome Usu\u00E1rio:");
		lblNomeUsurio.setForeground(UIManager.getColor("MenuBar:Menu[Enabled].textForeground"));
		lblNomeUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNomeUsurio = new GridBagConstraints();
		gbc_lblNomeUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblNomeUsurio.gridx = 1;
		gbc_lblNomeUsurio.gridy = 2;
		panel.add(lblNomeUsurio, gbc_lblNomeUsurio);
		
		JLabel lblUsername = new JLabel(ulog.getName());
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setForeground(new Color(0, 0, 255));
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 2;
		panel.add(lblUsername, gbc_lblUsername);
		
		JLabel lblGrupoUsurio = new JLabel("Grupo Usu\u00E1rio:");
		lblGrupoUsurio.setForeground(UIManager.getColor("MenuBar:Menu[Enabled].textForeground"));
		lblGrupoUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblGrupoUsurio = new GridBagConstraints();
		gbc_lblGrupoUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrupoUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblGrupoUsurio.gridx = 1;
		gbc_lblGrupoUsurio.gridy = 3;
		panel.add(lblGrupoUsurio, gbc_lblGrupoUsurio);
		
		JLabel lblUsergroup = new JLabel(ulog.getGroup().getName());
		lblUsergroup.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUsergroup.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsergroup.setForeground(new Color(0, 0, 255));
		GridBagConstraints gbc_lblUsergroup = new GridBagConstraints();
		gbc_lblUsergroup.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsergroup.anchor = GridBagConstraints.WEST;
		gbc_lblUsergroup.gridx = 2;
		gbc_lblUsergroup.gridy = 3;
		panel.add(lblUsergroup, gbc_lblUsergroup);
		
		JButton btnNewButton_3 = new JButton("Trocar de Usu\u00E1rio");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trocarUsuario();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridwidth = 2;
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 4;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Relat\u00F3rios");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_2.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_2.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_2.setIcon(new ImageIcon(
				"images/reports.png"));
		btnNewButton_2.setBounds(528, 272, 122, 109);
		jDesktopPanelPrincipal.add(btnNewButton_2);
		
		JButton btnLogs = new JButton("Produtos");
		btnLogs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogs.setIcon(new ImageIcon(
				"images/products.jpg"));
		btnLogs.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogs.setVerticalAlignment(SwingConstants.TOP);
		btnLogs.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogs.setBackground(Color.WHITE);
		btnLogs.setBounds(394, 272, 122, 109);
		jDesktopPanelPrincipal.add(btnLogs);
		
		JButton btnNewButton_1 = new JButton("Consignat\u00E1rio");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_1.setIcon(new ImageIcon(
				"images/consigs.png"));
		btnNewButton_1.setBounds(659, 151, 122, 109);
		jDesktopPanelPrincipal.add(btnNewButton_1);
		
		JButton btnBackup = new JButton("Cliente");
		btnBackup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBackup.setIcon(new ImageIcon(
				"images/clients-big.png"));
		btnBackup.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBackup.setVerticalAlignment(SwingConstants.TOP);
		btnBackup.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBackup.setBackground(Color.WHITE);
		btnBackup.setBounds(528, 151, 122, 109);
		jDesktopPanelPrincipal.add(btnBackup);
		
		JButton btnCaixa = new JButton("Venda");
		btnCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroSaida obj = new CadastroSaida(login);
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		btnCaixa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCaixa.setBackground(new Color(255, 255, 255));
		btnCaixa.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCaixa.setVerticalAlignment(SwingConstants.TOP);
		btnCaixa.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCaixa.setIcon(new ImageIcon(
				"images/vendas.png"));
		btnCaixa.setBounds(397, 151, 119, 109);
		jDesktopPanelPrincipal.add(btnCaixa);
		
		JButton btnTrocarUsurio = new JButton("Trocar Usu\u00E1rio");
		btnTrocarUsurio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTrocarUsurio.setIcon(new ImageIcon(
				"images/user_change.png"));
		btnTrocarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				trocarUsuario();
			}
		});
		btnTrocarUsurio.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTrocarUsurio.setVerticalAlignment(SwingConstants.TOP);
		btnTrocarUsurio.setHorizontalTextPosition(SwingConstants.CENTER);
		btnTrocarUsurio.setBackground(Color.WHITE);
		btnTrocarUsurio.setBounds(659, 272, 122, 109);
		jDesktopPanelPrincipal.add(btnTrocarUsurio);
		
		JButton btnNewButton_4 = new JButton("SAIR");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_4.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_4.setIcon(new ImageIcon(
				"images/sair.png"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(709, 11, 72, 128);
		jDesktopPanelPrincipal.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setIcon(new ImageIcon(
				"images/calculator.png"));
		btnNewButton_5.setBounds(394, 55, 80, 84);
		jDesktopPanelPrincipal.add(btnNewButton_5);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		if (ulog.getGroup().getName().equals("gerente")) {
			mnCadastro.setEnabled(true);
		} else {
			mnCadastro.setEnabled(false);
		}
		menuBar.add(mnCadastro);
		
		JMenuItem mntmConsignatrio = new JMenuItem("Novo Consignat\u00E1rio");
		mntmConsignatrio.setIcon((new ImageIcon(
				"images/consigs-small.png")));
		mntmConsignatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroConsignatario obj = new CadastroConsignatario(0);
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		
		JMenuItem mntmProdutos_2 = new JMenuItem("Gerenciar Produtos");
		mnCadastro.add(mntmProdutos_2);
		mntmProdutos_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarProduto obj = new ConsultarProduto();
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mntmProdutos_2.setIcon((new ImageIcon(
				"images/products-small.jpg")));
		
		JMenuItem mntmCliente = new JMenuItem("Novo Cliente");
		mntmCliente.setIcon((new ImageIcon(
				"images/clients.png")));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCliente obj = new CadastroCliente(0);
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		
		JSeparator separator_5 = new JSeparator();
		mnCadastro.add(separator_5);
		mnCadastro.add(mntmCliente);
		
		JSeparator separator_6 = new JSeparator();
		mnCadastro.add(separator_6);
		mnCadastro.add(mntmConsignatrio);
		
		JMenuItem mntmPedido = new JMenuItem("Nova Entrada");
		mntmPedido.setIcon((new ImageIcon(
				"images/entradas-small.png")));
		mntmPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroEntrada obj = new CadastroEntrada();
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmPedido);
		
		JSeparator separator_4 = new JSeparator();
		mnCadastro.add(separator_4);
		
		JMenuItem mntmCategoria = new JMenuItem("Nova Categoria");
		mntmCategoria.setIcon((new ImageIcon(
				"images/Letter-C-blue-icon.png")));
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroCategoria obj = new CadastroCategoria();
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmCategoria);
		
		JMenuItem mntmMarca = new JMenuItem("Nova Marca");
		mntmMarca.setIcon((new ImageIcon(
				"images/Letter-M-blue-icon.png")));
		mntmMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroMarca obj = new CadastroMarca();				
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mnCadastro.add(mntmMarca);
		
		JMenuItem mntmStatusProduto = new JMenuItem("Novo Tipo");
		mntmStatusProduto.setIcon((new ImageIcon(
				"images/Letter-T-blue-icon.png")));
		mntmStatusProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CadastroTipo obj = new CadastroTipo();				
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		
		JMenuItem mntmCor = new JMenuItem("Nova Cor");
		mnCadastro.add(mntmCor);
		
		JMenuItem mntmTamanho = new JMenuItem("Novo Tamanho");
		mnCadastro.add(mntmTamanho);
		mnCadastro.add(mntmStatusProduto);
				
				JSeparator separator = new JSeparator();
				mnCadastro.add(separator);
				
				JMenuItem mntmAlterarSenha = new JMenuItem("Alterar Senha");
				mnCadastro.add(mntmAlterarSenha);
				mntmAlterarSenha.setIcon(new ImageIcon("images/Status-dialog-password-icon.png"));
				
				JSeparator separator_1 = new JSeparator();
				mnCadastro.add(separator_1);
				
				JMenuItem mntmSair = new JMenuItem("Sair");
				mntmSair.setIcon(new ImageIcon(
						"images/quit.png"));
				mntmSair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				mnCadastro.add(mntmSair);
		
		JMenu mnFinanceiro = new JMenu("Financeiro");
		if (ulog.getGroup().getName().equals("gerente")) {
			mnFinanceiro.setEnabled(true);
		} else {
			mnFinanceiro.setEnabled(false);
		}
		menuBar.add(mnFinanceiro);
		
		JMenuItem mntmCaixa = new JMenuItem("Caixa");
		mntmCaixa.setIcon(new ImageIcon("images/caixa.png"));
		mnFinanceiro.add(mntmCaixa);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarVendas obj = new ConsultarVendas();				
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mntmVendas.setIcon(new ImageIcon("images/vendas-small.png"));
		mnFinanceiro.add(mntmVendas);
		
		JMenuItem mntmExtorno = new JMenuItem("Extorno");
		mntmExtorno.setIcon(new ImageIcon("images/coin-delete-icon.png"));
		mnFinanceiro.add(mntmExtorno);
		
		JSeparator separator_2 = new JSeparator();
		mnFinanceiro.add(separator_2);
		
		JMenuItem mntmContasPagar = new JMenuItem("Contas \u00E0 Pagar");
		mntmContasPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarAPagar obj = new ConsultarAPagar();
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mntmContasPagar.setIcon(new ImageIcon("images/balance-minus-icon.png"));
		mnFinanceiro.add(mntmContasPagar);
		
		JMenuItem mntmContasReceber = new JMenuItem("Contas \u00E0 Receber");
		mntmContasReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarAReceber obj = new ConsultarAReceber();
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mntmContasReceber.setIcon(new ImageIcon("images/balance-plus-icon.png"));
		mnFinanceiro.add(mntmContasReceber);
		
		JSeparator separator_3 = new JSeparator();
		mnFinanceiro.add(separator_3);
		
		JMenuItem mntmSaldoEmAberto = new JMenuItem("Saldo em Aberto");
		mnFinanceiro.add(mntmSaldoEmAberto);
		
		JMenu mnManuteno = new JMenu("Manuten\u00E7\u00E3o");
		if (ulog.getGroup().getName().equals("gerente")) {
			mnManuteno.setEnabled(true);
		} else {
			mnManuteno.setEnabled(false);
		}
		menuBar.add(mnManuteno);
		
		JMenuItem mntmEcf = new JMenuItem("ECF");
		mntmEcf.setIcon(new ImageIcon("images/ecf.png"));
		mnManuteno.add(mntmEcf);
		
		JMenuItem mntmBackup = new JMenuItem("Backup");
		mntmBackup.setIcon(new ImageIcon("images/Backup-Folder-Ash-icon.png"));
		mnManuteno.add(mntmBackup);
		
		JMenu mnBackup = new JMenu("Backup2");
		mnManuteno.add(mnBackup);
		
		JMenuItem mntmImportar = new JMenuItem("Importar");
		mnBackup.add(mntmImportar);
		
		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mnBackup.add(mntmExportar);
		
		JMenuItem mntmEventos = new JMenuItem("Eventos");
		mntmEventos.setIcon(new ImageIcon("images/Apps-utilities-log-viewer-icon.png"));
		mnManuteno.add(mntmEventos);
		
		JSeparator separator_7 = new JSeparator();
		mnManuteno.add(separator_7);
		
		JMenuItem mntmUsurios = new JMenuItem("Usu\u00E1rios");
		mntmUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroUsuario obj = null;
				try {
					obj = new CadastroUsuario(0);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				jDesktopPanelPrincipal.add(obj);
				obj.setVisible(true);
			}
		});
		mntmUsurios.setIcon(new ImageIcon("images/Apps-preferences-desktop-user-password-icon.png"));
		mnManuteno.add(mntmUsurios);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		if (ulog.getGroup().getName().equals("gerente")) {
			mnRelatrio.setEnabled(true);
		} else {
			mnRelatrio.setEnabled(false);
		}
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmCosignatrios = new JMenuItem("Consignat\u00E1rios");
		mntmCosignatrios.setIcon(new ImageIcon("images/reports-small.png"));
		mnRelatrio.add(mntmCosignatrios);
		
		JMenuItem mntmProdutos_1 = new JMenuItem("Produtos");
		mntmProdutos_1.setIcon(new ImageIcon("images/reports-small.png"));
		mnRelatrio.add(mntmProdutos_1);
		
		JMenuItem mntmVendas_1 = new JMenuItem("Vendas");
		mntmVendas_1.setIcon(new ImageIcon("images/reports-small.png"));
		mnRelatrio.add(mntmVendas_1);
		
		JMenuItem mntmClientes_1 = new JMenuItem("Clientes");
		mntmClientes_1.setIcon(new ImageIcon("images/reports-small.png"));
		mnRelatrio.add(mntmClientes_1);
		
		JMenuItem mntmUsurios_2 = new JMenuItem("Usu\u00E1rios");
		mntmUsurios_2.setIcon(new ImageIcon("images/reports-small.png"));
		mnRelatrio.add(mntmUsurios_2);
		
		JMenu mnUsurios = new JMenu("Usu\u00E1rios");
		mnRelatrio.add(mnUsurios);
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mntmTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioUsuarios ru = new RelatorioUsuarios();
				
				ru.gerarTodos();
			}
		});
		mnUsurios.add(mntmTodos);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre...");
		mntmSobre.setIcon(new ImageIcon("images/Actions-help-about-icon.png"));
		
		mnSobre.add(mntmSobre);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		checkPermissions();
		
		setVisible(true);
	}
	
	public void checkPermissions() {
		
	}

	private void trocarUsuario() {
		dispose();
		new SplashScreen();
	}
}
