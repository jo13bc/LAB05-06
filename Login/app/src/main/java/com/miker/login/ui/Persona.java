package com.miker.login.ui;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private Date date;

    public Persona(int id, String first_name, String last_name, String email, Date date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.date = date;
    }

    public Persona() {
        this(-1, null, null, null, null);
    }

    public Persona(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFull_name(){
        return first_name + ' ' + last_name;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
