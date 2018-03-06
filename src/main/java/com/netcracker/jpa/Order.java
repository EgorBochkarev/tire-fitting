package com.netcracker.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;

    @Basic
    @Column(name = "rating")
    private int rating;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false)
    private Service serviceId;

    @OneToMany(mappedBy = "orderId")
    private List<StatusHistory> statusHistory;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    private Status statusId;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public List<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (rating != order.rating) return false;
        if (description != null ? !description.equals(order.description) : order.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + rating;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                '}';
    }
}
