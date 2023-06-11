package com.exprogs.polishexpression;

import com.exprogs.polishexpression.models.calculate.Calculate;
import com.exprogs.polishexpression.models.calculate.PolishCalculate;
import com.exprogs.polishexpression.models.expression.Expression;
import com.exprogs.polishexpression.models.expression.PolishExpression;
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
    Expression expression;
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
            expression =new PolishExpression(input.getText());
            calculate = new PolishCalculate(expression);
            output.setText(calculate.getExpression().calculateFrom());
            try {
                mathOutput.setText(calculate.calculate());
            } catch (Exception e) {
                mathOutput.setText(e.getMessage());
            }
        } catch (Exception e) {
            output.setText(e.getMessage());
        }

    }
}