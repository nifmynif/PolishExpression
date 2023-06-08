module com.exprogs.polishexpression {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exprogs.polishexpression to javafx.fxml;
    exports com.exprogs.polishexpression;
    exports com.exprogs.polishexpression.expression;
    opens com.exprogs.polishexpression.expression to javafx.fxml;
    exports com.exprogs.polishexpression.models;
    opens com.exprogs.polishexpression.models to javafx.fxml;
}