package com.example.autopood.controllers;

import com.example.autopood.DTOs.UserDto;
import com.example.autopood.repositorities.ParameterRepository;
import com.example.autopood.repositorities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UsersController
{
    private UserRepository userRepository;
    private ParameterRepository parameterRepository;

    UsersController(@Autowired UserRepository userRepository, @Autowired ParameterRepository parameterRepository)
    {
        this.userRepository = userRepository;
        this.parameterRepository = parameterRepository;
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId)
    {
        if (userId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var user = userRepository.findById(userId).get();
        user.setParameterList(parameterRepository.findByUserId(userId));
        var userDto = new UserDto(user);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> postUser(@PathVariable Long userId, @RequestBody UserDto userDto)
    {
        if (userId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var user = userRepository.findById(userId).get();
        user.setEesnimi(userDto.getFirstName());
        user.setPerekonnanimi(userDto.getLastName());
        userRepository.save(user);
        return new ResponseEntity(new UserDto(user),HttpStatus.OK);
    }
}

