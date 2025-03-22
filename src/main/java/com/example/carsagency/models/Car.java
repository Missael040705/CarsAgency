package com.example.carsagency.models;

import com.example.carsagency.enums.BrakesType;
import com.example.carsagency.enums.TransmissionType;

public class Car {
    private int id_Car;
    private int year;
    private String model;
    private String color;
    private double price;
    private int mileage;
    private int doors;
    /* */
    private String image;
    private Brand brand;
    private Engine engine;
    private BrakesType brakesType;
    private TransmissionType transmissionType;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Car() {
    }

    public Car(int id_Car, int year, String model, String color, double price, int mileage, int doors, Brand brand, BrakesType brakesType, TransmissionType transmissionType, Engine engine, /*,   */String image) {
        this.id_Car = id_Car;
        this.year = year;
        this.model = model;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
        this.doors = doors;
        this.brand = brand;
        this.brakesType = brakesType;
        this.transmissionType = transmissionType;
        this.engine = engine;
        this.image = image;

    }

    public int getId_Car() {
        return id_Car;
    }

    public void setId_Car(int id_Car) {
        this.id_Car = id_Car;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public BrakesType getBrakesType() {
        return brakesType;
    }

    public void setBrakesType(BrakesType brakesType) {
        this.brakesType = brakesType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /*

    public BrakesType getBrakesType() {
        return brakesType;
    }

    public void setBrakesType(BrakesType brakesType) {
        this.brakesType = brakesType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }



     */
}
