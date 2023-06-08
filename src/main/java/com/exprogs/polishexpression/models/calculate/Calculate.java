package com.exprogs.polishexpression.models.calculate;

import java.util.zip.DataFormatException;

public abstract class Calculate {
    private String expression;

    private String res;

    public Calculate() {
        expression = "0";
    }

    public Calculate(String expression) throws DataFormatException {
        setExpression(expression);
    }

    public void setExpression(String expression) throws DataFormatException {
        if (expression.isEmpty() || expression.replaceAll("[0-9+\\-*/^\\s]", "").length() > 0)
            throw new DataFormatException("мы не можем решить данное выражение");
        this.expression = expression;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getExpression() {
        return expression;
    }

    public String getRes() {
        return res;
    }

    public abstract String work();

    public String math(String operator, String operand1, String operand2) {
        return switch (operator) {
            case "+" -> String.valueOf(Double.parseDouble(operand1) + Double.parseDouble(operand2));
            case "-" -> String.valueOf(Double.parseDouble(operand1) - Double.parseDouble(operand2));
            case "*" -> String.valueOf(Double.parseDouble(operand1) * Double.parseDouble(operand2));
            case "/" -> String.valueOf(Double.parseDouble(operand1) / Double.parseDouble(operand2));
            case "^" -> String.valueOf(Math.pow(Double.parseDouble(operand1), Double.parseDouble(operand2)));
            default -> "";
        };
    }
}
