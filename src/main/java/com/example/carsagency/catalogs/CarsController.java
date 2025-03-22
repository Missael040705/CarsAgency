package com.example.carsagency.catalogs;

import com.example.carsagency.CarsAgencyApplication;
import com.example.carsagency.database.BrandDao;
import com.example.carsagency.database.CarDao;
import com.example.carsagency.database.MySQLConnection;
import com.example.carsagency.enums.BrakesType;
import com.example.carsagency.models.Car;
import com.example.carsagency.models.Engine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public TextField tfYear;
    private List<Car> carList = new ArrayList<>();
    private ObservableList<Car> carOB = FXCollections.observableArrayList();

    private CarDao carDao=new CarDao();
    private BrandDao brandDao=new BrandDao();
    private Engine engineDao=new Engine();

    @FXML
    TableView<Car> tblCars;

    @FXML
    Text txtCarDetailId, txtCarDetailBrand, txtCarDetailModel, txtCarDetailPrice, txtCarDetailMileage, txtCarDetailDoors, txtCarDetailBreaks, txtCarDetailTransmission, txtCarDetailEngine, txtCarDetailYear;

    @FXML
    ImageView imgCarDetail;

    @FXML
    Button btnSave, btnEdit, btnReset, btnDelete;

    Connection conn = MySQLConnection.getConnection();

    boolean editMode = false; // id no se modifica

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        initCars();
        initCarsTableView();
        /*
        initForm():
        btnAdd(hnButton);
        btnDelete(hnButton);
        btnEdit(hnButton);
        btnReset(hnButton);
        * */

    }

    private void initCars() {

        initCarsTableView();
        initForm();
        //btnSave.setOnAction(HandlerButton);
        //btnReset.setOnAction(HandlerButton);

    }

    private void initCarsTableView() {
        // tblCars.setItems(FXCollections.observableList(carList)); conn properties (user)

        carOB = FXCollections.observableList(carDao.findAll());
        tblCars.setItems(carOB);


        tblCars.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Car car = tblCars.getSelectionModel().getSelectedItem();
                txtCarDetailId.setText(String.valueOf(car.getId_Car()));
                txtCarDetailBrand.setText(car.getBrand().getName());
                txtCarDetailModel.setText(car.getModel());
                txtCarDetailDoors.setText(String.valueOf(car.getDoors()));
                txtCarDetailMileage.setText(String.valueOf(car.getMileage()));
                txtCarDetailEngine.setText(car.getEngine().getName());
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

    /* EventHandler<ActionEvent> hnButton = (e) -> {
         if (e.getSource() = btnAdd) {
             SaveCar();
         }else if (event.getSource()==btnDelete){
         confirmDelete();
         }else if (event.getSource()==btnEdit){

         editMode = true;
         loadCar4Edit();

         }else if (event.getSource()==btnReset){
         resetForm();
         }

     };

     private void saveCar() {
         Car car = (editMode) ? tblCars.getSelectionModel().getSelectedItem():new Car();
         //Car car = new Car();
         car.setEngine(cmbEngine.getSelectionModel().setSelectedItem());
         model
         year
         mileage
         price
         image
         if(editmode){
         if(carDao.update(car)){
         sout("Car updated");
         editMode = false;
         carList.setAll(tblCars.getSelectionModel().getSelectedIndex(), car());
         }

         }

         else if(carDao.save(car)){
         sout("Succes");
         carList.add(car);

         }
resetForm();
resetCarDetails();
tblCar.setItems(FXCollections.observableArrayList(carDao.findAll()));
         tblCar.refresh();

         btnDelete.setDisable(false);
                btnEdit.setDisable(false);

     }
 */
    private void initForm() {
        /*cmb.setItems(FXCollections.observableArrayList)*/

    }

    private void resetForm() {
        /*
        tfModel.setText("");
        tfDoors.setText("");
        tfMileage.setText("");
        tfPrice.setText("");
        tfModel.setText("");
        *

        cmBrand.setValue(null);
        cmEngine.setValue(null);
        cmTransmission.setValue(null);
        cmBrakes.setValue(null);
        tfYear.RequestFocus();
        * */

    }

    private void resetCarDetails() {

        txtCarDetailId.setText("");
        txtCarDetailModel.setText("");
        txtCarDetailDoors.setText("");
        txtCarDetailMileage.setText("");
        txtCarDetailPrice.setText("");
        txtCarDetailYear.setText("");

        imgCarDetail.setImage(null);

    }

    private void resetDetails() {

    }

    private void loadCar4Edit() {
        Car car = tblCars.getSelectionModel().getSelectedItem();


        /*
        tfModel.setText(car.getModel());
        tfDoors.setText(String.valueOf(car.getDoors()));
        tfMileage.setText("");
        tfPrice.setText("");
        tfModel.setText("");
        *

        cmBrand.setValue(car.getBrand());
        cmEngine.setValue(car.getEngine());
        cmTransmission.setValue(car.getTransmission());
        cmBrakes.setValue(null);
        tfYear.RequestFocus();

        *******
        * */

    }

    private void confirmDelete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("Are u sure u want 2 delete this car");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            /*if( carDao.delete(car.getId())){
            sout("car succesfull");

            }
             */
        }
    }

    private void showMsg(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    private void updateTable(){
        //tblCars.setItems(FXCollecions.observableArrayList(carDao.findAll()));
        //tblCars.setItems(FXCollecions.observableArrayList(carDao.findAll()));
    }

}
