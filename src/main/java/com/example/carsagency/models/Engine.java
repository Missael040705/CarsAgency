package com.example.carsagency.models;

public class Engine {
    private int id_Engine;
    private String name;
    private String shape;

    public Engine() {
    }

    public Engine(int id_Engine, String name, String shape) {
        this.id_Engine = id_Engine;
        this.name = name;
        this.shape = shape;
    }

    public int getId_Engine() {
        return id_Engine;
    }

    public void setId_Engine(int id_Engine) {
        this.id_Engine = id_Engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }


}

