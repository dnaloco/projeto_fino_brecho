package br.arthur.interfaces.cadastros.dialogs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.arthur.utils.MyImageUtil;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class PicZoomDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PicZoomDialog dialog = new PicZoomDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PicZoomDialog(Blob blob_img, String descricao) {
		setTitle(descricao);
		setModal(true);
		setBounds(100, 100, 820, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel picZoom = new JLabel("");
		picZoom.setHorizontalAlignment(SwingConstants.CENTER);
		picZoom.setBounds(0, 0, 804, 582);
		contentPanel.add(picZoom);
		
		try {
			BufferedImage bi;
			bi = MyImageUtil.setMaxWidthHeight(blob_img, picZoom.getWidth(), picZoom.getHeight());
			picZoom.setIcon(new ImageIcon(bi));
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
