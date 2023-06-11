package com.exprogs.polishexpression.models.expression;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.DataFormatException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolishExpressionTest {

    @Test
    void nullInputTest() throws DataFormatException {
        PolishExpression polishExpression = new PolishExpression();
        assertEquals("0", polishExpression.calculateFrom());
    }

    @Test
    void onePlusOneTest() throws DataFormatException {
        PolishExpression polishExpression = new PolishExpression("1+1");
        assertEquals("+ 1 1", polishExpression.calculateFrom());
        polishExpression.setInfixExpr("1+1");
        assertEquals("+ 1 1", polishExpression.calculateFrom());
    }

    @Test
    void wrongInputTest() {
        try {
            PolishExpression polishExpression = new PolishExpression("+ 1 1");
            polishExpression.calculateFrom();
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
            assertEquals("в приведенной формуле присутствуют ошибки", e.getMessage());
        }
    }

    @Test
    void testDDT() throws Exception {
        try (InputStream in = new FileInputStream("src/test/resources/DDTExpression.xls");
             HSSFWorkbook wb = new HSSFWorkbook(in)) {
            Expression exception = new PolishExpression();
            Sheet sheet = wb.getSheet("Лист1");
            for (Row row : sheet) {
                try {
                    exception.setInfixExpr(row.getCell(0).getStringCellValue());
                    assertEquals(row.getCell(1).getStringCellValue(), exception.calculateFrom());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    assertEquals(row.getCell(1).getStringCellValue(), e.getMessage());
                }}
        }
    }
}