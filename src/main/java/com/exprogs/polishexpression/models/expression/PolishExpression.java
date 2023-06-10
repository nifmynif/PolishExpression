package com.exprogs.polishexpression.models.expression;

import com.exprogs.polishexpression.models.stack.Operand;

import java.util.zip.DataFormatException;

public class PolishExpression extends Expression {

    public PolishExpression(String infixExpr) throws DataFormatException {
        super(infixExpr);
    }

    public PolishExpression() throws DataFormatException {
        super();
    }

    public String calculateFrom() {
        infixToPolish("(" + getInfixExpr() + ")");
        if (getOperator().getSize() != 0)
            while (getOperator().isLeftBracket())
                getOperand().push(getOperator().pop().charAt(0));
        return getPolish();
    }

    public String getPolish() {
        return getResult();
    }

    //convert infix expression to polish
    private Operand infixToPolish(String infixExpr) {
        if (infixExpr.isEmpty())
            return null;
        char ch = infixExpr.charAt(0);
        if (Character.isLetterOrDigit(ch) || ch == '.')
            getOperand().pushWithCheck(ch);
        else if (ch == '(')
            getOperator().push(ch);
        else if (ch == ')') {
            if (getOperator().getSize() != 0)
                while (!getOperator().peek().equals("("))
                    getOperand().push(getOperator().pop().charAt(0));
            getOperator().pop();
        } else {
            getOperand().combine();
            if (getOperator().isLeftBracket())
                getOperator().push(ch);
            else if (getOperator().checkPrecedence(ch) <= 0) {
                while (!getOperator().peek().equals("("))
                    getOperand().push(getOperator().pop().charAt(0));
                getOperator().push(ch);
            } else
                getOperator().push(ch);
        }
        return infixToPolish(infixExpr.substring(1));
    }
}
