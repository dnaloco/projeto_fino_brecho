package br.arthur.temp.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataDemo extends JFrame {

	private JPanel contentPane;
	private JDateChooser chooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataDemo frame = new DataDemo();
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
	public DataDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		chooser = new JDateChooser();
		sl_contentPane.putConstraint(SpringLayout.NORTH, chooser, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, chooser, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, chooser, -317, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, chooser, -306, SpringLayout.EAST, contentPane);
		getContentPane().add(chooser);
		
		JButton btnMostrarData = new JButton("Mostrar Data");
		btnMostrarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, chooser.getDate());
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnMostrarData, 0, SpringLayout.NORTH, chooser);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnMostrarData, 6, SpringLayout.EAST, chooser);
		contentPane.add(btnMostrarData);
		
		
	}
}
