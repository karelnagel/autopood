package com.example.autopood.DTOs;

import com.example.autopood.models.User;

public class UserDto
{
    public UserDto(User user)
    {
        this.firstName = user.getEesnimi();
        this.lastName = user.getPerekonnanimi();
        this.id = user.getId();
    }
    public UserDto(){}

    private Long id;
    private String firstName;
    private String lastName;


    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
