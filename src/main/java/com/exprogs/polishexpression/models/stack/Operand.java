package com.exprogs.polishexpression.models.stack;

import java.util.ArrayList;

public class Operand implements Stack {
    private final ArrayList<StringBuilder> operand;
    private boolean flag = false;
    private char prev;

    public Operand() {
        this.operand = new ArrayList<>();
    }

    public String peek() throws IndexOutOfBoundsException {
        if (operand.isEmpty())
            throw new IndexOutOfBoundsException("операндов нет");
        return operand.get(operand.size() - 1).toString();
    }

    public String pop() throws IndexOutOfBoundsException {
        String tempOperand = peek();
        operand.remove(operand.size() - 1);
        return tempOperand;
    }

    public void pushWithCheck(char last) throws IndexOutOfBoundsException {
        if (!Character.isLetterOrDigit(prev))
            flag = false;
        if (flag) {
            String temp = pop().trim() + last;
            operand.add(new StringBuilder(" " + temp + " "));
        } else
            push(last);
        prev = last;
    }

    public void push(char last) {
        operand.add(new StringBuilder(" " + last + " "));
        prev = last;
        flag = true;
    }

    public int getSize() {
        return operand.size();
    }

    public void combine() throws IndexOutOfBoundsException {
        flag = false;
        prev = '*';
        if (getSize() > 1) {
            String operand1 = pop();
            String operand2 = pop();
            operand.add(new StringBuilder(" " + operand2 + " " + operand1 + " "));
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : operand) {
            res.append(stringBuilder);
        }
        return res.reverse().toString()
                .trim()
                .replaceAll(" {2}", " ")
                .replaceAll(" {2}", " ");
    }
}
