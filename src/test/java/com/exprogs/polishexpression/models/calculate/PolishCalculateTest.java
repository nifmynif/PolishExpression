package com.exprogs.polishexpression.models.calculate;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class PolishCalculateTest {

    @Test
    void wrongInputTest() {
        try {
            Calculate polishCalculate = new PolishCalculate();
            polishCalculate.getExpression().setInfixExpr(";");
            polishCalculate.calculate();
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            assertEquals("в приведенной формуле присутствуют ошибки", e.getMessage());
        }
    }

    @Test
    void testDDT() throws Exception {
        try (InputStream in = new FileInputStream("src/test/resources/DDTCalculate.xls");
             HSSFWorkbook wb = new HSSFWorkbook(in)) {
            Calculate calculate = new PolishCalculate();
            Sheet sheet = wb.getSheet("Лист1");
            for (Row row : sheet) {
                try {
                    calculate.getExpression().calculateFrom(row.getCell(0).getStringCellValue());
                    assertEquals(row.getCell(1).getStringCellValue(), calculate.calculate());
                } catch (DataFormatException e) {
                    System.out.println(e.getMessage());
                    assertEquals(row.getCell(1).getStringCellValue(), e.getMessage());
                }
            }
        }
    }
}