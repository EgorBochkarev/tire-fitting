package com.netcracker.jpa;

import javax.persistence.*;


@Entity
@Table(name = "cars_info")
public class CarInfo {

    @Id
    @GeneratedValue
    @Column(name = "car_info_id")
    private int carInfoId;

    @Basic
    @Column(name = "car_brand")
    private String carBrand;

    @Basic
    @Column(name = "tire_radius")
    private double tireRadius;

    @Basic
    @Column(name = "tire_type")
    private String tireType;

    public CarInfo(int carInfoId, String carBrand, double tireRadius, String tireType) {
        this.carInfoId = carInfoId;
        this.carBrand = carBrand;
        this.tireRadius = tireRadius;
        this.tireType = tireType;
    }

    public CarInfo(String carBrand, double tireRadius, String tireType) {
        this.carBrand = carBrand;
        this.tireRadius = tireRadius;
        this.tireType = tireType;
    }

    public CarInfo() {
    }

    public int getCarInfoId() {
        return carInfoId;
    }

    public void setCarInfoId(int carInfoId) {
        this.carInfoId = carInfoId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarInfo that = (CarInfo) o;

        if (carInfoId != that.carInfoId) return false;
        if (Double.compare(that.tireRadius, tireRadius) != 0) return false;
        if (carBrand != null ? !carBrand.equals(that.carBrand) : that.carBrand != null) return false;
        if (tireType != null ? !tireType.equals(that.tireType) : that.tireType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = carInfoId;
        result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
        temp = Double.doubleToLongBits(tireRadius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tireType != null ? tireType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carInfoId=" + carInfoId +
                ", carBrand='" + carBrand + '\'' +
                ", tireRadius=" + tireRadius +
                ", tireType='" + tireType + '\'' +
                '}';
    }
}
