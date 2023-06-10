package com.exprogs.polishexpression.models.expression;

import com.exprogs.polishexpression.models.stack.Operand;
import com.exprogs.polishexpression.models.stack.Operator;

import java.util.zip.DataFormatException;

public abstract class Expression {
    private Operator operator;
    private Operand operand;
    private String infixExpr;

    public Expression() throws DataFormatException {
        setInfixExpr("0");
    }

    public Expression(String infixExpr) throws DataFormatException {
        setInfixExpr(infixExpr);
    }

    public Operator getOperator() {
        return operator;
    }

    public Operand getOperand() {
        return operand;
    }

    public String getInfixExpr() {
        return infixExpr;
    }

    //check input expression(only decimal, math symbols and english letters)
    public void setInfixExpr(String infixExpr) throws DataFormatException {
        if (infixExpr.isEmpty()
                || infixExpr.replaceAll("[0-9a-zA-Z+\\-*/().^]", "").length() > 0
                || !Character.isLetterOrDigit(infixExpr.charAt(infixExpr.length() - 1)))
            throw new DataFormatException("в приведенной формуле присутствуют ошибки");
        if (infixExpr.contains(".") && !infixExpr.matches(".*\\d[.]\\d.*"))
            throw new DataFormatException("неправильная запись дробных чисел");
        if (infixExpr.charAt(0) == '+')
            this.infixExpr = infixExpr.substring(1);
        else if (Character.isLetterOrDigit(infixExpr.charAt(0)) || infixExpr.charAt(0) == '-')
            this.infixExpr = infixExpr;
        else
            throw new DataFormatException("в приведенной формуле присутствуют ошибки");
        operand = new Operand();
        operator = new Operator();
    }

    public abstract String calculateFrom();

    public String getRes() {
        return getOperand().toString();
    }
}
