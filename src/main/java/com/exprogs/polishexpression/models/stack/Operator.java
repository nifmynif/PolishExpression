package com.exprogs.polishexpression.models.stack;

import java.util.ArrayList;
import java.util.Objects;

public class Operator extends AbstructQueue {

    public Operator() {
        super(new ArrayList<Character>());
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
        return size() == 0 || element().equals("(");
    }

    //check precedence of input math symbol
    public int checkPrecedence(char ch) {
        return precedence(ch) - precedence(Objects.requireNonNull(element()).toString().charAt(0));
    }
}
