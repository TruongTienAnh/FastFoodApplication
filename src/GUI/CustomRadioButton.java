package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class CustomRadioButton extends JRadioButton {
	
	public Color bcolor;
	public Color fcolor;
	
	public CustomRadioButton(Color color1, Color color2) {
		bcolor = color1;
		fcolor = color2;
		
		setOpaque(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(fcolor);
		setForeground(bcolor);
		setFocusPainted(false);
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(bcolor);
		g2.fillRoundRect(0, 0, width, height, 10, 10);
		
		
		if(isSelected()) {
			g2.setPaint(bcolor);
			g2.fillRoundRect(0, 0, width, height, 10, 10);	
			setForeground(fcolor);
		}else {
			g2.setPaint(fcolor);
			g2.fillRoundRect(0, 0, width, height, 10, 10);
			setForeground(bcolor);
		}
		
		g.drawImage(img, 0,0,null);
		
		super.paintComponent(g);
	}
	@Override
	public void paint(Graphics grphcs) {
		super.paint(grphcs);
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(isSelected()) {
			g2.setPaint(bcolor);
			g2.fillRoundRect(1, 1, 15, getHeight()-2, 10, 10);
		}else {
			g2.setPaint(fcolor);
			g2.fillRoundRect(1, 1, 15, getHeight()-2, 10, 10);
		}
		
	}
	
}
