package edu.miu.cs544.springstartproject.service;

import edu.miu.cs544.springstartproject.model.CountryRegion;
import edu.miu.cs544.springstartproject.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryRegionServiceImpl implements CountryRegionService {

    @Autowired
    private CountryRegionRepository repository;

    public List<CountryRegion> findAll() {
        return repository.findAll();
    }
}
