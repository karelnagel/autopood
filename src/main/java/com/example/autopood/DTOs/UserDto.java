package com.example.autopood.DTOs;

import com.example.autopood.models.User;

public class UserDto
{
    public UserDto(User user)
    {
        this.firstName = user.getEesnimi();
        this.lastName = user.getPerekonnanimi();
        this.id = user.getId();
        if (user.getParameters() != null)
            this.parameters = user.getParameters().stream().map(p -> new ParameterDto(p)).toArray();
    }

    public UserDto()
    {
    }

    private Long id;
    private String firstName;
    private String lastName;
    private Object[] parameters;


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


    public Object[] getParameters()
    {
        return parameters;
    }

    public void setParameters(Object[] parameters)
    {
        this.parameters = parameters;
    }
}
