package com.netcracker.dto;


public class UserWrapper {

    private int userId;

    private String name;

    private String location;

    private String carBrand;

    private double tireRadius;

    private String tireType;

    public UserWrapper(int userId, String name, String location, String carBrand, double tireRadius, String tireType) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.carBrand = carBrand;
        this.tireRadius = tireRadius;
        this.tireType = tireType;
    }

    public UserWrapper() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public double getTireRadius() {
        return tireRadius;
    }

    public void setTireRadius(double tireRadius) {
        this.tireRadius = tireRadius;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    @Override
    public String toString() {
        return "UserWrapper{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", tireRadius=" + tireRadius +
                ", tireType='" + tireType + '\'' +
                '}';
    }
}
