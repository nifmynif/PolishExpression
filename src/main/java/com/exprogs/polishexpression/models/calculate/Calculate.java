package com.exprogs.polishexpression.models.calculate;

import com.exprogs.polishexpression.models.expression.Expression;

import java.util.zip.DataFormatException;

public abstract class Calculate {
    private String result;

    public Calculate(Expression expression) {
        setExpression(expression);
    }

    private Expression expression;

    //check input expression(only decimal, math symbols and spaces)
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void checkExpression(String expression) throws DataFormatException {
        if (expression.isEmpty() || expression.replaceAll("[0-9+\\-*/^\\s.]", "").length() > 0)
            throw new DataFormatException("мы не можем решить данное выражение");
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Expression getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    //calculate input expression
    public abstract String calculate() throws DataFormatException;

    //find and calculate input parameters
    public String math(String operator, String operand1, String operand2) {
        return switch (operator) {
            case "+" -> String.valueOf(Double.parseDouble(operand2) + Double.parseDouble(operand1));
            case "-" -> String.valueOf(Double.parseDouble(operand2) - Double.parseDouble(operand1));
            case "*" -> String.valueOf(Double.parseDouble(operand2) * Double.parseDouble(operand1));
            case "/" -> String.valueOf(Double.parseDouble(operand2) / Double.parseDouble(operand1));
            case "^" -> String.valueOf(Math.pow(Double.parseDouble(operand2), Double.parseDouble(operand1)));
            default -> "";
        };
    }
}
