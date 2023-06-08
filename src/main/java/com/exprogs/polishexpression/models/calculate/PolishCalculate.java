package com.exprogs.polishexpression.models.calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class PolishCalculate extends Calculate {
    public PolishCalculate() {
        super();
    }

    public PolishCalculate(String expression) throws DataFormatException {
        super(expression);
    }

    public String work() {
        ArrayList<String> arrayExpression = new ArrayList<>(List.of(getExpression().split(" ")));
        for (int i = 0; i < arrayExpression.size() - 2; i++) {
            if (arrayExpression.get(i).matches("[+\\-*/^]")
                    && arrayExpression.get(i + 1).matches("-?\\d+(\\.\\d+)?")
                    && arrayExpression.get(i + 2).matches("-?\\d+(\\.\\d+)?")) {
                arrayExpression.set(i, math(arrayExpression.get(i), arrayExpression.get(i + 1), arrayExpression.get(i + 2)));
                arrayExpression.remove(i + 1);
                arrayExpression.remove(i + 1);
                i = -1;
            }
        }
        if (arrayExpression.get(0).equals("-"))
            setRes(arrayExpression.get(0) + arrayExpression.get(1));
        else
            setRes(arrayExpression.get(0));
        return getRes();
    }
}
