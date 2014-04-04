package br.arthur.utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonDoubleValueEditorUtil extends DefaultCellEditor {
	protected JButton button;
	private String label;
	private boolean isPushed;

	public ButtonDoubleValueEditorUtil(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			String value = null;
			while (isPushed) {
				
				value = JOptionPane.showInputDialog("Qual o valor que será pago desta parcela?");
				
				try
				{
					value = value.replace(",", ".").trim();
					Double.parseDouble(value);
					isPushed = false;
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Esse não é um valor válido!");
				}
			}
			
			
			label = value;
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}