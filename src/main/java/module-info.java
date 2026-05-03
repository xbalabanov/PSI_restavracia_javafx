module com.example.psi_restavracia_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.psi_restavracia_javafx to javafx.fxml;
    exports com.example.psi_restavracia_javafx;
}