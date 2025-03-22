package com.example.carsagency.catalogs;

import com.example.carsagency.CarsAgencyApplication;
import com.example.carsagency.database.MySQLConnection;
import com.example.carsagency.models.Car;
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
    /* private CarDao carDao = new CarDao;*/

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
        /*Brand brand1 = new Brand(1, "Hyundai");
        Brand brand2 = new Brand(2, "Tesla");
        Engine engine1 = new Engine(1, "Diesel", "V");
        Engine engine2 = new Engine(2, "Electric", "V");
        carList.add(new Car(1, 2020, "Sante Fe", "Gray", 50000, 5000, 5, brand1, BrakesType.DISC, TransmissionType.AUTOMATIC, engine1, "hyundai_tucson.JPG"));
        carList.add(new Car(2, 2020, "Y", "Gray", 1500000, 9000, 4, brand2, BrakesType.DISC, TransmissionType.AUTOMATIC, engine2, "tesla_modelY.JPG"));

        //CHATGPT
        Brand bmw = new Brand(1, "BMW");
        Brand byd = new Brand(2, "BYD");
        Brand dodge = new Brand(3, "Dodge");
        Brand ford = new Brand(4, "Ford");
        Brand gmc = new Brand(5, "GMC");
        Brand kia = new Brand(6, "KIA");
        Brand lucid = new Brand(7, "Lucid Motors");
        Brand mercedes = new Brand(8, "Mercedes");
        Brand nissan = new Brand(9, "Nissan");
        Brand volvo = new Brand(10, "Volvo");
        Brand porsche = new Brand(11, "Porsche");
        Brand subaru = new Brand(12, "Subaru");
        Brand tesla = new Brand(13, "Tesla");
        Brand toyota = new Brand(14, "Toyota");
        Brand zeekr = new Brand(15, "Zeekr");

        // Inicializar Engines
        Engine electricV = new Engine(1, "Electric", "V");
        Engine hybridInline = new Engine(2, "Hybrid", "Inline");
        Engine gasolineV6 = new Engine(3, "Gasoline", "V6");
        Engine gasolineV8 = new Engine(4, "Gasoline", "V8");
        Engine dieselInline = new Engine(5, "Diesel", "Inline");

        // Agregar autos a la lista
        carList.add(new Car(1, 2024, "BMW i5", "Blue", 65000, 0, 4, bmw, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "bmw_i5.JPG"));
        carList.add(new Car(2, 2024, "BYD Song Pro", "Red", 35000, 0, 4, byd, BrakesType.DRLMS, TransmissionType.AUTOMATIC, hybridInline, "BYD_songpro.JPG"));
        carList.add(new Car(3, 2024, "Dodge Attitude", "White", 20000, 0, 4, dodge, BrakesType.HYDRAULIC, TransmissionType.MANUAL, gasolineV6, "dodge_attitude.JPG"));
        carList.add(new Car(4, 2024, "Ford Bronco", "Black", 50000, 0, 4, ford, BrakesType.DISC, TransmissionType.AUTOMATIC, gasolineV8, "ford_bronco.JPG"));
        carList.add(new Car(5, 2024, "GMC Hummer EV", "Silver", 110000, 0, 4, gmc, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "GMC_hummerEV.JPG"));
        carList.add(new Car(6, 2024, "KIA EV6", "Gray", 45000, 0, 4, kia, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "kia_ev6.JPG"));
        carList.add(new Car(7, 2024, "KIA K3", "Blue", 25000, 0, 4, kia, BrakesType.HYDRAULIC, TransmissionType.MANUAL, gasolineV6, "kia_K3.JPG"));
        carList.add(new Car(8, 2024, "Lucid Motors Lucid Air", "Black", 87000, 0, 4, lucid, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "lucidMotors_lucidAir.JPG"));
        carList.add(new Car(9, 2024, "Mercedes EQS", "White", 120000, 0, 4, mercedes, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "mercedes_eqs.JPG"));
        carList.add(new Car(10, 2024, "Nissan Sentra 24", "Gray", 25000, 0, 4, nissan, BrakesType.HYDRAULIC, TransmissionType.MANUAL, gasolineV6, "nissan_sentra24.JPG"));
        carList.add(new Car(11, 2024, "Polestar Volvo", "Silver", 55000, 0, 4, volvo, BrakesType.DRLMS, TransmissionType.AUTOMATIC, electricV, "polestar_volvo.JPG"));
        carList.add(new Car(12, 2024, "Porsche 911 Dakar", "Green", 180000, 0, 2, porsche, BrakesType.DISC, TransmissionType.AUTOMATIC, gasolineV8, "porsche_911dakar.JPG"));
        carList.add(new Car(13, 2024, "Subaru Forester", "Blue", 35000, 0, 4, subaru, BrakesType.DISC, TransmissionType.AUTOMATIC, gasolineV6, "subaru_forester.JPG"));
        carList.add(new Car(14, 2024, "Subaru Forester Compact", "White", 32000, 0, 4, subaru, BrakesType.HYDRAULIC, TransmissionType.MANUAL, gasolineV6, "subaru_foresterCMP.JPG"));
        carList.add(new Car(15, 2024, "Tesla Model Y", "Red", 50000, 0, 4, tesla, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "tesla_modelY.JPG"));
        carList.add(new Car(16, 2024, "Toyota Camry", "Black", 28000, 0, 4, toyota, BrakesType.HYDRAULIC, TransmissionType.MANUAL, gasolineV6, "toyota_camry.JPG"));
        carList.add(new Car(17, 2024, "Toyota Camry Hybrid", "Blue", 31000, 0, 4, toyota, BrakesType.DRLMS, TransmissionType.AUTOMATIC, hybridInline, "toyota_camryHybrid.JPG"));
        carList.add(new Car(18, 2024, "Toyota Prius", "White", 27000, 0, 4, toyota, BrakesType.DISC, TransmissionType.AUTOMATIC, hybridInline, "toyota_prius.JPG"));
        carList.add(new Car(19, 2024, "Toyota RAV4", "Silver", 34000, 0, 4, toyota, BrakesType.DISC, TransmissionType.AUTOMATIC, gasolineV6, "toyota_RAV4.JPG"));
        carList.add(new Car(20, 2024, "Volvo EX30", "Blue", 55000, 0, 4, volvo, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "volvo_EX30.JPG"));
        carList.add(new Car(21, 2024, "Zeekr X", "Gray", 45000, 0, 4, zeekr, BrakesType.DISC, TransmissionType.AUTOMATIC, electricV, "zeekr_X.JPG"));

        */

        String query = "select * from car;";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                // Consulta cada dato necesario para generar la persona, mientras aún haiga en la Base de Datos
                // Y lo añade a la lista
                System.out.println("Consulta: " + rs.getInt("id") + " Modelo: " + rs.getString("model"));
                carList.add(new Car(
                        rs.getInt("id"),
                        rs.getInt("year"),  // Asegúrate de que coincida con la BD
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("mileage"),
                        rs.getInt("doors"),
                        rs.getInt("brand_id"),
                        rs.getInt("brakes_type_id"),
                        rs.getInt("transmission_type_id"),
                        rs.getInt("engine_id"),
                        rs.getString("image")
                ));

                //carList.add(new Car(rs.getInt("id_product"), rs.getString("name"), rs.getDouble("price"), rs.getInt("category_id")));
            }

            tblCars.setItems(FXCollections.observableList(carList));
            //welcomeText.setText("Retrieving data...");
        } catch (SQLException e) {
            //welcomeText.setText(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private void initCarsTableView() {
        // tblCars.setItems(FXCollections.observableList(carList)); conn properties (user)
        /*
        carOB = FXCollections.observableList(carDao.finAll());
        tblCars.setItems(carOB);
        tblCars.setOnMouseClicked();
        * */

        tblCars.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Car car = tblCars.getSelectionModel().getSelectedItem();
                txtCarDetailId.setText(String.valueOf(car.getId()));
                //txtCarDetailBrand.setText(Brand.valueOf(rs.getString("brakes")));
                txtCarDetailModel.setText(car.getModel());
                txtCarDetailDoors.setText(String.valueOf(car.getDoors()));
                txtCarDetailMileage.setText(String.valueOf(car.getMileage()));
                //txtCarDetailEngine.setText(car.getEngine().getName());
                //txtCarDetailBreaks.setText(car.getBrakesType().toString());
                //txtCarDetailTransmission.setText(car.getTransmissionType().name());
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
