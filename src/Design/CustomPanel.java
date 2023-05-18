package Design;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import test.JPanelTest;
import test.testFrame;

public class CustomPanel extends JPanel {



	public CustomPanel() {
		setOpaque(false);
	}
	 @Override
	    protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        GradientPaint gra = new GradientPaint(0,0, Color.decode("#CB356B"), getWidth(),0, Color.decode("#BD3F32"));
			g2.setPaint(gra);
	    
	        
	        AffineTransform tran = new AffineTransform();
	        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 0, 0), tran);
	        Area area = new Area(p);
	        area.add(new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 0, 0)));
	        g2.fill(area);
	        g2.dispose();
	        super.paintComponent(grphcs);
	    }

}
