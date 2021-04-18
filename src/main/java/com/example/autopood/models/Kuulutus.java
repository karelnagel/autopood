package com.example.autopood.models;

import javax.persistence.*;

@Entity
public class Kuulutus
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pood_id", referencedColumnName = "id", nullable = false)
    private Pood pood;
    private String type;
    private String brand;
    private String model;
    private int year;
    private int price;
    private String link;
    private double engineKW;
    private double engineSize;
    private String fuelType;
    private int mileage;

    public Kuulutus()
    {

    }

    public double getEngineSize()
    {
        return engineSize;
    }

    public void setEngineSize(double engineSize)
    {
        this.engineSize = engineSize;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public double getEngineKW()
    {
        return engineKW;
    }

    public void setEngineKW(double engineKW)
    {
        this.engineKW = engineKW;
    }

    public String getFuelType()
    {
        return fuelType;
    }

    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }

    public int getMileage()
    {
        return mileage;
    }

    public void setMileage(int milage)
    {
        this.mileage = milage;
    }

    public Kuulutus(String brand, String model, int year, int price, String link)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.link = link;
    }

    @Override
    public String toString()
    {
        return "Kuulutus{" +
                "mark='" + brand + '\'' +
                ", mudel='" + model + '\'' +
                ", aasta=" + year +
                ", hind=" + price +
                ", link='" + link + '\'' +
                '}';
    }

    public String toMessenger()
    {
        return brand + " - " + model + "\n" +
                year + " aasta \n" +
                price + "$ \n" +
                link;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String mark)
    {
        this.brand = mark;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String mudel)
    {
        this.model = mudel;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int aasta)
    {
        this.year = aasta;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int hind)
    {
        this.price = hind;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
