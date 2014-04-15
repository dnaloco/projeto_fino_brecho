package br.arthur.interfaces.consultas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarCaixar extends JInternalFrame {
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarCaixar frame = new ConsultarCaixar();
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
	public ConsultarCaixar() {
		setTitle("Fechamento de Caixa");
		setBounds(100, 100, 1039, 348);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JDateChooser dateChooser = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, dateChooser, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, dateChooser, -703, SpringLayout.EAST, getContentPane());
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("some action...");
			}
		});
		dateChooser.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				System.out.println("adicionado");
			}
			public void ancestorMoved(AncestorEvent arg0) {
				System.out.println("movido");
			}
			public void ancestorRemoved(AncestorEvent arg0) {
				System.out.println("removido");
			}
		});
		getContentPane().add(dateChooser);
		
		JLabel lblDataFechamento = new JLabel("Data Fechamento:");
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 6, SpringLayout.EAST, lblDataFechamento);
		springLayout.putConstraint(SpringLayout.NORTH, lblDataFechamento, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDataFechamento, 10, SpringLayout.WEST, getContentPane());
		lblDataFechamento.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		getContentPane().add(lblDataFechamento);
		
		JButton btnNewButton = new JButton("ATUALIZAR FECHAMENTO");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnNewButton);
		
		JLabel lblUltimaAtualizaoPor = new JLabel("Ultima atualização por:");
		springLayout.putConstraint(SpringLayout.NORTH, lblUltimaAtualizaoPor, 1, SpringLayout.NORTH, lblDataFechamento);
		springLayout.putConstraint(SpringLayout.EAST, lblUltimaAtualizaoPor, -521, SpringLayout.EAST, getContentPane());
		lblUltimaAtualizaoPor.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		getContentPane().add(lblUltimaAtualizaoPor);
		
		JLabel lblNoHouveFechamento = new JLabel("Não houve fechamento na data");
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 21, SpringLayout.EAST, lblNoHouveFechamento);
		springLayout.putConstraint(SpringLayout.NORTH, lblNoHouveFechamento, 0, SpringLayout.NORTH, dateChooser);
		springLayout.putConstraint(SpringLayout.WEST, lblNoHouveFechamento, 6, SpringLayout.EAST, lblUltimaAtualizaoPor);
		lblNoHouveFechamento.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		getContentPane().add(lblNoHouveFechamento);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 43, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, dateChooser, -6, SpringLayout.NORTH, scrollPane);
		getContentPane().add(scrollPane);
		
		JLabel lblTotal = new JLabel("TOTAIS");
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, lblTotal);
		springLayout.putConstraint(SpringLayout.NORTH, lblTotal, 294, SpringLayout.NORTH, getContentPane());
		lblTotal.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		
		table = new JTable();
		scrollPane.setViewportView(table);
		springLayout.putConstraint(SpringLayout.WEST, lblTotal, 0, SpringLayout.WEST, lblDataFechamento);
		getContentPane().add(lblTotal);
		
		JLabel lblQtdeMov = new JLabel("Qtde Mov.:");
		lblQtdeMov.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblQtdeMov, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, lblQtdeMov, 40, SpringLayout.EAST, lblTotal);
		getContentPane().add(lblQtdeMov);
		
		JLabel label = new JLabel("0");
		label.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, label, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, lblQtdeMov);
		getContentPane().add(label);
		
		JLabel lblLquido = new JLabel("Líquido:");
		springLayout.putConstraint(SpringLayout.NORTH, lblLquido, 6, SpringLayout.SOUTH, scrollPane);
		lblLquido.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblLquido);
		
		JLabel lblR = new JLabel("R$ 0,00");
		springLayout.putConstraint(SpringLayout.WEST, lblR, 351, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblLquido, -6, SpringLayout.WEST, lblR);
		springLayout.putConstraint(SpringLayout.NORTH, lblR, 6, SpringLayout.SOUTH, scrollPane);
		lblR.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblR);
		
		JLabel lblConferido = new JLabel("Conferido:");
		springLayout.putConstraint(SpringLayout.NORTH, lblConferido, 0, SpringLayout.NORTH, lblTotal);
		lblConferido.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblConferido);
		
		JLabel lblR_1 = new JLabel("R$ 0,00");
		springLayout.putConstraint(SpringLayout.EAST, lblConferido, -6, SpringLayout.WEST, lblR_1);
		springLayout.putConstraint(SpringLayout.NORTH, lblR_1, 6, SpringLayout.SOUTH, scrollPane);
		lblR_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblR_1);
		
		JLabel lblNewLabel = new JLabel("Diferença:");
		springLayout.putConstraint(SpringLayout.EAST, lblR_1, -88, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, scrollPane);
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel);
		
		JLabel lblR_2 = new JLabel("R$ 0,00");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, lblR_2);
		springLayout.putConstraint(SpringLayout.NORTH, lblR_2, 0, SpringLayout.NORTH, lblTotal);
		springLayout.putConstraint(SpringLayout.EAST, lblR_2, -43, SpringLayout.EAST, getContentPane());
		lblR_2.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		getContentPane().add(lblR_2);
		
		acrescentarColunas();

	}

	private void acrescentarColunas() {
		String[] colunas = new String[] { 
				"Forma Pagto",
				"Qtde Mov",
				"Líquido",
				"Conferido",
				"Diferença",
				"Observação"
			};

		String[][] dataTable = new String[][] {};

		model = new DefaultTableModel(dataTable, colunas);
		
		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
	}
}
