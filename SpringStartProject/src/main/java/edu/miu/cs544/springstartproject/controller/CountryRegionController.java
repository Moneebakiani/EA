package edu.miu.cs544.springstartproject.controller;

import edu.miu.cs544.springstartproject.model.CountryRegion;
import edu.miu.cs544.springstartproject.service.CountryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryRegionController {

    @Autowired
    private CountryRegionService service;

    @GetMapping("/countryregions")
    public List<CountryRegion> findAll(){
        return service.findAll();
    }
}
