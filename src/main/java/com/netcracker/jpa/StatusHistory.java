package com.netcracker.jpa;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "statuses_history")
public class StatusHistory {

    @Id
    @GeneratedValue
    @Column(name = "status_history_id")
    private int statusHistoryId;

    @Basic
    @Column(name = "change_date")
    private Timestamp changeDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order orderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    private Status statusId;

    public StatusHistory() {
    }

    public int getStatusHistoryId() {
        return statusHistoryId;
    }

    public void setStatusHistoryId(int statusHistoryId) {
        this.statusHistoryId = statusHistoryId;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
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

        StatusHistory that = (StatusHistory) o;

        if (statusHistoryId != that.statusHistoryId) return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusHistoryId;
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatusHistory{" +
                "statusHistoryId=" + statusHistoryId +
                ", changeDate=" + changeDate +
                '}';
    }
}
