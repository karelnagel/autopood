package com.example.autopood.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Pood
{
    @Id
    private String id;

    @OneToMany(mappedBy = "pood")
    private Set<Kuulutus> kuulutused;
    private String viimaneKuulutus;
    private String authKey;
    private String country;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Set<Kuulutus> getKuulutused()
    {
        return kuulutused;
    }

    public void setKuulutused(Set<Kuulutus> kuulutused)
    {
        this.kuulutused = kuulutused;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String nimi)
    {
        this.id = nimi;
    }

    public String getViimaneKuulutus()
    {
        return viimaneKuulutus;
    }

    public void setViimaneKuulutus(String viimaneKuulutus)
    {
        this.viimaneKuulutus = viimaneKuulutus;
    }

    public String getAuthKey()
    {
        return authKey;
    }

    public void setAuthKey(String authKey)
    {
        this.authKey = authKey;
    }

}