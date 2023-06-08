import java.util.zip.DataFormatException;

import com.exprogs.polishexpression.expression.PolishExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolishExpressionTest {

    @Test
    void onePlusOneTest() throws DataFormatException {
        PolishExpression p = new PolishExpression("1+1");
        assertEquals(p.work(), "+ 1 1");
    }

    @Test
    void WrongInputTest() {
        try {
            PolishExpression p = new PolishExpression("+ 1 1");
            assertEquals("+ 1 1", p.work());
        } catch (DataFormatException e) {
            assertEquals("в приведенной формуле присутствуют ошибки", e.getMessage());
        }
    }
}