package com.example.autopood.models;

import com.example.autopood.DTOs.ParameterDto;

import javax.persistence.*;

@Entity
public class Parameter
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String brand;
    private String model;
    private String fuelType;
    private double minPrice;
    private double maxPrice;
    private double maxMileage;
    private double minMileage;
    private int maxYear;
    private int minYear;
    private int minEngineKW;
    private int maxEngineKW;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;


    public Parameter()
    {
    }

    public void update(ParameterDto parameterDto)
    {
        this.brand = parameterDto.getBrand();
        this.model = parameterDto.getModel();
        this.fuelType = parameterDto.getFuelType();
        this.minPrice = parameterDto.getMinPrice();
        this.maxPrice = parameterDto.getMaxPrice();
        this.maxMileage = parameterDto.getMaxMileage();
        this.minMileage = parameterDto.getMinMileage();
        this.maxYear = parameterDto.getMaxYear();
        this.minYear = parameterDto.getMinYear();
        this.minEngineKW = parameterDto.getMinEngineKW();
        this.maxEngineKW = parameterDto.getMaxEngineKW();
    }

    public boolean isNullOrEmpty(String string){
        if (string.equals(null) || string.isEmpty()) return true;
        return false;
    }
    public boolean kuulutusSobib(Kuulutus kuulutus)
    {
        if (!isNullOrEmpty(brand) && kuulutus.getMark() != brand) return false;
        if (!isNullOrEmpty(model) && kuulutus.getMudel() != model) return false;
        if (minPrice != 0 && kuulutus.getHind() < minPrice) return false;
        if (maxPrice != 0 && kuulutus.getHind() > maxPrice) return false;
        if (minYear != 0 && kuulutus.getAasta() < minYear) return false;
        if (maxYear != 0 && kuulutus.getAasta() > maxYear) return false;

        return true;
    }

    public String getFuelType()
    {
        return fuelType;
    }

    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }

    public double getMaxMileage()
    {
        return maxMileage;
    }

    public void setMaxMileage(double maxMileage)
    {
        this.maxMileage = maxMileage;
    }

    public double getMinMileage()
    {
        return minMileage;
    }

    public void setMinMileage(double minMileage)
    {
        this.minMileage = minMileage;
    }

    public int getMinEngineKW()
    {
        return minEngineKW;
    }

    public void setMinEngineKW(int minEngineKW)
    {
        this.minEngineKW = minEngineKW;
    }

    public int getMaxEngineKW()
    {
        return maxEngineKW;
    }

    public void setMaxEngineKW(int maxEngineKW)
    {
        this.maxEngineKW = maxEngineKW;
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

    public void setMaxPrice(double maxPrice)
    {
        this.maxPrice = maxPrice;
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

    @Override
    public String toString()
    {
        return "Kuulutuse parameetrid:" +
                "\nid: " + id +
                "\nMark: " + brand +
                "\nMudel: " + model +
                "\nKütus: " + fuelType +
                "\nMin hind: " + minPrice +
                "\nMax hind: " + maxPrice +
                "\nMin läbisõit: " + minMileage +
                "\nMax läbisõit: " + maxMileage +
                "\nMin aasta: " + minYear +
                "\nMax aasta: " + maxYear +
                "\nMin kW: " + minEngineKW +
                "\nMax kW: " + maxEngineKW;
        /*
        return "Kuulutuse parameetrid: " +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", maxMileage=" + maxMileage +
                ", minMileage=" + minMileage +
                ", maxYear=" + maxYear +
                ", minYear=" + minYear +
                ", minEngineKW=" + minEngineKW +
                ", maxEngineKW=" + maxEngineKW +
                '}';

         */
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
