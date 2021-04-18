package com.example.autopood.DTOs;

import com.example.autopood.models.Parameter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDto
{
    public ParameterDto(Parameter parameter)
    {
        this.id = parameter.getId();
        this.brand = parameter.getBrand();
        this.name = parameter.getName();
        this.type = parameter.getType();
        this.model = parameter.getModel();
        this.country = parameter.getCountry();
        this.fuelType = parameter.getFuelType();
        this.minPrice = parameter.getMinPrice();
        this.maxPrice = parameter.getMaxPrice();
        this.maxMileage = parameter.getMaxMileage();
        this.minMileage = parameter.getMinMileage();
        this.maxYear = parameter.getMaxYear();
        this.minYear = parameter.getMinYear();
        this.minEngineKW = parameter.getMinEngineKW();
        this.maxEngineKW = parameter.getMaxEngineKW();
        this.minEngineSize = parameter.getMinEngineSize();
        this.maxEngineSize = parameter.getMaxEngineSize();
    }
    public ParameterDto(){}

    private Long id;
    private String name;
    private String type;
    private String brand;
    private String model;
    private String fuelType;
    private String country;
    private double minPrice;
    private double maxPrice;
    private double maxMileage;
    private double minMileage;
    private int maxYear;
    private int minYear;
    private int minEngineKW;
    private int maxEngineKW;
    private int minEngineSize;
    private int maxEngineSize;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getMinEngineSize()
    {
        return minEngineSize;
    }

    public void setMinEngineSize(int minEngineSize)
    {
        this.minEngineSize = minEngineSize;
    }

    public int getMaxEngineSize()
    {
        return maxEngineSize;
    }

    public void setMaxEngineSize(int maxEngineSize)
    {
        this.maxEngineSize = maxEngineSize;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public String getFuelType()
    {
        return fuelType;
    }

    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
