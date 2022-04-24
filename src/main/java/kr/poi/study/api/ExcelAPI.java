package kr.poi.study.api;

import kr.poi.study.domain.Member;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static kr.poi.study.util.ModelUtil.readModelVo;

public class ExcelAPI {

    private static final String memberMockup = "src/main/resources/mocks/Member.json";

    public void makeFile() {

        // new workbook
        Workbook wb = new XSSFWorkbook();
        //new sheet
        Sheet sheet1 = Objects.requireNonNull(wb).createSheet("new sheet");


        final List<Member> memberMockData = readModelVo(Member.class, memberMockup);

        Integer[] list = {0, 1, 2, 3, 4, 5, 6, 7};

        Iterator<Integer> iterator = Arrays.stream(list).iterator();
        int rowIndex = 0;
        int cellIndex = 0; // 처음에는 ID 학번등 고정값을 넣기 위해 사용한 변수

        do {
            Integer param = iterator.next();
            Row row = sheet1.createRow(rowIndex++);


            Cell cell0 = row.createCell(0);
            if (cellIndex == 0) { // 처음에 고정값

                cell0.setCellValue("ID");
                Cell cell1 = row.createCell(1);
                cell1.setCellValue("학번");
                Cell cell2 = row.createCell(2);
                cell2.setCellValue("제목");
                Cell cell3 = row.createCell(3);
                cell3.setCellValue("내용");
                cellIndex++;

            } else {  // 다음부터는 순차적으로 값이 들어감

                cell0.setCellValue(param);
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(param++);
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(param * param);
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(0);


            }


        } while (iterator.hasNext());

        CreationHelper createHelper = wb.getCreationHelper();

        // Write the output to a file
        try (
                OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
