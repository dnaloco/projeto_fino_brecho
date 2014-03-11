package br.arthur.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class MyImageUtil {
	public static BufferedImage setMaxWidthHeight(Blob blob_img, int width, int height) throws SQLException, IOException {
		InputStream is;
		Image img;
		is = blob_img.getBinaryStream();
		img = ImageIO.read(is);

		int origW = img.getWidth(null), origH = img.getHeight(null), newW = origW, newH = origH;

		if (origW > width || origH > height) {
			if (origW >= origH) {
				newW = width;
				newH = origH * newW / origW;
			} else {
				newH = height;
				newW = origW * newH / origH;
			}
		}

		BufferedImage bi = new BufferedImage(newW, newH,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(img, 0, 0, newW, newH, null);
		g.setBackground(new Color(255, 255, 255, 0));
		g.dispose();
		return bi;
	}
}
