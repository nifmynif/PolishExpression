package com.exprogs.polishexpression;

import com.exprogs.polishexpression.expression.PolishExpression;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField input;
    @FXML
    private Label output;

    @FXML
    protected void onRunButtonClick() {
        //"(1+(2+33*44))";
        //"a+b*(c^d-e)^(f*g gg+h)-i*2";
        //String expression = "1+1+1+11";
        try {
            PolishExpression p = new PolishExpression(input.getText());
            output.setText(p.work());
        } catch (Exception e) {
            output.setText(e.getMessage());
        }
    }
}