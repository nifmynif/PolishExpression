package com.exprogs.polishexpression;

import java.util.ArrayList;

public class Operator {

    private final ArrayList<Character> operator;

    public Operator() {
        this.operator = new ArrayList<>();
    }

    public Character seeLast() {
        if (operator.isEmpty())
            throw new IndexOutOfBoundsException("операторов нет");
        return operator.get(operator.size() - 1);
    }

    public Character getLast() {
        if (operator.isEmpty())
            throw new IndexOutOfBoundsException("операторов нет");
        Character tempOperator = operator.get(operator.size() - 1);
        operator.remove(operator.size() - 1);
        return tempOperator;
    }

    public void set(char last) {
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
}
