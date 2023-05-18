package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor;

import BUS.ActionPaneEvent;
import BUS.CartView_BUS;
import BUS.QuantityEvent;
import BUS.SaleView_BUS;
import Design.GradientButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import Design.Combobox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

public class CartView extends JPanel {

//	private JPanel contentPane;
	public static JTable table;
	private JScrollPane scrollPane;
	public SaleView sale_view;
	private CartView self;
	public JLabel subTotalLabel;
	public JTextField TenTxt;
	public JTextField DiaChiTxt;
	public JTextField SDTTxt;
	public JTextField soNhanMonTxt;
	private JLabel lblSNhnMn;
	private JLabel TenLB;
	private JLabel DiaChiLB;
	private JLabel SDTLB;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	
	public double subTotal;
	public Combobox comboBox;
	public Combobox comboBox_1;

	/**
	 * Create the frame.
	 */
	public CartView(SaleView orderView) {
		self = this;
		sale_view = orderView;
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				odv.dispose();
//			}
//			@Override
//			public void windowClosed(WindowEvent e) {
//				odv.setVisible(true);
//			}
//		});
		
		
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				".No", "Hình ảnh", "Tên", "Size", "Đơn giá", "Số lượng", "Tổng cộng", "Xóa"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(5).setResizable(false);

		table.getColumn("Hình ảnh").setCellRenderer(new ImageRenderer());
		
		table.getColumn("Số lượng").setCellRenderer(new QuantityRenderer());
		table.getColumn("Số lượng").setCellEditor(new QuantityEditor(new CartView_BUS(self).qe));
		
		table.getTableHeader().setBackground(Color.decode("#CB356B"));
		table.getTableHeader().setForeground(Color.yellow);
		
		ActionPaneEvent ape = new ActionPaneEvent() {
			
			@Override
			public void onDelete(int row) {
					
					sale_view.odvf.Order_list.remove(row);
					sale_view.odvf.Size.remove(row);
					sale_view.odvf.Unitprice.remove(row);
					sale_view.odvf.Quantity.remove(row);
					sale_view.odvf.Total.remove(row);

					if(table.isEditing()) {
						table.getCellEditor().stopCellEditing();
						
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(row);
					
					
					new CartView_BUS(self).displaySubTotal();
					sale_view.odvf.displayCartLength();
				
				
			}
		};
		
		
		table.getColumn("Xóa").setCellRenderer(new ActionRenderer());
		table.getColumn("Xóa").setCellEditor(new ActionEditor(ape));
		table.setRowHeight(60);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderView.pw.splitPane.setRightComponent(orderView);
				orderView.odvf.displayItem();
				orderView.odvf.displayCartLength();
				orderView.setVisible(true);
//				dispose();
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(CartView.class.getResource("/IMG/back_hover_icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(CartView.class.getResource("/IMG/backicon.png")));
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(CartView.class.getResource("/IMG/backicon.png")));
		btnNewButton.setBorder(new EmptyBorder(0,0,0,0));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setPressedIcon(new ImageIcon(CartView.class.getResource("/IMG/back_hover_icon.png")));
		btnNewButton.setContentAreaFilled( false );
		
		JLabel lblNewLabel = new JLabel("Tổng thanh toán :");
		lblNewLabel.setForeground(Color.decode("#BD3F32"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		subTotalLabel = new JLabel("New label");
		subTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		GradientButton btnNewButton_1 = new GradientButton("Thanh Toán","#a8ff78","#78ffd6");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CartView_BUS(self).ThanhToan();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setColor1("#a8cc78");
				btnNewButton_1.setColor2("#78ccd6");
				btnNewButton_1.setForeground(Color.decode("#FFEE58"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setColor1("#a8ff78");
				btnNewButton_1.setColor2("#78ffd6");
				btnNewButton_1.setForeground(Color.decode("#ffffff"));
			}
		});
		btnNewButton_1.setFocusPainted(false);
		
		lblSNhnMn = new JLabel("Số nhận món :");
		lblSNhnMn.setForeground(new Color(203, 53, 107));
		lblSNhnMn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		soNhanMonTxt = new JTextField();
		soNhanMonTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		soNhanMonTxt.setColumns(10);
		
		TenLB = new JLabel("Tên :");
		TenLB.setForeground(new Color(203, 53, 107));
		TenLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		TenTxt = new JTextField();
		TenTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TenTxt.setColumns(10);
		
		DiaChiLB = new JLabel("Địa chỉ :");
		DiaChiLB.setForeground(new Color(203, 53, 107));
		DiaChiLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		SDTLB = new JLabel("Số điện thoại :");
		SDTLB.setForeground(new Color(203, 53, 107));
		SDTLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		DiaChiTxt = new JTextField();
		DiaChiTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DiaChiTxt.setColumns(10);
		
		SDTTxt = new JTextField();
		SDTTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SDTTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		
		JLabel MaNVtxt = new JLabel("New label");
		MaNVtxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên :");
		lblNewLabel_1.setForeground(new Color(203, 53, 107));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		JLabel lblLoin = new JLabel("Loại đơn :");
		lblLoin.setForeground(new Color(203, 53, 107));
		lblLoin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		comboBox = new Combobox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Tại chỗ")) {
					innitTaiCho();
				}else {
					innitTakeAway();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tại chỗ", "Mang đi"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblMKhuynMi = new JLabel("Mã khuyến mãi :");
		lblMKhuynMi.setForeground(new Color(203, 53, 107));
		lblMKhuynMi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		comboBox_1 = new Combobox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CartView_BUS(self).apDungKhuyenMai(comboBox_1.getSelectedItem().toString());
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new CartView_BUS(self).LayKhuyenMai()));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMKhuynMi, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addComponent(lblLoin, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(MaNVtxt, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(MaNVtxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLoin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblMKhuynMi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		panel_1 = new JPanel();
		
		lblNewLabel_2 = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSNhnMn, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addComponent(TenLB, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(DiaChiLB, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(SDTLB, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(soNhanMonTxt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(SDTTxt, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
									.addGap(309)
									.addComponent(lblNewLabel_2))
								.addComponent(TenTxt, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(DiaChiTxt, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subTotalLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(soNhanMonTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSNhnMn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TenLB, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(TenTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(DiaChiLB, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(SDTLB, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(DiaChiTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(SDTTxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(subTotalLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		setLayout(groupLayout);
		
		innitTaiCho();
		
		subTotal = 0.0;
		new CartView_BUS(self).displayCartItems();
		new CartView_BUS(self).displaySubTotal();
	}
	public void innitTaiCho() {
		if(TenLB != null &&
			TenTxt  != null &&
			DiaChiLB != null&&
			DiaChiTxt != null&&
			SDTLB != null&&
			SDTTxt != null) {
			
			TenLB.setVisible(false);
			TenTxt.setVisible(false);
			DiaChiLB.setVisible(false);
			DiaChiTxt.setVisible(false);
			SDTLB.setVisible(false);
			SDTTxt.setVisible(false);
			lblSNhnMn.setVisible(true);
			soNhanMonTxt.setVisible(true);
				
		}
		
	}
	public void innitTakeAway() {
		if(lblSNhnMn!=null && soNhanMonTxt!=null) {
			lblSNhnMn.setVisible(false);		
			soNhanMonTxt.setVisible(false);
			TenLB.setVisible(true);
			TenTxt.setVisible(true);
			DiaChiLB.setVisible(true);
			DiaChiTxt.setVisible(true);
			SDTLB.setVisible(true);
			SDTTxt.setVisible(true);
		}
			
		
	}
	class ImageRenderer extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			String photoName  = value.toString();
			JLabel label = new JLabel();
			label.setIcon(new ImageIcon(new ImageIcon(JtableTest.class.getResource(photoName)).getImage().getScaledInstance((table.getRowHeight()*400/235), table.getRowHeight(), Image.SCALE_SMOOTH)));
			return label;
			
		}
	}
	class QuantityRenderer extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			QuantityPane quantityPane = new QuantityPane(String.valueOf(sale_view.odvf.Quantity.get(row)));
			quantityPane.setBackground(new Color(200,200,200));
			return quantityPane;
		}
		
	}
	class QuantityEditor extends DefaultCellEditor{

		private QuantityEvent event;
		
		public QuantityEditor(QuantityEvent event) {
			super(new JCheckBox());
			this.event =event;	
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			QuantityPane qp =  new QuantityPane(String.valueOf(sale_view.odvf.Quantity.get(row)));
			qp.initEvent(event, row);
			
	
			return qp;
		}	
	}
	class ActionRenderer extends DefaultTableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			ActionPane ap = new ActionPane();
			ap.setBackground(getBackground());
			return new ActionPane();
		}
		
	}
	class ActionEditor extends DefaultCellEditor{
		private ActionPaneEvent event;
		
		public ActionEditor(ActionPaneEvent event) {
			super(new JCheckBox());
			this.event =event;	
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			ActionPane ap = new ActionPane();
			ap.initEvent(event, row);
			return ap;
		}	
	}
}
