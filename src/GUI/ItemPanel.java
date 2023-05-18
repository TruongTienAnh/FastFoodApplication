package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import DTO.MONAN;
import Design.GradientButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Currency;
import java.util.Locale;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ItemPanel extends JPanel{

	
	private AddCartFrame acf;
	public GradientButton addToCartButton;
	
	public ItemPanel(SaleView oderview,MONAN food) {
		setBackground(new Color(255, 255, 255));
		this.setSize(464, 400);

		
		addToCartButton = new GradientButton("Add to cart","#a8ff78","#78ffd6"
				+ "");
		addToCartButton.setText("Thêm vào giỏ");
		addToCartButton.setForeground(new Color(255, 255, 255));
		addToCartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				addToCartButton.setColor1("#a8cc78");
				addToCartButton.setColor2("#78ccd6");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				addToCartButton.setColor1("#a8ff78");
				addToCartButton.setColor2("#78ffd6");
			}
		});
		addToCartButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		addToCartButton.setFocusPainted(false);
		addToCartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				acf = new AddCartFrame(oderview, food);
				acf.setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ItemPanel.class.getResource(food.getImage())));
		
		JLabel dishName = new JLabel(food.getName());
		dishName.setForeground(Color.decode("#BD3F32"));
		dishName.setHorizontalAlignment(SwingConstants.CENTER);
		dishName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel pricelb = new JLabel();
		
		Locale locale = new Locale("vi", "VN");
		Currency vn = Currency.getInstance(locale);
		
		pricelb.setForeground(new Color(200, 200, 83));
		if(food.getType().equalsIgnoreCase("Hamburger")) {
			pricelb.setText(String.valueOf(food.getUnitPrice())+" " + vn +"  / 1 burger");
		}else if(food.getType().equalsIgnoreCase("French fried")||
				 food.getType().equalsIgnoreCase("Mash Potato")||
				 food.getType().equalsIgnoreCase("Salad") || 
				 food.getType().equalsIgnoreCase("Drink")) {
			double itemPrice = food.getUnitPrice();
			double itemPrice2 = Math.round(itemPrice*1.5*100)*1.0/100;
			double itemPrice3 = Math.round(itemPrice*2*100)*1.0/100;
			pricelb.setText(String.valueOf(itemPrice)+" " + vn +"  /  "
								+String.valueOf(itemPrice2)+" " + vn +"  /  "
								+String.valueOf(itemPrice3)+" " + vn);
		}else if(food.getType().equalsIgnoreCase("Fried Chicken")){
			pricelb.setText(String.valueOf(food.getUnitPrice())+" " + vn +"  / 1 Miếng");
		}
		else
		{
			pricelb.setText(String.valueOf(food.getUnitPrice())+" " + vn +"  / 1 Phần");
		}
		
		pricelb.setHorizontalAlignment(SwingConstants.CENTER);
		pricelb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
					.addGap(4))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(dishName, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
					.addGap(4))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pricelb, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
					.addGap(4))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(137)
					.addComponent(addToCartButton, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(115))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(dishName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(pricelb, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(addToCartButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		setLayout(groupLayout);
	}
	
}
