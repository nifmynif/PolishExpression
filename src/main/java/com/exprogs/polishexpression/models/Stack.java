package com.exprogs.polishexpression.models;

public interface Stack {
    String peek();

    String pop();

    void push(char last);

    int getSize();
}
