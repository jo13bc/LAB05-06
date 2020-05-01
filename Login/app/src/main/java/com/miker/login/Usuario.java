package com.miker.login;

import com.miker.login.ui.Persona;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String user;
    private String password;
    private Persona persona;
    private boolean admin;

    public Usuario(String user, String password, Persona persona, boolean admin) {
        this.user = user;
        this.password = password;
        this.persona = persona;
        this.admin = admin;
    }

    public Usuario(String user, String password) {
        this(user, password, null, false);
    }

    public Usuario() {
        this(null, null, new Persona(), false);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", tipo=" + admin +
                '}';
    }
}