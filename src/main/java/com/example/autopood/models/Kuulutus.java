package com.example.autopood.models;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Kuulutus
{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pood_id", referencedColumnName = "id", nullable = false)
    private Pood pood;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> soovitatud;

    String mark;
    String mudel;
    int aasta;
    int hind;
    String link;
    double mootor;
    String kütus;
    int labisoit;

    public Kuulutus()
    {

    }

    public Kuulutus(String mark, String mudel, int aasta, int hind, String link)
    {
        this.mark = mark;
        this.mudel = mudel;
        this.aasta = aasta;
        this.hind = hind;
        this.link = link;
    }
    @Override
    public String toString()
    {
        return "Kuulutus{" +
                "mark='" + mark + '\'' +
                ", mudel='" + mudel + '\'' +
                ", aasta=" + aasta +
                ", hind=" + hind +
                ", link='" + link + '\'' +
                '}';
    }

    public String toMessenger()
    {
        return mark+" - "+mudel+ "\n" +
                aasta +" aasta \n"  +
                hind +"$ \n" +
                link ;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public String getMudel()
    {
        return mudel;
    }

    public void setMudel(String mudel)
    {
        this.mudel = mudel;
    }

    public int getAasta()
    {
        return aasta;
    }

    public void setAasta(int aasta)
    {
        this.aasta = aasta;
    }

    public int getHind()
    {
        return hind;
    }

    public void setHind(int hind)
    {
        this.hind = hind;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Pood getPood()
    {
        return pood;
    }

    public void setPood(Pood pood)
    {
        this.pood = pood;
    }
}
