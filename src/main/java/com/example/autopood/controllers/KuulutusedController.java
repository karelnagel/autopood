package com.example.autopood.controllers;

import com.example.autopood.DTOs.KuulutusDto;
import com.example.autopood.DTOs.ParameterDto;
import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.ParameterRepository;
import com.example.autopood.components.KuulutusSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class KuulutusedController
{
    private ParameterRepository parameterRepository;
    private KuulutusRepository kuulutusRepository;
    private KuulutusSearch kuulutusSearch;

    KuulutusedController(@Autowired ParameterRepository parameterRepository, @Autowired KuulutusRepository kuulutusRepository, @Autowired KuulutusSearch kuulutusSearch)
    {
        this.parameterRepository = parameterRepository;
        this.kuulutusRepository = kuulutusRepository;
        this.kuulutusSearch = kuulutusSearch;
    }

    @GetMapping(value = "/kuulutused")
    public ResponseEntity<ArrayList<KuulutusDto>> getKuulutused(@RequestParam(required=false) Long paraId, @RequestParam(required=false) String sortBy)
    {
        List<Kuulutus> kuulutused;
        if (paraId!=null){
            if (!parameterRepository.existsById(paraId))
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            var parameter = parameterRepository.findById(paraId).get();

            kuulutused = kuulutusSearch.findKuulutus(new ParameterDto(parameter),sortBy);

        }
        else
        {
            kuulutused = kuulutusRepository.findAll();
        }
        var kuulutusedDto = kuulutused.stream().map(k -> new KuulutusDto(k)).toArray();
        return new ResponseEntity(kuulutusedDto, HttpStatus.OK);
    }


    @GetMapping(value = "/kuulutused/{kuulutusId}")
    public ResponseEntity<KuulutusDto> getKuulutus(@PathVariable Long kuulutusId)
    {
        if (kuulutusId == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (!kuulutusRepository.existsById(kuulutusId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        var parameter = kuulutusRepository.findById(kuulutusId).get();
        return new ResponseEntity(new KuulutusDto(parameter), HttpStatus.OK);
    }

    @PostMapping(value = "/kuulutused")
    public ResponseEntity<ArrayList<KuulutusDto>> searchKuulutused(@RequestBody ParameterDto parameterDto, @RequestParam(required=false) String sortBy)
    {
        if (parameterDto==null)
            new ResponseEntity(HttpStatus.BAD_REQUEST);
        var kuulutused = kuulutusSearch.findKuulutus(parameterDto,sortBy);

        var kuulutusedDto = kuulutused.stream().map(k -> new KuulutusDto(k)).toArray();
        return new ResponseEntity(kuulutusedDto, HttpStatus.OK);
    }

}

