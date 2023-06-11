package com.exprogs.polishexpression.models.stack;

import java.util.ArrayList;
import java.util.Objects;

public class Operand extends AbstructQueue {
    private boolean flag = false;
    private char prev;


    public Operand() {
        super(new ArrayList<StringBuilder>());
    }

    // set element with check if its one part (123 or abc)
    public void addWithCheck(char last) throws IndexOutOfBoundsException {
        if (!Character.isLetterOrDigit(prev) && prev != '.')
            flag = false;
        if (flag) {
            String temp = last + Objects.requireNonNull(poll()).toString().trim();
            super.add(new StringBuilder(" " + temp + " "));
        } else
            add(last);
        prev = last;
    }

    // set last unit with check if its many parts (1 2 3 or a b c)
    @Override
    public boolean add(Object o) {
        prev = (char) o;
        flag = true;
        return super.add(new StringBuilder(" " + o + " "));
    }

    //take 2 last elements and combine it to 1
    public void combine() throws IndexOutOfBoundsException {
        flag = false;
        prev = '*';
        if (size() > 1) {
            String operand1 = (String) poll();
            String operand2 = (String) poll();
            super.add(new StringBuilder(" " + operand2 + " " + operand1 + " "));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object element : getQueue()) {
            result.append(element);
        }
        return result.reverse().toString()
                .trim()
                .replaceAll(" {2}", " ")
                .replaceAll(" {2}", " ")
                .replaceAll(" {2}", " ");
    }
}
