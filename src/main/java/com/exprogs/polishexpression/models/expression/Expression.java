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

    public void setInfixExpr(String infixExpr) throws DataFormatException {
        if (infixExpr.isEmpty() || infixExpr.replaceAll("[0-9a-zA-Z+\\-*/()^]", "").length() > 0)
            throw new DataFormatException("в приведенной формуле присутствуют ошибки");
        if (infixExpr.charAt(0) == '+')
            this.infixExpr = infixExpr.substring(1);
        else
            this.infixExpr = infixExpr;
        operand = new Operand();
        operator = new Operator();
    }

    public abstract String work();

    public String getRes() {
        return getOperand().toString();
    }
}
