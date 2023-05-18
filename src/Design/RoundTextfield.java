package Design;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;

public class RoundTextfield extends JTextField {
	    private Shape shape;
	    public RoundTextfield(String text) {
//	        super(size);
	    	setText(text);
	        setOpaque(false); // As suggested by @AVD in comment.
	    }
	    protected void paintComponent(Graphics g) {
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	         super.paintComponent(g);
	    }
	    protected void paintBorder(Graphics g) {
	         g.setColor(getForeground());
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new Rectangle2D.Float(0, 0, getWidth()-1, getHeight()-1);
	         }
	         return shape.contains(x, y);
	    }
	}
