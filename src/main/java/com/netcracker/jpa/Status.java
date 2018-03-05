package com.netcracker.jpa;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    private int statusId;

    @Basic
    @Column(name = "status_name")
    private String statusName;

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public Status() {
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        return statusId == status.statusId && (statusName != null ? statusName.equals(status.statusName) : status.statusName == null);
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
