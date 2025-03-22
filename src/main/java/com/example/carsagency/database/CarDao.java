package com.example.carsagency.database;

import com.example.carsagency.enums.BrakesType;
import com.example.carsagency.enums.TransmissionType;
import com.example.carsagency.models.Brand;
import com.example.carsagency.models.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.carsagency.models.Engine;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.Optional;
import java.sql.*;


public class CarDao extends MySQLConnection implements Dao<Car>{
    Connection conn = getConnection();
    @Override
    public Optional<Car> findById(int id) {
        return Optional.empty();
    }
    @Override
    public List<Car> findAll() {
        List<Car> carList = FXCollections.observableArrayList();
        String query = "select * from car";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Car c = new Car();
                c.setId_Car(rs.getInt("id_Car"));
                c.setColor(rs.getString("color"));
                c.setModel(rs.getString("model"));
                c.setYear(rs.getInt("year"));
                c.setPrice(rs.getDouble("price"));
                c.setBrand(getBrand(rs.getInt("brand_id")));
                c.setEngine(getEngine(rs.getInt("engine_id")));
                c.setMileage(rs.getInt("mileage"));
                c.setDoors(rs.getInt("doors"));
                c.setImage(rs.getString("image"));
                c.setBrakesType(BrakesType.valueOf(rs.getString("brakes_type")));
                c.setTransmissionType(TransmissionType.valueOf(rs.getString("transmission_type")));

                carList.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carList;
    }


    private Brand getBrand(int id_Brand) {
        String query = "select * from brand where id_Brand = " + id_Brand;
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Brand brand = new Brand();
            brand.setId_Brand(rs.getInt("id_Brand"));
            brand.setName(rs.getString("name"));
            return brand;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private Engine getEngine(int id_Engine) {
        String query = "select * from Engine where id_Engine = " + id_Engine;
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Engine Engine = new Engine();
            Engine.setId_Engine(rs.getInt("id_Engine"));
            Engine.setName(rs.getString("name"));
            return Engine;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean save(Car car) {
        String query = "insert into car (year, model, color, price, brand_id, mileage, doors, engine_id, image, brakes_type, transmission_type)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, car.getYear());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getBrand().getId_Brand());
            ps.setInt(6, car.getMileage());
            ps.setInt(7, car.getDoors());
            ps.setInt(8, car.getEngine().getId_Engine());
            ps.setString(9, car.getImage());
            ps.setString(10, car.getBrakesType().name());
            ps.setString(11, car.getTransmissionType().name());
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Car car) {
        String query = "update car set year=?, model=?, color=?, price=?, brand_id=?, mileage=?, doors=?, engine_id=?, image=?, brakes_type=?, transmission_type=? where car_id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, car.getYear());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getBrand().getId_Brand());
            ps.setInt(6, car.getMileage());
            ps.setInt(7, car.getDoors());
            ps.setInt(8, car.getEngine().getId_Engine());
            ps.setString(9, car.getImage());
            ps.setString(10, car.getBrakesType().name());
            ps.setString(11, car.getTransmissionType().name());
            ps.setInt(12, car.getId_Car());
            ps.execute();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id_Car) {
        String query = "delete from car where id_Car = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_Car);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
