package com.exprogs.polishexpression.expression;

import com.exprogs.polishexpression.models.Operand;

import java.util.zip.DataFormatException;

public class PolishExpression extends Expression {

    public PolishExpression(String infixExpr) throws DataFormatException {
        super(infixExpr);
    }

    public String work() {
        infixToPolish("(" + getInfixExpr() + ")");
        return getPolish();
    }

    public String getPolish() {
        return getRes();
    }

    private Operand infixToPolish(String infixExpr) {
        if (infixExpr.isEmpty())
            return null;
        char ch = infixExpr.charAt(infixExpr.length() - 1);
        if (Character.isLetterOrDigit(ch))
            getOperand().pushWithCheck(ch);
        else if (ch == ')')
            getOperator().push(ch);
        else if (ch == '(') {
            while (getOperator().getSize() != 0 && !getOperator().peek().equals(")"))
                getOperand().push(getOperator().pop().charAt(0));
            getOperator().pop();
        } else {
            getOperand().combine();
            if (getOperator().getSize() != 0 && getOperator().precedence(ch) <= getOperator().precedence(getOperator().peek().charAt(0)))
                getOperand().pushWithCheck(getOperator().pop().charAt(0));
            getOperator().push(ch);
        }
        return infixToPolish(infixExpr.substring(0, infixExpr.length() - 1));
    }
}
