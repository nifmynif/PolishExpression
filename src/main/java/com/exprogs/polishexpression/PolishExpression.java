package com.exprogs.polishexpression;

public class PolishExpression {
    private final Operator operator;
    private final Operand operand;

    public PolishExpression(String infixExpr) {
        operand = new Operand();
        operator = new Operator();
        infixToPolish("(" + infixExpr + ")");
    }

    public String getPolish() {
        return operand.toString();
    }

    private Operand infixToPolish(String infixExpr) {
        if (infixExpr.isEmpty())
            return null;
        char ch = infixExpr.charAt(infixExpr.length() - 1);
        if (Character.isLetterOrDigit(ch))
            operand.set(ch);
        else if (ch == ')')
            operator.set(ch);
        else if (ch == '(') {
            while (operator.getSize() != 0 && operator.seeLast() != ')')
                operand.add(operator.getLast());
            operator.getLast();
        } else {
            operand.combine();
            if (operator.getSize() != 0 && operator.precedence(ch) <= operator.precedence(operator.seeLast()))
                operand.set(operator.getLast());
            operator.set(ch);
        }
        return infixToPolish(infixExpr.substring(0, infixExpr.length() - 1));
    }
}
