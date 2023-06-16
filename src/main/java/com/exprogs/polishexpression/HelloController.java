package com.exprogs.polishexpression;

import com.exprogs.polishexpression.models.calculate.Calculate;
import com.exprogs.polishexpression.models.calculate.PolishCalculate;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TextField input;
    @FXML
    private Label output;
    @FXML
    private Label mathOutput;
    @FXML
    private Button run;
    Calculate calculate;

    @FXML
    public void initialize() {
        run.disableProperty().bind(Bindings.isEmpty(input.textProperty()));
    }

    @FXML
    protected void onRunButtonClick() {
        output.setText("");
        mathOutput.setText("");
        try {
            calculate = new PolishCalculate();
            calculate.getExpression().setInfixExpr(input.getText());
            output.setText(calculate.getExpression().calculateFrom());
            try {
                mathOutput.setText(calculate.calculate());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                mathOutput.setText(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            output.setText(e.getMessage());
        }

    }
}