package com.exprogs.polishexpression;

import com.exprogs.polishexpression.models.calculate.Calculate;
import com.exprogs.polishexpression.models.calculate.PolishCalculate;
import com.exprogs.polishexpression.models.expression.Expression;
import com.exprogs.polishexpression.models.expression.PolishExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Label mathOutput;

    @FXML
    protected void onRunButtonClick() {
        //"(1+(2+33*44))";
        //"a+b*(c^d-e)^(f*g gg+h)-i*2";
        //String expression = "1+1+1+11";
        try {
            Expression p = new PolishExpression(input.getText());
            output.setText(p.work());
            Calculate calculate = new PolishCalculate(p.getRes());
            mathOutput.setText(calculate.work());
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }
}