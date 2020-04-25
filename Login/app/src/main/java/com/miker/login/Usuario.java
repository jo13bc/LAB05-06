package com.miker.login;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String user;
    private String password;
    private boolean admin;

    public Usuario(String user, String password, boolean admin) {
        this.user = user;
        this.password = password;
        this.admin = admin;
    }

    public Usuario(String user, String password){
        this(user, password, false);
    }

    public Usuario(){
        this(null,null);
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

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", tipo=" + admin +
                '}';
    }
}
