package com.exprogs.polishexpression.models.calculate;

import com.exprogs.polishexpression.models.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class PolishCalculate extends Calculate {

    public PolishCalculate(Expression expression) throws DataFormatException {
        super(expression);
    }

    public String calculate() throws DataFormatException {
        checkExpression(getExpression().getResult());
        ArrayList<String> arrayExpression = new ArrayList<>(List.of(getExpression().getResult().split(" ")));
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
        if (arrayExpression.get(0).equals("-")) {
            arrayExpression.set(1, String.valueOf(Double.parseDouble(arrayExpression.get(1)) * (-1)));
            arrayExpression.remove(0);
        }
        if (arrayExpression.size() > 2)
            for (int i = 0; i < arrayExpression.size() - 2; i++) {
                if (arrayExpression.get(i).matches("-?\\d+(\\.\\d+)?")
                        && arrayExpression.get(i + 1).matches("[+\\-*/^]")
                        && arrayExpression.get(i + 2).matches("-?\\d+(\\.\\d+)?")) {
                    arrayExpression.add(math(arrayExpression.get(i + 1), arrayExpression.get(i), arrayExpression.get(i + 2)));
                    if (Double.parseDouble(arrayExpression.get(i)) < 0 && arrayExpression.get(i + 1).equals("-"))
                        arrayExpression.set(i + 3, String.valueOf(Double.parseDouble(arrayExpression.get(i + 3)) * (-1)));
                    arrayExpression.remove(i);
                    arrayExpression.remove(i);
                    arrayExpression.remove(i);
                    i = -1;
                }
            }
        if (arrayExpression.get(0).equals("+"))
            setResult(arrayExpression.get(1));
        else if (arrayExpression.get(0).matches("[*/^]"))
            throw new DataFormatException("у вас ошибка в формуле");
        else
            setResult(arrayExpression.get(0));
        return getResult();
    }
}
