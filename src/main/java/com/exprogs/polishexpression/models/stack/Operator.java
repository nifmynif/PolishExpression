package com.exprogs.polishexpression.models.stack;

import java.util.ArrayList;

public class Operator implements Stack {

    private final ArrayList<Character> operator;

    public Operator() {
        this.operator = new ArrayList<>();
    }

    //get last element
    public String peek() {
        if (operator.isEmpty())
            throw new IndexOutOfBoundsException("операторов нет");
        return operator.get(operator.size() - 1).toString();
    }

    //get and remove last element
    public String pop() {
        String tempOperator = peek();
        operator.remove(operator.size() - 1);
        return tempOperator;
    }

    //set element
    public void push(char last) {
        operator.add(last);
    }

    public int getSize() {
        return operator.size();
    }

    //precedence of math symbols
    public int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    //check in a loop that takes statements up to the opening brace
    public boolean isCloseBracket() {
        return getSize() != 0 && !peek().equals(")");
    }

    //check precedence of input math symbol
    public boolean isLowerPrecedence(char ch) {
        return getSize() != 0 && precedence(ch) <= precedence(peek().charAt(0));
    }
}
