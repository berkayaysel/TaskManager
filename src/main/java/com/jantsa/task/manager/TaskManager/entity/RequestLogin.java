package com.jantsa.task.manager.TaskManager.entity;

import jakarta.persistence.*;


public class RequestLogin {

    private Integer id;
    private Integer company_id;
    private String password;
    private String role;

    public RequestLogin(Integer company_id, String password, Integer id, String role) {
        this.company_id = company_id;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    public RequestLogin() {
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
