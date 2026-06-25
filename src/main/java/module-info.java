module com.mik.tablehomework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires com.fasterxml.jackson.databind;

    opens com.mik.tablehomework to javafx.fxml;
    opens com.mik.tablehomework.controllers to javafx.fxml;
    opens com.mik.tablehomework.mail to javafx.fxml;

    opens com.mik.tablehomework.model to javafx.base, com.fasterxml.jackson.databind;

    exports com.mik.tablehomework;
    exports com.mik.tablehomework.controllers;
    exports com.mik.tablehomework.mail;
    opens com.mik.tablehomework.util to com.fasterxml.jackson.databind, javafx.base;
}