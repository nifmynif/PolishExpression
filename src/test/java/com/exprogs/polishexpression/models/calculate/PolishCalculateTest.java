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
    void nullInputTest() {
        Calculate c = new PolishCalculate();
        assertEquals("0", c.work());
    }

    @Test
    void onePlusOneTest() throws DataFormatException {
        Calculate c = new PolishCalculate("+ 1 1");
        assertEquals("2.0", c.work());
        c.setExpression("+ 1 1");
        assertEquals("2.0", c.work());
    }

    @Test
    void wrongInputTest() {
        try {
            Calculate p = new PolishCalculate(";");
            p.work();
        } catch (DataFormatException e) {
            assertEquals("мы не можем решить данное выражение", e.getMessage());
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
                    calculate.setExpression(row.getCell(0).getStringCellValue());
                    assertEquals(row.getCell(1).getStringCellValue(), calculate.work());
                } catch (DataFormatException e) {
                    assertEquals(row.getCell(1).getStringCellValue(), e.getMessage());
                }
            }
        }
    }
}