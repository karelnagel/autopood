package com.example.autopood.models;

import com.example.autopood.DTOs.ParameterDto;

import javax.persistence.*;

@Entity
public class Parameter
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String type;
    private String brand;
    private String model;
    private String country;
    private String fuelType;
    private String bodyType;
    private String gearType;
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;


    public Parameter()
    {
    }

    @Override
    public String toString()
    {
        return name + '\n' +
                "type: " + type + '\n' +
                "country: " + country + '\n' +
                "brand: " + brand + '\n' +
                "model: " + model + '\n' +
                "bodyType: " + bodyType + '\n' +
                "gearType: " + gearType + '\n' +
                "fuelType: " + fuelType + '\n' +
                "price: " + minPrice +" - " +maxPrice+'\n' +
                "year: " + minYear +" - " +maxYear+'\n' +
                "mileage: " + minMileage +" - " +maxMileage+'\n' +
                "engineKW: " + minEngineKW +" - " +maxEngineKW+'\n' +
                "engine size: " + minEngineSize +" - " +maxEngineSize+'\n';
    }

    public void update(ParameterDto parameterDto)
    {
        this.name = parameterDto.getName();
        this.type = parameterDto.getType();
        this.brand = parameterDto.getBrand();
        this.model = parameterDto.getModel();
        this.bodyType = parameterDto.getBodyType();
        this.gearType = parameterDto.getGearType();
        this.country = parameterDto.getCountry();
        this.fuelType = parameterDto.getFuelType();
        this.minPrice = parameterDto.getMinPrice();
        this.maxPrice = parameterDto.getMaxPrice();
        this.maxMileage = parameterDto.getMaxMileage();
        this.minMileage = parameterDto.getMinMileage();
        this.maxYear = parameterDto.getMaxYear();
        this.minYear = parameterDto.getMinYear();
        this.minEngineKW = parameterDto.getMinEngineKW();
        this.maxEngineKW = parameterDto.getMaxEngineKW();
        this.minEngineSize = parameterDto.getMinEngineSize();
        this.maxEngineSize = parameterDto.getMaxEngineSize();
    }

    private boolean isNotNullOrEmpty(String string)
    {
        if (string!=null && !string.isEmpty()) return true;
        return false;
    }

    private boolean isNotNull(double arv)
    {
        if (arv != 0) return true;
        return false;
    }

    private boolean compareDouble(double value, double max, double min)
    {
        if (isNotNull(value))
        {
            if (isNotNull(min) && value < min) return false;
            if (isNotNull(max) && value > max) return false;
        }
        return true;
    }
    private boolean compareString(String kuulutus,String parameter )
{
    if (isNotNullOrEmpty(kuulutus))
    {
        if (isNotNullOrEmpty(parameter) && !kuulutus.toLowerCase().contains(parameter.toLowerCase()))
            return false;
    }
    return true;
}

    public boolean kasKuulutusSobib(Kuulutus kuulutus)
    {
        if (!compareString(kuulutus.getBrand(),brand)) return false;
        if (!compareString(kuulutus.getModel(),model)) return false;
        if (!compareString(kuulutus.getFuelType(), fuelType)) return false;
        if (!compareString(kuulutus.getType(), type)) return false;
        if (!compareString(kuulutus.getPood().getCountry(), country)) return false;
        if (!compareString(kuulutus.getBodyType(), bodyType)) return false;
        if (!compareString(kuulutus.getGearType(), gearType)) return false;

        if (!compareDouble(kuulutus.getPrice(), maxPrice, minPrice)) return false;
        if (!compareDouble(kuulutus.getYear(), maxYear, minYear)) return false;
        if (!compareDouble(kuulutus.getMileage(), maxMileage, minMileage)) return false;
        if (!compareDouble(kuulutus.getEngineKW(), maxEngineKW, minEngineKW)) return false;
        if (!compareDouble(kuulutus.getEngineSize(), maxEngineSize, minEngineSize)) return false;

        return true;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public void setId(long id)
    {
        this.id = id;
    }

    public Long getId()
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
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
}
