package com.exprogs.polishexpression.models.expression;

import com.exprogs.polishexpression.models.stack.Operand;

import java.util.zip.DataFormatException;

public class PolishExpression extends Expression {

    public PolishExpression(String infixExpr) throws DataFormatException {
        super(infixExpr);
    }

    public PolishExpression() {
        super();
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
            while (getOperator().isCloseBracket())
                getOperand().push(getOperator().pop().charAt(0));
            getOperator().pop();
        } else {
            getOperand().combine();
            if (getOperator().isLowerPrecedence(ch))
                getOperand().push(getOperator().pop().charAt(0));
            getOperator().push(ch);
        }
        return infixToPolish(infixExpr.substring(0, infixExpr.length() - 1));
    }
}
