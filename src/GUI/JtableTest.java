package GUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.ListSelectionModel;

public class JtableTest {
	private JTable table;
	private JLabel lblNewLabel;

	public JtableTest() {
		
        JFrame frame = new JFrame("JButtonTable Example");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{{"button 1", "foo","/IMG/hamburger1.png"},
                    {"button 2", "bar","/IMG/hamburger2.png"}}, new Object[]{"Action", "String","Image"});

        table = new JTable(dm);
        table.setRowHeight(100);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(true);
        table.getColumn("Action").setCellRenderer(new ButtonRenderer("Details"));
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(),"Details"));
        table.getColumn("Image").setCellRenderer(new ImageRenderer());

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0, 0, 584, 289);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

        table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..
        frame.getContentPane().setLayout(null);

        frame.getContentPane().add(scroll);
        
        lblNewLabel = new JLabel("");
        
        lblNewLabel.setBounds(115, 357, 335, 150);
        frame.getContentPane().add(lblNewLabel);

       
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JtableTest();
            }
        });

    }
    class ButtonRenderer extends JButton implements TableCellRenderer {
    	
	    public ButtonRenderer(String value) {
	        setOpaque(true);
	        setText(value);
	    }
	
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        return this;
	    }
	}
class ButtonEditor extends DefaultCellEditor {
	
	    protected JButton button;
	    private String label;
	
	    public ButtonEditor(JCheckBox checkBox, String label) {
	        super(checkBox);
	        button = new JButton(label);
	        button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					lblNewLabel.setIcon(new ImageIcon(JtableTest.class.getResource(table.getValueAt(table.getSelectedRow(), 2).toString())));
				}	
			});
	        button.setOpaque(true);
	    }
	
	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	        return button;
	    }
    
	}
}


class ImageRenderer extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String photoName  = value.toString();
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(new ImageIcon(JtableTest.class.getResource(photoName)).getImage().getScaledInstance(80, 235/4, Image.SCALE_SMOOTH)));
		return label;
		
	}
	
	
}
