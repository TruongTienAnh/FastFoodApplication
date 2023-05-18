package BUS;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DAO.DAODonHang;
import DAO.JDBCUtil;
import DTO.DONHANG;
import GUI.OderView;
import GUI.SuaDonHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;


public class OrderView_BUS {
	private OderView order_view;
	public ArrayList<DONHANG> ds_don_hang;
	
	public OrderView_BUS(OderView order_view) {
		this.order_view = order_view;
		this.ds_don_hang  = new DAODonHang().selectAll();
	}
	
	public void hienThiBangDonHang() {
		DefaultTableModel model = (DefaultTableModel) this.order_view.table.getModel();
		model.setRowCount(0);
		DateFormat df =new  SimpleDateFormat("dd/MM/Y HH:mm:ss");
		for(int i = 0; i< this.ds_don_hang.size(); i++) {
			DONHANG don_hang = this.ds_don_hang.get(i);
			model.addRow(new Object[] {don_hang.getId_don_hang(),don_hang.getId_nhan_vien(),df.format(don_hang.getNgay_dat_hang()),
					don_hang.getTong_tien(), don_hang.getTen(), don_hang.getSo_dien_thoai(), don_hang.getDia_chi(),don_hang.getSo_dat_mon(), don_hang.getTrang_thai()});
		}
	}
	public void hienThiDonHangLanDau() {
		hienThiBangDonHang();
	}
	
