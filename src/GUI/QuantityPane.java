package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.JTextComponent;

import BUS.QuantityEvent;
import Design.GradientButton;
import Design.RoundTextfield;

public class QuantityPane extends JPanel {
	public RoundTextfield textField_1;
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int value;
	private GradientButton btnNewButton;
	private GradientButton btnNewButton_2;
	private QuantityPane self;
	
	public void initEvent(QuantityEvent event,int  row) {
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onIncrease(row, self);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onDecrease(row, self);

			}
		});
	}
	
	public QuantityPane(String value) {
		self = this;
		setSize(207,73);
		this.value = Integer.parseInt(value);
		btnNewButton = new GradientButton("-","#CB356B","#BD3F32");
		btnNewButton.setText("+");
		btnNewButton.setSize(50,50);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnNewButton);
		
		
		textField_1 = new RoundTextfield("");
		textField_1.setEditable(false);
		textField_1.setText(this.value+"");
		textField_1.setSize(50,50);
		add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_2 = new GradientButton("+","#CB356B","#BD3F32");
		btnNewButton_2.setText("-");
		btnNewButton_2.setSize(50,50);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnNewButton_2);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(17, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
        this.setLayout(layout);
	}
}
