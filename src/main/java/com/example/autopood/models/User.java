package com.example.autopood.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User
{
    @Id
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Kuulutus> soovitatud;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<KuulutusParameters> parameterList;
    private String eesnimi;
    private String perekonnanimi;
    private String lastAction="";

    public User() {
    }



    @Override
    public String toString(){
        return "Kasutaja ID: "+id+"\n"+
                "Nimi: "+"\n";
    }

    public Set<KuulutusParameters> getParameters() {
        return parameterList;
    }

    public void addParameters(KuulutusParameters parameters) {
        parameterList.add(parameters);
    }

    public void removeParameters(KuulutusParameters parameters) {
        parameterList.remove(parameters);
    }
    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String s) {
        this.lastAction = s;
    }
}