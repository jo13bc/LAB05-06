package com.miker.login;

import java.io.Serializable;
import java.util.Date;

public class Aplication implements Serializable {
    private int id;
    private String first_name;
    private String last_name;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String code;
    private String country;
    private String email;
    private String phone_area;
    private String phone_number;
    private String posicion;
    private Date date;

    public Aplication(int id, String first_name, String last_name, String address_1, String address_2, String city, String state, String code, String country, String email, String phone_area, String phone_number, String posicion, Date date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.code = code;
        this.country = country;
        this.email = email;
        this.phone_area = phone_area;
        this.phone_number = phone_number;
        this.posicion = posicion;
        this.date = date;
    }

    public Aplication(){
        this(-1, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Aplication(int id){
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

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_area() {
        return phone_area;
    }

    public void setPhone_area(String phone_area) {
        this.phone_area = phone_area;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "form{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address_1='" + address_1 + '\'' +
                ", address_2='" + address_2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone_area='" + phone_area + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", posicion='" + posicion + '\'' +
                ", date=" + date +
                '}';
    }
}
