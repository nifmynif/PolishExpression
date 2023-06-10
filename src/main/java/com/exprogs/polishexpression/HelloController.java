package com.exprogs.polishexpression;

import com.exprogs.polishexpression.models.calculate.Calculate;
import com.exprogs.polishexpression.models.calculate.PolishCalculate;
import com.exprogs.polishexpression.models.expression.Expression;
import com.exprogs.polishexpression.models.expression.PolishExpression;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button run;

    @FXML
    public void initialize() {
        run.disableProperty().bind(Bindings.isEmpty(input.textProperty()));
    }

    @FXML
    protected void onRunButtonClick() {
        output.setText("");
        mathOutput.setText("");
        try {
            Expression p = new PolishExpression(input.getText());
            output.setText(p.calculateFrom());
            try {
                Calculate calculate = new PolishCalculate(p.getRes());
                mathOutput.setText(calculate.work());
            } catch (Exception e) {
                mathOutput.setText(e.getMessage());
            }
        } catch (Exception e) {
            output.setText(e.getMessage());
        }

    }
}