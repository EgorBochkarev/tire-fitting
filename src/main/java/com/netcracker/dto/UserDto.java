package com.netcracker.dto;

import com.netcracker.jpa.CarInfo;


public class UserDto {
    private int userId;

    private String name;

    private String location;

    private CarInfo carInfo;

    public UserDto(int userId, String name, String location, CarInfo carInfo) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.carInfo = carInfo;
    }

    public UserDto() {
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

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", carInfoId=" + carInfo.getCarInfoId() +
                '}';
    }
}
