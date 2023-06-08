package com.exprogs.polishexpression.models.stack;

public interface Stack {
    String peek();

    String pop();

    void push(char last);

    int getSize();
}
