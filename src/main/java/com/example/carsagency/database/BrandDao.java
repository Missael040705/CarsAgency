package com.example.carsagency.database;

import com.example.carsagency.models.Brand;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BrandDao extends MySQLConnection implements Dao<Brand> {

    Connection conn = getConnection();

    @Override
    public Optional<Brand> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = FXCollections.observableArrayList();
        String query = "select * from brand limit 15";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Brand b = new Brand();
                b.setId_Brand(rs.getInt("id_Brand"));
                b.setName(rs.getString("name"));
                brandList.add(b);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    private Brand getBrand(int id_Car) {
        String query = "select * from brand where id_Brand = " + id_Car;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            Brand brand = new Brand();
            brand.setId_Brand(rs.getInt("id_Brand"));
            brand.setName(rs.getString("name"));
            return brand;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean save(Brand brand) {
        String query = "insert into brand (id_Brand, name)" +
                " values (?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, brand.getId_Brand());
            ps.setString(2, brand.getName());
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Brand brand) {
        String query = "update car set id_Brand=?, name=? where id_Brand = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, brand.getId_Brand());
            ps.setString(2, brand.getName());
            ps.execute();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id_Brand) {
        String query = "delete from brand where id_Brand = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_Brand);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}