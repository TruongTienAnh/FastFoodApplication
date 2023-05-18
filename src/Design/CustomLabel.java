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

import javax.swing.JLabel;

public class CustomLabel extends JLabel {
	/**
		 * Create the panel.
		 */
		public CustomLabel(String text) {
			setOpaque(false);
			setText(text);
		}
		 @Override
		    protected void paintComponent(Graphics grphcs) {
		        Graphics2D g2 = (Graphics2D) grphcs.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        GradientPaint gra = new GradientPaint(0,0, Color.decode("#CB356B"), getWidth(),0, Color.decode("#BD3F32"));
				g2.setPaint(gra);
		    
		        
		        AffineTransform tran = new AffineTransform();
		        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), tran);
		        Area area = new Area(p);
		        area.add(new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10)));
		        g2.fill(area);
		        g2.dispose();
		        super.paintComponent(grphcs);
		    }
}

