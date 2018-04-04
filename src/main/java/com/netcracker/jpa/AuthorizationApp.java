package com.netcracker.jpa;

import javax.persistence.*;

@Entity
@Table(name = "authorization_app")
public class AuthorizationApp {

    @Id
    @GeneratedValue
    @Column(name = "authorization_id")
    private int authorizationAppId;

    @Basic
    @Column(name = "login", unique = true)
    private String login;

    @Basic
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    private Service serviceId;

    public AuthorizationApp(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthorizationApp() {
    }

    public int getAuthorizationId() {
        return authorizationAppId;
    }

    public void setAuthorizationId(int authorizationId) {
        this.authorizationAppId = authorizationId;
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

}
