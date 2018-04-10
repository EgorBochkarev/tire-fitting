package com.netcracker.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue
    @Column(name = "service_id")
    private int serviceId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "location")
    private String location;

    @Basic
    @Column(name = "serving_staff")
    private int servingStaff;

    @Basic
    @Column(name = "total_rating")
    private double totalRating;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders;

    public Service(String name, String location, int servingStaff) {
        this.name = name;
        this.location = location;
        this.servingStaff = servingStaff;
    }

    public Service() {
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (serviceId != service.serviceId) return false;
        if (servingStaff != service.servingStaff) return false;
        if (Double.compare(service.totalRating, totalRating) != 0) return false;
        if (name != null ? !name.equals(service.name) : service.name != null) return false;
        if (location != null ? !location.equals(service.location) : service.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serviceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + servingStaff;
        temp = Double.doubleToLongBits(totalRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", servingStaff=" + servingStaff +
                ", totalRating=" + totalRating +
                '}';
    }
}
