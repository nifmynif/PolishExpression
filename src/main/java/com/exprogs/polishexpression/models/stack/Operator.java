package com.exprogs.polishexpression.models.stack;

import java.util.ArrayList;

public class Operator implements Stack{

    private final ArrayList<Character> operator;

    public Operator() {
        this.operator = new ArrayList<>();
    }

    public String peek() {
        if (operator.isEmpty())
            throw new IndexOutOfBoundsException("операторов нет");
        return operator.get(operator.size() - 1).toString();
    }

    public String pop() {
        String tempOperator = peek();
        operator.remove(operator.size() - 1);
        return tempOperator;
    }

    public void push(char last) {
        operator.add(last);
    }

    public int getSize() {
        return operator.size();
    }

    public int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
    public boolean isCloseBracket() {
        return getSize() != 0 && !peek().equals(")");
    }
    public boolean isLowerPrecedence(char ch) {
        return getSize() != 0 && precedence(ch) <= precedence(peek().charAt(0));
    }
}
