package Design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GradientButton extends JButton {
	private Color color1 ;
	private Color color2 ;
	
	public GradientButton(String text, String color1, String color2 ) {
		setContentAreaFilled(false);
		setForeground(Color.white);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(new EmptyBorder(10, 20, 10, 20));
		setText(text);
		setFocusPainted(false);
		this.color1 = Color.decode(color1);
		this.color2 = Color.decode(color2);
	}
	public void setColor1(String color1) {
		this.color1 = Color.decode(color1);
	}
	public void setColor2(String color2) {
		this.color2 = Color.decode(color2);
	}
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		GradientPaint gra = new GradientPaint(0,0, color1, width,0, color2);
		g2.setPaint(gra);
		g2.fillRoundRect(0, 0, width, height, height, height);
		g.drawImage(img, 0,0,null);
		
		super.paintComponent(g);
		if(!isEnabled()) {
			this.color1 = new Color(200,200,200);
			this.color2 = new Color(200,200,200);
		}
	}
	
	@Override
	public void setEnabled(boolean val) {
		this.color1 = new Color(200,200,200);
		this.color2 = new Color(200,200,200);
		setForeground(Color.white);
		super.setEnabled(val);
	}
}
