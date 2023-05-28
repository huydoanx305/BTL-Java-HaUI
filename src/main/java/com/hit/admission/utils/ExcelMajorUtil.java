package com.hit.admission.utils;

import com.hit.admission.controller.MajorDetailController;
import com.hit.admission.dto.MajorDetailDTO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Huy Doan
 */
public class ExcelMajorUtil {

    public static void exportFileExample(MajorDetailController majorDetailController, String excelFilePath) {
        // Tạo workbook mới
        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo một trang tính mới
            Sheet sheet = workbook.createSheet("Major List");

            // Tạo hàng đầu tiên (tiêu đề)
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Mã ngành", "Tên ngành", "Khối", "Chỉ tiêu", "Điểm chuẩn"};

            // Đặt các giá trị tiêu đề vào các ô trong hàng đầu tiên
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Đặt các giá trị dữ liệu từ danh sách Major vào các ô
            int rowNum = 1;
            List<MajorDetailDTO> majorDetailDTOs = majorDetailController.getMajorDetailsForAdmin(LocalDate.now().getYear(), null);
            for (MajorDetailDTO majorDetail : majorDetailDTOs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(majorDetail.getId());
                row.createCell(1).setCellValue(majorDetail.getCode());
                row.createCell(2).setCellValue(majorDetail.getName());
                if (ObjectUtils.isNotEmpty(majorDetail.getBlocks())) {
                    row.createCell(3).setCellValue(majorDetail.getBlocks());
                } else {
                    row.createCell(3).setCellValue("#N/A");
                }
                if (ObjectUtils.isNotEmpty(majorDetail.getAmountStudentReceived())) {
                    row.createCell(4).setCellValue(majorDetail.getAmountStudentReceived());
                } else {
                    row.createCell(4).setCellValue("");
                }
                if (ObjectUtils.isNotEmpty(majorDetail.getBenchMark())) {
                    row.createCell(5).setCellValue(majorDetail.getBenchMark());
                } else {
                    row.createCell(5).setCellValue("");
                }
            }

            // Tự động điều chỉnh kích thước cột cho phù hợp với nội dung
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi workbook vào file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void importFile() {

    }

}
