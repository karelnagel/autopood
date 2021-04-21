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
    private Double minPrice;
    private Double maxPrice;
    private Double maxMileage;
    private Double minMileage;
    private Integer maxYear;
    private Integer minYear;
    private Integer minEngineKW;
    private Integer maxEngineKW;
    private Integer minEngineSize;
    private Integer maxEngineSize;

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

    public Integer getMinEngineSize()
    {
        return minEngineSize;
    }

    public void setMinEngineSize(Integer minEngineSize)
    {
        this.minEngineSize = minEngineSize;
    }

    public Integer getMaxEngineSize()
    {
        return maxEngineSize;
    }

    public void setMaxEngineSize(Integer maxEngineSize)
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

    public Double getMinPrice()
    {
        return minPrice;
    }

    public void setMinPrice(Double minPrice)
    {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice()
    {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice)
    {
        this.maxPrice = maxPrice;
    }

    public Double getMaxMileage()
    {
        return maxMileage;
    }

    public void setMaxMileage(Double maxMileage)
    {
        this.maxMileage = maxMileage;
    }

    public Double getMinMileage()
    {
        return minMileage;
    }

    public void setMinMileage(Double minMileage)
    {
        this.minMileage = minMileage;
    }

    public Integer getMaxYear()
    {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear)
    {
        this.maxYear = maxYear;
    }

    public Integer getMinYear()
    {
        return minYear;
    }

    public void setMinYear(Integer minYear)
    {
        this.minYear = minYear;
    }

    public Integer getMinEngineKW()
    {
        return minEngineKW;
    }

    public void setMinEngineKW(Integer minEngineKW)
    {
        this.minEngineKW = minEngineKW;
    }

    public Integer getMaxEngineKW()
    {
        return maxEngineKW;
    }

    public void setMaxEngineKW(Integer maxEngineKW)
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
