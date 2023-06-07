package com.exprogs.polishexpression;

import java.util.ArrayList;

public class Operand {
    private final ArrayList<StringBuilder> operand;
    private boolean flag = false;

    public Operand() {
        this.operand = new ArrayList<>();
    }

    public StringBuilder seeLast() {
        if (operand.isEmpty())
            throw new IndexOutOfBoundsException("операндов нет");
        return operand.get(operand.size() - 1);
    }

    public StringBuilder getLast() {
        if (operand.isEmpty())
            throw new IndexOutOfBoundsException("операндов нет");
        StringBuilder tempOperand = operand.get(operand.size() - 1);
        operand.remove(operand.size() - 1);
        return tempOperand;
    }

    public void set(char last) {
        if (flag) {
            String temp = getLast().toString().trim() + last;
            operand.add(new StringBuilder(temp));
        } else {
            operand.add(new StringBuilder(" " + last + " "));
            flag = true;
        }
    }

    public int getSize() {
        return operand.size();
    }

    public boolean combine() {
        flag = false;
        try {
            if (getSize() > 1) {
                StringBuilder operand1 = getLast();
                StringBuilder operand2 = getLast();
                operand.add(operand2.append(operand1));
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ArithmeticException("проверьте правильность введенной формулы");
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : operand) {
            res.append(stringBuilder).append(" ");
        }
        return res.reverse().toString().trim();
    }
}
