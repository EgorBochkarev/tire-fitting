package com.netcracker.dto;


public class AuthorizationAppWrapper {

    String login;

    String password;

    int userId;

    int serviceId;

    public AuthorizationAppWrapper(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthorizationAppWrapper() {
    }

    public AuthorizationAppWrapper(String login, String password, int userId, int serviceId) {
        this.login = login;
        this.password = password;
        this.userId = userId;
        this.serviceId = serviceId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "AuthorizationAppWrapper{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                '}';
    }
}
