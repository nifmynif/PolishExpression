package com.exprogs.polishexpression;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String expression = "(1+(2+3*4))";
        //String expression = "a+b*(c^d-e)^(f+g*h)-i";
        PolishExpression p=new PolishExpression(expression);
        welcomeText.setText(p.getPolish());
    }
}