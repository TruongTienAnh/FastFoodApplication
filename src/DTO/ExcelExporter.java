package DTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;

public class ExcelExporter {
    public static String export(ArrayList<THONGKE> data, String title) {
        // Tạo một workbook mới
    	try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo một sheet mới
        XSSFSheet sheet = workbook.createSheet("Thống kê");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        // Tạo một ô mới cho tiêu đề
        Cell titleCell = row.createCell(0);

        // Đặt giá trị cho ô tiêu đề
        titleCell.setCellValue(title);

        // Tạo một CellStyle để định dạng tiêu đề
        CellStyle titleCellStyle = workbook.createCellStyle();

        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleCellStyle.setFont(titleFont);

        // Đặt thuộc tính cho ô tiêu đề
        titleCell.setCellStyle(titleCellStyle);
        
        row = sheet.createRow(rowNum++);
        Cell tcell1 = row.createCell(0);
		tcell1.setCellValue("STT");

		Cell tcell2 = row.createCell(1);
		tcell2.setCellValue("Thời gian");

		Cell tcell3 = row.createCell(2);
		tcell3.setCellValue("Tổng tiền");
        for (THONGKE rowData : data) {
            row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(rowData.getSTT());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(rowData.getDate());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(rowData.getDoanhThu());
        }

        // Ghi workbook ra file Excel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("untitle.xlsx"));
        fileChooser.setDialogTitle("Save Excel file"); 

        // Tạo một bộ lọc để chỉ cho phép lưu file với định dạng .xlsx
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
        fileChooser.setFileFilter(filter);

        // Hiển thị hộp thoại JFileChooser
        int userSelection = fileChooser.showSaveDialog(null);
        String filePath1 ="";
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            filePath1 = fileChooser.getSelectedFile().getAbsolutePath();
            
            try {
                // Tạo workbook và các sheet, thêm dữ liệu và định dạng

                // Lưu workbook ra file Excel
                FileOutputStream outputStream = new FileOutputStream(filePath1);
                workbook.write(outputStream);
                workbook.close();

                System.out.println("File saved to: " + filePath1);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    return filePath1;

}
}
