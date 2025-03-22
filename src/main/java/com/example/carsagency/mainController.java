package com.example.carsagency;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class mainController implements Initializable {
    @FXML
    private MenuItem mnuCars, mnuBrands;

    @FXML
    private Label welcomeText;

    @FXML
    BorderPane mainContainter;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mnuCars.setOnAction(handlerMenus);
        mnuBrands.setOnAction(handlerMenus);
    }

    EventHandler<ActionEvent> handlerMenus = event ->
    {
        if (event.getSource() == mnuCars) {
            mainContainter.setCenter(loadCatalog1());
        } else if (event.getSource() == mnuBrands) {
            mainContainter.setCenter(loadCatalog2());
        }
    };

    private Parent loadCatalog1() {
//        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER);
//        Label label = new Label("Cars Catalog");
//        hBox.getChildren().add(label);
//        return hBox;
        FXMLLoader fxmlloader = new FXMLLoader(CarsAgencyApplication.class.getResource("catalogs/cars-view.fxml"));
        try {
            Parent root = fxmlloader.load();
            return root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HBox loadCatalog2() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label label = new Label("Brands Catalog");
        hBox.getChildren().add(label);
        return hBox;
    }
}