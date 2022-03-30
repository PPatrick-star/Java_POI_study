package kr.poi.study;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

    public static void main(String [] args) {

        // new workbook
        Workbook wb = new XSSFWorkbook();
        //new sheet
        Sheet sheet1 = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet1.createRow(0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        CreationHelper createHelper = wb.getCreationHelper();

        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
