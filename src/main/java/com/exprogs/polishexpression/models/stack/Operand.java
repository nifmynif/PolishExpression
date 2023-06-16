package com.exprogs.polishexpression.models.stack;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Operand {
    private final List<StringBuilder> operand = new Stack<>();
    private boolean flag = false;
    private char prev;

    public StringBuilder pop() {
        return operand.remove(operand.size() - 1);
    }

    // set element with check if its one part (123 or abc)
    public void addWithCheck(char last) throws IndexOutOfBoundsException {
        if (!Character.isLetterOrDigit(prev) && prev != '.')
            flag = false;
        if (flag) {
            String temp = last + pop().toString().trim();
            operand.add(new StringBuilder(" " + temp + " "));
        } else
            add(last);
        prev = last;
    }

    // set last unit with check if its many parts (1 2 3 or a b c)
    public void add(char ch) {
        prev = ch;
        flag = true;
        operand.add(new StringBuilder(" " + ch + " "));
    }

    //take 2 last elements and combine it to 1
    public void combine() throws IndexOutOfBoundsException {
        flag = false;
        prev = '*';
        if (operand.size() > 1) {
            String operand1 = pop().toString();
            String operand2 = Objects.requireNonNull(pop()).toString();
            operand.add(new StringBuilder(" " + operand2 + " " + operand1 + " "));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object element : operand) {
            result.append(element);
        }
        return result.reverse().toString()
                .trim()
                .replaceAll(" {2}", " ")
                .replaceAll(" {2}", " ")
                .replaceAll(" {2}", " ");
    }
}
