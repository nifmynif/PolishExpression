package com.exprogs.polishexpression.expression;

import com.exprogs.polishexpression.models.Operand;
import com.exprogs.polishexpression.models.Operator;

import java.util.zip.DataFormatException;

public abstract class Expression {
    private final Operator operator;
    private final Operand operand;

    private String infixExpr;

    public Expression(String infixExpr) throws DataFormatException {
        setInfixExpr(infixExpr);
        operand = new Operand();
        operator = new Operator();
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
        this.infixExpr = infixExpr;
    }

    public abstract String work();

    public String getRes() {
        return getOperand().toString();
    }
}
