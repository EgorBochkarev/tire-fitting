package com.netcracker.dto;


public class OrderDto {

    private int orderId;

    private String status;

    private String description;

    private String location;

    private int rating;

    private int userId;

    private int serviceId;

    private String serviceName;

    private String userName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderDto(String status) {
        this.status = status;
    }

    public OrderDto() {
    }

    public OrderDto(int orderId, String location, String status, String description, String serviceName, String userName) {
        this.orderId = orderId;
        this.location = location;
        this.status = status;
        this.description = description;
        this.serviceName = serviceName;
        this.userName = userName;
    }

    public OrderDto(int orderId, String location,String status, String description, int rating, int userId, int serviceId) {
        this.orderId = orderId;
        this.location = location;
        this.status = status;
        this.description = description;
        this.rating = rating;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                '}';
    }
}
