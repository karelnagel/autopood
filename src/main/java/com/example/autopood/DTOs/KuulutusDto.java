package com.example.autopood.DTOs;

import com.example.autopood.models.Kuulutus;

public class KuulutusDto
{
    private Long id;
    private String pood;
    private String type;
    private String brand;
    private String model;
    private String bodyType;
    private String gearType;
    private String picture;
    private int year;
    private int price;
    private String link;
    private double engineKW;
    private double engineSize;
    private String fuelType;
    private int mileage;
    private String country;

    public KuulutusDto()
    {
    }

    public KuulutusDto(Kuulutus kuulutus)
    {
        this.id = kuulutus.getId();
        this.type = kuulutus.getType();
        this.brand = kuulutus.getBrand();
        this.model = kuulutus.getModel();
        this.bodyType = kuulutus.getBodyType();
        this.gearType = kuulutus.getGearType();
        this.year = kuulutus.getYear();
        this.price = kuulutus.getPrice();
        this.link = kuulutus.getLink();
        this.engineKW = kuulutus.getEngineKW();
        this.fuelType = kuulutus.getFuelType();
        this.mileage = kuulutus.getMileage();
        this.pood = kuulutus.getPood().getId();
        this.engineSize =kuulutus.getEngineSize();
        this.country = kuulutus.getPood().getCountry();
        this.picture = kuulutus.getPicture();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public double getEngineKW()
    {
        return engineKW;
    }

    public void setEngineKW(double engineKW)
    {
        this.engineKW = engineKW;
    }

    public double getEngineSize()
    {
        return engineSize;
    }

    public void setEngineSize(double engineSize)
    {
        this.engineSize = engineSize;
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

    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
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

    public void setYear(int year)
    {
        this.year = year;
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

    public String getPood()
    {
        return pood;
    }

    public void setPood(String pood)
    {
        this.pood = pood;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }
}
