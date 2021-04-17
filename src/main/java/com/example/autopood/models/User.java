package com.example.autopood.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User
{
 @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Kuulutus> soovitatud;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Parameter> parameterList;
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

    public String getEesnimi() {
        return eesnimi;
    }

    public void setEesnimi(String eesnimi) {
        this.eesnimi = eesnimi;
    }

    public String getPerekonnanimi() {
        return perekonnanimi;
    }

    public void setPerekonnanimi(String perekonnanimi) {
        this.perekonnanimi = perekonnanimi;
    }

    public Set<Parameter> getParameters() {
        return parameterList;
    }

    public void addParameters(Parameter parameters) {
        parameterList.add(parameters);
    }

    public void removeParameters(Parameter parameters) {
        parameterList.remove(parameters);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String s) {
        this.lastAction = s;
    }
}