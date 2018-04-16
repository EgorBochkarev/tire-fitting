package com.netcracker.dto;


public class OrderDto {

    private int orderId;

    private String status;

    private String description;

    private int rating;

    private int userId;

    private int serviceId;

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

    public OrderDto(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public OrderDto(int orderId, String status, String description, int rating, int userId, int serviceId) {
        this.orderId = orderId;
        this.status = status;
        this.description = description;
        this.rating = rating;
        this.userId = userId;
        this.serviceId = serviceId;
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderWrapper{" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                '}';
    }
}
