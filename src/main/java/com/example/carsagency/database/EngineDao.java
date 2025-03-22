package com.example.carsagency.database;

import com.example.carsagency.models.Engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.Optional;
import java.sql.*;

public class EngineDao extends MySQLConnection implements Dao<Engine>{
    //engine

    //engine
    Connection conn = getConnection();
    @Override
    public Optional<Engine> findById(int id) {
        return Optional.empty();
    }
    @Override
    public List<Engine> findAll() {
        List<Engine> EngineList = FXCollections.observableArrayList();
        String query = "select * from Engine";
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Engine m = new Engine();
                m.setId_Engine(rs.getInt("id_Engine"));
                m.setName(rs.getString("name"));
                m.setShape(rs.getString("shape"));
                EngineList.add(m);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return EngineList;
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
    public boolean save(Engine Engine) {
        String query = "insert into Engine (id_Engine,shape, name)" +
                " values (?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Engine.getId_Engine());
            ps.setString(2, Engine.getShape());
            ps.setString(3, Engine.getName());

            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Engine Engine) {
        String query = "update Engine set id_Engine=?, name=?, shape=? where id_Engine = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Engine.getId_Engine());
            ps.setString(2, Engine.getName());
            ps.setString(3, Engine.getShape());
            ps.execute();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id_Engine) {
        String query = "delete from Engine where id_Engine = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id_Engine);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
