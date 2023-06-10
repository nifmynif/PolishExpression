package com.exprogs.polishexpression.models.calculate;

import com.exprogs.polishexpression.models.expression.Expression;
import com.exprogs.polishexpression.models.expression.PolishExpression;
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
            Calculate p = new PolishCalculate(new PolishExpression(";"));
            p.calculate();
        } catch (DataFormatException e) {
            assertEquals("в приведенной формуле присутствуют ошибки", e.getMessage());
        }
    }

    @Test
    void testDDT() throws Exception {
        try (InputStream in = new FileInputStream("src/test/resources/DDTCalculate.xls");
             HSSFWorkbook wb = new HSSFWorkbook(in)) {
            Calculate calculate = new PolishCalculate();
            Expression expression = new PolishExpression();
            calculate.setExpression(expression);
            Sheet sheet = wb.getSheet("Лист1");
            for (Row row : sheet) {
                try {
                    calculate.getExpression().setInfixExpr(row.getCell(0).getStringCellValue());
                    expression.calculateFrom();
                    assertEquals(row.getCell(1).getStringCellValue(), calculate.calculate());
                } catch (DataFormatException e) {
                    assertEquals(row.getCell(1).getStringCellValue(), e.getMessage());
                }
            }
        }
    }
}