package com.netcracker.dto;

public class ServiceDto {

    private int serviceId;

    private String name;

    private String location;

    private int servingStaff;

    private double totalRating;

    public ServiceDto(int serviceId, String name, String location, int servingStaff, double totalRating) {
        this.serviceId = serviceId;
        this.name = name;
        this.location = location;
        this.servingStaff = servingStaff;
        this.totalRating = totalRating;
    }

    public ServiceDto() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public int getServingStaff() {
        return servingStaff;
    }

    public void setServingStaff(int servingStaff) {
        this.servingStaff = servingStaff;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    @Override
    public String toString() {
        return "ServiceWrapper{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", servingStaff=" + servingStaff +
                ", totalRating=" + totalRating +
                '}';
    }
}
