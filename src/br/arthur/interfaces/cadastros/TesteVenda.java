package br.arthur.interfaces.cadastros;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TesteVenda extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteVenda frame = new TesteVenda();
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
	public TesteVenda() {
		setBounds(100, 100, 845, 439);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, -342, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 823, SpringLayout.WEST, getContentPane());
		getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel label = new JLabel("Descri\u00E7\u00E3o do Produto");
		sl_panel.putConstraint(SpringLayout.WEST, label, 298, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label, -300, SpringLayout.SOUTH, panel);
		panel.add(label);
		
		JLabel label_1 = new JLabel("C\u00F3digo de Barras:");
		sl_panel.putConstraint(SpringLayout.WEST, label_1, 404, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label_1, -266, SpringLayout.SOUTH, panel);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Quantidade:");
		sl_panel.putConstraint(SpringLayout.WEST, label_2, 608, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label_2, -266, SpringLayout.SOUTH, panel);
		panel.add(label_2);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField, 58, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField, -5, SpringLayout.SOUTH, panel);
		textField.setColumns(10);
		panel.add(textField);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 300, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField_1, -41, SpringLayout.SOUTH, panel);
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JButton button = new JButton("Atualizar");
		sl_panel.putConstraint(SpringLayout.WEST, button, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button, -5, SpringLayout.SOUTH, panel);
		panel.add(button);
		
		JButton button_1 = new JButton("Buscar Produto");
		sl_panel.putConstraint(SpringLayout.WEST, button_1, 248, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_1, -5, SpringLayout.SOUTH, panel);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Excluir");
		sl_panel.putConstraint(SpringLayout.WEST, button_2, 12, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_2, -300, SpringLayout.SOUTH, panel);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Finalizar Venda");
		sl_panel.putConstraint(SpringLayout.WEST, button_3, 524, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_3, -294, SpringLayout.SOUTH, panel);
		panel.add(button_3);
		
		JButton button_4 = new JButton("Cancelar Venda");
		sl_panel.putConstraint(SpringLayout.WEST, button_4, 12, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_4, -39, SpringLayout.SOUTH, panel);
		panel.add(button_4);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.WEST, scrollPane, 524, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, scrollPane, -266, SpringLayout.SOUTH, panel);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 524, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, -5, SpringLayout.SOUTH, panel);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1);
		
		JButton button_5 = new JButton("Cancelar Item");
		sl_panel.putConstraint(SpringLayout.WEST, button_5, 300, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_5, -5, SpringLayout.SOUTH, panel);
		panel.add(button_5);
		
		JButton button_6 = new JButton(">>");
		sl_panel.putConstraint(SpringLayout.WEST, button_6, 566, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_6, -5, SpringLayout.SOUTH, panel);
		panel.add(button_6);
		
		JButton button_7 = new JButton(">");
		sl_panel.putConstraint(SpringLayout.WEST, button_7, 404, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_7, -294, SpringLayout.SOUTH, panel);
		panel.add(button_7);
		
		JLabel label_3 = new JLabel("0/0");
		sl_panel.putConstraint(SpringLayout.WEST, label_3, 207, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label_3, -5, SpringLayout.SOUTH, panel);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		JButton button_8 = new JButton("<");
		sl_panel.putConstraint(SpringLayout.WEST, button_8, 497, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_8, -232, SpringLayout.SOUTH, panel);
		panel.add(button_8);
		
		JButton button_9 = new JButton("<<");
		sl_panel.putConstraint(SpringLayout.WEST, button_9, 99, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button_9, -11, SpringLayout.SOUTH, panel);
		panel.add(button_9);
		
		JLabel label_4 = new JLabel("Total:");
		sl_panel.putConstraint(SpringLayout.WEST, label_4, 298, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label_4, -232, SpringLayout.SOUTH, panel);
		label_4.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("R$ 56,56");
		sl_panel.putConstraint(SpringLayout.WEST, label_5, 331, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, label_5, -278, SpringLayout.SOUTH, panel);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel.add(label_5);

	}

}
