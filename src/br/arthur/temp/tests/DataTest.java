package br.arthur.temp.tests;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataTest extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataTest frame = new DataTest();
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
	public DataTest() {
		setBounds(100, 100, 450, 300);
		final JCalendar dti = new JCalendar();  
		// adicionando o calendario no painel  
		getContentPane().add(dti);  
		
		JButton btnSelecionarData = new JButton("Selecionar Data");
		btnSelecionarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(dti.getDayChooser());
			}
		});
		dti.getDayChooser().add(btnSelecionarData, BorderLayout.SOUTH);
		// E tentando imprimir a data selecionada  
		  
	}

}
