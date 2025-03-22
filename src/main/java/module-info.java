module com.example.carsagency {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.example.carsagency to javafx.fxml;
    opens com.example.carsagency.catalogs;
    opens com.example.carsagency.models;

    exports com.example.carsagency;
}