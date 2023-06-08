module com.exprogs.polishexpression {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exprogs.polishexpression to javafx.fxml;
    exports com.exprogs.polishexpression;
    exports com.exprogs.polishexpression.models.expression;
    opens com.exprogs.polishexpression.models.expression to javafx.fxml;
    exports com.exprogs.polishexpression.models.stack;
    opens com.exprogs.polishexpression.models.stack to javafx.fxml;
    exports com.exprogs.polishexpression.models.calculate;
    opens com.exprogs.polishexpression.models.calculate to javafx.fxml;
}