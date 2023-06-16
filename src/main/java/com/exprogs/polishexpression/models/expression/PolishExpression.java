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

    public String calculateFrom() throws DataFormatException {
        infixToPolish("(" + getInfixExpr() + ")");
        return getPolish();
    }

    public String getPolish() {
        return getResult();
    }

    //convert infix expression to polish
    private Operand infixToPolish(String infixExpr) throws DataFormatException {
        if (infixExpr.isEmpty())
            return null;
        char ch = infixExpr.charAt(0);
        if (Character.isLetterOrDigit(ch) || ch == '.')
            getOperand().addWithCheck(ch);
        else if (ch == '(')
            getOperator().add(ch);
        else if (ch == ')') {
            if (getOperator().size() != 0) {
                while (getOperator().peek() != '(')
                    getOperand().add(getOperator().pop());
                getOperator().pop();
            }else throw new DataFormatException("нет хватает скобок");
        } else {
            getOperand().combine();
            if (getOperator().isLeftBracket())
                getOperator().add(ch);
            else if (getOperator().checkPrecedence(ch) < 0) {
                while (getOperator().peek() != '(')
                    getOperand().add(getOperator().pop());
                getOperator().add(ch);
            } else if (getOperator().checkPrecedence(ch) == 0) {
                getOperand().add(getOperator().pop());
                getOperator().add(ch);
            } else
                getOperator().add(ch);
        }
        return infixToPolish(infixExpr.substring(1));
    }
}
