package com.miker.login;

import com.miker.login.ui.Persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements Serializable {
    private Map<String, Usuario> usuarios;
    private List<Aplication> aplications;
    private Usuario loggedUser;
    public final String[] country = {"India", "USA", "China", "Japan", "Other"};
    public final String[] position = {"Ingeniera(o)", "Concerje", "Administrada(o)r", "Operaria(o)", "Secretaria(o)"};

    public Model() {
        this.usuarios = new HashMap<>();
        this.aplications = new ArrayList<>();
        this.generate_users();
        this.generate_aplications();
    }

    public void generate_users() {
        usuarios.put("1234", new Usuario("1234", "1234"));
        usuarios.put("admin", new Usuario("admin", "admin", new Persona(1, "admin", "", "admin@prueba.com", new Date()), true));
    }

    public void generate_aplications() {
        aplications.addAll(
                Arrays.asList(
                        new Aplication(1, "prueba", "1", "", "", "", "", "", "", "", "", "", "", new Date()),
                        new Aplication(2, "prueba", "2", "", "", "", "", "", "", "", "", "", "", new Date())
                )
        );
    }

    public List<Aplication> getAplications() {
        return aplications;
    }

    public void insertAplication(Aplication aplication) {
        aplication.setId(aplications.size() + 1);
        aplications.add(aplication);
    }

    public void updateAplication(Aplication aplication) {
        for (Aplication a : aplications) {
            if (a.getId() == aplication.getId()) {
                a.setFirst_name(aplication.getFirst_name());
                a.setLast_name(aplication.getLast_name());
                a.setAddress_1(aplication.getAddress_1());
                a.setAddress_2(aplication.getAddress_2());
                a.setCity(aplication.getCity());
                a.setState(aplication.getState());
                a.setCode(aplication.getCode());
                a.setCountry(aplication.getCountry());
                a.setEmail(aplication.getEmail());
                a.setPhone_area(aplication.getPhone_area());
                a.setPhone_number(aplication.getPhone_number());
                a.setPosicion(aplication.getPosicion());
                a.setDate(aplication.getDate());
                break;
            }
        }
    }

    public void insertUser(Usuario usuario, String confirmation) throws Exception {
        if (!usuario.getPassword().equals(confirmation)) {
            throw new Exception("¡Las contraseñas no coinciden!");
        }
        if (usuarios.containsKey(usuario.getUser())) {
            throw new Exception("¡Ya existe este usuario registrado!");
        }
        usuario.getPersona().setId(usuarios.size() + 1);
        usuarios.put(usuario.getUser(), usuario);
    }

    public void updateUser(Usuario usuario, String confirmation) throws Exception {
        if (!usuario.getPassword().equals(confirmation)) {
            throw new Exception("¡Las contraseñas no coinciden!");
        }
        if (!usuarios.containsKey(usuario.getUser())) {
            throw new Exception("¡No existe ningun registro con este usuario!");
        }
        Usuario u = usuarios.get(usuario.getUser());
        u.getPersona().setFirst_name(usuario.getPersona().getFirst_name());
        u.getPersona().setLast_name(usuario.getPersona().getLast_name());
        u.getPersona().setEmail(usuario.getPersona().getEmail());
        u.getPersona().setDate(usuario.getPersona().getDate());
        u.setUser(usuario.getPersona().getLast_name());
        u.setPassword(usuario.getPersona().getEmail());
    }

    public void setAplications(List<Aplication> aplications) {
        this.aplications = aplications;
    }

    public Usuario login(String user, String password) throws Exception {
        Usuario usuario = null;
        if (!usuarios.containsKey(user)) {
            throw new Exception("¡No existe ningun registro con este usuario!");
        }
        Usuario x = usuarios.get(user);
        if (x.getPassword().equals(password)) {
            usuario = x;
            loggedUser = x;
        }
        return usuario;
    }

    public Usuario getLoggedUser() {
        return loggedUser;
    }
}
