package com.example.autopood;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    private Integer id;
    private String lastAction;
    private String brand;
    private String model;
    private double minPrice;
    private double maxPrice;
    private double maxMilage;
    private double minMilage;
    private int maxYear;
    private int minYear;


    @Override
    public String toString(){
        return "Hind: "+minPrice+"-"+maxPrice+"\n"+
                "Labisoit: "+minMilage+"-"+maxMilage+"\n"+
                "Aasta: "+minYear+"-"+maxYear+"\n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMinPrice()
    {
        return minPrice;
    }

    public void setMinPrice(double minPrice)
    {
        this.minPrice = minPrice;
    }

    public double getMaxPrice()
    {
        return maxPrice;
    }

    public void setMaxPrice(double endingPrice)
    {
        this.maxPrice = endingPrice;
    }

    public double getMaxMilage()
    {
        return maxMilage;
    }

    public void setMaxMilage(double maxMilage)
    {
        this.maxMilage = maxMilage;
    }

    public double getMinMilage()
    {
        return minMilage;
    }

    public void setMinMilage(double minMilage)
    {
        this.minMilage = minMilage;
    }

    public int getMaxYear()
    {
        return maxYear;
    }

    public void setMaxYear(int maxYear)
    {
        this.maxYear = maxYear;
    }

    public int getMinYear()
    {
        return minYear;
    }

    public void setMinYear(int minYear)
    {
        this.minYear = minYear;
    }

    public String getLastAction()
    {
        return lastAction;
    }

    public void setLastAction(String lastAction)
    {
        this.lastAction = lastAction;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }
}