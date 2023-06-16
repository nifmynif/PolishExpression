package com.exprogs.polishexpression.models.stack;

import java.util.List;
import java.util.Stack;

public class Operator {
    private final List<Character> operator = new Stack<>();

    public void add(char ch) {
        operator.add(ch);
    }

    public int size() {
        return operator.size();
    }

    public char peek() {
        return operator.get(operator.size() - 1);
    }

    public char pop() {
        return operator.remove(operator.size() - 1);
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
    public boolean isLeftBracket() {
        return operator.size() == 0 || peek() == '(';
    }

    //check precedence of input math symbol
    public int checkPrecedence(char ch) {
        return precedence(ch) - precedence(peek());
    }
}
