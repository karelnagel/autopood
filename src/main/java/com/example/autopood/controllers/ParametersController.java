package com.example.autopood.controllers;

import com.example.autopood.DTOs.ParameterDto;
import com.example.autopood.models.Parameter;
import com.example.autopood.repositorities.ParameterRepository;
import com.example.autopood.repositorities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class ParametersController
{
    private UserRepository userRepository;
    private ParameterRepository parameterRepository;

    ParametersController(@Autowired UserRepository userRepository, @Autowired ParameterRepository parameterRepository)
    {
        this.userRepository = userRepository;
        this.parameterRepository = parameterRepository;
    }

    @GetMapping(value = "/users/{userId}/parameters")
    public ResponseEntity<ArrayList<ParameterDto>> getParameters(@PathVariable Long userId)
    {
        if (userId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var parameters = parameterRepository.findByUserId(userId);
        var parameterDtos = parameters.stream().map(p -> new ParameterDto(p)).toArray();
        return new ResponseEntity(parameterDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}/parameters/{paraId}")
    public ResponseEntity<ParameterDto> getParameter(@PathVariable Long userId, @PathVariable Long paraId)
    {
        if (userId == null || paraId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId) || !parameterRepository.existsById(paraId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var parameter = parameterRepository.findById(paraId).get();
        if (!parameter.getUser().getId().equals(userId)) return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(new ParameterDto(parameter), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/parameters/{paraId}")
    public ResponseEntity<ParameterDto> putParameter(@PathVariable Long userId, @PathVariable Long paraId, @RequestBody ParameterDto parameterDto)
    {
        if (userId == null || paraId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId) || !parameterRepository.existsById(paraId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var parameter = parameterRepository.findById(paraId).get();
        if (!userId.equals(parameter.getUser().getId()))
            return new ResponseEntity(HttpStatus.CONFLICT);
        parameter.update(parameterDto);
        parameterRepository.save(parameter);
        return new ResponseEntity(new ParameterDto(parameter), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/parameters/")
    public ResponseEntity<ParameterDto> postParameter(@PathVariable Long userId, @RequestBody ParameterDto parameterDto)
    {
        if (userId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var user = userRepository.findById(userId).get();

        var parameter = new Parameter();
        parameter.setUser(user);
        parameter.update(parameterDto);
        parameter.setId(0);

        var resultParameter = parameterRepository.save(parameter);
        return new ResponseEntity(new ParameterDto(resultParameter), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/parameters/{paraId}")
    public ResponseEntity putParameter(@PathVariable Long userId, @PathVariable Long paraId)
    {
        if (userId == null || paraId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!userRepository.existsById(userId) || !parameterRepository.existsById(paraId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var parameter = parameterRepository.findById(paraId).get();
        if (!userId.equals(parameter.getUser().getId()))
            return new ResponseEntity(HttpStatus.CONFLICT);
        parameterRepository.delete(parameter);
        return new ResponseEntity(HttpStatus.OK);
    }
}

