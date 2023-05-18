package GUI;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import BUS.ActionPaneEvent;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ActionPane extends JPanel {
	private JButton btnNewButton;
	public void initEvent(ActionPaneEvent e, int row) {
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				e.onDelete(row);
				
			}
		});
	}
	
	public ActionPane() {
		setLayout(null);
		setSize(230,66);	
		btnNewButton = new JButton("");
		btnNewButton.setBounds(81, 0, 64, 66);
		btnNewButton.setBackground(new Color(255,255,255));
		btnNewButton.setBorder(new EmptyBorder(10,10,10,10));
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnNewButton.setIcon(new ImageIcon(ActionPane.class.getResource("/IMG/x-cross-icon.png")));
		add(btnNewButton);
		
	}
}