	public void timKiemDonHang(String timKiem) {
		this.ds_don_hang = new DAODonHang().timKiemDonHang(timKiem);
		hienThiBangDonHang();
	}
	public void sapXep_LocTheoNgay() {
		JComboBox combobox = this.order_view.comboBox;
		if(combobox.getSelectedItem().toString().equalsIgnoreCase("Sắp xếp")) {
			Collections.sort(this.ds_don_hang, (o1,o2)-> o1.getId_don_hang().compareToIgnoreCase(o2.getId_don_hang()));
		}else if(combobox.getSelectedItem().toString().equalsIgnoreCase("Tổng tiền(Thấp -> Cao)")) {
			Collections.sort(this.ds_don_hang, (o1,o2)-> (int)(o1.getTong_tien() - o2.getTong_tien()));
		}else if(combobox.getSelectedItem().toString().equalsIgnoreCase("Tổng tiền(Cao -> Thấp)")) {
			Collections.sort(this.ds_don_hang, (o1,o2)-> (int)(o2.getTong_tien() - o1.getTong_tien()));
		}
		
		DefaultTableModel model = (DefaultTableModel) this.order_view.table.getModel();
		model.setRowCount(0);
		DateFormat df =new  SimpleDateFormat("dd/MM/Y HH:mm:ss");
		for(int i = 0; i< this.ds_don_hang.size(); i++) {
			DONHANG don_hang = this.ds_don_hang.get(i);
			Date date_duoi = this.order_view.dateChooser.getDate();
			Date date_tren = this.order_view.dateChooser_1.getDate();
			
//			System.out.println(df.format(date_duoi)+"<"+df.format(don_hang.getNgay_dat_hang()) +":" + (don_hang.getNgay_dat_hang().compareTo(date_duoi) >= 0));
//			System.out.println(df.format(date_tren)+">"+df.format(don_hang.getNgay_dat_hang()) +":" + (don_hang.getNgay_dat_hang().compareTo(date_tren) <= 0));
			
			if(don_hang.getNgay_dat_hang().compareTo(date_duoi) >= 0 &&
				don_hang.getNgay_dat_hang().compareTo(date_tren) <= 0	) {
				
			model.addRow(new Object[] {don_hang.getId_don_hang(),don_hang.getId_nhan_vien(),df.format(don_hang.getNgay_dat_hang()),
					don_hang.getTong_tien(), don_hang.getTen(), don_hang.getSo_dien_thoai(), don_hang.getDia_chi(),don_hang.getSo_dat_mon(), don_hang.getTrang_thai()});
		
			}
		}
	}
	public void xoaDonHang() {
		int row = this.order_view.table.getSelectedRow();
		if(row == -1) {
			JOptionPane.showConfirmDialog(null,"Làm ơn chọn 1 đơn hàng để xóa");
		}else {
			int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chăn muốn xóa đơn hàng ?");
			if(confirm == 0) {
				for(int i = 0 ; i<this.ds_don_hang.size();i++) {
					DONHANG don_hang = this.ds_don_hang.get(i);
					if(don_hang.getId_don_hang().equalsIgnoreCase(this.order_view.table.getValueAt(row, 0).toString())) {
						new DAODonHang().delete(don_hang);
					}
				}
			}
		}
		hienThiDonHangLanDau();
	}
	public void inDonHangRaExcel() {
		 try{
	           JFileChooser jFileChooser = new JFileChooser("C:\\Users\\ACER\\Desktop\\DoAnJava\\src\\inDonHang\\");
	           jFileChooser.showSaveDialog(null);
	           File saveFile = jFileChooser.getSelectedFile();
	           
	           if(saveFile != null){
	        	   
	               Workbook wb = new XSSFWorkbook();
	               Sheet sheet = wb.createSheet("DonHang");
	               
	               Row rowCol = sheet.createRow(0);
	               for(int i=0;i<this.order_view.table.getColumnCount();i++){
	                   Cell cell = rowCol.createCell(i);
	                   cell.setCellValue(this.order_view.table.getColumnName(i));
	               }
	               
	               for(int j=0;j<this.order_view.table.getRowCount();j++){
	                   Row row = sheet.createRow(j+1);
	                   for(int k=0;k<this.order_view.table.getColumnCount();k++){
	                       Cell cell = row.createCell(k);
	                       if(this.order_view.table.getValueAt(j, k)!=null){
	                           cell.setCellValue(this.order_view.table.getValueAt(j, k).toString());
	                       }
	                   }
	               }
	               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
	               wb.write(out);
	               wb.close();
	               out.close();
	               
	               
	               if (Desktop.isDesktopSupported()) {
	                       Desktop.getDesktop().open(saveFile);
	               }   
	               
	           }else{
	               JOptionPane.showMessageDialog(null,"Đường dẫn không hợp lệ");
	           }
	       }catch(FileNotFoundException e){
	           System.out.println(e);
	       }catch(IOException io){
	           System.out.println(io);
	       }
		 
	}
	public void inDonHangPDF(String id_don_hang) {
		try {
			Hashtable map = new Hashtable<>();
			JasperReport report = JasperCompileManager.compileReport("src/TemplateXuatHoaDon/template_inHOADON.jrxml");
			
			map.put("SoHD", id_don_hang);
			
			JasperPrint jasper_print = JasperFillManager.fillReport(report, map, JDBCUtil.getConnection());
//			JasperViewer.viewReport(jasper_print,false);
			
			Exporter exporter = (Exporter) new JRPdfExporter();
            ExporterInput exporterInput = new SimpleExporterInput(jasper_print);
            exporter.setExporterInput(exporterInput);
            File pdf = new File("src/HoaDon/"+id_don_hang+".pdf");
            FileOutputStream fos = new FileOutputStream(pdf);
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fos);
            exporter.setExporterOutput(exporterOutput);
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            fos.close();
            exporterOutput.close();
            
            if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdf);
            }   
		} catch (Exception e) {
			
		}
	}
	public void hienThiNgayLucDau() {
		if(this.ds_don_hang.size()>0) {
			this.order_view.dateChooser.setDate(this.ds_don_hang.get(this.ds_don_hang.size()-1).getNgay_dat_hang());
			this.order_view.dateChooser_1.setDate(this.ds_don_hang.get(0).getNgay_dat_hang());
		}else {
			this.order_view.dateChooser.setDate(new Date(System.currentTimeMillis()));
			this.order_view.dateChooser_1.setDate(new Date(System.currentTimeMillis()));
		}
	}
	public void chinhSuaDonHang() {
		int row = this.order_view.table.getSelectedRow();
		if(row!=-1) {
			for(int i = 0; i<ds_don_hang.size(); i++) {
				if(ds_don_hang.get(i).getId_don_hang().equalsIgnoreCase(this.order_view.table.getValueAt(row, 0).toString())) {
					SuaDonHang sdh = new SuaDonHang(this.order_view,ds_don_hang.get(i));
				}
			}
		}else {
			JOptionPane.showConfirmDialog(null, "Vui lòng chọn một đơn hàng để sửa","Cảnh báo",JOptionPane.CANCEL_OPTION);
		}
	}
}
