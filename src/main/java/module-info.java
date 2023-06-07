module com.exprogs.polishexpression {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exprogs.polishexpression to javafx.fxml;
    exports com.exprogs.polishexpression;
}