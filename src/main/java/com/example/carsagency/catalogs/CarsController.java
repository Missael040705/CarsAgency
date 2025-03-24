package com.example.carsagency.catalogs;

import com.example.carsagency.CarsAgencyApplication;
import com.example.carsagency.database.BrandDao;
import com.example.carsagency.database.CarDao;
import com.example.carsagency.database.EngineDao;
import com.example.carsagency.database.MySQLConnection;
import com.example.carsagency.enums.BrakesType;
import com.example.carsagency.enums.TransmissionType;
import com.example.carsagency.models.Brand;
import com.example.carsagency.models.Car;
import com.example.carsagency.models.Engine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CarsController implements Initializable {

    private List<Car> carList = new ArrayList<>();
    private ObservableList<Car> carOB = FXCollections.observableArrayList();

    private CarDao carDao = new CarDao();
    private BrandDao brandDao = new BrandDao();
    private EngineDao engineDao = new EngineDao();

    @FXML
    TableView<Car> tblCars;

    @FXML
    Text txtCarDetailId, txtCarDetailBrand, txtCarDetailModel, txtCarDetailPrice, txtCarDetailMileage, txtCarDetailDoors, txtCarDetailBreaks, txtCarDetailTransmission, txtCarDetailEngine, txtCarDetailYear, txtCarDetailColor;

    @FXML
    TextField tfYear, tfModel, tfColor, tfPrice, tfMileage, tfDoor, tfImage;

    @FXML
    ImageView imgCarDetail;

    @FXML
    Button btnSave, btnEdit, btnReset, btnDelete;

    @FXML
    ComboBox<Brand> cbBrand;
    @FXML
    ComboBox<TransmissionType> cbTransmission;
    @FXML
    ComboBox<BrakesType> cbBrakes;
    @FXML
    ComboBox<Engine> cbEngine;

    Connection conn = MySQLConnection.getConnection();

    boolean editMode = false; // id no se modifica

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        initCars();
        initCarsTableView();

        initForm();
        btnSave.setOnAction(hnButton);
        btnDelete.setOnAction(hnButton);
        btnEdit.setOnAction(hnButton);
        btnReset.setOnAction(hnButton);

    }

    private void initCars() {

        initCarsTableView();
        initForm();
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setOnAction(hnButton);
        btnReset.setOnAction(hnButton);

    }

    private void initCarsTableView() {
        // tblCars.setItems(FXCollections.observableList(carList)); conn properties (user)

        carOB = FXCollections.observableList(carDao.findAll());
        tblCars.setItems(carOB);

        tblCars.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Car car = tblCars.getSelectionModel().getSelectedItem();
                txtCarDetailId.setText(String.valueOf(car.getId_Car()));
                //txtCarDetailBrand.setText(car.getBrand().getName());
                txtCarDetailBrand.setText(car.getBrand_name());
                txtCarDetailModel.setText(car.getModel());
                txtCarDetailColor.setText(car.getColor());
                txtCarDetailDoors.setText(String.valueOf(car.getDoors()));
                txtCarDetailMileage.setText(String.valueOf(car.getMileage()));
                //txtCarDetailEngine.setText(car.getEngine().getName());
                txtCarDetailEngine.setText(car.getEngine_name());
                txtCarDetailBreaks.setText(car.getBrakesType().toString());
                txtCarDetailTransmission.setText(car.getTransmissionType().name());
                txtCarDetailPrice.setText(String.valueOf(car.getPrice()));
                txtCarDetailYear.setText(String.valueOf(car.getPrice()));
                imgCarDetail.setImage(new Image(String.valueOf(CarsAgencyApplication.class.getResource("images/" + car.getImage().toString()))));
                imgCarDetail.setFitHeight(200);
                imgCarDetail.setFitWidth(400);

                btnDelete.setDisable(false);
                btnEdit.setDisable(false);
            }
        });
    }

    EventHandler<ActionEvent> hnButton = (e) -> {
        if (e.getSource() == btnSave) {

            if (validate()) {
                saveCar();
            }

        } else if (e.getSource() == btnDelete) {
            confirmDelete();
        } else if (e.getSource() == btnEdit) {

            editMode = true;
            loadCar4Edit();

        } else if (e.getSource() == btnReset) {
            resetForm();
        }

    };

    private void saveCar() {
        Car car = (editMode) ? tblCars.getSelectionModel().getSelectedItem() : new Car();

        car.setEngine(cbEngine.getSelectionModel().getSelectedItem());
        car.setModel(tfModel.getText());
        car.setBrakesType(cbBrakes.getSelectionModel().getSelectedItem());
        car.setTransmissionType(cbTransmission.getSelectionModel().getSelectedItem());
        car.setDoors(Integer.parseInt(tfDoor.getText()));
        car.setPrice(Double.parseDouble(tfPrice.getText()));
        car.setImage(tfImage.getText());
        car.setMileage(Integer.parseInt(tfMileage.getText()));
        car.setBrand(cbBrand.getSelectionModel().getSelectedItem());
        car.setYear(Integer.parseInt(tfYear.getText()));
        car.setColor(tfColor.getText());

        if (editMode) {
            if (carDao.update(car)) {
                showMsg("Car Updated");
                carOB.set(tblCars.getSelectionModel().getSelectedIndex(), car);
                editMode = false;
                //carList.setAll(tblCars.getSelectionModel().getSelectedIndex(), car());
            }

        } else if (carDao.save(car)) {
            showMsg("Car Successfully added");
            carList.add(car);

        }

        resetForm();
        resetCarDetails();
        tblCars.setItems(FXCollections.observableArrayList(carDao.findAll()));
        tblCars.refresh();

        btnDelete.setDisable(true);
        btnEdit.setDisable(true);

    }

    private void initForm() {
        cbBrakes.setItems(FXCollections.observableArrayList(BrakesType.values()));
        cbTransmission.setItems(FXCollections.observableArrayList(TransmissionType.values()));
        cbBrand.setItems(FXCollections.observableArrayList(brandDao.findAll()));
        cbEngine.setItems(FXCollections.observableArrayList(engineDao.findAll()));
    }

    private void resetForm() {

        tfYear.setText("");
        tfModel.setText("");
        tfDoor.setText("");
        tfColor.setText("");
        tfMileage.setText("");
        tfPrice.setText("");
        tfModel.setText("");
        tfImage.setText("");

        cbBrand.setValue(null);
        cbEngine.setValue(null);
        cbTransmission.setValue(null);
        cbBrakes.setValue(null);

        cbEngine.setPromptText("Select a Engine");
        cbBrakes.setPromptText("Select a Brake Type");
        cbTransmission.setPromptText("Select a Transmission Type");
        cbBrand.setPromptText("Select a Brand");

        tfYear.requestFocus();

        editMode = false;
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void resetCarDetails() {

        txtCarDetailId.setText("");
        txtCarDetailModel.setText("");
        txtCarDetailDoors.setText("");
        txtCarDetailMileage.setText("");
        txtCarDetailPrice.setText("");
        txtCarDetailYear.setText("");
        txtCarDetailColor.setText("");
        txtCarDetailBrand.setText("");
        txtCarDetailTransmission.setText("");
        txtCarDetailEngine.setText("");
        txtCarDetailBreaks.setText("");
        imgCarDetail.setImage(null);

    }

    private void resetDetails() {

    }

    private void loadCar4Edit() {
        Car car = tblCars.getSelectionModel().getSelectedItem();

        tfYear.setText(String.valueOf(car.getYear()));
        tfColor.setText(String.valueOf(car.getColor()));
        tfModel.setText(car.getModel());
        tfDoor.setText(String.valueOf(car.getDoors()));
        tfMileage.setText(String.valueOf(car.getMileage()));
        tfPrice.setText(String.valueOf(car.getPrice()));
        tfImage.setText(car.getImage());
        tfModel.setText(car.getModel());

        cbBrand.setValue(car.getBrand());
        cbEngine.setValue(car.getEngine());
        cbTransmission.setValue(car.getTransmissionType());
        cbBrakes.setValue(car.getBrakesType());
        tfYear.requestFocus();

        //editMode = false;
    }

    private void confirmDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("Are u sure u want 2 delete this car");

        Optional<ButtonType> response = alert.showAndWait();
        Car car = tblCars.getSelectionModel().getSelectedItem();
        if (response.get() == ButtonType.OK) {
            if (carDao.delete(car.getId_Car())) {
                showMsg("Car deleted");
                //reloadTable();
                //carOB.remove(tblCars.getSelectionModel().getSelectedIndex());
                carOB.remove(car);
                reloadTable();
                resetForm();
                resetCarDetails();
            }
        }
    }

    private void showMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    private void updateTable() {
        tblCars.setItems(FXCollections.observableArrayList(carDao.findAll()));
        tblCars.setItems(FXCollections.observableArrayList(carDao.findAll()));
    }

    private void reloadTable() {
        //carList.add();
        tblCars.setItems(FXCollections.observableArrayList(carDao.findAll()));
        tblCars.refresh();
    }

    private boolean validate() {
        if (tfYear.getText().trim().isEmpty() ||
                tfModel.getText().trim().isEmpty() ||
                tfColor.getText().trim().isEmpty() ||
                tfPrice.getText().trim().isEmpty() ||
                tfMileage.getText().trim().isEmpty() ||
                tfDoor.getText().trim().isEmpty() ||
                tfImage.getText().trim().isEmpty()) {
            showMsg("Fill al Fields.");
            return false;
        }

        try {
            Integer.parseInt(tfYear.getText());
            Double.parseDouble(tfPrice.getText());
            Integer.parseInt(tfMileage.getText());
            Integer.parseInt(tfDoor.getText());
        } catch (NumberFormatException e) {
            showMsg("Year, Price, Mileage and Door must be numeric");
            return false;
        }

        if (cbEngine.getValue() == null ||
                cbTransmission.getValue() == null ||
                cbBrakes.getValue() == null ||
                cbBrand.getValue() == null) {
            showMsg("All selections must be made.");
            return false;
        }

        return true;
    }
}
