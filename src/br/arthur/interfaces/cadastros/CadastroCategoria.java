package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import br.arthur.models.CategoriaModel;

public class CadastroCategoria extends JInternalFrame {
	private JTextField txtCategoria;

	public CadastroCategoria() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastro de Categoria");
		setInheritsPopupMenu(true);
		setIgnoreRepaint(true);

		setBounds(100, 100, 262, 123);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblCategoriaID = new JLabel("Nova Categoria");
		lblCategoriaID.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoriaID, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCategoriaID, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCategoriaID);
		
		txtCategoria = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCategoria, 37, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtCategoria, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoria, 13, SpringLayout.SOUTH, lblCategoriaID);
		springLayout.putConstraint(SpringLayout.WEST, txtCategoria, 6, SpringLayout.EAST, lblCategoria);
		springLayout.putConstraint(SpringLayout.WEST, lblCategoria, 0, SpringLayout.WEST, lblCategoriaID);
		getContentPane().add(lblCategoria);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				HashMap<String, Object> c = new HashMap();
				String msgErro = "";
				boolean isValid = true;
				
				if(txtCategoria.getText().trim().isEmpty()) {
					msgErro +=  "O campo 'categoria' deve ser preenchido.\n";
					isValid = false;
				}
				
				if(isValid) {
					CategoriaModel cm = new CategoriaModel();
					short id = 0;
					try {
						id = cm.createCategoria(txtCategoria.getText());
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					if(id > 0) {
						txtCategoria.setText("");
						JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "N�o foi poss�vel inserir a categoria!");
					}
				} else {
					JOptionPane.showMessageDialog(null, msgErro);
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 6, SpringLayout.SOUTH, txtCategoria);
		springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, txtCategoria);
		getContentPane().add(btnSalvar);
		
		setVisible(true);

	}

}
