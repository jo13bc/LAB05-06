package com.miker.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Model implements Serializable {
    private List<Usuario> usuarios;
    private List<Aplication> aplications;
    private Usuario loggedUser;
    public final String[] country = { "India", "USA", "China", "Japan", "Other"};
    public final String[] position = { "Ingeniera(o)", "Concerje", "Administrada(o)r", "Operaria(o)", "Secretaria(o)"};

    public Model() {
        this.usuarios = new ArrayList<>();
        this.aplications = new ArrayList<>();
        this.generate_users();
        this.generate_aplications();
    }

    public void generate_users() {
        usuarios.addAll(
                Arrays.asList(
                        new Usuario("admin", "admin", true),
                        new Usuario("1234", "1234")
                )
        );
    }

    public void generate_aplications() {
        aplications.addAll(
                Arrays.asList(
                        new Aplication(1, "prueba", "1", "","","","","","","","","","",new Date()),
                        new Aplication(2, "prueba", "2", "","","","","","","","","","",new Date())
                )
        );
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Aplication> getAplications() {
        return aplications;
    }

    public void insertAplication(Aplication aplication){
        aplication.setId(aplications.size()+1);
        aplications.add(aplication);
    }

    public void updateAplication(Aplication aplication){
        for(Aplication a : aplications){
            if(a.getId() == aplication.getId()){
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

    public void setAplications(List<Aplication> aplications) {
        this.aplications = aplications;
    }

    public Usuario login(String user, String password) {
        Usuario usuario = null;
        for (Usuario x : usuarios) {
            if (x.getUser().equals(user) && x.getPassword().equals(password)) {
                usuario = x;
                break;
            }
        }
        loggedUser = usuario;
        return usuario;
    }
    public Usuario getLoggedUser() {
        return loggedUser;
    }
}
