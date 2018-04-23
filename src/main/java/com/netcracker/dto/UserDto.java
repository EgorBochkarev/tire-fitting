package com.netcracker.dto;

import com.netcracker.jpa.CarInfo;


public class UserDto {

    private int userId;

    private String name;

    private CarInfo carInfo;

    public UserDto(int userId, String name, CarInfo carInfo) {
        this.userId = userId;
        this.name = name;
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
                ", carInfoId=" + carInfo.getCarInfoId() +
                '}';
    }
}
